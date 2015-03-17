package com.leagueDB;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity @Table(name="TEAM", schema="JPBUTCHER")
public class Team {
	
	@Id @Column(name="TEAMID")
	private String teamId;
	@Column(name="TEAMNAME")
	private String teamname;
	@Column(name="SPONSOR")
	private String sponsor;
	@Column(name="WEBSITE")
	private String website;
	@ManyToOne
	@JoinColumn(name="headcoach")
	private Staff headcoach;
	@ManyToOne
	@JoinColumn(name="asstcoach")
	private Staff asstcoach;
	@ManyToOne
	@JoinColumn(name="manager")
	private Staff manager;
	@ManyToOne
	@JoinColumn(name="trainer")
	private Staff trainer;
	@ManyToOne
	@JoinColumn(name="league")
	private League league;
	
	@OneToMany(targetEntity=Roster.class, mappedBy="team")
	@OrderBy("jersey ASC")
	private Set<Roster> roster = new HashSet<Roster>();
	
	// roster specific region
	public Set<Roster> getRoster() {
		return roster;
	}

	public void setRoster(Set<Roster> roster) {
		this.roster = roster;
	}
	
	public void addPlayer(Roster teammate)
	{
		((Roster)roster).setTeam(this);
		roster.add(teammate);
	}
	// end of roster specific region

	public Team(){}
	
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Staff getHeadcoach() {
		return headcoach;
	}
	public void setHeadcoach(Staff headcoach) {
		this.headcoach = headcoach;
	}
	public Staff getAsstcoach() {
		return asstcoach;
	}
	public void setAsstcoach(Staff asstcoach) {
		this.asstcoach = asstcoach;
	}
	public Staff getManager() {
		return manager;
	}
	public void setManager(Staff manager) {
		this.manager = manager;
	}
	public Staff getTrainer() {
		return trainer;
	}
	public void setTrainer(Staff trainer) {
		this.trainer = trainer;
	}
	public League getLeague() {
		return league;
	}
	public void setLeague(League league) {
		this.league = league;
	}

}
