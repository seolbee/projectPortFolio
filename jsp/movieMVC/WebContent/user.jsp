<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<h1><%= user.getId() %> 님의 예매 정보 </h1>

<c:choose>
	<c:when test="${not empty ticketList[0].id}">
		<table border=1 style="text-align:center">
			<thead>
				<tr>
					<td>영화 제목</td>
					<td>예매 날짜</td>
					<td>상영 날짜</td>
					<td>상영관</td>
					<td>좌석 번호</td>
					<td>런타임</td>
					<td>예매 취소</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ticket" items="${ticketList}">
					<tr>
						<td>${ticket.movieName}</td>
						<td>${ticket.reserveDay}</td>
						<td>${ticket.runDay }</td>
						<td>${ticket.roomNo }관</td>
						<td>${ticket.seatNo }번</td>
						<td>${ticket.runTime }분</td>
						<td><a href="deleteTicket.do?ticketNo=${ticket.ticketNo}&&schNo=${ticket.schNo}">예매 취소</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<a href="movieList.do">영화 예매하러 가기</a>
	</c:otherwise>
</c:choose>

<%@ include file="footer.jsp" %>