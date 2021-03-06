<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Scripts/pages/cms/project_edit.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
</head>
<body>
<div class="window_content">
  <form id="form" name="form" action="editProject" method="post">
      <input type="hidden" name="project.proId" id="project.proId" value='${project.proId }'/>
      <div class="rowStyle">
         <div><span >项目名称：</span><span><input name="project.proName" type="text" class="textbox" id="project.proName" value="${project.proName}" ReadOnly/></span><span style="color:red">*</span></div>
      </div>
      <div class="rowStyle">
         <input type="hidden" name="proType" id="proType" value='${project.proType}'/>
         <div><span >项目类型：</span><span><select id="project.proType" name="project.proType" class="selectbox">
             <option selected="selected" value="">选择项目类型</option>
             <option>小区</option>
            <option>大厦</option>
            <option>别墅</option>
            <option>营业场所</option>
            <option>工业厂房</option>
            <option>住宅</option>
            <option>办公楼</option>
            <option>商业</option>
            <option>工业厂房</option>
             </select><span style="color:red">*</span>
             </span>
         </div>
      </div>
      <div class="rowStyle">
         <input type="hidden" name="proDistrict" id="proDistrict" value='${project.proDistrict}'/> 
         <div><span >所属地区：</span><span><select id="project.proDistrict" name="project.proDistrict" onchange="getStreets()" class="selectbox">
                             <option value="" selected="selected">选择地区</option>
                             <option value="定海区">定海区</option>
                             <option value="普陀区">普陀区</option>
                             <option value="岱山区">岱山区</option>
                             <option value="嵊泗区">嵊泗区</option>
                     </select><span style="color:red">*</span>
              </span>
          </div>
      </div>
      <div class="rowStyle">
          <div><span >所属街道：</span><span><select id="project.proStreet" name="project.proStreet" class="selectbox">
          <option value="${project.proStreet}">${project.proStreet}</option>
          </select><span style="color:red">*</span>
          </span>
         </div> </div>
      <div class="rowStyle">
         <div><span >小区地址：</span><span><input name="project.proAddress" type="text" class="textbox" id="project.proAddress" value='<s:property value="project.proAddress"/>'/></span><span style="color:red">*</span></div>
      </div>
      <div class="rowStyle">
         <div><span >建成时间：</span><span><input id="project.deliveryTime" name="project.deliveryTime" type="text" class="textbox"  value='<fmt:formatDate value="${project.deliveryTime }" type="both" pattern="yyyy-MM-dd"/>' readonly="readonly" style="cursor:pointer;" onFocus="WdatePicker()" /></span></div>
      </div>
      <div class="rowStyle">
         <div><span >户&nbsp;&nbsp;&nbsp;&nbsp;数：</span><span><input name="project.proHouseCount" type="text"  id="project.proHouseCount" value='<s:property value="project.proHouseCount"/>' onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "/></span></div>
      </div>
      <div class="rowStyle">
        <input type="hidden" name="enabled" id="enabled" value='${project.enabled}'/>
        <div><span >是否启用：</span><span><input id="project.enabled" name="project.enabled" type="radio" value="true" <c:if test="${project.enabled==true }">checked="checked"</c:if>/>启用<input id="notenabled" type="radio" name="project.enabled" value="false" <c:if test="${project.enabled==false }">checked="checked"</c:if>/>不启用</span></div>
      </div> 
      <div class="rowStyle">
        <input type="hidden" name="fireEnabled" id="fireEnabled" value='${project.fireEnabled}'/>
        <div><span >启用消控：</span><span><input id="project.fireEnabled" name="project.fireEnabled" type="radio" value="true" <c:if test="${project.fireEnabled==true }">checked="checked"</c:if>/>启用<input id="project.fireEnabled" type="radio" name="project.fireEnabled" value="false" <c:if test="${project.fireEnabled==false }">checked="checked"</c:if>/>不启用</span></div>
      </div>
      <div class="rowStyle">
         <div><span style=" display:inline-block; width:80px;height:40px;line-height:40px">备&nbsp;&nbsp;&nbsp;&nbsp;注：</span><span><textarea name="project.proDesc" style="height:40px;width:150px" class="textbox" id="project.proDesc" value='<s:property value="project.proDesc"/>'></textarea></span></div>
      </div>
      <div class="rowStyle">
         <div style=" margin-left:100px;margin-top:20px">
		  <input type="button" value="修改" onclick="return  FormCheck(); " />
		  <input type="button" value="取消" onclick="closeWindow('#editPro')"/>
         </div>
      </div>
     <div class="clear"></div>
  </form>
</div>
</body>
</html>