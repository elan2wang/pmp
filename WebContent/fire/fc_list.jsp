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
<script type="text/javascript" src="../Scripts/pages/fireControl/FireObject.js" ></script>
<script type="text/javascript" src="../Scripts/pages/fireControl/fc_list.js"></script>
<title>消防控制部署</title>
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
             <%@ include file="fc_tree.jsp" %>
            </div>
        </div>
        <div class="middle"></div>
        
        <div id="right_main" class="right_main" style="float:left;">
           <div style="height:400px">
           <iframe name="fc_device" id="fc_device"  frameborder="0" scrolling="auto" width="100%" height="100%" style="margin:0px;padding:0px"></iframe>
            </div>
          <div class="bottom">
             <div class="bottom_left" id="callFireInfo">
             </div>
             <div class="bottom_right" id="warnFireInfo">
             </div>
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