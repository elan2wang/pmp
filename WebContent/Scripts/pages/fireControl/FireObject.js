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
        alert("stop");
    	clearInterval(this.TimerID);
    	this.Obj.css("visibility" ,"visible");
    }
    this.startFireAlarm=function(){
    	id="s"+this.ID;
  	    $("#head",window.parent.document).append('<bgsound id='+id+'   src= "../fireConfig/FIRE.WAV"   loop=-1   volume=4>');
    }
    this.stopFireAlarm=function(){
    	$("#s"+this.ID).attr("src","");
  	    this.stopBlink(this.Obj);
    }
}