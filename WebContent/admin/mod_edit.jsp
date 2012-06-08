<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑模块信息</title>
</head>
<body>
<div class="window_content">
<form action="editModule" method="post">
<span style="display:none">模块编号:<input type="text" class="textbox" name="module.modId" value="${module.modId }"/></span>
<div class="rowStyle">
模块名称:<input type="text" class="textbox" name="module.modName" value="${module.modName }"/></div>
<div class="rowStyle">
模块链接:<input type="text" class="textbox" name="module.modUrl" value="${module.modUrl }"></div>
<div class="rowStyle">
模块级别:<input type="text" class="textbox" name="module.modLevel" value="${module.modLevel }"></div>
<div class="rowStyle">
模块排序:<input type="text" class="textbox" name="module.modOrder" value="${module.modOrder }"></div>
<div class="rowStyle">
是否启用:<input type="radio" name="module.enabled" value="true" <c:if test="${module.enabled == true }">checked</c:if>/>是
<input type="radio" name="module.enabled" value="false" <c:if test="${module.enabled == false }">checked</c:if>/>否</div>
<div class="rowStyle">
管理模块:<input type="radio" name="module.issys" value="true" <c:if test="${module.issys == true }">checked</c:if>/>是
<input type="radio" name="module.issys" value="false" <c:if test="${module.issys == false }">checked</c:if>/>否</div>
<div class="rowStyle">
       <div style=" margin-left:80px;margin-top:10px">
       <input type="submit" value="提交"/>
       <input type="button" value="取消" onclick="closeWindow('#editModule')" /> 
       </div>
</div>
</form>
</form>
</div>
</body>
</html>