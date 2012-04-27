// JavaScript Document

$(function(){
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	  $('#cmpy_data').html(AddTds(20,10));
	  $('#cmpylist').flexigrid({colModel: [
             { display: '序号',  width: 40,  align: 'center' },
             { display: '公司名称', width: 200, align: 'center' },
			 { display: '注册日期', width: 200, align: 'center' },
             { display: '公司法人', width: 200,align: 'center' },
			 { display: '联系方式', width: 200, align: 'center' },
			 { display: '工商执照', width: 200, align: 'center',hide:'true' },
			 { display: '注册资金', width: 200, align: 'center' ,hide:'true'},
			 { display: '公司地址', width: 200, align: 'center' ,hide:'true'},
			 { display: '备注', width: 200, align: 'center' ,hide:'true'},
             { display: '操作',  width: 120, sortable: true, align: 'center', align: 'left' }
             ],height:305});
	  PageDownOrUp(0);

	});
function openAddNewCmpy(){
			$('#newCmpy').window('open');
		}
function closeAddNewCmpy(){
			$('#newCmpy').window('close');
		}

function closeEditCmpy(){
	 $('#editCmpy').window('close');
	}

function PageDownOrUp(flag){
	        //alert(flag);
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
			urlstr="cmpy_list?currentPage="+nowpage+"&pageSize="+pagerow;
            $.ajax({
			  type: "POST",
			  url: urlstr,
			  dataType: "json",
			  success : function(data){
					  var i=0;
                      $('#cmpy_data').find('tr').hide();
                      tableTds=$('#cmpy_data').find('td');
                      tableTds.find('div').css('text-align','center');
					  for(i=0;i<pagerow;i++)
					  {$('#cmpy_data').find('tr').eq(i).show();}
					  tableTds.find('div').html("");
					   var k=1; i=0;
					  $.each( data.Rows  , function(commentIndex, comment) {
						tableTds.eq(i++).find('div').html(k++);
						tableTds.eq(i++).find('div').html(comment['comName']);
						tableTds.eq(i++).find('div').html(comment['registerTime']);
						tableTds.eq(i++).find('div').html(comment['comLegal']);
						tableTds.eq(i++).find('div').html(comment['comPhone']);
						tableTds.eq(i++).find('div').html(comment['comLicense']);
						tableTds.eq(i++).find('div').html(comment['registerMoney']);
						tableTds.eq(i++).find('div').html(comment['comAddress']);
						tableTds.eq(i++).find('div').html(comment['comDesc']);
						var strhtml="<a href=\"#\" onclick=\"openEditWindow('#editCmpy','getCompanyByID?comid='+$(this).next().html())\">编辑</a><span style=\"display:none;width:10px\">"+comment['comId']+"</span><span style=\"display:inline-block;width:10px\"></span><a href=\"#\" onclick=\"deleteRow($(this).parent().parent().parent(),$(this).prev().prev.html())\">删除</a>";
						tableTds.eq(i++).find('div').html(strhtml);
					  });
					  $('#now_page').html(currentPage);
					  $('#total_page').html(pagesCount);
					  $('#total_record').html(rowsCount);
					  
			  }
			}); 
        }	
		
