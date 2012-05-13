//$(function(){
//	  $('#SMSuserlist').flexigrid({colModel: [
//             { display: '序号',  width: 20,  align: 'center',checkbox:true },
//             { display: '姓名', width: 50, align: 'center' },
//			 { display: '联系方式', width: 100, align: 'center' },
//             { display: '所在小区', width: 100,align: 'center' },
//			 { display: '所属公司', width: 100, align: 'center' },
//			 { display: '职位', width: 50, align: 'center' }
//             ],height:280});
//	});
function init()
{
	alert("ready");
	var project = document.getElementById("projectId");
	project.options[1].selected = true;//将第一个小区设为默认的
	project.onchange();
}
function flexGrid(url)
{
	alert("url:"+url);
	var option={
			  url: url,
			  dataType: 'json',
			  colModel: [
		          { display: '序号',name:'username',  width: 20,  align: 'center',checkbox:true },
		          { display: '姓名',name:'password', width: 50, align: 'center' },
		          { display: '联系方式',name:'realname', width: 100, align: 'center' },
		          { display: '所在小区',name:'mobile', width: 100,align: 'center' },
		          { display: '所属公司', name:'identify',width: 100, align: 'center' },
		          { display: '职位',name:'userDesc', width: 50, align: 'center' }
		         ],
		         searchitems : [
					       		{display: '姓名', name : 'password', isdefault: true}
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
				nomsg: '找不到符合条件的用户信息！', 
				showcheckbox:true,
				operation:true,
				operationcontent:'<a href="javascript:void(0)">修改</a>',
			    operationWidth:Width*0.1	
	};
	alert(option.url);
	//alert(option);
	option.url=url;
	 $("#SMSuserlist").flexigrid(option);
     $("#SMSuserlist").flexOptions(option).flexReload();  
     
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

//选择小区的onchange事件触发函数
function ProjectChanged()
{
	alert("in user  ProjectChanged");
	var project = document.getElementById("projectId");	
	 var select1Value=document.getElementById("projectId").value;
	 alert("select1Value:"+select1Value);
	 var url='load_user_list?proId='+select1Value;
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