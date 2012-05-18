<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/cms/building_list.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-new.js"></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/building_list.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/building_add.js"></script>
</head>

<body style="height:100%">
  <div class="wrap">  
             <div id="top_info" name="top_info"  style="display:none;" >
                <div class="grid_top">
                  <span>当前小区：</span><span ><script>document.write(parent.document.getElementById("frame.projectName").value)</script></span>
                  <span>所属物业：</span><span ><script>document.write(parent.document.getElementById("frame.projectName").value)</script></span>
                </div>
                <div class="grid_top"> 
                  <span><a href="javascript:void(0)" class="linkbutton"  onClick="openAddNewBuild()">添加新楼宇</a></span>
                  <span><a href="javascript:void(0)" class="linkbutton"  onClick="openAddNewUser()">导入</a></span>
                </div>
             </div>
             <div id="top_info2" name="top_info2" class="grid_top"  style="display:none;" >
                <span>筛选</span><s:action name="getAllProject" namespace="/cms" executeResult="true"/>
                 <a href="javascript:void(0)" class="easyui-linkbutton" plain="true">搜索</a>
             </div>

              <table id="buildinglist">
            
              </table>
          
              <input type="hidden" id="buildingId" name="buildingId" value="" /> 
               <div id="newBuild" class="easyui-window" href="building_add.jsp" title="添加新楼宇" iconCls="icon-save" style="width:350px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
	          </div>
              <div id="editBuild" class="easyui-window" title="编辑楼宇" iconCls="icon-save" style="width:350px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
                  <iframe id="mainFrame" name="mainFrame" frameborder="0" scrolling="auto" width="100%" height="100%" src="building_edit.jsp">
                  </iframe>
	          </div>
  </div>
</body>
</html>