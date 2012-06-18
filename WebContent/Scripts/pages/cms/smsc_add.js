/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-4-17
 * 
 * this script is used by the smsc_list.jsp
 */ 
function loadCompanyList(){
	$.ajax({
		type: "POST",
		url: "select_company_list",
		dataType: "json",
		success: function(data){
			var selector1 = $('#comId');
			selector1.find('option').remove();
			$.each( data.Rows,function(commentIndex, comment){
				selector1.append("<option value=\""+comment['comId']+"\">"+comment['comName']+"</option>");
			});
		}
	});
}

function FormCheck(){
	objfc1=document.getElementById("smsCompany.smscName");
	objfc2=document.getElementById("smsCompany.smsUpUrl");
	objfc3=document.getElementById("smsCompany.smsDownUrl");
	objfc4=document.getElementById("smsCompany.username");
	objfc5=document.getElementById("smsCompany.password");
	objfc6=document.getElementById("smsCompany.extendCode");
	objfc7=document.getElementById("comId");
	
	if(strim(objfc1.value)==""){
		alert("账号名称不能为空");
		objfc1.focus();
		return (false);
	}
	if(strim(objfc2.value)==""){
		alert("上行地址不能为空");
		objfc2.focus();
		return (false);
	}
	if(strim(objfc3.value)==""){
		alert("下行地址不能为空");
		objfc3.focus();
		return (false);
	}
	if(strim(objfc4.value)==""){
		alert("应用账号不能为空");
		objfc4.focus();
		return (false);
	}
	if(strim(objfc5.value)==""){
		alert("应用密码不能为空");
		objfc5.focus();
		return (false);
	}
	if(strim(objfc6.value)==""){
		alert("扩展码不能为空");
		objfc6.focus();
		return (false);
	}
	if(strim(objfc7.value)==0){
		alert("请选择所属物业");
		objfc7.focus();
		return (false);
	}
	return true;
}