<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户列表</title>

<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/pages/admin/user_list.js"></script>
<script type="text/javascript" src="../Scripts/pages/admin/user_add.js"></script>
<script type="text/javascript" src="../Scripts/pages/admin/user_edit.js"></script>
</head>

<body onload="PageDownOrUp(0)">
   <div class="wrap">      
       <ul class="nav">
          <li id="tab1" class="active"><a href="#">用户管理</a></li>
       </ul>   
       <div class="content">
           <div class="innercontent">
             <div class="grid_top">
             <a href="#" class="easyui-linkbutton" plain="true" onClick="openAddNewUser()">添加新用户</a>
                <a href="#" class="easyui-linkbutton" plain="true" onClick="">导入</a>
                <span>筛选</span>：<select id="select1" name="select1"  style="width:100px" onchange="getSecondInfo()">
                                           <option selected="selected">所在小区</option>
                                           <option >1</option>
                                           <option >2</option>
                                  </select>
                                  <select id="select2" name="select2"  style="width:100px">
                                           <option selected="selected">请选择小区</option>
                                  </select>
                 <span> 搜索：</span><input type="text" width="100" />
                 <a href="#" class="easyui-linkbutton" plain="true">搜索</a>
                 <a href="#" class="easyui-linkbutton" plain="true" onclick="selectAll()">全选</a>
                 <a href="#" class="easyui-linkbutton" plain="true" onclick="selectOpposite()">反选</a>
                 <a href="#" class="easyui-linkbutton" plain="true" onclick="selectNone()">全不选</a>
                 <a href="#" class="easyui-linkbutton" plain="true" onclick="deleteSelected()">批量删除</a>
              </div>
              <table id="userlist">
                 <tbody id="user_data">
                 </tbody>
              </table>
              <div class="footer">
	                <span class="gotopage">
                        <a  href="#" title="跳转" onclick="PageDownOrUp(6)">
                            <img src="../Images/gotopage.gif" />
                        </a>
                    </span>
	                <span class="gotopage">转到：<input id="go_page" name="go_page" type="text" size="4" height="12"/></span> 
	                <span  class="nextpre">&nbsp;&nbsp;
                        <a id="first_page" name="first_page" href="#" title="第一页" onclick="PageDownOrUp(1)">
                             <img src="../Images/first1.gif"/>
                        </a>&nbsp;&nbsp;
                        <a id="pre_page" name="pre_page" href="#" title="上一页" onclick="PageDownOrUp(2)">
                             <img src="../Images/pre1.gif"/>
                        </a><span>&nbsp;&nbsp;</span>
                        <a id="next_page" name="next_page" href="#" title="下一页" onclick="PageDownOrUp(3)">
                             <img src="../Images/next1.gif"/>
                        </a><a id="last_page" name="last_page" href="#" title="最后一页" onclick="PageDownOrUp(4)">
                             <img src="../Images/last1.gif"/>
                        </a>
                    </span>
	                <span class="pageinfo">&nbsp;&nbsp;&nbsp;&nbsp;现在是第
                           <span id="now_page" name="now_page">1</span>页，一共有
                           <span id="total_page" name="total_page">10</span>页&nbsp;&nbsp;共
                           <span id="total_record" name="total_record">239</span>条记录 &nbsp;&nbsp;
                           每页显示<select id="page_row" name="page_row" onchange="PageDownOrUp(5)">
                                <option selected="selected">10</option>
                                <option>15</option>
                                <option>20</option>
                           </select>页
                    </span>
	          </div>
              <div id="newUser" class="easyui-window" href="user_add.jsp" title="添加新用户" iconCls="icon-save" style="width:350px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
              </div>
              
              
               <div id="editUser" class="easyui-window"  title="编辑用户" iconCls="icon-save" style="width:350px;height:370px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
               </div>
               

	       </div>
	  </div>
  </div>
</body>
</html>

