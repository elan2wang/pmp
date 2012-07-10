<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/pages/cms/owner_export.js"></script>
<title>Insert title here</title>
</head>
<h3>请选择导出条件</h3>
  <p>
  <select id="select1" name="select1" class="selectbox">
    <option value="house.building.project.proName">小区</option>
    <option value="house.building.builNum">楼号</option>
    <option value="house.houseNum">房号</option>
    <option value="owner.ownerName">业主</option>
    <option value="owner.mobile">手机号</option>
  </select>
  <input id="input1" name="input1" class="textbox" />
  </p>
  <p>
  <select id="select2" name="select2" class="selectbox">
  	<option value="house.building.project.proName">小区</option>
    <option value="house.building.builNum">楼号</option>
    <option value="house.houseNum">房号</option>
    <option value="owner.ownerName">业主</option>
    <option value="owner.mobile">手机号</option>
  </select>
  <input id="input2" name="input2" class="textbox" />
  </p>
  <p>
  <select id="select3" name="select3" class="selectbox">
  	<option value="house.building.project.proName">小区</option>
    <option value="house.building.builNum">楼号</option>
    <option value="house.houseNum">房号</option>
    <option value="owner.ownerName">业主</option>
    <option value="owner.mobile">手机号</option>
  </select>
  <input id="input3" name="input3" class="textbox" />
  </p>
  <p><input type="button" value="导出" onclick="owner_export()" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="取消" onclick="closeWindow('#ownerExport')" /></p>
  <div id="download" style="display:none;">请点击下载<a id="link" href="">《业主信息表》</a></div>
</body>
</html>