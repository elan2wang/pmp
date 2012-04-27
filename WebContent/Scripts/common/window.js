/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * phone： 15158133592
 * create: 2012-4-24
 * 
 * this script is used for windows operations
 */ 

/* open a window for adding */
function openAddWindow(id){
	$(id).window('open');
}

/* open a window for editing */
var reload=false;
function openEditWindow(id,url){
	$(id).window({href:url});
	if(reload){
		/* first open then refresh */
		/* otherwise the request will send twice */
		$(id).window('open');
		$(id).window('refresh');
	} else{
		$(id).window('open');
	}
	reload=true;
}

/* close a opened window */
function closeWindow(id){
	$(id).window('close');
}
