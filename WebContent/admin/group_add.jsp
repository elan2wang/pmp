<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../Scripts/pages/admin/group_info.js"></script>
<title>添加用户组</title>
</head>
<body>
<div class="window_content">
<form name="form1" id="form1" method="post" action="addGroup">
   <div class="rowStyle">
       <div><span >用户组名称:</span><span><input type="text" class="textbox" name="group.groupName"/></span></div>
   </div>
   <div class="rowStyle">
       <div><span >用户组描述:</span><span><input type="text" class="textbox" name="group.groupDesc"/></span></div>
   </div>
   <div class="rowStyle">
       <div><span >用户组级别:</span><span><select name="group.groupLevel" id="level" class="selectbox" onchange="levelChanged()">
                    <option value="1" selected="selected">1：系统级用户组</option>
                    <option value="2">2：公司级用户组</option>
                    <option value="3">3：小区级用户组</option>
                 </select>
            </span>
       </div>
   </div>
   <div class="rowStyle">
       <div><span >父亲用户组:</span><span><select name="group.fatherGroupId" class="selectbox" id="fatherGroupId" onchange="fatherGroupChanged()"> 
                  <option value=0>根用户组</option>
                  </select>
            </span>
       </div>
   </div>
   <div class="rowStyle">
       <div><span >关&nbsp;&nbsp;联&nbsp;&nbsp;域:</span><span><select name="group.refDomain" id="refDomain" class="selectbox">
                 <option value="空关联域">空关联域</option>
                 </select>
           </span>
      </div>
   </div>
   <div class="rowStyle">
       <div style=" margin-left:100px;margin-top:20px">
       <input type="submit" value="提交" onclick="return addFormCheck(); "/>
       <input type="button" value="取消" onclick="closeWindow('#newGroup')"/></div>
   </div>
</form>
</div>
</body>
</html>