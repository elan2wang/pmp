<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物业缴费管理</title>
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/fee/cf_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/pages/fee/cfi_list.js"></script>
<script type="text/javascript" src="../Scripts/pages/fee/cf_list.js"></script>
</head>
<body>
 <div class="wrap">      
	   <ul class="nav">
          <li id="tab1" class="active"><a href="#">物业费管理</a></li>
         <li id="tab2"><a href="#">代收水费管理</a></li>
          <li id="tab3"><a href="#">代收电费管理</a></li>
       </ul>   
       <div class="content">
           <div class="innercontent">
             <div class="grid_top">
                <a href="#" class="linkbutton"  onclick="openNewCondoFeeItem()">新建项目</a>
             	<span>筛选：</span><select id="project.proDistrict" name="project.proDistrict">
                                              <option selected="selected">选择地区</option>
                                              <option>定海区</option>
                                              <option>普陀区</option>
                                              <option>岱山区</option>
                                              <option>嵊泗区</option>
                                      </select>
                                      <select id="project.proDistrict" name="project.proDistrict">
                                              <option selected="selected">选择地区</option>
                                              <option>定海区</option>
                                              <option>普陀区</option>
                                              <option>岱山区</option>
                                              <option>嵊泗区</option>
                                      </select>
                 <a href="#" class="linkbutton" plain="true" onclick="searchProject()">搜索</a>
                 <input type="hidden" id="searchState" name="searchState" value="0"/>
              </div>
              <div class="content_main">
                 <div class="left_main" style="float:left">
                    <div class="main_nav">项目列表
                    </div>
                    <table id="property_fee_list">
                       <tbody id="property_fee_data">
                       <c:forEach var="item" items="${condoFeeItemList }">
                       <tr><td>1</td><td>${item.itemName }</td>
                          <td>
                           <a href="#" onclick="openCondoFeeList($(this).next().html())">清单</a>
                           <span style="display:none;width:10px">${item.cfiId }</span>
                           <span style="display:inline-block;width:10px"></span>
                           <a href="#" onclick="deleteRow($(this).parent().parent().parent(),$(this).prev().prev().html())">删除</a>
                          </td>
                        </tr>
                        </c:forEach>
                       </tbody>
                    </table>
                 </div>
                 <div class="middle">
                 </div>
                 <div id="right_main" class="right_main" style="float:right;display:none">
                    <iframe name="condoFeeList" id="condoFeeList" frameborder="0" scrolling="auto" width="100%" height="410" style="margin:0px;padding:0px"></iframe>
                 </div>
              </div>
              <div id="newCondoFeeItem" href="cfi_add.jsp" class="easyui-window"  title="新建物业费项目" iconCls="icon-save" style="width:400px;height:420px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false">
              </div>
	       </div>
            
           <div class="innercontent">
                <iframe id="waterFeeFrame" name="waterFeeFrame" frameborder="0" scrolling="auto" width="100%" height="450" style="margin:0px;padding:0px" >
                </iframe>
           </div>
           <div class="innercontent">
           </div>
      </div>
  </div>
</body>
</html>