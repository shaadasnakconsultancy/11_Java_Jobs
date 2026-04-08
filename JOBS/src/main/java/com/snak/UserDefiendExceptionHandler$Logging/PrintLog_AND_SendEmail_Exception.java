package com.snak.UserDefiendExceptionHandler$Logging;


public class PrintLog_AND_SendEmail_Exception {

	String className;
	String methodName;
	String excelFileName;
	String logMessage1;
	String logMessage2;
	public PrintLog_AND_SendEmail_Exception(String className, String methodName, String excelFileName,
			String logMessage1, String logMessage2) {
		super();
		this.className = className;
		this.methodName = methodName;
		this.excelFileName = excelFileName;
		this.logMessage1 = logMessage1;
		this.logMessage2 = logMessage2;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return  className +" -> "+ methodName +" -> "+ excelFileName +" -> "+  logMessage1 +" -> "+ logMessage2;
	}





	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	public String getLogMessage1() {
		return logMessage1;
	}
	public void setLogMessage1(String logMessage1) {
		this.logMessage1 = logMessage1;
	}
	public String getLogMessage2() {
		return logMessage2;
	}
	public void setLogMessage2(String logMessage2) {
		this.logMessage2 = logMessage2;
	}

	
	
	
}