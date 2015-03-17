package com.leagueDB;

import java.util.Date;

import javax.persistence.*;

@Entity @Table(name="PLAYER", schema="JPBUTCHER")
public class Player {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PLAYERID")
	private int playerId;
	@Column(name="CITY")
	private String city;
	@Column(name="COUNTRY")
	private String country;
	@Column(name="DOB")
	private Date dob;
	@Column(name="EMAIL")
	private String email;
	@Column(name="FIRSTNAME")
	private String firstname;
	@Column(name="LASTNAME")
	private String lastname;
	@Column(name="HEIGHT")
	private double height;
	@Column(name="WEIGHT")
	private double weight;
	@Column(name="MOBILE")
	private String mobile;
	@Column(name="PHONE")
	private String phone;
	@Column(name="POSTALCODE")
	private String postalcode;
	@Column(name="SCHOOL")
	private String school;
	@Column(name="STATE_PROVINCE")
	private String state_province;
	@Column(name="STREETADDRESS")
	private String streetaddress;
	
	public Player(){}
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
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
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
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
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
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
