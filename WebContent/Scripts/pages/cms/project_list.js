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
     
     
	 // $('#project_data').html(AddTds(20,10));
	  $('#projectlist').flexigrid({
		     url:"project_list",
		     dataType:"json",
		     colModel: [
             { display: '项目名称',name:'proName', width: Width*0.22, align: 'center' },
			 { display: '项目地址',name:'proAddress', width: Width*0.22, align: 'center' },
             { display: '所属物业公司',name:'company', width: Width*0.22,align: 'center' },
			 { display: '交付时间',name:'deliveryTime', width: Width*0.22, align: 'center' },
			 { display: '项目规模', name:'proHouseCount',width: Width*0.22, align: 'center',hide:'true' },
			 { display: '项目备注', name:'proDesc',width: Width*0.22, align: 'center' ,hide:'true'},
			 { display: '项目类型',name:'proType', width: Width*0.22, align: 'center' ,hide:'true'},
			 { display: '启用消控',name:'fireEnabled', width: Width*0.22, align: 'center' ,hide:'true'},
			 { display: '是否启用', name:'enabled',width: Width*0.22, align: 'center' ,hide:'true'}
             ],height:305,
             showcheckbox:true,
             usepager: true,
     		 useRp: true,
     		 rp: 15,
     		 operation:true,
			operationcontent:'<a href="javascript:void(0)" onclick="openEditProject($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"selectBuildTab($(this).parent().parent().parent(),$(this).parent().parent().parent(),$(this).parent().parent().parent())\">楼宇设置</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"deleteProject($(this).parent().parent().parent(),$(this).prev().prev().prev().prev().html());\">删除</a>',
			operationWidth: Width*0.22});
	//  document.getElementById("searchState").value="0";//搜索状态值  初始化
	 // PageDownOrUp(0);

	});
function openAddNewProject(){

			$('#newPro').window('open');
		}
function closeAddNewProject(){
			$('#newPro').window('close');
		}




function openEditProject(obj){
	//alert(obj.attr("id").substr(3));
	var id=parseInt(obj.attr("id").substr(3));
	//alert("fad");
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
		
function selectBuildTab(objproject,objcompany,objid){
	var project = objproject.find('td').eq(1).find('div').html();
	var company = objcompany.find('td').eq(3).find('div').html();
	alert("project:"+project);
	alert("company:"+company);
	var id=parseInt(objid.attr("id").substr(3));
	alert("id:"+id);
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
