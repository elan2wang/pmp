<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物业费创建</title>
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/fee_tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/cf_list2.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/common/select.js" ></script>
<script type="text/javascript" src="../Scripts/pages/fee/cf_item_add.js" ></script>
</head>
<body>
<div class="wrap">
    <ul class="nav">
      <li id="tab1" class="active"><a href="cf_item_add.jsp">创建物业费</a></li>
      <li id="tab1"><a href="cf_house_list.jsp">缴费录入</a></li>
      <li id="tab2"><a href="cf_month_list.jsp">缴费历史</a></li>
      <li id="tab2"><a href="cf_item_list.jsp">财务管理</a></li>
    </ul>
    <div class="content">
    <div class="innercontent">
      <div class="content_main">
        <form name="form1" action="cf_item_add" method="POST">
		<br/><fieldset>
		  <legend style="font-size:14px">第一步：选择物业项目</legend>
		  <select id="ProId" name="ProId">
		    <s:action name="select_project" executeResult="true" namespace="/fee" />
		  </select>
		</fieldset>
		<br/><fieldset>
		  <legend style="font-size:14px">第二步：选择时间</legend>
		  <select id="itemYear" name="condoFeeItem.itemYear">
		    <option value="2012">2014年</option>
		    <option value="2011">2013年</option>
		    <option value="2012" selected="selected">2012年</option>
		    <option value="2012">2011年</option>
		    <option value="2011">2010年</option>
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
      </div>
    </div>
	</div>
</div>

</body>
</html>