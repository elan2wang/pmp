// JavaScript Document
$(function(){
	$('#projectlist').flexigrid({
	     url:"loadProjectList",
	     dataType:"json",
	     colModel: [
	         { display: '所属物业',name:'company.comName', width: Width*0.15, sortable:true, align: 'center' },
             { display: '项目名称',name:'proName', width: Width*0.15, sortable:true, align: 'center' },
			 { display: '所属地区',name:'proDistrict', width: Width*0.1, sortable:true, align: 'center' },
			 { display: '所属街道',name:'proStreet', width: Width*0.1, sortable:true, align: 'center' },
			 { display: '项目规模', name:'proHouseCount',width: Width*0.1, sortable:true, align: 'center' },
			 { display: '项目类型',name:'proType', width: Width*0.1, sortable:true, align: 'center' },
			 { display: '详细地址',name:'proAddress', width: Width*0.22, sortable:true, align: 'center',hide:'true'  },
			 { display: '交付时间',name:'deliveryTime', width: Width*0.1, sortable:true, align: 'center',hide:'true'  },
			 { display: '项目备注', name:'proDesc',width: Width*0.22, sortable:true, align: 'center' ,hide:'true'},
			 { display: '启用消控',name:'fireEnabled', width: Width*0.1, sortable:true, align: 'center' ,hide:'true'},
			 { display: '是否启用', name:'enabled',width: Width*0.1, sortable:true, align: 'center' ,hide:'true'}
         ],
         buttons : [
	       	 {name: '新增项目', bclass: 'add', onpress : addProject},
	       	 {name: '导入项目', bclass: 'import', onpress : importProject},
	       	 {separator: true}
		 ],
		 searchitems:[
          	 { display: '项目名称', name: 'proName', isdefault:true },
          	 { display: '所属地区', name: 'proDistrict', isdefault:false },
          	 { display: '所属街道', name: 'proStreet', isdefault:false }
         ],
         height:Height*0.88,
         showSearch:true,
         showcheckbox:true,
         usepager: true,
 		 useRp: true,
 		 rp: 15,
 		 operation:true,
		 operationcontent:'<a href="javascript:void(0)" onclick="openEditProject($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"parent.selectBuildTab($(this).parent().parent().parent(),$(this).parent().parent().parent(),$(this).parent().parent().parent())\">楼宇清单</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"deleteProject($(this).parent().parent().parent(),$(this).parent().parent().parent());\">删除</a>',
		 operationWidth: Width*0.22
    });
});

function importProject(){
	openAddWindow('#proImport');
}
		
function addProject(){
	$('#newPro').window('open');
}

function openEditProject(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getProject?proId='+id;
	openEditWindow("#editPro",url);
}

function closeEditProject(){
	$('#editPro').window('close');
}

function deleteProject(obj,objid){
	var id = parseInt(objid.attr("id").substr(3));
	if(!confirm("您将删除该项目以及关联楼宇、房产、业主、物业费信息,确定删除吗?"))return;
	$.ajax({
		type: "POST",
	    url: "deleteProject",
	    data: [{name:'proId',value:id}],
	    dataType: "json",
	    success : function(data){
	    	alert(data.msg);
		    obj.hide();
	    }
	});
}

function getStreets(street)
{	
	var objppd=document.getElementById("project.proDistrict");
	var area;
	for(var i=0;i <objppd.options.length;i++){
		if(objppd.options[i].selected){
			area=objppd.options[i].text.replace(/^\s*/, "").replace(/\s*$/,"");
			break;
	    } 
	}
	var ppS=document.getElementById("project.proStreet");
	ppS.innerHTML="";
	if(!street){
		ppS.add(new Option("请选择街道",""));
		$.ajax({
			type: "GET",
			url: "../xmls/areas.xml",
			dataType: 'xml',
			success : function(xml){
				$(xml).find("area").each(function() {
					var name=$(this).children("name").text(); 
					if(name==area){
					  $(this).find("street").each(function(){							  		
						  var streetName = $(this).text();	
						  ppS.add(new Option(streetName,streetName));
					  });
				  }					
			  });
				UpdateSelectedItem(ppS,street);
			}
		});
	}
}
