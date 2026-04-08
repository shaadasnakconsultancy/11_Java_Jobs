package com.snak.Services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Dynamic_File_System{

	@Autowired
	applicationPropertiesService applicationPropertiesService;
//	@Value("${dealer.stock.source.directory.path}")
//	private  String stockSourceDirPath;
//	
//	@Value("${budget.plan.archive.directory}")
//	private  String archiveDirectory;
	int fileFoundCount=0;
public   void RUN_JOB() {
	String stockSourceDirPath=applicationPropertiesService.getProperty("dealer.stock.source.directory.path");
	String archiveDirectory=applicationPropertiesService.getProperty("budget.plan.archive.directory");
	 System.out.println("hi");
		try
		{
			/*
			 * READ ALL THE EXCELS IN THE DEALER STOCK SORUCE EXCELS DIRECTORY
			 */
			 System.out.println(stockSourceDirPath);
			
			if(null!=stockSourceDirPath && !"".equals(stockSourceDirPath))
 
			{ 
				File excelDir = new File(stockSourceDirPath);
				File convertedFile = null;
				if(excelDir.exists() && excelDir.isDirectory())
				{  
					 scanAndProcessFiles(excelDir,excelDir);
					
				 

				} 
				
			}
		}catch(Exception a ) {
			}
		}

	    void scanAndProcessFiles(File dir, File sourceRoot) {
	    try {
	    	String stockSourceDirPath=applicationPropertiesService.getProperty("dealer.stock.source.directory.path");
	    	String archiveDirectory=applicationPropertiesService.getProperty("budget.plan.archive.directory");
	    	
	        if (dir == null || !dir.exists()) {
	        	System.out.println("no directory exist -> "+dir.getName());
	            return;
	        }

	        File[] files = dir.listFiles();
	        if (files == null) {
	        	System.out.println("no file/Folder found in directory-> " +dir.getName());
	            return;
	        }

	        for (File file : files) {

	            // If directory → go deeper
	            if (file.isDirectory()) {
	                scanAndProcessFiles(file, sourceRoot);

	            } 

	            // If file → process
	            else if (file.isFile() && file.getName() != null &&
	                    (file.getName().endsWith(".xlsb")
	                    || file.getName().endsWith(".xlsx")
	                    || file.getName().endsWith(".xlsm"))) {
	            	
	            	fileFoundCount++;

	                System.out.println("Found File: " + file.getAbsolutePath());

	                // ✅ FIRST PROCESS
	                boolean isProcessed = processExcelFile(file);

	                // ✅ ONLY MOVE IF SUCCESS
	                if (isProcessed) {
	                 moveBudgetPlanFileToArchive(file, sourceRoot);
	                } else {
	                    System.out.println("File kept in source (processing failed): " + file.getName());
	                }
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    if(fileFoundCount==0) {
	    	System.out.println("no file found");
	    }else {
	    	fileFoundCount=0;
	    }
	    
	}
	private   boolean processExcelFile(File file) {
	    try {
	    	String stockSourceDirPath=applicationPropertiesService.getProperty("dealer.stock.source.directory.path");
	    	String archiveDirectory=applicationPropertiesService.getProperty("budget.plan.archive.directory");
	        System.out.println("Processing logic started for: " + file.getName());

	        // 👉 WRITE YOUR ACTUAL EXCEL READING CODE HERE
	        // Example:
	        // Read Excel
	        // Validate Data
	        // Insert Into DB

	        // Simulate processing
	        Thread.sleep(500);

	        System.out.println("Processing SUCCESS for: " + file.getName());
	        return true;   // SUCCESS

	    } catch (Exception e) {
	        System.out.println("Processing FAILED for: " + file.getName());
	        e.printStackTrace();
	        return false;  // FAILURE
	    }
	}
	public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {

		Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		String parsedDate = formatter.format(initDate);

		return parsedDate;
	}
	public   void moveBudgetPlanFileToArchive(File excelFile, File sourceRoot)
	{
		String stockSourceDirPath=applicationPropertiesService.getProperty("dealer.stock.source.directory.path");
		String archiveDirectory=applicationPropertiesService.getProperty("budget.plan.archive.directory");
	    try
	    {
	        

	        // Normalize paths
	        Path sourceRootPath = sourceRoot.toPath().toAbsolutePath();
	        Path sourceFilePath = excelFile.toPath().toAbsolutePath();

	        // 🔴 Get Relative Path (this preserves folder structure)
	        Path relativePath = sourceRootPath.relativize(sourceFilePath);

	        // Add timestamp to filename
	        Date currentDate = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
	        String convertedDate = sdf.format(currentDate);

	        String originalFileName = relativePath.getFileName().toString();
	        String newFileName = convertedDate + "_" + originalFileName;

	        // Rebuild relative path with new filename
	        Path newRelativePath = relativePath.getParent() == null
	                ? Paths.get(newFileName)
	                : relativePath.getParent().resolve(newFileName);

	        // Final destination path
	        Path destinationPath = Paths.get(archiveDirectory).resolve(newRelativePath);

	        // ✅ Create directories if not exist
	        Files.createDirectories(destinationPath.getParent());

	        // ✅ Move file
	        Files.move(sourceFilePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

	        System.out.println("Moved: " + sourceFilePath + " -> " + destinationPath);

	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	

	}