<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>

<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-new.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/project_list.js"></script>

</head>

<body >
   <div class="wrap">      
	   <ul class="nav">
          <li id="tab1" class="active"><a href="javascript:void(0)">物业项目管理</a></li>
          <li id="tab2"><a href="javascript:void(0)">楼宇信息管理</a></li>
          <li id="tab3"><a href="javascript:void(0)">房产信息管理</a></li>
       </ul>   
       <div class="content">
           <div class="innercontent">


             <div class="grid_top">
             	<div id="proImport" href="pro_import.jsp" class="easyui-window" title="项目导入" iconCls="icon-save" style="width:350px;height:200px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
              </div>
              <table id="projectlist">
              </table>
                 <div id="newPro" class="easyui-window" href="project_add.jsp?" title="添加新项目" iconCls="icon-save" style="width:400px;height:440px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
                 </div>
                 <div id="editPro" class="easyui-window"  title="编辑项目"  iconCls="icon-save" style="width:400px;height:440px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
	             </div>
	          </div>
           <input type="hidden" id="frame.pageType" name="frame.pageType" value="all" />
           <input type="hidden" id="frame.pageId" name="frame.pageId" value="" />
           <input type="hidden" id="frame.projectName" name="frame.projectName" value="" />
           <input type="hidden" id="frame.company" name="frame.company" value="" />
           <input type="hidden" id="frame.housepageType" name="frame.housepageType" value="all" />
           <input type="hidden" id="frame.housepageId" name="frame.housepageId" value="1" />
           <input type="hidden" id="frame.builNum" name="frame.builNum" value=""/>
            
           <div class="innercontent">
               <div class="content_main"> 
                  <iframe id="buildingFrame" name="buildingFrame" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
                  </iframe>
               </div>
           </div>
           <div class="innercontent">
               <div class="content_main"> 
                  <iframe id="houseFrame" name="houseFrame" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
                  </iframe>
               </div>
           </div>
         </div>
  </div>
  <script type="text/javascript">
   var Width2=document.documentElement.clientWidth;
   var Height2=document.documentElement.clientHeight;
   ChangeHeight(Width2,Height2,'content');
   ChangeHeight(Width2,Height2,'innercontent');
   ChangeHeight(Width2,Height2-4,'content_main');
  </script>
</body>
</html>