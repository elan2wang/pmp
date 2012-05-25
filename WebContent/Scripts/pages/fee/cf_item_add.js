/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-10
 * 
 * this script is used by the cf_item_add.jsp
 */ 
$(function(){
	$(".content .innercontent").show();
});

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
		}
	});
	$('input[type=submit]').removeAttr("disabled");
}

function cancel_item(){
	$('#display').html("");
	var itemMonth = $('input:checked');
	itemMonth.each(function(){
		$(this).removeAttr('checked');
	});
	$('input[type=submit]').attr("disabled","disabled");
}