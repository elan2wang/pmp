// JavaScript Document

    //=======================================================
	function strim(str){
		return str.replace(/(^\s*)|(\s*$)/g,""); 
	}
function newFormCheck(){
		objfc1=document.getElementById("company.comName");
		objfc2=document.getElementById("company.comLegal");
		objfc3=document.getElementById("company.comPhone");
		objfc4=document.getElementById("company.registerTime");
		objfc5=document.getElementById("company.registerMoney");
		objfc6=document.getElementById("company.comLicense");
		objfc7=document.getElementById("company.comAddress");
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
		//检查公司名称是否重复
		return check_Cmpy(strim(objfc1.value),objfc1);
}
function check_Cmpy(CmpyName,objfc1) {
	var url = "checkCompanyByName?companyName="+CmpyName;
    	$.ajax({
			type: "POST",
			url: url,
			dataType:"json",
			success : function(data){					
				var result = data["result"];
				if(result=="Failed")
				{
					alert("已存在同名公司，请核对！");
					objfc1.select();
					 return false;
				}
				else
				{
					document.getElementById("form1").submit();
					return true;
					closeAddNewCmpy();
				}
			}
		});    	
}
