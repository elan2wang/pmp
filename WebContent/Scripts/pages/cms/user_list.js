// JavaScript Document

$(function(){
		   $('.hDivBox').css('width','100%');
	$('#userlist').flexigrid({colModel: [
             { display: '序号', name: 'code', width: 40, sortable: true, align: 'center' },
              { display: '公司名称（注册日期）', name: 'name', width: 300, sortable: true, align: 'left' },
             { display: '公司法人（联系方式）', name: 'addr', width: 300, sortable: true, align: 'left' },
             { display: '操作', name: 'cil4', width: 130, sortable: true, align: 'left', align: 'left',hide:'true' }
             ]});
	$('#templatelist').flexigrid();
	$('#nextpage1').click(function(){
		alert('您点击了《a》');
		$.ajax({
			  type: "GET",
			  url: "datagrid_data.json",
			  dataType: "json",
			  success : function(data){
					 alert(data.total);
					  var html = '';
					  var i=0;var j=0;
					  $.each( data.rows  , function(commentIndex, comment) {
						$('#user_data1').find('td').eq(j=(i++)).find('div').append(comment['code']);//comment['code'].toString();
						$('#user_data1').find('td').eq(j).find('div').css('text-align','center');
						$('#user_data1').find('td').eq(j=(i++)).find('div').append(comment['name']);
						$('#user_data1').find('td').eq(j).find('div').css('text-align','center');
						$('#user_data1').find('td').eq(j=(i++)).find('div').append(comment['addr']);
						$('#user_data1').find('td').eq(j).find('div').css('text-align','center');
						$('#user_data1').find('td').eq(j=(i++)).find('div').append("<a href=\"#\">编辑</a><a href=\"#\">删除</a>");
						$('#user_data1').find('td').eq(j).find('div').css('text-align','center');
					  });
			  }
			}); 
	});
	$('#nextpage').click(function(){
		alert('您点击了《a》');
		$.ajax({
			  type: "GET",
			  url: "datagrid_data.json",
			  dataType: "json",
			  success : function(data){
					 alert(data.total);
					  var i=0;var j=0;
					  $.each( data.rows  , function(commentIndex, comment) {
						$('#user_data').find('td').eq(j=(i++)).find('div').append(comment['code']);//comment['code'].toString();
						$('#user_data').find('td').eq(j).find('div').css('text-align','center');
						$('#user_data').find('td').eq(j=(i++)).find('div').append(comment['name']);
						$('#user_data').find('td').eq(j).find('div').css('text-align','center');
						$('#user_data').find('td').eq(j=(i++)).find('div').append(comment['addr']);
						$('#user_data').find('td').eq(j).find('div').css('text-align','center');
						$('#user_data').find('td').eq(j=(i++)).find('div').append(comment['col4']);
						$('#user_data').find('td').eq(j).find('div').css('text-align','center');
					  });
			  }
			}); 
	});
 });