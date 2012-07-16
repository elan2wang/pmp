<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<link href="../CSS/pages/fire/fc_device_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/rightMenu.js"></script>
<script type="text/javascript" src="../Scripts/pages/fire/FireObject.js" ></script>

<script type="text/javascript" src="../Scripts/pages/fire/fc_device.js" ></script>

<style rel="stylesheet" type="text/css">
img {display:block}
#imgBox {margin:0px;padding:0px;z-index:-1px;}
#imgBox li {position:relative;list-style-type:none;border:1px solid #ccc;padding:3px;float:left}
.devicePosi {position:absolute;}
</style>

</head>
<body onload="" style="background:black">
<input id="imgUrl" type="hidden" value="${zone.zoneImgUrl}"/>
<input id="configUrl" type="hidden" value="${zone.zoneConfigUrl}"/>
<input id="zoneID" name="zoneID" type="hidden" value="${zone.zoneId}"/>
<div class="wrap" style="height: 400px; margin-right:20px">
   <div class="top" >
      <span><button  class="fullscreen"  onclick="FullScreen(this)">全屏显示</button></span>
   </div>
   <div class="device" >
      <ul id="imgBox" >
         <li id="devicelist">
         </li>
      </ul>
   </div>
</div>

</body>
</html>