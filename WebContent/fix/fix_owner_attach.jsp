<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/component/AjaxFileUploader/ajaxfileupload.js"></script>
<script type="text/javascript" src="../Scripts/pages/fix/fix_owner_attach.js"></script>
<title>维修单附件</title>
<script language="javascript">
   
</script>
<style type="text/css">
.textbox{
	width:90px;
	height:14px;
}
.textbox1 {	width:90px;
	height:14px;
}
.pinfen{
	border:0px;	
}
.pinfen td{
	border:0px;	
}
</style>
</head>
<body>
<form action="" method="post"  name="form1" id="form1" >
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td style="padding-top:5px; padding-bottom:5px; border-bottom:1px #6c92ad solid;">
      <div id="P1">
      <table width="380" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
        <tr>
          <td height="30" colspan="3" align="center" valign="middle"><strong>附件上传</strong>（限doc,docx,pdf,xls,xlsx,jpg,gif格式，5M大小）</td>
          </tr>
        <tr>
          <td width="280" height="28" align="center" valign="middle">
          <input type="hidden" name="opId" id="opId" value="2" />
          <input type="file" id="raFile" name="raFile" />
          </td>
          <td align="center" valign="middle"><input type="button" id="bt_upload" onclick="uploadAttach()" value="上传" /></td>
        </tr>        
        <tr>
          <td height="30" colspan="3" align="center" valign="middle"><strong>已上传附件</strong></td>
        </tr>
        <tr>
          <td height="125" colspan="3" align="center" valign="middle">
          <div style="width:100%; height:auto; overflow-y:auto;">
          <table id="uploadedList" width="100%" border="0" cellpadding="0" cellspacing="0" class="table1" style="border:0px;">
            <tr>
              <td width="23.3%" ><strong>上传时间</strong></td>
              <td width="63.3%" ><strong>文件名称</strong></td>
              <td width="13.3%" ></td>
            </tr>
            <c:forEach var="item" items="${raList }">
            <tr>
              <td ></td>
              <td><a href="${item.attachUrl }">${item.attachName }</a></td>
              <td><div style="display:none">${item.raId }</div>
                <a href="javascript:void(0)" onclick="javascript:removeRow(this)" style="color:red;text-decoration:none;">删除</a>
              </td>
            </tr>
            </c:forEach>
          </table>
          </div>
          </td>
          </tr>
      </table>
      </div>
      <div id="P2" style="display:;"></div>
      </td>
    </tr>
  </table>
</form>
</body>
</html>