<%@page import="Book.BookVO"%>
<%@page import="Book.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "header.jsp" %>

<%
	BookVO vo = (BookVO) request.getAttribute("book");//session에서 insertBook.do와 updateBook.do에서 설정해준 BookVO 객체를 가져옴 request.getAttribute()의 반환 타입은 object 타입이기 때문에 앞에 (BookVO)로 형변환을 해줘야 한다.
%>

<form action="<%= request.getParameter("type").equals("insert") ? "AddBook.do" : "EditBook.do" %>" method="post"><!-- forward 시켜준 url의 주소 insertBook.jsp?type=~ 에서 type의 값이 insert라면 AddBook.do로, update라면 EditBook.do 로 설정 -->
	<table border=1 style="text-align: center;">
		<tr>
			<th>도서코드</th>
			<td><input type="text" name="bcode" value="<%= vo.getBcode() %>" readonly/></td><!-- 도서코드의 값은 readonly로 설정 (사용자가 함부로 바꾸지 않기 위해서) -->
		</tr>
		<tr>
			<th>도서제목</th>
			<td><input type="text" name="btitle" value="<%= vo.getBtitle().isEmpty() ? "" : vo.getBtitle() %>" required></td><!-- vo.getBtitle()의 값이 "" 일 경우 (도서 등록 링크로 들어올 때) ""출력, 아니라면 (도서 코드 링크로 들어올 때) btitle 값 출력-->
		</tr>
		<tr>
			<th>도서저자</th>
			<td><input type="text" name="bwriter" value="<%= vo.getBwriter().isEmpty() ? "" : vo.getBwriter() %>" required /></td><!-- vo.getBwriter()의 값이 "" 일 경우 (도서 등록 으로 들어올 때) ""출력, 아니라면 (도서 코드로 들어올 때 ) bwriter 값 출력-->
		</tr>
		<tr>
			<th>출판사코드</th>
			<td>
				<select name="bpub" required>
					<option value="1001" <%= vo.getBpub() == 1001 ? "selected" : "" %>>양영디지털</option><!-- vo.getBpub()가 1001과 같으면 selected 속성 추가 -->
					<option value="1002" <%= vo.getBpub() == 1002 ? "selected" : "" %>>북스미디어</option><!-- vo.getBpub()가 1002과 같으면 selected 속성 추가 -->
					<option value="1003" <%= vo.getBpub() == 1003 ? "selected" : "" %>>한빛아카데미</option><!-- vo.getBpub()가 1003과 같으면 selected 속성 추가 -->
					<option value="1004" <%= vo.getBpub() == 1004 ? "selected" : "" %>>이지스</option><!-- vo.getBpub()가 1004과 같으면 selected 속성 추가 -->
				</select>
			</td>
		</tr>
		<tr>
			<th>가격</th>
			<td><input type="number" name="bprice" value="<%= vo.getBprice() >= 0 ? vo.getBprice() : "" %>" required></td> <!-- vo.getBprice()가 0보다 크거나 같다면 (updateBook.do로 들어왔을 때) bprice 값 출력, 아니라면 (insertBook.do로 들어왔을 때) "" 출력 -->
		</tr>
		<tr>
			<th>출간날짜</th>
			<td><input type="text" name="bdate" value="<%= vo.getBdate() == null ? "" : vo.getBdate() %>"  placeholder="ex)2020-11-05 앞의 형식을 지켜주세요" required></td> <!-- vo.getBdate()가 null이라면 (insertBook.do로 들어왔을 때) ""출력, 아니라면 bdate 값 출력 -->
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="등록"/>
			<% if(request.getParameter("type").equals("insert")){ %><!-- request.getParameter("type")이 insert일 경우 (도서 등록 경우)  -->
				<input type="reset" value="재작성" /></td><!-- input type=reset 으로 재작성 만들기 -->
			<% } else { %>
				<button type="button" onclick="location.href='selectBook.do'">취소</button><!-- 취소 버튼 만들기 누르면 selectBook페이지로 감 -->
			<%} %>
		</tr>
	</table>
</form>

<%@ include file ="footer.jsp" %>