function uploadAttach(){
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
	var opId=$('#opId').val();
	$.ajaxFileUpload({
		url:'addRepairAttach?opId='+opId, 
		secureuri:false,
		fileElementId:'raFile',
		dataType: 'json',
		success: function (data, status){
			if(typeof(data.result) != 'undefined'){
				if(data.result == 'success'){
					alert(data.message);
					$("#openAttach").window('refresh');
				}else {
					alert("上传失败,"+data.message);
				}
			}
		},
		error: function (data, status, e){
			alert("上传出错了");
		}
	});
	return false;
}

//the 表示当前标签
function deleteAttach(the){
	var raId = $(the).prev().html();
	$.ajax({
		type: "POST",
		url: "deleteRepairAttach?raId="+raId,
		dataType: "json",
		success: function(data){
			alert(data.msg);
			//移除当前<tr></tr>
			$(the).parent().parent().remove();
		}
	});
}
