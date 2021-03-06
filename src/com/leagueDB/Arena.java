package com.leagueDB;

import javax.persistence.*;

@Entity @Table(name="ARENA", schema="JPBUTCHER")
public class Arena {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ARENAID")
	private int arenaId;
	@Column(name="ARENANAME")
	private String arenaname;
	@Column(name="CAPACITY")
	private int capacity;
	@Column(name="CITY")
	private String city;
	@Column(name="COUNTRY")
	private String country;
	@Column(name="PHONE")
	private String phone;
	@Column(name="POSTALCODE")
	private String postalcode;
	@Column(name="STATE_PROVINCE")
	private String state_province;
	@Column(name="STREETADDRESS")
	private String streetaddress;
	
	public Arena(){}
	
	public int getArenaId() {
		return arenaId;
	}
	public void setArenaId(int arenaId) {
		this.arenaId = arenaId;
	}
	public String getArenaname() {
		return arenaname;
	}
	public void setArenaname(String arenaname) {
		this.arenaname = arenaname;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
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
