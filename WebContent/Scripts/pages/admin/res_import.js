/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-16
 * 
 * this script is used by the owner_import.jsp
 */ 

function res_import(){
	if($('#resFile').val()==""){
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
			url:'import_res', 
			secureuri:false,
			fileElementId:'resFile',
			dataType: 'json',
			success: function (data, status)
			{
				if(typeof(data.error) != 'undefined')
				{
					if(data.error != '')
					{
						$('#msg').html(data.msg);
					}else
					{
						$('#importRes').window('close');
						alert(data.msg);
						window.location.href="res_list.jsp";
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