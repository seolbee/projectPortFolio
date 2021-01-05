<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<form action="AddMovie.do" enctype="multipart/form-data" method="post">
	영화제목 : <input type="text" name="movieName" /> <br />
	<select name="category">
		<option value="01">액션</option>
		<option value="02">로맨스</option>
		<option value="03">코미디</option>
		<option value="04">스릴러</option>
		<option value="05">애니메이션</option>
	</select> <br />
	정보 : <textarea name="info" cols="30" rows="10"></textarea> <br />
	상영시간 : <input type="number" name="runtime" /> <br />
	이미지 파일 넣기 : <input type="file" name="img" accept="image/*"/> <br />
	<input type="submit" value="저장" />
</form>
<%@ include file="footer.jsp" %>