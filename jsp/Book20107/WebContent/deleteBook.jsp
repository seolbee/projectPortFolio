<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int bcode = Integer.parseInt(request.getParameter("bcode"));
%>
<script>
	if(confirm("정말 삭제하겠습니까?") == true) location.href="deleteBook.do?bcode=<%= bcode %>";
	else history.back();
</script>