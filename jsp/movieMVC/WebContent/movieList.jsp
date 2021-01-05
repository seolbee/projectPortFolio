<%@page import="java.util.Enumeration"%>
<%@page import="movie.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>
<%
	ArrayList<MovieVO> list = (ArrayList<MovieVO>) request.getAttribute("movieList");
	for(MovieVO vo:list){
%>
<div onclick="location.href='viewMovie.do?no=<%=vo.getNo()%>'" style="cursor:pointer; width:400px;">
	<img src="<%=getServletContext().getRealPath("upload") + "/" + vo.getImg()%>" alt="img" style="width:400px"/>
	<p><%= vo.getName() %></p>
</div>
<%
	}
%>
<%@ include file="footer.jsp"%>