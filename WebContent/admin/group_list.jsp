<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户组管理</title>
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/admin/group_list.js"></script>
</head>
<body>
 <div class="wrap">      
       <ul class="nav">
          <li id="tab1" class="active"><a href="javascript:void(0)">用户组管理</a></li>
       </ul>   
       <div class="content">
           <div class="innercontent">
             <div class="grid_top">
             <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="openAddWindow('#newGroup')">添加新用户组</a>
              </div>
              <table id="grouplist">
                 <tbody id="group_data"><%int i=1;%>
                 <c:forEach var="group" items="${groupList}">
                 <tr><td><%=i++ %></td><td>${group.groupName }</td><td>${group.refDomain }</td><td>${group.groupLevel }</td><td>${group.groupDesc }</td>
                    <td>
                       <a href="javascript:void(0)" onclick="openEditWindow('#editGroup','getGroup?groupId='+$(this).next().html())">编辑</a>
                       <span style="display:none;width:10px">${group.groupId }</span><span style="display:inline-block;width:10px"></span>
                       <a href="javascript:void(0)" onclick="deleteRow($(this).parent().parent().parent(),'deleteGroup?groupId='+$(this).prev().prev().html(),'您将删除该用户组，确认删除')">删除</a>
                     </td>
                 </tr>
                 </c:forEach>
                 </tbody>
              </table>
           </div>
       </div>
       <div id="newGroup" class="easyui-window" href="group_add.jsp" title="添加新用户组" iconCls="icon-save" style="width:350px;height:270px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
       </div>
        <div id="editGroup" class="easyui-window" href="group_edit.jsp" title="编辑用户组" iconCls="icon-save" style="width:350px;height:270px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
       </div>
    </div>
</body>
</html>