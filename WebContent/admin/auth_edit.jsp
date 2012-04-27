<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加权限</title>
</head>
<body>
<form name="form2" id="form2" method="post" action="updateAuth">
<input type="hidden" name="authority.authId" id="authority.authId" value="${authority.authId}"/>
       <div class="rowStyle">
           <div><span >权限名称:</span><span><input type="text" name="authority.authName" value="${authority.authName}"/></span></div>
       </div>
       <div class="rowStyle">
           <div><span >权限描述:</span><span><input type="text" name="authority.authDesc" value="${authority.authDesc}"/></span></div>
       </div>
       <div class="rowStyle">
           <div><span >是否启用:</span>              
               <span>
               <input type="radio" name="authority.enabled" id="enabled1" value="true" <c:if test="${authority.enabled == true }">checked</c:if> /><label for="enabled1">是</label>
               <input type="radio" name="authority.enabled" id="enabled2" value="false" <c:if test="${authority.enabled == false }">checked</c:if> /><label for="enabled2">否</label>
               </span>
          </div>
       </div>
       <div class="rowStyle">
           <div><span >管理权限:</span>                 
               <span>
               <input type="radio" name="authority.issys" id="sys1" value="true" <c:if test="${authority.issys == true }">checked</c:if> /><label for="sys1">是</label>
               <input type="radio" name="authority.issys" id="sys2" value="false" <c:if test="${authority.issys == true }">checked</c:if> /><label for="sys2">否</label>
               </span>
           </div>
       </div>
       <div class="rowStyle">
           <div style=" margin-left:100px;margin-top:20px"><input type="submit" value="提交" onclick="return  editFormCheck(); "/>
            <input type="button" value="关闭" onclick="editClose(); "/></div>
        </div>
</form>
</body>
</html>