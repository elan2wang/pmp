// JavaScript Document
    //=======================================================
	function strim(str){
		return str.replace(/(^\s*)|(\s*$)/g,""); 
	}
function FormCheck(){
		objfc1=document.getElementById("company.comName2");
		objfc2=document.getElementById("company.comLegal2");
		objfc3=document.getElementById("company.comPhone2");
		objfc4=document.getElementById("company.registerTime2");
		objfc5=document.getElementById("company.registerMoney2");
		objfc6=document.getElementById("company.comLicense2");
		objfc7=document.getElementById("company.comAddress2");
		var isPhonePattern = /\d{3}-\d{8}|\d{4}-\d{7}/;
		var isMobilePattern = /^(13|15|18)[0-9]{9}$/;
		//alert(strim(objfc1.value));
		if(strim(objfc1.value)==""){
			alert("公司名称不能为空");
			objfc1.focus();
			return (false);
		}
		if(strim(objfc2.value)==""){
			alert("公司法人不能为空");
			objfc2.focus();
			return (false);
		}
		if(strim(objfc3.value)==""){
			alert("公司电话不能为空");
			objfc3.focus();
			return (false);
		}
		if(isPhonePattern.test(objfc3.value)==false && isMobilePattern.test(objfc3.value)==false){
			alert("公司电话不符合电话号或手机号码的规则")	;
			objfc3.focus();
			return(false);
		}
		if(objfc4.value==""){
			alert("注册时间不能为空");
			objfc4.focus();
			return (false);
		}
		if(strim(objfc5.value)==""){
			alert("注册资金不能为空");
			objfc5.focus();
			return (false);
		}
		if(strim(objfc6.value)==""){
			alert("工商执照不能为空");
			objfc6.focus();
			return (false);
		}
		if(strim(objfc7.value)==""){
			alert("公司地址不能为空");
			objfc7.focus();
			return (false);
		}
		document.getElementById("form2").submit();
		closeEditCmpy();
		return true;
}