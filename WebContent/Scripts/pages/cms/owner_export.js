function owner_export(){
	var qtype="";
	var query="";
	qtype = $('#select1').val()+","+$('#select2').val()+","+$('#select3').val();
	query += ($('#input1').val()=="")?"null,":$('#input1').val()+",";
	query += ($('#input2').val()=="")?"null,":$('#input2').val()+",";
	query += ($('#input3').val()=="")?"null":$('#input3').val();
	closeWindow('#ownerExport');
	$.blockUI({ message: "<h3><img src='../Images/loading4.gif'><br>正在生成文件，请等待...</h3>" });
	$.ajax({
		type: "POST",
		dataType: "json",
		url: "exportOwner",
		data: [{name:'qtype',value:qtype},{name:'query',value:query}],
		success: function(data){
			$.unblockUI();
			openAddWindow('#export');
			$('#download').removeAttr('style');
			$('#downLink').attr('href',data.download_link);
		}
	});
}