package com.leagueDB;

import javax.persistence.*;

@Entity @Table(name="LEAGUE", schema="JPBUTCHER")
public class League {
	
	@Id @Column(name="LEAGUEID")
	private String leagueId;
	@Column(name="LEAGUENAME")
	private String leaguename;
	@Column(name="LEAGUESPONSOR")
	private String leaguesponsor;
	
	public League(){}
	
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	public String getLeaguename() {
		return leaguename;
	}
	public void setLeaguename(String leaguename) {
		this.leaguename = leaguename;
	}
	public String getLeaguesponsor() {
		return leaguesponsor;
	}
	public void setLeaguesponsor(String leaguesponsor) {
		this.leaguesponsor = leaguesponsor;
	}
}
