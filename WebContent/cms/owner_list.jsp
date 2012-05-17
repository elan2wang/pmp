<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业主信息列表</title>
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-new.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/owner_list.js"></script>
</head>
<body>
<div>
<select id="proSelect" name="proSelect">
<s:action name="load_owner_list_banner" namespace="/cms" executeResult="true"/>
</select>
</div>  
<table id="owner_list">
</table>
<!-- pop up windows -->
<div id="ownerEdit" class="easyui-window" title="业主信息修改" iconCls="icon-save" style="width:600px;height:410px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
<div id="ownerImport" href="owner_import.jsp" class="easyui-window" title="业主信息导入" iconCls="icon-save" style="width:350px;height:200px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
<div id="ownerAdd" href="owner_add.jsp" class="easyui-window" title="添加业主" iconCls="icon-save" style="width:600px;height:410px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>

</body>
</html>