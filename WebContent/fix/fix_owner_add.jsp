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
                  <option value="YZBuilding">其它（手工输入）</option>
                </select>
          </td>
          <td align="center" valign="middle">详细情况：</td>
          <td colspan="2" align="left" valign="middle"><textarea name="fdRPDetail" style="height:40px;width:170px;"  class="textbox1" id="fdRPDetail"></textarea></td>
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