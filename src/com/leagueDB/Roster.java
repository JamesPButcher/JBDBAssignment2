package com.leagueDB;

import javax.persistence.*;

@Entity @Table(name="ROSTER", schema="JPBUTCHER")
public class Roster {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROSTERID")
	private int rosterId;
	
	@Column(name="POSITION")
	private String position;
	
	@Column(name="JERSEY")
	private int jersey;
	
	@ManyToOne
	@JoinColumn(name="player")
	private Player player;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="team")
	private Team team;
	
	public Roster(){}
	
	public int getRosterId() {
		return rosterId;
	}
	public void setRosterId(int rosterId) {
		this.rosterId = rosterId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getJersey() {
		return jersey;
	}
	public void setJersey(int jersey) {
		this.jersey = jersey;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
}
