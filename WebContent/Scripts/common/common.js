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
function ChangeHeight(width,height,classname){
	var arr=getElementsByClassName(classname);
	var h=0;
	if(nv.userAgent.indexOf("MSIE")>=1)
	{
		h=52;
	}
	else if(nv.userAgent.indexOf("Firefox")>=1)
	{
		h=47;
	}
	else
	{
		h=47;
	}
	for(var i=0;i<arr.length;i++)
	{
		arr[i].style.height=(height-h)+'px';
	}
}
function getElementsByClassName(className){
	var elems = (document.body).getElementsByTagName("*");
	var result=[];
	for (i=0; j=elems[i]; i++){
	   if ((" "+j.className+" ").indexOf(" "+className+" ")!=-1){
	        result.push(j);
	   }
	}
	return result;
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


function UpdateSelectedItem(objSelect, objItemValue) {   
    for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == objItemValue) {  
       	 objSelect.options[i].selected = true; 
            break;       
        }       
    } 
}   

function strim(str){
	return str.replace(/(^\s*)|(\s*$)/g,""); 
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

//存放火灾警报的数组
var objList=new Array();


function controlLength(id){
	
	var arr=$("#"+id).find("#dd0").children().find(".dTreeNode").find("a");
	var strHtml="";
	$(arr).each(function(){
		strHtml=$(this).html();
		if(strHtml.length>10){
			strHtml=strHtml.substring(0,10)+"...";
		}
		$(this).html(strHtml);
	});
}