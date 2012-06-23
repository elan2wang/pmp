<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />

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
  
	<table id="projectlist">
	</table>
	<div id="proImport" class="easyui-window" href="pro_import.jsp" title="项目导入" iconCls="icon-save" style="width:350px;height:200px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
	<div id="newPro" class="easyui-window" href="project_add.jsp?" title="添加新项目" iconCls="icon-save" style="width:300px;height:400px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
	<div id="editPro" class="easyui-window"  title="编辑项目"  iconCls="icon-save" style="width:300px;height:400px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
  
</div>
</body>
</html>