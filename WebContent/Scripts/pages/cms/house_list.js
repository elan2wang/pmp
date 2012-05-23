// JavaScript Document
$(function(){
	var builid;
	var projectid;
	if(parent.document.getElementById("frame.pageType").value=="all"){	
		projectid = 0;
	}
	else
		projectid = parseInt(parent.document.getElementById("frame.pageId").value);
	if(parent.document.getElementById("frame.housepageType").value=="all"){
		builid = 0;
		
		$('#top_info').css("display","none");
		$('#top_info2').css("display","block");
	    $('#house_data').html(AddTds(20,8));
	}
	else
	{
		builid = parseInt(parent.document.getElementById("frame.housepageId").value);		
		$('#top_info').css("display","block");
		$('#top_info2').css("display","none");
		$('#house_data').html(AddTds(20,6));
	}

	    $('#houselist').flexigrid({
	    	     url:"house_listBySessionHandler?buildingId="+builid+"&projectId="+projectid,
			     dataType:"json",
			  	 colModel: [
	             { display: '房号', name:'houseNum',width: 200, align: 'center' },
				 { display: '房屋面积',name:'houseArea', width: 200, align: 'center' },
	             { display: '物业费标准', name:'condoFeeRate',width: 200,align: 'center' },
	             { display: '是否空置',name:'isempty', width: 200,align: 'center' }
	             ], 
	    		searchitems:[
	    		 		    { display: '房号', name: 'houseNum', isDefault:true },
	    		 		],
	             height:305,
	             showcheckbox:true,
	             usepager: true,
	     		 useRp: true,
	     		 rp: 15,
	     		 operation:true,
	     		operationcontent:'<a href="javascript:void(0)" onclick="openEditHouse($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"deleteHouse($(this).parent().parent().parent(),$(this).parent().parent().parent());\">删除</a>',
				 operationWidth: Width*0.22
	             });		

});
function houseImport(){
	openAddWindow('#houseImport');
}
function openEditHouse(obj)
{
	var id=parseInt(obj.attr("id").substr(3));	
	var url = 'getHouseById?houseId='+id;
	openEditWindow("#editHouse",url);
}
function closeEditHouse(){
    $('#editHouse').window('close');
}

function openAddNewHouse(){
	$('#newHouse').window('open');
}
function closeAddNewHouse(){
	$('#newHouse').window('close');
}


function deleteHouse(obj,id){
	alert("进入删除方法");
	alert(id);
	if(!confirm("您将删除该房屋的信息,确定删除吗?"))
	{
		return;
	}
	$.ajax({
		  type: "POST",
		  url: 'deleteHouse?houseId='+id,
		  dataType: "json",
		  success : function(data){
//			  if((data.success).equals("")){
			  obj.hide();
//			  }
		  }
	   });
	
}
function PageDownOrUp(flag){
//			if(parent.document.getElementById("frame.housepageType").value=="all"){
//		        housepageid=-1;
//		    }
//		    else{
//			    pageid=parseInt(parent.document.getElementById("frame.housepageId").value);
//		    }
//
//			var urlstr="";

//				projectId=document.getElementById("projectId").value;
//				buildingId=document.getElementById("buildingId").value;
//				alert("projectId"+projectId);
//				alert("buildingId"+buildingId);
//				urlstr="getHouseByConditions?buildingId="+buildingId+"&projectId="+projectId+"&currentPage="+nowpage+"&pageSize="+pagerow;
//				alert(urlstr);
//			}
//			  urlstr="house_list?currentPage="+nowpage+"&pageSize="+pagerow;
//            $.ajax({
//			  type: "POST",
//			  url: urlstr,
//			  dataType: "json",
//			  success : function(data){
//				  var i=0;
//                  $('#house_data').find('tr').hide();
//                  var tableTds=$('#house_data').find('td');
//                  tableTds.find('div').css('text-align','center');
//				  for(i=0;i<pagerow;i++)
//				  {$('#house_data').find('tr').eq(i).show();}
//				  i=0;
//				  tableTds.find('div').html("");
//				  var k=1;	//记录序号
//				  $.each( data.Rows  , function(commentIndex, comment) {
//												
//				    tableTds.eq(i++).find('div').html((k++)+"<input type='checkbox' id='checkgroup' name='checkgroup' value='"+comment['houseId']+"'/>");
//					if(parent.document.getElementById("frame.housepageType").value=="all")
//					{
//						tableTds.eq(i++).find('div').html(comment['company']);
//						tableTds.eq(i++).find('div').html(comment['project']);
//					}
//					tableTds.eq(i++).find('div').html(comment['houseNum']);
//					tableTds.eq(i++).find('div').html(comment['houseArea']);
//					
//					tableTds.eq(i++).find('div').html(comment['condoFeeRate']);
//					
//					tableTds.eq(i++).find('div').html(comment['isempty']);
//					var strhtml="<a href=\"#\" onclick=\"openEditHouse($(this).next().html());\">编辑</a><span style=\"display:none;width:1px\">"+comment['houseId']+"</span><span style=\"display:inline-block;width:10px\"></span><a href=\"#\" onclick=\"deleteHouse($(this).parent().parent().parent(),$(this).prev().prev().html());\">删除</a>";
//					
//					tableTds.eq(i++).find('div').html(strhtml);
//
//					  });
//					  $('#now_page').html(data.currentPage);
//					  $('#total_page').html(data.pagesCount);
//					  $('#total_record').html(data.rowsCount);
//					  selectNone();
        }	
function getSecondInfo()
{
	 var select1Value=document.getElementById("projectId").value;
	 $.ajax({
	  type: "POST",
	  url: "getBuildingByProject?projectId="+select1Value,
	  dataType: "json",
	  success : function(data){
		      var selector=$('#buildingId'); 
			  var s=selector.find('option').remove();
			  for(i=1;i<=10;i++){
			     s.eq(i).remove();
			  }
			  $.each( data.Rows , function(commentIndex, comment) {
                   selector.append('<option value="'+comment['builId']+'">'+comment['builNum']+'</option>');
			  });
			  
	  }
	});
}
function selectAll(){
	$('input[type="checkbox"][name="checkgroup"]').attr("checked",true);
}
function selectOpposite(){
	var allchecks=$('input[type="checkbox"][name="checkgroup"]');
	for(i=0;i<allchecks.length;i++)
	{
		onecheck=allchecks.eq(i);
		if(onecheck.attr("checked")=="checked"){
			onecheck.attr("checked",false);
		}
		else
		{
			onecheck.attr("checked",true);
		}
	}
}
function selectNone(){
	$('input[type="checkbox"][name="checkgroup"]').attr("checked",false);
}
function deleteSelected(the){
	var boxes=$('input[type="checkbox"][name="checkgroup"]');
	alert(boxes.length);
    var houseIds = new Array();  

    i=0;
    $('input[type="checkbox"][name="checkgroup"]').each(function(){
    	
    	if($(this).attr("checked")=="checked")
        {
    		houseIds[i++]=$(this).value;
    		//alert($(this).attr("value"));
        }
    });
    the.href="http://deleteHousesByIds?houseIds="+houseIds;
    //alert(groupTypeId.length);
}

