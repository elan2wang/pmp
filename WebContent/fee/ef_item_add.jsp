<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电费创建</title>
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/fee/cf_list2.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/common/select.js" ></script>
<script type="text/javascript" src="../Scripts/pages/fee/ef_item_add.js" ></script>
</head>
<body>
<div class="wrap">
  <div class="content_main" style='font-family:"微软雅黑","黑体","宋体";overflow:auto'>
    <form name="form" id="form" action="ef_item_add" method="POST"><br/>
	<fieldset class="add_fieldset">
	  <legend class="add_legend">第一步：选择物业项目</legend>
	  <div class="selectdiv">
	     <select id="proId" name="proId" onchange="projectChanged()">
	     <s:action name="select_project" executeResult="true" namespace="/fee" />
	     </select>
	  </div>
	</fieldset><br/>
	<fieldset class="add_fieldset">
	  <legend class="add_legend">第二步：选择时间</legend>
	  <div class="timeSelect">
	   <span>上期时间：</span>
	   <span><input type="text" name="electricFeeItem.beginDate" readonly="readonly" style="cursor:pointer;" onfocus="WdatePicker()"/> </span> 
	   <span style="display:inline-block;padding-left:30px">本期时间：</span>
	   <span><input type="text" name="electricFeeItem.endDate" readonly="readonly" style="cursor:pointer;" onfocus="WdatePicker()"/></span>
	  </div>
	</fieldset><br/>
	<fieldset class="add_fieldset">
	  <legend class="add_legend">第三步：编辑总表信息</legend>
	  <div class="tableDiv">
	    <div style="width:600px;height:50px">
	    <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table1">
	      <tr>
	          <td width="20%" height="25" align="center" valign="middle">编号</td>
	          <td width="20%" height="25" align="center" valign="middle">上期度数</td>
	          <td width="20%" height="25" align="center" valign="middle">本期度数</td>
	          <td width="20%" height="25" align="center" valign="middle">单价</td>
	          <td width="20%" height="25" align="center" valign="middle"></td>
	      </tr>
	      <tr style="background-color:#FFC;">
	          <td height="25" align="center" valign="middle"><input name="tableNum" class="textbox1" id="tableNum" style="width:60px"/></td>
              <td height="25" align="center" valign="middle"><input name="lastDegree" type="text" class="textbox1" id="lastDegree" style="width:60px"/></td>
              <td height="25" align="center" valign="middle"><input name="nowDegree" type="text" class="textbox1" id="nowDegree" style="width:60px"/></td>
              <td height="25" align="center" valign="middle"><input name="degreeFee" type="text" class="textbox1" id="degreeFee" style="width:60px"/></td>
              <td align="center" valign="middle"><input type="button" name="button" id="button" value="新增" onclick="appendRow();"/></td>
          </tr>
	    </table>
	    </div>
	     <div id="electricHeight" style="width:600px; height:50px; overflow-y:auto;">
	         <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table1" id="electricList">
               <tr>
	              <td width="20%" height="25" align="center" valign="middle"></td>
	              <td width="20%" height="25" align="center" valign="middle">上次度数</td>
	              <td width="20%" height="25" align="center" valign="middle">本次度数</td>
	              <td width="20%" height="25" align="center" valign="middle">单价</td>
	              <td width="20%" height="25" align="center" valign="middle"></td>
	             </tr>
	              <tr>
	              <td width="20%" height="25" align="center" valign="middle" >总度数</td>
	              <td width="20%" height="25" align="center" valign="middle" name="totalDegree" id="totalDegree"></td>
	              <td width="20%" height="25" align="center" valign="middle" >总金额</td>
	              <td width="20%" height="25" align="center" valign="middle" name="totalMoney" id="totalMoney"></td>
	              <td width="20%" height="25" align="center" valign="middle"><input type="hidden" name="electricFeeItem.totalMoney" id="electricFeeItem.totalMoney"></td>
	             </tr>
                </table>
           </div>
	  </div>
	  
	</fieldset>
	<fieldset class="add_fieldset">
	  <legend class="add_legend">第四步：编辑电梯表信息</legend>
	   <div class="tableDiv">
	    <div style="width:600px;height:60px">
	    <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table1">
	      <tr>
	          <td width="20%" height="25" align="center" valign="middle">楼号</td>
	          <td width="20%" height="25" align="center" valign="middle">上期度数</td>
	          <td width="20%" height="25" align="center" valign="middle">本期度数</td>
	          <td width="20%" height="25" align="center" valign="middle">单价</td>
	          <td width="20%" height="25" align="center" valign="middle"></td>
	      </tr>
	      <tr style="background-color:#FFC;">
	          <td height="25" align="center" valign="middle">
	                  <select id="builNum" name="builNum">
	                  </select>
	          </td>
              <td height="25" align="center" valign="middle"><input name="elevatorlastDegree" type="text" class="textbox1" id="elevatorlastDegree" style="width:60px"/></td>
              <td height="25" align="center" valign="middle"><input name="elevatornowDegree" type="text" class="textbox1" id="elevatornowDegree" style="width:60px"/></td>
              <td height="25" align="center" valign="middle"><input name="elevatordegreeFee" type="text" class="textbox1" id="elevatordegreeFee" style="width:60px"/></td>
              <td align="center" valign="middle"><input type="button" name="button" id="button" value="新增" onclick="appendRowElev();"/></td>
          </tr>
	    </table>
	    </div>
	     <div id="elevatorHeight" style="width:600px; height:25px; overflow-y:auto;">
	         <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table1" id="elevatorList">
               <tr>
	              <td width="20%" height="25" align="center" valign="middle">楼号</td>
	              <td width="20%" height="25" align="center" valign="middle">上期度数</td>
	              <td width="20%" height="25" align="center" valign="middle">本期度数</td>
	              <td width="20%" height="25" align="center" valign="middle">单价</td>
	              <td width="20%" height="25" align="center" valign="middle"></td>
	             </tr>
                </table>
           </div>
	  </div>
	 
	</fieldset>
	<fieldset class="add_fieldset">
	  <legend class="add_legend">第五步：预览创建信息</legend>
	  <div class="buttongroup">
	    <input type="button" value="预览创建信息" onclick="preview()" /><br/>
	  </div>
	  <div class="buttongroup">
	  <textarea name="display" id="display" disabled="disabled" rows="2" cols="120" style="font-size:14px;"></textarea><br/>
	  </div>
	</fieldset>
	<div style="padding-left:20px;margin-top:10px">
	<input type="submit" id="submitbtn" value="确认创建"  onclick="return FormCheck();"/>
	<input type="reset" value="取消创建" onclick="cancel_item()" /><br/>
	</div>
    </form>
  </div>
</div>
  <script type="text/javascript">
   var Width2=document.documentElement.clientWidth;
   var Height2=document.documentElement.clientHeight;
   ChangeHeight(Width2,Height2+30,'content_main');
  </script>
</body>
</html>