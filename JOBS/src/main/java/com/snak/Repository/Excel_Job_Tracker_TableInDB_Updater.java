package com.snak.Repository;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.snak.MainBootClass;
import com.snak.UserDefiendExceptionHandler$Logging.GlobalExceptionHandler_AND_EmailSender;
import com.snak.UserDefiendExceptionHandler$Logging.PrintLog_AND_SendEmail_Exception;

@Repository
public class Excel_Job_Tracker_TableInDB_Updater {
	static Logger logger=LoggerFactory.getLogger(Excel_Job_Tracker_TableInDB_Updater.class); 
	@Autowired 
	 DataSource DataSource;
	//@Autowired 
	// MainBootClass MainBootClass;

	@Autowired
	GlobalExceptionHandler_AND_EmailSender GlobalExceptionHandler_AND_EmailSender;
	
	
	public void setFileName_FilePath_SheetName_BatchID(File file, String sheetName, String batchId) {
		CallableStatement cstmt=null;
		String sql=null;
		PreparedStatement deleteStmt=null;
		PreparedStatement pstmt=null;
		Connection conn = null;
		
		try {
			conn = DataSource.getConnection();
			conn.setAutoCommit(false);
			  sql="insert into Excel_Job_Tracker(FileName,FullPath,SheetName,BatchID,LastModified,ProcessedAt) "
						+ "values(?,?,?,?,?,?)";
			 
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, file.getName());
				pstmt.setString(2, file.getAbsolutePath());
				pstmt.setString(3, sheetName);
				pstmt.setInt(4, Integer.parseInt(batchId));
				pstmt.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
				pstmt.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));

				int i=pstmt.executeUpdate();
				

				/* COMMIT TRANSACTION */
						 
						conn.commit();
				
		
	}catch(Exception e){
		e.printStackTrace() ;
		try
		{
			if(null!=conn)
				conn.rollback();
		}
		catch(SQLException e1)
		{
			logger.info("connection is already null, cant roll back in ResultDep_repository.class");
		}
		GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
                    "Excel_Job_Tracker_TableInDB_Updater.class",
                    "match_FY$setFileName_FilePath_SheetName_BatchID()",
                   file.getName(),
                    "not able to delete or insert in table 'Excel_Job_Tracker' in DB -> "+ "Java_Job" +"",
                   "so processing other sheets data and storing it in DB"
                    
            ));
		
//		System.exit(0);
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

			logger.info("connection and preparedStatement is already null, cant roll back in Excel_Job_Tracker_TableInDB_Updater.class");
		}
		pstmt = null; 
	}
	}
		
	 
	
	public void updatingExcelJobTrackr_DeletedRowsAndInsertedRowsColumns(File file, String sheetName , String batchId, int noOfDeletdRow, int noOfInsertedRow) {
		CallableStatement cstmt=null;
		String sql=null;
		PreparedStatement deleteStmt=null;
		PreparedStatement pstmt=null;
		Connection conn = null;
		
		try {
			conn = DataSource.getConnection();
			conn.setAutoCommit(false);
			  
			  sql = "UPDATE Excel_Job_Tracker SET DeletedRows = ?, InsertedRows = ? , LastModified=?,ProcessedAt=? " +
				      "WHERE fileName = ? AND SheetName = ? AND batchId = ?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, noOfDeletdRow);
				pstmt.setInt(2, noOfInsertedRow);
				pstmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
				pstmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
				pstmt.setString(5, file.getName());
				pstmt.setString(6, sheetName);
				pstmt.setInt(7, Integer.parseInt(batchId));

				int i=pstmt.executeUpdate();
				

				/* COMMIT TRANSACTION */
						 
						conn.commit();
				
		
	}catch(Exception e){
		e.printStackTrace() ;
		try
		{
			if(null!=conn)
				conn.rollback();
		}
		catch(SQLException e1)
		{
			logger.info("connection is already null, cant roll back in ResultDep_repository.class");
		}
		GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
                    "Excel_Job_Tracker_TableInDB_Updater.class",
                    "updatingExcelJobTrackr_DeletedRowsAndInsertedRowsColumns",
                   file.getName(),
                    "not able to delete or insert in table 'Excel_Job_Tracker' in DB -> "+ "Java_Job" +"",
                   "so processing other sheets data and storing it in DB"
                    
            ));
		
//		System.exit(0);
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

			logger.info("connection and preparedStatement is already null, cant roll back in Excel_Job_Tracker_TableInDB_Updater.class");
		}
		pstmt = null; 
	}
	}
	
	
	
	
	public void updatingExcelJobTrackr_ErrorMessagesColumns(File file, String sheetName , String batchId,String errorMessages) {
		CallableStatement cstmt=null;
		String sql=null;
		PreparedStatement deleteStmt=null;
		PreparedStatement pstmt=null;
		Connection conn = null;
		
		try {
			conn = DataSource.getConnection();
			conn.setAutoCommit(false);
			  
			  sql = "UPDATE Excel_Job_Tracker SET errorMessages = ?, LastModified=?,ProcessedAt=? " +
				      "WHERE fileName = ? AND SheetName = ? AND batchId = ?";
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, errorMessages);

				pstmt.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
				pstmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
				
				pstmt.setString(4, file.getName());
				
				pstmt.setString(5, sheetName);
				pstmt.setInt(6,Integer.parseInt(batchId));

				int i=pstmt.executeUpdate();
				

				/* COMMIT TRANSACTION */
						 
						conn.commit();
				
		
	}catch(Exception e){
		e.printStackTrace() ;
		try
		{
			if(null!=conn)
				conn.rollback();
		}
		catch(SQLException e1)
		{
			logger.info("connection is already null, cant roll back in ResultDep_repository.class");
		}
		GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
                    "Excel_Job_Tracker_TableInDB_Updater.class",
                    "updatingExcelJobTrackr_ErrorMessagesColumns",
                   file.getName(),
                    "not able to delete or insert in table 'Excel_Job_Tracker' in DB -> "+ "Java_Job" +"",
                   "so processing other sheets data and storing it in DB"
                    
            ));
		
//		System.exit(0);
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

			logger.info("connection and preparedStatement is already null, cant roll back in Excel_Job_Tracker_TableInDB_Updater.class");
		}
		pstmt = null; 
	}
	}
	
	
}
