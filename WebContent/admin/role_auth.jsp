<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/common/remove.js"></script>
<title>给角色分配权限</title>
</head>
<body>
<div class="window_content">
<form name="form3" id="form3" action="edit_role_auth" method="post">
	<input type="hidden" name="roleId" id="roleId" value="${roleId}"/>
      <div class="left">
         <div class="select_tab">未分配权限</div>
         <div class="">
              <select id="auth_1" name="auth_1" multiple="multiple" style="width:200px">
              <c:forEach var="item" items="${noneGrantedAuthList}">
              		<option title="描述：${item.authDesc}；是否启用：${item.enabled}；是否系统管理员：${item.issys}" value="${item.authId}">${item.authName}</option>
				</c:forEach>
              </select>
         </div>
      </div>
      <div class="middle">
      		<div class="rowStyle"><input  type="button" onclick="Add('#auth_1','#authList');" value="添&nbsp;&nbsp;&nbsp;&nbsp;加" /></div>
			<div class="rowStyle"><input  type="button" onclick="Remove('#auth_1','#authList');"  value="移&nbsp;&nbsp;&nbsp;&nbsp;除" /></div>
			<div class="rowStyle"><input  type="button" onclick="AddAll('#auth_1','#authList')" value="全部添加"  /></div>
			<div class="rowStyle"><input  type="button" onclick="RemoveAll('#auth_1','#authList');" value="全部移除"/></div>
      </div>
      <div class="right">
         <div class="select_tab">已分配权限</div>
         <div class="">
              <select id="authList" name="authList" multiple="multiple" style="width:200px">
                <c:forEach var="item" items="${grantedAuthList}">
              		<option title="描述：${item.authDesc}；是否启用：${item.enabled}；是否系统管理员：${item.issys}" value="${item.authId}">${item.authName}</option>
				</c:forEach>
              </select>
         </div>
      </div>
      <div style="clear:both"></div>
     <div class="rowStyle">
           <div style=" margin-left:230px;margin-top:20px">
           <input type="submit" value="提交" onclick="return selectAll('#authList')"/>
           <input type="button" value="取消" onclick="closeWindow('#role_auth')"/>
           </div>
      </div>
</form>
</div>
</body>
</html>