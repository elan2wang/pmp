<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户组</title>
</head>
<body>
<form name="form2" id="form2" method="post" action="editGroup">
         <div class="rowStyle" style="display:none">
             <div><span >用户组编号:</span><span><input type="text" name="group.groupId" value="${group.groupId }"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >用户组名称:</span><span><input type="text" name="group.groupName" value="${group.groupName }"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >用户组描述:</span><span><input type="text" name="group.groupDesc" value="${group.groupDesc }"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >用户组级别:</span>
                  <span><select name="group.groupLevel" id="level" onchange="levelChanged()">
                          <option value="1" <c:if test="${group.groupLevel == 1 }">selected="selected"</c:if> >1：系统级用户组</option>
                          <option value="2" <c:if test="${group.groupLevel == 2 }">selected="selected"</c:if>>2：公司级用户组</option>
                          <option value="3" <c:if test="${group.groupLevel == 3 }">selected="selected"</c:if>>3：小区级用户组</option>
                       </select>
                  </span>
             </div>
         </div>
         <div class="rowStyle" id="span_fatherGroupId" <c:if test="${group.groupLevel == 1 or group.groupLevel == 2 }">style="display:none"</c:if>>
             <div><span >父亲用户组:</span>
                  <span><select name="group.fatherGroupId" id="fatherGroupId" disabled="disabled" onchange="fateherGroupChanged()">
                        </select>
                  </span>
             </div>
         </div>
         <div class="rowStyle" id="span_refDomain" <c:if test="${group.groupLevel == 1 }">style="display:none"</c:if>>
             <div><span >关联域:</span>      
                 <span><select name="group.refDomain" id="refDomain" disabled="disabled">
                       </select>
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