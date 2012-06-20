<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/fee/cf_list2.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/fee/cf_page.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="wrap">
  <ul class="nav">
    <sec:authorize access="hasAnyRole('AUTH_CONDOFEE_VIEW')"><li id="tab1" class="active"><a href="#">缴费历史</a></li></sec:authorize>
    <sec:authorize access="hasAnyRole('AUTH_CONDOFEE_CREATE')"><li id="tab2"><a href="#">创建物业费</a></li></sec:authorize>
    <sec:authorize access="hasAnyRole('AUTH_CONDOFEE_INPUT')"><li id="tab3"><a href="#">缴费录入</a></li></sec:authorize>
    <sec:authorize access="hasAnyRole('AUTH_CONDOFEE_AUDIT')"><li id="tab4"><a href="#">财务管理</a></li></sec:authorize>
  </ul>
  <div class="content" style="height:570px;">
    <!-- 物业费历史记录页面  -->
    <div class="innercontent" style="height:100%">
      <iframe id="cf_time_list" name="cf_time_list" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
      </iframe>
    </div>
    <!-- 物业费创建页面  -->
    <div class="innercontent" style="height:100%">
      <iframe id="cf_item_add" name="cf_item_add" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
      </iframe>
    </div>
    <!-- 物业费录入页面  -->
    <div class="innercontent" style="height:100%">
      <iframe id="cf_house_list" name="cf_house_list" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
      </iframe>
    </div>
    <!-- 物业费审核页面  -->
    <div class="innercontent" style="height:100%">
      <iframe id="cf_item_list" name="cf_item_list" frameborder="0" scrolling="auto" style="width:100%; height:100%" src="" >
      </iframe>
    </div>
  </div>
</div>
</body>
</html>