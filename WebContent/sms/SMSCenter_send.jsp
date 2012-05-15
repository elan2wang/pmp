<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../CSS/common/common.css" rel="stylesheet" type="text/css" />
<link href="../CSS/common/tab.css" rel="stylesheet" type="text/css" />
<link href="../CSS/pages/sms/SMSCenter_send.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../Scripts/jquery-1.7.1.js" ></script>
<script type="text/javascript" src="../Scripts/pages/sms/SMSCenter_send.js"></script>

</head>
<body>
<div class="wrap">      
	   <ul class="nav">
          <li id="tab1" class="active"><a href="#">发送短信</a></li>
          <li id="tab2"><a href="#">已发信息</a></li>
       </ul>   
       <div class="content">
           <div class="innercontent">
               <div class="SMS_main">
                  <div class="row1"><span>接收人号码:</span>
                  <span><input name="receiverNumber" type="text" id="receiverNumber" onFocus="this.select()" /></span>
                  <span><a href="#" class="linkbutton" name="buttonAddReceiver" id="buttonAddReceiver"  onclick="addReceiver();" >加入接收列表</a></span>
                  <span><a href="#" class="linkbutton" name="button2" id="button2" onclick="FindReceiver();">查找接收人</a></span>
                  </div>
                  <div class="row2"><span >接收人列表:</span>
                        <span><select  name="receiverList" size="1" multiple="multiple" id="receiverList" onclick="return receiverListID_onclick()"></select></span>
                  </div>
                  <div class="row3"><span class="row3_1"></span>
                       <span><a href="#" class="linkbutton" name="button3" id="button3" onclick="removeReceiver();" >删除接收人</a></span>
                       <span><a href="#" class="linkbutton" name="button4" id="button4"  onclick="removeAllReceiver();">清空接收列表</a></span>
                       <span><a href="#" class="linkbutton" name="button5" id="button5" onclick="deleteText();" >清空短信内容</a></span>
                       <span style="margin-left:40px;">共<span name="recevierCount" class="SMS_Count" id="recevierCount">0</span>人</span> 
                  </div>
                  <div class="row4">
                       <span>短信内容:</span>
                       <span><textarea name="smsSend.smssContent" id="text" cols="45" rows="5"  maxlength="140" onkeyup="textCount();"></textarea></td>
                       </span>
                  </div>
                  <div class="row5">
                        <span class="row5_1"></span>
                       <span><a href="#" class="linkbutton" name="button6" id="button6"  onclick="formcheck();receiverListTransfer();" >发送</a></span>
                       <span class="row5_3">
                          <span style="display:none" name="currentWords" class="SMS_Count" id="currentWords" >0</span>
                          <span style="display:none" name="totalMsg"  class="SMS_Count" id="totalMsg" >1</span>还可输入
                         <span name="remainWords"  class="SMS_Count" id="remainWords">140</span>
                                                                   字
                      </span>
                  </div>
               </div>
               <input type="hidden" name="smsSend.smssReceiver" id="realReceiverList" />
               <div class="SMS_Recv">
                    <iframe id="SMSlist_Frame" name="SMSlist_Frame" frameborder="0" scrolling="auto" width="100%" height="440"  >
                    </iframe>
               </div>
	       </div>
            
           <div class="innercontent">
                  <iframe id="SMSsended_Frame" name="SMSsended_Frame" frameborder="0" scrolling="auto" width="100%" height="450"  >
                  </iframe>
           </div>
  
      </div>
  </div>
</body>
</html>