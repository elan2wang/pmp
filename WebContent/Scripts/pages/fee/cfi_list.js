// JavaScript Document
$(function(){
	
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	$("#tab2").click(function(){
		   document.getElementById("waterFeeFrame").src="propertyWaterFee_list.jsp";
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(1).show();
			//document.getElementById("frame.pageType").value="all";
			return false;
		});
     $("#tab3").click(function(){
	   //document.getElementById("houseFrame").src="house_info.jsp";
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(2).show();
			//document.getElementById("frame.housepageType").value="all";
			return false;
		});
	 
	 //$('#property_fee_data').html(AddTds(20,3));
	  $('#property_fee_list').flexigrid({colModel: [
             { display: '序号',  width: 20,  align: 'center' },
             { display: '项目名称', width:180, align: 'center' },
             { display: '操作',  width: 100, align: 'center' }
             ],height:360});
	  
	});
function closeFeeEntry(){
	$('#FeeEntry').window('close');
}

function openCondoFeeList(id){
	document.getElementById("condoFeeList").src="loadCondoFeeList?cfiId="+id;
	$("#right_main").css("display","block");
}

function openNewCondoFeeItem(){
	$('#newCondoFeeItem').window('open');
}
