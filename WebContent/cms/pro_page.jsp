<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-new.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/pro_page.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="wrap">      
    <ul class="nav">
      <sec:authorize access="hasAnyRole('AUTH_PROJECT_MANAGE')"><li id="tab1" class="active"><a href="javascript:void(0)">物业项目列表</a></li></sec:authorize>
      <sec:authorize access="hasAnyRole('AUTH_BUILDING_MANAGE','AUTH_BUILDING_VIEW')"><li id="tab2"><a href="javascript:void(0)">楼宇信息列表</a></li></sec:authorize>
      <sec:authorize access="hasAnyRole('AUTH_HOUSE_MANAGE','AUTH_HOUSE_VIEW')"><li id="tab3"><a href="javascript:void(0)">房产信息列表</a></li></sec:authorize>
    </ul>
    <input type="hidden" id="frame.pageType" name="frame.pageType" value="all" />
    <input type="hidden" id="frame.pageId" name="frame.pageId" value="" />
    <input type="hidden" id="frame.projectName" name="frame.projectName" value="" />
	<input type="hidden" id="frame.company" name="frame.company" value="" />
	<input type="hidden" id="frame.housepageType" name="frame.housepageType" value="all" />
	<input type="hidden" id="frame.housepageId" name="frame.housepageId" value="1" />
	<input type="hidden" id="frame.builNum" name="frame.builNum" value=""/>
	
    
    <div class="content" style="height:560px;">
        <!-- 物业项目管理TAB -->
        <div class="innercontent" style="height:100%">
           <div class="content_main">
           <iframe id="projectFrame" name="houseFrame" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
           </iframe>
           </div>
        </div>
       
       <!-- 楼宇信息管理TAB -->
	    <div class="innercontent" style="height:100%">
	     <div class="content_main">
	       <iframe id="buildingFrame" name="buildingFrame" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
	       </iframe>
	      </div>
	    </div>
	    <!-- 房产信息管理TAB -->
	    <div class="innercontent" style="height:100%">
	        <div class="content_main">
	       <iframe id="houseFrame" name="houseFrame" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
	       </iframe>
	       </div>
	    </div>
    </div>
    
</div>
<script type="text/javascript" src="../Scripts/common/changeSize.js"></script>
</body>
</html>