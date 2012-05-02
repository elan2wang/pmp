// JavaScript Document

//根据浏览器可见高度设置相应的高度
var nv=navigator;
var Width=document.documentElement.clientWidth;
var Height=document.documentElement.clientHeight;

if(nv.userAgent.indexOf("MSIE")>=1)
{
	Width=Width*0.93;
    Height=Height*0.80;
}
else if(nv.userAgent.indexOf("Firefox")>=1)
{
	Width=Width*0.93;
    Height=Height*0.84;
}
else
{
	Width=Width*0.93;
    Height=Height*0.84;
}

//更改content的高度
function ChangeHeight(width,height){
	alert("进来了+"+height+document.getElementsByClassName("content").length);
	var arr=document.getElementsByClassName("content");
	for(var i=0;i<arr.length;i++)
	{
		arr[i].style.height=(height-37)+'px';
	}
	arr=document.getElementsByClassName("innercontent");
	for(i=0;i<arr.length;i++)
	{
		arr[i].style.height=(height-37)+'px';
	}
}

//添加页面的<tr><td>
function AddTds(row,colm){
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
	document.getElementById("first_page").firstChild.src="../Images/first1.gif";
	document.getElementById("first_page").onclick="PageDownOrUp(1)";
	document.getElementById("pre_page").firstChild.src="../Images/pre1.gif";
	document.getElementById("pre_page").onclick="PageDownOrUp(2)";
	document.getElementById("next_page").firstChild.src="../Images/next1.gif";
	document.getElementById("next_page").onclick="PageDownOrUp(3)";
	document.getElementById("last_page").firstChild.src="../Images/last1.gif";
	document.getElementById("last_page").onclick="PageDownOrUp(4)";
}
function changeIcon(nowpage,totalpage){
	if(nowpage==1)
	{
	    document.getElementById("first_page").firstChild.src="../Images/first2.gif";
	    document.getElementById("pre_page").firstChild.src="../Images/pre2.gif";
	    document.getElementById("first_page").onclick="";
	    document.getElementById("pre_page").onclick="";
		if(nowpage==totalpage)
		{
	       document.getElementById("next_page").firstChild.src="../Images/next2.gif";
	       document.getElementById("last_page").firstChild.src="../Images/last2.gif";
	       document.getElementById("next_page").onclick="";
	       document.getElementById("last_page").onclick="";
		}
		
	}
	if(nowpage==totalpage){
		 document.getElementById("next_page").firstChild.src="../Images/next2.gif";
	     document.getElementById("last_page").firstChild.src="../Images/last2.gif";
	     document.getElementById("next_page").onclick="";
	     document.getElementById("last_page").onclick="";
		 if(nowpage==1){
			  document.getElementById("first_page").firstChild.src="../Images/first2.gif";
	          document.getElementById("pre_page").firstChild.src="../Images/pre2.gif";
	          document.getElementById("first_page").onclick="";
	          document.getElementById("pre_page").onclick="";
		 }
    }
}