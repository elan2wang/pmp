$(function(){
	$('#form1').attr("action","addPublicRepair?fbiId="+$('#FBIID').val());
	$.ajax({
	    type: "POST",
		url: "select_equip?fbiId="+$('#FBIID').val(),
		dataType: "json",
		success : function(data){
			var selector=$("#equipNum"); 
		    $.each(data.Rows,function(commentIndex, comment) {
			    selector.append('<option value="'+comment['equipNum']+'">'+comment['equipNum']+'</option>');
		    });
		},
	});
});

//以下表单检测
function strim(str){
    return str.replace(/(^\s*)|(\s*$)/g,""); 
}

function formcheck(){
	
	obj1=document.getElementById("publicRepair.equipNum");
	obj2=document.getElementById("publicRepair.repairDate");
	obj3=document.getElementById("publicRepair.beginTime");
	obj4=document.getElementById("publicRepair.endTime");
	obj5=document.getElementById("publicRepair.repairDetail");
	obj6=document.getElementById("publicRepair.state");
	obj7=document.getElementById("publicRepair.dutyMan");
	var isTime = /^([01]\d|2[01234]):([0-5]\d|60)$/;
	if(obj1.value==""){
		alert("亲选择设备编号");
		return false;
	}
	if(obj2.value==""){
		alert("请选择日期");
		return false;
	}
	if(strim(obj3.value)==""){
		alert("请填写开始时间");
		obj3.focus();
		return false;
	}else{
		if(isTime.test(strim(obj3.value))==false){
			alert("时间格式错误，例：12:01");
			return false;
		}
	}
	if(strim(obj4.value)==""){
		alert("请填写完成时间");
		obj4.focus();
		return false;
	}else{
		if(isTime.test(strim(obj4.value))==false){
			alert("时间格式错误，例：12:01");
			return false;
		}
	}
	if(strim(obj5.value)==""){
		alert("请填写维修详情");
		obj5.focus();
		return false;
	}
	if(obj6.value==""){
		alert("请选择目前状态");
		return false;
	}
	if(strim(obj7.value)==""){
		alert("请填写责任人");
		obj7.focus();
		return false;
	}
	else{
		return true;
		}
	}
