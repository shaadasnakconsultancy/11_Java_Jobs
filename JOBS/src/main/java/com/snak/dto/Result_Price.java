package com.snak.dto;

public class Result_Price {

	private String SNO;
    private String FY;
    private String DataType;
    private String Country;
    private String firstHalf;        // 1st_half
    private String secondHalf;       // 2nd_half
    private String Model;
    private String Group;
    
    
    private String Category;

    private String Apr;
    private String May;
    private String Jun;
    private String Jul;
    private String Aug;
    private String Sep;
    private String Oct;
    private String Nov;
    private String Dec;
    private String Jan;
    private String Feb;
    private String Mar;

    private String Monthly;
    private String Avaraged;
    private String LastMonth;

    
    //extra non matching columns
    
    private String Accumulated;
    
    public String getAccumulated() {
		return Accumulated;
	}

	public void setAccumulated(String accumulated) {
		Accumulated = accumulated;
	}

	// Default Constructor
    public Result_Price() {
    }

	public String getSNO() {
		return SNO;
	}

	public void setSNO(String sNO) {
		SNO = sNO;
	}

	public String getFY() {
		return FY;
	}

	public void setFY(String fY) {
		FY = fY;
	}

	public String getDataType() {
		return DataType;
	}

	public void setDataType(String dataType) {
		DataType = dataType;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getFirstHalf() {
		return firstHalf;
	}

	public void setFirstHalf(String firstHalf) {
		this.firstHalf = firstHalf;
	}

	public String getSecondHalf() {
		return secondHalf;
	}

	public void setSecondHalf(String secondHalf) {
		this.secondHalf = secondHalf;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public String getGroup() {
		return Group;
	}

	public void setGroup(String group) {
		Group = group;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getApr() {
		return Apr;
	}

	public void setApr(String apr) {
		Apr = apr;
	}

	public String getMay() {
		return May;
	}

	public void setMay(String may) {
		May = may;
	}

	public String getJun() {
		return Jun;
	}

	public void setJun(String jun) {
		Jun = jun;
	}

	public String getJul() {
		return Jul;
	}

	public void setJul(String jul) {
		Jul = jul;
	}

	public String getAug() {
		return Aug;
	}

	public void setAug(String aug) {
		Aug = aug;
	}

	public String getSep() {
		return Sep;
	}

	public void setSep(String sep) {
		Sep = sep;
	}

	public String getOct() {
		return Oct;
	}

	public void setOct(String oct) {
		Oct = oct;
	}

	public String getNov() {
		return Nov;
	}

	public void setNov(String nov) {
		Nov = nov;
	}

	public String getDec() {
		return Dec;
	}

	public void setDec(String dec) {
		Dec = dec;
	}

	public String getJan() {
		return Jan;
	}

	public void setJan(String jan) {
		Jan = jan;
	}

	public String getFeb() {
		return Feb;
	}

	public void setFeb(String feb) {
		Feb = feb;
	}

	public String getMar() {
		return Mar;
	}

	public void setMar(String mar) {
		Mar = mar;
	}

	public String getMonthly() {
		return Monthly;
	}

	public void setMonthly(String monthly) {
		Monthly = monthly;
	}

	public String getAvaraged() {
		return Avaraged;
	}

	public void setAvaraged(String avaraged) {
		Avaraged = avaraged;
	}

	public String getLastMonth() {
		return LastMonth;
	}

	public void setLastMonth(String lastMonth) {
		LastMonth = lastMonth;
	}
    
    
}