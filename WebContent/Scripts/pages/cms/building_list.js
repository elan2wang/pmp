// JavaScript Document
$(function(){
	
	if(parent.document.getElementById("frame.pageType").value=="all"){	
		$('#top_info').css("display","none");
		$('#top_info2').css("display","block");
		  $('#building_data').html(AddTds(20,13));
		  $('#buildinglist').flexigrid({colModel: [
	             { display: '序号',  width: 40,  align: 'center' },
	             { display: '楼号', width: 100, align: 'center' },
				 { display: '当前小区',  width: 200,  align: 'center' },
				 { display: '所属物业',  width: 200,  align: 'center' },
				 { display: '单元数', width: 100, align: 'center' },
	             { display: '楼层数', width: 100,align: 'center' },
				 { display: '单元层户数', width: 100, align: 'center' },
				 { display: '跳过楼层数', width: 100, align: 'center' },
				 { display: '楼宇类型', width: 100, align: 'center'},
				 { display: '物业费标准', width: 100, align: 'center'},
				 { display: '备注', width: 100, align: 'center' ,hide:'true'},
				 { display: '是否启用', width: 200, align: 'center' ,hide:'true'},
	             { display: '操作',  width: 200, align: 'center' }
	             ],height:305});
	   }
	   else{
		  $('#top_info').css("display","block");
		  $('#top_info2').css("display","none");
		  $('#building_data').html(AddTds(20,11));
		  $('#buildinglist').flexigrid({colModel: [
	             { display: '序号',  width: 40,  align: 'center' },
	             { display: '楼号', width: 100, align: 'center' },
				 { display: '单元数', width: 100, align: 'center' },
	             { display: '楼层数', width: 100,align: 'center' },
				 { display: '单元层户数', width: 100, align: 'center' },
				 { display: '跳过楼层数', width: 100, align: 'center' },
				 { display: '楼宇类型', width: 100, align: 'center'},
				 { display: '物业费标准', width: 100, align: 'center'},
				 { display: '备注', width: 100, align: 'center' ,hide:'true'},
				 { display: '是否启用', width: 200, align: 'center' ,hide:'true'},
	             { display: '操作',  width: 200, align: 'center' }
	             ],height:305});
	   }

});
function openAddNewBuild(){
	         $win=$('#newBuild').window({
                top:10,   
                left:($(window).width() - 450) * 0.5
			 });
	         
	         $win.window('open');
			//$('#newBuild').window('open');
		}
function closeAddNewBuild(){
			$('#newBuild').window('close');
		}
function openEditBuild(id){
	         $win=$('#editBuild').window({
                top:10,   
                left:($(window).width() - 450) * 0.5,
                href:'get_building?buildingId='+id
			 });
	         $win.window('open');
		}


function deleteBuilding(obj,id){
	alert("进入删除方法");
	alert("building"+id);
	if(!confirm("您将删除该楼宇相关的所有房屋的信息,确定删除吗?"))
	{
		return;
	}
	$.ajax({
		  type: "POST",
		  url: 'delete_building?buildingId='+id,
		  dataType: "json",
		  success : function(data){
//			  if((data.success).equals("")){
			  obj.hide();
//			  }
		  }
	   });
	
}
function closeEditBuild(){
	        $('#editBuild').window('close');
	    }
function setBuildingId(buidingId){
	document.getElementById("buildingId").value=buidingId;
}
function PageDownOrUp(flag){
//	        alert(flag);
	        var pageid;
            if(parent.document.getElementById("frame.pageType").value=="all"){
		        pageid=-1;
		    }
		    else{
			    pageid=parseInt(parent.document.getElementById("frame.pageId").value);
		    }
            alert(pageid);

	        var nowpage=parseInt(document.getElementById("now_page").innerHTML);
			var pagerow=parseInt(document.getElementById("page_row").options[document.getElementById("page_row").selectedIndex].text);
			var gopage=document.getElementById("go_page").value;
			var totalpage=parseInt(document.getElementById("total_page").innerHTML);
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
			urlstr="building_list?currentPage="+nowpage+"&pageSize="+pagerow+"&projectId="+pageid;
            $.ajax({
			  type: "POST",
			  url: urlstr,
			  dataType: "json",
			  success : function(data){
					  var i=0;
                      $('#building_data').find('tr').hide();
                      var tableTds=$('#building_data').find('td');
                      tableTds.find('div').css('text-align','center');
					  for(i=0;i<pagerow;i++)
					  {$('#building_data').find('tr').eq(i).show();}
					  i=0;
					  tableTds.find('div').html("");
					  var k=1;	//记录序号
					  $.each( data.Rows  , function(commentIndex, comment) {
													
						tableTds.eq(j=(i++)).find('div').html(k++);
						tableTds.eq(j=(i++)).find('div').html(comment['builNum']);
						
						if(parent.document.getElementById("frame.pageType").value=="all"){
							   tableTds.eq(j=(i++)).find('div').html(comment['project']);
							   tableTds.eq(j=(i++)).find('div').html(comment['builNum']);
							}
						
						tableTds.eq(j=(i++)).find('div').html(comment['unitCount']);
						
						tableTds.eq(j=(i++)).find('div').html(comment['floorCount']);
						
						tableTds.eq(j=(i++)).find('div').html(comment['housesPer']);
						
						tableTds.eq(j=(i++)).find('div').html(comment['skipFloor']);
						
						tableTds.eq(j=(i++)).find('div').html(comment['builType']);
						
						tableTds.eq(j=(i++)).find('div').html(comment['builType']);
						
						tableTds.eq(j=(i++)).find('div').html(comment['builDesc']);
						
						tableTds.eq(j=(i++)).find('div').html(comment['enabled']);
						//eval("var a"+i+"="+comment['builId']+";");
						//alert(eval('a'+i));
						var strhtml="<a href=\"#\" onclick=\"openEditBuild($(this).next().html());\">编辑</a><span style=\"display:none;width:1px\">"+comment['builId']+"</span><span style=\"display:inline-block;width:10px\"></span><a href=\"#\" onclick=\"parent.selectHouseTab()\">清单</a><span style=\"display:inline-block;width:10px\"></span><a href=\"#\" onclick=\"deleteBuilding($(this).parent().parent().parent(),$(this).prev().prev().prev().prev().html());\">删除</a>";
						//alert();

						tableTds.eq(j=(i++)).find('div').html(strhtml);
					  });
					  $('#now_page').html(data.currentPage);
					  $('#total_page').html(data.pagesCount);
					  $('#total_record').html(data.rowsCount);
			  }
			}); 
        }
