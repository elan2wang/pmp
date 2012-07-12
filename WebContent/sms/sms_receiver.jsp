<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" />
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-new.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/pages/sms/sms_receiver.js"></script>
</head>
<body>
<div class="wrap">      
	   <ul class="nav">
          <li id="tab1" class="active"><a href="#">用户</a></li>
          <li id="tab2"><a href="#">业主</a></li>
       </ul>   
       <div class="content" style="height:550;border:1px solid lightblue;border-top:hidden">
           <div class="innercontent" style="height:100%">
              <iframe id="SMSuser_Frame" name="SMSuser_Frame" frameborder="0" scrolling="auto" width="100%" height="100%"  >
              </iframe>
           </div>
           <div class="innercontent" style="height:100%">
             <iframe id="SMSowner_Frame" name="SMSowner_Frame" frameborder="0" scrolling="auto" width="100%" height="100%"  >
              </iframe>
           </div>
       </div>
 </div>
</body>
</html>