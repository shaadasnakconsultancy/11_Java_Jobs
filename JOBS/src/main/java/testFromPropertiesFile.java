import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

class testFromPropertiesFile{
	public static void main(String a[]) {
		
		try {
              Properties prop = new Properties();
              String s="C:/Users/ShaadAnsari/Downloads/WORK/Eclipse Workspaces/JOBS(all merged)/JOBS/src/main/resources/application.properties";
              FileInputStream fis = new FileInputStream(s);
        /*      prop.load(fis);
              fis.close();
              // store exception info
              
              String batchID=prop.getProperty("job.batch.id");
           
              System.out.println(batchID);
              // Extract prefix and number
              String prefix = batchID.replaceAll("\\d+", "");
              String numberPart = batchID.replaceAll("\\D+", "");

              int number = Integer.parseInt(numberPart);
              number++;

              String newBatchId = prefix + number;

              // Update property
              prop.setProperty("job.batch.id", newBatchId);

              // Save back to properties file
              FileOutputStream fos = new FileOutputStream(s);
              prop.store(fos, "ok update----");
              
              */
             //-------------------------------
          	List<String> lines = Files.readAllLines(Paths.get(s));

        	for (int i = 0; i < lines.size(); i++) {
        	    if (lines.get(i).startsWith("job.batch.id")) {
        	    	
        	    	String	batchID=lines.get(i);
        	    	  System.out.println(batchID);
                      // Extract prefix and number
                      String prefix = batchID.replaceAll("\\d+", "");
                      String numberPart = batchID.replaceAll("\\D+", "");

                      int number = Integer.parseInt(numberPart);
                      number++;

                      String newBatchId = prefix + number;
        	        lines.set(i,  newBatchId);
        	    }
        	}

        	Files.write(Paths.get(s), lines);
             

          } catch (Exception e) {
          	e.printStackTrace();
           }
	}
	

	
}