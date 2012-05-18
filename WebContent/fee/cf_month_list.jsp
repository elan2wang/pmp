<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/fee_tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/fee/cf_list2.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/fee/cf_month_list.js"></script>
<title>物业费管理</title>
</head>
<body>
<div class="wrap">
  <ul class="nav">
    <sec:authorize access="hasRole('ROLE_COMPANY_MANAGER')"><li id="tab1"><a href="cf_item_add.jsp">创建物业费</a></li></sec:authorize>
    <li id="tab1"><a href="cf_house_list.jsp">缴费录入</a></li>
    <li id="tab2" class="active"><a href="cf_month_list.jsp">缴费历史</a></li>
    <sec:authorize access="hasRole('ROLE_COMPANY_MANAGER')"><li id="tab2"><a href="cf_item_list.jsp">财务管理</a></li></sec:authorize>
  </ul>
  <div class="content">
    <div class="innercontent">
      <div class="content_main">
        <div class="left_main" style="float:left">
            <div class="left_main_content">
             <%@ include file="month_tree.jsp" %>
             </div>
        </div>
        <div class="middle"></div>
        <div id="right_main" class="right_main" style="float:left;display:none">
          <iframe name="condoFeeList" id="condoFeeList" frameborder="0" scrolling="auto" width="100%" height="100%" style="margin:0px;padding:0px"></iframe>
        </div>
      </div>
    </div>
  </div>
</div>
  <script type="text/javascript">
   var Width2=document.documentElement.clientWidth;
   var Height2=document.documentElement.clientHeight;
   ChangeHeight(Width2,Height2,'content');
   ChangeHeight(Width2,Height2,'innercontent');
   ChangeHeight(Width2,Height2,'left_main');
   ChangeHeight(Width2,Height2,'middle');
   ChangeHeight(Width2,Height2,'right_main');
  </script>
</body>
</html>