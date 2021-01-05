<%@page import="java.util.ArrayList"%>
<%@page import="Book.BookVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file = "header.jsp" %>

<%
	ArrayList<BookVO> list = (ArrayList<BookVO>) request.getAttribute("list");//request.getAttribute()로 session에 있는 list를 가져온다.
%>

<table border=1>
	<thead>
		<tr>
			<th>도서코드</th>
			<th>도서제목</th>
			<th>도서저자</th>
			<th>출판사</th>
			<th>가격</th>
			<th>출간날짜</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="book" items="${list}">
		 <%-- c:forEach var = '변수명' items='배열 또는 리스트' --%>
			<tr>
				<td><a href="updateBook.do?bcode=${book.bcode}">${book.bcode}</a></td><%-- ${출력할 변수} bcode를 출력 --%>
				<td>${book.btitle}</td><%-- btitle 출력 --%>
				<td>${book.bwriter}</td><%-- bwriter 출력 --%>
				<td>
					<c:set var="bpub" value="${book.bpub}"/><%-- jstl 안에 쓰일 변수 선언 --%>
					<c:choose><%--jstl choose 태그 = java switch --%>
						<c:when test="${bpub == 1001}"><%-- c:when test='${조건문 입력}' --%>
							양영디지털<%--참일 경우 --%>
							<%--bpub가 1001일경우  양영디지털 출력 --%>
						</c:when><%-- 태그 닫기 --%>
						<c:when test="${bpub == 1002}">
							북스미디어
							<%--bpub가 1002일경우  북스미디어 출력 --%>
						</c:when>
						<c:when test="${bpub == 1003 }">
							한빛아카데미
							<%--bpub가 1003일경우 한빛아카데미 출력 --%>
						</c:when>
						<c:when test="${bpub == 1004}">
							이지스
							<%--bpub가 1004일경우 이지스 출력 --%>
						</c:when>
					</c:choose>
				</td>
				<%--fmt:format 태그 : 주어진 값을 patten에 맞춰서 출력해주는 태그 --%>
				<td><fmt:formatNumber value="${book.bprice}" groupingUsed="true"/></td><%-- fmt:formatNumber:숫자를 format 해주는 태그  || groupingUsed : 통화 단위 기호 (세자릿수 콤마)를 사용할 여부에 대한 속성 true면 사용 false면 사용 안함 --%>
				<td><fmt:formatDate value="${book.bdate}" pattern="yyyy년 MM월 dd일"/></td><%-- fmt:formatDate : Date를 format 해주는 태그 || pattern : 출력해줄 형식을 입력하는 속성 --%>
				<td><a href="deleteBook.jsp?bcode=${book.bcode}">삭제</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file = "footer.jsp" %>