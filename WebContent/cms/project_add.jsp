<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../Scripts/pages/cms/project_add.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
</head>

<body>
              <div class="window_content">
                   <form id="form" name="form" action="projectAction!addProject.action" method="post">
                       <div class="rowStyle">
                          <div><span >项目名称：</span><span><input name="project.proName" type="text" class="textbox" id="project.proName" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >项目类型：</span>
                              <span><select id="project.proType" name="project.proType" style="width:150px;">
                              <option selected="selected" value="">选择项目类型</option>
                              <option>小区</option>
                              <option>大厦</option>
                              <option>别墅</option>
                              </select>
                              </span>
                          </div>
                       </div>
                       <div class="rowStyle">
                          <div><span >所属地区：</span>
                               <span><select id="proDistrict" name="project.proDistrict" onchange="getStreets()" style="width:150px;">
                                              <option selected="selected"  value="">选择地区</option>
                                              <option>定海区</option>                                            
                                              <option>普陀区</option>
                                              <option>岱山区</option>
                                              <option>嵊泗区</option>
                                      </select>
                               </span>
                           </div>
                       </div>
                       <div class="rowStyle">
                           <div><span >所属街道：</span>
                              <span><select id="project.proStreet" name="project.proStreet" style="width:150px;">
<!--                                         <option selected="selected">选择镇或街道</option> -->
                                    </select>
                              </span>
                          </div> </div>
                       <div class="rowStyle">
                          <div><span >小区地址：</span><span><input name="project.proAddress" type="text" class="textbox" id="project.proAddress" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >建成时间：</span><span><input id="project.deliveryTime" name="project.deliveryTime" type="text" class="textbox"   readonly="readonly" style="cursor:pointer;" onFocus="WdatePicker()" /></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >户&nbsp;&nbsp;&nbsp;&nbsp;数：</span><span><input name="project.proHouseCount" type="text"  id="project.proHouseCount" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "/></span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span >是否启用：</span><span><input id="project.enabled" name="project.enabled" type="radio" value="true"/>启用<input id="project.enabled" type="radio" name="project.enabled" value="false" checked/>不启用</span></div>
                       </div >
                       <div class="rowStyle" >
                          <div><span>启用消控：</span><span><input id="project.fireEnabled" name="project.fireEnabled" type="radio" value="true"/>启用<input id="project.fireEnabled" type="radio" name="project.fireEnabled" value="false" checked/>不启用</span></div>
                       </div>
                       <div class="rowStyle">
                          <div><span style=" display:inline-block;width:80px;height:40px;line-height:40px">备&nbsp;&nbsp;&nbsp;&nbsp;注：</span><span><textarea name="project.proDesc" style="height:40px;width:150px" class="textbox" id="project.proDesc"></textarea></span></div>
                       </div>
                       <div class="rowStyle">
                          <div style=" margin-left:100px;margin-top:20px"><input type="button" value="完成" onclick="return  FormCheck(); "/></div>
                       </div>
                      <div class="clear"></div>
                   </form>
                   </div>
</body>
</html>