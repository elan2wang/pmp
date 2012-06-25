//公共函数

//根据小区 获取楼宇信息
function getBuilding(){
	var proId = $('#project').val();
	var builId=$('#builId'); 
	builId.find('option').remove();
	builId.append('<option value="null2">请选择楼宇</option>');
	$('#houseId').find('option').remove();
	$('#houseId').append('<option value="null3">请选择房号</option>');
	$.ajax({
		type: "POST",
		url: "select_building?proId="+proId,
		dataType: "json",
		success: function(data){
			$.each( data.Rows , function(commentIndex, comment) {				
				builId.append('<option value="'+comment['builId']+'">'+comment['builNum']+'</option>');
		    });
		}
	});
}

//根据楼号 获取房屋信息
function getHouse(){
	var builId = $('#builId').val();
	var selector=$('#houseId'); 
	selector.find('option').remove();
	selector.append('<option value="null3">请选择房号</option>');
	$.ajax({
		type: "POST",
		url: "select_house?builId="+builId,
		dataType: "json",
		success: function(data){
			$.each( data.Rows , function(commentIndex, comment) {				
		        selector.append('<option value="'+comment['houseId']+'">'+comment['houseNum']+'</option>');
		    });
		}
	});
}

//根据房号获取用户信息
function getOwner(){
	var houseId = $('#houseId').val();
	$.ajax({
		type: "POST",
		url: "get_houseOwner_info?houseId="+houseId,
		dataType: "json",
		success: function(data){
			$('#applyPerson').val(data.ownerName);
			$('#contactPhone').val(data.contactPhone);
		}
	});
}
    
//-------------------------------------------------------

//=======================================================

//以下表单检测
function strim(str){
    return str.replace(/(^\s*)|(\s*$)/g,""); 
}
function formchk(){
	
	obj1=document.getElementById("project");
	obj2=document.getElementById("builId");
	obj3=document.getElementById("houseId");
	obj4=document.getElementById("ownerRepair.applyPerson");
	obj5=document.getElementById("ownerRepair.contactPhone");
	obj6=document.getElementById("ownerRepair.applyTime");
	obj7=document.getElementById("ownerRepair.opNum");
	obj8=document.getElementById("ownerRepair.orderDate");
	obj9=document.getElementById("ownerRepair.orderTime");
	obj10=document.getElementById("wnerRepair.repairType");
	obj11=document.getElementById("ownerRepair.repairDetail");
	var isotherPatten=/^\d*$/;
	var isPhonePattern = /\d{3}-\d{8}|\d{4}-\d{7}/;
	var isMobilePattern = /^(13|15|18)[0-9]{9}$/;
	if(obj1.value=="null1"){
		alert("请选择小区");
		return false;
	}
	if(obj2.value=="null2"){
		alert("请选择楼宇");
		return false;
	}
	if(obj3.value=="null3"){
		alert("请选择房号");
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
//	if(isPhonePattern.test(obj5.value)==false && isMobilePattern.test(obj5.value)==false){
//		alert("请输入正确的联系电话，如：\n手机：13855556666\n固话：0571-58586666");
//		obj5.select();
//		return false;
//	}
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