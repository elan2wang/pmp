<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/pages/admin/user_add.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="window_content">
<form name="form2" id="form2" action="updateUser" method="post">
		<input type="hidden" name="user.userId" id="user.userId" value='${ugr.tbUser.userId }'/>
         <div class="rowStyle">
             <div><span >真实姓名:</span><span><input type="text" name="user.realname"  value='${ugr.tbUser.realname }'/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >手机号码:</span><span><input type="text" name="user.mobile" value='${ugr.tbUser.mobile }'/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >身份证号:</span><span><input type="text" name="user.identify" value='${ugr.tbUser.identify }'/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >职位:</span><span><input type="text" name="user.position" value='${ugr.tbUser.position }'/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >用户描述:</span><span><input type="text" name="user.userDesc" value='${ugr.tbUser.userDesc }'/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >选择角色:</span><span><select id="roleId" name="roleId" onchange="roleChange()">
             <c:forEach var="item" items="${roleList }">
               <option value="${item.roleId }" <c:if test="${ugr.tbRole.roleId == item.roleId }">selected="true"</c:if>>${item.roleName }</option>
             </c:forEach>
             </select></span></div>
         </div>
         <div class="rowStyle">
             <div><span >选择用户组:</span><span><select id="groupId" name="groupId">
             <c:forEach var="item" items="${groupList }">
               <option value="${item.groupId }" <c:if test="${ugr.tbGroup.groupId == item.groupId }">selected="true"</c:if>>${item.groupName }</option>
             </c:forEach>
             </select></span></div>
         </div>
         <div class="rowStyle">
             <div><span >是否启用:</span>
                 <span>
                 <input type="radio" name="user.enabled" value="true" <c:if test="${ugr.tbUser.enabled == true }">checked</c:if> /><label for="user.enabled">是</label>
                 <input type="radio" name="user.enabled" value="false" <c:if test="${ugr.tbUser.enabled == false }">checked</c:if> /><label for="user.enabled">否</label>
                 </span>
             </div>
         </div>
          <div class="rowStyle">
              <div style=" margin-left:100px;margin-top:20px">
              <input type="submit" value="保存" onclick=""/>
              <input type="button" value="关闭" onclick="editClose(); "/></div>
          </div>
         </form>
         </div>
</body>
</html>