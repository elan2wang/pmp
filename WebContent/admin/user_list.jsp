<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户列表</title>

<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-new.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/admin/user_list.js"></script>
</head>

<body>
   <div class="wrap">      
       <ul class="nav">
          <li id="tab1" class="active"><a href="javascript:void(0)">用户管理</a></li>
       </ul>   
       <div class="content">
           <div class="innercontent">
               <div class="content_main">
                   <table id="user_list">
                   </table>
                   <div id="newUser" class="easyui-window" href="user_add.jsp" title="添加新用户" iconCls="icon-save" style="width:350px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
                   <div id="editUser" class="easyui-window"  title="编辑用户" iconCls="icon-save" style="width:350px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
                 </div>
	       </div>
	  </div>
  </div>
  <script type="text/javascript" src="../Scripts/common/changeSize.js"></script>
</body>
</html>

