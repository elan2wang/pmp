<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/pages/fee/efc_list.js" ></script>
<title>电费充值记录列表</title>
</head>
<body>
<table class="tableid" cellpadding="0" cellspacing="0">
  <tr height="30">
    <th width="15%">充值金额</th><th width="20%">充值时间</th><th width="15%">录入人员</th><th width="30%">备注</th><th width="10%">操作</th>
  </tr>
  <c:forEach var="item" items="${efcList }">
  <tr>
    <td>${item.chargeMoney }</td><td><fmt:formatDate value="${item.chargeTime}" type="both" pattern="yyyy-MM-dd"/></td><td>${item.inputPerson }</td><td>${item.comment }</td><td><a href="#" onclick="efcDelete(${item.efcId})">删除</a></td>
  </tr>
  </c:forEach>
</table>
</body>
</html>