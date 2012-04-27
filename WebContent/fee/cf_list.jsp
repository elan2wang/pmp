<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物业费记录列表</title>
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/fee/cf_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/fee/cf_list.js"></script>
</head>
<body>
<div class="main_nav">项目清单</div>
<div class="" style="height:50px;line-height:50px">
  <span style="display:inline-block;line-height:25px">项目：${condoFeeItem.itemName }</span>
  <span style="display:inline-block;line-height:25px">已缴：${payedCount }户</span>
  <span style="display:inline-block;line-height:25px">未缴：${nonePayedCount }户</span>
  
  <a href="loadCondoFeeList?cfiId=${condoFeeItem.cfiId }" class="linkbut" id="but_all" onclick="changeState(this);"><span>全部</span></a>
  <a href="loadCondoFeeList?cfiId=${condoFeeItem.cfiId }&type=none" class="linkbut" id="but_no" onclick="changeState(this);">未缴费</a>
  <a href="loadCondoFeeList?cfiId=${condoFeeItem.cfiId }&type=payed" class="linkbut" id="but_yes" onclick="changeState(this);">已缴费</a>
  <a href="#" class="linkbut" onclick="openInfoWindow('#viewAttrs','loadAttrs')">数据导出</a>
</div>
<table id="cf_list">
   <tbody id="cf_list_data">
   <c:forEach var="fee" items="${condoFeeList }">
   <tr>
     <td>1</td><td>${fee.house.houseNum }</td><td>${fee.owner.ownerName }</td><td>${fee.state }</td><td>${fee.oughtMoney }(元)</td><td>${fee.fetchMoney }(元)</td><td><fmt:formatDate value="${fee.fetchDate }" type="both" pattern="yyyy-MM-dd"/></td>
     <td>
     <c:if test="${fee.state == '新生成' }"><a href="#" onclick="openInfoWindow('#addFee','getCondoFee?cfId='+${fee.cfId })">录入</a></c:if>
     <c:if test="${fee.state == '已缴费' }"><a href="#" onclick="openInfoWindow('#viewFee','viewCondoFee?cfId='+${fee.cfId})">查看</a></c:if>
     <span style="display:inline-block;width:10px"></span>
     <c:if test="${fee.issend == true }"><a href="#" onclick="">重发催缴</a></c:if>
     <c:if test="${fee.issend == false }"><a href="#" onclick="">短信催缴</a></c:if>
     </td>
   </tr>
   </c:forEach>
   </tbody>
</table>
<div id="addFee" class="easyui-window" title="物业费录入" style="width:400px;height:420px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
<div id="viewFee" class="easyui-window" title="物业费记录信息" style="width:360px;height:200px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
<div id="viewAttrs" class="easyui-window" title="物业费导出选项" style="width:360px;height:250px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
</body>
</html>