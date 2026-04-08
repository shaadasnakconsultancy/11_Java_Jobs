package com.snak.UserDefiendExceptionHandler$Logging;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.snak.Services.emailSender;

//read below comment
@Service
public class GlobalExceptionHandler_AND_EmailSender {

	static Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler_AND_EmailSender.class);
	@Autowired
	emailSender emailSender;
	

	 
	@Value("${properties.file.path.outside.jar}")
	    public   String propertiesFilePathOutSideJar;
	
	     
	 
	    public   void handleException(PrintLog_AND_SendEmail_Exception ex) {

	      

    	 //then we are setting in .properties file true, (true) mean yes log is available
    	 //from main boot app storing batchId in .propertirs, so taking it and storing logs in .log file with that batchId 
		 //then storing these logs in .properties file, so that at run method in main boot application
    	 //it will be checked that "yes.logs.are.present" is their means log is present
    	 //and when log is their in .properties it will send faileur email with these logs
    	 // and also remove the log from .properties and make that variable in .properties false for iteration of next job
    	 //if variable is already false means no log is their, means no exception occured, so send just a success email
    	    try {
    	    //	String s="C:/Users/ShaadAnsari/Downloads/WORK/Eclipse Workspaces/JOBS(all merged)/JOBS/src/main/resources/application.properties";
                Properties prop = new Properties();

                FileInputStream fis = new FileInputStream(propertiesFilePathOutSideJar);
                prop.load(fis);
                fis.close();
                // store exception info
                
               String batchID=prop.getProperty("job.batch.id");
//                also setting logs in .log file along with batch Id
           	 logger.info(batchID + ex.toString());
                
                
                prop.setProperty("logs.present", "yes.logs.are.present");
                String logMessageToSet=null;
                String logMessage=prop.getProperty("logs.message");
                if(logMessage==null || logMessage.length()<=0) {
                	logMessageToSet=batchID+ " -> " +ex.toString()+"<br><br><br>";
                }else if(logMessage!=null || logMessage.length()>0) {
                	logMessageToSet=batchID+ " -> " +logMessage+ex.toString()+"<br><br><br>";
                	
                }
                prop.setProperty("logs.message", logMessageToSet);

                FileOutputStream fos = new FileOutputStream(propertiesFilePathOutSideJar);
                prop.store(fos, "Updated on -");
                fos.close();

            } catch (Exception e) {
            	e.printStackTrace();
                logger.error("GlobalExceptionHandler_AND_EmailSender.class -> Error writing to properties file", e);
            }
	       
	    }
	
}
