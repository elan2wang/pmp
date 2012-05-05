<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/pages/admin/group_info.js"></script>
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
         <div class="rowStyle">
             <div><span >父亲用户组:</span>
                  <span><select name="group.fatherGroupId" id="fatherGroupId" onchange="fatherGroupChanged()">
                        <c:if test="${group.groupLevel == 1 }"><option value=0>根用户组</option></c:if>
                        <c:if test="${group.groupLevel == 2 }"><option value=0>根用户组</option></c:if>
                        <c:if test="${group.groupLevel == 3 }">
                        <c:forEach var="item" items="${groupList }">
                          <option value="${item.groupId }" <c:if test="${group.fatherGroupId == item.groupId }">selected</c:if> >${item.groupName }</option>
                        </c:forEach>
                        </c:if>
                        </select>
                  </span>
             </div>
         </div>
         <div class="rowStyle">
             <div><span >关联域:</span>      
                 <span><select name="group.refDomain" id="refDomain">
                       <c:if test="${group.groupLevel == 1 }"><option value="空关联域">空关联域</option></c:if>
                       <c:if test="${group.groupLevel == 2 }">
                       <c:forEach var="item" items="${companyList }">
                         <option value="${item.comName }" <c:if test="${group.refDomain == item.comName }">selected</c:if> >${item.comName }</option>
                       </c:forEach>
                       </c:if>
                       <c:if test="${group.groupLevel == 3 }">
                       <c:forEach var="item" items="${projectList }">
                         <option value="${item.proName }" <c:if test="${group.refDomain == item.proName }">selected</c:if> >${item.proName }</option>
                       </c:forEach>
                       </c:if>
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