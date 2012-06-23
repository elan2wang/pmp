<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../Scripts/pages/fix/fix_owner_edit.js"></script>
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
<form id="form1" name="form1" method="post"  action="fix_owner_edit" onsubmit="return formchk()">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td style="padding-top:5px; padding-bottom:5px; border-bottom:1px #6c92ad solid;">
      <div id="P1">
      <table width="547" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>业主基本信息</strong></td>
          </tr>
        <tr>
          <td height="30" align="center" valign="middle"><input type="hidden" name="ownerRepair.opId" value="${ownerRepair.opId }" />小&nbsp;&nbsp;&nbsp;&nbsp;区：</td>
          <td align="left" valign="middle">${ownerRepair.houseOwner.house.building.project.proName }</td>
          <td align="center" valign="middle">楼&nbsp;&nbsp;&nbsp;&nbsp;宇：</td>
          <td align="left" valign="middle">${ownerRepair.houseOwner.house.building.builNum }</td>
          <td align="center" valign="middle">房&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
          <td align="left" valign="middle">${ownerRepair.houseOwner.house.houseNum }</td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">报&nbsp;修&nbsp;人：</td>
          <td width="100" align="left" valign="middle"><input name="ownerRepair.applyPerson" type="hidden" class="textbox1" id="fdName" value="${ownerRepair.applyPerson }"/>${ownerRepair.applyPerson }</td>
          <td width="80" align="center" valign="middle">联系电话：</td>
          <td width="100" align="left" valign="middle"><input name="ownerRepair.contactPhone" id="ownerRepair.contactPhone" type="text" class="textbox1" id="fdPhone" value="${ownerRepair.contactPhone }"/></td>
          <td width="80" align="center" valign="middle">报修时间：</td>
          <td width="100" align="left" valign="middle"><input name="ownerRepair.applyTime" type="hidden" class="textbox1" id="fdReportDate" value="<fmt:formatDate value="${ownerRepair.applyTime}" type="both" pattern="yyyy-MM-dd"/>"/><fmt:formatDate value="${ownerRepair.applyTime}" type="both" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>维修项目信息</strong></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">维修单号：</td>
          <td align="left" valign="middle"><input name="ownerRepair.opNum" type="hidden" class="textbox1" id="fdID" value="${ownerRepair.opNum }"/>${ownerRepair.opNum }</td>
          <td align="center" valign="middle">预约日期：</td>
          <td align="left" valign="middle"><input name="ownerRepair.orderDate" id="ownerRepair.orderDate" type="text" class="textbox" id="fdApDate"  readonly="readonly" style="cursor:pointer;" onfocus="WdatePicker()" value="<fmt:formatDate value="${ownerRepair.orderDate}" type="both" pattern="yyyy-MM-dd"/>"/></td>
          <td align="center" valign="middle">预约时间：</td>
          <td align="left" valign="middle"><select name="ownerRepair.orderTime" id="ownerRepair.orderTime" class="selectbox" style="width:95px;" id="fdApTime">
            <option value="">请选择时间</option>
            <c:forEach var="clock" begin="0" end="23" step="1">
              <option value="${clock }:00" <c:if test="${ownerRepair.orderTime==':00' }">selected="selected"</c:if>>${clock }:00</option>
              <option value="${clock }:30" <c:if test="${ownerRepair.orderTime==':30' }">selected="selected"</c:if>>${clock }:30</option>
            </c:forEach>
          </select></td>
        </tr>
        <tr>
          <td width="80" height="50" align="center" valign="middle">维修类型：</td>
          <td colspan="2" align="left" valign="middle">
              <select name="ownerRepair.repairType" class="selectbox" id="ReprContent"  style="width:170px" >
                <option value="有偿维修服务" <c:if test="${ownerRepair.repairType == '有偿维修服务' }">selected="selected"</c:if>>有偿维修服务</option>
                <option value="保修期内服务" <c:if test="${ownerRepair.repairType == '保修期内服务' }">selected="selected"</c:if>>保修期内维修</option>
              </select>
          </td>
          <td align="center" valign="middle">详细情况：</td>
          <td colspan="2" align="left" valign="middle"><textarea name="ownerRepair.repairDetail" style="height:40px;width:170px;"  class="textbox1" id="fdRPDetail">${ownerRepair.repairDetail }</textarea></td>
          </tr>
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>维修反馈信息</strong></td>
          </tr>
        <tr>
          <td height="30" align="center" valign="middle">维&nbsp;修&nbsp;员：</td>
          <td colspan="2" align="left" valign="middle"><input name="ownerRepair.repairPerson" type="text" class="textbox1" id="fdWorker"  style="width:170px" value="${ownerRepair.repairPerson }"/></td>
          <td align="center" valign="middle">是否派单：</td>
          <td colspan="2" align="left" valign="middle"><select name=ownerRepair.state class="selectbox"  style="width:170px" id="fdState">
            <option value="未派单" <c:if test="${ownerRepair.state=='未派单' }">selected="selected"</c:if>>未派单</option>
            <option value="已派单" <c:if test="${ownerRepair.state=='已派单' }">selected="selected"</c:if>>已派单</option>
          </select></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">完工日期：</td>
          <td colspan="2" align="left" valign="middle"><input name="ownerRepair.finishDate" type="text"   class="textbox1" id="fdFinDate" readonly="readonly"  onfocus="WdatePicker()" style="width:170px" value="<fmt:formatDate value="${ownerRepair.finishDate}" type="both" pattern="yyyy-MM-dd"/>"/></td>
          <td align="center" valign="middle">完工时间：</td>
          <td colspan="2" align="left" valign="middle"><select name="ownerRepair.finishTime"  class="selectbox" style="width:170px" id="fdFinTime" >
            <option value="">请选择时间</option>
            <c:forEach var="clock" begin="0" end="23" step="1">
              <option value="${clock }:00" <c:if test="${ownerRepair.orderTime==':00' }">selected="selected"</c:if>>${clock }:00</option>
              <option value="${clock }:30" <c:if test="${ownerRepair.orderTime==':30' }">selected="selected"</c:if>>${clock }:30</option>
            </c:forEach>
          </select></td>
        </tr>
        <tr>
          <td height="30" align="center" valign="middle">业主验收：</td>
          <td colspan="2" align="left" valign="middle"><input type="checkbox" name="ownerRepair.accepted" id="ownerRepair.accepted" value="true" <c:if test="${ownerRepair.accepted==true }">checked="checked"</c:if> /></td>
          <td rowspan="2" align="center" valign="middle">业主评价：</td>
          <td colspan="2" rowspan="2" align="left" valign="middle"><textarea name="ownerRepair.evaluateDetail" style="height:40px;width:170px;font-size:11.5px" class="textbox1"  id="fdYZComment" >${ownerRepair.evaluateDetail }</textarea></td>
        </tr>
        <tr>
          <td height="30" align="center" valign="middle">服务态度：</td>
          <td colspan="2" align="left" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="pinfen" id="pinfen">
            <tr>
              <td><input type="radio" name="ownerRepair.evaluate" id="radio" value="满意" <c:if test="${ownerRepair.evaluate=='满意' }">checked="checked"</c:if> /></td>
              <td>满意</td>
              <td><input type="radio" name="ownerRepair.evaluate" id="radio2" value="一般" <c:if test="${ownerRepair.evaluate=='一般' }">checked="checked"</c:if> /></td>
              <td>一般</td>
              <td><input type="radio" name="ownerRepair.evaluate" id="radio3" value="不满意" <c:if test="${ownerRepair.evaluate=='不满意' }">checked="checked"</c:if> /></td>
              <td>不满意</td>
              </tr>
          </table></td>
          </tr>
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>材料/费用清单</strong></td>
        </tr>
        <tr>
          <td height="150" style="border-width:0 0 1px 0" colspan="6" align="center" valign="middle">
          <table width="100%" border="0" cellspacing="0"  cellpadding="0">
            <tr>
              <td style="width:120px;height:25px" align="center" valign="middle"><strong>名称</strong></td>
              <td style="width:60px" align="center" valign="middle"><strong>数量</strong></td>
              <td style="width:100px" align="center" valign="middle"><strong>金额</strong></td>
              <td style="width:190px" align="center" valign="middle"><strong>备注</strong></td>
              <td align="center" valign="middle"><strong>操作</strong></td>
            </tr>
            <tr style="background-color:#FFC;">
              <td height="25" align="center" valign="middle"><input name="mtName" type="text" class="textbox1" id="mtName" <c:if test="${showLaborFee == 'true' }">value="人工费"</c:if> /></td>
              <td height="25" align="center" valign="middle"><input name="mtNum" type="text" class="textbox1" id="mtNum" style="width:40px"/></td>
              <td height="25" align="center" valign="middle"><input name="mtPrice" type="text" class="textbox1" id="mtPrice" style="width:40px"/></td>
              <td height="25" align="center" valign="middle"><input name="mtNote" type="text" class="textbox1" id="mtNote" <c:if test="${showLaborFee == 'true' }">value="含所有非材料费用"</c:if> style="width:120px"/></td>
              <td align="center" valign="middle"><input type="button" name="button" id="button" value="新增" onclick="appendRow();"/></td>
            </tr>
            <tr>
              <td height="25" colspan="5" align="center" valign="middle">
              <div id="feeList" style="width:100%;height:auto; overflow-y:auto;">
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  id="repairFeeList">
                <c:forEach var="item" items="${rfList }">
                <tr>
                  <td style="width:120px; height:25px" align="center" valign="middle"><input type="hidden" name="rfId" value="${item.rfId }" />${item.rfName }</td>
                  <td style="width:60px"  height="25" align="center" valign="middle">${item.amount }</td>
                  <td style="width:100px" height="25" align="center" valign="middle">${item.money }</td>
                  <td style="width:190px" height="25" align="center" valign="middle"><div style="width:180px">${item.comment }</div></td>
                  <td align="center" height="25" valign="middle"><a href="javascript:void(0);" onclick="deleteFee(this)" style="color:red;text-decoration:none;">删除</a></td>
                </tr>
                </c:forEach>
                </table>
              </div>
              </td>
           </tr>
           <tr>
             <td height="30" colspan="5" align="center" valign="middle"><strong>历史操作记录</strong></td>
           </tr>
           <tr>
              <td height="30" colspan="1" align="center" valign="middle"><strong>操作时间</strong></td>
              <td height="30" colspan="1" align="center" valign="middle"><strong>操作人员</strong></td>
              <td height="30" colspan="3" align="center" valign="middle"><strong>操作详情</strong></td>
           </tr>
           <c:forEach var="item" items="${odList }">
           <tr>
              <td height="30" colspan="1" align="center" valign="middle"><fmt:formatDate value="${item.operateTime }" type="both" pattern="yyyy-MM-dd hh:mm"/></td>
              <td height="30" colspan="1" align="center" valign="middle">${item.operatePerson }</td>
              <td height="30" colspan="3" align="center" valign="middle">${item.operateDetail }</td>
           </tr>
           </c:forEach>
           <tr>
              <td height="30" colspan="1" align="center" valign="middle">本次操作详情</td>
              <td height="30" colspan="4" align="center" valign="middle"><textarea name="operateDetail.operateDetail" style="height:40px;width:410px;" class="textbox1"></textarea></td>
           </tr>
          </table></td>
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