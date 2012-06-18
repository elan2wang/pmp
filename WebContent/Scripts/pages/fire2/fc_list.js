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

//setInterval("makeRequest()",5000);
var deviceNumList=new Array();
function makeRequest(){
	 $.ajax({
		    type: "POST",
			url: "fire/getFireInfos",
			cache:false,
			dataType: "json",
			success : function(data){
				deviceNumList.length=0;
				$("#alarm_data").html(" ");
				$("#abnormal_data").html(" ");//先初始化
				devices="";
				zoneID="";
				time="";
				$.each(data.callFireInfos, function( i,content ){
					devices=content["deviceNumber"];
					zoneID=content["zoneId"];
					time=content["receiveTime"];
					var zone=new Zone(content["zoneId"],content["deviceNumber"],'call');
					setDeviceNum(zone);zone=null;
					 $("#alarm_data").append("<p id="+devices+"><a  target='fc_device' href='toZoneView?zoneId="+zoneID+"' onclick='setDeviceNum("+'"'+devices+'"'+");'>警报数据:  场地ID "+zoneID+"  设备ID "+devices+" 时间："+time+"</a></p>");
					});
				$.each(data.warnFireInfos, function( i,content ){
					devices=content["deviceNumber"];
					zoneID=content["zoneId"];
					time=content["receiveTime"];
					var zone=new Zone(content["zoneId"],content["deviceNumber"],'warn');
					setDeviceNum(zone);zone=null;
					$("#abnormal_data").append("<p id="+devices+"><a  target='fc_device' href='toZoneView?zoneId="+zoneID+"' onclick='setDeviceNum("+'"'+devices+'"'+")'>异常数据:  场地ID "+zoneID+"  设备ID "+devices+"  时间："+time+"</a></p>");
				});
//				frame.length=document.getElementById("fc_device").contentWindow.thisList.length;
//				frame.zoneID=document.getElementById("fc_device").contentWindow.getElementById("zoneID").value;
//				length=getLengthByZoneId(frame.zoneID,data.callFireInfos);	
				if($("#alarm").attr("src")==""&&data.callFireInfos.length>0)
				{
					$("#alarm").attr("src","../fireConfig/FIRE.WAV");
				}else if(data.callFireInfos==0){
					$("#alarm").attr("src","");
				}
				
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
function setDeviceNum(zone){
	deviceNumList.push(zone);

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
//根据zoneID找到相应的数组长度
function getLengthByZoneId(zoneId,List){
	  var thisList=new Array();
	  for(var i=0;i<List.length;i++)
	  {
		  if(List[i].ID==zoneId)
		  {
			  thisList.push(List[i].deviceID);
		  }
	  }
	  //alert(thisList.length);
	  return thisList.length;
}