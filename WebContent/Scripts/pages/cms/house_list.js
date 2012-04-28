// JavaScript Document
$(function(){
	if(parent.document.getElementById("frame.housepageType").value=="all"){
		$('#top_info').css("display","none");
		$('#top_info2').css("display","block");
	  $('#house_data').html(AddTds(20,8));
	  $('#houselist').flexigrid({colModel: [
             { display: '序号',  width: 40,  align: 'center' },
             { display: '当前小区', width: 200, align: 'center' },
			 { display: '所属物业', width: 200, align: 'center' },
             { display: '楼号', width: 100,align: 'center' },
             { display: '单元号', width: 100, align: 'center' },
			 { display: '楼层', width: 100, align: 'center' },
             { display: '房号', width: 100,align: 'center' },
             { display: '操作',  width: 200, align: 'center' }
             ],height:305});
	}
	else
	{
		$('#top_info').css("display","block");
		$('#top_info2').css("display","none");
		  $('#house_data').html(AddTds(20,5));
		  $('#houselist').flexigrid({colModel: [
	             { display: '序号',  width: 40,  align: 'center' },
	             { display: '单元号', width: 200, align: 'center' },
				 { display: '楼层', width: 200, align: 'center' },
	             { display: '房号', width: 200,align: 'center' },
	             { display: '操作',  width: 200, align: 'center' }
	             ],height:305});		
		
	}

});
function searchHouse(){
	document.getElementById("searchState").value="1";
	PageDownOrUp(0);
}
function openEditHouse(id)
{
	alert(id);
    $win=$('#editHouse').window({
        top:10,   
        left:($(window).width() - 450) * 0.5,
        href:'getHouseById?houseId='+id
	 });
	//alert(id);
	//$('#editHouse').window('open');
  $win.window('open');
}
function closeEditHouse(){
    $('#editHouse').window('close');
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
	        var nowpage=parseInt(document.getElementById("now_page").innerHTML);
			var pagerow=parseInt(document.getElementById("page_row").options[document.getElementById("page_row").selectedIndex].text);
			var gopage=document.getElementById("go_page").value;
			var totalpage=parseInt(document.getElementById("total_page").innerHTML);
			if(parent.document.getElementById("frame.housepageType").value=="all"){
		        housepageid=-1;
		    }
		    else{
			    pageid=parseInt(parent.document.getElementById("frame.housepageId").value);
		    }

			var urlstr="";
			//alert(nowpage+1);
			initIcon();
			switch(flag)
			{
				case 0://默认值
				   nowpage=1;
				   pagerow=10;
				   break;
				case 1:
				   nowpage=1;
				   break;
				case 2:
				   nowpage--;
				   break;
				case 3:
				   nowpage++;
				   break;
				case 4:
				   nowpage=totalpage;
				   break;
				case 5:
				   break;
				case 6:	
				   if(gopage==""){
			          alert("跳转页面不能为空！");
				      return;
				   }
				   if(isNaN(parseInt(gopage))){
					   return;
				   }
				   if(parseInt(gopage)>totalpage){
			          alert("跳转页面大于总页面数！");
				      return;
				   }
				   break;
			}
			changeIcon(nowpage,totalpage);
			if(document.getElementById("searchState").value=="1"){
				projectId=document.getElementById("projectId").value;
				buildingId=document.getElementById("buildingId").value;
				alert("projectId"+projectId);
				alert("buildingId"+buildingId);
				urlstr="getHouseByConditions?buildingId="+buildingId+"&projectId="+projectId+"&currentPage="+nowpage+"&pageSize="+pagerow;
				alert(urlstr);
			}
			else{
			  urlstr="house_list?currentPage="+nowpage+"&pageSize="+pagerow;
			  }
			//urlstr="house_list?currentPage="+nowpage+"&pageSize="+pagerow;
            $.ajax({
			  type: "POST",
			  url: urlstr,
			  dataType: "json",
			  success : function(data){
				  var i=0;
                  $('#house_data').find('tr').hide();
                  var tableTds=$('#house_data').find('td');
                  tableTds.find('div').css('text-align','center');
				  for(i=0;i<pagerow;i++)
				  {$('#house_data').find('tr').eq(i).show();}
				  i=0;
				  tableTds.find('div').html("");
				  var k=1;	//记录序号
				  $.each( data.Rows  , function(commentIndex, comment) {
												
				    tableTds.eq(i++).find('div').html((k++)+"<input type='checkbox' id='checkgroup' name='checkgroup' value='"+comment['houseId']+"'/>");
					if(parent.document.getElementById("frame.housepageType").value=="all")
					{
						tableTds.eq(i++).find('div').html(comment['houseUnit']);
						tableTds.eq(i++).find('div').html(comment['houseUnit']);
						tableTds.eq(i++).find('div').html(comment['houseUnit']);
					}
					tableTds.eq(i++).find('div').html(comment['houseUnit']);
					
					tableTds.eq(i++).find('div').html(comment['houseFloor']);
					
					tableTds.eq(i++).find('div').html(comment['houseNum']);
					var strhtml="<a href=\"#\" onclick=\"openEditHouse($(this).next().html());\">编辑</a><span style=\"display:none;width:1px\">"+comment['houseId']+"</span><span style=\"display:inline-block;width:10px\"></span><a href=\"#\" onclick=\"deleteHouse($(this).parent().parent().parent(),$(this).prev().prev().html());\">删除</a>";
					
					tableTds.eq(i++).find('div').html(strhtml);

					  });
					  $('#now_page').html(data.currentPage);
					  $('#total_page').html(data.pagesCount);
					  $('#total_record').html(data.rowsCount);
					  selectNone();
					  
			  }
			}); 
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

