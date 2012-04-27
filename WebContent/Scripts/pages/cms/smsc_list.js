/**
 * author: ChrussyGe
 * email： chrussyge@gmail.com
 * create:   2012-4-17
 * 
 * this script is used by the smsc_list.jsp
 */
$(function(){
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	  $('#smsclist').flexigrid({colModel: [
             { display: '序号',  width: 40,  align: 'center' },
             { display: '信息机名称', width: 100, align: 'center' },
			 { display: '信息机上行地址', width: 200, align: 'center' },
             { display: '信息及下行地址', width: 200,align: 'center' },
			 { display: '应用账号', width: 100, align: 'center' },
			 { display: '应用扩展码', width: 100, align: 'center' },
			 { display: '公司名称', width: 100, align: 'center' },
             { display: '操作',  width:100, sortable: true, align: 'center', align: 'center' }
             ],height:305});
	});
function openAddSMSC(){
	
	$('#newSmsc').window({
        top:10,   
        left:($(window).width() - 450) * 0.5
	 });
    $('#newSmsc').window('open');
    loadCompanyList();
}
function closeAddSMSC(){
	//alert("close");
    $('#newSmsc').window('close');
}
function deleteRow(obj,id)
{
	if(!confirm("确定删除？"))
	{
		return;
	}
	$.ajax({
		type: "POST",
		url: "deleteSMSCompany?smscId="+id,
	    dataType: "json",
	    success : function(data){
	    	obj.hide();
	    }
    });
}
var windowsOpened=false;
function openEditSMSC(id){
	//alert(id);
	$('#editSmsc').window({
        top:10,   
        left:($(window).width() - 450) * 0.5,
        href:'getSMSCompany?smscId='+id
	 });
    $('#editSmsc').window('open');
	if (windowsOpened) $('#editSmsc').window('refresh');
    windowsOpened = true;
    loadCompanyList();
}
function closeEditSMSC(){
    $('#editSmsc').window('close');
}
