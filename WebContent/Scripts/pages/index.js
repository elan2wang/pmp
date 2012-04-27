// JavaScript Document
    var itemnow = null;
    function button(obj, action, URL) {
        var buttonArr = document.getElementById("mm").getElementsByTagName("div");

        if (action == "onclick") {
            for (i = 0; i < buttonArr.length; i++) {
                if (buttonArr[i] == obj) {
                    buttonArr[i].className = "LMIclick";
                    window.mainFrame.location.href = URL;
                    itemnow = buttonArr[i];
                }
                else {
                    buttonArr[i].className = "LMIitem";
                }
            }
        }
        if (action == "over") {
            if (itemnow != obj) {
                obj.className = "LMIover";
            }
        }
        if (action == "out") {
            if (itemnow != obj) {
                obj.className = "LMIitem";
            }
        }

    }
    function todayDate() {
        var charDay = new Array();
        charDay[0] = "日";
        charDay[1] = "一";
        charDay[2] = "二";
        charDay[3] = "三";
        charDay[4] = "四";
        charDay[5] = "五";
        charDay[6] = "六";
        today = new Date(); //申明一个时间对象
        intDay = today.getDay(); //返回当前的星期
        intDate = today.getDate(); //返回当前的天日期
        intMonth = today.getMonth() + 1; //月加1
        intYear = today.getFullYear(); //返回年
        datestr = intYear + "年" + intMonth + "月" + intDate + "日 星期" + charDay[intDay];
        document.write(datestr);
    }