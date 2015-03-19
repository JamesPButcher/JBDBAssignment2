package com.servlets;

import java.io.IOException;
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
import com.leagueDB.Team;

/**
 * Servlet implementation class ScheduleServlet
 */
@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleServlet() {
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
		String teamId = "";
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try
		{
			// instantiate emf and em
			emf = Persistence.createEntityManagerFactory("LeagueService");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			teamId = (String) request.getParameter("teamId");
			
			if(teamId == null || teamId == "")
			{
				String error = "No team selected";
				request.setAttribute("error", error);
    	
				url ="/error.jsp";
			}
			
			Team team = em.createQuery("SELECT t FROM Team t WHERE t.teamId = :teamId", Team.class)
					.setParameter("teamId", teamId)
					.getSingleResult();
			
			List<Game> upcomingGames = em.createQuery("SELECT g FROM Game g "
					+ "WHERE "
					+ "(g.home = :teamId OR g.visitor = :teamId)"
					+ "order by g.gamedate ASC, g.gametime ASC", Game.class)
					.setParameter("teamId", team)
					.getResultList();
			Iterator<Game> gameIt = upcomingGames.iterator();
			
			String games = "<h2>" + team.getTeamname() + " Game Schedule</h2><br/><table class=\"table table-striped\">";
			
			games += "<tr><th>Date</th><th>Time</th><th>Score</th><th>Home Team</th><th>Visitor Team</th><th>Arena</th></tr>";
			
			
			while(gameIt.hasNext())
			{
				Game g = gameIt.next();
				
				games += "<tr>"
						+ "<td>" + g.getGamedate() + "</td>"
						+ "<td>" + g.getGametime() + "</td>";
				
						if (g.getHomescore() != null && g.getVisitorscore() != null)
						games += "<td>" + g.getHomescore() + "-" + g.getVisitorscore() + getOtSo(g) + "</td>";
						else
						games += "<td></td>";
						
						games += "<td>" + g.getHome().getTeamname() + "</td>"
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
	
	public String getOtSo(Game g)
	{
		if (g.getSo().contains("Y"))
		{
			return "(SO)";
		}
		if (g.getOt().contains("Y"))
		{
			return "(OT)";
		}
		return "";
	}

}
