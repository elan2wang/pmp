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
function makeRequest(arr){
	alert("request");
	 $.ajax({
		    type: "GET",
			url: "../fireConfig/user.txt",
			cache:false,
			dataType: "json",
			success : function(data){
				$.each(data.rows, function( i,row ){
					
					if(i==2)
					{
						//alert(row["id"]);
						var fireobj2=findByFireId(row["id"],arr);
						fireobj2.showMessage();
						fireobj2.startFireAlarm();
						fireobj2.startBlink();
					}
				});
				setTimeout(_makeRequest(arr), 2000);
			},
			error:function(){
				alert("error");
				setTimeout(_makeRequest(arr), 2000);
			}
		});
}
function _makeRequest(arr){
	return function(){
		makeRequest(arr);
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