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
<script type="text/javascript" src="../Scripts/pages/cms/house_list.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/project_list.js"></script>
</head>

<body>
  <div class="wrap">  
              <div id="top_info" name="top_info" style="display:none" >
                <div class="grid_top">
                <span>当前小区：</span><span><script>document.write(parent.document.getElementById("frame.projectName").value)</script></span>
                <span>当前楼号：</span><span><script>document.write(parent.document.getElementById("frame.builNum").value)</script></span>
                <span>所属物业：</span><span><script>document.write(parent.document.getElementById("frame.projectName").value)</script></span>
            </div>
            <div class="grid_top"> 
                  <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="openAddNewHouse()">添加新房产</a>
                   <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="houseImport()">导入</a>
               	  <div id="houseImport" href="house_import.jsp" class="easyui-window" title="房产导入" iconCls="icon-save" style="width:350px;height:200px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
                </div>
             </div>
             
              <div id="top_info2" name="top_info2" class="grid_top"  style="display:none;" >
                <!--  
                <span>筛选</span><s:action name="getAllProject" namespace="/cms" executeResult="true"/>
                                      <select id="buildingId" name="buildingId">
                                              <option selected="selected">选择楼宇</option>
                                      </select>
                 <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="searchHouse()">搜索</a>
                 <input type="hidden" id="searchState" name="searchState" value="0"/>
                  <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="selectAll()">全选</a>
                 <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="selectOpposite()">反选</a>
                 <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="selectNone()">全不选</a>
                  <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="deleteSelected(this)">批量删除</a>
            -->
             </div>
             
              <table id="houselist">
              </table>
             
	          <div id="newHouse" class="easyui-window" href="house_add.jsp?" title="添加新房产" iconCls="icon-save" style="width:400px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
          </div>
	          
	          <div id="editHouse" class="easyui-window"  title="编辑房屋" iconCls="icon-save" style="width:350px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
	          </div>
  </div>
</body>
</html>