// JavaScript Document
$(function(){
	
	$(".content .innercontent").eq(0).show();
	document.getElementById("SMSuser_Frame").src="SMSuser_list.jsp";

	$("#tab1").click(function(){
		    document.getElementById("SMSuser_Frame").src="SMSuser_list.jsp";
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	$("#tab2").click(function(){
		    document.getElementById("SMSowner_Frame").src="SMSowner_list.jsp";
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(1).show();
			//document.getElementById("frame.pageType").value="all";
			return false;
		});

	});