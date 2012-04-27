/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * phone： 15158133592
 * create: 2012-4-25
 * 
 * this script is used for checkAll/checkNone/unCheck... operations
 */ 

function checkAll(name){
	$("[name='"+name+"']").attr("checked","checked");
}

function checkNone(name){
	$("[name='"+name+"']").removeAttr("checked");
}

function reverseCheck(name){
	$("[name='"+name+"']").each(function(){
		if($(this).attr("checked")){
			$(this).removeAttr("checked");
		}
		else{
			$(this).attr("checked","checked");
		}
	});
}
