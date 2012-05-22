/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-18
 * 
 * this script is used by the cf_export.jsp
 */ 
$(function(){
	var cfiId = document.getElementById("cfiId").value;
	document.getElementById("exportForm").setAttribute("action", "new_cf_export?cfiId="+cfiId);
});