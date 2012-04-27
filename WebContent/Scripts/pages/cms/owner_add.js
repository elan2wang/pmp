//JavaScript 描述
    function pageInt() {
        objP1 = document.getElementById("P1");
        objP2 = document.getElementById("P2");
        objTab1 = document.getElementById("Tab1");
        objTab2 = document.getElementById("Tab2");
        objP1.style.display = "none";
        objP2.style.display = "none";
        objTab1.className = "ModuleTap";
        objTab2.className = "ModuleTap";
    }
    function to(page) {
        objP1 = document.getElementById("P1");
        objP2 = document.getElementById("P2");
        objTab1 = document.getElementById("Tab1");
        objTab2 = document.getElementById("Tab2");
        if (page == "P2") {
            pageInt();
            objP2.style.display = "block";
            objTab2.className = "ModuleTapOn";
        }
        if (page == "P1") {
            pageInt();
            objP1.style.display = "block";
            objTab1.className = "ModuleTapOn";
        }
    }
    function birthyear() {
        today = new Date(); //申明一个时间对象
        intYear = today.getFullYear(); //返回年
        document.write("<option>" + intYear + "</option>");
        for (i = 1; i <= 150; i++) {
            document.write("<option>" + (intYear - i) + "</option>");
        }
    }
	//=================
	function strim(str){
		return str.replace(/(^\s*)|(\s*$)/g,""); 
	}
	function FormCheck(){
		objfc1=document.getElementById("YZName");
		objfc2=document.getElementById("YZGender");
		objfc3=document.getElementById("YZ_MZ");
		objfc4=document.getElementById("YZ_JG");
		objfc5=document.getElementById("YZmarriage");
		objfc6=document.getElementById("YZ_IDClass");
		objfc7=document.getElementById("YZ_IDNum");
		objfc8=document.getElementById("YZphone");
		objfc9=document.getElementById("YZmobile");
		objfc10=document.getElementById("YZ_LF");
		objfc11=document.getElementById("YZ_XQ");
		objfc12=document.getElementById("YZBuilding");
		objfc13=document.getElementById("YZunit");
		objfc14=document.getElementById("YZroom");
		objfc15=document.getElementById("YZarea");
		objfc16=document.getElementById("radio4");
		objfc17=document.getElementById("OtherText");
		var isIDCardPattern = /\d{15}|\d{18}/;
		var isPhonePattern = /\d{3}-\d{8}|\d{4}-\d{7}/;
		var isMobilePattern = /^(13|15|18)[0-9]{9}$/;
		isnum=/^[1-9][0-9]*$/;
		/*if(strim(objfc1.value)==""){
			alert("姓名不能为空");
			objfc1.focus();
			return (false);
		}
		if(objfc2.value=="null"){
			alert("请选择业主性别");
			objfc2.focus();
			return (false);
		}
		if(objfc3.value=="0"){
			alert("请选择民族");
			objfc3.focus();
			return (false);
		}
//		if(isPhonePattern.test(objfc3.value)==false && isMobilePattern.test(objfc3.value)==false){
//			alert("公司电话不符合电话号或手机号码的规则")	;
//			objfc3.focus();
//			return(false);
//		}
		if(objfc5.value=="null"){
			alert("请选择婚姻状况");
			objfc5.focus();
			return (false);
		}
		if(objfc6.value=="null"){
			alert("请选择证件类型");
			objfc6.focus();
			return (false);
		}
		if(strim(objfc7.value)==""){
			alert("证件编号不能为空");
			objfc7.focus();
			return (false);
		}
		if(objfc6[objfc6.selectedIndex].value=="身份证"){
			if(isIDCardPattern.test(objfc7.value)==false){
				alert("证件编号不符合身份证号码规则");
				objfc6.focus();
				return (false);
			}
		}
		if(strim(objfc8.value)=="" && strim(objfc9.value)==""){
			alert("请至少留下一种联系方式");
			objfc9.focus();
			return (false);
		}
		if(objfc8.value!="" && isPhonePattern.test(objfc8.value)==false){
			alert("家庭电话格式不正确（例：0580-8088123）");
			objfc8.focus();
			return (false);
		}
		if(objfc9.value!="" && isMobilePattern.test(objfc9.value)==false){
			alert("手机号码格式不正确（例：13812345678）");
			objfc9.focus();
			return (false);
		}
		if(objfc10.value==""){
			alert("领房日期不能为空");
			objfc10.focus();
			return (false);
		}
		if(objfc11.value=="- -请选择小区- -"){
			alert("请选择所在小区");
			objfc11.focus();
			return (false);
		}
		if(objfc12.value=="- -请选择楼号- -"){
			alert("请选择所在楼宇号");
			objfc12.focus();
			return (false);
		}
		if(objfc13.value=="- -请选择单元- -"){
			alert("请选择所在单元");
			objfc13.focus();
			return (false);
		}
		if(objfc14.value=="- -请选择房号- -"){
			alert("请选择房号");
			objfc14.focus();
			return (false);
		}
		if(strim(objfc15.value)==""){
			alert("房屋面积不能为空");
			objfc15.focus();
			return (false);
		}
		if(isnum.test(objfc15.value)==false){
			alert("请核对房屋面积");
			objfc15.select();
			return (false);
		}
		if(objfc16.checked==true && strim(objfc17.value)==""){
			alert("请填写使用状况");
			objfc17.focus();
			return (false);
		}*/
		to('P2');;
	}
