function grid(url){
	var config = {
		url: url,
    	dataType:"json",
    	colModel: [
            { display: '房号', name:'houseNum', width: Width*0.2, sortable:true, align: 'center' },
 		    { display: '业主', name:'ownerName', width: Width*0.2, sortable:true, align: 'center' },
 	        { display: '电话', name:'mobile', width: Width*0.4, sortable:true, align: 'center' }
 		],
 		searchitems : [
 		    { display: '房号', name : 'houseNum', isdefault: true },
 		    { display: '业主', name : 'ownerName', isdefault: false }
 		],
 		buttons: [
			{ name: '提交', bclass: 'add', onpress : submitForm},
			{ separator: true },      
			{ name: '取消', bclass: 'delete', onpress : closeFrame},
			{ separator: true },      
	    ],
	    height: Height*0.81,
 		showSearch:true,
 		showcheckbox:true,
 		usepager: true,
 		useRp: true,
 		rp: 15,
 		nomsg: '无符合条件的业主'
	};
	config.url=url;
	$('#SMSownerlist').flexigrid(config);
	$('#SMSownerlist').flexOptions(config).flexReload();
}

$(function(){
	var project=document.getElementById("projectId");
	project.options[1].selected = true;
	project.onchange();
});

function projectChanged()
{
	var select1Value=document.getElementById("projectId").value;
	$.ajax({
		type: "POST",
		url: "selectBuilding_ByPro?proId="+select1Value,
		dataType: "json",
		success: function(data){
			var selector=$('#buildingId'); 
			selector.find('option').remove();
			$.each( data.Rows , function(commentIndex, comment) {				
		        selector.append('<option value="'+comment['builId']+'">'+comment['builNum']+'</option>');
		    });
			var url = "load_owner_list?proId="+select1Value;
			grid(url);
		}
	});
}

function buildingChanged()
{
	var proId = document.getElementById("projectId").value;
	var builId = document.getElementById("buildingId").value;
	var url = "load_owner_list?proId="+proId+"&builId="+builId;
	grid(url);
}

function submitForm()
{
	var arr=new Array();
	var i = 0;
	$("#SMSownerlist td input:checked").each(function(){
		arr[i] = $(this).parent().parent().next().next().next().children().html();
		i++;
	});
	
	parent.parent.AddReceiverList(arr);
	closeFrame();
}
function closeFrame()
{
	parent.parent.closeReceiver();
}