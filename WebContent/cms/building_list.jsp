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
<link href="../CSS/pages/cms/building_list.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/building_list.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/building_add.js"></script>
</head>

<body onload="PageDownOrUp(0)" style="height:100%">
  <div class="wrap">  
             <div id="top_info" name="top_info"  style="display:none;" >
                <div class="top_info">
                  <span>当前小区：</span><span ><script>document.write(parent.document.getElementById("frame.projectName").value)</script></span>
                  <span>所属物业：</span><span ><script>document.write(parent.document.getElementById("frame.projectName").value)</script></span>
                </div>
                <div class="grid_top"> 
                  <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="openAddNewBuild()">添加新楼宇</a>
                  <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onClick="openAddNewUser()">导入</a>
                </div>
             </div>
             <div id="top_info2" name="top_info2" class="grid_top"  style="display:none;" >
                <span>筛选</span><s:action name="getAllProject" namespace="/cms" executeResult="true"/>
                 <a href="javascript:void(0)" class="easyui-linkbutton" plain="true">搜索</a>
             </div>

              <table id="buildinglist">
                <tbody id="building_data">
                </tbody>
              </table>
              <div class="footer">
	                <span class="gotopage">
                        <a  href="javascript:void(0);" title="跳转" onclick="PageDownOrUp(6)">
                            <img src="../Images/gotopage.gif" />
                        </a>
                    </span>
	                <span class="gotopage">转到：<input id="go_page" name="go_page" type="text" size="4" height="12"/></span> 
	                <span  class="nextpre">&nbsp;&nbsp;
                        <a href="javascript:void(0);" id="first_page" name="first_page"   title="第一页" onclick=""><img src="../Images/first1.gif"/>
                        </a>
                        <a href="javascript:void(0);" id="pre_page" name="pre_page"  title="上一页" onclick=""><img src="../Images/pre1.gif"/>
                        </a>
                        <a href="javascript:void(0);" id="next_page" name="next_page"  title="下一页" onclick=""><img src="../Images/next1.gif"/>
                        </a>
                        <a href="javascript:void(0);" id="last_page" name="last_page"   title="最后一页" onclick=""><img src="../Images/last1.gif"/>
                        </a>
                    </span>
	                <span class="pageinfo">现在是第
                           <span id="now_page" name="now_page">1</span>页，一共有
                           <span id="total_page" name="total_page"></span>页共
                           <span id="total_record" name="total_record"></span>条记录
                           每页显示</span><span><select id="page_row" name="page_row" onchange="PageDownOrUp(5)">
                                <option selected="selected">10</option>
                                <option>15</option>
                                <option>20</option>
                           </select></span>
	          </div>
              <input type="hidden" id="buildingId" name="buildingId" value="" /> 
               <div id="newBuild" class="easyui-window" href="building_add.jsp" title="添加新楼宇" iconCls="icon-save" style="width:350px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
	          </div>
              <div id="editBuild" class="easyui-window" title="编辑楼宇" iconCls="icon-save" style="width:350px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
                  <iframe id="mainFrame" name="mainFrame" frameborder="0" scrolling="auto" width="100%" height="100%" src="building_editBuild.jsp">
                  </iframe>
	          </div>
  </div>
</body>
</html>