package com.snak.Services;
 
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
import com.snak.dto.PlBudgetExport_DTO; 

@Service
public class  PlBudgetExportEndBoundedExcelReader{
	static Logger logger=LoggerFactory.getLogger(PlBudgetExportEndBoundedExcelReader.class);
	Object value =null;
	int columnIndex=0;
	@Autowired
	GlobalExceptionHandler_AND_EmailSender GlobalExceptionHandler_AND_EmailSender;
	File excelFile2=null;
	Row row=null;
	PlBudgetExport_DTO PlBudgetExport_DTO=null;
	List<PlBudgetExport_DTO> rowData = new ArrayList<>();
	 public   List<PlBudgetExport_DTO> readDataBetweenEndMarkers(File excelFile) {



		 InputStream is=null;
		 XSSFWorkbook workbook=null;
	        try {

	        	excelFile2=excelFile;
	        	  is = new FileInputStream(excelFile);
	               workbook = new XSSFWorkbook(is);
	          // ⭐ Required to calculate formulas
	             FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//	             evaluator.evaluateAll();
//	            XSSFSheet sheet = workbook.getSheet("Domestic");
	             XSSFSheet sheet = null;
	             String sheetName=null;
	             int matchCount = 0;
	             String matchedSheetName = null;
	             for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
	                  sheetName = workbook.getSheetName(i);

	                 if (sheetName != null && sheetName.toLowerCase().contains("export")) {
	                	 System.err.println(excelFile+" have sheet with 'export' in it: " + sheetName);
	                	   matchCount++;
	                       matchedSheetName = sheetName;
	                       
	                       // If more than one match, no need to continue
	                       if (matchCount > 1) {
	                           System.err.println("Multiple sheets found with 'export' in name");
	                           GlobalExceptionHandler_AND_EmailSender.handleException(
	           	                    new PrintLog_AND_SendEmail_Exception(
	           	                        "PlBudgetDomesticEndBoundedExcelReader.class",
	           	                        "readDataBetweenEndMarkers()",
	           	                        excelFile.getName(),
	           	                        "in this excel file their are more that one sheet with name 'export' in it, their should be only one sheet with name 'export' ",
	           	                        "not processing domestic data of this excel, moving to other year excel files"
	           	                    )
	           	                );
	                           return null;
	                       }
	                       
	                 }else {
//	                	 sheet=null; 
	                 }
	             }
	             // After loop decision
	             if (matchCount == 1) {
	                 System.out.println("Sheet found: " + matchedSheetName);
	                 sheet = workbook.getSheet(matchedSheetName);
	             } else {
	            	 GlobalExceptionHandler_AND_EmailSender.handleException(
	 	                    new PrintLog_AND_SendEmail_Exception(
	 	                        "PlBudgetDomesticEndBoundedExcelReader.class ",
	 	                        "readDataBetweenEndMarkers() ",
	 	                        excelFile.getName(),
	 	                        "'export' sheet not found in Pl Budget exlec file ",
	 	                        "Skipping this excel file and moving to other years excel files"
	 	                    )
	 	                );
	                 return null;
	             }
	            if (sheet == null) {
	                GlobalExceptionHandler_AND_EmailSender.handleException(
	                    new PrintLog_AND_SendEmail_Exception(
	                        "PlBudgetExportEndBoundedExcelReader.class",
	                        "readDataBetweenEndMarkers()",
	                        excelFile.getName(),
	                        "'Export' sheet not found in Pl Budget exlec file",
	                        "Skipping this excel file and moving to other years excel files"
	                    )
	                );
	                return null;   // stop processing this sheet
	            }
System.out.println(sheet.getSheetName());
	            //   Find boundaries
	            int lastColumn = findLastColumnBeforeEnd(sheet.getRow(0),  evaluator);
	            int lastRow = findLastRowBeforeEnd(sheet,  evaluator);

	            if(lastColumn==-101 || lastRow==-101) {
	             //    	logger.info("not able to find END marker in column or row in ResultTransPackingEndBoundedExcelReader() in :: > "+ ResultPartsEndBoundedExcelReader.class.getName());
	            	GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                         "PlBudgetExportEndBoundedExcelReader.class",
	                         "readDataBetweenEndMarkers()",
	                         excelFile.getName(),
	                         "END marker not found in COLUMN or ROW in sheet "+sheetName+ " in excel file "+excelFile,
	                         " so for pl budget export skipping this file and processing others, Pl Budget excel should have sheet haveing 'export' in its name"
	                         
	              )   );
	            	return null;   // stop processing this sheet
//System.exit(0);
	            }
	            //   Read data only inside END-to-END box
	            for (int r = 1; r <= lastRow; r++) {
	            	//if END marker not found then already thrown exception so log will be maintained and email will be sent
	            	//and null, so caller of this method will continue it loop
	            	 if(lastColumn==-101 || lastRow==-101) {
	            		 return null;
		            		// break;
	            		 
	            	 }
	                  row = sheet.getRow(r);
	                if (row == null) { ; continue ;}

	                PlBudgetExport_DTO=new PlBudgetExport_DTO();	                
	                for (int c = 0; c <= lastColumn; c++) {

	                      value =  readCellValue(row.getCell(c),evaluator);
	                    if(c==0) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    	 
	                    		
	                    		PlBudgetExport_DTO.setFy(String.valueOf( value).trim());
 	
	    					}else {
	    						PlBudgetExport_DTO.setFy("0");	    					}
	                    	 
	                    	 
	                    }else if(c==1) {
	                    	
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		PlBudgetExport_DTO.setDataType(String.valueOf(value).trim());
	                    			    					}else {
	                    			    						PlBudgetExport_DTO.setDataType("");
		    				}
	                    	
	                    	 
	                    	
	                    }else if(c==2) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		PlBudgetExport_DTO.setMarket(String.valueOf(value).trim());
	                    		}else {
	                    			PlBudgetExport_DTO.setMarket("");
	    					}
	                    	 columnIndex++;
	                    }else if(c==3) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{

	                    		PlBudgetExport_DTO.setCountry(String.valueOf(value).trim());
	                    	
	    					}else { 
	    						PlBudgetExport_DTO.setCountry("");
	    					
	    					}
	                    	 columnIndex++;
	                    }else if(c==4) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		{
	                    			PlBudgetExport_DTO.setTradeCountry(String.valueOf(value).trim());
	                    		    
	                    		}
	    					}else {
	    						{
	    							PlBudgetExport_DTO.setTradeCountry("");
	    						}
	    					}
	                    	 columnIndex++;
	                    }else if(c==5) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		{
									PlBudgetExport_DTO.setXcode(String.valueOf(value).trim());
	                    		    
	                    		}
	    					}else {
	    						{
	    							PlBudgetExport_DTO.setXcode("");
	    						}
	    					}
	                    	 columnIndex++;
	                    }else if(c==6) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		{
	                    			PlBudgetExport_DTO.setModelCode(String.valueOf(value).trim());
	                    		    
	                    		}
	    					}else {
	    						{
	    							PlBudgetExport_DTO.setModelCode("");
	    						}
	    					}
	                    	 columnIndex++;
	                    }else if(c==7) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		{
	                    			PlBudgetExport_DTO.setExportCode(String.valueOf(value).trim());
	                    			}
	    					}else {

	    						{
	    							PlBudgetExport_DTO.setExportCode("");
	    						    
	    						}
	    					
	    					}
	                     	columnIndex++;
	                    }else if(c==8) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		{
	                    			PlBudgetExport_DTO.setTradeType(String.valueOf(value).trim());
	                    		    
	                    		}
	    					}else {
	    						{
	    							
	    							PlBudgetExport_DTO.setTradeType("");
	    						    
	    						}
	    					}
	                    	 columnIndex++;
	                    }else if(c==9) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		{
	                    			PlBudgetExport_DTO.setOrderNumber(String.valueOf(value).trim());
	                    		    
	                    		}
	    					}else {
	    						{
	    							
	    							PlBudgetExport_DTO.setOrderNumber("");
	    						    
	    						}
	    					}
	                    	 columnIndex++;
	                    }else if(c==10) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		{
	                    			PlBudgetExport_DTO.setMonth(String.valueOf(value).trim());
	                    		    
	                    		}
	    					}else {
	    						{
	    							PlBudgetExport_DTO.setMonth("");
	    						    
	    						}
	    					}
	                    	 columnIndex++;
	                    }else if(c==11) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		PlBudgetExport_DTO.setSalesUnits(String.valueOf(value).trim());
	    					}else {
	    						PlBudgetExport_DTO.setSalesUnits("");
	    					}
	                    	 columnIndex++;
	                    }else if(c==12) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		PlBudgetExport_DTO.setCurrency(String.valueOf(value).trim());
	    					}else {
	    						PlBudgetExport_DTO.setCurrency("");
	    					}
	                    	 columnIndex++;
	                    }else if(c==13) {
	                    	if(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()))
	    					{
	                    		PlBudgetExport_DTO.setOriginalCurrency(String.valueOf(value).trim());
	    					}else {
	    						PlBudgetExport_DTO.setOriginalCurrency("");
	    					}
	                    	 columnIndex++;
	                    }
	                 // ================= SALES =================
	                    else if(c==14){ PlBudgetExport_DTO.setSalesAmountInrGross(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==15){ PlBudgetExport_DTO.setSaSalesRebate(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==16){ PlBudgetExport_DTO.setSaInsuranceRecovery(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==17){ PlBudgetExport_DTO.setSaFreightRecovery(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==18){ PlBudgetExport_DTO.setSalesAmountInr(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }

	                    // ================= VARIABLE FACTORY =================
	                    else if(c==19){ PlBudgetExport_DTO.setVariableFactoryCost(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==20){ PlBudgetExport_DTO.setVfcMaterialsComponents(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==21){ PlBudgetExport_DTO.setVfcLaborExpenses(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==22){ PlBudgetExport_DTO.setVfcFuel(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==23){ PlBudgetExport_DTO.setVfcSubmaterial(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==24){ PlBudgetExport_DTO.setVfcRoyalty(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==25){ PlBudgetExport_DTO.setVfcOthers(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==26){ PlBudgetExport_DTO.setVfcTotal(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }

	                    // ================= VARIABLE SELLING =================
	                    else if(c==27){ PlBudgetExport_DTO.setVariableSellingCost(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==28){ PlBudgetExport_DTO.setVscCommodityTax(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==29){ PlBudgetExport_DTO.setVscTransportation(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==30){ PlBudgetExport_DTO.setVscPackingExpenses(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==31){ PlBudgetExport_DTO.setVscSalesRebate(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==32){ PlBudgetExport_DTO.setVscSalesCommission(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==33){ PlBudgetExport_DTO.setVscOthers(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==34){ PlBudgetExport_DTO.setVscTotal(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }

	                    // ================= VARIABLE TOTAL =================
	                    else if(c==35){ PlBudgetExport_DTO.setVariableCostTotal(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==36){ PlBudgetExport_DTO.setVctMarginalProfit(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==37){ PlBudgetExport_DTO.setVctRatio(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    // ================= FIXED FACTORY =================
	                    else if(c==38){ PlBudgetExport_DTO.setFixedFactoryCost(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==39){ PlBudgetExport_DTO.setFfcIndirectLabourCost(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==40){ PlBudgetExport_DTO.setFfcDepreciationDiesJigs(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==41){ PlBudgetExport_DTO.setFfcDepreciationOthers(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==42){ PlBudgetExport_DTO.setFfcConsumableCost(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==43){ PlBudgetExport_DTO.setFfcSupplementaryDeptCost(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==44){ PlBudgetExport_DTO.setFfcOthers(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==45){ PlBudgetExport_DTO.setFfcTotal(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }

	                    // ================= CONTRIBUTION =================
	                    else if(c==46){ PlBudgetExport_DTO.setContributionProfitByModel(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==47){ PlBudgetExport_DTO.setContributionRatio(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    
	                    // ================= SELLING =================
	                    else if(c==48){ PlBudgetExport_DTO.setSellingExpenses(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==49){ PlBudgetExport_DTO.setSeMarketingExpensesFixed(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==50){ PlBudgetExport_DTO.setSeSalesDeptExpenses(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==51){ PlBudgetExport_DTO.setSeOthers(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==52){ PlBudgetExport_DTO.setSeTotal(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==53){ PlBudgetExport_DTO.setSeOperatingContributionProfit(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==54){ PlBudgetExport_DTO.setSeRatio(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }

	                    // ================= ADMIN =================
	                    else if(c==55){ PlBudgetExport_DTO.setAdministrativeExpenses(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==56){ PlBudgetExport_DTO.setAeGeneralAdExpenses(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==57){ PlBudgetExport_DTO.setAeWarrantyExpenses(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==58){ PlBudgetExport_DTO.setAeOthers(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==59){ PlBudgetExport_DTO.setAeTotal(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==60){ PlBudgetExport_DTO.setAeOperatingProfit(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==61){ PlBudgetExport_DTO.setAeRatio(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }

	                    // ================= COMPONENT =================
	                    else if(c==62){ PlBudgetExport_DTO.setComponentCost(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==63){ PlBudgetExport_DTO.setCcKdFromSuzuki(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==64){ PlBudgetExport_DTO.setCcImportFromOther(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==65){ PlBudgetExport_DTO.setCcDomesticVendors(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==66){ PlBudgetExport_DTO.setCcMsilEnginePrice(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==67){ PlBudgetExport_DTO.setCcSubcontractorsCost(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==68){ PlBudgetExport_DTO.setCcConversionCost(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==69){ PlBudgetExport_DTO.setCcOthers(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                    else if(c==70){ PlBudgetExport_DTO.setCcTotal(null!=value && !"".equals(value) && !"-".equals(value.toString().trim()) ? String.valueOf(value).trim():""); columnIndex++; }
	                
	                
	                
	                }
	                columnIndex=0;
	               rowData.add(PlBudgetExport_DTO == null ? null :PlBudgetExport_DTO);
	            

	                  
	                
	                
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
//	            logger.info("not able to read data from cell in readDataBetweenEndMarkers()");

	            GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                    "PlBudgetExportEndBoundedExcelReader.class",
	                    "readDataBetweenEndMarkers()",
	                    excelFile.getName() ,
	                    "not able to read data from cell for sheet -> Export in Pl Budget excel",
	                   " so processing other files, check logs ->  " +e.toString()+  "  " +e.getMessage()
	                    
	          )  );
//System.exit(0);
	        }   finally {
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
	            	   
	  	           // logger.info("not able to find END marker in column in findLastColumnBeforeEnd() in :: > "+ EndBoundedExcelReader.class.getName());
	            	  // logger.info("not able to find END marker in column in findLastColumnBeforeEnd() in :: > "+ ResultTransPackingEndBoundedExcelReader.class.getName());

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
	            	   
	  	           // logger.info("not able to find END marker in row in findLastRowBeforeEnd() in :: > "+ EndBoundedExcelReader.class.getName());
	            	//  logger.info("not able to find END marker in row in findLastRowBeforeEnd() in :: > "+ ResultTransPackingEndBoundedExcelReader.class.getName());

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
	                    "PlBudgetExportEndBoundedExcelReader.class",
	                    "readCellValue()",
	                    excelFile2.getName(),
	                    "not able to read data from cell for sheet -> Export sheet in Pl Budget excel",
	                    "so processing other files, check logs -> " + e.toString() + " " + e.getMessage()
	                )
	            );
	        }

	        return cellValue;
	    }
	    /*
	   
	    public Object readCellValue(Cell cell, FormulaEvaluator evaluator)
	    {
	    	CellValue evaluatedValue =null;
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

	                // Try evaluating (works for SUM, IF, etc.)
	                evaluatedValue = evaluator.evaluate(cell);
	                try
	                {

	                    if (evaluatedValue != null)
	                    {
	                        switch (evaluatedValue.getCellType())
	                        {
	                             case Cell.CELL_TYPE_NUMERIC:
	                               cellValue = formatter.formatCellValue(cell, evaluator);
//	                            	 cellValue = String.valueOf(evaluatedValue.getNumberValue());
//	                            	 System.err.println(cellValue);
	                                break;
	                                

//	                        case Cell.CELL_TYPE_NUMERIC:
//	                            cellValue = evaluatedValue.getNumberValue();
//	                            break;
	                            
	                             
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
//	                                cellValue = formatter.formatCellValue(cell);
	                             	 cellValue = String.valueOf(evaluatedValue.getNumberValue());
	                            	 System.err.println(cellValue);
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
	        	e.printStackTrace();
	            logger.info("Exception in readCellValue: " + e.getMessage());
	            cellValue = null;
	            GlobalExceptionHandler_AND_EmailSender.handleException(  new PrintLog_AND_SendEmail_Exception(
	                    "ResultTransPackingEndBoundedExcelReader.class",
	                    "readCellValue()",
	                    excelFile2.getName() ,
	                    "not able to read data from cell for sheet -> Result Trans Packing",
	                   " so processing other files, check logs ->  " +e.toString()+  "  " +e.getMessage()
	                    
	            ));
	        }

	        return cellValue;
	    }
	    
	   
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