<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Scripts/pages/admin/user_add.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加新用户</title>
</head>
<body>
<script>loadRoleList();</script>
<div class="window_content">
<form name="form" id="form" action="addUser" method="post">
         <div class="rowStyle">
             <div><span >真实姓名:</span><span><input type="text" name="user.realname" id="user.realname"/></span><span style="color:red">*</span></div>
         </div>
         <div class="rowStyle">
             <div><span >用&nbsp;户&nbsp;名:</span><span><input type="text" name="user.username" id="user.username"/></span><span style="color:red">*</span></div>
         </div>
         <div class="rowStyle">
             <div><span >手机号码:</span><span><input type="text" name="user.mobile" id="user.mobile"/></span><span style="color:red">*</span></div>
         </div>
         <div class="rowStyle">
             <div><span >身份证号:</span><span><input type="text" name="user.identify" id="user.identify"/></span><span style="color:red">*</span></div>
         </div>
         <div class="rowStyle">
             <div><span >职&nbsp;&nbsp;&nbsp;&nbsp;位:</span><span><input type="text" name="user.position" id="user.position"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >用户描述:</span><span><input type="text" name="user.userDesc" id="user.userDesc"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >选择角色:</span><span><select id="roleId" name="roleId" onchange="roleChange()" class="selectbox"></select></span><span style="color:red">*</span></div>
         </div>
         <div class="rowStyle">
             <div><span >用&nbsp;户&nbsp;组:</span><span><select id="groupId" name="groupId" class="selectbox"></select></span><span style="color:red">*</span></div>
         </div>
         <div class="rowStyle">
             <div><span >是否启用:</span>
                 <span>
                 <input type="radio" name="user.enabled" id="user.enabled1" value="true" checked/><label for="user.enabled">是</label>
                 <input type="radio" name="user.enabled" id="user.enabled2" value="false"/><label for="user.enabled">否</label>
                 </span>
             </div>
         </div>
          <div class="rowStyle">
              <div style="margin-left:100px; margin-top:20px">
              <input type="button" value="提交" onclick="return FormCheck();"/>
              <input type="button" value="关闭" onclick="closeWindow('#newUser')"/></div>
           </div>
         </form>
         </div>
</body>
</html>