$(function(){
	alert("aaa");
    $('#smsHistory').flexigrid({
    	url:"loadHistory",
    	dataType:"json",
        colModel: [
            { display: '所属物业', name:'SMSCompany', width: Width*0.08, align: 'center' },  
            { display: '发送时间', name:'smssTime', width: Width*0.06, align: 'center' },
            { display: '状态', name:'smssState', width: Width*0.06, align: 'center' },
            { display: '发送人', name:'smssPerson', width: Width*0.05, align: 'center' },
            { display: '消息内容', name:'smssContent', width: Width*0.3, align: 'center' },
            { display: '接收人', name:'smssReceiver', width: Width*0.4, align: 'center' },
        ],
        height:350,
        showcheckbox:true,
        nomsg: '没有符合条件的短信记录',
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true
    });
});