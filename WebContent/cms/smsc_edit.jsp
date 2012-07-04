<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Scripts/pages/cms/smsc_add.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/smsc_edit.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="window_content">
<form name="form2" id="form2" action="editSMSCompany" method="post">
  <input type="hidden" name="smsCompany.smscId" id="smsCompany.smscId" value='${smsCompany.smscId}'/>
  <div class="rowStyle">
      <div><span >账号名称:</span>
      <span><input type="text" id="smsCompany.smscName" name="smsCompany.smscName" value="${smsCompany.smscName }"/></span><span style="color:red">*</span></div>
  </div>
  <div class="rowStyle">
      <div><span >上行地址:</span>
      <span><input type="text" id="smsCompany.smsUpUrl" name="smsCompany.smsUpUrl" value="${smsCompany.smsUpUrl }"/></span><span style="color:red">*</span></div>
  </div>
  <div class="rowStyle">
      <div><span >下行地址:</span>
      <span ><input type="text" id="smsCompany.smsDownUrl" name="smsCompany.smsDownUrl" value="${smsCompany.smsDownUrl }"/></span><span style="color:red">*</span></div>
  </div>
  <div class="rowStyle">
      <div><span >应用账号:</span>
      <span><input type="text" id="smsCompany.username" name="smsCompany.username" value="${smsCompany.username }"/></span><span style="color:red">*</span></div>
  </div>
  <div class="rowStyle">
      <div><span >应用密码:</span>
      <span><input type="text" id="smsCompany.password" name="smsCompany.password" value="${smsCompany.password }"/></span><span style="color:red">*</span></div>
  </div>
  <div class="rowStyle">
      <div><span >扩&nbsp;展&nbsp;码:</span>
      <span><input type="text" id="smsCompany.extendCode" name="smsCompany.extendCode" value="${smsCompany.extendCode }"/></span><span style="color:red">*</span></div>
  </div>
  <div class="rowStyle">
      <div><span >公司名称:</span>
      <input type="hidden" name="hiddenComId" id="hiddenComId" value='${smsCompany.company.comId}'/>
      <span><select name="comId" id="comId" class="selectbox">
      <s:action name="selectCompany" namespace="/cms" executeResult="true"/>
      </select></span><span style="color:red">*</span></div>
  </div>
  <div class="rowStyle">
       <div style=" margin-left:100px;margin-top:20px">
       <input type="submit" value="提交" onclick="return FormCheck(); "/>
       <input type="button" value="关闭" onclick="closeWindow('#editSmsc')"/></div>
  </div>
</form>
</div>
</body>
</html>