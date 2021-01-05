<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="movie.MovieDAO"%>
<%@page import="movie.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 	UserVO user = (UserVO) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화</title>
</head>
<body>

<h1>영화</h1>

<header>
	<c:if test="${user.id ne 'admin' || empty user}">
		<nav>
			<a href="movieList.do?type=00">전체</a>
			<a href="movieList.do?type=01">액션</a>
			<a href="movieList.do?type=02">로맨스</a>
			<a href="movieList.do?type=03">코미디</a>
			<a href="movieList.do?type=04">스릴러</a>
			<a href="movieList.do?type=05">애니메이션</a>
		</nav>
	</c:if>
	
	<c:choose>
		<c:when test="${empty user}">
			<nav>
				<form action="login.do">
					<input type="text" name="id" placeholder="아이디" />
					<input type="password" name="password" placeholder="비밀번호" />
					<input type="submit" value="로그인" />
				</form>
				<a href="Registe.jsp">회원가입</a>
			</nav>
		</c:when>
		<c:when test="${user.id eq 'admin' }">
			<nav>
				<a href="Admin.do">홈으로</a>
				<a href="AddMovie.jsp">영화 추가하기</a>
				<a href="AllTicketList.do">전체 예매 목록 보여주기</a>
				<a href="Logout.do">로그아웃</a>
			</nav>
		</c:when>
		<c:otherwise>
			<nav>
				<a href="user.do">내 예매정보</a>
				<a href="Logout.do">로그아웃</a>
			</nav>
		</c:otherwise>
	</c:choose>
</header>