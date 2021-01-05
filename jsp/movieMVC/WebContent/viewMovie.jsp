<%@page import="java.util.ArrayList"%>
<%@page import="movie.MovieVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "header.jsp" %>

<img src="upload/${movie.img}" alt="img" width="600"/>
<p>${movie.name}</p>
<p>${movie.category}</p>
<p>${movie.runtime}분</p>
<p>${movie.info}</p>

<h3>영화 예매하기</h3>
<form action="checkSeat.do">
	<table border=1 style="text-align:center">
		<thead>
			<tr>
				<td>상영날짜</td>
				<td>상영관</td>
				<td>남은 좌석 수</td>
				<td>선택</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="schedule" items="${ScheduleList}">
				<tr>
					<td>${schedule.runDay }</td>
					<td>${schedule.roomNo }관</td>
					<td>${20 - schedule.seatCnt}개</td>
					<td>
						<input type="radio" value="${schedule.no}" name="schNo" required/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="submit" value="선택완료" />
</form>

<%@ include file="footer.jsp" %>