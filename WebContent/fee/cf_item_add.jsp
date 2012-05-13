<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物业费创建</title>

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/pages/fee/cf_item_add.js" ></script>
<script type="text/javascript" src="../Scripts/common/select.js" ></script>
</head>
<body>

<form name="form1" action="cf_item_add" method="POST">
<fieldset>
  <legend style="font-size:14px">第一步：选择物业项目</legend>
  <select id="ProId" name="ProId">
    <option value="1">海景城市花园</option>
  </select>
</fieldset>
<fieldset>
  <legend style="font-size:14px">第二步：选择时间</legend>
  <select id="itemYear" name="condoFeeItem.itemYear">
    <option value="2012">2012年</option>
    <option value="2011">2011年</option>
  </select><br />
  <c:forEach var="month" begin="1" end="12" step="1">
    <input type="checkbox" name="itemMonth" value="${month }" /><span style="font-size:12px;">${month }月</span>
    <c:if test="${month%6 == 0 }"><br /></c:if>
  </c:forEach>
  <input type="button" value="全选" onclick="checkAll('itemMonth')" /><input type="button" value="反选" onclick="reverseCheck('itemMonth')" />
  <input type="button" value="全不选" onclick="checkNone('itemMonth')" />
</fieldset><br/>
<fieldset>
  <legend style="font-size:14px">第三步：预览信息</legend>
  <input type="button" value="预览创建信息" onclick="preview()" /><br/>
  <textarea name="display" id="display" disabled="disabled" rows="2" cols="120" style="font-size:14px;"></textarea><br/>
</fieldset>
<input type="submit" value="确认创建" disabled="disabled" />
<input type="button" value="取消创建" onclick="" /><br/>
</form>

</body>
</html>