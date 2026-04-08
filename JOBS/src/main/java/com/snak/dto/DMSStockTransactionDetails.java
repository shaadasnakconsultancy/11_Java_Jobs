package com.snak.dto;

import java.sql.Timestamp;
import java.util.Date;

public class DMSStockTransactionDetails {

	private Long autoId=null;
	private String dealerCode=null;
	private String dealerActCode=null;
	private String dealerName=null;
	private Date stockDate=null;
	private String mslSalVarCd=null;
	private String colorCode=null;
	private double totalStockQty;
	private double totalRetailQty;
	private double totalRetailValue;
	private double totalSaleReturnQty;
	private Timestamp createdDate=null;
	private Timestamp receivedDate=null;
	private String sieModel=null;
	private double totalInTransitQty;
	private String zone=null;
	private String state=null;
	private String vehicleType=null;
	private String modelDesc=null;
	private String commercialModelCode=null;
	private String commercialModelDesc=null;
	private String specCode=null;
	private String displacement=null;
	private String segmentCode=null;
	private String modelReqBySales=null;
	
	private String district=null;
	private String town=null;
	private String status=null;
	private int stockDateIndex;
	
	
	public String getDealerActCode() {
		return dealerActCode;
	}
	public void setDealerActCode(String dealerActCode) {
		this.dealerActCode = dealerActCode;
	}
	public Long getAutoId() {
		return autoId;
	}
	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}
	public int getStockDateIndex() {
		return stockDateIndex;
	}
	public void setStockDateIndex(int stockDateIndex) {
		this.stockDateIndex = stockDateIndex;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setTotalStockQty(double totalStockQty) {
		this.totalStockQty = totalStockQty;
	}
	public void setTotalRetailQty(double totalRetailQty) {
		this.totalRetailQty = totalRetailQty;
	}
	public void setTotalRetailValue(double totalRetailValue) {
		this.totalRetailValue = totalRetailValue;
	}
	public void setTotalSaleReturnQty(double totalSaleReturnQty) {
		this.totalSaleReturnQty = totalSaleReturnQty;
	}
	public void setTotalInTransitQty(double totalInTransitQty) {
		this.totalInTransitQty = totalInTransitQty;
	}
	public String getModelReqBySales() {
		return modelReqBySales;
	}
	public void setModelReqBySales(String modelReqBySales) {
		this.modelReqBySales = modelReqBySales;
	}
	public String getDisplacement() {
		return displacement;
	}
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}
	public String getSegmentCode() {
		return segmentCode;
	}
	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
	}
	public String getDealerCode() {
		return dealerCode;
	}
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public Date getStockDate() {
		return stockDate;
	}
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}
	public String getMslSalVarCd() {
		return mslSalVarCd;
	}
	public void setMslSalVarCd(String mslSalVarCd) {
		this.mslSalVarCd = mslSalVarCd;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public Double getTotalStockQty() {
		return totalStockQty;
	}
	public void setTotalStockQty(Double totalStockQty) {
		this.totalStockQty = totalStockQty;
	}
	public Double getTotalRetailQty() {
		return totalRetailQty;
	}
	public void setTotalRetailQty(Double totalRetailQty) {
		this.totalRetailQty = totalRetailQty;
	}
	public Double getTotalRetailValue() {
		return totalRetailValue;
	}
	public void setTotalRetailValue(Double totalRetailValue) {
		this.totalRetailValue = totalRetailValue;
	}
	public Double getTotalSaleReturnQty() {
		return totalSaleReturnQty;
	}
	public void setTotalSaleReturnQty(Double totalSaleReturnQty) {
		this.totalSaleReturnQty = totalSaleReturnQty;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Timestamp receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getSieModel() {
		return sieModel;
	}
	public void setSieModel(String sieModel) {
		this.sieModel = sieModel;
	}
	public Double getTotalInTransitQty() {
		return totalInTransitQty;
	}
	public void setTotalInTransitQty(Double totalInTransitQty) {
		this.totalInTransitQty = totalInTransitQty;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getModelDesc() {
		return modelDesc;
	}
	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}
	public String getCommercialModelCode() {
		return commercialModelCode;
	}
	public void setCommercialModelCode(String commercialModelCode) {
		this.commercialModelCode = commercialModelCode;
	}
	public String getCommercialModelDesc() {
		return commercialModelDesc;
	}
	public void setCommercialModelDesc(String commercialModelDesc) {
		this.commercialModelDesc = commercialModelDesc;
	}
	public String getSpecCode() {
		return specCode;
	}
	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}
}