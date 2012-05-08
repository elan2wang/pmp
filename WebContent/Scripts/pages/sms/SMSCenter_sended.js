$(function(){
	  $('#SMSsendedlist').flexigrid({colModel: [
             { display: '序号', width: 20,  align: 'center' },
             { display: '电话号码', width: 100, align: 'center' },
			 { display: '消息内容', width: 500, align: 'center' },
             { display: '发送时间', width: 100,align: 'center' },
			 { display: '状态', width: 100, align: 'center' }
             ],height:305});
	});