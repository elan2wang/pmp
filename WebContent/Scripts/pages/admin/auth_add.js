function addFormCheck(){
    document.getElementById("form1").submit();
	closeAddNewAuth();
}
function addClose(){
	closeAddNewAuth();
}
function editFormCheck(){
    document.getElementById("form2").submit();
	closeEditAuth();
}
function editClose(){
	closeEditAuth();
}
function resFormCheck(){
	SetSelect();
    document.getElementById("form3").submit();
	closeAuthRes();
}
function resClose(){
	closeAuthRes();
}
function SetSelect()
{
	$('#resourceList').find("option").attr("selected",true);
}