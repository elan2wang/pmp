/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-16
 * 
 * this script is used by the owner_import.jsp
 */ 

function owner_import(){
	if($('#ownerFile').val()==""){
		alert("请选择上传的文件");
		return false;
	}
	$("#loading").ajaxStart(function(){
		$(this).show();
	})
	.ajaxComplete(function(){
		$(this).hide();
	});
	
	//clear msg
	$('#msg').html("");
	
	$.ajaxFileUpload({
		url:'ownerImport', 
		secureuri:false,
		fileElementId:'ownerFile',
		dataType: 'json',
		success: function (data, status){
			if(typeof(data.error) != 'undefined')
			{
				if(data.error != ''){
					$('#msg').html(data.msg);
				}else{
					alert(data.msg);
					$('#ownerImport').window('close');
					window.location.href="owner_list.jsp";
				}
			}
		},
		error: function (data, status, e){
			alert(e);
		}
	});
	
	return false;
}