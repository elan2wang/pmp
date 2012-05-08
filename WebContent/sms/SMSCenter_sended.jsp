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
<script type="text/javascript" src="../Scripts/pages/sms/SMSCenter_sended.js"></script>
</head>
<body>
<div class="wrap">
              <div class="grid_top">
                 <input type="text" />
                 <a href="#" class="linkbut" >检索</a>
                 <span style="">可以按电话号码/消息内容/发送日期检索.按日期检索式，格式如：2012-04-01</span>
              </div>
              <table id="SMSsendedlist">
                <tbody id="SMSsended_data">
                  <tr><td></td><td></td><td></td><td></td><td></td>
                  </tr>
                  <tr><td></td><td></td><td></td><td></td><td></td>
                  </tr>
                  <tr><td></td><td></td><td></td><td></td><td></td>
                  </tr>
                </tbody>
              </table>
</div>

</body>
</html>