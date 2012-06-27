<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript" src="../Scripts/pages/cms/building_edit.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
</head>

<body>
<div class="window_content">
    <form id="form" name="form" action="editBuilding" method="post">
    <div class="rowStyle">
       <div><span >小&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：</span><span><input name="building.project.proId" type="hidden" class="textbox" id="building.project.proId" value='${building.project.proId}'/><input name="building.project.proName" type="text" class="textbox" id="building.project.proName" value='${building.project.proName}'/></span>
       <span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
       <div><span >楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</span><span><input name="building.builNum" type="text" class="textbox" id="building.builNum" value='${building.builNum}' readOnly/></span>
       <span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
       <div><span >单元标识：</span><span><input type="text" name="building.unitTag" id="building.unitTag" class="textbox" value='${building.unitTag}' readOnly/>
       </span><span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
       <div style="float:left"><span >单&nbsp;&nbsp;元&nbsp;&nbsp;数：</span><span><input name="building.unitCount" type="text" class="textbox" id="building.unitCount" value='${building.unitCount}' readOnly/>
       </span><span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
       <div><span >楼&nbsp;&nbsp;层&nbsp;&nbsp;数：</span><span><input name="building.floorCount" type="text" class="textbox" id="building.floorCount" value='${building.floorCount}' readOnly/></span>
       <span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
       <div><span>每层户数：</span><span><input name="building.housesPer" type="text" class="textbox" id="building.housesPer" value='${building.housesPer}' readOnly/></span>
       <span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
       <div><span >跳过楼层：</span><span><input name="building.skipFloor" type="text" class="textbox" id="building.skipFloor" value='${building.skipFloor}' readOnly/></span>
       <span style="color:red">*</span></div>
    </div>
    <div class="rowStyle">
       <input type="hidden" name="builType" id="builType" value='${building.builType}'/>
       <div><span >楼宇类型：</span><span><select id="building.builType" name="building.builType" class="selectbox">
                           <option selected="selected" value="">请选择楼宇类型</option>
                           <option>多层（普通）</option>
                           <option>多层（电梯）</option>
                           <option>小高层</option>
                           <option>高层</option>
                           <option>别墅/排屋</option>
                           <option>商铺</option>
                           <option>写字楼</option>
                           <option>其他</option>
                   </select>
            </span><span style="color:red">*</span>
        </div>
    </div>
    <div class="rowStyle">
       <div>
         <span >是否启用：</span>
         <span>
            <input name="building.enabled" type="radio" value="true" <c:if test="${building.enabled==true }">checked="checked"</c:if> />启用
            <input name="building.enabled" type="radio" value="false" <c:if test="${building.enabled==false }">checked="checked"</c:if> />不启用
         </span>
       </div>
    </div>
    <div class="rowStyle">
       <div><span style="">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span><span><textarea name="building.builDesc" style="height:40px;" class="textbox" id="building.builDesc"><s:property value="building.builDesc"/></textarea></span></div>
    	<input type="hidden" id="building.builId" name="building.builId" value='<s:property value="building.builId"/>'>
    </div>
    <div class="rowStyle">
       <div style=" margin-left:100px;margin-top:20px">
       <input type="button" value="修改" onclick="return editFormCheck(); "/>
       <input type="button" value="取消" onclick="closeWindow('#editBuild')"/></div>
   </div>
   <div class="clear"></div>
   </form>
</div>
</body>
</html>