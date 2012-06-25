
function efcAdd(){
	var objMoney = document.getElementById("electricFeeCharge.chargeMoney");
	//var isDigital= "/\d/";
	if (strim(objMoney.value)==""){
		alert('预存金额不能为空');
		objMoney.focus();
		return;
	}
	/*if(isDigital.test(strim(objMoney.value))==false){
		alert('预存金额格式有误,请输入整数值');
		objMoney.focus();
		return;
	}*/
	$('#form1').attr('action','efc_add?houseId='+$('#houseId').val());
	$('#form1').submit();
}
