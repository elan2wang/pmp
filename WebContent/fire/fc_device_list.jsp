<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head id="head">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../CSS/pages/fireControl/fc_device_list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/common/rightMenu.js"></script>
<script type="text/javascript" src="../Scripts/pages/fireControl/fc_device.js" ></script>
<style rel="stylesheet" type="text/css">
img {display:block}
#imgBox {margin:0px;padding:0px;z-index:-1px;}
#imgBox li {position:relative;list-style-type:none;border:1px solid #ccc;padding:3px;float:left}
.devicePosi {position:absolute;}
</style>
<script type="text/javascript">
    var zoneImgUrl="${zone.zoneImgUrl}";
    var zoneConfigUrl="${zone.zoneConfigUrl}";
    if(zoneImgUrl!=null&&zoneImgUrl!=""&&zoneConfigUrl!=null&&zoneConfigUrl!=""){
        alert(zoneImgUrl);
        alert(zoneConfigUrl); 
    }
</script>
</head>
<body onload="load_device('../fireConfig/basement1.xml')">
<div class="wrap" >
   <div class="top" >
      <span><input type="button" value="停止警报" onclick="stopBlink()"/></span>
      <span><input type="button" value="全屏显示"/></span>
   </div>
   <div class="device" >
      <ul id="imgBox" >
         <li id="devicelist">
         </li>
      </ul>
   </div>
   <div class="bottom">
      <div class="bottom_left"><span id="myajax"></span></div>
      <div class="bottom_right">1111111</div>
   </div>
</div>
</body>
</html>