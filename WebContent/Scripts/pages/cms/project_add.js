// JavaScript Document
 <!--
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
	
	function FormCheck(){
		objfc1=document.getElementById("project.proName");
		objfc2=document.getElementById("project.proAddress");
		objfc3=document.getElementById("project.deliveryTime");
		if(objfc1.value==""){
			alert("小区名称不能为空");
			objfc1.focus();
			return (false);
		}
		if(objfc2.value==""){
			alert("小区地址不能为空");
			objfc2.focus();
			return (false);
		}
		if(objfc3.value==""){
			alert("建成时间不能为空");
			objfc3.focus();
			return (false);
		}
		document.getElementById("form").submit();
		window.parent.closeAddNewProject();
	}
-->