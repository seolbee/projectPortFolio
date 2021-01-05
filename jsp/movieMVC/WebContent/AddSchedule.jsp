<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%
	int movieNo = Integer.parseInt(request.getParameter("movieNo"));
%>

<form action="AddSchedule.do" method="post">
	<input type="hidden" name="movieNo" value="<%= movieNo %>"/> <br />
	<select name="roomNo">
		<c:forEach var="i" begin="1" end="10">
			<option value="${i}">${i}관</option>
		</c:forEach>
	</select> <br />
	<input type="text" name="runDay" placeholder="Ex)2020-12-01 13:00" /> <br />
	<input type="submit" value="등록" />
</form>

<%@ include file="footer.jsp" %>