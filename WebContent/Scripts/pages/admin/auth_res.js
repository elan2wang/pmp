/**
 * author: ChrussyGe
 * email： chrussyge@gmail.com
 * date:   2012-4-12
 * 
 * this script is used by the role_authority.jsp
 */
function RemoveAll(){
	    $("#resourceList").find("option").appendTo( $('#res_1'));
		$('#resourceList option').remove();
}
function AddAll(){
	    $("#res_1").find("option").appendTo( $('#resourceList'));
		$('#res_1 option').remove();
}
function Add(){
	    $("#res_1").find("option:selected").appendTo( $('#resourceList'));
	    $("#res_1").find("option:selected").remove();
}
function Remove(){
	   $("#resourceList").find("option:selected").appendTo( $('#res_1'));
	   $("#resourceList").find("option:selected").remove();
}