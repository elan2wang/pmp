<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/pages/admin/role_mod.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="window_content">
<form name="form3" id="form3" action="editRoleModule" method="post">
	<input type="hidden" name="roleId" id="roleId" value="${roleId}"/>
      <div class="left">
         <div class="select_tab">未授权模块</div>
         <div class="">
              <select id="mod_1" name="mod_1" multiple="multiple">
                   <c:forEach var="item" items="${noneGrantedModuleList }">
              		<option title="模块级别：${item.modLevel}；管理模块：${item.issys}" value="${item.modId }">${item.modName}</option>
				   </c:forEach>
              </select>
         </div>
      </div>
      <div class="middle">
      		<div ><input  type="button" onclick="Add();" value="添&nbsp;&nbsp;&nbsp;&nbsp;加" /></div>
			<div ><input  type="button" onclick="Remove();"  value="移&nbsp;&nbsp;&nbsp;&nbsp;除" /></div>
			<div ><input  type="button" onclick="AddAll()" value="全部添加"  /></div>
			<div ><input  type="button" onclick="RemoveAll();" value="全部移除"/></div>
      </div>
      <div class="right">
         <div class="select_tab">已授权模块</div>
         <div class="">
              <select id="moduleList" name="moduleList" multiple="multiple">
                <c:forEach var="item" items="${grantedModuleList }">
              		<option title="模块级别：${item.modLevel}；管理模块：${item.issys}" value="${item.modId }">${item.modName}</option>
				   </c:forEach>
              </select>
         </div>
      </div>
      <div style="clear:both"></div>
      <div class="rowStyle">
           <div style=" margin-left:100px;margin-top:20px"><input type="submit" value="提交" onclick=""/>
          <input type="button" value="关闭" onclick="closeRoleMod(); "/></div>
      </div>
</form>
</div>
</body>
</html>