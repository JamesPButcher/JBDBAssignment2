package com.leagueDB;

import javax.persistence.*;

@Entity @Table(name="SCHEDULE", schema="JPBUTCHER")
public class Schedule {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SCHEDULEID")
	private int scheduleId;
	@Column(name="SCHEDULENAME")
	private String schedulename;
	@Column(name="SEASON")
	private String season;
	@Column(name="SPONSOR")
	private String sponsor;
	@ManyToOne
	@JoinColumn(name="league")
	private League league;
	
	public Schedule(){}
	
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getSchedulename() {
		return schedulename;
	}
	public void setSchedulename(String schedulename) {
		this.schedulename = schedulename;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public League getLeague() {
		return league;
	}
	public void setLeague(League league) {
		this.league = league;
	}
}
