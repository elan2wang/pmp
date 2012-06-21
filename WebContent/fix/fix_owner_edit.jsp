<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form id="form1" name="form1" method="post"  action="" onsubmit="return formchk()">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
    <tr>
      <td style="padding-top:5px; padding-bottom:5px; border-bottom:1px #6c92ad solid;">
      <div id="P1">
      <table width="547" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>业主基本信息（新建必填）
              
          </strong></td>
          </tr>
        <tr>
          <td height="30" align="center" valign="middle">小区：</td>
          <td align="left" valign="middle">
          <select name="YZBuilding" class="selectbox"  style="width:95px;" id="YZBuilding" onchange="getBuilding();">
              <option value="null">请选择小区</option>
          </select>
          </td>
          <td align="center" valign="middle">楼宇：</td>
          <td align="left" valign="middle">
          <select name="YZunit" class="selectbox" style="width:95px;" id="YZunit"  onchange="getUnit();">
            <option value="null2">请选择楼宇</option>
          </select></td>
          <td align="center" valign="middle">户：</td>
          <td align="left" valign="middle">
          <select name="YZroom" class="selectbox" style="width:95px;"  id="YZroom">
            <option value="null">请选择户号</option>
          </select>
          </td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">业主姓名：</td>
          <td width="100" align="left" valign="middle"><input name="fdName" type="text" class="textbox1" id="fdName"  /></td>
          <td width="80" align="center" valign="middle">联系电话：</td>
          <td width="100" align="left" valign="middle"><input name="fdPhone" type="text" class="textbox1" id="fdPhone"  /></td>
          <td width="80" align="center" valign="middle">报修时间：</td>
          <td width="100" align="left" valign="middle"><input name="fdReportDate" type="text" class="textbox1"  id="fdReportDate" readonly="readonly" style="cursor:pointer;" onfocus="WdatePicker()" /></td>
        </tr>
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>维修项目信息（新建必填）</strong></td>
          </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">维修单号：</td>
          <td align="left" valign="middle"><input name="fdID" type="text" class="textbox1"  id="fdID" onblur="ajaxRequest_SN();"/></td>
          <td align="center" valign="middle">预约日期：</td>
          <td align="left" valign="middle"><input name="fdApDate" type="text" class="textbox" id="fdApDate"  readonly="readonly" style="cursor:pointer;" onfocus="WdatePicker()" /></td>
          <td align="center" valign="middle">预约时间：</td>
          <td align="left" valign="middle"><select name="fdApTime" class="selectbox" style="width:95px;"  id="fdApTime">
            <option value="" selected="selected" >请选择时间</option>
            <option value="0:00" >0:00</option>
            <option value="0:30" >0:30</option>
            <option value="1:00" >1:00</option>
            <option value="1:30" >1:30</option>
            <option value="2:00" >2:00</option>
            <option value="2:30" >2:30</option>
            <option value="3:00" >3:00</option>
            <option value="3:30" >3:30</option>
            <option value="4:00" >4:00</option>
            <option value="4:30" >4:30</option>
            <option value="5:00" >5:00</option>
            <option value="5:30" >5:30</option>
            <option value="6:00" >6:00</option>
            <option value="6:30" >6:30</option>
            <option value="7:00" >7:00</option>
            <option value="7:30" >7:30</option>
            <option value="8:00" >8:00</option>
            <option value="8:30" >8:30</option>
            <option value="9:00" >9:00</option>
            <option value="9:30" >9:30</option>
            <option value="10:00" >10:00</option>
            <option value="10:30" >10:30</option>
            <option value="11:00" >11:00</option>
            <option value="11:30" >11:30</option>
            <option value="12:00" >12:00</option>
            <option value="12:30" >12:30</option>
            <option value="13:00" >13:00</option>
            <option value="13:30" >13:30</option>
            <option value="14:00" >14:00</option>
            <option value="14:30" >14:30</option>
            <option value="15:00" >15:00</option>
            <option value="15:30" >15:30</option>
            <option value="16:00" >16:00</option>
            <option value="16:00" >16:00</option>
            <option value="17:00" >17:00</option>
            <option value="17:30" >17:30</option>
            <option value="18:00">18:00</option>
            <option value="18:30" >18:30</option>
            <option value="19:00" >19:00</option>
            <option value="19:30" >19:30</option>
            <option value="20:00" >20:00</option>
            <option value="20:30">20:30</option>
            <option value="21:00" >21:00</option>
            <option value="21:30">21:30</option>
            <option value="22:00">22:00</option>
            <option value="22:30">22:30</option>
            <option value="23:00">23:00</option>
            <option value="23:30" >23:30</option>
          </select></td>
        </tr>
        <tr>
          <td width="80" height="50" align="center" valign="middle">维修类型：</td>
          <td colspan="2" align="left" valign="middle">
              <select name="ReprContent" class="selectbox" id="ReprContent"  style="width:170px" >
                <option value="no" selected="selected">请选择或输入</option>
               
                  <option disabled="disabled">------------</option>
                  <option value="OtherContent">其它（手工输入）</option>
                </select>
          </td>
          <td align="center" valign="middle">详细情况：</td>
          <td colspan="2" align="left" valign="middle"><textarea name="fdRPDetail" style="height:40px;width:170px;"  class="textbox1" id="fdRPDetail"></textarea></td>
          </tr>
        <tr>
          <td height="30" colspan="6" align="center" valign="middle"><strong>维修反馈信息</strong></td>
          </tr>
        <tr>
          <td height="30" align="center" valign="middle">维修员：</td>
          <td colspan="2" align="left" valign="middle"><input name="fdWorker" type="text" class="textbox1"  id="fdWorker"  style="width:170px" /></td>
          <td align="center" valign="middle">是否派单：</td>
          <td colspan="2" align="left" valign="middle"><select name="fdState" class="selectbox"  style="width:170px" id="fdState">
            <option value="未派单">未派单</option>
            <option value="已派单">已派单</option>
          </select></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">完工日期：</td>
          <td colspan="2" align="left" valign="middle"><input name="fdFinDate" type="text"   class="textbox1" id="fdFinDate" readonly="readonly"  onfocus="WdatePicker()" style="width:170px"/></td>
          <td align="center" valign="middle">完工时间：</td>
          <td colspan="2" align="left" valign="middle"><select name="fdFinTime"  class="selectbox" style="width:170px" id="fdFinTime" >
          <option value="" selected="selected">请选择时间</option>
         
            <option value="0:00" >0:00</option>
            <option value="0:30" >0:30</option>
            <option value="1:00" >1:00</option>
            <option value="1:30" >1:30</option>
            <option value="2:00" >2:00</option>
            <option value="2:30" >2:30</option>
            <option value="3:00" >3:00</option>
            <option value="3:30" >3:30</option>
            <option value="4:00" >4:00</option>
            <option value="4:30" >4:30</option>
            <option value="5:00" >5:00</option>
            <option value="5:30" >5:30</option>
            <option value="6:00" >6:00</option>
            <option value="6:30" >6:30</option>
            <option value="7:00" >7:00</option>
            <option value="7:30" >7:30</option>
            <option value="8:00" >8:00</option>
            <option value="8:30" >8:30</option>
            <option value="9:00" >9:00</option>
            <option value="9:30" >9:30</option>
            <option value="10:00" >10:00</option>
            <option value="10:30" >10:30</option>
            <option value="11:00" >11:00</option>
            <option value="11:30" >11:30</option>
            <option value="12:00" >12:00</option>
            <option value="12:30" >12:30</option>
            <option value="13:00" >13:00</option>
            <option value="13:30" >13:30</option>
            <option value="14:00" >14:00</option>
            <option value="14:30" >14:30</option>
            <option value="15:00" >15:00</option>
            <option value="15:30" >15:30</option>
            <option value="16:00" >16:00</option>
            <option value="16:00" >16:00</option>
            <option value="17:00" >17:00</option>
            <option value="17:30" >17:30</option>
            <option value="18:00">18:00</option>
            <option value="18:30" >18:30</option>
            <option value="19:00" >19:00</option>
            <option value="19:30" >19:30</option>
            <option value="20:00" >20:00</option>
            <option value="20:30">20:30</option>
            <option value="21:00" >21:00</option>
            <option value="21:30">21:30</option>
            <option value="22:00">22:00</option>
            <option value="22:30">22:30</option>
            <option value="23:00">23:00</option>
            <option value="23:30" >23:30</option>
          </select></td>
          </tr>
        <tr>
          <td height="30" align="center" valign="middle">业主验收：</td>
          <td colspan="2" align="left" valign="middle"><input type="checkbox" name="checkbox" id="checkbox"  /></td>
          <td rowspan="2" align="center" valign="middle">业主评价：</td>
          <td colspan="2" rowspan="2" align="left" valign="middle"><textarea name="fdYZComment" style="height:40px;width:170px;" class="textbox1"  id="fdYZComment"></textarea></td>
        </tr>
        <tr>
          <td height="30" align="center" valign="middle">服务态度：</td>
          <td colspan="2" align="left" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="pinfen" id="pinfen">
            <tr>
              <td><input type="radio" name="radio" id="radio" value="满意"   /></td>
              <td>满意</td>
              <td><input type="radio" name="radio" id="radio2" value="一般"  /></td>
              <td>一般</td>
              <td><input type="radio" name="radio" id="radio3" value="不满意"  /></td>
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
              <td style="width:120px;height:25px" align="center" valign="middle"><strong>材料名称</strong></td>
              <td style="width:60px" align="center" valign="middle"><strong>数量</strong></td>
              <td style="width:100px" align="center" valign="middle"><strong>金额</strong></td>
              <td style="width:190px" align="center" valign="middle"><strong>备注</strong></td>
              <td align="center" valign="middle"><strong>操作</strong></td>
            </tr>
            <tr style="background-color:#FFC;">
              <td height="25" align="center" valign="middle"><input name="mtName" type="text" class="textbox1" id="mtName" /></td>
              <td height="25" align="center" valign="middle"><input name="mtNum" type="text" class="textbox1" id="mtNum" style="width:40px"/></td>
              <td height="25" align="center" valign="middle"><input name="mtPrice" type="text" class="textbox1" id="mtPrice" style="width:40px"/></td>
              <td height="25" align="center" valign="middle"><input name="mtNote" type="text" class="textbox1" id="mtNote" style="width:120px"/></td>
              <td align="center" valign="middle"><input type="button" name="button" id="button" value="新增" onclick="appendRow();"/></td>
            </tr>
            <tr>
              <td height="25" style="border-width:0 0 1px 0" colspan="5" align="center" valign="middle">
              <div id="feeList" style="width:100%; height:50px; overflow-y:auto;">
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  id="tbProjList">
                <tr>
                  <td style="width:120px;height:25px" align="center" valign="middle"><input type="hidden" name="" value="工时费">工时费</td>
                  <td style="width:60px"  height="25" align="center" valign="middle">-</td>
                  <td style="width:100px" height="25" align="center" valign="middle"><input name="workerPrice" type="text" class="textbox1" id="workerPrice"  style="width:40px"/></td>
                  <td style="width:190px" height="25" align="center" valign="middle"><div style="width:180px"><input type="hidden" name="" value="含所有非材料费用">含所有非材料费用</div></td>
                  <td align="center" height="25" valign="middle"><input type="hidden" name="inputArr" id="inputArr"  />.</td>
                </tr>
                </table>
              </div>
              </td>
              </tr>
            <tr>
             <td height="30" colspan="5" align="center" valign="middle"><strong>历史操作记录</strong></td>
           </tr>
           <tr>
              <td height="30" colspan="1" align="center" valign="middle"><strong>操作时间</strong></td>
              <td height="30" colspan="2" align="center" valign="middle"><strong>操作人员</strong></td>
              <td height="30" colspan="2" align="center" valign="middle"><strong>操作详情</strong></td>
           </tr>
           <tr>
              <td height="30" colspan="1" align="center" valign="middle">1</td>
              <td height="30" colspan="2" align="center" valign="middle">1</td>
              <td height="30" colspan="2" align="center" valign="middle">1</td>
           </tr>
           <tr>
              <td height="30" colspan="1" align="center" valign="middle">本次操作详情</td>
              <td height="30" colspan="4" align="center" valign="middle"><textarea name="" style="height:40px;width:420px;"></textarea></td>
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
      <input type="submit" name="button2" id="button2" value="保 存"  /><span style="display:inline-block;width:20px"></span>
      <input type="button" name="button3" id="button3" value="取消"  />
      </td>
    </tr>
  </table>
</form>
</body>
</html>