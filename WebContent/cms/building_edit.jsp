<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript" src="../Scripts/pages/cms/building_edit.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
</head>

<body>
              <div class="window_content">
                   <form id="form" name="form" action="updateBuilding" method="post">
                    <input type="hidden" id="building.project.proId" name="building.project.proId" value="${building.project.proId}" />
                       <div class="rowStyle">
                          <div><span >楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</span><span><input name="building.builNum" type="text" class="textbox" id="building.builNum" value='${building.builNum}' readOnly/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >单&nbsp;元&nbsp;标识：</span>
                           <input type="hidden" name="building.unitTag2" id="building.unitTag2" value='${building.unitTag}'/>
                          <span><select id="building.unitTag" name="building.unitTag" style="width:155px">
                          	<option selected="selected" value="">请选择单元标识</option>
                          	<option>数字</option>
                          	<option>字母</option>
                          </select></span></div>
                       </div>
                       <div class="rowStyle">
                          <div style="float:left"><span >单&nbsp;&nbsp;元&nbsp;&nbsp;数：</span><span><input name="building.unitCount" type="text" class="textbox" id="building.unitCount" value='${building.unitCount}' readOnly/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >楼&nbsp;&nbsp;层&nbsp;&nbsp;数：</span><span><input name="building.floorCount" type="text" class="textbox" id="building.floorCount" value='${building.floorCount}' readOnly/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span>单元层户数：</span><span><input name="building.housesPer" type="text" class="textbox" id="building.housesPer" value='${building.housesPer}' readOnly/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >跳过楼层数：</span><span><input name="building.skipFloor" type="text" class="textbox" id="building.skipFloor" value='${building.skipFloor}' readOnly/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >楼&nbsp;宇&nbsp;类型：</span>
                          <input type="hidden" name="building.builType2" id="building.builType2" value='${building.builType}'/>
                               <span><select id="building.builType" name="building.builType" style="width:150px">
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
                               </span>
                           </div>
                       </div>
                       <div class="rowStyle">
                          <div>
                            <span >是&nbsp;否&nbsp;启用：</span>
                            <span>
                             	<input type="hidden" name="building.enabled2" id="building.enabled" value='${building.enabled}'/>
                               <input id="isenabled" name="building.enabled" type="radio" value="true"/><label for="building.enabled">启用</label>
                               <input id="notenabled" type="radio" name="building.enabled" value="false" checked/><label for="building.enabled">不启用</label>
                            </span>
                          </div>
                       </div>
                       <div class="rowStyle">
                          <div><span style="">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span><span><textarea name="building.builDesc" style="height:40px;" class="textbox" id="building.builDesc"><s:property value="building.builDesc"/></textarea></span></div>
                       	<input type="hidden" id="building.builId" name="building.builId" value='<s:property value="building.builId"/>'>
                       </div>
                       <div class="rowStyle">
                          <div style=" margin-left:100px;margin-top:20px"><input type="button" value="保存" onclick="return  editFormCheck(); "/>
                                         <input type="button" value="关闭" onclick="editClose(); "/></div>
                       </div>
                      <div class="clear"></div>
                   </form>
             </div>
</body>
</html>