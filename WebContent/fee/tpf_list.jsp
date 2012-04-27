<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid.js"></script>
<script type="text/javascript" src="../Scripts/pages/fee/tpf_list.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<title>按时收费停车位收费记录</title>
</head>
<body onload="loadData(1)">
<table id="tpf_list">
   <tbody id="tpf_list_data">
   </tbody>
</table>
<div class="footer">
  <span class="gotopage"><a href="#" title="跳转" onclick=""><img src="../Images/gotopage.gif" /></a></span>
  <span class="gotopage">转到：<input id="go_page" name="go_page" type="text" size="4" height="12"/></span> 
  <span class="nextpre">
  &nbsp;&nbsp;<a id="first_page" name="first_page" href="#" title="第一页" onclick=""><img src="../Images/first1.gif"/></a>
  &nbsp;&nbsp;<a id="pre_page" name="pre_page" href="#" title="上一页" onclick=""><img src="../Images/pre1.gif"/></a>
  &nbsp;&nbsp;<a id="next_page" name="next_page" href="#" title="下一页" onclick=""><img src="../Images/next1.gif"/></a>
  &nbsp;&nbsp;<a id="last_page" name="last_page" href="#" title="最后一页" onclick=""><img src="../Images/last1.gif"/></a>  
  </span>
  <span class="pageinfo">&nbsp;&nbsp;&nbsp;&nbsp;现在是第<span id="now_page">1</span>页，共有<span id="total_page">10</span>页&nbsp;&nbsp;每页显示
  <select id="page_row" name="page-row" onchange="">
    <option value="10" selected>10</option>
    <option value="15">15</option>
    <option value="20">20</option>
  </select>条
  </span>
</div>
</body>
</html>