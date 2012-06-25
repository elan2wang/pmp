/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-14
 * 
 * this script is used by the *_tree.jsp
 */ 

var d = new dTree("d");
function load_data(action,url,target){
	$.ajax({
		type: "POST",
		url: action+"?url="+url+"&target="+target,
		dataType: "json",
		success: function(data){
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