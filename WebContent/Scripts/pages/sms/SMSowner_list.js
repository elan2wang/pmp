$(function(){
  $('#SMSuserlist').flexigrid({
	  url: 'load_owner_list',
	  dataType: 'json',
	  colModel: [
         { display: '序号', name:'num', width: 20,  sortable:true, align: 'center' },
         { display: '业主', name:'owner', width: 50, sortable:true, align: 'center' },
		 { display: '电话', name:'phone', width: 100, sortable:true, align: 'center' },
		 { display: '小区', name:'project', width: 100, sortable:true, align: 'center' },
         { display: '房号', name:'houseNum', width: 100, sortable:true, align: 'center' }
         ],
      height:305
  });
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