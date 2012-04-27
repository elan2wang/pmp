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
      <div style="float:right; margin-left:620px; margin-right:20px; display:inline-block" class="index_top_right">
           <p></p>
           <p >
                 <span><a href="#">欢迎页 </a> </span><span>|</span>
                 <span><a href="#"> 帮助 </a></span><span>|</span>
                 <span><a href="#"> 关于</a> </span><span>|</span>
                 <span><input type="button" value="退出" /></span>
            </p>
       </div>
       <div style="float:left">
           <img src="../Images/Logo2.gif" width="340" height="60" />
       </div>
       <div style="float:left" class="index_top_block">
           <p><span>1</span><span>1</span><span>1</span></p>
           <p >当前小区：</p>
       </div>
       <div style="float:left" class="index_top_block1">
           <p >[ <a href="" target="iMdifPSW">修改密码</a> ]</p>
           <p>[ <a href="">切换</a> ]</p>
       </div>
       <div class="clear"></div>
   </div><!-- 结束标签 class="index_top"-->
   <div class="clear"></div>
   <div class="index_main">
       <div class="index_main_top"><img src="../Images/blank11.gif" width="6" height="6" /></div>
       <div class="index_main_left">
          <div class="index_main_left_top">
		       <script type="text/jscript">
			     todayDate();
               </script>
          </div>
          <div style="border-right:1px #6c92ad solid; height::8px"><img src="../../Images/blank11.gif" width="8" height="8" /></div>
          <div class="LeftMenuItem" id="mm">
             <c:forEach var="item" items="${moduleList }">
             <div id="m1" class="LMIitem" onmouseover="button(this,'over')" onmouseout="button(this,'out')" onclick="button(this,'onclick','${item.modUrl}')">${item.modName }</div>
             </c:forEach>
          </div>
       </div><!-- 结束标签 class="index_main_left"-->
       <div class="index_main_right">
          <iframe id="mainFrame" name="mainFrame" frameborder="0" scrolling="auto" onload="iFrameHeight(this)"  style="width:100%; height:450px">
          </iframe>
       </div>
   </div><!-- 结束标签 class="index_main"-->
   <div class="clear"></div>
   <div class="index_main_top"><img src="../Images/blank11.gif" width="6" height="6" /></div>
   <div class="index_footer">
      <div id="date" style="float:right;display:inline-block;line-height:30px;"> 
            <script type="text/jscript">
			     todayDate();
            </script>
       </div>
       <div id="userinfo" style="float:left;display:inline-block;line-height:30px;">用户：张先生  10:56分登录  在线122分钟</div>
      
   </div>
</div>
</body>
</html>