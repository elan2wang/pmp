/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-6-25
 * 
 * this script is used by the efc_list.jsp
 */ 

function efcDelete(efcId){
	$.ajax({
		type:'post',
		url:'efc_delete?efcId='+efcId,
		dataType:"json",
		success:function(data){
			alert(data.msg);
			$('#efcList').window('refresh');
		}
	});
}
