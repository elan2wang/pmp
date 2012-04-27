/**
 * author: ChrussyGe
 * email： chrussyge@gmail.com
 * date:   2012-4-11
 * 
 * this script is used by the role_authority.jsp
 */ 
function RemoveAll(){
	    $("#authList").find("option").appendTo( $('#auth_1'));
		$('#authList option').remove();
}
function AddAll(){
	    $("#auth_1").find("option").appendTo( $('#authList'));
		$('#auth_1 option').remove();
}
function Add(){
	    $("#auth_1").find("option:selected").appendTo( $('#authList'));
	    $("#auth_1").find("option:selected").remove();
}
function Remove(){
	   $("#authList").find("option:selected").appendTo( $('#auth_1'));
	   $("#authList").find("option:selected").remove();
}
