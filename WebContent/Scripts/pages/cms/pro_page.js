$(function(){
	$(".content .innercontent").eq(1).show();
	document.getElementById("buildingFrame").src="building_list.jsp";
	$(".nav li").removeClass("active");	
	$('#tab2').addClass('active');
	
	$("#tab1").click(function(){
		document.getElementById("projectFrame").src="project_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
	$("#tab2").click(function(){
		document.getElementById("buildingFrame").src="building_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(1).show();
		document.getElementById("frame.pageType").value="all";
		return false;
	});
	$("#tab3").click(function(){
		document.getElementById("houseFrame").src="house_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(2).show();
		document.getElementById("frame.housepageType").value="all";
		return false;
	});
	
});


function selectBuildTab(objproject,objcompany,objid){
	var project = objproject.find('td').eq(1).find('div').html();
	var company = objcompany.find('td').eq(3).find('div').html();
	var id=parseInt(objid.attr("id").substr(3));

    document.getElementById("frame.pageType").value="one";
    document.getElementById("frame.pageId").value=id;
    document.getElementById("frame.projectName").value=project;
    document.getElementById("frame.company").value=company;
    document.getElementById("buildingFrame").src="building_list.jsp";
	$(".nav li").removeClass("active");	
	$("#tab2").addClass('active');
	$(".content .innercontent").hide().eq(1).show();

}

function selectHouseTab(builidObj){
	var id = parseInt(builidObj.attr("id").substr(3));	
	document.getElementById("frame.housepageType").value="one";
	//document.getElementById("frame.builNum").value=buildNum;
	document.getElementById("frame.housepageId").value=id;
	document.getElementById("houseFrame").src="house_list.jsp";
	$(".nav li").removeClass("active");
	$("#tab3").addClass('active');
	$(".content .innercontent").hide().eq(2).show();
}

