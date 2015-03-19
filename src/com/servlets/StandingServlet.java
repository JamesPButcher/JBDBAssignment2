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

/**
 * Servlet implementation class StandingServlet
 */
@WebServlet("/StandingServlet")
public class StandingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StandingServlet() {
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
			
			List<Game> upcomingGames = em.createQuery("SELECT g FROM Game g "
					+ "WHERE g.homescore IS NOT null AND "
					+ "g.visitorscore IS NOT null ", Game.class)
					.getResultList();
			Iterator<Game> gameIt = upcomingGames.iterator();
			
			String games = "<h2>League Standings</h2><br/><table class=\"table table-striped\" data-sort-name=\"pts\">";
			
			games += "<tr><th>Team Name</th><th data-sortable=\"true\" data-field=\"pts\" data-sorter=\"ptssorter\">Points</th><th>Games Played</th><th>Total Wins</th><th>Total Losses</th><th>Total Overtime Losses</th></tr>";
			
			PlayedGame[] pgs = new PlayedGame[1024];
			
			while(gameIt.hasNext())
			{
				Game g = gameIt.next();
			
				boolean homeInResult = false;
				boolean awayInResult = false;
				for (int i = 0; i < pgs.length; i++)
				{
					if (pgs[i] != null && pgs[i].teamname == g.getHome().getTeamname() )
					{
						homeInResult = true;
					}
					if (pgs[i] != null && pgs[i].teamname == g.getVisitor().getTeamname() )
					{
						awayInResult = true;
					}
				}
				
				if (!homeInResult)
				{
					for (int i = 0; i < pgs.length; i++)
					{
						if (pgs[i] == null)
						{
							pgs[i] = new PlayedGame();
							pgs[i].teamname = g.getHome().getTeamname();
							System.out.println("Home created: " + pgs[i].teamname);
							break;
						}
					}
				}
				
				if (!awayInResult)
				{
					for (int i = 0; i < pgs.length; i++)
					{
						if (pgs[i] == null)
						{
							pgs[i] = new PlayedGame();
							pgs[i].teamname = g.getVisitor().getTeamname();
							System.out.println("Away created: " + pgs[i].teamname);
							break;
						}
						
					}
				}
				
				for (int i = 0; i < pgs.length; i++)
				{
					
					if (pgs[i] != null)
					{
						
							int points = 0;
							if (pgs[i].teamname == g.getHome().getTeamname() && Integer.parseInt(g.getHomescore()) > Integer.parseInt(g.getVisitorscore()))
							{
								if (g.getOt().contains("Y") || g.getSo().contains("Y"))
								{
									points = 2;
									pgs[i].totalWins += 1;
									pgs[i].gamesplayed +=1;
								}
								else
								{
									points = 1;
									pgs[i].totalWins += 1;
									pgs[i].gamesplayed +=1;
								}
							}
							else if (pgs[i].teamname == g.getHome().getTeamname() && Integer.parseInt(g.getHomescore()) < Integer.parseInt(g.getVisitorscore()))
							{
								if (g.getOt().contains("Y") || g.getSo().contains("Y"))
								{
									points = 0;
									pgs[i].totalOTLosses += 1;
									pgs[i].gamesplayed +=1;
								}
								else
								{
									points = 0;
									pgs[i].totalLosses += 1;
									pgs[i].gamesplayed +=1;
								}
							}
							
							if (pgs[i].teamname == g.getVisitor().getTeamname() && Integer.parseInt(g.getHomescore()) < Integer.parseInt(g.getVisitorscore()))
							{
								if (g.getOt().contains("Y") || g.getSo().contains("Y"))
								{
									points = 2;
									pgs[i].totalWins += 1;
									pgs[i].gamesplayed +=1;
								}
								else
								{
									points = 1;
									pgs[i].totalWins += 1;
									pgs[i].gamesplayed +=1;
								}
							}
							else if (pgs[i].teamname == g.getVisitor().getTeamname() && Integer.parseInt(g.getHomescore()) > Integer.parseInt(g.getVisitorscore()))
							{
								if (g.getOt().contains("Y") || g.getSo().contains("Y"))
								{
									points = 0;
									pgs[i].totalOTLosses += 1;
									pgs[i].gamesplayed +=1;
								}
								else
								{
									points = 0;
									pgs[i].totalLosses += 1;
									pgs[i].gamesplayed +=1;
								}
							}
							pgs[i].points += points;
						}
					}
						
			}
			
			
			for (int i = 0; i < pgs.length; i++)
			{
				if (pgs[i] != null)
				{
					games += "<tr>"
							+ "<td>" + pgs[i].teamname + "</td>"
							+ "<td>" + pgs[i].points + "</td>"
							+ "<td>" + pgs[i].gamesplayed + "</td>"
							+ "<td>" + pgs[i].totalWins + "</td>"
							+ "<td>" + pgs[i].totalLosses + "</td>"
							+ "<td>" + pgs[i].totalOTLosses + "</td>"
							+ "</tr>";
				}
			}
			
		
			
			games += "</table>";
			
			games += "<script> \r\n"
    +"function ptssorter(a, b) { \r\n"
     +"   a = +a.substring(1); \r\n"
      +"  b = +b.substring(1); \r\n"
       +" if (a > b) return 1; \r\n"
       +" if (a < b) return -1; \r\n"
        + "return 0; \r\n"
    +"} \r\n" 
+"</script> \r\n"
+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.6.0/bootstrap-table.min.js\"></script>";
			
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
	
	class PlayedGame{
	
		int points;
		String teamname;
		int totalWins;
		int totalLosses;
		int totalOTLosses;
		int gamesplayed;
		
	}
	
}
