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
<form name="form1" id="form1" action="cf_edit" method="post">
<table cellpadding="0" cellspacing="0" border="0" style="bottom-border:1px,dash">
<tr>
  <td>序号</td><td>小区</td><td>房号</td><td>业主</td><td>时间</td><td>应收金额</td>
</tr>
<c:forEach var="item" items="${cfList }" varStatus="status">
<tr class="item">
  <td style="width:20px;"><input type="checkbox" name="ids" value="${item.cfId }" checked="checked">${status.count }</td>
  <td>${item.house.building.project.proName }</td>
  <td>${item.house.houseNum }</td>
  <td>${item.owner.ownerName }</td>
  <td style="width:80px;">${item.cfYear }-${item.cfMonth }</td>
  <td style="width:200px;"><input type="text" name="oughtMoney" value="${item.oughtMoney }"/>元</td>
</tr>
</c:forEach>
<tr>
  <td><input type="submit" value="提交" /></td><td><input type="button" value="取消" /></td>
</tr>
</table>
</form>
</div>   
</body>
</html>