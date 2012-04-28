<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/admin/mod_list.js"></script>

<title>模块管理</title>
</head>
<body>
<div class="wrap">
  <ul class="nav">
    <li id="tab1" class="active"><a href="#">模块管理</a></li>
  </ul>
  <div class="content">
    <div class="innercontent">
      <div class="grid_top"><a href="#" class="easyui-linkbutton" plain="true" onClick="openAddWindow(’#newModule‘)">添加新模块</a></div>
      <table id="module_list"><%int i=1;%>
      <tbody id="module_list_data">
        <c:forEach var="module" items="${moduleList }">
        <tr>
          <td><%=i++%></td><td>${module.modName }</td><td>${module.modLevel }</td><td>${module.modUrl }</td><td>${module.enabled }</td><td>${module.issys }</td>
          <td>
          <a href="#" onclick="openEditWindow('#editModule','getModule?modId='+$(this).next().html())">编辑</a>
          <span style="display:none;width:10px">${module.modId }</span>
          <span style="display:inline-block;width:10px"></span>
          <a href="#" onclick="deleteRow($(this).parent().parent().parent(),'deleteModule?modId='+$(this).prev().prev().html(),'您将删除该模块，确认删除？')">删除</a>        
          </td>
        </tr>
        </c:forEach>
      </tbody>
      </table>
    </div>
  </div>
  <div id="newModule" class="easyui-window" href="mod_add.jsp" title="添加新模块" iconCls="icon-save" style="width:350px;height:270px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
  <div id="editModule" class="easyui-window" title="编辑模块" iconCls="icon-save" style="width:350px;height:270px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
</div>
</body>
</html>