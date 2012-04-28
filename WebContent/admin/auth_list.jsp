<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css"/>
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/admin/role_auth.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/admin/auth_list.js"></script>
</head>
<body onload="">
 <div class="wrap">      
       <ul class="nav">
          <li id="tab1" class="active"><a href="#">权限管理</a></li>
       </ul>   
       <div class="content">
           <div class="innercontent">
             <div class="grid_top">
             <a href="#" class="easyui-linkbutton" plain="true" onClick="openAddWindow('#newAuth')">添加新权限</a>
              </div>
              <table id="authlist"><%! int i =1; %>
                 <tbody id="auth_data"><c:forEach var="auth" items="${authorityList}">
                 <tr><td><%=i++%></td><td>${auth.authName}</td><td>${auth.authDesc}</td><td>${auth.enabled}</td><td>${auth.issys}</td>
                    <td>
                       <a href="#" onclick="openEditWindow('#editAuth','getAuthById?authId='+$(this).next().html())">编辑</a>
                       <span style="display:none;width:10px">${auth.authId}</span>
                       <span style="display:inline-block;width:10px"></span>
                       <a href="#" onclick="openEditWindow('#auth_res','getAuthRes?authID='+$(this).prev().prev().html())">分配资源</a>
                       <span style="display:inline-block;width:10px"></span>
                       <a href="#" onclick="deleteRow($(this).parent().parent().parent(),'deleteAuthById?authId='+$(this).prev().prev().prev().prev().html(),'您将删除该权限，确认删除?')">删除</a>
                     </td>
                 </tr>
                 </c:forEach>
                 <% i=1; %>
                 </tbody>
              </table>
           </div>
       </div>
       <div id="newAuth" class="easyui-window" href="auth_add.jsp" title="添加新权限" iconCls="icon-save" style="width:350px;height:270px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
       </div>
       <div id="editAuth" class="easyui-window" title="编辑权限" iconCls="icon-save" style="width:350px;height:270px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
       </div>
       <div id="auth_res" class="easyui-window" title="给权限分配资源" iconCls="icon-save" style="width:500px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
       </div>
    </div>
</body>
</html>