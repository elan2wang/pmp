// JavaScript Document
function AddTds(row,colm){//添加页面的<tr><td>
	var  html="";
	for(i=0;i<row;i++)
	{
		html+="<tr>";
		for(j=0;j<colm;j++)
		{
			html+="<td></td>";
		}
		html+="</tr>";
	}
	return html;
	
}
function initIcon(){
	document.getElementById("first_page").firstChild.src="../../Images/first1.gif";
	document.getElementById("pre_page").firstChild.src="../../Images/pre1.gif";
	document.getElementById("next_page").firstChild.src="../../Images/next1.gif";
	document.getElementById("last_page").firstChild.src="../../Images/last1.gif";
}
function changeIcon(nowpage,totalpage){
	if(nowpage==1)
	{
	    document.getElementById("first_page").firstChild.src="../../Images/first2.gif";
	    document.getElementById("pre_page").firstChild.src="../../Images/pre2.gif";
		if(nowpage==totalpage)
		{
	       document.getElementById("next_page").firstChild.src="../../Images/next2.gif";
	       document.getElementById("last_page").firstChild.src="../../Images/last2.gif";
		}
		
	}
	if(nowpage==totalpage){
		 document.getElementById("next_page").firstChild.src="../../Images/next2.gif";
	     document.getElementById("last_page").firstChild.src="../../Images/last2.gif";
		 if(nowpage==1){
			  document.getElementById("first_page").firstChild.src="../../Images/first2.gif";
	          document.getElementById("pre_page").firstChild.src="../../Images/pre2.gif";
		 }
    }
}