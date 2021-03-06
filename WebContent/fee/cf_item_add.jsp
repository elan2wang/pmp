<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<link href="../CSS/pages/fee/cf_list2.css" rel="stylesheet" type="text/css" />

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
  <div class="content_main" style='font-family:"微软雅黑","黑体","宋体";'>
    <form name="form" id="form" action="cf_item_add" method="POST"><br/>
	<fieldset class="add_fieldset">
	  <legend class="add_legend">第一步：选择物业项目</legend>
	  <div class="selectdiv">
	     <select id="ProId" name="ProId">
	     <s:action name="select_project" executeResult="true" namespace="/fee" />
	     </select>
	  </div>
	</fieldset><br/>
	<fieldset class="add_fieldset">
	  <legend class="add_legend">第二步：选择时间</legend>
	  <div class="selectdiv2">
	       <select id="itemYear" name="condoFeeItem.itemYear">
	      <option value="2014">2014年</option>
	      <option value="2013">2013年</option>
	      <option value="2012" selected="selected">2012年</option>
	      <option value="2011">2011年</option>
	      <option value="2010">2010年</option>
	   </select><br />
	  </div>
	  <div class="checkgroup">
	    <c:forEach var="month" begin="1" end="12" step="1">
	       <input type="checkbox" name="itemMonth" value="${month }" /><span style="display:inline-block;font-size:12px;width:80px">${month }月</span>
	       <c:if test="${month%6 == 0 }"><br /></c:if>
	    </c:forEach>
	  </div>
	  <div class="buttongroup">
	    <span><input type="button" value="全选" onclick="checkAll('itemMonth')" /></span>
	    <span><input type="button" value="反选" onclick="reverseCheck('itemMonth')" /></span>
	    <span><input type="button" value="全不选" onclick="checkNone('itemMonth')" /></span>
	  </div>
	</fieldset><br/>
	<fieldset class="add_fieldset">
	  <legend class="add_legend">第三步：预览信息</legend>
	  <div class="buttongroup">
	    <input type="button" value="预览创建信息" onclick="preview()" /><br/>
	  </div>
	  <div class="buttongroup">
	  <textarea name="display" id="display" disabled="disabled" rows="2" cols="120" style="font-size:14px;"></textarea><br/>
	  </div>
	</fieldset>
	<div style="padding-left:20px;margin-top:10px">
	<input type="button" id="submitbtn" value="确认创建"  onclick="return FormCheck();" disabled="disabled"/>
	<input type="button" value="取消创建" onclick="cancel_item()" /><br/>
	</div>
    </form>
  </div>
</div>
<script type="text/javascript" src="../Scripts/common/changeSize.js"></script>
</body>
</html>