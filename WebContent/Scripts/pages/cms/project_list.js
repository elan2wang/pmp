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
     
     
	  $('#project_data').html(AddTds(20,10));
	  $('#projectlist').flexigrid({colModel: [
             { display: '序号',  width: 40,  align: 'center' },
             { display: '项目名称', width: 200, align: 'center' },
			 { display: '项目地址', width: 200, align: 'center' },
             { display: '所属物业公司', width: 200,align: 'center' },
			 { display: '交付时间', width: 200, align: 'center' },
			 { display: '项目规模', width: 200, align: 'center',hide:'true' },
			 { display: '项目备注', width: 200, align: 'center' ,hide:'true'},
			 { display: '项目类型', width: 200, align: 'center' ,hide:'true'},
			 { display: '是否启用', width: 200, align: 'center' ,hide:'true'},
             { display: '操作',  width: 200, align: 'center' }
             ],height:305});
	  document.getElementById("searchState").value="0";//搜索状态值  初始化
	  PageDownOrUp(0);

	});
function openAddNewProject(){

			$('#newPro').window('open');
		}
function closeAddNewProject(){
			$('#newPro').window('close');
		}
function openEditProject(id){
	$('#editPro').window({href:'get_project?projectId='+id});
	$('#editPro').window('open');
		}
function closeEditProject(){
	        $('#editPro').window('close');
	    }
function deleteProject(obj,id){
	alert("进入删除方法");
	alert(id);
	if(!confirm("您将删除该项目有关的楼宇及房屋所有的信息,确定删除吗?"))
	{
		return;
	}
	$.ajax({
		  type: "POST",
		  url: 'deleteProject?projectId='+id,
		  dataType: "json",
		  success : function(data){
//			  if((data.success).equals("")){
			  obj.hide();
//			  }
		  }
	   });
}
function searchProject(){
	document.getElementById("searchState").value="1";
	PageDownOrUp(0);
}
function PageDownOrUp(flag){
//	        alert(flag);
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
//			alert("nowpage="+nowpage);
			if(document.getElementById("searchState").value=="1"){
				district=document.getElementById("project.proDistrict").options[document.getElementById("project.proDistrict").selectedIndex].innerHTML;
				keyWord=document.getElementById("keyWord").value;
				urlstr="getProjectByDistrict?currentPage="+nowpage+"&pageSize="+pagerow+"&district="+district+"&keyWord="+keyWord;
			}
			else{
			  urlstr="project_list?currentPage="+nowpage+"&pageSize="+pagerow;
			  }
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
						tableTds.eq(i++).find('div').html(comment['proName']);
						tableTds.eq(i++).find('div').html(comment['proAddress']);
						tableTds.eq(i++).find('div').html(comment["company"]);
						tableTds.eq(i++).find('div').html(comment['deliveryTime']);
						tableTds.eq(i++).find('div').html(comment['proHouseCount']);
						tableTds.eq(i++).find('div').html(comment['proDesc']);
						tableTds.eq(i++).find('div').html(comment['proType']);
						tableTds.eq(i++).find('div').html(comment['enabled']);
						eval("var a"+i+"="+comment['proId']+";");
						
						var strhtml="<a href=\"#\" onclick=\"openEditProject($(this).next().html());\">编辑</a>" +
								"<span style=\"display:none;width:10px\">"+comment['proId']+"</span>" +
										"<span style=\"display:inline-block;width:10px\"></span>" +
										"<a href=\"#\" onclick=\"selectBuildTab($(this).parent().parent().parent().find('td').eq(1).find('div').html(),$(this).parent().parent().parent().find('td').eq(3).find('div').html(),$(this).prev().prev().html())\">楼宇设置</a>" +
										"<span style=\"display:inline-block;width:10px\"></span>" +
										"<a href=\"#\" onclick=\"deleteProject($(this).parent().parent().parent(),$(this).prev().prev().prev().prev().html());\">删除</a>";
						tableTds.eq(i++).find('div').html(strhtml);
					  });
					  $('#now_page').html(data.currentPage);
					  $('#total_page').html(data.pagesCount);
					  $('#total_record').html(data.rowsCount);
			  }
			}); 
        }	
		
		
        function selectBuildTab(project,company,id){
	        alert(id);
	        document.getElementById("frame.pageType").value="one";
	        document.getElementById("frame.pageId").value=id;
	        document.getElementById("frame.projectName").value=project;
	        document.getElementById("frame.company").value=company;
	        document.getElementById("buildingFrame").src="building_list.jsp";
			$(".nav li").removeClass("active");	
			$("#tab2").addClass('active');
			$(".content .innercontent").hide().eq(1).show();

        }

        function selectHouseTab(buildNum,id){
			document.getElementById("frame.housepageType").value="one";
			document.getElementById("frame.builNum").value=buildNum;
			document.getElementById("frame.housepageId").value=id;
			document.getElementById("houseFrame").src="house_list.jsp";
			$(".nav li").removeClass("active");
			$("#tab3").addClass('active');
			$(".content .innercontent").hide().eq(2).show();
		}
