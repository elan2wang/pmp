<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../Scripts/pages/fix/fix_owner_add.js"></script>
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
<form id="form1" name="form1" method="post"  action="fix_owner_add" onsubmit="return formchk()">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
    <tr>
      <td style="padding-top:5px; padding-bottom:5px; border-bottom:1px #6c92ad solid;">
      <div id="P1">
      <table width="547" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>业主基本信息（新建必填）</strong></td>
        </tr>
        <tr>
          <td height="30" align="center" valign="middle">小&nbsp;&nbsp;&nbsp;&nbsp;区：</td>
          <td align="left" valign="middle">
          <select name="project" class="selectbox"  style="width:95px;" id="projectId" onchange="getBuilding()">
              <option value="null1">请选择小区</option>
              <s:action name="select_project" executeResult="true" namespace="/fix" />
          </select>
          </td>
          <td align="center" valign="middle">楼&nbsp;&nbsp;&nbsp;&nbsp;宇：</td>
          <td align="left" valign="middle">
          <select name="builId" class="selectbox" style="width:95px;" id="builId" onchange="getHouse()">
            <option value="null2">请选择楼宇</option>
          </select></td>
          <td align="center" valign="middle">房&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
          <td align="left" valign="middle">
          <select name="houseId" class="selectbox" style="width:95px;" id="houseId" onchange="getOwner()">
            <option value="null">请选择房号</option>
          </select>
          </td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">报&nbsp;修&nbsp;人：</td>
          <td width="100" align="left" valign="middle"><input name="ownerRepair.applyPerson" type="text" class="textbox1" id="applyPerson"  /></td>
          <td width="80" align="center" valign="middle">联系电话：</td>
          <td width="100" align="left" valign="middle"><input name="ownerRepair.contactPhone" type="text" class="textbox1" id="contactPhone"  /></td>
          <td width="80" align="center" valign="middle">报修时间：</td>
          <td width="100" align="left" valign="middle"><input name="ownerRepair.applyTime" type="text" class="textbox1"  id="fdReportDate" readonly="readonly" style="cursor:pointer;" onfocus="WdatePicker()" /></td>
        </tr>
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>维修项目信息（新建必填）</strong></td>
          </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">维修单号：</td>
          <td align="left" valign="middle"><input name="ownerRepair.opNum" type="text" class="textbox1"  id="fdID" onblur="ajaxRequest_SN();"/></td>
          <td align="center" valign="middle">预约日期：</td>
          <td align="left" valign="middle"><input name="ownerRepair.orderDate" type="text" class="textbox" id="fdApDate"  readonly="readonly" style="cursor:pointer;" onfocus="WdatePicker()" /></td>
          <td align="center" valign="middle">预约时间：</td>
          <td align="left" valign="middle"><select name="ownerRepair.orderTime" class="selectbox" style="width:95px;"  id="fdApTime">
            <option value="" selected="selected" >请选择时间</option>
            <c:forEach var="clock" begin="0" end="23" step="1">
              <option value="${clock }:00">${clock }:00</option>
              <option value="${clock }:30">${clock }:30</option>
            </c:forEach>
          </select></td>
        </tr>
        <tr>
          <td width="80" height="50" align="center" valign="middle">维修类型：</td>
          <td colspan="2" align="left" valign="middle">
              <select name="ownerRepair.repairType" class="selectbox" id="ReprContent"  style="width:170px" >
                <option value="有偿维修服务" selected="selected">有偿维修服务</option>
                <option value="保修期内维修">保修期内维修</option>
                </select>
          </td>
          <td align="center" valign="middle">详细情况：</td>
          <td colspan="2" align="left" valign="middle"><textarea name="ownerRepair.repairDetail" style="height:40px;width:170px;font-size:1l.5px"  class="textbox1" id="fdRPDetail"></textarea></td>
          </tr>
      </table>
      </div>
      <div id="P2" style="display:;"></div>
      </td>
    </tr>
    <tr>
      <td  colspan="2" align="center" valign="middle" style="padding-top:5px; padding-bottom:5px; ">
      <input type="submit" name="button2" id="button2" value="保 存"  /><span style="display:inline-block;width:20px"></span>
      <input type="button" name="button3" id="button3" value="取消"  />
      </td>
    </tr>
  </table>
</form>
</body>
</html>