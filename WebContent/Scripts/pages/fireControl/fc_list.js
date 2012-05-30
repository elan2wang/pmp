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


makeRequest();

function makeRequest(){
	 $.ajax({
		    type: "POST",
			url: "fire/getFireInfos",
			cache:false,
			dataType: "json",
			success : function(data){
				$.each(data.callFireInfos, function( i,row ){
					//alert(row.deviceNumber+":"+row.receiveInfo+":"+i);
					insertCallFireInfos(row.zoneId,row.deviceNumber);
				});
		        
				$.each(data.warnFireInfos, function( i,row ){
					//alert(row.deviceNumber+":"+row.zoneId+":"+i);
				});
				//setTimeout("makeRequest()", 2000);
			},
			error:function(){
				alert("error");
				//setTimeout("makeRequest()", 2000);
			}
		});
}

function CreateLink(deviceNumber,zoneId){
	
}

function insertCallFireInfos(zoneId,deviceNumber){
	    var callFire=document.getElementById("callFireInfo").innerHTML='&emsp;<a href="#" style="color: red;">消火栓11</a>&emsp;<a href="#">点型感温</a>&emsp;<a href="#">点型感烟</a>';
	    
	    var warnFire=document.getElementById("warnFireInfo").innerHTML;
	    
	    //alert(warnFire=="");
	    
	    //alert(callFire);
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