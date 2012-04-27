// JavaScript Document
    //公共函数
    function removeChildren(parent) {
        while (parent.firstChild) {
            parent.removeChild(parent.firstChild);
        }
        return parent;
    }
    function createRequest(options) {
        var req = false;
        if (window.XMLHttpRequest) {
            var req = new window.XMLHttpRequest();
        } else if (window.ActiveXObject) {
            var req = new window.ActiveXObject('Microsoft.XMLHTTP');
        }
        if (!req) return false;
        req.onreadystatechange = function () {
            if (req.readyState == 4 && req.status == 200) {
                options.listener.call(req);
            } else if (req.readyState == 4 && req.status == 404) {
                options.failListener.call(req)
            }
        };
        req.open(options.method, options.url, true);
        return req;
    }
    //楼号选择函数段
    function ajaxRequest_Cmpy() {
        var objCmpyName = document.getElementById('CmpyName');
        var cmpyName = objCmpyName.value;
        var ran = (new Date().getTime()) ^ Math.random();
        var options = {
            url: 'CmpyNameChk_callback.aspx?ran=' + ran + '&q=' + cmpyName,
            listener: callback_Cmpy,
            method: 'GET',
            failListener: failCallback_Cmpy
        }
        var request = createRequest(options);
        request.send(null);
    }
    function callback_Cmpy() {
        var objCmpyName = document.getElementById('CmpyName');
        var txtDoc = this.responseText;
        if (txtDoc == "true") {
            alert("已存在同名公司，请核对！");
            objCmpyName.select();
            return false;
        }
        else {
            return true;
        }
    }
    function failCallback_Cmpy() {
        alert("ERROR:CmpyNameChk_callback.aspx?q=' + cmpyName FAIL");
    }
    //=======================================================
    function to(page) {
        objP1 = document.getElementById("P1");
        objP2 = document.getElementById("P2");
        objTab1 = document.getElementById("Tab1");
        objTab2 = document.getElementById("Tab2");
        if (page == "P2") {
            objP1.style.display = "none";
            objP2.style.display = "block";
            objTab1.className = "ModuleTap";
            objTab2.className = "ModuleTapOn";
        }
        if (page == "P1") {
            objP1.style.display = "block";
            objP2.style.display = "none";
            objTab1.className = "ModuleTapOn";
            objTab2.className = "ModuleTap";
        }
    }
	function strim(str){
		return str.replace(/(^\s*)|(\s*$)/g,""); 
	}
function FormCheck(){
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
		document.getElementById("form").submit();
		window.parent.closeEditCmpy();
}