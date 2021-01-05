<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%
	boolean[] seatList = (boolean[]) request.getAttribute("seat");
%>

<form action="reserveMovie.do" method="post">
	<input type="hidden" name="schNo" value="${schNo}" />
	<input type="hidden" name="roomNo" value="${roomNo }" />
	<table border=1>
	<%
		for(int i = 0; i<2; i++){
	%>
		<tr>
		<%
			for(int j = 0; j<10; j++){
		%>
			<td width="50px" style="text-align:center">
				<input type="radio" value="<%= i * 10 + j + 1%>" name="seatNo" <%= seatList[i * 10 + j] ? "disabled" : "" %> /> <br />
				<%= i * 10 + j + 1%>
			</td>
		<%
			}
		%>		
		</tr>
	<%
		}
	%>
	</table>
	<input type="submit" value="예매하기" />
</form>
<%@ include file="footer.jsp" %>