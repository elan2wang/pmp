	function strim(str){
		return str.replace(/(^\s*)|(\s*$)/g,""); 
	}
    function selectDel() {
        objSelect = document.getElementById("EqpList");

        for (i = 0; i < objSelect.length; i++) {
            if (objSelect[i].selected == true) {
                objSelect.options.remove(i);
            }
        }
    }
    function selectAdd() {
        objSelect = document.getElementById("EqpList");
        objSN = document.getElementById("SN");
		if(strim(objSN.value)==""){
			alert("设施编号不正确");
			objSN.select();
		}
		else{
			var varItem = new Option(objSN.value.toString(), objSN.value);
			//alert(objSN.value.toString());
			//objSelect.options[objSelect.options.length]=varItem;
			//objSelect[objSelect.length-1].selected=true;
			objSelect.add(varItem, 0);
			//objSelect.options.add(varItem);
			objSN.value = "";
			objSN.focus();
		}

    }
    function selectTransfer() {
        objSelect = document.getElementById("EqpList");
        objSelectValues = document.getElementById("SelectValues");

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
		//alert(objSelectValues.value);
    }
	//===========================================================
	function formchk(){
		objPRName = document.getElementById("PRName");
		objPRfacilityName = document.getElementById("PRfacilityName");
		objSN = document.getElementById("SN");
		objEqpList = document.getElementById("EqpList");
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