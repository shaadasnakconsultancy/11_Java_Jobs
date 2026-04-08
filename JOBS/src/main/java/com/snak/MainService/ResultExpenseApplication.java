package com.snak.MainService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snak.Repository.Excel_Job_Tracker_TableInDB_Updater;
import com.snak.Repository.ResultExpense_repository;
import com.snak.Services.Dynamic_File_System;
import com.snak.Services.File_System;
import com.snak.Services.LoggerCLass;
import com.snak.Services.ResultExpenseEndBoundedExcelReader;
import com.snak.Services.applicationPropertiesService;
import com.snak.Services.emailSender;
import com.snak.UserDefiendExceptionHandler$Logging.GlobalExceptionHandler_AND_EmailSender;
import com.snak.UserDefiendExceptionHandler$Logging.PrintLog_AND_SendEmail_Exception;
import com.snak.dto.Result_Expense;
@Service
public class ResultExpenseApplication  {

	static Logger logger=LoggerFactory.getLogger(ResultExpenseApplication.class);
	@Autowired
	LoggerCLass LoggerCLass;
	@Autowired
	Excel_Job_Tracker_TableInDB_Updater Excel_Job_Tracker_TableInDB_Updater;

	@Autowired
	applicationPropertiesService applicationPropertiesService;
	
	@Autowired
	ResultExpense_repository ResultExpense_repository;  
	
	@Autowired
	GlobalExceptionHandler_AND_EmailSender GlobalExceptionHandler_AND_EmailSender;
	@Autowired
	ResultExpenseEndBoundedExcelReader ResultExpenseEndBoundedExcelReader;
	
	@Autowired
	Dynamic_File_System Dynamic_File_System;
	@Autowired
	File_System File_System;
	@Autowired
	emailSender emailSender;
	 
 
	public void run( )   {

        System.out.println("ResultExpenseApplication Job Started");
        //emailSender.sendEmail("mdshaad024@gmail.com", "subject", "message");
        //System.exit(0);
        
    	List<File> excelFileList = new ArrayList<>();
    	File file2=null;
    	List<Result_Expense> list=null;
   	 String completed=null;
        try {
            // YOUR ACTUAL JOB
        	// 1. getting all .xlsx in directory
              excelFileList=File_System.getFileFromDirectory("directory.path.resultexpense","archive.directory.path.resultexpense");
            // Dynamic_File_System.RUN_JOB();


              String currentBatchId=applicationPropertiesService.getProperty("job.batch.id");
              
              
            //2. getting all excel data in List<Result_Units> from each file one by one
              for (File file : excelFileList) {
             list=ResultExpenseEndBoundedExcelReader.readDataBetweenEndMarkers(file);
            if(list==null) {
              	 continue;
               }
            file2=file;
            
            //3.if FY and Actual is present in DB, then within one transaction remove data from DB having FY and Actual 
            //and insert data 
     		/*		3.1.  calling setFileName_FilePath_SheetName_BatchID() (Excel_Job_Tracker_TableInDB_Updater.class) to set these
			 * FileName, FilePath, SheetName, BatchID these data in Excel job tracker table
			 * then will call match_FY$Actual_Remove_Insert() to do DB operataion in which again call   method from class 
			 * (Excel_Job_Tracker_TableInDB_Updater.class)  to update row deleted column in Excel job tracker table and  update row inserted in Excel job tracker table
*/
//Excel_Job_Tracker_TableInDB_Updater.  setFileName_FilePath_SheetName_BatchID(file,"Result Expense",currentBatchId);
   //completed=   ResultExpense_repository.match_FY$Actual_Remove_Insert(list,file);
              /*
               * 3.2. then calling method from Excel_Job_Tracker_TableInDB_Updater.class in which we will check if their is any log being created 
               * in .properties file if yes then updateing ErrorMessage column
               * 
               *  OR
               *  
               *  we can just check if match_FY$Actual_Remove_Insert() is returning null means yes error happened and update method fromExcel_Job_Tracker_TableInDB_Updater.class
               * */
          //  if(completed!=null) {
//          	   Excel_Job_Tracker_TableInDB_Updater.  updatingExcelJobTrackr_ErrorMessagesColumns(file,"Result Expense",currentBatchId,"SUCCESS");
             	 
          //    }else if(completed==null){
//              	 Excel_Job_Tracker_TableInDB_Updater.  updatingExcelJobTrackr_ErrorMessagesColumns(file,"Result Expense",currentBatchId,"FAIL");
              //	continue;	
            //  }      
           	//  repository.createRecords(getDummyList());
           
           //4. moving processed file to archive folder
//          File_System.moveFileToArchive(file, file);
        // clearing the list so only next excel data will be available in lsit not data from all excel files
           if(list!=null)  list.clear(); 
              }
              
        } catch (Exception e) {
        	// logger.info("run() in :: > "+ ResultUnitsApplication.class.getName());

        	 // LoggerCLass.printStackTraceToLogs(ResultUnitsApplication.class.getName(),"run", e);
        	GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception("ResultExpenseApplication.class", "run()", file2.getName(), "exception while reading data from this excel or at doing DB operation of this file",e.toString()+ " "+e.getMessage()));
        	e.printStackTrace();
        }

      //  if(excelFileList!=null) excelFileList.clear();
        System.out.println("ResultExpense Job Finished");

        //  THIS LINE MAKES APP EXIT (Important for batch jobs)
       //  System.exit(0);  
	}

	private void exit(int i) {
		// TODO Auto-generated method stub
		
	}

public   List<File> getFileFromDirectory() {

	String stockSourceDirPath=applicationPropertiesService.getProperty("dealer.stock.source.directory.path.for.resultexpense");
	String archiveDirectory=applicationPropertiesService.getProperty("budget.plan.archive.directory.for.resultexpense");
	  File childFile = null;
	List<File> excelFileList = new ArrayList<>();
	try
	{
		/*
		 * READ ALL THE .xlsx IN THE DEALER STOCK SORUCE EXCELS DIRECTORY
		 */
		 if(null!=stockSourceDirPath && !"".equals(stockSourceDirPath))
		
		{
			File excelDir = new File(stockSourceDirPath); 
			if(excelDir.exists() && excelDir.isDirectory())
			{
				 //scanAndProcessFiles(excelDir,excelDir);
				
				File[] excelFiles = excelDir.listFiles();
				if(null!=excelFiles && excelFiles.length>0)
				{
				  
				    for(int a=0;a<excelFiles.length;a++)
				    { 
				    System.out.println(excelFiles[a].getName());
				        childFile = excelFiles[a];
				        System.out.println(childFile.getName());
				        if(null!=childFile && childFile.isFile() && null!=childFile.getName() && 
								(childFile.getName().endsWith(".xls") || childFile.getName().endsWith(".xlsx") || childFile.getName().endsWith(".xlsm")))
						{
				        	  excelFileList.add(childFile);
//				        	 
							}else {
							//	logger.info("file found in directory -> "+childFile+" is not .xlsx");
								GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception("File_System.class", "getFileFromDirectory()", (childFile != null ? childFile.getName() : ""), "is not .xlsm file", " so cant read, although other .xlsm file have been proccessed succussfuly"));
							} 
							
						 
						
					}
					childFile = null;
				
			}
			else
			{
//				LoggerCLass.printStackTraceToLogs(File_System.class.getName(),"main",new  Exception("No Dealer Stock Directory Path Found. Exiting."));
			 //	logger.info(" No Excel Files Found Inside Directory :: >"+ stockSourceDirPath);
				GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception("File_System.class", "getFileFromDirectory()", (childFile != null ? childFile.getName() : ""), "no file in folder "+stockSourceDirPath, " so cant read, "));
				
			} 
		}
		else
		{
			GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception("File_System.class", "getFileFromDirectory()", (childFile != null ? childFile.getName() : ""), "Directory Does not Exist at Location or it doesn't have any .xlsm files", ""));
			
			//	logger.info("main :: Dealer Stock Excel Directory Does not Exist at Location :: >"+ stockSourceDirPath);
		}
		excelDir = null;
	}
	else
	{
		GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception("File_System.class", "getFileFromDirectory()", "", " No Directory Path Found in system where .xlsx file should be, ", " expected path "+stockSourceDirPath));
	//	logger.info(" No Directory Path Found in system where .xlsx file should be"+", expected path -> "+stockSourceDirPath);
	}
}catch(Exception e){
	GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception("File_System.class", "getFileFromDirectory()", "", " some problem occured while pickingup .xlsm files from directory -",stockSourceDirPath+" , check .log file  , "+e.toString()));
	
	//logger.info("getFileFromDirectory() method :: No Directory Path Found is system where .xlsx file should be :: ");
	}
	return excelFileList;
}
	
	
	/*
	 public static List<DMSStockTransactionDetails> getDummyList() {

	        List<DMSStockTransactionDetails> list = new ArrayList<>();

	        try {
	            // Record 1
	            DMSStockTransactionDetails d1 = new DMSStockTransactionDetails();
	            d1.setZone("North");
	            d1.setState("Jharkhand");
	            d1.setDistrict("Palamu");
	            d1.setTown("Daltonganj");
	            d1.setDealerCode("DLR001");
	            d1.setDealerActCode("ACT1001");
	            d1.setDealerName("Ansari Motors");
	            d1.setSieModel("SWIFT VXI");
	            d1.setStockDate(new Date());
	            d1.setTotalStockQty(25.0);
	            d1.setTotalInTransitQty(5.0);
	            d1.setCreatedDate(new Timestamp(System.currentTimeMillis()));
	            d1.setReceivedDate(new Timestamp(System.currentTimeMillis()));

	            list.add(d1);

	            // Record 2
	            DMSStockTransactionDetails d2 = new DMSStockTransactionDetails();
	            d2.setZone("East");
	            d2.setState("Bihar");
	            d2.setDistrict("Patna");
	            d2.setTown("Patna");
	            d2.setDealerCode("DLR002");
	            d2.setDealerActCode("ACT2002");
	            d2.setDealerName("Kumar Auto");
	            d2.setSieModel("BALENO ZETA");
	            d2.setStockDate(new Date());
	            d2.setTotalStockQty(40.0);
	            d2.setTotalInTransitQty(10.0);
	            d2.setCreatedDate(new Timestamp(System.currentTimeMillis()));
	            d2.setReceivedDate(new Timestamp(System.currentTimeMillis()));

	            list.add(d2);

	            // Record 3
	            DMSStockTransactionDetails d3 = new DMSStockTransactionDetails();
	            d3.setZone("West");
	            d3.setState("Maharashtra");
	            d3.setDistrict("Pune");
	            d3.setTown("Pune");
	            d3.setDealerCode("DLR003");
	            d3.setDealerActCode("ACT3003");
	            d3.setDealerName("Sharma Vehicles");
	            d3.setSieModel("BREZZA LXI");
	            d3.setStockDate(new Date());
	            d3.setTotalStockQty(18.0);
	            d3.setTotalInTransitQty(2.0);
	            d3.setCreatedDate(new Timestamp(System.currentTimeMillis()));
	            d3.setReceivedDate(new Timestamp(System.currentTimeMillis()));

	            list.add(d3);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
*/
}
