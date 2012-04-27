<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户组</title>
</head>
<body>
<form name="form1" id="form1" method="post" action="addGroup">
         <div class="rowStyle">
             <div><span >用户组名称:</span><span><input type="text" name="group.groupName"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >用户组描述:</span><span><input type="text" name="group.groupDesc"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >用户组级别:</span>
                  <span><select name="group.groupLevel" id="level" onchange="levelChanged()">
                          <option value="1" selected="selected">1：系统级用户组</option>
                          <option value="2">2：公司级用户组</option>
                          <option value="3">3：小区级用户组</option>
                       </select>
                  </span>
             </div>
         </div>
         <div class="rowStyle" id="span_fatherGroupId" style="display:none">
             <div><span >父亲用户组:</span>
                  <span><select name="group.fatherGroupId" id="fatherGroupId" disabled="disabled" onchange="fateherGroupChanged()">
                        </select>
                  </span>
             </div>
         </div>
         <div class="rowStyle" id="span_refDomain"  style="display:none">
             <div><span >关联域:</span>              
                 <span><select name="group.refDomain" id="refDomain" disabled="disabled">
                       </select>
                 </span>
            </div>
         </div>
          <div class="rowStyle">
              <div style=" margin-left:100px;margin-top:20px"><input type="submit" value="提交" onclick="return  addFormCheck(); "/>
               <input type="button" value="关闭" onclick="addClose(); "/></div>
           </div>
</form>
</body>
</html>