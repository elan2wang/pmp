$(function(){
	var cfiId = document.getElementById("cfiId").value;
	document.getElementById("exportlink").setAttribute("href", "new_cf_export?cfiId="+cfiId);
});

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
					if(data.error != ''){
						$('#msg').html(data.msg);
					}else {
						alert(data.msg);
						closeWindow('#cfImport');
						window.location.href = window.location.href;
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