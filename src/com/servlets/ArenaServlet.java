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

import com.leagueDB.Arena;
import com.leagueDB.Game;

/**
 * Servlet implementation class ArenaServlet
 */
@WebServlet("/ArenaServlet")
public class ArenaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArenaServlet() {
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
			
			List<Arena> upcomingGames = em.createQuery("SELECT a FROM Arena a "
								+ "order by a.arenaname ASC", Arena.class)
					.getResultList();
			Iterator<Arena> gameIt = upcomingGames.iterator();
			
			String games = "<h2>Arena Information</h2><br/><table class=\"table table-striped\">";
			
			games += "<tr><th>Name</th><th>Address</th><th>Phone</th><th>Capacity</th></tr>";
			
			
			while(gameIt.hasNext())
			{
				Arena g = gameIt.next();
				
				games += "<tr>"
						+ "<td>" + g.getArenaname() + "</td>"
						+ "<td>" + g.getStreetaddress() +",<br/>" + g.getCity() + ", "
							+ g.getState_province() + "<br/>" + g.getPostalcode()
							+ ", " + g.getCountry()+  "</td>"
						+ "<td>" + g.getPhone() + "</td>"
						+ "<td>" + g.getCapacity() + "</td>"
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
