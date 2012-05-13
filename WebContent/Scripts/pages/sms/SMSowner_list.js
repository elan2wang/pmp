function init()
{
	alert("ready");
	var project = document.getElementById("projectId");
	project.options[1].selected = true;//将第一个小区设为默认的
	project.onchange();
}
function test(com, grid) {
	if (com == 'Delete') {
		//confirm('Delete ' + $('.trSelected', grid).length + ' items?');
		if ($(".trSelected").length > 0) {
		      if (confirm('是否删除 ' + $('.trSelected', grid).length + ' 条记录吗?')) {
		        var id = "";
		        for (var i = 0; i < $('.trSelected', grid).length; i++) {
		          if(i<$('.trSelected', grid).length-1){
		            id += "id=" + $('.trSelected').eq(i).attr('id').substring(3) + "&";
		          }else if(i==$('.trSelected', grid).length-1){
		            id += "id=" + $('.trSelected').eq(i).attr('id').substring(3);
		          }
		        }
		      }
		}else {
		      alert("请选择某行删除！");
	    }


	} else if (com == 'Add') {
		$('#newUser').window('open');
	}
}

function flexGrid(url)
{
	alert("url:"+url);
	var option={
			  url: url,
			  dataType: 'json',
			  colModel: [
		         { display: '业主', name:'ownerName', width: 50, sortable:true, align: 'center' },
				 { display: '电话', name:'mobile', width: 100, sortable:true, align: 'center' },
				 { display: '小区', name:'parkNum', width: 100, sortable:true, align: 'center' },
		         { display: '房号', name:'houseNum', width: 100, sortable:true, align: 'center' }
		         ],
		         searchitems : [
					       		{display: '业主', name : 'ownerName', isdefault: true}
					       		],
		         buttons : [
					       	{name: 'Add', bclass: 'add', onpress : test},
					       	{name: 'Delete', bclass: 'delete', onpress : test},
					       	{separator: true}
				],
		      usepager: true,
				useRp: true,
				rp: 15,
				showTableToggleBtn: true,
				width: "auto",
				height: "auto",
				nomsg: '找不到符合条件的业主信息！', 
				showcheckbox:true,
				operation:true,
				operationcontent:'<a href="javascript:void(0)">修改</a>',
			    operationWidth:Width*0.1	
	};
	alert(option.url);
	alert(option);
	option.url=url;
	 $("#SMSuserlist").flexigrid(option);
     $("#SMSuserlist").flexOptions(option).flexReload();  
//	$("#SMSuserlist").flexOptions(option).flexReload(); 
//	 $('#SMSuserlist').flexigrid({
//		  url: url,
//		  dataType: 'json',
//		  colModel: [
//	         { display: '业主', name:'owner', width: 50, sortable:true, align: 'center' },
//			 { display: '电话', name:'phone', width: 100, sortable:true, align: 'center' },
//			 { display: '小区', name:'project', width: 100, sortable:true, align: 'center' },
//	         { display: '房号', name:'houseNum', width: 100, sortable:true, align: 'center' }
//	         ],
//	         searchitems : [
//				       		{display: '业主', name : 'owner', isdefault: true}
//				       		],
//	         buttons : [
//				       	{name: 'Add', bclass: 'add', onpress : test},
//				       	{name: 'Delete', bclass: 'delete', onpress : test},
//				       	{separator: true}
//			],
//	      usepager: true,
//			useRp: true,
//			rp: 15,
//			showTableToggleBtn: true,
//			width: "auto",
//			height: "auto",
//			nomsg: '找不到符合条件的业主信息！', 
//			showcheckbox:true,
//			operation:true,
//			operationcontent:'<a href="javascript:void(0)">修改</a>',
//		    operationWidth:Width*0.1
//	  });
}
//选择小区的onchange事件触发函数
function ProjectChanged()
{
	alert("in ProjectChanged");
	var project = document.getElementById("projectId");	
	 var select1Value=document.getElementById("projectId").value;
	 alert("select1Value:"+select1Value);
	 $.ajax({
	  type: "POST",
	  url: "getBuildingByProject?projectId="+select1Value,
	  dataType: "json",
	  success : function(data){
		  		
		      var selector=$('#buildingId'); 
		      var house=$('#houseId'); 
			  var s=selector.find('option');
			  var s_house=house.find('option');
			  for(i=1;i<s.length;i++){
			     s.eq(i).remove();
			  }
			  for(i=1;i<s_house.length;i++){
				  s_house.eq(i).remove();
			  }
			  if(data)
		  	  {
				  $.each( data.Rows , function(commentIndex, comment) {				
						 selector.append('<option value="'+comment['builId']+'">'+comment['builNum']+'</option>');
				  });
		  	  }
			 
			  
	  }
	});
	 var url='load_owner_list?proId='+select1Value;
	 flexGrid(url);
	 
}


//选择楼号的onchange事件触发函数
function buildingChanged()
{
	alert("in buildingChanged");
	var building = document.getElementById("buildingId");
	var project = document.getElementById("projectId");	
	 var proId=project.value;
	var buildingId = building.value;
	alert(buildingId);
	var url='load_owner_list?builId='+buildingId+'&proId='+proId;
	 flexGrid(url);
}
function selectAll(){
	$('input[type="checkbox"]').attr("checked",true);
}
function selectOpposite(){
	var allchecks=$('input[type="checkbox"]');
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
	$('input[type="checkbox"]').attr("checked",false);
}
function submitForm()
{
	var allchecks=$('input[type="checkbox"][name="checkgroup"]');
	//alert("当前1"+allchecks.length);
	var arr=new Array(); 
    for (var i = 0; i < allchecks.length; i++)  
    {  
       if (allchecks[i].checked)  
       {  
	      arr[i] = allchecks[i].value;  
       }
     }
    //alert("当前"+arr.length);
	parent.parent.AddReceiverList(arr);
	closeFrame();
}
function closeFrame()
{
	parent.parent.closeReceiver();
}