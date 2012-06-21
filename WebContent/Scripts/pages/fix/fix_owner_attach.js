
function uploadAttach(){
	var filename=$('#refFile').val();
	if($('#refFile').val()==""){
		alert("请选择上传的文件");
		return false;
	}
	$("#loading").ajaxStart(function(){
		$(this).show();
	})
	.ajaxComplete(function(){
		$(this).hide();
	});
	
	$.ajaxFileUpload
	(
		{
			url:'new_buil_import', 
			secureuri:false,
			fileElementId:'refFile',
			dataType: 'json',
			success: function (data, status)
			{
				if(typeof(data.error) != 'undefined')
				{
					if(data.error != '')
					{
						alert(data.msg);
						$('#msg').html(data.msg);
					}else
					{
						$('#msg').html(data.msg);
					}
				}
			},
			error: function (data, status, e)
			{
				alert(filename);
				appendRow(filename);
			}
		}
	);
	
	return false;
}
///----------------------\
//------已上传文件列表

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