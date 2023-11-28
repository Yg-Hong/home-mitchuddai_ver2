package com.whereismyhome.house.house.dto;

public class HouseDto {

	private long aptCode;	//
	private String apartmentName;
	private String lng;	//
	private String lat;	//
	private String dealAmount;	//
	private int dealYear;	//
	private int dealMonth;	//
	private int dealDay;	//
	private String area;	//
	
//	private int buildYear;
//	private String roadName;
//	private String roadNameBonbun;
//	private String roadNameBubun;
//	private String roadNameSeq;
//	private String roadNameBasementCode;
//	private String roadNameCode;
//	private String dong;
//	private String bonbun;
//	private String bubun;
//	private String sigunguCode;
//	private String eubmyundongCode;
//	private String dongCode;
//	private String landCode;
//	private String jibun;
//	private long no;
//	private String floor;
//	private String cancelDealType;
	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public HouseDto(long aptCode, String apartmentName, String lng, String lat, String dealAmount, int dealYear,
			int dealMonth, int dealDay, String area) {
		super();
		this.aptCode = aptCode;
		this.apartmentName = apartmentName;
		this.lng = lng;
		this.lat = lat;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
	}
	@Override
	public String toString() {
		return "HouseInfoDto [aptCode=" + aptCode + ", apartmentName=" + apartmentName + ", lng=" + lng + ", lat=" + lat
				+ ", dealAmount=" + dealAmount + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay="
				+ dealDay + ", area=" + area + "]";
	}

}
