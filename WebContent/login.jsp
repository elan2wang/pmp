<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%if (request.getParameter("error")!=null && request.getParameter("error").equals("true")){ %><span style="color:red">用户名或密码有误，请重新输入</span><%} %>
  <form name="login_form" method="post" action="j_spring_security_check">
    <b>UserName: </b><input type="text" name="j_username"/><br>
    <b>PassWord: </b><input type="password" name="j_password"/><br>
    <input type="submit" value="login"/>
  </form>
</body>
</html>