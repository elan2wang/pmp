<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(request.getAttribute("message")!=null){ %><span style="color:red">${message }</span><%} %>
<form name="form1" action="import_res" method="post" enctype="multipart/form-data">
<input type="file" name="resFile" /><input type="submit" value="导入">
</form>
</body>
</html>