$(function(){
	$('#cf_list').flexigrid({
        colModel:[
            { display: '序号',  width: 30,  align: 'center' },
            { display: '时间',  width: 60,  align: 'center' },
            { display: '状态', width: 50, align: 'center' },
            { display: '应收金额',  width: 60,  align: 'center' },
            { display: '实收金额', width: 50, align: 'center' },
            { display: '收费时间', width: 60, align: 'center' },
            { display: '操作',  width: 150, align: 'center' }
        ],
        height:360
	});
});