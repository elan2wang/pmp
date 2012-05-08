// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	  $('#project_data').html(AddTds(20,5));
	  $('#projectlist').flexigrid({colModel: [
             { display: '序号',  width: 40,  align: 'center' },
             { display: '姓名', width: 200, align: 'center' },
			 { display: '联系方式', width: 200, align: 'center' },
             { display: '地址', width: 200,align: 'center' },
             { display: '操作',  width: 200, align: 'center' }
             ],height:305}
	  );
	  PageDownOrUp(0);
	});
function openAddNewOwner(){

	$('#newOwner').window('open');
}
function closeAddNewOwner(){
	$('#newOwner').window('close');
}
function openEditOwner(id){
	alert(id);

	$('#editOwner').window({href:'get_owner?ownerId='+id});

	$('#editOwner').window('open');
	owner_edit_init();

}
function closeEditOwner(){
    $('#editOwner').window('close');
}




function deleteOwner(obj,ownerId){
	alert("进入删除方法");
	alert(ownerId);
	if(!confirm("您将删除与该业主有关的所有信息,确定删除吗?"))
	{
		return;
	}
	$.ajax({
		  type: "POST",
		  url: 'delete_owner?ownerId='+ownerId,
		  dataType: "json",
		  success : function(data){
//			  if((data.success).equals("")){
			  obj.hide();
//			  }
		  }
	   });
}
function PageDownOrUp(flag){
//    alert(flag);
    var nowpage=parseInt(document.getElementById("now_page").innerHTML);
	var pagerow=parseInt(document.getElementById("page_row").options[document.getElementById("page_row").selectedIndex].text);
	var gopage=document.getElementById("go_page").value;
	var totalpage=parseInt(document.getElementById("total_page").innerHTML);
	var urlstr="";
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
//	alert("nowpage="+nowpage);
	urlstr="owner_list?currentPage="+nowpage+"&pageSize="+pagerow;
    $.ajax({
	  type: "POST",
	  url: urlstr,
	  dataType: "json",
	  success : function(data){
			  var i=0;
              $('#project_data').find('tr').hide();
              var tableTds=$('#project_data').find('td');
              tableTds.find('div').css('text-align','center');
			  for(i=0;i<pagerow;i++)
			  {$('#project_data').find('tr').eq(i).show();}
			  i=0;
			  tableTds.find('div').html("");
			  var k=1;	//记录序号
			  $.each( data.Rows  , function(commentIndex, comment) {
											
				tableTds.eq(i++).find('div').html(k++);
				tableTds.eq(i++).find('div').html(comment['ownerName']);
				tableTds.eq(i++).find('div').html(comment['mobile']);
				tableTds.eq(i++).find('div').html(comment["ownerDesc"]);
				eval("var a"+i+"="+comment['ownerId']+";");
				
				var strhtml="<a href=\"#\" onclick=\"openEditOwner($(this).next().html());\">编辑</a>" +
						"<span style=\"display:none;width:10px\">"+comment['ownerId']+"</span>" +
								"<span style=\"display:inline-block;width:10px\"></span>" +
								"<a href=\"#\" onclick=\"deleteOwner($(this).parent().parent().parent(),$(this).prev().prev().html())\">删除</a>";
				tableTds.eq(i++).find('div').html(strhtml);
			  });
			  $('#now_page').html(data.currentPage);
			  $('#total_page').html(data.pagesCount);
			  $('#total_record').html(data.rowsCount);
			  
	  }
	}); 
}	
function getSecondInfo()
{
	alert("进入getSecondInfo on change 方法");
	var project = document.getElementById("projectId");
	var index=project.selectedIndex;
	alert(index);
	var projectName = project.options[index].text;
	alert(projectName);
	//alert(projectName);
	//document.getElementById("projectName").value=projectName;
	
	 var select1Value=document.getElementById("projectId").value;
	 alert(select1Value);
	 $.ajax({
	  type: "POST",
	  url: "getBuildingByProject?projectId="+select1Value,
	  dataType: "json",
	  success : function(data){
		      var selector=$('#buildingId'); 
			  var s=selector.find('option');
			  for(i=1;i<s.length;i++){
			     s.eq(i).remove();
			  }
			  $.each( data.Rows , function(commentIndex, comment) {
                   selector.append('<option value="'+comment['builId']+'">'+comment['builNum']+'</option>');
			  });
			  
	  }
	});
}
function getHouseInfo(){

	var house = document.getElementById("houseId");
	var index=house.selectedIndex;
	var houseNum = house.options[index].text;
	alert(houseNum);
	document.getElementById("houseNum").value=houseNum;
}

function getAllHouse(){
	alert("进入getAllHouse on change 方法");
	var building = document.getElementById("buildingId");
	var index=building.selectedIndex;
	alert(index);
	var buildingName = building.options[index].text;
	alert(buildingName);
	//document.getElementById("buildingName").value=buildingName;
	var buildingId = document.getElementById("buildingId").value;
	alert(buildingId);
	$.ajax({
		type: "POST",
		url: "getAllHouseNum?buildingId="+buildingId,
		dataType:"json",
		success:function(data){
			var houseId=$('#houseId');
			var option = houseId.find('option');
			alert(option.length);
			for(i=1;i<=option.length;i++){
				option.eq(i).remove();
			  }
			$.each(data.Rows,function(commentIndex,comment){
				houseId.append('<option value="'+comment['houseId']+'">'+comment['houseNum']+'</option>');
			});
		}
	});
}
