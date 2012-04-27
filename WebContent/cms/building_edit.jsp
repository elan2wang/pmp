<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="../Scripts/pages/cms/building_edit.js"></script>
</head>

<body>
              <div class="window_content">
                   <form id="form" name="form" action="buildingAction!updateBuilding.action" method="post">
                       <div class="rowStyle">
                          <div><span >楼号：</span><span><input name="building.builNum" type="text" class="textbox" id="building.builNum" value='<s:property value="building.builNum"/>'/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div style="float:left"><span >单元数：</span><span><input name="building.unitCount" type="text" class="textbox" id="building.unitCount" value='<s:property value="building.unitCount"/>' /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >楼层数：</span><span><input name="building.floorCount" type="text" class="textbox" id="building.floorCount" value='<s:property value="building.floorCount"/>'/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span>单元层户数：</span><span><input name="building.housesPer" type="text" class="textbox" id="building.housesPer" value='<s:property value="building.housesPer"/>'/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >跳过楼层数：</span><span><input name="building.skipFloor" type="text" class="textbox" id="building.skipFloor" value='<s:property value="building.skipFloor"/>'/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >楼宇类型：</span>
                               <span><select id="building.builType" name="building.builType">
                                              <option selected="selected"><s:property value="building.builType"/></option>
                                              <option>多层（普通）</option>
                                              <option>多层（电梯）</option>
                                              <option>小高层</option>
                                              <option>高层</option>
                                              <option>别墅/排屋</option>
                                              <option>商铺</option>
                                              <option>写字楼</option>
                                              <option>其他</option>
                                      </select>
                               </span>
                           </div>
                       </div>
                       <div class="rowStyle">
                          <div>
                            <span >是否启用：</span>
                            <span>
                               <input id="project.enabled" name="project.enabled" type="radio" value="true"/><label for="project.enabled">启用</label>
                               <input id="project.enabled" type="radio" name="project.enabled" value="false" checked/><label for="project.enabled">不启用</label>
                            </span>
                          </div>
                       </div> 
                       <div class="rowStyle">
                          <div><span style="">备&nbsp;&nbsp;注：</span><span><textarea name="building.builDesc" style="height:40px;" class="textbox" id="building.builDesc"><s:property value="building.builDesc"/></textarea></span></div>
                       	<input type="hidden" id="building.builId" name="building.builId" value='<s:property value="building.builId"/>'>
                       </div>
                       <div class="rowStyle">
                          <div style=" margin-left:100px;margin-top:20px"><input type="submit" value="保存" onclick="return  editFormCheck(); "/>
                                         <input type="button" value="关闭" onclick="editClose(); "/></div>
                       </div>
                      <div class="clear"></div>
                   </form>
             </div>
</body>
</html>