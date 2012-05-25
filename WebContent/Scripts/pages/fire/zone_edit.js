var select_roleId = document.getElementById("projectId");

var roleId = document.getElementById("proId").value;

UpdateSelectedItem(select_roleId,roleId);

function UpdateSelectedItem(objSelect, objItemValue) {
    for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == objItemValue) {
	        objSelect.options[i].selected=true;
            break;       
        }       
    }
}
