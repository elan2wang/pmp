<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/fee_tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/fee/cf_list2.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/fix/fix_page.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="wrap">
  <ul class="nav">
     <li id="tab1" class="active"><a href="javascript:void(0)">业主报修管理</a></li>
     <li id="tab2"><a href="javascript:void(0)">公共维修保养</a></li>
  </ul>
  <div class="content" style="height:570px;">
    <!-- 业主报修管理  -->
    <div class="innercontent" style="height:100%">
      <iframe id="fix_owner" name="fix_owner" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
      </iframe>
    </div>
    <!-- 公共维修保养  -->
    <div class="innercontent" style="height:100%">
      <iframe id="fix_public" name="fix_public" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
      </iframe>
    </div>

  </div>
</div>
</body>
</html>