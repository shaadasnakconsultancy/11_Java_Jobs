package com.snak.dto;

import java.util.Date;

public class excelJobTrackerDTO {


    private String batchID;
    private String fileName;
    private String fullPath;
    private String processedAt;
    private String sheetName;
    private String insertedRows;
    private String deletedRows;
    private String errorMessages;
	public String getBatchID() {
		return batchID;
	}
	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public String getProcessedAt() {
		return processedAt;
	}
	public void setProcessedAt(String processedAt) {
		this.processedAt = processedAt;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getInsertedRows() {
		return insertedRows;
	}
	public void setInsertedRows(String insertedRows) {
		this.insertedRows = insertedRows;
	}
	public String getDeletedRows() {
		return deletedRows;
	}
	public void setDeletedRows(String deletedRows) {
		this.deletedRows = deletedRows;
	}
	public String getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}
	@Override
	public String toString() {
		return "excelJobTrackerDTO [batchID=" + batchID + ", fileName=" + fileName + ", fullPath=" + fullPath
				+ ", processedAt=" + processedAt + ", sheetName=" + sheetName + ", insertedRows=" + insertedRows
				+ ", deletedRows=" + deletedRows + ", errorMessages=" + errorMessages + "]";
	}


    // Getters & Setters
     
}
