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

import com.snak.Services.applicationPropertiesService;
import com.snak.UserDefiendExceptionHandler$Logging.GlobalExceptionHandler_AND_EmailSender;
import com.snak.UserDefiendExceptionHandler$Logging.PrintLog_AND_SendEmail_Exception;
import com.snak.dto.Result_Pro_Ass; 

@Repository
public class ResultProAss_repository{  
	@Autowired
	Excel_Job_Tracker_TableInDB_Updater Excel_Job_Tracker_TableInDB_Updater;
	@Autowired
	applicationPropertiesService applicationPropertiesService;
	static Logger logger=LoggerFactory.getLogger(ResultProAss_repository.class); 
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

	CallableStatement cstmt =null;
	String sql=null;
	PreparedStatement deleteStmt=null;
	PreparedStatement pstmt=null;
	Connection conn = null;
public String match_FY$Actual_Remove_Insert(List<Result_Pro_Ass> list,File file){
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
//	1.  terminating job if DB with name 'Java_Job' does not exist

		System.out.println("in try");
		conn = DataSource.getConnection();
		conn.setAutoCommit(false);
		sql="SELECT DB_NAME()";
		pstmt= conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		    System.err.println("Connected DB = " + rs.getString(1));
		}
		
		

		// //checking below is that DB with "FinanceDashboard" name exist or not
		sql="SELECT name FROM sys.databases WHERE  name='FinanceDashboard' ";
		pstmt= conn.prepareStatement(sql);
		pstmt.execute();
		
		if(!pstmt.getResultSet().next()) {
			
			System.out.println("DB does not exist ");
			//log it
		      logger.info("database doesnot exist in sqlserver with name -> "+ "FinanceDashboard" +"   ");
//		      
		      GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                    "ResultProAss_repository.class",
	                    "match_FY$Actual_Remove_Insert()",
	                    file.getName(),
	                    "database doesnot exist in sqlserver with name -> "+ "FinanceDashboard" +"",
	                   ""
	                    
	            ));
		      return null;
//			System.exit(0);
		}else {
			System.out.println("DB FinanceDashboard is present ");
		}
//		if(!pstmt.getResultSet().next()) {
//			
//			 return null;
//		}else { 
//		}
		//END//checking below is that DB with "FinanceDashboard" name exist or not
		
		
		////checking below is that tabel with "PL_ResultTransPacking" name exist or not
		sql = "SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'PL_ResultProAss'";
		pstmt = conn.prepareStatement(sql);
		  rs = pstmt.executeQuery();

		if (!rs.next()) {

		    System.out.println("Table does not exist");

		    logger.info("Table does not exist in FinanceDashboard DB with name -> PL_ResultProAss");

		    GlobalExceptionHandler_AND_EmailSender.handleException(
		        new PrintLog_AND_SendEmail_Exception(
		            "ResultProAss_repository.class",
		            "match_FY$Actual_Remove_Insert()",
		            file.getName(),
		            "Table does not exist in SQL Server with name -> PL_ResultProAss",
		            ""
		        )
		    );

		    return null;

		} else {
		    System.out.println("Table PL_ResultProAss is present");
		}

		//END//checking below is that tabel with "PL_ResultTransPacking" name exist or not
				
		
	
	
//	2. deleting all record of table which matches with excel FY and Actual


    // DELETE
		int DELETEbatchSize = 100;
		int DELETEcount = 0; 
    deleteStmt = conn.prepareStatement("DELETE FROM PL_ResultProAss WHERE FY=? AND DataType=?");
    System.out.println("DELETEING OLD RECORD");
    Set<String> pairs = list.stream()
        .map(n -> n.getFy().trim() + "|" + n.getDataType().trim())
        .collect(Collectors.toSet());
    System.err.println("->>>> Unique FY-DataType pairs: " + pairs.size());
    int totalDeletedRows=0;
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
		int i[]=deleteStmt.executeBatch();
		deleteStmt.clearBatch();
		DELETEcount = 0;
		 for (int r : i) {
		        totalDeletedRows += r;
		    }
	    System.out.println("executing DELETE batch with less than 100 queries");
	} 
	pairs=null;
			
//	3.	 storing excel data of fy and Actual in table in batches of 100
	sql = "INSERT INTO PL_ResultProAss (" +
			"[Sno],[FY],[DataType],[Country],[1st_half],[2nd_half],[Model],[Group],[SpecCode],[Model Group Description]," +

			"[CateGory]," +

			"[Apr],[May],[Jun],[Jul],[Aug],[Sep]," +
			"[Oct],[Nov],[Dec],[Jan],[Feb],[Mar]," +
			"[Monthly]" +

			") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int batchSize = 100;
			int count = 0; 
			pstmt=conn.prepareStatement(sql);

            
			int totalUpdatedRows=0;
			for (Result_Pro_Ass r : list) {

			    pstmt.setString(1, r.getSno());
			    pstmt.setString(2, r.getFy());
			    pstmt.setString(3, r.getDataType());
			    pstmt.setString(4, r.getCountry());
			    pstmt.setString(5, r.getFirstHalf());
			    pstmt.setString(6, r.getSecondHalf());
			    pstmt.setString(7, r.getModel());
			    pstmt.setString(8, r.getGroup());
			    pstmt.setString(9, r.getSpecCode());
			    pstmt.setString(10, r.getModelGroupCode());

			    pstmt.setString(11, r.getCategory());

			    pstmt.setString(12, r.getApr());
			    pstmt.setString(13, r.getMay());
			    pstmt.setString(14, r.getJun());
			    pstmt.setString(15, r.getJul());
			    pstmt.setString(16, r.getAug());
			    pstmt.setString(17, r.getSep());
			    pstmt.setString(18, r.getOct());
			    pstmt.setString(19, r.getNov());
			    pstmt.setString(20, r.getDec());
			    pstmt.setString(21, r.getJan());
			    pstmt.setString(22, r.getFeb());
			    pstmt.setString(23, r.getMar());
			    pstmt.setString(24, r.getMonthly());
			    
			    
			    //below means if object have "Standard Price by Dept per Unit" category
			    //then populating Model Discription column in table
			    //else population "" for all other category
//			    if ("Standard Price by Dept per Unit".equalsIgnoreCase(r.getCategory())) {
//			   
//			    pstmt.setString(25, r.getModelDescription());
//			   	}else {
//			    pstmt.setString(25, "");
//			    		}

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
			 int i[]=   pstmt.executeBatch();
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
			System.out.println("Calling stored procedure usp_Load_Fact_Result_Trans_Packing");

			  cstmt = conn.prepareCall("{call dbo.usp_Load_Fact_Result_Trans_Packing}");
			  boolean result = cstmt.execute();
				 cstmt.close();
				}catch(Exception e) {
					e.printStackTrace() ;
					   //logger.info("Exception in calling usp_Load_Fact_Result_Trans_Packing procedure in match_FY$Actual_Remove_Insert() method > "+ EndBoundedExcelReader.class.getName()+ "  terminating JOB");
	conn.rollback();
				    GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
		                    "ResultTransPacking_repository.class",
		                    "match_FY$Actual_Remove_Insert()",
		                    file.getName(),
		                    "Exception in calling usp_Load_Fact_Result_Trans_Packing procedure in match_FY$Actual_Remove_Insert() method  "+ "Java_Job" +"",
		                   ""
		                    
		           ) ); 
					 //  System.exit(0);
					   
				}
  */
	/* COMMIT TRANSACTION */
			conn.commit();
			//updating excel_job_tracker and no. of deleted rows and updated rows
			 Excel_Job_Tracker_TableInDB_Updater.updatingExcelJobTrackr_DeletedRowsAndInsertedRowsColumns(file,"Result Pro Ass",applicationPropertiesService.getProperty("job.batch.id"),totalDeletedRows,totalUpdatedRows);
			
		}
		catch(Exception e){
			e.printStackTrace() ;
			   logger.info("Exception in doing DB task in match_FY$Actual_Remove_Insert() method > "+ ResultProAss_repository.class.getName()+ "  "+e.toString()+" "+e.getMessage());

System.out.println("catch block");
				  e.printStackTrace() ;
			try
			{
				if(null!=conn)
					conn.rollback();
			}
			catch(SQLException e1)
			{
				logger.info("connection is already null, cant roll back in ResultProAss_repository.class");
			}
			GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                    "ResultProAss_repository.class",
	                    "match_FY$Actual_Remove_Insert()",
	                    file.getName(),
	                    "not able to delete or insert in table 'PL_ResultProAss' in DB -> "+ "FinanceDashboard" +"",
	                   "so processing other sheets data and storing it in DB ->  "+e.toString()+ "  " +e.getMessage()
	                    
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



				logger.info("connection and preparedStatement is already null, cant roll back in ResultProAss_repository.class");
			
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