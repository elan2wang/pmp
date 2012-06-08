<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加新模块</title>
</head>
<body>
<div class="window_content">
<form action="addModule" method="post">
<div class="rowStyle">
模块名称:<input type="text" class="textbox" name="module.modName"/></div>
<div class="rowStyle">
模块链接:<input type="text" class="textbox" name="module.modUrl"></div>
<div class="rowStyle">
模块级别:<input type="text" class="textbox" name="module.modLevel"></div>
<div class="rowStyle">
模块排序:<input type="text" class="textbox" name="module.modOrder"></div>
<div class="rowStyle">
是否启用:<input type="radio" name="module.enabled" value="true" checked/>是
<input type="radio" name="module.enabled" value="false"/>否</div>
<div class="rowStyle">
管理模块:<input type="radio" name="module.issys" value="true" checked/>是
<input type="radio" name="module.issys" value="false"/>否
</div>
<div class="rowStyle">
       <div style=" margin-left:80px;margin-top:10px">
       <input type="submit" value="提交"/>
       <input type="button" value="取消" onclick="closeWindow('#newModule')" /> 
       </div>
</div>
</form>
</div>
</body>
</html>