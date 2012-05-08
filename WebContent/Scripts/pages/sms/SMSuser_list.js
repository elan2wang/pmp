$(function(){
	  $('#SMSuserlist').flexigrid({colModel: [
             { display: '序号',  width: 20,  align: 'center',checkbox:true },
             { display: '姓名', width: 50, align: 'center' },
			 { display: '联系方式', width: 100, align: 'center' },
             { display: '所在小区', width: 100,align: 'center' },
			 { display: '所属公司', width: 100, align: 'center' },
			 { display: '职位', width: 50, align: 'center' }
             ],height:280});
	});
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