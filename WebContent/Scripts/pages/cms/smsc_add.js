/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-4-17
 * 
 * this script is used by the smsc_list.jsp
 */ 
function loadCompanyList(){
	$.ajax({
		type: "POST",
		url: "select_company_list",
		dataType: "json",
		success: function(data){
			var selector1 = $('#comId');
			selector1.find('option').remove();
			$.each( data.Rows,function(commentIndex, comment){
				selector1.append("<option value=\""+comment['comId']+"\">"+comment['comName']+"</option>");
			});
		}
	});
}

function addFormCheck()
	{
		document.getElementById("form1").submit();
		closeAddSMSC();
	}
	function addClose()
	{
		closeAddSMSC();
	}
	function editFormCheck()
	{
		document.getElementById("form1").submit();
		closeEditSMSC();
	}
	function editClose()
	{
		closeEditSMSC();
	}