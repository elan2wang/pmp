 Array.prototype.indexOf = function(val) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == val) return i;
            }
            return -1;
        };
 Array.prototype.remove = function(val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        };
function FireAlarm(id,obj)
{
    //添加属性
    this.ID = id;
    this.TimerID;
    this.Obj=obj;
    //添加方法
    this.showMessage = function()
    {
        alert("ID: " + this.ID );
    }
    this.startBlink=function(){
    	
    	if(!this.Obj)
    		return;
    	this.TimerID=setInterval(_Blink(this.Obj),300);
    }
    this.stopBlink=function(){
    	if(!this.Obj)
    		return;
    	clearInterval(this.TimerID);
    	this.Obj.find("img").css("visibility" ,"visible");
    }

}
function ZoneFireInfo(zoneID,deviceID,type){
	this.ID=zoneID;
	this.deviceID=deviceID;
	this.Type=type;
}
