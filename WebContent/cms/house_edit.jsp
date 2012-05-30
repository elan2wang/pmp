<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="../Scripts/pages/cms/house_edit.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
 <div class="window_content">
                   <form id="form" name="form" action="updateHouse" method="post">
                   		<input type="hidden" name="house.building.builId" id= "house.building.builId" value=""/>
                   		<input type="hidden" name="house.houseId" id= "house.houseId" value='${house.houseId}'/>
                       <div class="rowStyle">
                          <div><span >楼号：</span><span><input name="buildingNum" type="text" class="textbox" id="buildingNum" value='${buildingNum}' readOnly/></span></div>
                       </div>
                        <div class="rowStyle">
                          <div><span >单元号：</span><span><input name="unit" type="text" class="textbox" id="unit" value='<s:property value="unit"/>' readOnly/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >房号：</span><span><input name="house.houseNum" type="text" class="textbox" id="houseNum" value='${house.houseNum}' readOnly/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >房屋面积：</span><span><input name="house.houseArea" type="text" class="textbox" id="house.houseArea" value='${house.houseArea}' onkeyup="this.value=this.value.replace(/[^\d\.]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d\.]/g,'') " /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div>
                            <span>房屋状态：</span>
                            <span>
                               <input type="hidden" name="house.isempty2" id="house.isempty" value='${house.isempty}'/>
                               <input id="isempty" name="house.isempty" type="radio" value="true" checked/><label for="house.isempty">空置</label>
                               <input id="notempty" type="radio" name="house.isempty" value="false"/><label for="house.isempty">入住</label>
                            </span>
                          </div>
                       </div> 
                       <div class="rowStyle">
                          <div><span>备&nbsp;&nbsp;注：</span><span><textarea name="house.houseDesc" style="height:40px;" class="textbox" id="house.houseDesc"></textarea></span></div>
                       </div>
                       <div class="rowStyle">
                          <div style=" margin-left:100px;margin-top:20px"><input type="button" value="完成" onclick="return  FormCheck(); "/>
                                                                          <input type="button" value="关闭" onclick="Close(); "/></div>
                       </div>
                      <div class="clear"></div>
                   </form>
                   </div>
</body>

</html>
