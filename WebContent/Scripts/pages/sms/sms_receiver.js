// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();
	document.getElementById("SMSuser_Frame").src="sms_user_list.jsp";

	$("#tab1").click(function(){
	    document.getElementById("SMSuser_Frame").src="sms_user_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
	$("#tab2").click(function(){
	    document.getElementById("SMSowner_Frame").src="sms_owner_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(1).show();
		return false;
	});

});