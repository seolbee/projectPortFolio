<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="header.jsp" %>
<form action="/login.do">
	<input type="text" name="id" placeholder="아이디를 입력하세요" />
	<input type="password" name="password" placeholder="비밀번호를 입력하세요" />
	<input type="submit" value="로그인" />
</form>
아이디가 없다면? <a href="Register.jsp">회원가입하러 가기</a>
<%@ include file="footer.jsp"%>