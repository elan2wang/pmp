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
		     url:"project_listBySessionHandler",
		     dataType:"json",
		     colModel: [
             { display: '项目名称',name:'proName', width: Width*0.15, align: 'center' },
			 { display: '项目地址',name:'proAddress', width: Width*0.22, align: 'center' },
             { display: '所属物业公司',name:'company', width: Width*0.15,align: 'center' },
			 { display: '交付时间',name:'deliveryTime', width: Width*0.1, align: 'center' },
			 { display: '项目规模', name:'proHouseCount',width: Width*0.1, align: 'center',hide:'true' },
			 { display: '项目备注', name:'proDesc',width: Width*0.22, align: 'center' ,hide:'true'},
			 { display: '项目类型',name:'proType', width: Width*0.1, align: 'center' ,hide:'true'},
			 { display: '启用消控',name:'fireEnabled', width: Width*0.1, align: 'center' ,hide:'true'},
			 { display: '是否启用', name:'enabled',width: Width*0.1, align: 'center' ,hide:'true'}
             ],
             buttons : [
    			       	{name: '添加新项目', bclass: 'add', onpress : openAddNewProject},
    			       	{name: '导入', bclass: 'modify', onpress : proImport},
    			       	{separator: true}
    		],
    		searchitems:[
    		              	{ display: '项目名称', name: 'proName', isdefault:false },
    		              	{ display: '交付时间', name: 'deliveryTime', isdefault:false },
    		              	{ display: '项目地址', name: 'proAddress', isdefault:false },
    		              	{ display: '项目规模', name: 'proHouseCount', isdefault:true }
    		             ],
             height:Height*0.84,
             
             showcheckbox:true,
             usepager: true,
     		 useRp: true,
     		 rp: 15,
     		 operation:true,
			operationcontent:'<a href="javascript:void(0)" onclick="openEditProject($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"selectBuildTab($(this).parent().parent().parent(),$(this).parent().parent().parent(),$(this).parent().parent().parent())\">楼宇设置</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"deleteProject($(this).parent().parent().parent(),$(this).parent().parent().parent());\">删除</a>',
			operationWidth: Width*0.22});

	});

function proImport(){
	openAddWindow('#proImport');
}
		
function openAddNewProject(){

			$('#newPro').window('open');
		}
function closeAddNewProject(){
			$('#newPro').window('close');
		}




function openEditProject(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'get_project?projectId='+id;
	openEditWindow("#editPro",url);
		}
function closeEditProject(){
	        $('#editPro').window('close');
	    }
function deleteProject(obj,objid){
	var id = parseInt(objid.attr("id").substr(3));
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
	alert("in selectHouseTab");

	var id = parseInt(builidObj.attr("id").substr(3));	
	alert("buidlid:"+id);
			document.getElementById("frame.housepageType").value="one";
			//document.getElementById("frame.builNum").value=buildNum;
			document.getElementById("frame.housepageId").value=id;
			document.getElementById("houseFrame").src="house_list.jsp";
			$(".nav li").removeClass("active");
			$("#tab3").addClass('active');
			$(".content .innercontent").hide().eq(2).show();
		}
function getStreets(street)
{	
		var objppd=document.getElementById("proDistrict");
		var area;
		for(var i=0;i <objppd.options.length;i++){
			if(objppd.options[i].selected)
			{
				area=objppd.options[i].text.replace(/^\s*/, "").replace(/\s*$/,"");
				break;
		    } 
		}
		var ppS=document.getElementById("project.proStreet");
		ppS.innerHTML="";
		if(!street)
			ppS.add(new Option("请选择街道","请选择街道"));
			$.ajax({
				type: "GET",
				url: "../xmls/areas.xml",
				dataType: 'xml',
				success : function(xml){
					$(xml).find("area").each(function() {
						var name=$(this).children("name").text(); 
						if(name==area)
						{
						  $(this).find("street").each(function()
								  {							  		
									  var streetName = $(this).text();	
									  ppS.add(new Option(streetName,streetName));
								  }
						   );
					  }					
				  });
					UpdateSelectedItem(ppS,street);
				}
			});
}
