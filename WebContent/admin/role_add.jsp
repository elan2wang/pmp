<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加角色</title>
</head>
<body>
<div class="window_content">
<form name="form1" id="form1" method="post" action="addRole">
         <div class="rowStyle">
             <div><span >角色名称:</span><span><input type="text" name="role.roleName"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >角色层次:</span><span><input type="text" name="role.roleLevel"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >角色描述:</span><span><input type="text" name="role.roleDesc"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >是否启用:</span>              
                 <span>
                 <input type="radio" name="role.enabled" value="true" checked/><label for="role.enabled">是</label>
                 <input type="radio" name="role.enabled" value="false"/><label for="role.enabled">否</label>
                 </span>
            </div>
         </div>
         <div class="rowStyle">
             <div><span >管理角色:</span>                 
                 <span>
                 <input type="radio" name="role.issys" value="true" checked/><label for="role.issys">是</label>
                 <input type="radio" name="role.issys" value="false"/><label for="role.issys">否</label>
                 </span>
             </div>
         </div>
          <div class="rowStyle">
              <div style=" margin-left:100px;margin-top:20px">
              <input type="submit" value="提交" onclick="return  addFormCheck(); "/>
              <input type="button" value="取消" onclick="close('#newRole')"/></div>
           </div>
</form>
</div>
</body>
</html>