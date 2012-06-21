<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<form id="form1" name="form1" onsubmit="return formchk()">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    
    <tr>
      <td style="padding-top:5px; padding-bottom:5px; border-bottom:1px #6c92ad solid;">
      <div id="P1">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
        <tr>
          <td width="80" height="30" align="center" valign="middle">项目名称</td>
          <td width="180" colspan="2" align="left"><input name="PRName" type="text" class="textbox" id="PRName" style="width:160px;" /></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">项目类型</td>
          <td width="180" colspan="2">
              <input name="radio" type="radio" id="radio" value="维修" checked="true" />维修
              <input type="radio" name="radio" id="radio2" value="例行检查保养" />例行检查保养
          </td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">设施名称</td>
          <td width="180" colspan="2"><input name="PRfacilityName" type="text" class="textbox" id="PRfacilityName" style="width:160px;" /></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">设施编号</td>
          <td width="120" style="padding-left:10px"><input name="SN" type="text" class="textbox" id="SN" style="display:inline-block;width:100px;" /></td>
          <td width="60"  style="padding-left:0px"><input type="button" name="button" id="button" value="添加" onclick="selectAdd();" /></td>
        </tr>
        <tr>
          <td width="80" height="30" align="center" valign="middle">设备清单</td>
         <td width="180" colspan="2" rowspan="3"><select name="EqpList" size="1" multiple="multiple" class="selectbox" id="EqpList" style="width:170px;height:80px;">
</select></td>
        </tr>
        <tr align="center" valign="middle">
          <td width="80" height="30"><a href="#" onclick="selectDel();">删除选中</a></td>
          </tr>
        <tr align="center" valign="middle">
          <td width="80" height="30" style="color:#666">按Ctrl多选</td>
          </tr>
        <tr>
          <td height="55" align="center" valign="middle">备　　注</td>
          <td colspan="2"><textarea name="PRnote" style="width:160px;height:40px;" class="textbox" id="PRnote" ></textarea></td>
          </tr>
      </table>
      </div>
      <div id="P2" style="display:;"></div>
      </td>
    </tr>
    <tr>
    <td style="padding-top:20px"><input type="button" name="" value="完成" onclick="formchk()"/></td>
    </tr>
  </table>
</form>
</body>
</html>