  
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


var rowIndex=1;
tableheight=0;
function appendRow(){
	objtb=document.getElementById("tbProjList");
	objName=document.getElementById("mtName");
	objNum=document.getElementById("mtNum");
	objPrice=document.getElementById("mtPrice");
	objNote=document.getElementById("mtNote");
	isnum1=/^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$/;
	isnum2=/^[1-9]d*$/;
	ismoney=/^[0-9]+(.[0-9]{1,2})?$/;
	if(objName.value==""){
		alert("材料名称不能为空");
		objName.focus();
		return false;
	}
	else if(isnum1.test(objNum.value)==false&&isnum2.test(objNum.value)==false){
		alert("材料数量不正确");
		objNum.focus();
		return false;
	}
	else if(ismoney.test(objPrice.value)==false){
		alert("材料金额不正确");
		objPrice.focus();
		return false;
	}
	else if(objNote.value.length>120){
		alert("备注信息过长，请限制在120字以内");
		objPrice.focus();
		return false;
	}
	else{
		tableheight=parseInt(document.getElementById("feeList").style.height);
		tableheight+=26;
		document.getElementById("feeList").style.height=tableheight+"px";
		var newTr = objtb.insertRow(0);
		var newTd0 = newTr.insertCell(0);
		var newTd1 = newTr.insertCell(1);
		var newTd2 = newTr.insertCell(2);
		var newTd3 = newTr.insertCell(3);
		var newTd4 = newTr.insertCell(4);
		newTr.style.verticalAlign="middle";
		newTr.style.textAlign="center";
		newTr.id="td"+rowIndex;
		newTd0.style.height="25px";
		newTd0.style.witdth="120px";
		newTd0.innerHTML="<input name=\"itemName\" type=\"hidden\" id=\"mtName"+rowIndex+"\" value=\""+objName.value+"\"/>"+objName.value;
		newTd0.style.witdth="60px";
		newTd1.innerHTML="<input name=\"itemAmount\" type=\"hidden\" id=\"mtNum"+rowIndex+"\" value=\""+objNum.value+"\"/>"+objNum.value;
		newTd0.style.witdth="100px";
		newTd2.innerHTML="<input name=\"itemMoney\" type=\"hidden\" id=\"mtPrice"+rowIndex+"\" value=\""+objPrice.value+"\"/>"+objPrice.value;
		newTd0.style.witdth="190px";
		newTd3.align="center";
		newTd3.innerHTML="<div style=\"width:180px\">"+objNote.value+"</div><input name=\"itemComment\" type=\"hidden\" id=\"mtNote"+rowIndex+"\" value=\""+objNote.value+"\"/>";
		newTd4.innerHTML="<a href=\"javascript:removeRow("+rowIndex+")\" style=\"color:red;text-decoration:none;\">删除</a>";
		rowIndex++;
		updateRow();
		objName.value="";
		objNum.value="";
		objPrice.value="";
		objNote.value="";
		objName.focus();
	}
}
function removeRow(index){
	objtb=document.getElementById("tbProjList");
	var arrTR=objtb.getElementsByTagName("tr");
	for(x=0;x<arrTR.length;x++){
		if(arrTR[x].id=="td"+index){
			objtb.deleteRow(x);
		}
	}
	updateRow();
	tableheight=parseInt(document.getElementById("feeList").style.height);
	tableheight-=26;
	document.getElementById("feeList").style.height=tableheight+"px";
}
function updateRow(){
	objtb=document.getElementById("tbProjList");
	var arrINPUT=objtb.getElementsByTagName("input");
	var strINPUT=arrINPUT[0].id;
	for(y=1;y<arrINPUT.length-2;y++){
		strINPUT=strINPUT+","+arrINPUT[y].id;
	}
	document.getElementById("inputArr").value=strINPUT;
	//alert(document.getElementById("inputArr").value);
	//workerPrice和inputArr两个空间名也会跟在后面上传
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
	obj11=document.getElementById("OtherContent");
	obj12=document.getElementById("fdRPDetail");
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
		alert("请选择维修内容");
		return false;
	}
	if(obj10.value=="" && strim(obj11.value)==""){
		alert("请输入维修内容");
		obj11.focus();
		return false;
	}
	if(strim(obj12.value)==""){
		alert("请输入详细情况");
		obj12.focus();
		return false;
	}
	else{
		return true;
	}
}