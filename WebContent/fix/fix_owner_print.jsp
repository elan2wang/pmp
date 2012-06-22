<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维修单打印</title>
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />

<script language=javascript>

    function doPrint() {

        bdhtml = window.document.body.innerHTML;

        sprnstr = "<!--startprint-->";

        eprnstr = "<!--endprint-->";

        prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);

        prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));

        window.document.body.innerHTML = prnhtml;

        window.print();

    }    

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
<!--startprint-->

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" align="center" valign="middle">&nbsp;</td>
  </tr>
  <tr>
    <td align="center" valign="middle"><h1>维修单</h1></td>
  </tr>
  <tr>
    <td height="35" align="center" valign="middle" style="font-size:16px;">服务中心：_________________　　　　　　　　　　　　　NO:__________</td>
  </tr>
  <tr>
    <td align="center" valign="middle"><table width="547" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
      <tr>
        <td height="30" colspan="6" align="center" valign="middle"><strong>业主基本信息</strong></td>
      </tr>
      <tr>
          <td height="30" align="center" valign="middle">小&nbsp;&nbsp;&nbsp;&nbsp;区：</td>
          <td align="center" valign="middle">${ownerRepair.houseOwner.house.building.project.proName }</td>
          <td align="center" valign="middle">楼&nbsp;&nbsp;&nbsp;&nbsp;宇：</td>
          <td align="center" valign="middle">${ownerRepair.houseOwner.house.building.builNum }号楼</td>
          <td align="center" valign="middle">房&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
          <td align="center" valign="middle">${ownerRepair.houseOwner.house.houseNum }</td>
        </tr>
      <tr>
        <td width="80" height="30" align="center" valign="middle">报&nbsp;修&nbsp;人：</td>
          <td width="100" align="center" valign="middle">${ownerRepair.applyPerson }</td>
          <td width="80" align="center" valign="middle">联系电话：</td>
          <td width="100" align="center" valign="middle">${ownerRepair.contactPhone }</td>
          <td width="80" align="center" valign="middle">报修时间：</td>
          <td width="100" align="center" valign="middle"><fmt:formatDate value="${ownerRepair.applyTime}" type="both" pattern="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <td height="30" colspan="6" align="center" valign="middle"><strong>维修项目信息</strong></td>
      </tr>
      <tr>
        <td width="80" height="30" align="center" valign="middle">维修单号：</td>
        <td align="center" valign="middle">${ownerRepair.opNum }</td>
        <td align="center" valign="middle">预约日期：</td>
        <td align="center" valign="middle"><fmt:formatDate value="${ownerRepair.orderDate}" type="both" pattern="yyyy-MM-dd"/></td>
        <td align="center" valign="middle">预约时间：</td>
        <td align="center" valign="middle">${ownerRepair.orderTime }</td>
      </tr>
      <tr>
        <td width="80" height="50" align="center" valign="middle">维修类型：</td>
        <td colspan="2" align="center" valign="middle">${ownerRepair.repairType }</td>
        <td align="center" valign="middle">详细情况：</td>
        <td colspan="2" align="center" valign="middle">${ownerRepair.repairDetail }</td>
      </tr>
      <tr>
        <td height="30" colspan="6" align="center" valign="middle"><strong>维修反馈信息</strong></td>
      </tr>
      <tr>
        <td height="30" align="center" valign="middle">维&nbsp;修&nbsp;员：</td>
        <td colspan="2" align="center" valign="middle">${ownerRepair.repairPerson }</td>
        <td align="center" valign="middle">是否派单：</td>
        <td colspan="2" align="center" valign="middle">${ownerRepair.state }</td>
      </tr>
      <tr>
        <td width="80" height="30" align="center" valign="middle">完工日期：</td>
        <td colspan="2" align="center" valign="middle"><fmt:formatDate value="${ownerRepair.finishDate}" type="both" pattern="yyyy-MM-dd"/></td>
        <td align="center" valign="middle">完工时间：</td>
        <td colspan="2" align="center" valign="middle">${ownerRepair.finishTime }</td>
      </tr>
      <tr>
        <td height="30" align="center" valign="middle">业主验收：</td>
        <td colspan="2" align="center" valign="middle"></td>
        <td rowspan="2" align="center" valign="middle">业主评价：</td>
        <td colspan="2" rowspan="2" align="center" valign="middle">${ownerRepair.evaluateDetail }</td>
      </tr>
      <tr>
        <td height="30" align="center" valign="middle">服务态度：</td>
        <td colspan="2" align="left" valign="middle" style="font-size:12px">
        <input type="checkbox">满意  <input type="checkbox">一般<input type="checkbox">不满意
        </td>
      </tr>
      <tr>
        <td height="30" colspan="6" align="center" valign="middle"><strong>材料/费用清单（业主未结单）</strong></td>
      </tr>
      <tr>
        <td height="150" colspan="6" align="center" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="120" height="25" align="center" valign="middle"><strong>材料名称</strong></td>
            <td width="60" align="center" valign="middle"><strong>数量</strong></td>
            <td width="100" align="center" valign="middle"><strong>金额</strong></td>
            <td align="center" valign="middle"><strong>备注</strong></td>
          </tr>
          <c:forEach var="item" items="${rfList }">
          <tr>
            <td width="120" height="25" align="center" valign="middle">${item.rfName }</td>
            <td width="60" align="center" valign="middle">${item.amount }</td>
            <td width="100" align="center" valign="middle">${item.money }</td>
            <td  align="center" valign="middle">${item.comment }</td>
          </tr>
          </c:forEach>
        </table></td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="25" align="center" valign="middle" style="font-size:12px">注：本维修单一式三联，业主、维修员、服务中心各留存一份。 签发日期：　       年　  月　  日</td>
  </tr>
</table>
<!--endprint-->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="35" align="center" valign="middle"><input type="button" name="button" id="button" value="打印" onClick="doPrint()"/></td>
  </tr>
</table>
</body>
</html>