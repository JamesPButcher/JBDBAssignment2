package com.leagueDB;

import javax.persistence.*;

@Entity @Table(name="PLAYERSTATS", schema="JPBUTCHER")
public class PlayerStats {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STATSID")
	private int statsId;
	@ManyToOne
	@JoinColumn(name="player")
	private Player player;
	@ManyToOne
	@JoinColumn(name="roster")
	private Roster roster;
	@Column(name="ASSISTS")
	private int assists;
	@Column(name="GAA")
	private int gaa;
	@Column(name="GOALS")
	private int goals;
	@Column(name="GP")
	private int gp;
	@Column(name="PIM")
	private int pim;
	@Column(name="SO")
	private int so;
	@Column(name="SUSPENSIONS")
	private int suspensions;
	
	public PlayerStats(){}
	
	public int getStatsId() {
		return statsId;
	}
	public void setStatsId(int statsId) {
		this.statsId = statsId;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Roster getRoster() {
		return roster;
	}
	public void setRoster(Roster roster) {
		this.roster = roster;
	}
	public int getAssists() {
		return assists;
	}
	public void setAssists(int assists) {
		this.assists = assists;
	}
	public int getGaa() {
		return gaa;
	}
	public void setGaa(int gaa) {
		this.gaa = gaa;
	}
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	public int getGp() {
		return gp;
	}
	public void setGp(int gp) {
		this.gp = gp;
	}
	public int getPim() {
		return pim;
	}
	public void setPim(int pim) {
		this.pim = pim;
	}
	public int getSo() {
		return so;
	}
	public void setSo(int so) {
		this.so = so;
	}
	public int getSuspensions() {
		return suspensions;
	}
	public void setSuspensions(int suspensions) {
		this.suspensions = suspensions;
	}
}
