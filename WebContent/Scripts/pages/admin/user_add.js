/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create: 2012-3-30
 * 
 * this script is used for user_add.jsp
 */ 

/**
 * invoked when page onload
 * 
 * load rolelist and grouplist
 */

function strim(str){
	return str.replace(/(^\s*)|(\s*$)/g,""); 
}
function FormCheck(){
			objfc1=document.getElementById("user.realname");
			objfc2=document.getElementById("user.username");
			objfc3=document.getElementById("user.mobile");
			objfc4=document.getElementById("user.identify");
			objfc5=document.getElementById("user.position");
			objfc6=document.getElementById("user.userDesc");
			objfc7=document.getElementById("roleId");
			objfc8=document.getElementById("groupId");
			
			//objfc8=document.getElementById("building.project.proId");
			var isPhonePattern = /\d{3}-\d{8}|\d{4}-\d{7}/;
			var isMobilePattern = /^(13|15|18)[0-9]{9}$/;
			var isIdentifyPattern = /^d{17}(\d|X)$/;
			if(strim(objfc1.value)==""){
				alert("真实姓名不能为空");
				objfc1.focus();
				return (false);
			}
			if(strim(objfc2.value)==""){
				alert("用户名不能为空");
				objfc2.focus();
				return (false);
			}
			if(isPhonePattern.test(objfc3.value)==false && isMobilePattern.test(objfc3.value)==false){
				alert("公司电话不符合电话号或手机号码的规则")	;
				objfc3.focus();
				return(false);
			}
			if(isIdentifyPattern.test(objfc4.value)==false){
				alert("身份证号不规范");
				objfc4.focus();
				return (false);
			}
			if(strim(objfc5.value)==""){
				alert("职位不能为空");
				objfc5.focus();
				return (false);
			}
			if(strim(objfc6.value)==""){
				alert("用户描述不能为空");
				objfc6.focus();
				return (false);
			}
			if(strim(objfc7.value)==""){
				alert("请选择角色");
				objfc7.focus();
				return (false);
			}
			if(strim(objfc8.value)==""){
				alert("请选择用户组");
				objfc8.focus();
				return (false);
			}
			
			//检查楼号是否重复
			return check_User(strim(objfc2.value));
		}
function check_User(userName) {
			var url = "checkUser?userName="+userName;
		    	$.ajax({
					type: "POST",
					url: url,
					dataType:"json",
					success : function(data){					
						var result = data["result"];
						if(result=="Failed")
						{
							alert("已存在该用户名，请核对！");
							objfc1.select();
							 return false;
						}
						else
						{
							document.getElementById("form").submit();
							closeAddNewUser();
							return true;						
						}
					}
				});    	
		}

function addClose(){
	closeAddNewUser();
}
function loadRoleList(){
	$.ajax({
		type: "POST",
		url: "load_Role_list",
		dataType: "json",
		success: function(data){
			var roleSelector = $("#roleId");
			roleSelector.find('option').remove();
			//roleSelector加载option选项
			$.each( data.Rows,function(commentIndex, comment){
				roleSelector.append("<option value=\""+comment['roleId']+"\">"+comment['roleName']+"</option>");
			});
			roleChange();
		}
	});
}

function roleChange(groupId){
	var roleId = document.getElementById('roleId').value;
	$.ajax({
		type: "POST",
		url: "load_Group_list?roleId="+roleId,
		dataType: "json",
		success: function(data){
			var groupSelector = $("#groupId");
			groupSelector.find('option').remove();
			//roleSelector加载option选项
			$.each( data.Rows,function(commentIndex, comment){
				if(groupId!='undefined' && groupId==comment['groupId']){
					groupSelector.append("<option selected='selected' value=\""+comment['groupId']+"\">"+comment['groupName']+"</option>");
				} else {
					groupSelector.append("<option value=\""+comment['groupId']+"\">"+comment['groupName']+"</option>");
				}
			});
		}
			
	});
}


