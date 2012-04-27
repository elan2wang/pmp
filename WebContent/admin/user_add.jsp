<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加新用户</title>
</head>
<body onload="">
<script>loadInfo();</script>
<div class="window_content">
<form name="form1" id="form1" action="addUser" method="post">
         <div class="rowStyle">
             <div><span >真实姓名:</span><span><input type="text" name="user.realname" /></span></div>
         </div>
         <div class="rowStyle">
             <div><span >手机号码:</span><span><input type="text" name="user.mobile" /></span></div>
         </div>
         <div class="rowStyle">
             <div><span >身份证号:</span><span><input type="text" name="user.identify" /></span></div>
         </div>
         <div class="rowStyle">
             <div><span >职位:</span><span><input type="text" name="user.position" /></span></div>
         </div>
         <div class="rowStyle">
             <div><span >用户描述:</span><span><input type="text" name="user.userDesc" /></span></div>
         </div>
         <div class="rowStyle">
             <div><span >选择角色:</span><span><select id="roleId" name="roleId" ></select></span></div>
         </div>
         <div class="rowStyle">
             <div><span >选择用户组:</span><span><select id="groupId" name="groupId"></select></span></div>
         </div>
         <div class="rowStyle">
             <div><span >是否启用:</span>
                 <span>
                 <input type="radio" name="user.enabled" value="true" checked/><label for="user.enabled">是</label>
                 <input type="radio" name="user.enabled" value="false"/><label for="user.enabled">否</label>
                 </span>
             </div>
         </div>
          <div class="rowStyle">
              <div style=" margin-left:100px;margin-top:20px"><input type="submit" value="提交" onclick="return  addFormCheck(); "/>
                                 <input type="button" value="关闭" onclick="addClose(); "/></div>
           </div>
         </form>
         </div>
</body>
</html>