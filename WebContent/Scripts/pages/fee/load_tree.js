/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-14
 * 
 * this script is used by the *_tree.jsp
 */ 

var d;
function load_data(action,url,target){
	var path;
	if(action.indexOf("?")==-1){
		path = action+"?url="+url+"&target="+target;
	} else {
		path = action+"&url="+url+"&target="+target;
	}
	$.ajax({
		type: "POST",
		url: path,
		dataType: "json",
		success: function(data){
			d = new dTree("d");
			d.clearCookie();
			d.add(0,-1,"");
			$.each(data.Nodes,function(commentIndex,comment){
				d.add(comment['id'], comment['pid'], comment['name'], comment['url'], comment['name'], 
						comment['target'], comment['icon'], comment['iconOpen'], comment['open']);
			});
			document.getElementById("tree").innerHTML=d.toString();
			controlLength('tree');
		}
	});
}