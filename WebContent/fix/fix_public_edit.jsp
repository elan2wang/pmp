<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="../Scripts/pages/fix/fix_public_edit.js"></script>
<title>Insert title here</title>
<style type="text/css">
.pinfen {border:0px;	
}
#pinfen {	border:0px;	
}
tr{height:30px;line-hight:30px;padding:2px;}
</style>
</head>
<body>
<form id="form1" name="form1" action="editPublicRepair" method="post" onsubmit="return formcheck()">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td style="padding-top:5px; padding-bottom:5px; border-bottom:1px #6c92ad solid;">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
        <tr>
          <td width="80" height="30" align="center" valign="middle">设备编号
          <input type="hidden" name="publicRepair.fbId" id="publicRepair.fbId" value="${publicRepair.fbId }">
          <input type="hidden" name="publicRepair.equipNum" id="publicRepair.equipNum" value="${publicRepair.equipNum }">
          </td>
          <td width="180" colspan="2" align="left">${publicRepair.equipNum }</td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">日期</td>
          <td width="180" colspan="2"><input name="publicRepair.repairDate" type="text" class="textbox1"  id="publicRepair.repairDate" value="<fmt:formatDate value="${publicRepair.repairDate }" type="both" pattern="yyyy-MM-dd"/>" readonly="readonly" style="cursor:pointer;width:160px;" onfocus="WdatePicker()" />
          </td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">开始时间</td>
          <td width="180" colspan="2"><input name="publicRepair.beginTime" type="text" class="textbox" value="${publicRepair.beginTime }" id="publicRepair.beginTime" style="width:100px;" /><span>格式12:00</span></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">完成时间</td>
          <td width="180" colspan="2"><input name="publicRepair.endTime" type="text" class="textbox" value="${publicRepair.endTime }" id="publicRepair.endTime" style="width:100px;" /><span>格式12:00</span></td>
        </tr>
        <tr>
          <td height="55" align="center" valign="middle">维修详情</td>
          <td colspan="2"><textarea name="publicRepair.repairDetail" style="width:160px;height:40px;" class="textbox" id="publicRepair.repairDetail" >${publicRepair.repairDetail }</textarea></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">维修类型</td>
          <td width="180" colspan="2">
          <input name="publicRepair.repairType" type="radio" id="radio1" value="保修期内" <c:if test="${publicRepair.repairType == '保修期内' }"> checked="checked"</c:if> />保修期内
          <input name="publicRepair.repairType" type="radio" id="radio2" value="保修期外" <c:if test="${publicRepair.repairType == '保修期外' }"> checked="checked"</c:if> />保修期外
          </td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">目前状态</td>
          <td width="180" colspan="2">
            <select name="publicRepair.state" id="publicRepair.state" class="selectbox" style="width:165px;">
              <option value="正常" <c:if test="${publicRepair.state == '正常' }">selected="selected"</c:if> >正常</option>
              <option value="故障" <c:if test="${publicRepair.state == '故障' }">selected="selected"</c:if>>故障</option>
              <option value="老化" <c:if test="${publicRepair.state == '老化' }">selected="selected"</c:if>>老化</option>
              <option value="待修" <c:if test="${publicRepair.state == '待修' }">selected="selected"</c:if>>待修</option>
              <option value="待更换" <c:if test="${publicRepair.state == '待更换' }">selected="selected"</c:if>>待更换</option>
            </select>
          </td>
        </tr>
        <tr>
          <td height="55" align="center" valign="middle">责任人</td>
          <td width="180" colspan="2"><input name="publicRepair.dutyMan" type="text" class="textbox" id="publicRepair.dutyMan" value="${publicRepair.dutyMan }" style="width:160px;" /></td>
        </tr>
      </table>
      </td>
    </tr>
    <tr>
    <td style="padding-top:20px"><input type="submit" value="完成"/></td>
    </tr>
  </table>
</form>
</body>
</html>