<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../Scripts/component/flexigrid-1.1/css/flexigrid.pack.css" />
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/component/flexigrid-1.1/js/flexigrid-2.js"></script>
<script type="text/javascript" src="../Scripts/common/common.js"></script>
<script type="text/javascript" src="../Scripts/pages/sms/SMSuser_list.js"></script>
</head>
<body>
<div class="wrap">
              <div class="grid_top"> 
                  <select>
                     <option>-----公司-----</option>
                  </select>
                  <select>
                     <option>----小区----</option>
                  </select>
                  <select>
                     <option>----职位----</option>
                  </select>
                  <span style="display:inline-block;width:10px;"></span>
                  <a href="#" class="linkbutton" onclick="selectAll()">全选</a>
                  <a href="#" class="linkbutton" onclick="selectOpposite()">反选</a>
                  <a href="#" class="linkbutton" onclick="selectNone()">全不选</a>
              </div>
              <table id="SMSuserlist">
                <tbody id="SMSuser_data">
                  <tr><td><input type="checkbox" id="checkgroup" name="checkgroup" value="13568821380" onclick="selectAll()"/></td><td></td><td></td><td></td><td></td><td></td>
                  </tr>
                  <tr><td><input type="checkbox" id="checkgroup" name="checkgroup" value="13568821380"/></td><td></td><td></td><td></td><td></td><td></td>
                  </tr>
                  <tr><td><input type="checkbox" id="checkgroup" name="checkgroup" value="13568821380"/></td><td></td><td></td><td></td><td></td><td></td>
                  </tr>
                </tbody>
              </table>
              <div class="">
                 <a href="#" class="linkbutton" onclick="submitForm()">确认提交</a>
                 <a href="#" class="linkbutton" onclick="closeFrame()">取消</a>
              </div>
</div>
</body>
</html>