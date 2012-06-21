//公共函数

    //根据小区 获取楼宇信息
function getBuilding(){}
   

    //根据楼号 获取户信息
function getUnit(){
	
}

    
//-------------------------------------------------------

//=======================================================
//材料费用清单控制
function to(page){
	objP1=document.getElementById("P1");
	objP2=document.getElementById("P2");
	objTab1=document.getElementById("Tab1");
	objTab2=document.getElementById("Tab2");
	if(page=="P2"){
		objP1.style.display="none";
		objP2.style.display="block";
		objTab1.className="ModuleTap";
		objTab2.className="ModuleTapOn";		
	}
	if(page=="P1"){
		objP1.style.display="block";
		objP2.style.display="none";
		objTab1.className="ModuleTapOn";
		objTab2.className="ModuleTap";		
	}
}



function intRO(){
	document.getElementById("fdReportDate").readOnly=true;
	document.getElementById("fdApDate").readOnly=true;
	document.getElementById("fdFinDate").readOnly=true;
}
function intTime(){
	for(i=0;i<24;i++){
		document.write("<option value=\""+i+":00\">"+i+":00</option>\n");
		document.write("<option value=\""+i+":30\">"+i+":30</option>\n");
	}
}
//===========================================
//以下表单检测
function strim(str){
		return str.replace(/(^\s*)|(\s*$)/g,""); 
	}
function formchk(){
	obj1=document.getElementById("YZBuilding");
	obj2=document.getElementById("YZunit");
	obj3=document.getElementById("YZroom");
	obj4=document.getElementById("fdName");
	obj5=document.getElementById("fdPhone");
	obj6=document.getElementById("fdReportDate");
	obj7=document.getElementById("fdID");
	obj8=document.getElementById("fdApDate");
	obj9=document.getElementById("fdApTime");
	obj10=document.getElementById("ReprContent");
	obj11=document.getElementById("fdRPDetail");
	var isPhonePattern = /\d{3}-\d{8}|\d{4}-\d{7}/;
	var isMobilePattern = /^(13|15|18)[0-9]{9}$/;
	if(obj1.value=="请选择楼号"){
		alert("请选择楼号");
		return false;
	}
	if(obj2.value=="null"){
		alert("请选择单元");
		return false;
	}
	if(obj3.value=="null"){
		alert("请选择户号");
		return false;
	}
	if(strim(obj4.value)==""){
		alert("请输入业主姓名");
		obj4.focus();
		return false;
	}
	if(strim(obj5.value)==""){
		alert("请输入联系电话");
		obj5.focus();
		return false;
	}
	if(isPhonePattern.test(obj5.value)==false && isMobilePattern.test(obj5.value)==false){
		alert("请输入正确的联系电话，如：\n手机：13855556666\n固话：0571-58586666");
		obj5.select();
		return false;
	}
	if(obj6.value==""){
		alert("请选择报修时间");
		return false;
	}
	if(strim(obj7.value)==""){
		alert("请输入维修单号");
		obj7.focus();
		return false;
	}
	if(obj8.value==""){
		alert("请选择预约日期");
		return false;
	}
	if(obj9.value=="null"){
		alert("请选择预约时间");
		return false;
	}
	if(obj10.value=="no"){
		alert("请选择维修类型");
		return false;
	}
	
	if(strim(obj11.value)==""){
		alert("请输入详细情况");
		obj12.focus();
		return false;
	}
	else{
		return true;
	}
}