<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head id="head">
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/fee_tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/fee/cf_list2.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/fireControl/fc_device_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/rightMenu.js"></script>
<script type="text/javascript" src="../Scripts/pages/fire2/FireObject.js" ></script>
<script type="text/javascript" src="../Scripts/pages/fire2/fc_list.js"></script>
<script type="text/javascript" src="../Scripts/pages/fire2/fc_device.js"></script>
<style rel="stylesheet" type="text/css">
img {display:block}
.imgBox {margin:0px;padding:0px;z-index:-1px;}
.imgBox li {position:relative;list-style-type:none;border:1px solid #ccc;padding:3px;float:left}
.devicePosi {position:absolute;}
</style>
<title>消防控制部署</title>
<bgsound id="alarm"   src=""   loop=-1   volume=4>
</head>
<body>
<div class="wrap">
  <ul class="nav">
    <li id="tab1" class="active"><a href="fc_list.jsp">消防控制</a></li>
  </ul>
  <div class="content">
    <div class="innercontent">
      <div class="content_main">
        <div class="left_main" style="float:left">
            <div class="left_main_content">
            </div>
        </div>
        <div class="middle"></div>
        
        <div id="right_main" class="right_main" style="float:left;">

           <div id="fc_device" class="IMGContent">
                <div class="top" >
                   <span><button  class="fullscreen"  onclick="FullScreen(this)">全屏显示</button></span>
                </div>
                <div id="imgList" class="IMGList">
                </div>
           </div>
          <div class="bottom">
             <div id="alarm_data" class="bottom_left"></div>
             <div id="abnormal_data" class="bottom_right"></div>

          </div>
        </div>
      </div>
    </div>
  </div>
</div>
  <script type="text/javascript">
   var Width2=document.documentElement.clientWidth;
   var Height2=document.documentElement.clientHeight;
   ChangeHeight(Width2,Height2,'content');
   ChangeHeight(Width2,Height2,'innercontent');
   ChangeHeight(Width2,Height2,'left_main');
   ChangeHeight(Width2,Height2,'middle');
   ChangeHeight(Width2,Height2,'right_main');
  </script>
</body>
</html>