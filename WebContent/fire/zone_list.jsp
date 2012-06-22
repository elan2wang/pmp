<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>场地信息列表</title>
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />

<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-new.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/fire/zone_list.js"></script>
</head>
<body>
<div class="wrap">      
       <ul class="nav">
          <li id="tab1" class="active"><a href="javascript:void(0)">场地信息管理</a></li>
       </ul> 
       <div class="content">
           <div class="innercontent">
               <div class="content_main" >
                   <table id="owner_list">
                   </table>
                   <!-- pop up windows -->
                   <div id="zoneEdit" href="zone_edit.jsp" class="easyui-window" title="场地信息修改" iconCls="icon-save" style="width:380px;height:310px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
                   <div id="zoneAdd" href="zone_add.jsp" class="easyui-window" title="添加场地" iconCls="icon-save" style="width:380px;height:310px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
               </div>
           </div>
       </div>
</div>
 <script type="text/javascript">
   var Width2=document.documentElement.clientWidth;
   var Height2=document.documentElement.clientHeight;
   ChangeHeight(Width2,Height2,'content');
   ChangeHeight(Width2,Height2,'innercontent');
  </script>
</body>
</html>