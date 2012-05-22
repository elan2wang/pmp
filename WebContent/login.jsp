<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8 " />
<title>登录信息选择</title>
<style type="text/css">
body,td,th {
	font-size:12px;
}
body {
	background-image:url(Images/bgLogin.jpg);
	background-position:center;
	background-attachment:fixed;
	background-repeat:no-repeat;
}
#DivLoginSelect{
	border:1px #FFFFFF solid;
	position:absolute;
	z-index:100;
	bottom:50%;
	right:50%;
	margin-bottom:-125px;
	margin-right:-200px;
	width:400px;
	height:260px;
	background-color:#FFFFFF;
}
</style>
<script language="JavaScript">
if (window != top)
top.location.href = location.href; 
</script>
</head>

<body>
<div id="DivLoginSelect">
<table width="400" border="0" cellspacing="0" cellpadding="0" style="border:1px #666 solid;">
  <tr>
    <td height="237" align="center" valign="middle" style="background-image:url(Images/Logo1.gif); background-repeat:no-repeat; background-position:left top;">
   <form name="login_form" method="post" action="j_spring_security_check">
    <table width="250" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="right" valign="middle">&nbsp;</td>
        <td height="60" align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td width="70" align="right" valign="middle">用户名：</td>
        <td height="30" align="center" valign="middle"><input type="text" name="j_username" style="width:160px; height:18px; border:1px #666 solid;" /></td>
      </tr>
      <tr>
        <td align="right" valign="middle">密　码：</td>
        <td height="30" align="center" valign="middle"><input  type="password" name="j_password" style="width:160px; height:18px; border:1px #666 solid;"  /></td>
      </tr>
      <tr>
        <td align="center" valign="middle">&nbsp;</td>
        <td height="30" align="left" valign="middle" style="padding-left:10px;"><table width="150" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td  align="left" valign="middle"><input type="checkbox" id="CheckBox1" name="CheckBox1"/>记住用户名和密码</td>
           
          </tr>
        </table></td>
      </tr>
      <tr>
        <td align="center" valign="middle">&nbsp;</td>
        <td height="30" align="left" valign="middle" style="padding-left:10px;"><input type="submit" name="button" id="button" value="登 录" /></td>
      </tr>
    </table>
    </form></td>
  </tr>
  <tr>
    <td height="20" align="center" valign="middle" style="color:red; background-color:#FFF">
    <%if (request.getParameter("error")!=null && request.getParameter("error").equals("true")){ %><span style="color:red">用户名/密码有误、或该用户无任何权限</span><%} %>
    </td>
  </tr>
</table>
</div>
</body>
</html>
