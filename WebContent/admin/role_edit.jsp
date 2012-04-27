<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑角色</title>
</head>
<body>
<div class="window_content">
<form name="form2" id="form2" method="post" action="updateRole">
		<input type="hidden" name="role.roleId" id="role.roleId" value='<s:property value="role.roleId"/>'/>
         <div class="rowStyle">
             <div><span >角色名称:</span><span><input type="text" name="role.roleName" value='<s:property value="role.roleName"/>'/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >角色描述:</span><span><input type="text" name="role.roleDesc" value='<s:property value="role.roleDesc"/>'/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >是否启用:</span>              
                 <span>
                 <input type="radio" name="role.enabled" value="true" <c:if test="${role.enabled == true }">checked</c:if> /><label for="role.enabled">是</label>
                 <input type="radio" name="role.enabled" value="false" <c:if test="${role.enabled == false }">checked</c:if> /><label for="role.enabled">否</label>
                 </span>
            </div>
         </div>
         <div class="rowStyle">
             <div><span >管理角色:</span>                 
                 <span>
                 <input type="radio" name="role.issys" value="true" <c:if test="${role.issys == true }">checked</c:if> /><label for="role.issys">是</label>
                 <input type="radio" name="role.issys" value="false" <c:if test="${role.issys == false }">checked</c:if> /><label for="role.issys">否</label>
                 </span>
             </div>
         </div>
          <div class="rowStyle">
              <div style=" margin-left:100px;margin-top:20px"><input type="submit" value="提交" onclick="return  editFormCheck(); "/>
               <input type="button" value="关闭" onclick="editClose(); "/></div>
           </div>
</form>
</div>
</body>
</html>