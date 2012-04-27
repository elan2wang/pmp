<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/admin/role_auth.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/pages/admin/role_list.js"></script>
<script type="text/javascript" src="../Scripts/pages/admin/role_add.js"></script>
<script type="text/javascript" src="../Scripts/pages/admin/role_auth.js"></script>
</head>
<body>
 <div class="wrap">      
       <ul class="nav">
          <li id="tab1" class="active"><a href="#">角色管理</a></li>
       </ul>   
       <div class="content">
           <div class="innercontent">
             <div class="grid_top">
             <a href="#" class="easyui-linkbutton" plain="true" onClick="openAddNewRole()">添加新角色</a>
              </div>
              <table id="rolelist"><%!int i=1; %>
                 <tbody id="role_data"><c:forEach var="role" items="${roleList}">
                 <tr><td><%=i++%></td><td>${role.roleName}</td><td>${role.roleDesc}</td><td>${role.enabled}</td><td>${role.issys}</td>
                    <td>
                       <a href="#" onclick="openEditRole($(this).next().html())">编辑</a>
                       <span style="display:none;width:10px">${role.roleId}</span>
                       <span style="display:inline-block;width:10px"></span>
                       <a href="#" onclick="openRoleAuth($(this).prev().prev().html())">分配权限</a>
                       <span style="display:inline-block;width:10px"></span>
                       <a href="#" onclick="openRoleMod($(this).prev().prev().prev().prev().html())">分配模块</a>
                       <span style="display:inline-block;width:10px"></span>
                       <!-- 当生成页面时，默认会给每个td内部加上一个<div> -->
                       <a href="#" onclick="deleteRow($(this).parent().parent().parent(),$(this).prev().prev().prev().prev().pre().pre().html())">删除</a>
                     </td>
                 </tr>
                 </c:forEach>
                 <% i=1; %>
                 </tbody>
              </table>
           </div>
       </div>
       <div id="newRole" class="easyui-window" href="role_add.jsp" title="添加新角色" iconCls="icon-save" style="width:350px;height:270px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
       </div>
        <div id="editRole" class="easyui-window"  title="编辑角色" iconCls="icon-save" style="width:350px;height:270px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
       </div>
       <div id="role_auth" class="easyui-window" title="给角色分配权限" iconCls="icon-save" style="width:500px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
       </div>
       <div id="role_mod" class="easyui-window" title="给角色分配模块" iconCls="icon-save" style="width:500px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
       </div>
    </div>
</body>
</html>