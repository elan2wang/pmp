<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Scripts/pages/cms/building_add.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
</head>

<body>
              <div class="window_content">
                   <form id="form" name="form" action="saveBuilding" method="post">
                   	<input type="hidden" id="thisProjectId" name="thisProjectId" value=""/>
                   	 <input type="hidden" id="building.project.proId" name="building.project.proId"" value="" />
                       <div class="rowStyle">
                          <div><span >楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</span>
                          <span>
                          <input name="building.builNum" type="text" class="textbox" id="building.builNum" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " />
                          </span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >单&nbsp;元&nbsp;标识：</span><span><select id="building.unitTag" name="building.unitTag" style="width:155px">
                          	<option selected="selected" value="">请选择单元标识</option>
                          	<option>数字</option>
                          	<option>字母</option>
                          </select></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >单&nbsp;&nbsp;元&nbsp;&nbsp;数：</span><span>
                          <input name="building.unitCount" type="text" class="textbox" id="building.unitCount" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " />
                          </span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >楼&nbsp;&nbsp;层&nbsp;&nbsp;数：</span>
                          <span>
                          <input name="building.floorCount" type="text" class="textbox" id="building.floorCount" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " />
                          </span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span>单元层户数：</span><span>
                          <input name="building.housesPer" type="text" class="textbox" id="building.housePer" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " />
                          </span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >跳过楼层数：</span><span>
                          <input name="building.skipFloor" type="text" class="textbox" id="building.skipFloor" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " />
                          </span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >楼&nbsp;宇&nbsp;类型：</span>
                               <span><select id="building.builType" name="building.builType" style="width:150px">
                                              <option selected="selected" value="">选择楼宇类型</option>
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
                               <input id="building.enabled1" name="building.enabled" type="radio" value="true"/><label for="building.enabled">启用</label>
                               <input id="building.enabled2" type="radio" name="building.enabled" value="false" checked/><label for="building.enabled">不启用</label>
                            </span>
                          </div>
                       </div> 
                       <div class="rowStyle">
                          <div><span style="">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span><span><textarea name="building.builDesc" style="height:40px;" class="textbox" id="building.builDesc"></textarea></span></div>
                       </div>
                       <div class="rowStyle">
                          <div style=" margin-left:100px;margin-top:20px"><input type="button" value="完成" onclick="return FormCheck(); "/></div>
                       </div>
                      <div class="clear"></div>
                   </form>
             </div>
           <script type="text/javascript">document.getElementById("thisProjectId").value=parent.document.getElementById("frame.pageId").value;</script>
	</body>
</html>