<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../Scripts/component/dtree/dtree.css" type="text/css" rel="StyleSheet"/>
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script src="../Scripts/component/dtree/dtree.js"  type="text/javascript" ></script>
<script src="../Scripts/pages/fee/house_tree.js"  type="text/javascript" ></script>
<title>Insert title here</title>
</head>
<body onload="load_data()">
<h2>业主树</h2>

<div class="dtree">
	<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>
</div>
<div id="tree"></div>

</body>
</html>