/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-8
 * 
 * this script is used by the house_tree.jsp
 */ 
var d = new dTree("d");
d.add(0,-1,"house");
function load_data(){
	$.ajax({
		type: "POST",
		url: "houseTree",
		dataType: "json",
		success: function(data){
			$.each(data.Nodes,function(commentIndex,comment){
				d.add(comment['id'], comment['pid'], comment['name'], comment['url'], comment['title'], 
						comment['target'], comment['icon'], comment['iconOpen'], comment['open']);
			});
			document.getElementById("tree").innerHTML=d.toString();
		}
	});
}