<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Scripts/pages/cms/building_add.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
</head>

<body>
  <div class="window_content">
       <form id="form" name="form" action="addBuilding" method="post">
           <div class="rowStyle">
              <div><span >小&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：</span><select id="building.project.proId" name="building.project.proId" class="selectbox">
               <s:action name="selectProject_ByAuth" executeResult="true" namespace="/cms" />
              </select>
              <span style="color:red">*</span></div>
           </div>
           <div class="rowStyle">
              <div><span >楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</span><span><input name="building.builNum" type="text" class="textbox" id="building.builNum" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " /></span>
              <span style="color:red">*</span></div>
           </div>
           <div class="rowStyle">
              <div><span >单元标识：</span><span><select id="building.unitTag" name="building.unitTag" class="selectbox">
              	<option selected="selected" value="">请选择单元标识</option>
              	<option>数字</option>
              	<option>字母</option>
              </select>
              </span><span style="color:red">*</span></div>
           </div>
           <div class="rowStyle">
              <div><span >单&nbsp;&nbsp;元&nbsp;&nbsp;数：</span><span><input name="building.unitCount" type="text" class="textbox" id="building.unitCount" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " />
              </span><span style="color:red">*</span></div>
           </div>
           <div class="rowStyle">
              <div><span >楼&nbsp;&nbsp;层&nbsp;&nbsp;数：</span><span><input name="building.floorCount" type="text" class="textbox" id="building.floorCount" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " />
              </span><span style="color:red">*</span></div>
           </div>
           <div class="rowStyle">
              <div><span>每层户数：</span><span><input name="building.housesPer" type="text" class="textbox" id="building.housePer" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " />
              </span><span style="color:red">*</span></div>
           </div>
           <div class="rowStyle">
              <div><span >跳过楼层：</span><span><input name="building.skipFloor" type="text" class="textbox" id="building.skipFloor" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " />
              </span></div>
           </div>
           <div class="rowStyle">
              <div><span >楼宇类型：</span><span><select id="building.builType" name="building.builType"  class="selectbox">
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
                   </span><span style="color:red">*</span>
               </div>
           </div>
           <div class="rowStyle">
              <div>
                <span >是否启用：</span>
                <span>
                   <input id="building.enabled1" name="building.enabled" type="radio" value="true"/><label for="building.enabled">启用</label>
                   <input id="building.enabled2" type="radio" name="building.enabled" value="false" checked/><label for="building.enabled">不启用</label>
                </span>
              </div>
           </div> 
           <div class="rowStyle">
              <div><span style="">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span><span><textarea name="building.builDesc" style="height:40px;" class="textbox" id="building.builDesc"></textarea></span></div>
           </div>
           <div class="rowStyle">
              <div style=" margin-left:100px;margin-top:20px">
              <input type="button" value="创建" onclick="return FormCheck();"/>
              <input type="button" value="取消" onclick="closeWindow('#newBuild')"/></div>
           </div>
          <div class="clear"></div>
       </form>
  </div>
</body>
</html>