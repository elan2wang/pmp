$(function(){
	var opId = getQueryString("opId");
	//$('#opId').val(opId);
});

function uploadAttach(){
	var filename=$('#raFile').val();
	if($('#raFile').val()==""){
		alert("请选择上传的文件");
		return false;
	}
	$("#loading").ajaxStart(function(){
		$(this).show();
	})
	.ajaxComplete(function(){
		$(this).hide();
	});
	
	$.ajaxFileUpload({
		url:'addRepairAttach?opId='+$('#opId').val(), 
		secureuri:false,
		fileElementId:'raFile',
		dataType: 'json',
		success: function (data, status){
			if(typeof(data.result) != 'undefined'){
				if(data.result != 'error'){
					$('#msg').html(data.msg);
				}else {
					alert(data.message);
					appendRow(filename);
				}
			}
		},
		error: function (data, status, e){
			alert("上传出错了");
		}
	});
	
	return false;
}

function appendRow(){
	
}

function removeRow(the){
	var id=parseInt($(the).prev().html());//获取文件id号
	$.ajax({
		type: 'POST',
		url: 'sms_inform?houseId='+houseId,
		success: function(data){
			if(data.result=='success'){
				$(the).parent().parent().remove();//页面上删除某一行
			}
			else{
				
			}
		}
	});
}