<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/pages/fee/efc_add.js" ></script>
<title>Insert title here</title>
</head>
<body>
<form name="form1" id="form1" method="post">
  <div class="rowStyle" style="height:30px">
    <div><span >预存金额：</span><span><input type="text" name="electricFeeCharge.chargeMoney" id="electricFeeCharge.chargeMoney"/></span><span style="color:red">*</span></div>
  </div>
  <div class="rowStyle" style="height:30px">
    <div><span >备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span><span><input type="text" name="electricFeeCharge.comment" id="electricFeeCharge.comment"/></span></div>
  </div>
  <div class="rowStyle">
  <div style="margin-left:100px; margin-top:10px">
    <input type="button" value="提交" onclick="efcAdd()"/>
    <input type="button" value="关闭" onclick="closeWindow('#efcAdd')"/>
  </div>
  </div>
</form>
</body>
</html>