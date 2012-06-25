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
		success:function(){
			alert('删除成功');
			$('#efcList').window('refresh');
		}
	});
}
