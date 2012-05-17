// JavaScript Document
$(function(){
		var isornotempty = document.getElementById("house.isempty");
		if(isornotempty.value==document.getElementById("isempty").value)
		{
			document.getElementById("isempty").checked = true;
		}
		else if(isornotempty.value==document.getElementById("notempty").value)
		{
			document.getElementById("notempty").checked = true;
		}
});
function FormCheck(){
	    document.getElementById("form").submit();
		closeEditHouse();
	}
function Close(){
	    closeEditHouse();
	}
