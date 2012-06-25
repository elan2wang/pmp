<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../Scripts/pages/cms/owner_edit.js"></script>
<title>无标题文档</title>
</head>

<body>

<form id="form1" name="form1" action="editOwner" method="post">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="20" height="25" class="TopNav">&nbsp;</td>
          <td width="80" height="25" align="center" valign="middle" class="ModuleTapOn" id="eTab1">1：业主信息</td>
          <td width="80" align="center" valign="middle" class="ModuleTap" id="eTab2">2：成员信息</td>
          <td class="TopNav">&nbsp;</td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td style="padding-top:5px; padding-bottom:5px; border-bottom:1px #6c92ad solid;">
      <div id="eP1">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" valign="middle"><table border="0" align="center" cellpadding="2" cellspacing="2" class="table1">
              <tr style="display:none">
                <td colspan="4">
                <input type="hidden" name="owner.ownerId" id="owner.ownerId" value="${owner.ownerId}"/>
              	<input type="hidden" name="gender" id="gender" value="${owner.gender}"/>
              	<input type="hidden" name="nationality" id="nationality" value="${owner.nationality}"/>
              	<input type="hidden" name="ismarried" id="ismarried" value="${owner.ismarried}"/>
              	<input type="hidden" name="identityType" id="identityType" value="${owner.identityType}"/>
              	<input type="hidden" id="proId" name="proId" value="${project.proId}"/>
              	<input type="hidden" name="builId" id="builId" value="${building.builId}"/>
              	<input type="hidden" name="houseId2" id="houseId2" value="${house.houseId}"/>
              	<input type="hidden" name="houseNum2" id="houseNum2" value="${house.houseNum}"/>
              	<input type="hidden" name="objName" id="objName" value="${objName}"/>
                </td>
              </tr>
              <tr>
                <td width="70" height="30" align="center" valign="middle">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
                <td width="180" align="left" valign="middle"><input name="owner.ownerName" type="text" class="textbox" id="owner.ownerName" value="${owner.ownerName}"/><span style="color:red">*</span></td>
                <td width="70" align="center" valign="middle">性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
                <td width="180" align="left" valign="middle"><select name="owner.gender" class="selectbox" id="select_gender" >
                  <option value="null" selected="selected">－－请选择－－</option>
                  <option value="男">男</option>
                  <option value="女">女</option>
                </select></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">民&nbsp;&nbsp;&nbsp;&nbsp;族：</td>
                <td align="left" valign="middle"><select name="owner.nationality" class="selectbox" id="owner.nationality" >
                <option selected="selected" value="0">－－请选择－－</option>
                <option value="汉族">汉族</option>
                <option value="蒙古族">蒙古族</option>
                <option value="彝族">彝族</option>
                <option value="侗族">侗族</option>
                <option value="哈萨克族">哈萨克族</option>
                <option value="畲族">畲族</option>
                <option value="纳西族">纳西族</option>
                <option value="仫佬族">仫佬族</option>
                <option value="仡佬族">仡佬族</option>
                <option value="怒族">怒族</option>
                <option value="保安族">保安族</option>
                <option value="鄂伦春族">鄂伦春族</option>
                <option value="回族">回族</option>
                <option value="壮族">壮族</option>
                <option value="瑶族">瑶族</option>
                <option value="傣族">傣族</option>
                <option value="高山族">高山族</option>
                <option value="景颇族">景颇族</option>
                <option value="羌族">羌族</option>
                <option value="锡伯族">锡伯族</option>
                <option value="乌孜别克族">乌孜别克族</option>
                <option value="裕固族">裕固族</option>
                <option value="赫哲族">赫哲族</option>
                <option value="藏族">藏族</option>
                <option value="布依族">布依族</option>
                <option value="白族">白族</option>
                <option value="黎族">黎族</option>
                <option value="拉祜族">拉祜族</option>
                <option value="柯尔克孜族">柯尔克孜族</option>
                <option value="布朗族">布朗族</option>
                <option value="阿昌族">阿昌族</option>
                <option value="俄罗斯族">俄罗斯族</option>
                <option value="京族">京族</option>
                <option value="门巴族">门巴族</option>
                <option value="维吾尔族">维吾尔族</option>
                <option value="朝鲜族">朝鲜族</option>
                <option value="土家族">土家族</option>
                <option value="傈僳族">傈僳族</option>
                <option value="水族">水族</option>
                <option value="土族">土族</option>
                <option value="撒拉族">撒拉族</option>
                <option value="普米族">普米族</option>
                <option value="鄂温克族">鄂温克族</option>
                <option value="塔塔尔族">塔塔尔族</option>
                <option value="珞巴族">珞巴族</option>
                <option value="苗族">苗族</option>
                <option value="满族">满族</option>
                <option value="哈尼族">哈尼族</option>
                <option value="佤族">佤族</option>
                <option value="东乡族">东乡族</option>
                <option value="达斡尔族">达斡尔族</option>
                <option value="毛南族">毛南族</option>
                <option value="塔吉克族">塔吉克族</option>
                <option value="德昂族">德昂族</option>
                <option value="独龙族">独龙族</option>
                <option value="基诺族">基诺族</option>
                </select></td>
                <td align="center" valign="middle">籍&nbsp;&nbsp;&nbsp;&nbsp;贯：</td>
                <td width="220" align="left" valign="middle"><input name="owner.native_" type="text" class="textbox" id="owner.native_" value="${owner.native_}"/></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">出生年月：</td>
                <td align="center" valign="middle"><input name="owner.birthday" type="text" class="textbox" id="owner.birthday" readonly="readonly"  style="cursor:pointer;" onFocus="WdatePicker()" value="<fmt:formatDate value="${owner.birthday}" type="both" pattern="yyyy-MM-dd"/>"/></td>
                <td align="center" valign="middle">婚姻状况：</td>
                <td align="left" valign="middle"><select name="owner.ismarried" class="selectbox" id="owner.ismarried" >
                  <option value="null" selected="selected">－－请选择－－</option>
                  <option value="true">已婚</option>
                  <option value="false">未婚</option>
                </select></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">工作单位：</td>
                <td align="left" valign="middle"><input name="owner.organization" type="text" class="textbox" id="owner.organization" value="${owner.organization}"/></td>
                <td align="center" valign="middle">个人爱好：</td>
                <td align="left" valign="middle"><input name="owner.hobby" type="text" class="textbox" id="owner.hobby"  value="${owner.hobby}"/></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">证件类型：</td>
                <td align="left" valign="middle"><select name="owner.identityType" class="selectbox" id="owner.identityType" >
                  <option value="null" selected="selected">－－请选择－－</option>
                  <option value="身份证">身份证</option>
                  <option value="户口本">户口本</option>
                  <option value="军官证">军官证</option>
                  <option value="护照">护照</option>
                  <option value="港澳通行证">港澳通行证</option>
                  <option value="台胞证">台胞证</option>
                </select></td>
                <td align="center" valign="middle">证件编号：</td>
                <td align="left" valign="middle"><input name="owner.identityCode" type="text" class="textbox" id="owner.identityCode" value="${owner.identityCode}"/></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">家庭电话：</td>
                <td align="left" valign="middle"><input name="owner.homePhone" type="text" class="textbox" id="owner.homePhone" value="${owner.homePhone}"/></td>
                <td align="center" valign="middle">手机号码：</td>
                <td align="left" valign="middle"><input name="owner.mobile" type="text" class="textbox" id="owner.mobile"  value="${owner.mobile}"/><span style="color:red">*</span></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">领房时间：</td>
                <td align="left" valign="middle"><input name="owner.getTime" type="text" value="<fmt:formatDate value="${owner.getTime }" type="both" pattern="yyyy-MM-dd"/>" class="textbox" id="owner.getTime" readonly="readonly"  style="cursor:pointer;" onFocus="WdatePicker()" /></td>
                <td align="center" valign="middle">装修时间：</td>
                <td align="left" valign="middle"><input name="owner.decorateTime" type="text" value="<fmt:formatDate value="${owner.decorateTime }" type="both" pattern="yyyy-MM-dd"/>" class="textbox" id="owner.decorateTime" readonly="readonly"  style="cursor:pointer;" onFocus="WdatePicker()" /></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">入住时间：</td>
                <td align="left" valign="middle"><input name="owner.inTime" value="<fmt:formatDate value="${owner.inTime }" type="both" pattern="yyyy-MM-dd"/>" type="text" class="textbox" id="owner.inTime" readonly="readonly"  style="cursor:pointer;" onFocus="WdatePicker()" /></td>
                <td align="center" valign="middle">车位库号：</td>
                <td align="left" valign="middle"><input name="owner.parkNum" type="text" class="textbox" id="owner.parkNum" value="${owner.parkNum}"/></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">车牌号码：</td>
                <td align="left" valign="middle"><input name="owner.carNum" type="text" class="textbox" id="owner.carNum"  value="${owner.carNum}"/></td>
                <td align="center" valign="middle">车&nbsp;型&nbsp;号：</td>
                <td align="left" valign="middle"><input name="owner.carType" type="text" class="textbox" id="owner.carType" value="${owner.carType}"/></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">所在小区：</td>
                <td align="left" valign="middle">
              	<s:action name="getProjectBySessionHander" namespace="/cms" executeResult="true"/>  
              	<input type="hidden" id="projectName" name="projectName" value=""/>          	 	                	 	
                </td>
                <td align="center" valign="middle">楼&nbsp;宇&nbsp;号：</td>
                <td align="left" valign="middle">
                <select id="buildingId" name="buildingId" class="selectbox" onchange="buildingChanged()">
                  <option value="0" value="0" selected="selected">选择楼宇</option>
                </select><span style="color:red">*</span>
                <input type="hidden" id="buildingNum" name="buildingNum" value=""/>
                </td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">房&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                <td align="left" valign="middle">
                	<select id="houseId" name="houseId" class="selectbox">
                		<option value="0" selected="selected">选择房号</option>
                	</select><span style="color:red">*</span>
                	<input type="hidden" name="houseNum" id="houseNum" value=""/>
                </td>
                <td height="30" align="center" valign="middle">房屋面积：</td>
                <td align="left" valign="middle"><input name="owner.houseArea" type="text" class="textbox" id="owner.houseArea" value="${owner.houseArea}" style="width:110px" />平方米<span style="color:red">*</span></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">储藏室号：</td>
                <td align="left" valign="middle"><input name="owner.storeroom" type="text" class="textbox" id="owner.storeroom" value="${owner.storeroom}"/></td>
                <td width="70" align="center" valign="middle">使用状况：</td>
                <td align="left" valign="middle">
                  <input type="radio" name="owner.useStyle" id="radio" value="常住" <c:if test="${owner.useStyle=='常住' }">checked="checked"</c:if> />常住
                  <input type="radio" name="owner.useStyle" id="radio2" value="租住" <c:if test="${owner.useStyle=='租住' }">checked="checked"</c:if> />租住
                  <input type="radio" name="owner.useStyle" id="radio3" value="暂住" <c:if test="${owner.useStyle=='暂住' }">checked="checked"</c:if> />暂住
                  <input type="radio" name="owner.useStyle" id="radio3" value="其他" <c:if test="${owner.useStyle=='其他' }">checked="checked"</c:if> />其他
                </td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="30" align="center" valign="bottom">
            <input type="button" name="Prev" id="Prev" value="上一步" onclick="eto('eP1');" disabled="disabled" />
　                           <input type="button" name="toP2" id="toP2" value="下一步" onclick="eFormCheck();" />
            </td>
          </tr>
        </table>
      </div>
      <div id="eP2" style="display:none;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" valign="middle">
              <table border="0" align="center" cellpadding="2" cellspacing="2" class="table1">
              <tr>
                <td height="30" colspan="4" align="center" valign="middle">家庭成员</td>
                </tr>
              <tr>
                <td width="100" height="30" align="center" valign="middle">姓名</td>
                <td width="100" align="center" valign="middle">关系</td>
                <td width="160" align="center" valign="middle">联系电话</td>
                <td width="220" align="center" valign="middle">身份证及其它有效证件编号</td>
              </tr>
              <c:forEach var="item" items="${memberList }">
              <tr>
                <td height="30" align="center" valign="middle"><input name="memberName" type="text" id="memberName" size="12" value="${item.memName }" /></td>
                <td align="center" valign="middle"><input name="memberRelation" type="text" id="memberRelation" size="12" value="${item.memRelation }" /></td>
                <td align="center" valign="middle"><input name="cardNum" type="text" id="cardNum" size="18" value="${item.memIdentity }" /></td>
                <td align="center" valign="middle"><input name="phoneNum" type="text" id="phoneNum" size="25" value="${item.memPhone }" /></td>
              </tr>
              </c:forEach>
              <c:forEach begin="${memberList.size()+1 }" end="5" step="1">
              <tr>
                <td height="30" align="center" valign="middle"><input name="memberName" type="text" id="memberName" size="12" /></td>
                <td align="center" valign="middle"><input name="memberRelation" type="text" id="memberRelation" size="12" /></td>
                <td align="center" valign="middle"><input name="cardNum" type="text" id="cardNum" size="18" /></td>
                <td align="center" valign="middle"><input name="phoneNum" type="text" id="phoneNum" size="25" /></td>
              </tr>
              </c:forEach>
              <tr>
                <td height="30" colspan="4" align="left" valign="middle">&nbsp;</td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">邮政编码：</td>
                <td align="center" valign="middle"><input name="owner.otherPostcode" type="text" size="12" id="owner.otherPostcode" value="${owner.otherPostcode }" /></td>
                <td height="30" align="center" valign="middle">其它地址：</td>
                <td align="center" valign="middle"><input name="owner.otherAddress" type="text" size="25" id="owner.otherAddress" value="${owner.otherAddress }" /></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">紧急联系人：</td>
                <td height="30" align="center" valign="middle"><input name="owner.emergencyContact" type="text" size="12" id="owner.emergencyContact" value="${owner.emergencyContact }" /></td>
                <td height="30" align="center" valign="middle">联系电话：</td>
                <td align="center" valign="middle"><input name="owner.emergencyPhone" type="text" size="25" id="owner.emergencyPhone" value="${owner.emergencyPhone }" /></td>
              </tr>
              </table>
            </td>
          </tr>  
          <tr>
            <td height="30" align="center" valign="bottom"><input type="button" name="ToP1" id="ToP1" value="上一步" onclick="eto('eP1');" />
            <input type="submit" name="Submit" id="Submit" value="完&nbsp;&nbsp;成"   />
            <input type="button" name="button" id="button" value="关&nbsp;&nbsp;闭" onclick="closeWindow('#ownerEdit')"/>
            </td>
          </tr>
      </table>
      </div>
      <div id="P3" style="display:;"></div>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
