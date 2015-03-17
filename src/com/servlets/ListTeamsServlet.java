package com.servlets;

import java.io.IOException;
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

import com.leagueDB.Team;

/**
 * Servlet implementation class ListTeamsServlet
 */
@WebServlet("/ListTeamsServlet")
public class ListTeamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListTeamsServlet() {
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
		EntityManagerFactory emf = null;
		EntityManager em = null;
		String url = "/DisplayTeams.jsp";
		
		try
		{
			emf = Persistence.createEntityManagerFactory("LeagueService");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			
			List<Team> teams = em.createQuery("SELECT t FROM Team t").getResultList();
			request.setAttribute("teams", teams);
			
			em.getTransaction().commit();
			em.close();
			emf.close();
		}
		catch(Exception ex)
		{
			String error = "Stacktrace: " + ex.getStackTrace()
					+ " - Exception Message: " + ex.getMessage();
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
