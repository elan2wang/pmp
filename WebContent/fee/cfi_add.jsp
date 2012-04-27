<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="form1" action="addCondoFeeItem" method="post">
<label for="condoFeeItem.itemName">物业费项目名称：</label><input type="text" name="condoFeeItem.itemName" value="xxxx年xx月应收物业费项目"/><br/>
<label for="condoFeeItem.itemYear">征收年月：</label>
<select name="condoFeeItem.itemYear">
  <c:forEach var="year" begin="2010" end="2020" step="1" >
    <option value="${year }">${year }</option>
  </c:forEach>
</select><span>年</span>
<select name="condoFeeItem.itemMonth">
  <c:forEach var="month" begin="1" end="12" step="1">
    <option value="${month }">${month }</option>
  </c:forEach>
</select><span>月</span><br/>
<input type="submit" value="提交" />
</form>
</body>
</html>