package com.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leagueDB.Player;
import com.leagueDB.Roster;
import com.leagueDB.Team;

/**
 * Servlet implementation class PlayerServlet
 */
@WebServlet("/PlayerServlet")
public class PlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerServlet() {
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
		String url = "/DisplayPlayer.jsp";
		EntityManagerFactory emf = null;
		EntityManager em = null;
		int playerId = 0;
		
		try
		{
			playerId = Integer.parseInt(request.getParameter("playerId"));
			
//			if(playerId == null || playerId == "")
//			{
//				String error = "No team selected";
//				request.setAttribute("error", error);
//    	
//				url ="/error.jsp";
//			}
		
			// instantiate emf and em
			emf = Persistence.createEntityManagerFactory("LeagueService");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Player player = em.createQuery("SELECT p FROM Player p WHERE p.playerId = :playerId", Player.class)
							.setParameter("playerId", playerId)
							.getSingleResult();
			// Get team
			Roster roster = em.createQuery("SELECT t FROM Roster t WHERE t.player = :player", Roster.class)
						.setParameter("player", player)
						.getSingleResult();
				
				
			String result = "<h3>Player</h3>";
			result += "<table class=\"table table-striped\">";
			result += "<tr><tr><th>Jersey</th><th>Last Name</th><th>First Name</th><th>Position</th><th>Weight</th><th>Height</th><th>Home Town</th><th>Province/State</th><th>Country</th><th>School</th></tr>";
			result += "<tr>";
			result += "<td>" + roster.getJersey() + "</td>";
			result += "<td>" + roster.getPlayer().getLastname() + "</td>";
			result += "<td>" + roster.getPlayer().getFirstname() + "</td>";
			result += "<td>" + roster.getPosition() + "</td>";
			result += "<td>" + roster.getPlayer().getWeight() + "</td>";
			result += "<td>" + roster.getPlayer().getHeight() + "</td>";
			result += "<td>" + roster.getPlayer().getCity() + "</td>";
			result += "<td>" + roster.getPlayer().getState_province() + "</td>";
			result += "<td>" + roster.getPlayer().getCountry() + "</td>";
			if (roster.getPlayer().getSchool() != "")
				result += "<td>" + roster.getPlayer().getSchool() + "</td>";
			else
				result += "<td></td>";
			result += "</tr>";
			result += "</table>";
				
				
			request.setAttribute("player", result);
			
			em.getTransaction().commit();
		    em.close();
		    emf.close();
		}
		catch (Exception ex)
		{
			String error = "Error: Stacktrace: " + ex.getStackTrace()
					+ " - Exception Message: " + ex.getMessage();
			request.setAttribute("error", error);
	
			url ="/error.jsp";
		}
		
		ServletContext context = getServletContext();
	    RequestDispatcher dispatcher = context.getRequestDispatcher(url);
	    dispatcher.forward(request,response);
	}
	
}
