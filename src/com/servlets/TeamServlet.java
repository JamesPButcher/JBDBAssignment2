package com.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.persistence.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leagueDB.*;

/**
 * Servlet implementation class TeamServlet
 */
@WebServlet("/TeamServlet")
public class TeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HandleRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HandleRequest(request, response);
	}
	
	public void HandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String teamId = "";
		EntityManagerFactory emf = null;
		EntityManager em = null;
		String url = "/DisplayTeam.jsp";
		
		try {
			teamId = (String) request.getParameter("teamId");
			
			if(teamId == null || teamId == "")
			{
				String error = "No team selected";
				request.setAttribute("error", error);
    	
				url ="/error.jsp";
			}
			
			// instantiate emf and em
			emf = Persistence.createEntityManagerFactory("LeagueService");
			em = emf.createEntityManager();
		
	       // Begin a transaction within the session
			em.getTransaction().begin();
			
			// Get team
			Team team = em.createQuery("SELECT t FROM Team t WHERE t.teamId = :teamId", Team.class)
					.setParameter("teamId", teamId)
					.getSingleResult();
			
			request.setAttribute("team", team);
			
			request.setAttribute("headCoach", (team.getHeadcoach().getFirstname()
								+ " " + team.getHeadcoach().getLastname()));
			request.setAttribute("asstCoach", (team.getAsstcoach().getFirstname()
					+ " " + team.getAsstcoach().getLastname()));
			request.setAttribute("trainer", (team.getTrainer().getFirstname()
					+ " " + team.getTrainer().getLastname()));
			request.setAttribute("manager", (team.getManager().getFirstname()
					+ " " + team.getManager().getLastname()));
			
			// Iterate through roster and display name, jersey number and position
			Iterator<Roster> roster = team.getRoster().iterator();
			String result = "<ul>";
			while(roster.hasNext())
			{
				Roster r = roster.next();
				result += "<li>" + r.getPlayer().getFirstname() + " "
						+ r.getPlayer().getLastname() + " - "
						+ r.getJersey() + " - " + r.getPosition() + "</li>";
				
			}
			result += "</ul>";
			
			request.setAttribute("players", result);
			
			// Commit then close manager and manager factory
           em.getTransaction().commit();
	       em.close();
	       emf.close();

	     }

	    catch(Exception e) {
	    	String error = "Error: Stacktrace: " + e.getStackTrace()
	    					+ " - Exception Message: " + e.getMessage() + "ID: " + teamId;
	    	request.setAttribute("error", error);
	    	
	    	url ="/error.jsp";
	    }
		finally
		{
			
		}
		
		ServletContext context = getServletContext();
	    RequestDispatcher dispatcher = context.getRequestDispatcher(url);
	    dispatcher.forward(request,response);
		
	}

}
