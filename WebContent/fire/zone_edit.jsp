<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../Scripts/pages/fire/zone_edit.js"></script>
<title>无标题文档</title>
</head>
	<body>
 <div class="window_content">
	            <form id="form" name="form" action="updateZone" method="post" enctype="multipart/form-data">
	                <div class="rowStyle">
	                   <input type="hidden" value="${zone.zoneId}" name="zone.zoneId">
	                   <input type="hidden" value="${zone.zoneImgUrl}" name="zone.zoneImgUrl">
	                   <input type="hidden" value="${zone.zoneConfigUrl}" name="zone.zoneConfigUrl">
	                   <div><span >场地名称：</span><span><input name="zone.zoneName" type="text" class="textbox" id="zone.zoneName" value="${zone.zoneName}"/></span></div>
	                </div>
	                <div class="rowStyle">
	                              <div>
		                              <span >所在小区：</span>
		                              <span>
	                                     <s:action name="getProjectBySessionHander" namespace="/cms" executeResult="true"/>
	                                     <input type="hidden" id="proId" name="proId" value="${zone.project.proId}"/> 
	                                  </span>
                                  </div>
	                </div>
	                <div class="rowStyle">
	                   <div><span >类型：</span><span><input name="zone.zoneType" type="text" class="textbox" id="zone.zoneType" value="${zone.zoneType}"/></span></div>
	                </div>
	                <div class="rowStyle">
		                   <div>
		                       <span >图片：</span>
		                       <span><input type="file" name="zoneImg"></span>
		                   </div>
	                </div>
	                <div class="rowStyle">
	                       <div>
	                           <span >配置文件：</span>
	                           <span><input type="file" name="zoneConfig"></span>
	                       </div>
	                </div>
	                <div class="rowStyle">
	                   <div><span >描述：</span><span><input name="zone.zoneDesc" type="text" class="textbox" id="zone.zoneDesc"  value="${zone.zoneDesc}"/></span></div>
	                </div>
	                <div class="rowStyle">
	                   <div style=" margin-left:100px;margin-top:20px"><span><input type="submit" value="修改"></span></div>
	                </div>
	            </form>
	      </div>
	</body>
</html>