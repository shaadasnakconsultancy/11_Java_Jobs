package com.snak.Repository;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.snak.Services.ResultExpenseEndBoundedExcelReader;
import com.snak.Services.applicationPropertiesService;
import com.snak.UserDefiendExceptionHandler$Logging.GlobalExceptionHandler_AND_EmailSender;
import com.snak.UserDefiendExceptionHandler$Logging.PrintLog_AND_SendEmail_Exception;
import com.snak.dto.Result_Expense; 

 @Repository
public class ResultExpense_repository{  
	@Autowired
	Excel_Job_Tracker_TableInDB_Updater Excel_Job_Tracker_TableInDB_Updater;
	@Autowired
	applicationPropertiesService applicationPropertiesService;
	static Logger logger=LoggerFactory.getLogger(ResultExpense_repository.class); 
	@Autowired 
	 DataSource DataSource;
	@Autowired
	GlobalExceptionHandler_AND_EmailSender GlobalExceptionHandler_AND_EmailSender;
	/*
	@Value("${jdbc.driverClassName}")
	private  String databaseClassName;
	
	 @Value("${jdbc.databaseURL}")
	 private  String databaseURL;
	@Value("${jdbc.databaseUserName}")
	private  String databaseUserName;
	@Value("${jdbc.databasePassword}")
	private  String databasePassword;
public  void createRecords(List<DMSStockTransactionDetails> list)
	{
		  System.out.println("createRecords");
		  System.out.println(DataSource);
		PreparedStatement pstmt=null;
		Connection conn = null;
		try
		{
			conn =   DataSource.getConnection();
			 System.out.println(conn);
			// set AutoCommit to false
			conn.setAutoCommit(false);
			String sql="insert into dbo.TblDMSDealerDailyStockImport (Zone_Name,state,district,town,DealerCode,DealerName,"
					+ "SIEModel,Stock_Date,Total_Stock_Qty,Total_InTransit_Qty,dealer_act_code) values(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			DMSStockTransactionDetails details = null;
			for(int a=0;a<list.size();a++)
			{
				details  =(DMSStockTransactionDetails)list.get(a);
				pstmt.setString(1, details.getZone());
				pstmt.setString(2, details.getState());
				pstmt.setString(3, details.getDistrict());
				pstmt.setString(4, details.getTown());
				pstmt.setString(5, details.getDealerCode());
				pstmt.setString(6, details.getDealerName());
				pstmt.setString(7, details.getSieModel());
				pstmt.setDate(8, new java.sql.Date(details.getStockDate().getTime()));
				if(null!=details.getTotalStockQty())
				{
					pstmt.setDouble(9, details.getTotalStockQty().doubleValue());
				}
				else
				{
					pstmt.setNull(9, Types.DOUBLE);
				}
				if(null!=details.getTotalInTransitQty())
				{
					pstmt.setDouble(10, details.getTotalInTransitQty().doubleValue());
				}
				else
				{
					pstmt.setNull(10, Types.DOUBLE);
				}
				pstmt.setString(11, details.getDealerActCode());
				pstmt.addBatch();
				details = null;
			}
			int i[]=pstmt.executeBatch();
			
			for(int a:i) {
				System.out.println(a);
			}
			
			pstmt.close();pstmt=null;
			details = null;
			// commit transaction
			conn.commit();
		}
		catch(Exception e)
		{
			try
			{
				if(null!=conn)
					conn.rollback();
			}
			catch(SQLException e1)
			{
				
			}
			
		}
		finally
		{
			try
			{
				if(null!=pstmt)
					pstmt.close();
				if(null!=conn)
					conn.close();
			}
			catch(SQLException e)
			{
				
			}
			pstmt = null;
			list = null;
		}
	}*/
	
	int totalDeletedRows=0;
	public String match_FY$Actual_Remove_Insert(List<Result_Expense> list,File file){
	
	CallableStatement cstmt =null;
	String sql=null;
	PreparedStatement deleteStmt=null;
	PreparedStatement pstmt=null;
	Connection conn = null;
	try {
	System.out.println (list.stream().collect (Collectors.counting()));
	
	list = list.stream()
		    .filter(n ->
		        n.getDataType() != null &&
		        n.getFy() != null &&
		        "ACTUAL".equalsIgnoreCase(n.getDataType().trim()) &&
		        !n.getFy().trim().isEmpty()
		    )
		    .collect(Collectors.toList());
	System.out.println (list.stream().collect (Collectors.counting()));
 
	System.out.println("match_FY$Actual_Remove_Insert");
//	1.    if DB with name 'FinanceDashboard' does not exist

		System.out.println("in try");
		conn = DataSource.getConnection();
		conn.setAutoCommit(false);
		sql="SELECT DB_NAME()";
		pstmt= conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		    System.err.println("Connected DB = " + rs.getString(1));
		}
		
		
		////checking below is that DB with "FinanceDashboard" name exist or not
		sql="SELECT name FROM sys.databases WHERE  name='FinanceDashboard' ";
		pstmt= conn.prepareStatement(sql);
		pstmt.execute();
		
		if(!pstmt.getResultSet().next()) {
			
			System.out.println("DB does not exist  ");
			//log it
		      logger.info("database doesnot exist in sqlserver with name -> "+ "FinanceDashboard" +"   ");
		      GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                    "ResultExpense_repository.class",
	                    "match_FY$Actual_Remove_Insert()",
	                  file.getName(),
	                    "database doesnot exist in sqlserver with name -> "+ "FinanceDashboard" +"",
	                   ""
	                    
	         )   );
		      return null;
			//System.exit(0);

		}else {
			System.out.println("DB FinanceDashboard is present ");
		}
//		if(!pstmt.getResultSet().next()) {
//			
//			 return null;
//		}else { 
//		}

		//END//checking below is that DB with "FinanceDashboard" name exist or not
		
		
		////checking below is that tabel with "PL_ResultRoyalty" name exist or not
		sql = "SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'PL_ResultExpense'";
		pstmt = conn.prepareStatement(sql);
		  rs = pstmt.executeQuery();

		if (!rs.next()) {

		    System.out.println("Table does not exist");

		    logger.info("Table does not exist in FinanceDashboard DB with name -> PL_ResultExpense");

		    GlobalExceptionHandler_AND_EmailSender.handleException(
		        new PrintLog_AND_SendEmail_Exception(
		            "ResultExpense_repository.class",
		            "match_FY$Actual_Remove_Insert()",
		            file.getName(),
		            "Table does not exist in SQL Server with name -> PL_ResultExpense",
		            ""
		        )
		    );

		    return null;

		} else {
		    System.out.println("Table PL_ResultExpense is present");
		}

		//END//checking below is that tabel with "PL_ResultRoyalty" name exist or not
		
		
	
	
//	2. deleting all record of table which matches with excel FY and Actual


    // DELETE
		int DELETEbatchSize = 100;
		int DELETEcount = 0; 
    deleteStmt = conn.prepareStatement("DELETE FROM PL_ResultExpense WHERE FY=? AND DataType=?");
    System.out.println("DELETEING OLD RECORD");
    Set<String> pairs = list.stream()
        .map(n -> n.getFy().trim() + "|" + n.getDataType().trim())
        .collect(Collectors.toSet());
    System.err.println("->>>> Unique FY-DataType pairs: " + pairs.size());
    
    for (String pair : pairs) {
        String[] p = pair.split("\\|");
        System.out.println(p[0]+"    "+p[1]);
        deleteStmt.setString(1, p[0]);
        deleteStmt.setString(2, p[1]);
        deleteStmt.addBatch();
        DELETEcount++;

	    if (DELETEcount == DELETEbatchSize) {   // better than %
	      int i[]=  deleteStmt.executeBatch();
	        deleteStmt.clearBatch();     // important
	        DELETEcount = 0;
	        for (int r : i) {
		        totalDeletedRows += r;
		    }
		    System.out.println("executing DELETE batch with 100 queries");
	    }
	}

	/*   EXECUTE REMAINING RECORDS when batch did not filled with 100 statements */
	if (DELETEcount > 0) {
	int i[]=	deleteStmt.executeBatch();
		deleteStmt.clearBatch();
		DELETEcount = 0;
		 for (int r : i) {
		        totalDeletedRows += r;
		    }
	    System.out.println("executing DELETE batch with less than 100 queries");
	} 
	pairs=null;
			
//	3.	 storing excel data of fy and Actual in table in batches of 100
			  sql="insert into PL_ResultExpense(Sno,FY,DataType,Model,Country,[1st_half],[2nd_half],[Model Description],[Group],"
					+ "[Category],[Apr],[May],[Jun],[Jul],[Aug],[Sep],[Oct],[Nov],[Dec],[Jan],[Feb],[Mar],"
					+ "[Monthly],Accumlated,[Last_Month])"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int batchSize = 100;
			int count = 0; 
			pstmt=conn.prepareStatement(sql);

            int totalUpdatedRows=0;
            
			for (Result_Expense Result_Expense : list) {
 System.out.println(Result_Expense.toString());
				pstmt.setString(1, Result_Expense.getSno());
				pstmt.setString(2, Result_Expense.getFy());
				pstmt.setString(3, Result_Expense.getDataType());
				pstmt.setString(4, Result_Expense.getModel());
				pstmt.setString(5, Result_Expense.getCountry());
				pstmt.setString(6, Result_Expense.getFirstHalf());
				pstmt.setString(7, Result_Expense.getSecondHalf());
				pstmt.setString(8, Result_Expense.getModelDiscription());
				pstmt.setString(9, Result_Expense.getGroup());
				pstmt.setString(10, Result_Expense.getCategory());
				pstmt.setString(11, Result_Expense.getApr());
				pstmt.setString(12, Result_Expense.getMay());
				pstmt.setString(13, Result_Expense.getJun());
				pstmt.setString(14, Result_Expense.getJul());
				pstmt.setString(15, Result_Expense.getAug());
				pstmt.setString(16, Result_Expense.getSep());
				pstmt.setString(17, Result_Expense.getOct());
				pstmt.setString(18, Result_Expense.getNov());
				pstmt.setString(19, Result_Expense.getDec());
				pstmt.setString(20, Result_Expense.getJan());
				pstmt.setString(21, Result_Expense.getFeb());
				pstmt.setString(22, Result_Expense.getMar());
				pstmt.setString(23, Result_Expense.getMonthly());
				pstmt.setString(24, Result_Expense.getAverage());
				pstmt.setString(25, Result_Expense.getLastMonth());
				
//				 // ⭐ Handle Average / Accumulated
//			    if ("Standard Cost for Royality Calculation".equalsIgnoreCase(Result_Royalty.getCategory())) {
//			        pstmt.setString(23,  Result_Royalty.getRoyalityPercentage());   // Royality %age
//			        pstmt.setString(24, "0");     // Monthly
//			        pstmt.setString(25, "0");     // LastMonth
//			    } else if("Royality Cost".equalsIgnoreCase(Result_Royalty.getCategory())){
//			    	pstmt.setString(23,  "0");   // Royality %age
//			        pstmt.setString(24, Result_Royalty.getMonthly());     // Monthly
//			        pstmt.setString(25, Result_Royalty.getLastMonth());     // LastMonth
//			    }
			
				

			    pstmt.addBatch();
			    count++;

			    if (count == batchSize) {   // better than %
			        int i[]=pstmt.executeBatch();
//			        for(int ii:i) {System.out.println(ii);};
			        pstmt.clearBatch();     // important
			        count = 0;
			        for (int ra  :  i) {
			            totalUpdatedRows += ra ;
			        }
				    System.out.println("executing batch with 100 queries");
			    }
			}

			/*   EXECUTE REMAINING RECORDS when batch did not filled with 100 statements */
			if (count > 0) {
			   int i[]= pstmt.executeBatch();
			    pstmt.clearBatch();
			    count = 0;
			    for (int ra  :  i) {
		            totalUpdatedRows += ra ;
		        }
			    System.out.println("executing batch with less than 100 queries");
			}
  /*
			try {
			// CALL STORED PROCEDURE 
			System.out.println("Calling stored procedure usp_Load_Fact_ResultRoyalty");

			  cstmt = conn.prepareCall("{call dbo.usp_Load_Fact_Result_Royalty}");
			  boolean result = cstmt.execute();
				 cstmt.close();
				}catch(Exception e) {
					e.printStackTrace() ;
					   logger.info("Exception in calling usp_Load_Fact_ResultRoyalty procedure in match_FY$Actual_Remove_Insert() method > "+ ResultRoyalty_repository.class.getName()+ "  ");

						conn.rollback();
						
						GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
			                    "ResultRoyalty_repository.class",
			                    "match_FY$Actual_Remove_Insert()",
			                    file.getName(),
			                    "Exception in calling usp_Load_Fact_ResultRoyalty procedure in match_FY$Actual_Remove_Insert() method  "+ "Java_Job" +"",
			                   ""
			                    
			         )   ); 
					  // System.exit(0);
					   
				}
				*/
	/* COMMIT TRANSACTION */
			conn.commit();
			//updating excel_job_tracker and no. of deleted rows and updated rows
			 Excel_Job_Tracker_TableInDB_Updater.updatingExcelJobTrackr_DeletedRowsAndInsertedRowsColumns(file,"Result Expense",applicationPropertiesService.getProperty("job.batch.id"),totalDeletedRows,totalUpdatedRows);
			
		}
		catch(Exception e){
			e.printStackTrace() ;
			   logger.info("Exception in doing DB task in match_FY$Actual_Remove_Insert() method > "+ ResultExpenseEndBoundedExcelReader.class.getName()+ "  ");

System.out.println("catch block");
				  e.printStackTrace() ;
			try
			{
				if(null!=conn)
					conn.rollback();
			}
			catch(SQLException e1)
			{
				logger.info("connection is already null, cant roll back in ResultExpense_repository.class");
			}
			GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                    "ResultExpense_repository.class",
	                    "match_FY$Actual_Remove_Insert()",
	                    file.getName(),
	                    "not able to delete or insert in table 'PL_ResultExpense' in DB -> "+ "FinanceDashboard" +"",
	                   "so processing other sheets data and storing it in DB -> "+e.toString()+ "  " +e.getMessage()
	                    
	           ) );
			return null;
//			System.exit(0);
		}
		finally
		{
			try
			{
				if(null!=pstmt)
					pstmt.close();
				if(null!=conn)
					conn.close();
			}
			catch(SQLException e)
			{

				logger.info("connection and preparedStatement is already null, cant roll back in ResultExpense_repository.class");
			}
			pstmt = null;
			list.clear();
			list = null;
		}
	return "completed";
}

	

	public String match_FY$Actual_UpdateStatement(List<Result_Expense> list,File file){
	
	CallableStatement cstmt =null;
	String sql=null;
	PreparedStatement deleteStmt=null;
	PreparedStatement pstmt=null;
	Connection conn = null;
	try {
	System.out.println (list.stream().collect (Collectors.counting()));
	
	list = list.stream()
		    .filter(n ->
		        n.getDataType() != null &&
		        n.getFy() != null &&
		        "ACTUAL".equalsIgnoreCase(n.getDataType().trim()) &&
		        !n.getFy().trim().isEmpty()
		    )
		    .collect(Collectors.toList());
	System.out.println (list.stream().collect (Collectors.counting()));
 
	System.out.println("match_FY$Actual_UpdateStatement");
//	1.    if DB with name 'FinanceDashboard' does not exist

		System.out.println("in try");
		conn = DataSource.getConnection();
		conn.setAutoCommit(false);
		sql="SELECT DB_NAME()";
		pstmt= conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		    System.err.println("Connected DB = " + rs.getString(1));
		}
		
		
		////checking below is that DB with "FinanceDashboard" name exist or not
		sql="SELECT name FROM sys.databases WHERE  name='FinanceDashboard' ";
		pstmt= conn.prepareStatement(sql);
		pstmt.execute();
		
		if(!pstmt.getResultSet().next()) {
			
			System.out.println("DB does not exist  ");
			//log it
		      logger.info("database doesnot exist in sqlserver with name -> "+ "FinanceDashboard" +"   ");
		      GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                    "ResultExpense_repository.class",
	                    "match_FY$Actual_UpdateStatement()",
	                  file.getName(),
	                    "database doesnot exist in sqlserver with name -> "+ "FinanceDashboard" +"",
	                   ""
	                    
	         )   );
		      return null;
			//System.exit(0);

		}else {
			System.out.println("DB FinanceDashboard is present ");
		}
//		if(!pstmt.getResultSet().next()) {
//			
//			 return null;
//		}else { 
//		}

		//END//checking below is that DB with "FinanceDashboard" name exist or not
		
		
		////checking below is that tabel with "PL_ResultRoyalty" name exist or not
		sql = "SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'PL_ResultExpense'";
		pstmt = conn.prepareStatement(sql);
		  rs = pstmt.executeQuery();

		if (!rs.next()) {

		    System.out.println("Table does not exist");

		    logger.info("Table does not exist in FinanceDashboard DB with name -> PL_ResultExpense");

		    GlobalExceptionHandler_AND_EmailSender.handleException(
		        new PrintLog_AND_SendEmail_Exception(
		            "ResultExpense_repository.class",
		            "match_FY$Actual_UpdateStatement()",
		            file.getName(),
		            "Table does not exist in SQL Server with name -> PL_ResultExpense",
		            ""
		        )
		    );

		    return null;

		} else {
		    System.out.println("Table PL_ResultExpense is present");
		}

		//END//checking below is that tabel with "PL_ResultRoyalty" name exist or not
		
		
	
	
		
//	2.	 storing excel data of fy and Actual in table in batches of 100
		// Update only the month column, keep others unchanged
		  sql = "UPDATE PL_ResultExpense SET " +
		             "Apr = COALESCE(?, Apr), " +  // If ? is null, keep existing Apr
		             "May = COALESCE(?, May), " +
		             "Jun = COALESCE(?, Jun), " +
		             "Jul = COALESCE(?, Jul), " +
		             "Aug = COALESCE(?, Aug), " +
		             "Sep = COALESCE(?, Sep), " +
		             "Oct = COALESCE(?, Oct), " +
		             "Nov = COALESCE(?, Nov), " +
		             "Dec = COALESCE(?, Dec), " +
		             "Jan = COALESCE(?, Jan), " +
		             "Feb = COALESCE(?, Feb), " +
		             "Mar = COALESCE(?, Mar), " +
		             "Monthly =?, " +
		             "Accumlated =?, " +
		             "[Last_month] =? " +
		             "WHERE FY = ? AND DataType = ? AND Model = ? AND Country = ? " +
		             "AND [1st_half] = ? AND [2nd_half] = ? AND [Model Description] = ? AND [Group] = ? AND category=?";
					
			int batchSize = 100;
			int count = 0; 
			pstmt=conn.prepareStatement(sql);

            int totalUpdatedRows=0;
            
            
            
         // Then in your loop, set null for months you don't want to update
			for (Result_Expense Result_Expense : list) {
 
				 // For the month that matches, set the actual value
			    // For all other months, set null to keep existing values
			    
			    String monthName = Result_Expense.getMonthName();
			 // Apr
			    pstmt.setString(1, "Apr".equalsIgnoreCase(monthName) ? Result_Expense.getApr() : null);
			    // May
			    pstmt.setString(2, "May".equalsIgnoreCase(monthName) ? Result_Expense.getMay() : null);
			    // Jun
			    pstmt.setString(3, "Jun".equalsIgnoreCase(monthName) ? Result_Expense.getJun() : null);
			    // Jul
			    pstmt.setString(4, "Jul".equalsIgnoreCase(monthName) ? Result_Expense.getJul() : null);
			    // Aug
			    pstmt.setString(5, "Aug".equalsIgnoreCase(monthName) ? Result_Expense.getAug() : null);
			    // Sep
			    pstmt.setString(6, "Sep".equalsIgnoreCase(monthName) ? Result_Expense.getSep() : null);
			    // Oct
			    pstmt.setString(7, "Oct".equalsIgnoreCase(monthName) ? Result_Expense.getOct() : null);
			    // Nov
			    pstmt.setString(8, "Nov".equalsIgnoreCase(monthName) ? Result_Expense.getNov() : null);
			    // Dec
			    pstmt.setString(9, "Dec".equalsIgnoreCase(monthName) ? Result_Expense.getDec() : null);
			    // Jan
			    pstmt.setString(10, "Jan".equalsIgnoreCase(monthName) ? Result_Expense.getJan() : null);
			    // Feb
			    pstmt.setString(11, "Feb".equalsIgnoreCase(monthName) ? Result_Expense.getFeb() : null);
			    // Mar
			    pstmt.setString(12, "Mar".equalsIgnoreCase(monthName) ? Result_Expense.getMar() : null);
			    // monlty
			    pstmt.setString(13,  Result_Expense.getMonthly() );
			    // accumulated
			    pstmt.setString(14,  Result_Expense.getAverage() );
			    // lastmonth
			    pstmt.setString(15,  Result_Expense.getLastMonth() );
			    
			    // WHERE clause parameters (positions 13-20)
			    pstmt.setString(16, Result_Expense.getFy());
			    pstmt.setString(17, Result_Expense.getDataType());
			    pstmt.setString(18, Result_Expense.getModel());
			    pstmt.setString(19, Result_Expense.getCountry());
			    pstmt.setString(20, Result_Expense.getFirstHalf());
			    pstmt.setString(21, Result_Expense.getSecondHalf());
			    pstmt.setString(22, Result_Expense.getModelDiscription());
			    pstmt.setString(23, Result_Expense.getGroup());
			    pstmt.setString(24, Result_Expense.getCategory());
				

			    pstmt.addBatch();
			    count++;

			    if (count == batchSize) {   // better than %
			        int i[]=pstmt.executeBatch();
//			        for(int ii:i) {System.out.println(ii);};
			        pstmt.clearBatch();     // important
			        count = 0;
			        for (int ra  :  i) {
			            totalUpdatedRows += ra ;
			        }
				    System.out.println("executing batch with 100 queries");
			    }
			}

			/*   EXECUTE REMAINING RECORDS when batch did not filled with 100 statements */
			if (count > 0) {
			   int i[]= pstmt.executeBatch();
			    pstmt.clearBatch();
			    count = 0;
			    for (int ra  :  i) {
		            totalUpdatedRows += ra ;
		        }
			    System.out.println("executing batch with less than 100 queries");
			}
 
	/* COMMIT TRANSACTION */
			conn.commit();
			//updating excel_job_tracker and no. of deleted rows and updated rows
			 Excel_Job_Tracker_TableInDB_Updater.updatingExcelJobTrackr_DeletedRowsAndInsertedRowsColumns(file,"Result Expense",applicationPropertiesService.getProperty("job.batch.id"),totalDeletedRows,totalUpdatedRows);
			
		}
		catch(Exception e){
			e.printStackTrace() ;
			   logger.info("Exception in doing DB task in match_FY$Actual_UpdateStatement() method > "+ ResultExpenseEndBoundedExcelReader.class.getName()+ "  ");

System.out.println("catch block");
				  e.printStackTrace() ;
			try
			{
				if(null!=conn)
					conn.rollback();
			}
			catch(SQLException e1)
			{
				logger.info("connection is already null, cant roll back in ResultExpense_repository.class");
			}
			GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                    "ResultExpense_repository.class",
	                    "match_FY$Actual_UpdateStatement()",
	                    file.getName(),
	                    "not able to delete or insert in table 'PL_ResultExpense' in DB -> "+ "FinanceDashboard" +"",
	                   "so processing other sheets data and storing it in DB -> "+e.toString()+ "  " +e.getMessage()
	                    
	           ) );
			return null;
//			System.exit(0);
		}
		finally
		{
			try
			{
				if(null!=pstmt)
					pstmt.close();
				if(null!=conn)
					conn.close();
			}
			catch(SQLException e)
			{

				logger.info("connection and preparedStatement is already null, cant roll back in ResultExpense_repository.class");
			}
			pstmt = null;
			list.clear();
			list = null;
		}
	return "completed";
}
	
	
	
/*
			private   void createTransaction(List<Result_Units_DTO> list,Connection conn ) throws SQLException
			{
				System.out.println(" createTransaction()");
				Result_Units_DTO Result_Units_DTO=null;
			String sql="insert into resultunits  (SNO,FY,ACTUAL,COUNTRY,FIRST_HALF,SECOND_HALF,MODEL,GROUP,"
					+ "APR,MAY,JUN,JUL,AUG,SEP,OCT,NOV,DEC,JAN,FEB,MAR,MONTH,ACCUMULATED,LAST_MONTH"
					+ "RAW_LABEL,BASE_MODEL,VEHICLE_TYPE,X_MODEL_CODE)"
					+ "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement pstmt  =   conn.prepareStatement(sql);
			
			for (int a = 0; a < list.size(); a++) {

				  Result_Units_DTO=    list.get(a); 

					pstmt.setString(1, Result_Units_DTO.getSno());
					pstmt.setString(2, Result_Units_DTO.getFy());
					pstmt.setString(3, Result_Units_DTO.getActual());
					pstmt.setString(4, Result_Units_DTO.getCountry());
					pstmt.setString(5, Result_Units_DTO.getFirstHalf());
					pstmt.setString(6, Result_Units_DTO.getSecondHalf());
					pstmt.setString(7, Result_Units_DTO.getModel());
					pstmt.setString(8, Result_Units_DTO.getGroup());
					pstmt.setString(9, Result_Units_DTO.getApr());
					pstmt.setString(10, Result_Units_DTO.getMay());
					pstmt.setString(11, Result_Units_DTO.getJun());
					pstmt.setString(12, Result_Units_DTO.getJul());
					pstmt.setString(13, Result_Units_DTO.getAug());
					pstmt.setString(14, Result_Units_DTO.getSep());
					pstmt.setString(15, Result_Units_DTO.getOct());
					pstmt.setString(16, Result_Units_DTO.getNov());
					pstmt.setString(17, Result_Units_DTO.getDec());
					pstmt.setString(18, Result_Units_DTO.getJan());
					pstmt.setString(19, Result_Units_DTO.getFeb());
					pstmt.setString(20, Result_Units_DTO.getMar());
					pstmt.setString(21, Result_Units_DTO.getMonth());
					pstmt.setString(22, Result_Units_DTO.getAccumulated());
					pstmt.setString(23, Result_Units_DTO.getLastMonth());
					pstmt.setString(24, Result_Units_DTO.getRawLabel());
					pstmt.setString(25, Result_Units_DTO.getBaseModel());
					pstmt.setString(26, Result_Units_DTO.getVehicleType());
					pstmt.setString(27, Result_Units_DTO.getxModelCode());
					
					
			    pstmt.addBatch();
			    
			    Result_Units_DTO = null;
			}
			int i1[]=pstmt.executeBatch();
			
		
	  
	  
}
*/

  /*
public   Connection getConnection() throws Exception {
	Connection connection = null;
	System.out.println("getConnection()");
	try {
	 
	 
		Class.forName(databaseClassName);
		// getConnection Object
		connection = DataSource.getConnection();
		System.out.println(databaseClassName);
		System.out.println(databaseURL);
		System.out.println(databaseUserName);
		System.out.println(databasePassword);
		System.out.println(connection);
		// connection = DriverManager.getConnection(databaseURL);

		// set databaseURL and databaseClassName to null
		databaseClassName = null;
		databaseURL = null;
		// set userName & password to null
		databaseUserName = null;
		databasePassword = null;
	} catch (Exception e) {
		 // set connection to null
		System.out.println(e.toString());
		connection = null;
	}
	return connection;
}
 */
  }