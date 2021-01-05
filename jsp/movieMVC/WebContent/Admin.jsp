<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<h1>관리자 페이지</h1>

<table border=1 style="text-align:center">
	<tr>
		<th>가입한 유저</th>
		<th>현재 영화 개수</th>
		<th>현재 영화 예매 수</th>
	</tr>
	<tr>
		<td>${userCnt}</td>
		<td>${movieCnt }</td>
		<td>${ticketCnt }</td>
	</tr>
</table>
<%@ include file="footer.jsp" %>