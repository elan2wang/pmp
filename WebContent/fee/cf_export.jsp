<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/pages/fee/cf_export.js"></script>
<title>Insert title here</title>
</head>
<body>
<form id="exportForm" action="" method="post">
<div>
  <input type="checkbox" name="attrs" value="cfId" checked="checked">编号
  <input type="checkbox" name="attrs" value="project" checked="checked">所在小区
  <input type="checkbox" name="attrs" value="houseNum" checked="checked">房号
  <input type="checkbox" name="attrs" value="houseArea" checked="checked">面积
  <input type="checkbox" name="attrs" value="cfYear" checked="checked">年份
  <input type="checkbox" name="attrs" value="cfMonth" checked="checked">月份
  <input type="checkbox" name="attrs" value="owner" checked="checked">业主
  <input type="checkbox" name="attrs" value="oughtMoney" checked="checked">应收物业费
  <br><input type="submit" value="导出" />
</div>
</form>
</body>
</html>