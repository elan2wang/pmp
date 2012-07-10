<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
<form id="form1" name="form1" action="editPublicRepairItem" method="POST" >
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td style="padding-top:5px; padding-bottom:5px; border-bottom:1px #6c92ad solid;">
      <div id="P1">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
        <tr>
          <td width="80" height="30" align="center" valign="middle">项目名称
          <input type="hidden" name="publicRepairItem.fbiId" id="publicRepairItem.fbiId" value="${publicRepairItem.fbiId }"/>
          </td>
          <td width="180" colspan="2" align="left"><input name="publicRepairItem.itemName" type="text" class="textbox" id="publicRepairItem.itemName" value="${publicRepairItem.itemName }" style="width:160px;" /></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">所在小区</td>
          <td width="180" colspan="2" align="left">${publicRepairItem.project.proName }</td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">项目类型</td>
          <td width="180" colspan="2">
              <input name="publicRepairItem.itemType" type="radio" id="radio" value="维修" <c:if test="${publicRepairItem.itemType == '维修' }">checked="checked"</c:if> />维修
              <input name="publicRepairItem.itemType" type="radio" id="radio2" value="例行检查保养" <c:if test="${publicRepairItem.itemType == '例行检查保养' }">checked="checked"</c:if> />例行检查保养
          </td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">设施名称</td>
          <td width="180" colspan="2"><input name="publicRepairItem.equipName" type="text" class="textbox" id="publicRepairItem.equipName" style="width:160px;" value="${publicRepairItem.equipName }" /></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">设施编号</td>
          <td width="120" style="padding-left:10px"><input name="SN" type="text" class="textbox" id="SN" style="display:inline-block;width:100px;" /></td>
          <td width="60"  style="padding-left:0px"><input type="button" name="button" id="button" value="添加" onclick="selectAdd();" /></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">设备清单</td>
         <td width="180" colspan="2" rowspan="3">
         <select name="EqpList" size="1" multiple="multiple" class="selectbox" id="equipList" style="width:170px;height:80px;">
           <c:forEach var="item" items="${options }">
             <option value="${item }">${item }</option>
           </c:forEach>
         </select>
         <input type="hidden" name="publicRepairItem.equipList" id="publicRepairItem.equipList" value="${publicRepairItem.equipList }" />
         </td>
        </tr>
        <tr align="center" valign="middle">
          <td width="80" height="30"><a href="#" onclick="selectDel();">删除选中</a></td>
          </tr>
        <tr align="center" valign="middle">
          <td width="80" height="30" style="color:#666">按Ctrl多选</td>
          </tr>
        <tr>
          <td height="55" align="center" valign="middle">备　　注</td>
          <td colspan="2"><textarea name="publicRepairItem.comment" style="width:160px;height:40px;" class="textbox" id="publicRepairItem.comment" >${publicRepairItem.comment }</textarea></td>
          </tr>
      </table>
      </div>
      <div id="P2" style="display:;"></div>
      </td>
    </tr>
    <tr>
    <td style="padding-top:20px"><input type="submit" name="" value="完成" onclick="return formcheck()"/></td>
    </tr>
  </table>
</form>
</body>
</html>