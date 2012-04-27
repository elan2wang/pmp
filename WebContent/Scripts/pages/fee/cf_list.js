// JavaScript Document
$(function(){
	$('#cf_list').flexigrid({
        colModel:[
            { display: '序号',  width: 30,  align: 'center' },
            { display: '房号',  width: 60,  align: 'center' },
            { display: '业主姓名', width: 50, align: 'center' },
            { display: '缴费状态', width: 50, align: 'center' },
            { display: '应收金额', width: 60, align: 'center' },
            { display: '实收金额', width: 60, align: 'center' },
            { display: '缴费时间', width: 70, align: 'center' },
            { display: '操作',  width: 150, align: 'center' }
        ],
        height:310
	});
});

function changeState(the)
{
	$("#but_all").addClass('linkbut');
	$("#but_no").addClass('linkbut');
	$(the).removeClass('linkbut');
}
function changeState2(the)
{
  $(the).removeClass('linkbut');
}
function FormCheck()
{
	document.getElementById("form1").submit();
	closeFeeEntry();
}
function Close()
{
	closeFeeEntry();
}