<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看物业费记录</title>
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" style="border-bottom:solid 1px #333333">
  <tr>
    <td width="70px">业主姓名:</td><td width="100px" style="text-align:left">${condoFee.owner.ownerName }</td><td width="70px">房号:</td><td width="120px" style="text-align:left">${condoFee.house.houseNum }</td>
  </tr>
  <tr>
    <td>联系方式:</td><td style="text-align:left">${condoFee.owner.mobile }</td><td>状态:</td><td style="text-align:left">${condoFee.state }</td>
  </tr>
  <tr>
    <td>房屋面积:</td><td style="text-align:left">${condoFee.house.houseArea }</td><td>收费标准:</td><td style="text-align:left">${condoFee.condoFeeRate }(元/平方米·月)</td>
  </tr>
  <tr>
    <td>开始时间:</td><td style="text-align:left"><fmt:formatDate value="${condoFee.startDate}" type="both" pattern="yyyy-MM-dd"/></td>
    <td>结束日期:</td><td style="text-align:left"><fmt:formatDate value="${condoFee.endDate}" type="both" pattern="yyyy-MM-dd"/></td>
  </tr>
  <tr>
    <td>应收金额:</td><td style="text-align:left">${condoFee.oughtMoney }(元)</td><td>实收金额:</td><td style="text-align:left">${condoFee.fetchMoney }(元)</td>
  </tr>
  <tr>
    <td>收费人员:</td><td style="text-align:left">${condoFee.fetchPerson }</td>
    <td>收费时间:</td><td style="text-align:left"><fmt:formatDate value="${condoFee.fetchDate}" type="both" pattern="yyyy-MM-dd"/></td>
  </tr>
  <tr>
    <td>录入人员:</td><td style="text-align:left">${condoFee.recordPerson }</td>
    <td>录入时间:</td><td style="text-align:left"><fmt:formatDate value="${condoFee.inputTime }" type="both" pattern="yyyy-MM-dd"/></td>
  </tr>
</table>
</body>
</html>