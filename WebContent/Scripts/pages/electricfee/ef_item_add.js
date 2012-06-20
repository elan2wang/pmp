var rowIndex=1;
var rowIndex2=1;
var table1height=80;
var table2height=100;
function appendRow(){
	$("#electricHeight").css("height",table1height+"px");
	objtb=document.getElementById("electricList");
	objNum=document.getElementById("tableNum");
	objLast=document.getElementById("lastDegree");
	objNow=document.getElementById("nowDegree");
	objFee=document.getElementById("degreeFee");
	isnum1=/^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$/;
	isnum2=/^[1-9]d*$/;
	ismoney=/^[0-9]+(.[0-9]{1,2})?$/;
	objNum.value=rowIndex;
	if(isnum1.test(objLast.value)==false&&isnum2.test(objLast.value)==false){
		alert("上次度数不正确");
		objLast.focus();
		return false;
	}
	else if(isnum1.test(objNow.value)==false&&isnum2.test(objNow.value)==false){
		alert("本次度数不正确");
		objNow.focus();
		return false;
	}
	else if(ismoney.test(objFee.value)==false){
		alert("电费金额不正确");
		objFee.focus();
		return false;
	}
	else{
		var newTr = objtb.insertRow(1);
		var newTd0 = newTr.insertCell(0);
		var newTd1 = newTr.insertCell(1);
		var newTd2 = newTr.insertCell(2);
		var newTd3 = newTr.insertCell(3);
		var newTd4 = newTr.insertCell(4);
		newTr.style.verticalAlign="middle";
		newTr.style.textAlign="center";
		newTr.id="td"+rowIndex;
		newTd0.style.height="25px";
		newTd0.innerHTML="<input name=\"mtNum"+rowIndex+"\" type=\"hidden\" id=\"mtNum"+rowIndex+"\" value=\""+objNum.value+"\"/>"+objNum.value;
		newTd1.innerHTML="<input name=\"mtLast"+rowIndex+"\" type=\"hidden\" id=\"mtLast"+rowIndex+"\" value=\""+objLast.value+"\"/>"+objLast.value;
		newTd2.innerHTML="<input name=\"mtNow"+rowIndex+"\" type=\"hidden\" id=\"mtNow"+rowIndex+"\" value=\""+objNow.value+"\"/>"+objNow.value;
		newTd3.innerHTML="<input name=\"mtFee"+rowIndex+"\" type=\"hidden\" id=\"mtFee"+rowIndex+"\" value=\""+objFee.value+"\"/>"+objFee.value;
		newTd4.innerHTML="<a href=\"javascript:removeRow("+rowIndex+")\" style=\"color:red;text-decoration:none;\">删除</a>";
		rowIndex++;
		table1height+=25;
		updateRow();
		objNum.innerHTML=rowIndex;
		objLast.value="";
		objNow.value="";
		objFee.value="";
		objLast.focus();
	}
}
function removeRow(index){
	objtb=document.getElementById("electricList");
	var arrTR=objtb.getElementsByTagName("tr");
	for(x=0;x<arrTR.length;x++){
		if(arrTR[x].id=="td"+index){
			objtb.deleteRow(x);
			table1height-=25;
		}
	}
	updateRow();
}
function updateRow(){
	objtb=document.getElementById("electricList");
	var totalLast=0;
	var totalNow=0;
	var totalFee=0;
	var arrTR=objtb.getElementsByTagName("tr");
	for(x=1;x<arrTR.length-1;x++){
		totalLast+=parseInt(arrTR[x].getElementsByTagName("td")[1].getElementsByTagName("input")[0].value);
		totalNow+=parseInt(arrTR[x].getElementsByTagName("td")[2].getElementsByTagName("input")[0].value);
		totalFee+=parseInt(arrTR[x].getElementsByTagName("td")[3].getElementsByTagName("input")[0].value);
	}
	document.getElementById("totalLast").innerHTML=""+totalLast;
	document.getElementById("totalNow").innerHTML=""+totalNow;
	document.getElementById("totalFee").innerHTML=""+totalFee;
}
//--------------------------------------
//----电梯表增加一行
function appendRowElev(){
	table2height+=80;
	$("#elevatorHeight").css("height",table2height+"px");
	objtb=document.getElementById("elevatorList");
	objNum=document.getElementById("elevatortableNum");
	objLast=document.getElementById("elevatorlastDegree");
	objNow=document.getElementById("elevatornowDegree");
	objFee=document.getElementById("elevatordegreeFee");
	isnum1=/^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$/;
	isnum2=/^[1-9]d*$/;
	ismoney=/^[0-9]+(.[0-9]{1,2})?$/;
	if(isnum1.test(objNum.value)==false&&isnum2.test(objNum.value)==false){
		alert("楼号不正确");
		objNum.focus();
		return false;
	}
	else if(isnum1.test(objLast.value)==false&&isnum2.test(objLast.value)==false){
		alert("上次电梯表度数不正确");
		objLast.focus();
		return false;
	}
	else if(isnum1.test(objNow.value)==false&&isnum2.test(objNow.value)==false){
		alert("本次电梯表度数不正确");
		objNow.focus();
		return false;
	}
	else if(ismoney.test(objFee.value)==false){
		alert("电费金额不正确");
		objFee.focus();
		return false;
	}
	else{
		var newTr = objtb.insertRow(1);
		var newTd0 = newTr.insertCell(0);
		var newTd1 = newTr.insertCell(1);
		newTr.style.verticalAlign="middle";
		newTr.style.textAlign="center";
		newTr.id="td"+rowIndex;
		newTd0.style.height="25px";
		newTd1.style.height="25px";
		newTd1.colSpan="4";
		newTd0.innerHTML='<input type="hidden" name="buildingNum'+rowIndex2+'" value="'+objNum.value+'"/>'+objNum.value;
		newTd1.innerHTML='<div><table id="buidingTable'+objNum.value+'" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">'+
		'<tr><td width="25%" style="height:25px">'+objLast.value+'</td><td width="25%">'+objNow.value+'</td><td width="25%">'+objFee.value+'</td><td width="25%"><a href="javascript:void(0)" onclick="javascript:removeRowBuild(this)" style="color:red;text-decoration:none;">删除</a></td></tr>'+
			'<tr><td style="height:25px">起始楼层</td><td>终止楼层</td><td>比例</td><td>楼层分摊</td></tr>'+
			'<tr><td style="height:25px"><input type="text" style="width:80px"/></td><td><input type="text" style="width:80px" /></td><td><input type="text" style="width:80px"/></td><td><input type="button" value="添加" onclick="appendRowBuild(this)"/></td></tr>'+
			'</table></div>';
		rowIndex2++;
		
		updateRow();
		objNum.innerHTML="";
		objLast.value="";
		objNow.value="";
		objFee.value="";
		objLast.focus();
	}
}
function removeRowElev(index){
	objtb=document.getElementById("electricList");
	var arrTR=objtb.getElementsByTagName("tr");
	for(x=0;x<arrTR.length;x++){
		if(arrTR[x].id=="td"+index){
			objtb.deleteRow(x);
			table2height-=50;
		}
	}
}
function removeRowBuild(the){
	$(the).parent().parent().parent().parent().parent().parent().parent().remove();
}
//-----------------------------------
//---------楼号表增加一行----
function appendRowBuild(the){
	   tableObj=$(the).parent().parent().parent().parent();
	   height=(tableObj.find("tr").length+1)*25;
	   $("#elevatorHeight").css("height",(table2height+=25)+"px");
	   tableObj.parent().css("height",height+"px");
	   var inputs=$(the).parent().parent().find("input");
	   var firstFloor=inputs[0];
	   var lastFloor=inputs[1];
	   var rate=inputs[2];
	   isnum2=/^[1-9]\d*$/;
	   israte=/^[0](.[0-9]{1,2})?$/;
	   if(isnum2.test(firstFloor.value)==false){
			alert("起始楼层数不正确");
			objLast.focus();
			return false;
		}
		else if(isnum2.test(lastFloor.value)==false){
			alert("终止楼层数不正确");
			objNow.focus();
			return false;
		}
		else if(israte.test(rate.value)==false){
			alert("比例不正确,应该是0-1的小数 比如0.5");
			objNow.focus();
			return false;
		}
		
	   //添加一行
		else{
	       var newTr = $(tableObj)[0].insertRow(3);
	       var newTd0 = newTr.insertCell(0);
	       var newTd1 = newTr.insertCell(1);
	       var newTd2 = newTr.insertCell(2);
	       var newTd3 = newTr.insertCell(3);
	       newTd0.style.height="25px";
	       newTd0.innerHTML='<input type="hidden" name="buildingNum'+rowIndex2+'" value="'+firstFloor.value+'"/>'+firstFloor.value;
	       newTd1.innerHTML='<input type="hidden" name="buildingNum'+rowIndex2+'" value="'+lastFloor.value+'"/>'+lastFloor.value;
	       newTd2.innerHTML='<input type="hidden" name="buildingNum'+rowIndex2+'" value="'+rate.value+'"/>'+rate.value;
	       newTd3.innerHTML="<a href=\"javascript:void(0)\" onclick=\"javascript:removeRowCommon(this)\" style=\"color:red;text-decoration:none;\">删除</a>";
		}
}
function removeRowCommon(the){
	
	$(the).parent().parent().remove();
}