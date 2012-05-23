<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Scripts/pages/cms/cmpy_edit.js"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>新建物业公司</title>

</head>

<body>
<form id="form1" method="post" action="updateCompany" onsubmit="return FormCheck()">
                       <div class="rowStyle" style="display:none">
                          <div><span >公司编号：</span><span><input name="company.comId" type="text" class="textbox" id="company.comId" value="${company.comId }"/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >公司名称：</span><span><input name="company.comName" type="text" class="textbox" id="company.comName" value="${company.comName }"/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div ><span >公司法人：</span><span><input name="company.comLegal" type="text" class="textbox" id="company.comLegal" value="${company.comLegal }"/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >公司电话：</span><span><input name="company.comPhone" type="text" class="textbox" id="company.comPhone" value="${company.comPhone }"/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >注册时间：</span><span><input name="company.registerTime" type="text" class="textbox" id="company.registerTime" value="${company.registerTime }" readonly="readonly"  style="cursor:pointer;" onFocus="WdatePicker()"/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >注册资金：</span><span><input name="company.registerMoney" type="text" class="textbox" id="company.registerMoney" value="${company.registerMoney }"/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >工商执照：</span><span><input name="company.comLicense" type="text" class="textbox" id="company.comLicense" value="${company.comLicense }"/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >公司地址：</span><span><input name="company.comAddress" type="text" class="textbox" id="company.comAddress" value="${company.comAddress }"/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span style=" display:inline-block;width:80px;height:40px;line-height:40px">备&nbsp;&nbsp;&nbsp;&nbsp;注：</span><span><textarea name="company.comDesc" style="height:40px;width:150px" class="textbox" id="company.comDesc">${company.comDesc }</textarea></span></div>
                       </div>
                       <div class="rowStyle">
                          <div style=" margin-left:100px;margin-top:20px"><input type="submit" value="完成" onclick="return FormCheck(); "/></div>
                       </div>
                      <div class="clear"></div>
</form>
</body>
</html>