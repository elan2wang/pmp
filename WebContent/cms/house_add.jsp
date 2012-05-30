<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="../Scripts/pages/cms/house_add.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
 <div class="window_content">
                   <form id="form" name="form" action="" method="post">
                   		<input type="hidden" name="house.building.builId" id= "house.building.builId" value=""/>
                      <!--  
                       <div class="rowStyle">
                          <div><span >楼号：</span><span><input name="house.buildingNum" type="text" class="textbox" id="buildingNum" /></span></div>
                       </div>
                       -->
                        <div class="rowStyle">
                          <div><span >单元号：</span><span><input name="unit" type="text" class="textbox" id="unit" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >房号：</span><span><input name="houseNum" type="text" class="textbox" id="houseNum" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >房屋面积：</span><span><input name="house.houseArea" type="text" class="textbox" id="house.houseArea" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div>
                            <span>房屋状态：</span>
                            <span>
                               <input id="house.isempty1" name="house.isempty" type="radio" value="true" checked="checked"/><label for="house.isempty">空置</label>
                               <input id="house.isempty2" type="radio" name="house.isempty" value="false"/><label for="house.isempty">入住</label>
                            </span>
                          </div>
                       </div> 
                       <div class="rowStyle">
                          <div><span>备&nbsp;&nbsp;注：</span><span><textarea name="house.houseDesc" style="height:40px;" class="textbox" id="house.houseDesc"></textarea></span></div>
                       </div>
                       <div class="rowStyle">
                          <div style=" margin-left:100px;margin-top:20px"><input type="button" value="完成" onclick="return  FormCheck(); "/>
                                                                          <input type="button" value="关闭" onclick="return Close(); "/></div>
                       </div>
                      <div class="clear"></div>
                   </form>
                   </div>
</body>
</html>
