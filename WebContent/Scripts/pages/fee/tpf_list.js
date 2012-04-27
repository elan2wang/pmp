/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-4-18
 * 
 * this script is used by the tpf_list.jsp
 */ 
$(function(){
	$('#tpf_list_data').html(AddTds(30,9));
	$('#tpf_list').flexigrid({
		colModel:
			[{ display:'序号', width:50, align:'center' },
			 { display:'物业项目', width:50, align:'center' },
			 { display:'停车日期', width:50, align:'center' },
			 { display:'车牌号', width:50, align:'center' },
			 { display:'停车时间', width:50, align:'center' },
			 { display:'收费标准', width:50, align:'center' },
			 { display:'收费金额', width:50, align:'center' },
			 { display:'收费人员', width:50, align:'center' },
			 { display:'状态', width:50, align:'center' }
			],
		height:310
	});
});

function loadData(proId){
	var currentPage=parseInt(document.getElementById("now_page").innerHTML);
	var pageSize=parseInt(document.getElementById("page_row").options[document.getElementById("page_row").selectedIndex].text);
	var urlStr="loadTimeParkFeeByProject?proId="+proId+"&currentPage="+currentPage+"&pageSize="+pageSize;
	alert("proId="+proId);
	$.ajax({
		type: "POST",
		url: urlStr,
		dataType: "json",
		success: function(data){
			//先隐藏table的所有行，然后根据pageSize显示指定数量的数据行
			$('tpf_list_data').find('tr').hide();
			var i;
			alert("pageSize="+pageSize);
			for(i=0;i<pageSize;i++){
				$('#tpf_list_data').find('tr').eq(i).show();
			}
			//清空table所有单元格的数据
			var tableTds=$('#tpf_list_data').find('td');
			tableTds.find('div').css('text-align','center');
			tableTds.find('div').html("");
			
			//异步获取Json数据给table的单元格赋值
			var k=1; //记录序号
			i=0;
			$.each(data.Rows,function(commentIndex,comment){
				tableTds.eq(i++).find('div').html((k++)+"<input type='checkbox' id='checkgroup' name='checkgroup'/>");
				tableTds.eq(i++).find('div').html(comment['project']);
				tableTds.eq(i++).find('div').html(comment['parkDate']);
				tableTds.eq(i++).find('div').html(comment['carNum']);
				tableTds.eq(i++).find('div').html(comment['parkTime']);
				tableTds.eq(i++).find('div').html(comment['parkFeeRate']);
				tableTds.eq(i++).find('div').html(comment['fetchMoney']);
				tableTds.eq(i++).find('div').html(comment['fetchPerson']);
				tableTds.eq(i++).find('div').html(comment['state']);
			});
			alert("aaa");
			$('#now_page').html(data.CurrentPage);
			$('#total_page').html(data.PagesCount);
		}
	});
}

function openImport(){
	alert("aaa");
	$('#tpfImport').window('open');
}