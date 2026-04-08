package com.snak.Services;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class applicationPropertiesService{
	

	 @Value("${properties.file.path.outside.jar}")
	 private  String propertiesFilePathOutSideJar;
	
	static Logger logger=LoggerFactory.getLogger(applicationPropertiesService.class);
public String getProperty(String key) {
        	String batchID=null;
        try {
            Properties prop = new Properties();

            FileInputStream fis = new FileInputStream(propertiesFilePathOutSideJar);
            prop.load(fis);
            fis.close();
            // store exception info
            
              batchID=prop.getProperty(key);
           
            
           

        } catch (Exception e) {
            logger.error("MainBootClass.class -> Error reading to properties file", e);
        }
		return batchID;
        }
        
        public String incrementAndStoreBatchId(String key) {
            try {
            	 Properties prop = new Properties();

                 FileInputStream fis = new FileInputStream(propertiesFilePathOutSideJar);
                 prop.load(fis);
                 fis.close();
                 // store exception info
                 
                 String batchID=prop.getProperty(key);
                // Extract prefix and number
                String prefix = batchID.replaceAll("\\d+", "");
                String numberPart = batchID.replaceAll("\\D+", "");

                int number = Integer.parseInt(numberPart);
                number++;

                String newBatchId = prefix + number;

                // Update property
//                prop.setProperty("next.job.batch.id", newBatchId);
                prop.setProperty(key, newBatchId);

                // Save back to properties file
                FileOutputStream fos = new FileOutputStream(propertiesFilePathOutSideJar);
                prop.store(fos, "udated on-");
                fos.close();

                return newBatchId;

            } catch (Exception e) {
                logger.error("Error in incrementAndStoreBatchId() in MainBootClass.class while updating batch id", e);
            }

            return null;
        }
        
        
        public String setProperty(String key, String value) {
            try {
            	System.out.println("setProperty()");
                Properties prop = new Properties();

                FileInputStream fis = new FileInputStream(propertiesFilePathOutSideJar);
                prop.load(fis);
                fis.close();
                // 
                
                System.out.println("setting value="+value);
                // Update property
                prop.setProperty(key,value);
               
                // Save back to properties file
                FileOutputStream fos = new FileOutputStream(propertiesFilePathOutSideJar);
                prop.store(fos, "updated on -");
                fos.close();
               

            } catch (Exception e) {
                logger.error("MainBootClass.class -> Error reading to properties file", e);
            }
    		return key;
            }
	
}