function addFormCheck(){
    document.getElementById("form1").submit();
	closeAddNewRole();
}
function addClose(){
	closeAddNewRole();
}
function editFormCheck(){
    document.getElementById("form2").submit();
	closeEditRole();
}
function editClose(){
	closeEditRole();
	closeEditRole();
}
function authFormCheck(){
	SetSelect();
    document.getElementById("form3").submit();
	closeRoleAuth();
}
function authClose(){
	closeRoleAuth();
}
function SetSelect()
{
	$('#authList').find("option").attr("selected",true);
}