<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/fee_tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/fee/cf_list2.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script src="../Scripts/component/dtree/dtree.js"  type="text/javascript" ></script>
<script src="../Scripts/pages/fee/load_tree.js"  type="text/javascript" ></script>

<title>电费缴费管理</title>
</head>
<body onload="load_data('houseTree','ef_list_by_house.jsp','electricFeeList')">
<div class="wrap">
  <div class="content_main">
    <div class="left_main" style="float:left">
      <div class="left_main_content">
        <div class="dtree">
	    <p><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a></p>
		</div>
		<div id="tree"></div>
      </div>
    </div>
    <div class="middle"></div>
    <div id="right_main" class="right_main" style="float:left;display:block">
      <iframe name="electricFeeList" id="electricFeeList" frameborder="0" scrolling="auto" width="100%" height="100%" style="margin:0px;padding:0px"></iframe>
    </div>
  </div>
</div>
  <script type="text/javascript">
   var Width2=document.documentElement.clientWidth;
   var Height2=document.documentElement.clientHeight;
   ChangeHeight(Width2,Height2-40,'left_main');
   ChangeHeight(Width2,Height2-40,'middle');
   ChangeHeight(Width2,Height2-40,'right_main');
  </script>
</body>
</html>