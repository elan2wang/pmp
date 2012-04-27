<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/common/select.js"></script>
<title>Insert title here</title>
</head>
<body>
<form name="form1" action="excel" method="post">
请选择要导出的选项：
<table border="0" cellpadding="0" cellspacing="0" style="border-bottom:solid 1px #333333;border-top:solid 1px #333333">
<tr>
<c:forEach var="item" items="${attrs }" varStatus="status">
<td width="120px" style="text-align:left"><input type="checkbox" name="showAttrs" value="${item.key }">${item.value }</td>
<c:if test="${status.count%3 == 0 }"></tr><tr></c:if>
</c:forEach>
</tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" style="border-bottom:solid 1px #333333;">
<tr>
  <td width="70px"><input type="radio" name="type" value="all" checked="checked" />全部</td>
  <td width="70px"><input type="radio" name="type" value="payed" />已缴费</td>
  <td width="70px"><input type="radio" name="type" value="none" />未缴费</td>
  <td width="150px"></td>
</tr>
</table>
<input type="button" onclick="checkAll('showAttrs')" value="全选">
<input type="button" onclick="checkNone('showAttrs')" value="全不选">
<input type="button" onclick="reverseCheck('showAttrs')" value="反选">
<input style="text-align:center" type="submit" value="导出" />
</form>
</body>
</html>