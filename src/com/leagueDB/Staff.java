package com.leagueDB;

import javax.persistence.*;

@Entity @Table(name="STAFF", schema="JPBUTCHER")
public class Staff {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STAFFID")
	private int staffId;
	@Column(name="CERTIFICATION")
	private String certification;
	@Column(name="CITY")
	private String city;
	@Column(name="COUNTRY")
	private String country;
	@Column(name="EMAIL")
	private String email;
	@Column(name="FIRSTNAME")
	private String firstname;
	@Column(name="LASTNAME")
	private String lastname;
	@Column(name="MOBILE")
	private String mobile;
	@Column(name="PHONE")
	private String phone;
	@Column(name="POSTALCODE")
	private String postalcode;
	@Column(name="STATE_PROVINCE")
	private String state_province;
	@Column(name="STREETADDRESS")
	private String streetaddress;
	
	public Staff(){}
	
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getState_province() {
		return state_province;
	}
	public void setState_province(String state_province) {
		this.state_province = state_province;
	}
	public String getStreetaddress() {
		return streetaddress;
	}
	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}
}
