<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/fee/cf_item_list.js"></script>
<title>物业费管理</title>
</head>
<body>
<div class="wrap">
  <ul class="nav">
    <li id="tab1" class="active"><a href="#">物业费项目</a></li>
    <li id="tab2"><a href="#">物业费审核</a></li>
  </ul>
  <div class="content">
    <div class="innercontent">
      <div class="grid_top">
      </div>
      <div class="content_main">
        <div class="left_main" style="float:left">
          <table id="cfi_list">
            <tbody id="cfi_list_data">
            <c:forEach var="item" items="${cfiList }" varStatus="status">
            <tr>
              <td>${status.count }</td><td>${item.itemName }</td><td><a href="#" target="condoFeeList" onclick="openList('condoFeeList','loadCondoFeeList_ByCFI?cfiId=${item.cfiId }')">清单</a>&nbsp;&nbsp;<a href="exportNewCondoFee?cfiId=${item.cfiId }">数据导出</a></td>
            </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        <div class="middle"></div>
        <div id="right_main" class="right_main" style="float:left;display:none">
          <iframe name="condoFeeList" id="condoFeeList" frameborder="0" scrolling="auto" width="600px" height="500" style="margin:0px;padding:0px"></iframe>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>