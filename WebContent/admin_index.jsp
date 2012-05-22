<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="CSS/common/reset.css" rel="stylesheet" type="text/css" />
<link href="CSS/pages/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="Scripts/pages/index.js"></script>
<style type="text/css">
<!--
body {
	margin-bottom: 0px;
	overflow-y:hidden;
}
a{
	text-decoration:none;
	color:#00F;
}
-->
</style>
<title>舟山市物业管理信息系统</title>
</head>
<body>
<div class="index">
   <div style="height:60px" class="index_top">
       <div class="clear"></div>
      <div class="index_top_right">
           <span><a href="javascript:void(0)" >欢迎页 </a> </span><span>|</span>
           <span><a href="javascript:void(0)"> 帮助 </a></span><span>|</span>
           <span><a href="javascript:void(0)"> 关于</a> </span><span>|</span>
           <span><a href="javascript:void(0)">退出</a></span>
       </div>
       <div style="float:left">
           <img src="Images/Logo2.gif" width="340" height="60" />
       </div>
       <div style="float:left" class="index_top_block1">
       </div>
       <div class="clear"></div>
   </div><!-- 结束标签 class="index_top"-->
   <div class="clear"></div>
   <div id="index_main" class="index_main">
       <div class="index_main_top"><img src="Images/blank11.gif" width="6" height="6" /></div>
       <div class="index_main_left">
          <div class="index_main_left_top">
		       <script type="text/javascript">
			     document.write(todayDate());
               </script>
          </div>
          <div style="border-right:1px #6c92ad solid; height:8px"><img src="Images/blank11.gif" width="8" height="8" /></div>
          <div class="LeftMenuItem" id="mm">
             <c:forEach var="item" items="${moduleList }">
             <div id="m1" class="LMIitem" onmouseover="button(this,'over')" onmouseout="button(this,'out')" onclick="button(this,'onclick','${item.modUrl}')">${item.modName }</div>
             </c:forEach>
             <div id="blanks" name="blanks" style="border-right:1px #6c92ad solid;">&nbsp;</div>
             
          </div>
       </div><!-- 结束标签 class="index_main_left"-->
       <div class="index_main_right">
          <iframe id="mainFrame" name="mainFrame" frameborder="0" scrolling="no"   style="width:100%; height:100%">
          </iframe>
       </div>
   </div><!-- 结束标签 class="index_main"-->
   <div class="clear"></div>
   
   <div class="index_footer">
       <div class="index_main_top"><img src="Images/blank11.gif" width="6" height="6" /></div>
       <div id="userinfo" style="display:inline-block;line-height:30px;text-align:center">版权所有：中国移动公司</div>
   </div>
   
</div>
<script type="text/javascript">
initSize();
</script>
</body>
</html>
