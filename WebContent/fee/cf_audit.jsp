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
<form name="form1" id="form1" action="cf_audit" method="post">
<table cellpadding="2px" cellspacing="2px" border="0">
<tr>
  <th style="width:40px;">序号</th>
  <th style="width:40px;">房号</th><th style="width:40px;">业主</th>
  <th style="width:60px;">时间</th><th style="width:80px;">应收金额(元)</th>
  <th style="width:80px;">实收金额(元)</th><th style="width:60px;">录入人员</th>
  <th style="width:60px;">录入时间</th><th style="width:60px;">审核结果</th>
</tr>
<c:forEach var="item" items="${cfList }" varStatus="status">
<tr class="item">
  <td><input type="checkbox" name="ids" value="${item.cfId }" checked="checked">${status.count }</td>
  <td>${item.house.houseNum }</td>
  <td>${item.owner.ownerName }</td>
  <td>${item.cfYear }-${item.cfMonth }</td>
  <td>${item.oughtMoney }</td>
  <td>${item.fetchMoney }</td>
  <td>${item.recordPerson }</td>
  <td><fmt:formatDate value="${item.inputTime }" type="both" pattern="yyyy-MM-dd"/></td>
  <td><select name="state"><option value="pass">通过</option><option value="denied" selected="selected">有误</option></select></td>
</tr>
</c:forEach>
</table>
<input type="submit" value="确认" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="取消" />
</form>
</div>   
</body>
</html>