<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>File Upload Result</title>
</head>
<body>
<%
    // ������ ������ ����Ǵ� path
    String directory = this.getClass().getResource("").getPath();
	directory = directory.substring(1, directory.indexOf(".metadata")) +"movieMVC/WebContent/upload/";
	int maxSize = 1024 * 1024 * 100;//100MB
	String encoding = "UTF-8";//�ѱ��̸� �����ϱ� ����
	
	
	
	MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
	
	String fileName = multipartRequest.getOriginalFileName("file");
	String fileRealName = multipartRequest.getFilesystemName("file");
%>
<h1>���丮 : <%= directory %></h1>
<h1> ���ϸ� : <%= fileName %> </h1>
<h1> ���� ���ϸ� : <%= fileRealName %></h1>

<img src="<%=getServletContext().getRealPath("upload") +"/" +fileName %>" alt="img" />

</body>
</html>