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
<link href="../CSS/pages/maintain/fix_public.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-new.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/fix/fix_public_list.js"></script>
<script type="text/javascript" src="../Scripts/pages/fix/fix_public_add.js"></script>
<title>物业费管理</title>
</head>
<body>
<div class="wrap">
  <div class="content_main" style="border:0px;">
    <div class="left_main" style="float:left">
       <table id="maintainlist"></table>
      <div id="newMaintain" class="easyui-window" href="mt_public_add.jsp" title="添加公共维修/保养项目" iconCls="icon-save" style="width:350px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
  	  </div>
  	  <div id="editMaintain" class="easyui-window" href="" title="编辑公共维修/保养项目" iconCls="icon-save" style="width:310px;height:350px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
  	  </div>
    </div>
    <div class="middle"></div>
    <div id="right_main" class="right_main" style="float:left;display:block">
      <iframe name="maintainProList" id="maintainProList" frameborder="0" scrolling="auto" width="100%" height="100%" style="margin:0px;padding:0px"></iframe>
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