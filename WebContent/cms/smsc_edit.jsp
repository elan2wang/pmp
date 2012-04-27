<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="loadCompanyList()">
<div class="window_content">
<form name="form2" id="form2" action="editSMSCompany" method="post">
         <div class="rowStyle" style="display:none">
             <div><span >信息机编号:</span><span><input type="text" name="smsCompany.smscId" value="${smsCompany.smscId }"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >信息机名称:</span><span><input type="text" name="smsCompany.smscName" value="${smsCompany.smscName }"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >上行地址:</span><span><input type="text" name="smsCompany.smsUpUrl" value="${smsCompany.smsUpUrl }"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >下行地址:</span><span ><input type="text" name="smsCompany.smsDownUrl" value="${smsCompany.smsDownUrl }"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >应用账号:</span><span><input type="text" name="smsCompany.username" value="${smsCompany.username }"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >应用密码:</span><span><input type="text" name="smsCompany.password" value="${smsCompany.password }"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >应用扩展码:</span><span><input type="text" name="smsCompany.extendCode" value="${smsCompany.extendCode }"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >公司名称:</span>
             <span style="display:none" id="hiddenComId">${smsCompany.company.comId }</span>
             <span><select name="comId" id="comId"></select>
             </span></div>
         </div>
          <div class="rowStyle">
              <div style=" margin-left:100px;margin-top:20px"><input type="submit" value="提交" onclick="return  editFormCheck(); "/>
              <input type="button" value="关闭" onclick="editClose(); "/></div>
           </div>
         </form>
         </div>

</body>
</html>