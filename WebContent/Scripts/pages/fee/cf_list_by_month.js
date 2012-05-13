$(function(){
	$('#cf_list').flexigrid({
        colModel:[
            { display: '序号',  width: 30,  align: 'center' },
            { display: '小区',  width: 100,  align: 'center' },
            { display: '房号', width: 50, align: 'center' },
            { display: '业主',  width: 60,  align: 'center' },
            { display: '状态', width: 50, align: 'center' },
            { display: '应收金额', width: 60, align: 'center' },
            { display: '实收金额',  width: 60, align: 'center' },
            { display: '录入时间',  width: 60, align: 'center' }
        ],
        height:360
	});
});