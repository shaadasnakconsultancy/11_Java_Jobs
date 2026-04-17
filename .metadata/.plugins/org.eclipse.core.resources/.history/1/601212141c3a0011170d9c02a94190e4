package com.snak.Services;
 
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snak.UserDefiendExceptionHandler$Logging.GlobalExceptionHandler_AND_EmailSender;
import com.snak.UserDefiendExceptionHandler$Logging.PrintLog_AND_SendEmail_Exception;
import com.snak.dto.Result_Dep; 

@Service
public class  ResultDepEndBoundedExcelReader{
	static Logger logger=LoggerFactory.getLogger(ResultDepEndBoundedExcelReader.class);
	Object value =null;
	int columnIndex=0;
	Row row=null;
	@Autowired
	GlobalExceptionHandler_AND_EmailSender GlobalExceptionHandler_AND_EmailSender;
	File excelFile2=null;
	Result_Dep Result_Dep =null;
	List<Result_Dep> rowData = new ArrayList<>();
	 public   List<Result_Dep> readDataBetweenEndMarkers(File excelFile) {

		 InputStream is=null;
		  XSSFWorkbook workbook=null;
	        try {

	   		 excelFile2=excelFile;
	        	  is = new FileInputStream(excelFile);
	               workbook = new XSSFWorkbook(is);
	          // ⭐ Required to calculate formulas
	             FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
	            XSSFSheet sheet = workbook.getSheet("Result Dep");
	            
	            if (sheet == null) {
	                GlobalExceptionHandler_AND_EmailSender.handleException(
	                    new PrintLog_AND_SendEmail_Exception(
	                        "ResultDepEndBoundedExcelReader.class",
	                        "readDataBetweenEndMarkers()",
	                        excelFile.getName(),
	                        "'Result Dep' sheet not found",
	                        "Skipping this sheet"
	                    )
	                );
	                return null;   // stop processing this sheet
	            }
	            	
	            
System.out.println(sheet.getSheetName());
	            //   Find boundaries
	            int lastColumn = findLastColumnBeforeEnd(sheet.getRow(0),  evaluator);
	            int lastRow = findLastRowBeforeEnd(sheet,  evaluator);

	            if(lastColumn==-101 || lastRow==-101) {
	            	// logger.info("not able to find END marker in column or row in ResultDepEndBoundedExcelReader() in :: > "+ ResultDepEndBoundedExcelReader.class.getName());
	            	  
	            	GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                         "ResultDepEndBoundedExcelReader.class",
	                         "readDataBetweenEndMarkers()",
	                         excelFile.getName(),
	                         "END marker not found in COLUMN or ROW in Sheet - Result Dep",
	                         "skipping this file and processing others"
	                         
	               )  );
	            	return null;   // stop processing this sheet
//System.exit(0);
	            }
	            //   Read data only inside END-to-END box
	            for (int r = 1; r <= lastRow; r++) {
	            	//if END marker not found then already thrown exception so log will be maintained and email will be sent
	            	//and breaking this loop
	            	//and null, so caller of this method will continue it loop
	            	 if(lastColumn==-101 || lastRow==-101) {
	            		 return null;
		            		// break;
	            		 
	            	 }
	                  row = sheet.getRow(r);
	                if (row == null) { ; continue ;}

	                Result_Dep=new Result_Dep();
	                for (int c = 0; c <= lastColumn; c++) {

	                      value =  readCellValue(row.getCell(c),evaluator);
	                    if(c==0) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setSno( Integer.parseInt(String.valueOf( value).trim()));
	    					}else {
	                    	value = null;
	    					}
	                    	 
	                    	 
	                    }else if(c==1) {
	                    	
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setFy(String.valueOf(value).trim());
	    					}
	                    	value = null;
	                    	 
	                    	columnIndex++;
	                    }else if(c==2) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setDataType(String.valueOf(value).trim());
	    					}else {
	                    	value = null;
	    					}
	                    	 columnIndex++;
	                    }else if(c==3) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setCountry(String.valueOf(value).trim());
	    					}else {
	                    	value = null;
	    					}
	                    	 columnIndex++;
	                    }else if(c==4) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setFirstHalf(String.valueOf(value).trim());
	    					}else {
	                    	value = null;
	    					}
	                    	 columnIndex++;
	                    }else if(c==5) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setSecondHalf(String.valueOf(value).trim());
	    					}else {
	                    	value = null;
	    					}
	                    	 columnIndex++;
	                    }else if(c==6) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setModel(String.valueOf(value).trim());
	    					}else {

	    						Result_Dep.setModel("0");
	    					}
	                     	columnIndex++;
	                    }else if(c==7) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setGroup(String.valueOf(value).trim());
	    					}else {
	                    	value = null;
	    					}
	                    	 columnIndex++;
	                    }else if(c==8) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setCode(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setCode("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==9) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setDep(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setDep("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==10) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setApr(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setApr("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==11) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setMay(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setMay("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==12) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setJun(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setJun("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==13) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setJul(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setJul("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==14) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setAug(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setAug("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==15) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setSep(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setSep("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==16) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setOct(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setOct("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==17) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setNov(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setNov("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==18) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setDec(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setDec("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==19) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setJan(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setJan("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==20) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setFeb(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setFeb("0");
	    					}
	                    	 columnIndex++;
	                    }
	                    else if(c==21) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setMar(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setMar("0");
	    					}
	                    	 columnIndex++;
	                    }
	                    else if(c==22) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setCurrentMonth(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setCurrentMonth("0");
	    					}
	                    	 columnIndex++;
	                    }else if(c==23) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		Result_Dep.setAccumulatedDepreciation(String.valueOf(value).trim());
	    					}else {
	    						Result_Dep.setCurrentMonth("0");
	    					}
	                    	 columnIndex++;
	                    }
	                    }
	                columnIndex=0;
	                rowData.add(Result_Dep == null ? null :Result_Dep);
 
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
//	            logger.info("not able to read data from cell in readDataBetweenEndMarkers()");
	            GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                    "ResultDepEndBoundedExcelReader.class",
	                    "readDataBetweenEndMarkers()",
	                    excelFile.getName() ,
	                    "not able to read data from cell for sheet -> Result Dep",
	                   " so processing other files, check logs -> "+e.toString()+" " +e.getMessage()
	                    
	          )  );
//System.exit(0);
	        } finally {
	            try {
	                
	                if (is != null) is.close();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }

	        return rowData;
	    }

	    //   Detect END in first row -> stop columns
	    private   int findLastColumnBeforeEnd(Row headerRow, FormulaEvaluator evaluator) {

	        int lastCol = headerRow.getLastCellNum();

	        for (int c = 0; c < lastCol; c++) {

	            Object val =  readCellValue(headerRow.getCell(c),evaluator);

	            if (val != null && "END".equalsIgnoreCase(val.toString().trim())) {
	                return c - 1;
	            }else {
	         	 //  System.err.println("not able to find END marker in column in findLastColumnBeforeEnd() ");
	 		  	  
	               // logger.info("not able to find END marker in column in findLastColumnBeforeEnd() in :: > "+ ResultDepEndBoundedExcelReader.class.getName());

	  	           // 

	            }
	        }
	        return  -101;
	    }

	    //   Detect END in first column -> stop rows
	    private   int findLastRowBeforeEnd(XSSFSheet sheet, FormulaEvaluator evaluator) {

	        int lastRow = sheet.getLastRowNum();

	        for (int r = 0; r <= lastRow; r++) {

	            Row row = sheet.getRow(r);
	            if (row == null) continue;

	            Object val =  readCellValue(row.getCell(0),evaluator);

	            if (val != null && "END".equalsIgnoreCase(val.toString().trim())) {
	                return r - 1;
	            }else {
	         	   //System.err.println("not able to find END marker in row in findLastColumnBeforeEnd() ");
		  	        
	            	//   logger.info("not able to find END marker in row in findLastColumnBeforeEnd() in :: > "+ ResultDepEndBoundedExcelReader.class.getName());	   
	  	           // 

	            }
	        }
	        return -101;
	    }
	    public Object readCellValue(Cell cell, FormulaEvaluator evaluator)
	    {
	        Object cellValue = null;

	        try
	        {
	            if (cell == null)
	                return null;

	            DataFormatter formatter = new DataFormatter();

	            // ================= FORMULA =================
	            if (cell.getCellType() == Cell.CELL_TYPE_FORMULA)
	            {
	                try
	                {
	                    CellValue eval = evaluator.evaluate(cell);

	                    if (eval != null)
	                    {
	                        switch (eval.getCellType())
	                        {
	                            case Cell.CELL_TYPE_NUMERIC:
	                                double num = eval.getNumberValue();
	                                cellValue = (long) num;   // 🔥 remove decimal
	                                break;

	                            case Cell.CELL_TYPE_STRING:
	                                cellValue = eval.getStringValue();
	                                break;

	                            case Cell.CELL_TYPE_BOOLEAN:
	                                cellValue = eval.getBooleanValue();
	                                break;

	                            default:
	                                cellValue = null;
	                        }
	                    }
	                }
	                catch (Exception e)
	                {
	                    // 🔥 IMPORTANT: fallback when POI fails (IFERROR, XLOOKUP)

	                    switch (cell.getCachedFormulaResultType())
	                    {
	                        case Cell.CELL_TYPE_NUMERIC:
	                            double num = cell.getNumericCellValue();
	                            cellValue = (long) num;   // 🔥 remove decimal
	                            break;

	                        case Cell.CELL_TYPE_STRING:
	                            cellValue = cell.getStringCellValue();
	                            break;

	                        case Cell.CELL_TYPE_BOOLEAN:
	                            cellValue = cell.getBooleanCellValue();
	                            break;

	                        default:
	                            cellValue = 0;
	                    }
	                }
	            }

	            // ================= NUMERIC =================
	            else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
	            {
	                double num = cell.getNumericCellValue();
	                cellValue = (long) num;   // 🔥 remove decimal
	            }

	            // ================= STRING =================
	            else if (cell.getCellType() == Cell.CELL_TYPE_STRING)
	            {
	                cellValue = cell.getStringCellValue();
	            }

	            // ================= CLEANUP =================
	            if (cellValue != null)
	            {
	                String val = cellValue.toString();

	                if (val.contains(",")) val = val.replace(",", "");
	                if (val.contains("* ")) val = val.replace("* ", "");

	                cellValue = val.trim();
	            }
	        }
	        catch (Exception e)
	        {

	        	e.printStackTrace();
	            logger.info("Exception in readCellValue: " + e.toString() + " " + e.getMessage());

	            cellValue = null;

	            GlobalExceptionHandler_AND_EmailSender.handleException(
	                new PrintLog_AND_SendEmail_Exception(
	                    "ResultDepEndBoundedExcelReader.class",
	                    "readCellValue()",
	                    excelFile2.getName(),
	                    "not able to read data from cell for sheet -> Result Dep",
	                    "so processing other files, check logs -> " + e.toString() + " " + e.getMessage()
	                )
	            );
	        }

	        return cellValue;
	    }
	    
	    
	    /*
	    public Object readCellValue(Cell cell, FormulaEvaluator evaluator)
	    {
	        Object cellValue = null;

	        try
	        {
	            if (cell == null)
	                return null;

	            DataFormatter formatter = new DataFormatter();

	            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
	            {
	                cellValue = formatter.formatCellValue(cell);
	            }
	            else if (cell.getCellType() == Cell.CELL_TYPE_STRING)
	            {
	                cellValue = cell.getStringCellValue();
	            }
	            else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA)
	            {
	                String formula = cell.getCellFormula();

	                try
	                {
	                    // Try evaluating (works for SUM, IF, etc.)
	                    CellValue evaluatedValue = evaluator.evaluate(cell);

	                    if (evaluatedValue != null)
	                    {
	                        switch (evaluatedValue.getCellType())
	                        {
	                            case Cell.CELL_TYPE_NUMERIC:
	                                cellValue = formatter.formatCellValue(cell, evaluator);
	                                break;

	                            case Cell.CELL_TYPE_STRING:
	                                cellValue = evaluatedValue.getStringValue();
	                                break;

	                            case Cell.CELL_TYPE_BOOLEAN:
	                                cellValue = evaluatedValue.getBooleanValue();
	                                break;

	                            default:
	                                cellValue = null;
	                        }
	                    }
	                }
	                catch (Exception ex)
	                {
	                    // POI cannot evaluate (example: XLOOKUP)
	                    cellValue = null;
	                }

	                
//	                 * If evaluator failed (XLOOKUP case),
//	                 * read cached Excel result
	                
	                if (cellValue == null)
	                {
	                    try
	                    {
	                        int cachedType = cell.getCachedFormulaResultType();

	                        switch (cachedType)
	                        {
	                            case Cell.CELL_TYPE_NUMERIC:
	                                cellValue = formatter.formatCellValue(cell);
	                                break;

	                            case Cell.CELL_TYPE_STRING:
	                                cellValue = cell.getRichStringCellValue().getString();
	                                break;

	                            case Cell.CELL_TYPE_BOOLEAN:
	                                cellValue = cell.getBooleanCellValue();
	                                break;

	                            default:
	                                cellValue = null;
	                        }
	                    }
	                    catch (IllegalStateException ignore)
	                    {
	                        cellValue = null;
	                    }
	                }

	                // IFERROR(...,"0") fallback
	                if (cellValue == null || "".equals(cellValue))
	                {
	                    cellValue = "0";
	                }
	            }

	            // cleanup formatting
	            if (cellValue != null)
	            {
	                String val = cellValue.toString();
	                if (val.contains(",")) val = val.replace(",", "");
	                if (val.contains("* ")) val = val.replace("* ", "");
	                cellValue = val.trim();
	            }
	        }
	        catch (Exception e)
	        {
	            logger.info("Exception in readCellValue: " + e.getMessage());
	            cellValue = null;
	            GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                    "ResultDepEndBoundedExcelReader.class",
	                    "readCellValue()",
	                    excelFile2.getName() ,
	                    "not able to read data from cell for sheet -> Result Dep",
	                   " so processing other files, check logs -> " +e.toString()+ " " +e.getMessage()
	                    
	            ));
	        }

	        return cellValue;
	    }
	    
	    */
	/*
	public   Object readCellValue(Cell cell, FormulaEvaluator evaluator)
	{
		Object cellValue = null;
		try {
			if (null != cell) {
				
//				  check for Cell Type and format accordingly
				
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					
					// set cell type as String
//					cell.setCellType(Cell.CELL_TYPE_STRING);
//					cellValue = cell.getStringCellValue();
					DataFormatter dmt = new DataFormatter();
					cellValue = dmt.formatCellValue(cell);
					if(null!=cellValue && cellValue.toString().contains(","))
					{
						cellValue=  cellValue.toString().replace(",", "");
					}
					if(null!=cellValue && !"".equals(cellValue))
					{
						cellValue = cellValue.toString().replace("* ", "");
					}
					dmt = null;
				}
				else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA)
				{
				    DataFormatter formatter = new DataFormatter();

				    // Evaluate the formula result
				    CellValue evaluatedValue = evaluator.evaluate(cell);

				    if (evaluatedValue != null)
				    {
				        switch (evaluatedValue.getCellType())
				        {
				            case Cell.CELL_TYPE_NUMERIC:
				                cellValue = formatter.formatCellValue(cell, evaluator);
				                if(null!=cellValue && cellValue.toString().contains(","))
								{
									cellValue=  cellValue.toString().replace(",", "");
								}
								if(null!=cellValue && !"".equals(cellValue))
								{
									cellValue = cellValue.toString().replace("* ", "");
								}
				                break;

				            case  Cell.CELL_TYPE_STRING:
				                cellValue = evaluatedValue.getStringValue();
				                break;

				            case Cell.CELL_TYPE_BOOLEAN:
				                cellValue = evaluatedValue.getBooleanValue();
				                break;

				            case Cell.CELL_TYPE_BLANK:
				            	 
				            	
				                cellValue = null;
				                break;

				            default:
				                cellValue = formatter.formatCellValue(cell);
				        }
				    }
				}
				else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					cellValue = cell.getStringCellValue();
				}
			}
		} catch (Exception e) {
			//printStackTraceToLogs(Utilities.class.getName(), "readCellValue()", e);
	            logger.info("Exception in converting cell value to object in readCellValue (so returning null) in :: > "+ EndBoundedExcelReader.class.getName());

			// set cellValue to null
			cellValue = null;
		}
		if (cell.getCellType() == Cell.CELL_TYPE_FORMULA)
	    {
			  DataFormatter formatter = new DataFormatter();
			   int cachedType = cell.getCachedFormulaResultType();
			   if(cachedType==Cell.CELL_TYPE_NUMERIC) {
				   cellValue = formatter.formatCellValue(cell);
			   }else if(cachedType==Cell.CELL_TYPE_STRING) {
				   cellValue = cell.getRichStringCellValue().getString();
			   }else if(cachedType==Cell.CELL_TYPE_BOOLEAN) {
				   cellValue = cell.getBooleanCellValue();
			   }else if(Cell.CELL_TYPE_BLANK==cachedType) {
				   cellValue = null;
			   }
			   

			 
//cellValue = formatter.formatCellValue(cell);
System.out.println(String.valueOf(  cellValue));
 
	    }
		return cellValue;
	
	
	
	
	}
	*/
}