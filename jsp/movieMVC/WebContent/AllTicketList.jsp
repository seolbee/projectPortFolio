<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<c:forEach var="movie" items="${AllTicketList}">
	<c:if test="${not empty movie[0].movieName }">
		<h1>${movie[0].movieName}</h1>
		<table border=1 style="text-align:center">
			<tr>
				<th>티켓 번호</th>
				<th>예매자</th>
				<th>상영일</th>
				<th>상영관</th>
			</tr>
			<c:forEach var="ticket" items="${movie}">
				<tr>
					<td>${ticket.ticketNo }</td>
					<td>${ticket.id }</td>
					<td>${ticket.runDay }</td>
					<td>${ticket.roomNo }관</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</c:forEach>
<%@ include file="footer.jsp" %>