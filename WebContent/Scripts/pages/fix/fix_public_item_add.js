function strim(str){
	return str.replace(/(^\s*)|(\s*$)/g,""); 
}
function selectDel() {
    objSelect = document.getElementById("equipList");
    for (i = 0; i < objSelect.length; i++) {
        if (objSelect[i].selected == true) {
            objSelect.options.remove(i);
        }
    }
}
function selectAdd() {
    objSelect = document.getElementById("equipList");
    objSN = document.getElementById("SN");
	if(strim(objSN.value)==""){
		alert("请填写设备编号");
		objSN.select();
	}
	else{
		var varItem = new Option(objSN.value.toString(), objSN.value);
		objSelect.add(varItem, 0);
		objSN.value = "";
		objSN.focus();
	}
}
function selectTransfer() {
    objSelect = document.getElementById("equipList");
    objSelectValues = document.getElementById("publicRepairItem.equipList");

    strValues = "";
    for (i = objSelect.length; i > 0; i--) {
        if (i == objSelect.length) {
            strValues = objSelect[i - 1].value;
        }
        else {
            strValues = strValues + "," + objSelect[i - 1].value;
        }
    }
    objSelectValues.value = strValues;
}
//===========================================================
function formcheck(){
	selectTransfer();
	
	objPRName = document.getElementById("publicRepairItem.itemName");
	objPRfacilityName = document.getElementById("publicRepairItem.equipName");
	objEqpList = document.getElementById("equipList");
	if(strim(objPRName.value)==""){
		alert("请输入项目名称");
		objPRName.focus();
		return false;
	}
	if(strim(objPRfacilityName.value)==""){
		alert("请输入设施名称");
		objPRfacilityName.focus();
		return false;
	}
	if(objEqpList.length<=0){
		alert("请添加至少一个设备");
		objSN.focus();
		return false;
	}
	else{
		return true;
	}
}