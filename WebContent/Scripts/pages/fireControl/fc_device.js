$(function(){
	//Blink();
	//makeRequest();
	//var obj=findByNumber("995001");
	//Blink(obj);
	createFireAlarm("995001");
});
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
//把装置部署的背景图片上
function list_divice(xml){
	//alert("come in");
	var divCon=$('#devicelist');
	var zone=$(xml).find("zone");
	var devideArr=zone.find("device");
	var deviceNum=devideArr.length;
	
	//alert(encodeURI("地下室1.bmp"));
	url=encodeURI("地下室1.bmp");
	divCon.append('<img  src="../fireConfig/basement1.bmp"  border="0"/>');
	//divCon.append('<img  src='+zone.attr("picpath").toString()+'  border="0"/>');
	var imageid='';
	for(var i=0;i<deviceNum;i++)
	{   
	    var thisdevice=devideArr.eq(i);
		//alert();
		imageurl=thisdevice.attr("imageid").toString();
		imgid=thisdevice.attr("devicenumber").toString();
		if(imageurl.length<=1){imageurl='0'+imageurl;}
		divCon.append('<div class="devicePosi" id='+imgid+'><a href="http://www.baidu.com/" target="_blank" title="信息"><img src="../fireConfig/DevIco/'+imageurl+'.ico" border="0" id='+imgid+'></a></div>');
		
		thisdevice=null;
	}

	$('li div').each(function(i)
	{
		 //alert("33");
		 createRightMenu($(this).attr("id"));
		 var thisdevice2=devideArr.eq(i);
		 $(this).find('img').css("height",thisdevice2.attr("height")+"px");
	     $(this).find('img').css("width",thisdevice2.attr("width")+"px");
		 this.style.left=thisdevice2.attr("left")+"px";
		 this.style.top=thisdevice2.attr("top")+"px";
		 thisdevice2=null;
	});
}

//向服务器请求异常数据
 function makeRequest(){
	 $.ajax({
		    type: "GET",
			url: "../fireConfig/user.txt",
			cache:false,
			dataType: "json",
			success : function(data){
				$.each(data.rows, function( i,row ){
					$("#myajax").append(row["id"]);
					if(i==2)
					{
						var obj=findByNumber("995001");
						Blink(obj);
					}
				});
				setTimeout("makeRequest()", 10000);
			},
			error:function(){
				alert("error");
				setTimeout("makeRequest()", 10000);
			}
		});
 }
 //切换显示与否  实现闪烁功能
 var t;
 function Blink(obj){
	 if(!obj)
		 return;
  if(obj.css("visibility") == "visible")
	  obj.css("visibility" , "hidden");
  else
	  obj.css("visibility" ,"visible");
  t=setTimeout( _Blink(obj),300);
 }
 //setTimeout 方法不能传递参数  写这个方法  以弥补这个缺陷
 function  _Blink(obj){
	 return function(){
		 Blink(obj);
	 }
 }
//停止闪烁的方法
  function stopBlink(obj)
  {
	 obj.css("visibility" ,"visible");
     alert("stop");
     clearTimeout(t);
  }
  function stopBlink()
  {
	  obj=$('li div').eq(0);
	 obj.css("visibility" ,"visible");
     alert("stop");
     clearTimeout(t);
  }
  
 //根据number查找div
  function findByNumber(deviceNumber){
	  var obj;
	  $('li div').each(function(){
		  if(($(this).attr("id")).toString()==deviceNumber.toString()){
			  alert("1");
			  obj=$(this);
		  }
	  });
	  return obj;
  }
  
  //生成右键菜单
  function createRightMenu(id){
	  $('#'+id).RightMenu('myMenu',{
		   menuList:[
		       {menuName:"关闭警报",clickEvent:"stopFireAlarm("+id+")"}
		   ]
	  });
  }
  //关闭警报的方法
  function stopFireAlarm(id){
	  ("s"+id).src='';
	  stopBlink(findByNumber(id));
	  
  }
  //添加警报文件
  function createFireAlarm(id){
	  id="s"+id;
	  $("#head").append('<bgsound id='+id+'   src= "../fireConfig/FIRE.WAV"   loop=-1   volume=4>');
  }