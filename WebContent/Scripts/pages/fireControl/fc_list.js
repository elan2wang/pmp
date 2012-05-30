/**
 * author: Chrussy Ge
 * email： chrussyge@gmail.com
 * create:   2012-5-22
 * 
 * this script is used by the cf_house_list.jsp
 */ 
$(function(){
	$(".content .innercontent").eq(0).show();
	$("#right_main").css("display","block");
});

setInterval("makeRequest()",5000);
var deviceNumList=new Array();
function makeRequest(){
	 $.ajax({
		    type: "POST",
			url: "fire/getFireInfos",
			cache:false,
			dataType: "json",
			success : function(data){
				$.each(data.fireData, function( i,content ){
					  zoneID=content["zone_ID"];
					  var devices="";
					  $.each(content["info"],function(j,inner){
						  devices+=inner["device_ID"]+" ";
					  });
					  if(content["type"]=="001"){
							$("#alarm_data").append("<p><a  target='fc_device' href='toZoneView?zone.zoneId="+zoneID+"' onclick='setDeviceNum("+'"'+devices+'"'+");'>警报数据:  场地ID "+zoneID+"  设备ID "+devices+"</a></p>");
					  }else{
							$("#abnormal_data").append("<p><a  target='fc_device' href='toZoneView?zone.zoneId="+zoneID+"' onclick='setDeviceNum("+'"'+devices+'"'+")'>异常数据:  场地ID "+zoneID+"  设备ID "+devices+"</a></p>");
					  }
					});
			},
			error:function(){
				alert("error");

			}
		});
}


//操作异常数据和警报数据
function operation(data){
	
}
//设置页面devicelist的值
function setDeviceNum(str){
	deviceNumList.length=0;
	strs=str.split(" ");
	for(var i=0;i<strs.length-1;i++)
	{
		deviceNumList.push(strs[i]);
	}

}


//根据id找fire对象
function findByFireId(id,objList){
	alert(objList.length);
	  for(var i=0;i<objList.length;i++)
	  {
		  if(objList[i].ID==id){
			  alert(objList[i].TimerID);
			  return objList[i];
		  }
	  }
	  return null;
}