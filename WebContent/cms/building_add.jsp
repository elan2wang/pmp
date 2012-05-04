<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
</head>

<body>
              <div class="window_content">
                   <form id="form" name="form" action="buildingAction!saveBuilding.action" method="post">
                   	<input type="hidden" id="thisProjectId" name="thisProjectId" value=""/>
                       <div class="rowStyle">
                          <div><span >楼号：</span><span><input name="building.builNum" type="text" class="textbox" id="building.builNum" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >单元标识：</span><span><select id="building.unitTag" name="building.unitTag">
                          	<option selected="selected">请选择单元标识</option>
                          	<option>数字</option>
                          	<option>字母</option>
                          </select></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >单元数：</span><span><input name="building.unitCount" type="text" class="textbox" id="building.unitCount" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >楼层数：</span><span><input name="building.floorCount" type="text" class="textbox" id="building.floorCount" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span>单元层户数：</span><span><input name="building.housesPer" type="text" class="textbox" id="building.housePer" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >跳过楼层数：</span><span><input name="building.skipFloor" type="text" class="textbox" id="building.skipFloor" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >楼宇类型：</span>
                               <span><select id="building.builType" name="building.builType">
                                              <option selected="selected">选择楼宇类型</option>
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
                          <div><span >物业费标准：</span><span><input name="building.condoFeeRate" type="text" class="textbox" id="building.condoFeeRate" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div>
                            <span >是否启用：</span>
                            <span>
                               <input id="project.enabled" name="building.enabled" type="radio" value="true"/><label for="building.enabled">启用</label>
                               <input id="project.enabled" type="radio" name="building.enabled" value="false" checked/><label for="building.enabled">不启用</label>
                            </span>
                          </div>
                       </div> 
                       <div class="rowStyle">
                          <div><span style="">备&nbsp;&nbsp;注：</span><span><textarea name="building.builDesc" style="height:40px;" class="textbox" id="company.comDesc"></textarea></span></div>
                       </div>
                       <div class="rowStyle">
                          <div style=" margin-left:100px;margin-top:20px"><input type="submit" value="完成" onclick="return FormCheck(); "/></div>
                       </div>
                      <div class="clear"></div>
                   </form>
             </div>
             <script type="text/javascript">document.getElementById("thisProjectId").value=parent.document.getElementById("frame.pageId").value;</script>
</body>
</html>