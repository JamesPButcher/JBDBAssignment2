<%@ page import="com.leagueDB.Team" %>
<%@ include file="header.jsp" %>

<h2><%= ((Team)request.getAttribute("team")).getTeamname() %></h2>

<p>Manager: ${manager}</p>
<p>Head Coach: ${headCoach}</p>
<p>Assistant Coach: ${asstCoach}</p>
<p>Trainer: ${trainer}</p>
<p>Players:</p>
<%= request.getAttribute("players") %>

<%@ include file="footer.jsp" %>