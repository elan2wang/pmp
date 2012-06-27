<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../Scripts/component/AjaxFileUploader/ajaxfileupload.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../Scripts/component/AjaxFileUploader/ajaxfileupload.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/owner_import.js"></script>
<title>Insert title here</title>
</head>
<body>

<div style="align:center">
<br/>
<h2>请选择上传的文件</h2>
<input type="file" id="ownerFile" size="25" name="ownerFile" /><input type="button" onclick="owner_import()" value="导入">
<br><img src='../Images/loading.gif' id='loading' style='display:none'><br>
<div id="msg" style="color:red;font-size:13px;text-aligan:center"></div>
<div id="download" style="font-size:12px">请先下载<a href="../file/ownerinfo.xls">《业主信息模板》</a></div>
</div>
</body>
</html>