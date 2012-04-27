<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加新模块</title>
</head>
<body>
<form action="addModule" method="post">
模块名称:<input type="text" name="module.modName"/><br/>
模块链接:<input type="text" name="module.modUrl"><br/>
模块级别:<input type="text" name="module.modLevel"><br/>
是否启用:<input type="radio" name="module.enabled" value="true" checked/>是
<input type="radio" name="module.enabled" value="false"/>否<br/>
是否管理模块:<input type="radio" name="module.issys" value="true" checked/>是
<input type="radio" name="module.issys" value="false"/>否<br/>
<input type="submit" value="提交"/>
</form>
</body>
</html>