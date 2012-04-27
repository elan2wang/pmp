/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-4-17
 * 
 * this script is used by the role_mod.jsp
 */ 
function RemoveAll(){
	    $("#moduleList").find("option").appendTo( $('#mod_1'));
		$('#moduleList option').remove();
}
function AddAll(){
	    $("#mod_1").find("option").appendTo( $('#moduleList'));
		$('#mod_1 option').remove();
}
function Add(){
	    $("#mod_1").find("option:selected").appendTo( $('#moduleList'));
	    $("#mod_1").find("option:selected").remove();
}
function Remove(){
	   $("#moduleList").find("option:selected").appendTo( $('#mod_1'));
	   $("#moduleList").find("option:selected").remove();
}