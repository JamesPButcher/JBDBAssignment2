<%@ page import="java.util.List" %>
<%@ page import="com.leagueDB.Team" %>
<%@ include file="header.jsp" %>

<h2>NHL Teams</h2>

<form method="POST" action="TeamServlet">
	<table class="table table-striped">
		<tr>
			<th>Team</th><th></th>
		</tr>
	
	    <%
	    	List<Team> eList = (List)request.getAttribute("teams");
	    	for(int i=0; i<eList.size();i++){%>
	        	<tr>
	            	<td><%= ((Team)eList.get(i)).getTeamname() %></td>
	            	<td><a href="TeamServlet?teamId=<%= ((Team)eList.get(i)).getTeamId() %>">View Team</a></td>  
	        	</tr>
	      	<%}
	    %>
		
	</table>
</form>

<%@ include file="footer.jsp" %>