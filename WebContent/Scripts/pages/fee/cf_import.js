/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-16
 * 
 * this script is used by the cf_import.jsp
 */ 

function new_cf_import(){
	if($('#cfFile').val()==""){
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
	
	$.ajaxFileUpload
	(
		{
			url:'new_cf_import', 
			secureuri:false,
			fileElementId:'cfFile',
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
				alert(e);
			}
		}
	);
	
	return false;
}