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
<form name="form1" id="form1" method="post" action="updateRes">
		<input type="hidden" name="resource.resId" id="resource.resId" value="${resource.resId}"/>
         <div class="rowStyle">
             <div><span >资源名称:</span><span><input type="text" name="resource.resName" value="${resource.resName}"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >资源类型:</span><span><input type="text" name="resource.resType" value="${resource.resType}"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >资源链接:</span><span><input type="text" name="resource.resLink" value="${resource.resLink}"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >资源描述:</span><span><input type="text" name="resource.resDesc" value="${resource.resDesc}"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >是否启用:</span>              
                 <span>
                 <input type="radio" name="resource.enabled" id="enabled1" value="true" <c:if test="${resource.enabled == true }">checked</c:if> /><label for="enabled1">是</label>
                 <input type="radio" name="resource.enabled" id="enabled2" value="false"<c:if test="${resource.enabled == false }">checked</c:if> /><label for="enabled2">否</label>
                 </span>
            </div>
         </div>
         <div class="rowStyle">
             <div><span >管理资源:</span>                 
                 <span>
                 <input type="radio" name="resource.issys" id="sys1" value="true" <c:if test="${resource.issys == true }">checked</c:if> /><label for="sys1">是</label>
                 <input type="radio" name="resource.issys" id="sys2" value="false" <c:if test="${resource.issys == false }">checked</c:if> /><label for="sys2">否</label>
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