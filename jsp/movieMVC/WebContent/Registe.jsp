<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="Register.do">
	<table border=1 style="text-align:center">
		<thead>
			<tr>
				<th colspan=2>회원가입</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="id" required/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password" required /></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email" required /></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="tel" required /></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="date" name="birth" required /></td>
			</tr>
			<tr>
				<td colspan=2><input type="submit" value="회원가입" /></td>
			</tr>
		</tbody>
	</table>
</form>
<%@ include file="footer.jsp"%>