<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息机管理</title>
<link href="../Scripts/component/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" rel="stylesheet" type="text/css" />
<link href="../Scripts/component/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/iBox.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-new.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/common/window.js"></script>
<script type="text/javascript" src="../Scripts/pages/cms/smsc_list.js"></script>
</head>
<body>
 <div class="wrap">      
       <ul class="nav">
          <li id="tab1" class="active"><a href="javascript:void(0)">信息机管理</a></li>
       </ul>   
       <div class="content">
           <div class="content_main">
              <table id="smsclist">
              </table>
           </div>
       </div>
       <div id="newSmsc" class="easyui-window" href="smsc_add.jsp" title="添加新信息机" iconCls="icon-save" style="width:350px;height:330px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
       <div id="editSmsc" class="easyui-window"  title="编辑信息机" iconCls="icon-save" style="width:350px;height:330px;padding:5px;" closed="true" collapsible="false" minimizable="false" maximizable="false"></div>
    </div>
</body>
</html>