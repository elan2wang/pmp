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

function Zone(zoneId,zoneName,zoneImgUrl,zoneConfigUrl) {
	this.zoneId=zoneId;
	this.zoneName=zoneName;
	this.zoneImgUrl=zoneImgUrl;
	this.zoneConfigUrl=zoneConfigUrl;
}

var zoneList=new Array();

loadAllZones();
//setLinkZone();

setInterval("makeRequest()",10000);
setInterval("setLinkZone()",10000);

makeRequest();
var deviceNumList=new Array();

var length=0;

function makeRequest(){
	 $.ajax({
		    type: "POST",
			url: "fire/getFireInfos",
			cache:false,
			dataType: "json",
			success : function(data){
				length=deviceNumList.length;
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
					var zone=new ZoneFireInfo(content["zoneId"],content["deviceNumber"],'call');
					setDeviceNum(zone);zone=null;
					 $("#alarm_data").append("<p id="+devices+"><a href='javascript:linkZone("+zoneID+");'>警报数据:  场地ID "+zoneID+"  设备ID "+devices+" 时间："+time+"</a></p>");
					});
				$.each(data.warnFireInfos, function( i,content ){
					devices=content["deviceNumber"];
					zoneID=content["zoneId"];
					time=content["receiveTime"];
					var zone=new ZoneFireInfo(content["zoneId"],content["deviceNumber"],'warn');
					setDeviceNum(zone);zone=null;
					$("#abnormal_data").append("<p id="+devices+"><a href='javascript:linkZone("+zoneID+");'>异常数据:  场地ID "+zoneID+"  设备ID "+devices+"  时间："+time+"</a></p>");
				});
				
				if($("#alarm").attr("src")==""&&data.callFireInfos.length>0)
				{
					$("#alarm").attr("src","../fireConfig/FIRE.WAV");
				}else if(data.callFireInfos==0){
					$("#alarm").attr("src","");
				}
				
				if(data.callFireInfos.length==0&&data.warnFireInfos.length==0){
					isSetLink=true;
				}else {
					isSetLink=false;
				}
				
				if(!(length==deviceNumList.length)){
					if(data.callFireInfos.length!=0){
						z=data.callFireInfos[0];
						zoneId=z.zoneId;
						linkZone(zoneId);
					}else{
						if(data.warnFireInfos.length!=0){
							z=data.warnFireInfos[0];
							zoneId=z.zoneId;
							linkZone(zoneId);
						}
					}
				}
				
				//if(data.callFireInfos.length!=length){
					//document.getElementById("fc_device").contentWindow.location.reload();
				//}
			},
			error:function(){
				alert("error");
			}
		});
}

var zoneImgUrlTmp="";
var zoneConfigUrlTmp="";
var zoneIdTmp="";

function linkZone(zoneId){
	for(i=0;i<zoneList.length;i++){
		if(zoneList[i].zoneId==zoneId){
			zoneImgUrlTmp=zoneList[i].zoneImgUrl;
			zoneConfigUrlTmp=zoneList[i].zoneConfigUrl;
			zoneIdTmp=zoneList[i].zoneId;
			break;
		}
	}
	
	var fc_device=document.getElementById("fc_device");
	fc_device.src="fc_device_list.jsp";
}

var n=0;

var isSetLink=true;

function setLinkZone(){
	var num=zoneList.length;
    if(n==num){
       n=0;
    }
    
    if(isSetLink==true){
        z=zoneList[n];
        //alert(num);
        //alert(n);
        //alert(z);
        //alert(z.zoneId);
    	zoneId=z.zoneId;
    	linkZone(zoneId);
    }
	n++;
}

function loadAllZones(){
	 $.ajax({
		    type: "POST",
			url: "fire/getAllZones",
			cache:false,
			dataType: "json",
			success : function(data){
				$.each(data.zones, function( i,content ){
					zoneId=content["zoneId"];
					zoneName=content["zoneName"];
					zoneImgUrl=content["zoneImgUrl"];
					zoneConfigUrl=content["zoneConfigUrl"];
					//alert(zoneId+":"+zoneImgUrl);
					zone=new Zone(zoneId,zoneName,zoneImgUrl,zoneConfigUrl);
					zoneList.push(zone);
			    });
				//alert(zoneList.length);
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