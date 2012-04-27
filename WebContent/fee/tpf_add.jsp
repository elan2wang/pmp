<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../Scripts/jquery-1.7.1.min.js" ></script>
<title>Insert title here</title>
</head>
<body>
<form name="from1" action="addTimeParkFee" method="post">
车牌号:<input type="text" name="timeParkFee.carNum" /><br/>
停车日期:<input type="text" name="timeParkFee.parkDate"  readonly="readonly" style="cursor:pointer;" onFocus="WdatePicker()"/><br/>
停车时间:<input type="text" name="timeParkFee.parkTime" />不足1小时按1小时计<br/>
收费标准:<input type="text" name="timeParkFee.parkFeeRate" />如:4.0元/小时<br/>
实收金额:<input type="text" name="timeParkFee.fetchMoney" /><br/>
收费人员:<input type="text" name="timeParkFee.fetchPerson" /><br/>
<input type="submit" value="提交">
</form>
</body>
</html>