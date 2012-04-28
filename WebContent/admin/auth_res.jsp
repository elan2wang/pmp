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
<title>给权限分配资源</title>
</head>
<body>
<div class="window_content">
<form name="form3" id="form3" action="edit_auth_res" method="post">
	<input type="hidden" name="authID" id="authID" value="${authID}"/>
      <div class="left">
         <div class="select_tab">未分配权限</div>
         <div class="">
              <select id="res_1" name="res_1" multiple="multiple">
                   <c:forEach var="item" items="${noneGrantedResList}">
              		<option title="资源类型：${item.resType}；资源连接：${item.resLink}；资源描述：${item.resDesc}；是否启用：${item.enabled}；issys：${item.issys}；modId：${item.modId}" value="${item.resId}">${item.resName}</option>
				   </c:forEach>
              </select>
         </div>
      </div>
      <div class="middle">
      		<div ><input  type="button" onclick="Add('#res_1','#resourceList');" value="添&nbsp;&nbsp;&nbsp;&nbsp;加" /></div>
			<div ><input  type="button" onclick="Remove('#res_1','#resourceList');"  value="移&nbsp;&nbsp;&nbsp;&nbsp;除" /></div>
			<div ><input  type="button" onclick="AddAll('#res_1','#resourceList')" value="全部添加"  /></div>
			<div ><input  type="button" onclick="RemoveAll('#res_1','#resourceList');" value="全部移除"/></div>
      </div>
      <div class="right">
         <div class="select_tab">已分配权限</div>
         <div class="">
              <select id="resourceList" name="resourceList" multiple="multiple">
                <c:forEach var="item" items="${grantedResList}">
              		<option title="资源类型：${item.resType}；资源连接：${item.resLink}；资源描述：${item.resDesc}；是否启用：${item.enabled}；issys：${item.issys}；modId：${item.modId}" value="${item.resId}">${item.resName}</option>
				</c:forEach>
              </select>
         </div>
      </div>
      <div style="clear:both"></div>
      <div class="rowStyle">
           <div style=" margin-left:100px;margin-top:20px">
           <input type="submit" value="提交" onclick="return selectAll()"/>
           </div>
      </div>
</form>
</div>
</body>
</html>