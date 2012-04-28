/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-4-28
 * 
 * this script is used by the remove operations
 */ 
function RemoveAll(src,target){
    $(target).find("option").appendTo($(src));
    $(target).find("option").remove();
}
function AddAll(src,target){
    $(src).find("option").appendTo($(target));
    $(src).find("option").remove();
    $(target).find("option").attr("selected",true);
}
function Add(src,target){
    $(src).find("option:selected").appendTo($(target));
    $(src).find("option:selected").remove();
    $(target).find("option").attr("selected",true);
}
function Remove(src,target){
    $(target).find("option:selected").appendTo($(src));
    $(target).find("option:selected").remove();
}
function selectAll(target){
	$(target).find("option").attr("selected",true);
}