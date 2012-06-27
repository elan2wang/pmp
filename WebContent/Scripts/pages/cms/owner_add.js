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
    objTab1 = document.getElementById("Tab21");
    objTab2 = document.getElementById("Tab22");
    objP1.style.display = "none";
    objP2.style.display = "none";
    objTab1.className = "ModuleTap";
    objTab2.className = "ModuleTap";
}
function to(page) {
    objP1 = document.getElementById("P1");
    objP2 = document.getElementById("P2");
    objTab1 = document.getElementById("Tab21");
    objTab2 = document.getElementById("Tab22");
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
function projectChanged(builId,houseId,houseNum)
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
				  buildingChanged(houseId,houseNum);
		      }
	  	  }
	  }
	});
}


function buildingChanged(houseid,houseNum){
	var buildingId = document.getElementById("buildingId").value;
	$.ajax({
		type: "POST",
		url: "selectHouse_ByBuil?builId="+buildingId+"&isEmpty=1",
		dataType:"json",
		success:function(data){
			var houseId=$('#houseId');
			var option = houseId.find('option');
			for(var i=1;i<=option.length;i++){
				option.eq(i).remove();
			  }
			if(data)
		  	{	
				if(houseid!=undefined&&houseNum!=undefined)houseId.append('<option selected="selected" value="'+houseid+'">'+houseNum+'</option>');		
				$.each(data.Rows,function(commentIndex,comment){
				     houseId.append('<option value="'+comment['houseId']+'">'+comment['houseNum']+'</option>');
			    });
		  	}
		}
	});
}

function strim(str){
	return str.replace(/(^\s*)|(\s*$)/g,""); 
}

function FormCheck(){
	/* 必填字段校验 */
	var obj1 = document.getElementById("owner.ownerName");
	var obj2 = document.getElementById("owner.mobile");
	var obj3 = document.getElementById("houseId");
	var obj4 = document.getElementById("owner.houseArea");
	var isMobilePattern = /^(13|15|18)[0-9]{9}$/;
	var isNumber = /^[1-9][0-9]{1,2}.?[0-9]{1,2}$/;
	if(strim(obj1.value)==""){
		alert("用户名不能为空");
		obj1.focus();
		return (false);
	}
	if(strim(obj2.value)==""){
		alert("电话号码不能为空");
		obj2.focus();
		return (false);
	}
	if(isMobilePattern.test(strim(obj2.value))==false){
		alert("手机号码格式有误");
		obj2.focus();
		return (false);
	}
	if(obj3.value==null||strim(obj3.value)==""||strim(obj3.value)=="null"){
		alert("您还没有选择房屋信息");
		obj3.focus();
		return (false);
	}
	if(strim(obj4.value)==""){
		alert("房屋面积不能为空");
		obj4.focus();
		return (false);
	}
	if(isNumber.test(strim(obj4.value))==false){
		alert("房屋面积格式有误");
		obj4.focus();
		return (false);
	}
	
	/* set hidden input projectName */
	document.getElementById("projectName").value = document.getElementById("projectId").options[document.getElementById("projectId").selectedIndex].text;
	/* set hidden input buildingNum */
	document.getElementById("buildingNum").value = document.getElementById("buildingId").options[document.getElementById("buildingId").selectedIndex].text;
	/* set hidden input houseNum */
	document.getElementById("houseNum").value = document.getElementById("houseId").options[document.getElementById("houseId").selectedIndex].text;
	
	to('P2');
	return true;
}