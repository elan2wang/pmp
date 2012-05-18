/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-4-10
 * 
 * this script is used by the owner_add.jsp
 */ 

function pageInt() {
    objP1 = document.getElementById("P1");
    objP2 = document.getElementById("P2");
    objTab1 = document.getElementById("Tab1");
    objTab2 = document.getElementById("Tab2");
    objP1.style.display = "none";
    objP2.style.display = "none";
    objTab1.className = "ModuleTap";
    objTab2.className = "ModuleTap";
}
function to(page) {
    objP1 = document.getElementById("P1");
    objP2 = document.getElementById("P2");
    objTab1 = document.getElementById("Tab1");
    objTab2 = document.getElementById("Tab2");
    if (page == "P2") {
        pageInt();
        objP2.style.display = "block";
        objTab2.className = "ModuleTapOn";
    }
    if (page == "P1") {
        pageInt();
        objP1.style.display = "block";
        objTab1.className = "ModuleTapOn";
    }
}

/* when method defined in owner_add.js whose name is 'owner_edit_init' */
/* invoke this method,it will contain parameters, otherwise there is no parameters */
function projectChanged(builId,houseId)
{
	 var projectId=document.getElementById("projectId").value;
	 $.ajax({
	  type: "POST",
	  url: "selectBuilding_ByPro?proId="+projectId,
	  dataType: "json",
	  success : function(data){
	      var selector=$('#buildingId'); 
	      var house=$('#houseId'); 
		  var s=selector.find('option');
		  var s_house=house.find('option');
		  for(var i=1;i<s.length;i++){
		     s.eq(i).remove();
		  }
		  for(var j=1;j<s_house.length;j++){
			  s_house.eq(j).remove();
		  }
		  if(data)
	  	  {
		      var succ = false;
			  $.each( data.Rows , function(commentIndex, comment) {
				  if(builId && builId==comment['builId'])
				  {
					  selector.append('<option selected="selected" value="'+comment['builId']+'">'+comment['builNum']+'</option>');
					  succ = true;
				  }
				  else
				  {
					  selector.append('<option value="'+comment['builId']+'">'+comment['builNum']+'</option>');
				  }
			  });
			  if(succ && houseId){
				  buildingChanged(houseId);
		      }
	  	  }
	  }
	});
}

/* when method defined in owner_add.js whose name is 'owner_edit_init' */
/* invoke this method,it will contain parameters, otherwise there is no parameters */
function buildingChanged(houseid){
	var buildingId = document.getElementById("buildingId").value;
	$.ajax({
		type: "POST",
		url: "selectHouse_ByBuil?builId="+buildingId,
		dataType:"json",
		success:function(data){
			var houseId=$('#houseId');
			var option = houseId.find('option');
			for(var i=1;i<=option.length;i++){
				option.eq(i).remove();
			  }
			if(data)
		  	{			
				$.each(data.Rows,function(commentIndex,comment){
					 if(houseid && houseid==comment['houseId'])
					 {
					     houseId.append('<option selected="selected" value="'+comment['houseId']+'">'+comment['houseNum']+'</option>');
					 }
					 else
					 {
				         houseId.append('<option value="'+comment['houseId']+'">'+comment['houseNum']+'</option>');
					 }
				 });
		  	}
		}
	});
}

function FormCheck(){
	/* set hidden input projectName */
	document.getElementById("projectName").value = document.getElementById("projectId").options[document.getElementById("projectId").selectedIndex].text;
	/* set hidden input buildingNum */
	document.getElementById("buildingNum").value = document.getElementById("buildingId").options[document.getElementById("buildingId").selectedIndex].text;
	/* set hidden input houseNum */
	document.getElementById("houseNum").value = document.getElementById("houseId").options[document.getElementById("houseId").selectedIndex].text;
	
	to('P2');
}