/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-10
 * 
 * this script is used by the cf_item_add.jsp
 */ 

function preview(){
	var itemMonth = $('input:checked');
	if(itemMonth.length==0){
		alert("请选择月份");
		return;
	}
	var months = "";
	itemMonth.each(function(){
		months+=$(this).val()+",";
	});
	months = months.substring(0, months.length-1);

	var proId = $('#ProId').val();
	var itemYear = $('#itemYear').val();
	
	var url = "cf_item_preview?proId="+proId+"&itemYear="+itemYear+"&months="+months;
	$.ajax({
		type: "POST",
		url: url,
		dataType: "json",
		success: function(data){
			$('#display').html(data.info);
			$('#submitbtn').removeAttr("disabled");
		}
	});
}

function cancel_item(){
	$('#display').html("");
	var itemMonth = $('input:checked');
	itemMonth.each(function(){
		$(this).removeAttr('checked');
	});
	$('#submitbtn').attr("disabled","disabled");
}

function FormCheck(){
	var proId = $('#ProId').val();
	var itemYear = $('#itemYear').val();
	var itemMonth = $('input:checked');
	if(itemMonth.length==0){
		alert("请选择月份");
		objfc1.focus();
		return (false);
	}
	var months = "";
	itemMonth.each(function(){
		months+=$(this).val()+",";
	});
	months = months.substring(0, months.length-1);
	//检查月份是否重复
	return check_Month(months,proId,itemYear);
}

function check_Month(months,proId,itemYear) {
	var url = "check_Month?months="+months+"&proId="+proId+"&itemYear="+itemYear;
	$.ajax({
		type: "POST",
		url: url,
		dataType:"json",
		success : function(data){					
			var result = data["result"];
			if(result=="Failed"){
				alert("有月份已创建，请核对！");
				return false;
			} else{
				document.getElementById("form").submit();
				return true;						
			}
		}
	});    	
}
