var rowIndex=1;
tableheight=0;
function appendRow(){
	objtb=document.getElementById("repairFeeList");
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
		newTd0.style.width="120px";
		newTd0.innerHTML="<input name=\"itemName\" type=\"hidden\" id=\"mtName"+rowIndex+"\" value=\""+objName.value+"\"/>"+objName.value;
		newTd1.style.width="60px";
		newTd1.innerHTML="<input name=\"itemAmount\" type=\"hidden\" id=\"mtNum"+rowIndex+"\" value=\""+objNum.value+"\"/>"+objNum.value;
		newTd2.style.width="100px";
		newTd2.innerHTML="<input name=\"itemMoney\" type=\"hidden\" id=\"mtPrice"+rowIndex+"\" value=\""+objPrice.value+"\"/>"+objPrice.value;
		newTd3.style.width="190px";
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

//删除还未提交到数据库的费用记录
function removeRow(index){
	objtb=document.getElementById("repairFeeList");
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

//删除已经提交到数据库的费用记录
//the 表示当前标签，rfId表示费用记录的编号
function deleteFee(the){
	var rfId = $(the).parent().parent().find('input').eq(0).val();
	$.ajax({
		type: "POST",
		url: "deleteRepairFee?rfId="+rfId,
		dataType: "json",
		success: function(data){
			alert("删除成功");
			//移除当前<tr></tr>
			$(the).parent().parent().remove();
		}
	});
}

function updateRow(){
	objtb=document.getElementById("repairFeeList");
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
	obj5=document.getElementById("ownerRepair.contactPhone");
	obj8=document.getElementById("ownerRepair.orderDate");
	obj9=document.getElementById("ownerRepair.orderTime");
	obj11=document.getElementById("ownerRepair.repairDetail");
	var isPhonePattern = /\d{3}-\d{8}|\d{4}-\d{7}/;
	var isMobilePattern = /^(13|15|18)[0-9]{9}$/;
	var isotherPatten=/^\d*$/;
	
	if(strim(obj5.value)==""){
		alert("请输入联系电话");
		obj5.focus();
		return false;
	}
	if(strim(obj5.value)!=""){
		var phonevalue=obj5.value;
		var wrong='';
		if(phonevalue.indexOf(",")>0){
			var strs=phonevalue.split(',');
			for(var i=0;i<strs.length;i++){
				if(isPhonePattern.test(strs[i])==false && isMobilePattern.test(strs[i])==false&&isotherPatten.test(strs[i])==false){
					wrong+=strs[i]+",";
				};
			}
			wrong=wrong.substring(0,wrong.length-1);
			if(wrong.length>0){
				alert(wrong+"输入错误，正确：\n手机：13855556666\n固话：0571-58586666\n其他：123456");
			   obj5.select();
			  return false;
			}
		}
		else{
			if(isPhonePattern.test(phonevalue)==false && isMobilePattern.test(phonevalue)==false&&isotherPatten.test(phonevalue)==false){
				alert("请输入正确的联系电话，如：\n手机：13855556666\n固话：0571-58586666 \n其他：123456\n多个电话请用,号隔开" );
				return false;
			};	
		}
	}
	
	if(obj8.value==""){
		alert("请选择预约日期");
		return false;
	}
	if(obj9.value=="null"){
		alert("请选择预约时间");
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