<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Scripts/pages/admin/user_edit.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="window_content">
<form name="form" id="form" action="editUser" method="post">
    <input type="hidden" name="user.userId" id="user.userId" value='${ugr.tbUser.userId }'/>
    <div class="rowStyle">
        <div><span >真实姓名:</span><span><input type="text" name="user.realname"  value='${ugr.tbUser.realname }' id="user.realname" readOnly/></span><span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
        <div><span >用&nbsp;户&nbsp;名:</span><span><input type="text" name="user.username"  value='${ugr.tbUser.username }' id="user.username" readOnly/></span><span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
        <div><span >手机号码:</span><span><input type="text" name="user.mobile" value='${ugr.tbUser.mobile }' id="user.mobile"/></span><span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
        <div><span >身份证号:</span><span><input type="text" name="user.identify" value='${ugr.tbUser.identify }' id="user.identify"/></span><span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
        <div><span >职&nbsp;&nbsp;&nbsp;&nbsp;位:</span><span><input type="text" name="user.position" value='${ugr.tbUser.position }' id="user.position"/></span></div>
    </div>
    <div class="rowStyle">
        <div><span >用户描述:</span><span><input type="text" name="user.userDesc" value='${ugr.tbUser.userDesc }' id="user.userDesc"/></span></div>
    </div>
    <div class="rowStyle">
        <input type="hidden" id="roleID" value="${ugr.tbRole.roleId }" />
        <div><span >选择角色:</span><span><select id="roleId" name="roleId" onchange="roleChange()" class="selectbox"></select></span><span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
        <input type="hidden" id="groupID" value="${ugr.tbGroup.groupId }" />
        <div><span >用&nbsp;户&nbsp;组:</span><span><select id="groupId" name="groupId" class="selectbox"></select></span><span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
        <div><span >是否启用:</span>
            <span>
            <input type="radio" name="user.enabled" id="user.enabled1" value="true" <c:if test="${ugr.tbUser.enabled == true }">checked</c:if> /><label for="user.enabled">是</label>
            <input type="radio" name="user.enabled" id="user.enabled2" value="false" <c:if test="${ugr.tbUser.enabled == false }">checked</c:if> /><label for="user.enabled">否</label>
            </span>
        </div>
    </div>
     <div class="rowStyle">
         <div style=" margin-left:100px;margin-top:20px">
         <input type="button" value="保存" onclick="return FormCheck();"/>
         <input type="button" value="关闭" onclick="closeWindow('#editUser');"/></div>
     </div>
    </form>
    </div>
</body>
</html>