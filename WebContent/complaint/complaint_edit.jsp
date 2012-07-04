<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../Scripts/pages/complaint/complaint_add.js"></script>
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
<body onload="intRO();">
<form id="form1" name="form1" method="post"  action="complaint_edit" onsubmit="return formchk()">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
    <tr>
      <td style="padding-top:5px; padding-bottom:5px; border-bottom:1px #6c92ad solid;">
      <div id="P1">
      <table width="547" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>投诉人基本信息（新建必填）</strong></td>
        </tr>
        <tr>
          <td height="30" align="center" valign="middle"><input type="hidden" name="complaint.compId" value="${complaint.compId}" />小&nbsp;&nbsp;&nbsp;&nbsp;区：</td>
          <td align="left" valign="middle">
          ${complaint.houseOwner.house.building.project.proName}
          </td>
          <td align="center" valign="middle">楼&nbsp;&nbsp;&nbsp;&nbsp;宇：</td>
          <td align="left" valign="middle">
          ${complaint.houseOwner.house.building.builNum}
          </td>
          <td align="center" valign="middle">房&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
          <td align="left" valign="middle">
          ${complaint.houseOwner.house.houseNum}
          </td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">投&nbsp;诉&nbsp;人：</td>
          <td width="100" align="left" valign="middle">${complaint.compPerson}</td>
          <td width="80" align="center" valign="middle">投诉人电话：</td>
          <td width="100" align="left" valign="middle"><input name="complaint.compTel" type="text" class="textbox1" id="compTel"  value="${complaint.compTel}"/></td>
          <td width="80" align="center" valign="middle">投诉时间：</td>
          <td width="100" align="left" valign="middle"><input name="complaint.compTime" type="hidden" class="textbox1" id="fdReportDate" value="<fmt:formatDate value="${complaint.compTime}" type="both" pattern="yyyy-MM-dd"/>"/><fmt:formatDate value="${complaint.compTime}" type="both" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
        	<td width="80" align="center" valign="middle">投诉原因：</td>
        	<td width="460" align="left" valign="middle" colspan="5">${complaint.compContent}</td>
        </tr>
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>处理信息</strong></td>
        </tr>
        <tr>
          <td width="80" height="50" align="center" valign="middle">经办人：</td>
          <td colspan="2" align="left" valign="middle"><input name="complaint.handlePerson" type="text" class="textbox1" id="complaint.handlePerson" />
          </td>
          <td align="center" valign="middle">经办时间：</td>
          <td colspan="2" align="left" valign="middle"><input name="complaint.handleTime" type="text" class="textbox1"  id="complaint.handleTime" readonly="readonly" style="cursor:pointer;" onfocus="WdatePicker()" /></td>
        </tr>
        <tr>
        	<td width="80" align="center" valign="middle">处理结果：</td>
        	<td width="460" align="left" valign="middle" colspan="5"><textarea name="complaint.handleResult" style="height:40px;width:230px;font-size:1l.5px"  class="textbox1" id="complaint.handleResult"></textarea></td>
        </tr>
      </table>
      </div>
      <div id="P2" style="display:;"></div>
      </td>
    </tr>
    <tr>
      <td  colspan="2" align="center" valign="middle" style="padding-top:5px; padding-bottom:5px; ">
      <input type="submit" name="button2" id="button2"  value="保 存"  /><span style="display:inline-block;width:20px"></span>
      <input type="button" name="button3" id="button3" value="取消"  />
      </td>
    </tr>
  </table>
</form>
</body>
</html>