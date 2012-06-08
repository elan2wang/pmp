// JavaScript Document
$(function(){
	var builid;
	var projectid;
	if(parent.document.getElementById("frame.pageType").value=="all"){	
		projectid = 0;
	}
	else{
		projectid = parseInt(parent.document.getElementById("frame.pageId").value);
    }
	if(parent.document.getElementById("frame.housepageType").value=="all"){
		builid = 0;
	}
	else{
		builid = parseInt(parent.document.getElementById("frame.housepageId").value);
	}

    $('#houselist').flexigrid({
	     url:"house_listBySessionHandler?buildingId="+builid+"&projectId="+projectid,
	     dataType:"json",
	  	 colModel: [
	  	     { display: '所属小区', name:'project',width: Width*0.2, sortable:true, align: 'center' },
             { display: '房号', name:'houseNum',width: Width*0.15, sortable:true, align: 'center' },
			 { display: '房屋面积',name:'houseArea', width: Width*0.2, sortable:true, align: 'center' },
             { display: '是否空置',name:'isempty', width: Width*0.15, sortable:true, align: 'center' }
         ], 
         buttons:[
              { name: '删除房屋', bclass: 'delete', onpress: deleteHouse },
              { separator: true }
      	 ],
		 searchitems:[
 		     { display: '房号', name: 'houseNum', isDefault:true },
 		     { display: '是否空置', name: 'isempty', isDefault:false }
 		 ],
         height:Height*0.84,
         showSearch:true,
         showcheckbox:true,
         usepager: true,
 		 useRp: true,
 		 rp: 15,
 		 operation:true,
 		 operationcontent:'<a href="javascript:void(0)" onclick="openEditHouse($(this).parent().parent().parent())">编辑</a>',
		 operationWidth: Width*0.2
     });		

});

function deleteHouse(){
	var rowid,idString="";
	$("#houselist td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择要修改的物业费记录");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	alert(idString);
	if(!confirm("您将删除该房屋的信息,确定删除吗?"))return;
	$.ajax({
	  type: "POST",
	  url: 'deleteHouse?idStr='+idString,
	  dataType: "json",
	  success : function(data){
		  alert("选中的房产删除成功");
		  window.location.href = window.location.href;
	  }
	});
	
}

function openEditHouse(obj)
{
	var id=parseInt(obj.attr("id").substr(3));	
	var url = 'getHouseById?houseId='+id;
	openEditWindow("#editHouse",url);
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

