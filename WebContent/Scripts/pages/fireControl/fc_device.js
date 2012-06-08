
//alert($("#configUrl").val());

var zoneImgUrl=parent.zoneImgUrlTmp;
var zoneConfigUrl=parent.zoneConfigUrlTmp;
var zoneId=parent.zoneIdTmp;

//alert(zoneImgUrl+":"+zoneConfigUrl);

load_device("../"+zoneConfigUrl);

var thisList=new Array();
//load xml配置文件
function load_device(url){
	$.ajax({
		type: "GET",
		url: url,
		cache:false,
		dataType: "xml",
		success : function(xml){
			var pagedata=$(xml).find("zone");
			//alert(pagedata.attr("picpath"));
			list_divice(xml);
		}
	});
}
//把装置部署到背景图片上
function list_divice(xml){
	//alert("come in");
	var divCon=$('#devicelist');
	var zone=$(xml).find("zone");
	var devideArr=zone.find("device");
	var deviceNum=devideArr.length;


	divCon.append('<img  src="../'+zoneImgUrl+'"  border="0"/>');
	//divCon.append('<img  src='+zone.attr("picpath").toString()+'  border="0"/>');
	var imageid='';
	for(var i=0;i<deviceNum;i++)
	{   
	    var thisdevice=devideArr.eq(i);
		imageurl=thisdevice.attr("imageid").toString();
		imgid=thisdevice.attr("devicenumber").toString();
		var info=thisdevice.attr("devicetypename").toString();
		if(imageurl.length<=1){imageurl='0'+imageurl;}
		divCon.append('<div class="devicePosi" id='+imgid+'><a href="javascript:void(0);" title="'+info+'"><img src="../fireConfig/DevIco/'+imageurl+'.ico" border="0" id='+imgid+'></a></div>');
		
		thisdevice=null;
	}
    
	$('li div').each(function(i)
	{
		 var fire=new FireAlarm($(this).attr('id'),$(this));
		 objList.push(fire);
		 createRightMenu(fire);
		 //startAlarm(fire);
		 //startBlink(fire);
		 var thisdevice2=devideArr.eq(i);
		 $(this).find('img').css("height",thisdevice2.attr("height")+"px");
	     $(this).find('img').css("width",thisdevice2.attr("width")+"px");
		 this.style.left=thisdevice2.attr("left")+"px";
		 this.style.top=thisdevice2.attr("top")+"px";
		 thisdevice2=null;
		 fire=null;
	});
	
	//决定是否闪烁
	thisList.length=0;
	thisList=findByZoneId(zoneId,parent.deviceNumList);
	if(thisList&&thisList.length>0){
		for(var i=0;i<thisList.length;i++)
		{
			findByFireId(thisList[i]).startBlink();
		}
	}
}

//向服务器请求异常数据

 
 //切换显示与否  实现闪烁功能

 function Blink(obj){
  if(obj.find("img").css("visibility") == "visible")
	  obj.find("img").css("visibility" , "hidden");
  else
	  obj.find("img").css("visibility" ,"visible");
 }
 //setTimeout 方法不能传递参数  写这个方法  以弥补这个缺陷
 function  _Blink(obj){
	 return function(){
		 Blink(obj);
	 }
 }
 
 
 //开始闪烁
 function startBlink(fire){
	fire.startBlink();
 }
//停止闪烁的方法
  function stopBlink(fire){
	  fire.stopBlink();
  }
  
  //关闭警报的方法
  function stopAlarm(fire){
	  fire.stopFireAlarm();
  }
  
  //开始发出警报
  function startAlarm(fire){
	  fire.startFireAlarm();
  }
  
  
  
 //根据number查找div
  function findByNumber(deviceNumber){
	  var obj;
	  $('li div').each(function(){
		  if(($(this).attr("id")).toString()==deviceNumber.toString()){
			 // alert("1");
			  obj=$(this);
		  }
	  });
	  return obj;
  }
  //根据id找fire对象
  function findByFireId(id){
	  for(var i=0;i<objList.length;i++)
	  {
		  if(objList[i].ID==id){
			  return objList[i];
		  }
	  }
	  return null;
  }
  //根据zoneID找到相应的数组
  function findByZoneId(zoneId,parentList){
	  var thisList=new Array();
	  for(var i=0;i<parentList.length;i++)
	  {
		  if(parentList[i].ID==zoneId)
		  {
			  thisList.push(parentList[i].deviceID);
		  }
	  }
	  return thisList;
  }

  function _stopAlarm(id,clickId){
	  var fireObj=findByFireId(id);
      fireObj.stopBlink();
      updateFireInfoState(id,clickId);
  }
  
  function updateFireInfoState(deviceNum,state){
	  $.ajax(
				{
					type:'POST',
					url:'fire/updateFireInfoState',
					data:{'deviceNum':''+deviceNum+'','state':state},
					success:function(msg){
					},
					error:function(resut){
					    alert("处理警报错误!");
					}
				}
	   );
  }
  
  //生成右键菜单
  function createRightMenu(fire){
	  //alert(fire.ID);
	  $('#'+fire.ID).RightMenu("m"+fire.ID,{
		   menuList:[
		       {menuName:"关闭警报",clickEvent:"_stopAlarm("+fire.ID+",'2')"},
		       {menuName:"视为误报",clickEvent:"_stopAlarm("+fire.ID+",'3')"}
		   ]
	  });
  }
 //全屏显示功能
  function FullScreen(self){
	  
	  var frame=parent.document.getElementById("fc_device");
	  if(self.innerHTML=="全屏显示"){
	      frame.style.position= "absolute";
	      frame.style.left="0px";
	      frame.style.top="0px";
	      self.innerHTML="退出全屏";
	  }
	  else{
		  frame.style.position="";
	      frame.style.left="0px";
	      frame.style.top="0px";
	      self.innerHTML="全屏显示";
	  }
  }
