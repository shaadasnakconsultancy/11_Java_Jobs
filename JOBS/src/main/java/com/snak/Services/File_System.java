package com.snak.Services;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.snak.UserDefiendExceptionHandler$Logging.GlobalExceptionHandler_AND_EmailSender;
import com.snak.UserDefiendExceptionHandler$Logging.PrintLog_AND_SendEmail_Exception;

@Service
public class File_System{
	
	static Logger logger=LoggerFactory.getLogger(File_System.class);
	@Autowired
	GlobalExceptionHandler_AND_EmailSender GlobalExceptionHandler_AND_EmailSender;
//	@Value("${dealer.stock.source.directory.path}")
//	private  String stockSourceDirPath;
//	
//	@Value("${budget.plan.archive.directory}")
//	private  String archiveDirectory;
//	
	@Autowired
	ResultunitsEndBoundedExcelReader ResultunitsEndBoundedExcelReader;
	@Autowired
	applicationPropertiesService applicationPropertiesService;
	
	
	@Autowired
	LoggerCLass LoggerCLass;
	int fileFoundCount=0;
	
public   List<File> getFileFromDirectory(String folderPath, String archiveFolderPath) {

	String stockSourceDirPath=applicationPropertiesService.getProperty(folderPath);
	String archiveDirectory=applicationPropertiesService.getProperty(archiveFolderPath);
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
public void moveFileToArchive(File excelFile, File sourceRoot,String folderPath, String archiveFolderPath)
{
	String stockSourceDirPath=applicationPropertiesService.getProperty(folderPath);
	String archiveDirectory=applicationPropertiesService.getProperty(archiveFolderPath);
	Path relativePath = null;
	 Path sourceRootPath = null;
	 Path sourceFilePath = null;
	 Path destinationPath = null;
    try
    {
          sourceRootPath = sourceRoot.toPath().toAbsolutePath().normalize();
          sourceFilePath = excelFile.toPath().toAbsolutePath().normalize();

        

        if (sourceFilePath.startsWith(sourceRootPath)) {
            relativePath = sourceRootPath.relativize(sourceFilePath);
        } else {
            // fallback if file not inside sourceRoot
            relativePath = sourceFilePath.getFileName();
        }

        // Timestamp
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss")
                .format(new Date());

        // ALWAYS take real filename from actual file
        String originalFileName = sourceFilePath.getFileName().toString();

        String newFileName = timeStamp + "_" + originalFileName;

        Path newRelativePath = (relativePath.getParent() == null)
                ? Paths.get(newFileName)
                : relativePath.getParent().resolve(newFileName);

          destinationPath = Paths.get(archiveDirectory)
                .resolve(newRelativePath);

        Files.createDirectories(destinationPath.getParent());

        Files.move(sourceFilePath, destinationPath,
                StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Moved: " + sourceFilePath + " -> " + destinationPath);
    }
    catch (Exception e)
    {
    	GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
                  "File_System.class",
                  "moveFileToArchive()",
                  excelFile.getName(),
                  "Problem occurred while archiving file",
                  "Moving from [" + sourceFilePath + "] to [" + destinationPath + "]"
                  
          ));
	//	logger.info(" problem in moving processed file to archive folder in moveFileToArchive() ");
    }
}
	
//this below method have to be called only MainBootClass.class
//at the time of removing files to archive, 
//(important)in this method if files are not available in folder then not maintinging log in .properties
//after sending failiur email we setting logs in .properties as ""
//but if we after sending failur email if we mainting log in .properties if files are not available in folder that is
//commented in this method then log in properties will not become "", 
//so in last at application runnning we alwasy want log in properties "";
public   List<File> getFileFromDirectory2(String folderPath, String archiveFolderPath) {
	String stockSourceDirPath=applicationPropertiesService.getProperty(folderPath);
	String archiveDirectory=applicationPropertiesService.getProperty(archiveFolderPath);
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
				//GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception("File_System.class", "getFileFromDirectory()", (childFile != null ? childFile.getName() : ""), "no file in folder "+stockSourceDirPath, " so cant read, "));
				
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

}