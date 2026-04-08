package com.snak.dto;
 
public class Result_Units_DTO{

    private Integer id;

    private String sno;
    private String fy;
    private String actual;
    private String country;
    private String firstHalf;
    private String secondHalf;
    private String model;
    private String group;

    private String apr;
    private String may;
    private String jun;
    private String jul;
    private String aug;
    private String sep;
    private String oct;
    private String nov;
    private String dec;
    private String jan;
    private String feb;
    private String mar;

    private String month;
    private String accumulated;
    private String lastMonth;

    private String rawLabel;
    private String baseModel;
    private String vehicleType;
    private String xModelCode;
  

    // ===== Getters and Setters =====

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getFy() {
        return fy;
    }

    public void setFy(String fy) {
        this.fy = fy;
    }

    public String getActual () {
        return actual;
    }

    public void setActual (String actual ) {
        this.actual = actual ;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return model;
    }
	   

    @Override
	public String toString() {
		return "Result_Units_DTO [sno=" + sno + ", fy=" + fy + ", actual=" + actual + ", country=" + country
				+ ", firstHalf=" + firstHalf + ", secondHalf=" + secondHalf + ", model=" + model + ", group=" + group
				+ ", apr=" + apr + ", may=" + may + ", jun=" + jun + ", jul=" + jul + ", aug=" + aug + ", sep=" + sep
				+ ", oct=" + oct + ", nov=" + nov + ", dec=" + dec + ", jan=" + jan + ", feb=" + feb + ", mar=" + mar
				+ ", month=" + month + ", accumulated=" + accumulated + ", lastMonth=" + lastMonth + ", rawLabel="
				+ rawLabel + ", baseModel=" + baseModel + ", vehicleType=" + vehicleType + ", xModelCode=" + xModelCode
				+ "]";
	}

	public void setModel(String model) {
        this.model = model;
    }

    public String getGroup () {
        return group;
    }

    public void setGroup (String group ) {
        this.group = group ;
    }

    public String getApr() { return apr; }
    public void setApr(String apr) { this.apr = apr; }

    public String getMay() { return may; }
    public void setMay(String may) { this.may = may; }

    public String getJun() { return jun; }
    public void setJun(String jun) { this.jun = jun; }

    public String getJul() { return jul; }
    public void setJul(String jul) { this.jul = jul; }

    public String getAug() { return aug; }
    public void setAug(String aug) { this.aug = aug; }

    public String getSep() { return sep; }
    public void setSep(String sep) { this.sep = sep; }

    public String getOct() { return oct; }
    public void setOct(String oct) { this.oct = oct; }

    public String getNov() { return nov; }
    public void setNov(String nov) { this.nov = nov; }

    public String getDec() { return dec; }
    public void setDec(String dec) { this.dec = dec; }

    public String getJan() { return jan; }
    public void setJan(String jan) { this.jan = jan; }

    public String getFeb() { return feb; }
    public void setFeb(String feb) { this.feb = feb; }

    public String getMar() { return mar; }
    public void setMar(String mar) { this.mar = mar; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public String getAccumulated() { return accumulated; }
    public void setAccumulated(String accumulated) { this.accumulated = accumulated; }

    public String getLastMonth() { return lastMonth; }
    public void setLastMonth(String lastMonth) { this.lastMonth = lastMonth; }

    public String getRawLabel() { return rawLabel; }
    public void setRawLabel(String rawLabel) { this.rawLabel = rawLabel; }

    public String getBaseModel() { return baseModel; }
    public void setBaseModel(String baseModel) { this.baseModel = baseModel; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getxModelCode() { return xModelCode; }
    public void setxModelCode(String xModelCode) { this.xModelCode = xModelCode; }

 }