package com.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

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

import com.leagueDB.Game;
import com.leagueDB.Player;
import com.leagueDB.Roster;

/**
 * Servlet implementation class GamesServlet
 */
@WebServlet("/GamesServlet")
public class GamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GamesServlet() {
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
		String url = "/DisplayUpcomingGames.jsp";
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try
		{
			// instantiate emf and em
			emf = Persistence.createEntityManagerFactory("LeagueService");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			List<Game> upcomingGames = em.createQuery("SELECT g FROM Game g WHERE g.homescore = null AND g.visitorscore = null", Game.class)
					//.setParameter("gameDate", new Date(System.currentTimeMillis()))
					.getResultList();
			Iterator<Game> gameIt = upcomingGames.iterator();
			
			String games = "<table class=\"table table-striped\">";
			
			games += "<tr><th>Date</th><th>Time</th><th>Home Team</th><th>Visitor Team</th><th>Arena</th></tr>";
			
			
			while(gameIt.hasNext())
			{
				Game g = gameIt.next();
				
				games += "<tr>"
						+ "<td>" + g.getGamedate() + "</td>"
						+ "<td>" + g.getGametime() + "</td>"
						+ "<td>" + g.getHome().getTeamname() + "</td>"
						+ "<td>" + g.getVisitor().getTeamname() + "</td>"
						+ "<td>" + g.getArena().getArenaname() + "</td>"
						+ "</tr>";
			}
			
			games += "</table>";
			
			request.setAttribute("games", games);
			
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
