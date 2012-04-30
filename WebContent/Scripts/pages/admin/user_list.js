// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();
	$("#tab1").click(function(){
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
	$('#user_data').html(AddTds(20,5));
	$('#userlist').flexigrid({
		colModel: [
            { display: '序号',  width: 40,  align: 'center' },
            { display: '真实姓名', width: 200, align: 'center' },
            { display: '用户描述', width: 200,align: 'center' },
            { display: '职务', width: 200, align: 'center' },
            { display: '操作',  width: 120, sortable: true, align: 'center', align: 'center' }
	    ],
	    height:305
	});
});

function PageDownOrUp(flag){
	        var nowpage=parseInt(document.getElementById("now_page").innerHTML);
			var pagerow=parseInt(document.getElementById("page_row").options[document.getElementById("page_row").selectedIndex].text);
			var gopage=document.getElementById("go_page").value;
			var totalpage=parseInt(document.getElementById("total_page").innerHTML);
			var urlstr="";
			//alert(nowpage+1);
			initIcon();
			switch(flag)
			{
				case 0://默认值
				   nowpage=1;
				   pagerow=10;
				   break;
				case 1:
				   nowpage=1;
				   break;
				case 2:
				   nowpage--;
				   break;
				case 3:
				   nowpage++;
				   break;
				case 4:
				   nowpage=totalpage;
				   break;
				case 5:
				   break;
				case 6:	
				   if(gopage==""){
			          alert("跳转页面不能为空！");
				      return;
				   }
				   if(isNaN(parseInt(gopage))){
					   return;
				   }
				   if(parseInt(gopage)>totalpage){
			          alert("跳转页面大于总页面数！");
				      return;
				   }
				   break;
			}
			changeIcon(nowpage,totalpage);
			urlstr="user_list?currentPage="+nowpage+"&pageSize="+pagerow;
            $.ajax({
			  type: "POST",
			  url: urlstr,
			  dataType: "json",
			  success : function(data){
					  var i=0;
                      $('#user_data').find('tr').hide();
					  var tableTds=$('#user_data').find('td');
					  tableTds.find('div').css('text-align','center');
					  for(i=0;i<pagerow;i++)
					  {$('#user_data').find('tr').eq(i).show();}
					  i=0;
					  tableTds.find('div').html("");
					  var k=1;	//记录序号
					  $.each(data.Rows, function(commentIndex, comment){
						tableTds.eq(i++).find('div').html((k++)+"<input type='checkbox' id='checkgroup' name='checkgroup'/>");
						tableTds.eq(i++).find('div').html(comment['realname']);
						tableTds.eq(i++).find('div').html(comment['userDesc']);
						tableTds.eq(i++).find('div').html(comment['position']);
						var strhtml="<a href=\"#\" onclick=\"openEditWindow('#editUser','get_user?userId='+$(this).next().html())\">编辑</a><span style=\"display:none;width:10px\">"+comment['userId']+"</span><span style='display:none'>"+i+"</span><span style=\"display:inline-block;width:10px\"></span><a href=\"#\" onclick=\"deleteRow($(this).parent().parent().parent(),'deleteUserById?userId='+$(this).prev().prev().prev().html(),'您将删除该用户,确认删除?')\">删除</a>";
						tableTds.eq(i++).find('div').html(strhtml);
					  });
					  $('#now_page').html(data.CurrentPage);
					  $('#total_page').html(data.PagesCount);
					  $('#total_record').html(data.RowsCount);
					  
			  }
			}); 
        }


function selectAll(){
	$('input[type="checkbox"]').attr("checked",true);
}
function selectOpposite(){
	var allchecks=$('input[type="checkbox"]');
	for(i=0;i<allchecks.length;i++)
	{
		onecheck=allchecks.eq(i);
		if(onecheck.attr("checked")=="checked"){
			onecheck.attr("checked",false);
		}
		else
		{
			onecheck.attr("checked",true);
		}
	}
}
function selectNone(){
	$('input[type="checkbox"]').attr("checked",false);
}
function deleteSelected(){
	//alert($('input[checked="checked"]').length);
	//$('input[type="checkbox"]').attr("checked",false);
	var boxes = $("checkgroup");  
    var groupTypeId = new Array();  
    for (var i = 0; i < boxes.length; i++)  
    {  
       if (boxes[i].checked)  
       {  
	      alert(1);
          groupTypeId[i] = boxes[i].value;  
       }
     }
}
function getSecondInfo()
{
	 var select1Value=document.getElementById("select1")[document.getElementById("select1").selectedIndex].innerHTML;
	 $.ajax({
	  type: "POST",
	  url: "../cms/datagrid_data.json",
	  dataType: "json",
	  success : function(data){
		      var selector=$('#select2'); 
			  var s=selector.find('option');
			  for(i=1;i<=10;i++){
			     s.eq(i).remove();
			  }
			  $.each( data.rows , function(commentIndex, comment) {
                   selector.append('<option value="'+comment['name']+'">'+comment['name']+'</option>');
			  });
			  
	  }
	});
}
