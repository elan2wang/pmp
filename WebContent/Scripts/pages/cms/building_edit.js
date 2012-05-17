// JavaScript Document
$(function(){
		var isornotempty = document.getElementById("project.enabled");
		if(isornotempty.value==document.getElementById("isenabled").value)
		{
			document.getElementById("isenabled").checked = true;
		}
		else if(isornotempty.value==document.getElementById("notenabled").value)
		{
			document.getElementById("notenabled").checked = true;
		}
});
function editFormCheck(){
	    document.getElementById("form").submit();
		closeEditBuild();
	}
function editClose(){
	    closeEditBuild();
	}
