package com.leagueDB;

import java.sql.Time;
import java.sql.Date;

import javax.persistence.*;

@Entity @Table(name="GAME", schema="JPBUTCHER")
public class Game {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GAMEID")
	private int gameId;
	@Column(name="GAMEDATE")
	private Date gamedate;
	@Column(name="GAMETIME")
	private Time gametime;
	@Column(name="HOMESCORE", nullable=true)
	private String homescore;
	@Column(name="OT")
	private String ot;
	@Column(name="SO")
	private String so;
	@Column(name="VISITORSCORE", nullable=true)
	private String visitorscore;
	@ManyToOne
	@JoinColumn(name="arena")
	private Arena arena;
	@ManyToOne
	@JoinColumn(name="home")
	private Team home;
	@ManyToOne
	@JoinColumn(name="visitor")
	private Team visitor;
	@ManyToOne
	@JoinColumn(name="schedule")
	private Schedule schedule;
	
	public Game(){}

	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public Date getGamedate() {
		return gamedate;
	}
	public void setGamedate(Date gamedate) {
		this.gamedate = gamedate;
	}
	public Time getGametime() {
		return gametime;
	}
	public void setGametime(Time gametime) {
		this.gametime = gametime;
	}
	public String getHomescore() {
		return homescore;
	}
	public void setHomescore(String homescore) {
		
		this.homescore = homescore;
	}
	public String getOt() {
		return ot;
	}
	public void setOt(String ot) {
		this.ot = ot;
	}
	public String getSo() {
		return so;
	}
	public void setSo(String so) {
		this.so = so;
	}
	public String getVisitorscore() {
		return visitorscore;
	}
	public void setVisitorscore(String visitorscore) {
		
		this.visitorscore = visitorscore;
	}
	public Arena getArena() {
		return arena;
	}
	public void setArena(Arena arena) {
		this.arena = arena;
	}
	public Team getHome() {
		return home;
	}
	public void setHome(Team home) {
		this.home = home;
	}
	public Team getVisitor() {
		return visitor;
	}
	public void setVisitor(Team visitor) {
		this.visitor = visitor;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
