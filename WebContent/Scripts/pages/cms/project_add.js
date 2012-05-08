// JavaScript Document
<<<<<<< .merge_file_a03020
 	function createXHR()
 	{
 		//非IE浏览器创建XmlHttpRequest对象
 		var xmlhttp;
 		if(window.XMLHttpRequest)
 		{
 			xmlhttp=new XMLHttpRequest();
 		}
 		//IE浏览器创建XmlHttpRequest对象
 		else if(window.ActiveXObject)
 		{
 			try
 			{
 				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");    
 			}catch(e){
 				try{
 					xmlhttp=new ActiveXObject("msxml2.XMLHTTP");
   	  	  		}catch(ex){}
 			}
 		}
 		return xmlhttp;
 	}
 
 function getStreets()
	{		
		var xmlhttp=createXHR();
		if(!xmlhttp)
	    {
	     alert("创建xmlhttp对象异常！");
	     return false;
	    } 
		//判断该浏览器创建的xmlhttp对象是否支持overrideMimeType
		if(xmlhttp.overrideMimeType)
		{
			xmlhttp.overrideMimeType('text/xml');
		}
		try
		{
			xmlhttp.open("GET","../xmls/areas.xml",true);			
			xmlhttp.onreadystatechange=function()
			{
				if(xmlhttp.readyState==4&&xmlhttp.status==200)
				{
						xmlDOM=xmlhttp.responseXML;
						getDatas(xmlDOM);
				}
			};
			xmlhttp.setRequestHeader("If-Modified-Since","0");
			xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=UTF-8");
			xmlhttp.send();
		}catch(ex){	
			alert(ex.description||ex);
		}		
	}
 
=======
>>>>>>> .merge_file_a01544
 
   function to(page) {
        objP1 = document.getElementById("P1");
        objP2 = document.getElementById("P2");
        objTab1 = document.getElementById("Tab1");
        objTab2 = document.getElementById("Tab2");
        if (page == "P2") {
            objP1.style.display = "none";
            objP2.style.display = "block";
            objTab1.className = "ModuleTap";
            objTab2.className = "ModuleTapOn";
        }
        if (page == "P1") {
            objP1.style.display = "block";
            objP2.style.display = "none";
            objTab1.className = "ModuleTapOn";
            objTab2.className = "ModuleTap";
        }
    }
	
	
	function getDatas(xmlDOM)
	{
		var objppd=document.getElementById("proDistrict");
		var area;
		for(var i=0;i <objppd.options.length;i++){
			if(objppd.options[i].selected)
			{
				area=objppd.options[i].text.replace(/^\s*/, "").replace(/\s*$/,"");
				break;
		    } 
		}
		var ppS=document.getElementById("project.proStreet");
		var datas=xmlDOM.getElementsByTagName("area");
		for(var i=0;i<datas.length;++i)
		{
			if(datas[i].childNodes[0].childNodes[0].nodeValue.replace(/^\s*/, "").replace(/\s*$/,"")==area)
			{
				ppS.innerHTML="";
				ppS.add(new Option(datas[i].childNodes[1].childNodes[0].nodeValue,datas[i].childNodes[1].childNodes[0].nodeValue));
				ppS.add(new Option(datas[i].childNodes[2].childNodes[0].nodeValue,datas[i].childNodes[2].childNodes[0].nodeValue));
				ppS.add(new Option(datas[i].childNodes[3].childNodes[0].nodeValue,datas[i].childNodes[3].childNodes[0].nodeValue));
				ppS.add(new Option(datas[i].childNodes[4].childNodes[0].nodeValue,datas[i].childNodes[4].childNodes[0].nodeValue));
				break;
			}
		}
		
	}

	function FormCheck(){
		objfc1=document.getElementById("project.proName");
		objfc2=document.getElementById("project.proAddress");
		objfc3=document.getElementById("project.deliveryTime");
		if(objfc1.value==""){
			alert("小区名称不能为空");
			objfc1.focus();
			return (false);
		}
		if(objfc2.value==""){
			alert("小区地址不能为空");
			objfc2.focus();
			return (false);
		}
		if(objfc3.value==""){
			alert("建成时间不能为空");
			objfc3.focus();
			return (false);
		}
		document.getElementById("form").submit();
		window.parent.closeAddNewProject();
<<<<<<< .merge_file_a03020
	}
=======
	}
>>>>>>> .merge_file_a01544
