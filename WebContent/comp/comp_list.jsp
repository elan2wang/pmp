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
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-new.js"></script>
<script language="javascript" type="text/javascript" src="../Scripts/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/comp/comp_list.js"></script>

<title>投诉管理</title>
</head>
<body>
<div class="wrap">
  <ul class="nav">
     <li id="tab1" class="active"><a href="javascript:void(0)">投诉记录列表</a></li>
  </ul> 
  <div class="content">
      <div class="innercontent">
          <div class="content_main" >
          <table id="complaintList">
          </table>
	      <div id="newComplaint" class="easyui-window" href="comp_add.jsp" title="添加投诉记录" iconCls="icon-save" style="width:600px;height:200px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
	  	  </div>
	  	  <div id="editComplaint" class="easyui-window" href="" title="编辑投诉记录" iconCls="icon-save" style="width:600px;height:270px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
	  	  </div>
	  	  <div id="browseComplaint" class="easyui-window" href="" title="查看投诉记录" iconCls="icon-save" style="width:600px;height:250px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
	  	  </div>
	  	  </div>
	  </div>
  </div>
</div>
<script type="text/javascript" src="../Scripts/common/changeSize.js"></script>
</body>
</html>