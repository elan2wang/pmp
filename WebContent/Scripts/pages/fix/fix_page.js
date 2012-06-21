/**
 * author: Chrussy Ge
 * email： chrussyge@gmail.com
 * create:   2012-6-18
 * 
 * this script is used by the cf_page.jsp
 */ 
$(function(){
	$(".content .innercontent").hide().eq(0).show();
	document.getElementById("fix_owner").src="fix_owner_list.jsp";
	
	$("#tab1").click(function(){
	    document.getElementById("fix_owner").src="fix_owner_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
	$("#tab2").click(function(){
	    document.getElementById("fix_public").src="fix_public_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(1).show();
		return false;
	});
	
});