var rowIndex=1;
var rowIndex2=1;
var table1height=50;
var table2height=25;

$(function(){
	var project=document.getElementById("proId");
	project.options[0].selected = true;
	projectChanged();
});

function projectChanged()
{
	var select1Value=document.getElementById("proId").value;
	$.ajax({
		type: "POST",
		url: "select_building?proId="+select1Value,
		dataType: "json",
		success: function(data){
			var selector=$('#builNum'); 
			selector.find('option').remove();
			$.each( data.Rows , function(commentIndex, comment) {				
		        selector.append('<option value="'+comment['builId']+'">'+comment['builNum']+'</option>');
		    });
		}
	});
}

function appendRow(){
	objtb=document.getElementById("electricList");
	objNum=document.getElementById("tableNum");
	objLast=document.getElementById("lastDegree");
	objNow=document.getElementById("nowDegree");
	objFee=document.getElementById("degreeFee");
	isnum1=/^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$/;
	isnum2=/^[1-9]d*$/;
	ismoney=/^[0-9]+(.[0-9]{1,2})?$/;
	if(isnum1.test(objLast.value)==false&&isnum2.test(objLast.value)==false){
		alert("上期度数不正确");
		objLast.focus();
		return false;
	}
	else if(isnum1.test(objNow.value)==false&&isnum2.test(objNow.value)==false){
		alert("本期度数不正确");
		objNow.focus();
		return false;
	}
	else if(ismoney.test(objFee.value)==false){
		alert("单价不正确");
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
		newTd0.innerHTML="<input name=\"pmNum\" type=\"hidden\" id=\"mtNum"+rowIndex+"\" value=\""+objNum.value+"\"/>"+objNum.value;
		newTd1.innerHTML="<input name=\"pmBeginDegree\" type=\"hidden\" id=\"mtLast"+rowIndex+"\" value=\""+objLast.value+"\"/>"+objLast.value;
		newTd2.innerHTML="<input name=\"pmEndDegree\" type=\"hidden\" id=\"mtNow"+rowIndex+"\" value=\""+objNow.value+"\"/>"+objNow.value;
		newTd3.innerHTML="<input name=\"pmPrice\" type=\"hidden\" id=\"mtFee"+rowIndex+"\" value=\""+objFee.value+"\"/>"+objFee.value;
		newTd4.innerHTML="<a href=\"javascript:removeRow("+rowIndex+")\" style=\"color:red;text-decoration:none;\">删除</a>";
		rowIndex++;
		table1height+=25;
		$("#electricHeight").css("height",table1height+"px");
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
	$("#electricHeight").css("height",table1height+"px");
}
function updateRow(){
	objtb=document.getElementById("electricList");
	var beginDegree=0;
	var endDegree=0;
	var price = 0;
	var totalMoney=0;
	var totalDegree=0;
	var arrTR=objtb.getElementsByTagName("tr");
	for(x=1;x<arrTR.length-1;x++){
		endDegree=parseFloat(arrTR[x].getElementsByTagName("td")[2].getElementsByTagName("input")[0].value);
		beginDegree=parseFloat(arrTR[x].getElementsByTagName("td")[1].getElementsByTagName("input")[0].value);
		totalDegree += endDegree-beginDegree;
		price=parseFloat(arrTR[x].getElementsByTagName("td")[3].getElementsByTagName("input")[0].value);
		totalMoney +=(endDegree-beginDegree) * price;
	}
	document.getElementById("totalDegree").innerHTML=""+totalDegree.toFixed(2);
	document.getElementById("totalMoney").innerHTML=""+totalMoney.toFixed(2);
	document.getElementById("electricFeeItem.totalMoney").value=totalMoney.toFixed(2);//
}
//--------------------------------------
//----电梯表增加一行
function updateElev(money){
	var temp=document.getElementById("electricFeeItem.totalMoney").value;
	var totalmoney=parseFloat(temp)+parseFloat(money);
	document.getElementById("electricFeeItem.totalMoney").value=totalmoney.toFixed(2);
}
function appendRowElev(){
	objtb=document.getElementById("elevatorList");
	objNum=document.getElementById("builNum");
	objLast=document.getElementById("elevatorlastDegree");
	objNow=document.getElementById("elevatornowDegree");
	objFee=document.getElementById("elevatordegreeFee");
	
	isnum1=/^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$/;
	isnum2=/^[1-9]d*$/;
	ismoney=/^[0-9]+(.[0-9]{1,2})?$/;
	if(isnum1.test(objLast.value)==false&&isnum2.test(objLast.value)==false){
		alert("上期度数不正确");
		objLast.focus();
		return false;
	}
	else if(isnum1.test(objNow.value)==false&&isnum2.test(objNow.value)==false){
		alert("本期度数不正确");
		objNow.focus();
		return false;
	}
	else if(ismoney.test(objFee.value)==false){
		alert("单价不正确");
		objFee.focus();
		return false;
	}
	else{
		table2height+=81;
		$("#elevatorHeight").css("height",table2height+"px");
		//更新隐藏域
		updateElev(objFee.value);
		//获取楼宇信息
		var builInfo='';
		$.ajax({
			type: "POST",
			url: "getBuilInfo?builId="+document.getElementById("builNum").value,
			dataType: "json",
			success: function(data){
				builInfo += data.info;
				var newTr = objtb.insertRow(1);
				var newTd0 = newTr.insertCell(0);
				var newTd1 = newTr.insertCell(1);
				newTr.style.verticalAlign="middle";
				newTr.style.textAlign="center";
				newTr.id="td"+rowIndex;
				newTd0.style.height="75px";
				newTd1.style.height="75px";
				newTd1.style.borderWidth="0px";
				newTd1.style.padding="0px";
				newTd1.colSpan="4";
				newTd0.innerHTML='<input type="hidden" name="builId" value="'+objNum.value+'"/>'+objNum.options[objNum.selectedIndex].text+'号楼,'+builInfo;
				newTd1.innerHTML='<div style="height:auto"><table id="buidingTable'+objNum.value+'" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">'+
				    '<tr><td width="25%" style="width:25%;height:25px"><input type="hidden" name="lmBeginDegree" value="'+objLast.value+'"/>'+objLast.value+'</td><td width="25%"  style="width:25%;height:25px"><input type="hidden" name="lmEndDegree" value="'+objNow.value+'"/>'+objNow.value+'</td><td width="25%"  style="width:25%;height:25px"><input type="hidden" name="lmPrice" value="'+objFee.value+'"/>'+objFee.value+'</td><td width="25%"  style="width:25%;height:25px"><a href="javascript:void(0)" onclick="javascript:removeRowBuild(this)" style="color:red;text-decoration:none;">删除</a></td></tr>'+
					'<tr><td style="height:25px">起始楼层</td><td>终止楼层</td><td>比例</td><td>.</td></tr>'+
					'<tr style="background-color:#FFC;"><td style="height:25px"><input type="text" style="width:60px;"/></td><td><input type="text" style="width:60px;" /></td><td><input type="text" style="width:60px;"/></td><td><input type="button" value="添加" onclick="appendRowBuild(this)"/></td></tr>'+
					'</table></div>';
				rowIndex2++;
				
				objLast.value="";
				objNow.value="";
				objFee.value="";
				objLast.focus();
			}
		});
	}
}

function removeRowBuild(the){
	tableObj=$(the).parent().parent().parent().parent();
	 var h=tableObj.parent().css("height");
	 alert(parseInt(h));
	table2height-=parseInt(h);
	$("#elevatorHeight").css("height",table2height+"px");
	$(the).parent().parent().parent().parent().parent().parent().parent().remove();
}
//-----------------------------------
//---------楼号表增加一行----
function appendRowBuild(the){
	   tableObj=$(the).parent().parent().parent().parent();
	   
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
		   height=(tableObj.find("tr").length+1)*25;
		   $("#elevatorHeight").css("height",(table2height+=25)+"px");
	       var newTr = $(tableObj)[0].insertRow(3);
	       var bfrBuilId = (tableObj.parent().parent().prev().find("input"))[0].value;
	       //alert(bfrBuilId);
	       var newTd0 = newTr.insertCell(0);
	       var newTd1 = newTr.insertCell(1);
	       var newTd2 = newTr.insertCell(2);
	       var newTd3 = newTr.insertCell(3);
	       newTd0.style.height="25px";
	       newTd0.innerHTML='<input type="hidden" name="bfrBuilId" value="'+bfrBuilId+'"/>'+'<input type="hidden" name="beginFloor" value="'+firstFloor.value+'"/>'+firstFloor.value;
	       newTd1.innerHTML='<input type="hidden" name="endFloor" value="'+lastFloor.value+'"/>'+lastFloor.value;
	       newTd2.innerHTML='<input type="hidden" name="rate" value="'+rate.value+'"/>'+rate.value;
	       newTd3.innerHTML="<a href=\"javascript:void(0)\" onclick=\"javascript:removeRowCommon(this)\" style=\"color:red;text-decoration:none;\">删除</a>";
	       
	       firstFloor.value=parseInt(lastFloor.value)+1;
	       lastFloor.value="";
	       rate.value="";
		}
}

function removeRowCommon(the){
	tableObj=$(the).parent().parent().parent().parent();
    height=(tableObj.find("tr").length-1)*25;
	$("#elevatorHeight").css("height",(table2height-=25)+"px");
	$(the).parent().parent().remove();
}