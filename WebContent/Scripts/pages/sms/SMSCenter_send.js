// JavaScript Document
$(function(){
	
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	$("#tab2").click(function(){
		    document.getElementById("SMSsended_Frame").src="SMSCenter_sended.jsp";
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(1).show();
			//document.getElementById("frame.pageType").value="all";
			return false;
		});

	});


function AddReceiverList(arr)
{
	//alert("祖父"+arr.length);
    objreceiverList = document.getElementById("receiverList");
    objrecevierCount = document.getElementById("recevierCount");
    var recvierCount = new Number(objrecevierCount.innerHTML);

	for(var i=0;i<arr.length;i++)
	{
		phone=arr[i];
		var objOption = new Option(phone, phone);
        objreceiverList.add(objOption, 0);
	}
	objrecevierCount.innerHTML=recvierCount+arr.length;
}
function FindReceiver()
{
	document.getElementById("SMSlist_Frame").src="SMSreceiver_list.jsp";
}
function closeReceiver()
{
	document.getElementById("SMSlist_Frame").src="";
}
//键盘事件控制
$(function () {
    $("#receiverNumber").keydown(function (evt) { if (evt.keyCode == 0xd) $('#buttonAddReceiver').click(); });
    //$("#delAll").bind("click",function(){delAll(); }); 
    //$("#check").bind("click",function(){check(); }); 
    //var msg =$('#text')[0];
    //checkOverFlow(msg);
})

function addReceiver() {
    objreceiverNumber = document.getElementById("receiverNumber");
    objreceiverList = document.getElementById("receiverList");
    objrecevierCount = document.getElementById("recevierCount");
    phone = objreceiverNumber.value;
    var recvierCount = new Number(objrecevierCount.innerHTML);

    for (i = 0; i < objreceiverList.length; i++) {
        if (objreceiverList[i].value == phone) {
            //alert(objreceiverList[i]);
            alert("该号码已在列表中！");
            return;
        }
    }
    if (isLegalNumber(phone)) {
        var objOption = new Option(phone, phone);
        objreceiverList.add(objOption, 0);
        objreceiverNumber.value = "";
        objrecevierCount.innerHTML = recvierCount + 1;
        objreceiverNumber.focus();
    }
    else {
        alert("接收人号码格式错误！");
        objreceiverNumber.focus();
        return;
    }
}

function isLegalNumber(msg) {
    if (/^(13|15|18)[0-9]{9}$/.test(msg) == false) {
        return false;
    }
    else {
        return true;
    }

}

function removeReceiver() {
    objreceiverList = document.getElementById("receiverList");
    objrecevierCount = document.getElementById("recevierCount");
    var recvierCount = new Number(objrecevierCount.innerHTML);

    if (objreceiverList.length > 0) {
        objreceiverList.remove(objreceiverList.selectedIndex);
    }
    if (recvierCount > 0) {
        objrecevierCount.innerHTML = recvierCount - 1;
    }
}

function removeAllReceiver() {
    objreceiverList = document.getElementById("receiverList");
    objrecevierCount = document.getElementById("recevierCount");

    //	for(i=0;i<objreceiverList.length;i++){
    //		objreceiverList.remove(i);
    //	}
    objreceiverList.length = 0;
    objrecevierCount.innerHTML = 0;
}

function deleteText() {
    objtext = document.getElementById("text");
    objtext.value = "";
    objtext.focus();
}

function textCount() {
    objcurrenttext = document.getElementById("text");
    objcurrentWords = document.getElementById("currentWords");
    objtotalMsg = document.getElementById("totalMsg");
    objremainWords = document.getElementById("remainWords");
    var currentWordsCount = new Number(objcurrentWords.innerHTML);
    var totalMsgCount = new Number(objtotalMsg.innerHTML);
    var remainWordsCount = new Number(objremainWords.innerHTML);

    currentWordsCount = objcurrenttext.value.length;
    totalMsgCount = Math.floor(currentWordsCount / 70) + 1;
    remainWordsCount = 140 - currentWordsCount;
    objcurrentWords.innerHTML = currentWordsCount;
    objtotalMsg.innerHTML = totalMsgCount;
    objremainWords.innerHTML= remainWordsCount;
}

function receiverListTransfer() {
    objreceiverList = document.getElementById("receiverList");
    objrealReceiverList = document.getElementById("realReceiverList");

    strValues = "";
    for (i = objreceiverList.length; i > 0; i--) {
        if (i == objreceiverList.length) {
            strValues = objreceiverList[i - 1].value;
        }
        else {
            strValues = strValues + "," + objreceiverList[i - 1].value;
        }
    }
    objrealReceiverList.value = strValues;
}

function formcheck() {
    objreceiverList = document.getElementById("receiverList");
    objcurrenttext = document.getElementById("text");

    if (objreceiverList.length == 0) {
        alert("接收不能人为空！");
        return;
    }
    if (objcurrenttext.value == "") {
        alert("短信内容不能为空！");
        return;
    }
}
function receiverListID_onclick() {

}
function submit(){
	document.getElementById('button6').submit();
}