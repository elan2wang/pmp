<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="window_content">
<form name="form1" id="form1" action="ef_edit" method="post">
<table class="tableid" id="tableedit" cellpadding="2px" cellspacing="2px" border="0" style="bottom-border:1px,dash">
<tr>
  <th style="width:40px;"><input type="checkbox" checked="checked" onclick="selectAllOrNone(this)"></th>
  <th style="width:30px;">序号</th><th style="width:50px;">房号</th>
  <th style="width:40px;">业主</th>
  <th style="width:90px;">小区均摊(元)</th><th style="width:90px;">电梯均摊(元)</th>
</tr>
<c:forEach var="item" items="${efList }" varStatus="status">
<tr class="item">
  <td><input type="checkbox" name="idStr" value="${item.efId }" checked="checked"></td>
  <td>${status.count }</td>
  <td>${item.houseOwner.house.houseNum }</td>
  <td>${item.houseOwner.owner.ownerName }</td>
  <td><input type="text" name="proMeterFee" size="6" value="${item.proMeterFee }"/></td>
  <td><input type="text" name="liftMeterFee" size="6" value="${item.liftMeterFee }"/></td>
</tr>
</c:forEach>
</table>
<div style="text-align:center;margin-top:20px">
<input type="submit" value="确认" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="取消" onclick="closeWindow('#efEdit')" />
</div>
</form>
</div>   
</body>
</html>