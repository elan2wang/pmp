<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
</head>

<body>

<form id="form1" name="form1" action="updateOwner" method="post">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="20" height="25" class="TopNav">&nbsp;</td>
          <td width="80" height="25" align="center" valign="middle" class="ModuleTapOn" id="Tab1">1：业主信息</td>
          <td width="80" align="center" valign="middle" class="ModuleTap" id="Tab2">2：成员信息</td>
          <td class="TopNav">&nbsp;</td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td style="padding-top:5px; padding-bottom:5px; border-bottom:1px #6c92ad solid;">
      <div id="P1">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" valign="middle"><table border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
              <tr>
              	<input type="hidden" name="owner.ownerId" id="owner.ownerId" value="${owner.ownerId}"/>
              	<input type="hidden" name="gender" id="gender" value="${owner.gender}"/>
              	<input type="hidden" name="nationality" id="nationality" value="${owner.nationality}"/>
              	<input type="hidden" name="ismarried" id="ismarried" value="${owner.ismarried}"/>
              	<input type="hidden" name="identityType" id="identityType" value="${owner.identityType}"/>
              	
                 <td width="70" height="30" align="center" valign="middle">姓名：</td>
                <td width="220" colspan="2" align="left" valign="middle"><input name="owner.ownerName" type="text" class="textbox" id="owner.ownerName" value="${owner.ownerName}"/></td>
                <td width="70" align="center" valign="middle">性别：</td>
                <td width="220" align="left" valign="middle"><select name="owner.gender" class="selectbox" id="select_gender" >
                  <option value="null" selected="selected">－－请选择－－</option>
                  <option value="男">男</option>
                  <option value="女">女</option>
                </select></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">民族：</td>
                <td colspan="2" align="left" valign="middle"><select name="owner.nationality" class="selectbox" id="owner.nationality" >
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
                <td align="center" valign="middle">籍贯：</td>
                <td width="220" align="left" valign="middle"><input name="owner.native_" type="text" class="textbox" id="owner.native_" value="${owner.native_}"/></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">出生年月：</td>
                <td colspan="2" align="center" valign="middle"><input name="owner.birthday" type="text" class="textbox" id="owner.birthday" readonly="readonly"  style="cursor:pointer;" onFocus="WdatePicker()" value="<fmt:formatDate value="${owner.birthday}" type="both" pattern="yyyy-MM-dd"/>"/></td>
                <td align="center" valign="middle">婚姻状况：</td>
                <td align="left" valign="middle"><select name="owner.ismarried" class="selectbox" id="owner.ismarried" >
                  <option value="null" selected="selected">－－请选择－－</option>
                  <option value="true">已婚</option>
                  <option value="false">未婚</option>
                </select></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">工作单位：</td>
                <td colspan="2" align="left" valign="middle"><input name="owner.organization" type="text" class="textbox" id="owner.organization" value="${owner.organization}"/></td>
                <td align="center" valign="middle">个人爱好：</td>
                <td align="left" valign="middle"><input name="owner.hobby" type="text" class="textbox" id="owner.hobby"  value="${owner.hobby}"/></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">证件类型：</td>
                <td colspan="2" align="left" valign="middle"><select name="owner.identityType" class="selectbox" id="owner.identityType" >
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
                <td colspan="2" align="left" valign="middle"><input name="owner.homePhone" type="text" class="textbox" id="owner.homePhone" value="${owner.homePhone}"/></td>
                <td align="center" valign="middle">手机号码：</td>
                <td align="left" valign="middle"><input name="owner.mobile" type="text" class="textbox" id="owner.mobile"  value="${owner.mobile}"/></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">领房时间：</td>
                <td colspan="2" align="left" valign="middle"><input name="owner.getTime" type="text" value="${owner.getTime}" class="textbox" id="owner.getTime" readonly="readonly"  style="cursor:pointer;" onFocus="WdatePicker()" /></td>
                <td align="center" valign="middle">装修时间：</td>
                <td align="left" valign="middle"><input name="owner.decorateTime" type="text" value="${owner.decorateTime}" class="textbox" id="owner.decorateTime" readonly="readonly"  style="cursor:pointer;" onFocus="WdatePicker()" /></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">入住时间：</td>
                <td colspan="2" align="left" valign="middle"><input name="owner.inTime" value="${owner.inTime}" type="text" class="textbox" id="owner.inTime" readonly="readonly"  style="cursor:pointer;" onFocus="WdatePicker()" /></td>
                <td align="center" valign="middle">车位库号：</td>
                <td align="left" valign="middle"><input name="owner.parkNum" type="text" class="textbox" id="owner.parkNum" value="${owner.parkNum}"/></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">车牌号码：</td>
                <td colspan="2" align="left" valign="middle"><input name="owner.carNum" type="text" class="textbox" id="owner.carNum"  value="${owner.carNum}"/></td>
                <td align="center" valign="middle">车型号：</td>
                <td align="left" valign="middle"><input name="owner.carType" type="text" class="textbox" id="owner.carType" value="${owner.carType}"/></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">储藏室号：</td>
                <td colspan="2" align="left" valign="middle"><input name="owner.storeroom" type="text" class="textbox" id="owner.storeroom" value="${owner.storeroom}"/></td>
                <td align="center" valign="middle"></td>
                <td align="left" valign="middle"></td>
              </tr>
          
              <tr>
              	
                <td height="30" align="center" valign="middle">所在小区：</td>
                <td colspan="2" align="left" valign="middle">
              	 	 <s:action name="getAllProject" namespace="/cms" executeResult="true"/>               	 	 
               	 	 <input type="hidden" id="proId" name="proId" value="${requestScope.pro.proId}"/>
                </td>
                <td align="center" valign="middle">楼宇号：</td>
                <td align="left" valign="middle"><select id="buildingId" name="buildingId" onchange="getAllHouse()">
                                              <option selected="selected">选择楼宇</option>
                                      </select> <input type="hidden" name="builId" id="builId" value="${requestScope.bui.builId}"/></td></td>
              </tr></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">房号：</td>
                <td colspan="2" align="left" valign="middle">
                	<select id="houseId" name="houseId" onchange="getHouseInfo()">
                		<option selected="selected">选择房号</option>
                	</select>
                	<input type="hidden" name="houseId2" id="houseId2" value="${requestScope.hou.houseId}"/>
                </td>
                <td align="center" valign="middle"></td>
                <td align="left" valign="middle"></td>
              </tr>
              <tr>
                <td height="30" align="center" valign="middle">房屋面积：</td>
                <td align="left" valign="middle"><input name="owner.houseArea" type="text" class="textbox" id="owner.houseArea" value="${owner.houseArea}" style="width:60px" />
                  平方米</td>
                <td width="70" align="center" valign="middle">使用状况：</td>
                <td colspan="2" align="left" valign="middle"><table border="0" cellpadding="0" cellspacing="0" class="pinfen">
                  <tr>
                    <td align="center" valign="middle"><input type="radio" name="owner.useStyle" id="radio" value="常住" ${(owner.useStyle=='常住')?'checked' : ''} /></td>
                    <td width="40" align="left" valign="middle">常住</td>
                    <td align="center" valign="middle"><input type="radio" name="owner.useStyle" id="radio2" value="租住" ${(owner.useStyle=='租住')?'checked' : ''} /></td>
                    <td width="40" align="left" valign="middle">租住</td>
                    <td align="center" valign="middle"><input type="radio" name="owner.useStyle" id="radio3" value="暂住" ${(owner.useStyle=='暂住')?'checked' : ''} /></td>
                    <td width="40" align="left" valign="middle">暂住</td>
                    <td align="center" valign="middle"><input type="radio" name="owner.useStyle" id="radio4" value="Other"  /></td>
                    <td align="center" valign="middle"><input name="OtherText" type="text" class="textbox" id="OtherText" style="width:80px;" value="其它情况"  onfocus="this.value='';" onblur="if(this.value==''){this.value='其它情况'};" /></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="30" align="center" valign="bottom"><input type="button" name="Prev" id="Prev" value="上一步" onclick="to('P1');" disabled="disabled" />
　  <input type="button" name="toP2" id="toP2" value="下一步" onclick="FormCheck();" /></td>
          </tr>
        </table>
      </div>
      <div id="P2" style="display:none;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" valign="middle"><table border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
              <tr>
                <td height="30" colspan="6" align="center" valign="middle">家庭成员</td>
                </tr>
              <tr>
                <td width="100" height="30" align="center" valign="middle">姓名</td>
                <td width="70" align="center" valign="middle">关系</td>
                <td width="220" align="center" valign="middle">身份证及其它有效证件编号</td>
                <td width="200" colspan="2" align="center" valign="middle">联系电话</td>
              </tr>
              <c:forEach var="member" items="${memberList}">
              <tr>
              <input type="hidden" name="memberId" id="memberId" value="${member.memId}"/>
               <td height="30" align="center" valign="middle"><input name="memberName" type="text" class="textbox" id="memberName" value="${member.memName}" style="width:80px;"  /></td>
                <td align="center" valign="middle"><input name="memberRelation" type="text" class="textbox" id="memberRelation" value="${member.memRelation}" style="width:50px;"  /></td>
                <td align="center" valign="middle"><input name="cardNum" type="text" class="textbox" id="cardNum" value="${member.memIdentity}" style="width:200px;"   /></td>
                <td colspan="2" align="center" valign="middle"><input name="phoneNum" type="text" class="textbox" id="phoneNum" value="${member.memPhone}" style="width:180px;"  /></td>
              </tr>
              </c:forEach>
              <tr>
                <td height="30" colspan="2" align="center" valign="middle">其它地址：</td>
                <td height="30" align="center" valign="middle"><input name="owner.otherAddress" type="text" class="textbox" id="owner.otherAddress" value="${owner.otherAddress}" style="width:200px;"   /></td>
                <td width="70" height="30" align="center" valign="middle">邮政编码：</td>
                <td align="center" valign="middle"><input name="owner.otherPostcode" type="text" class="textbox" id="owner.otherPostcode" value="${owner.otherPostcode}" style="width:80px;"  /></td>
              </tr>
              <tr>
                <td height="30" colspan="2" align="center" valign="middle">紧急联系人：</td>
                <td height="30" align="center" valign="middle"><input name="owner.emergencyContact" type="text" class="textbox" id="owner.emergencyContact" value="${owner.emergencyContact}" style="width:200px;"   /></td>
                <td height="30" align="center" valign="middle">联系电话：</td>
                <td align="center" valign="middle"><input name="owner.emergencyPhone" type="text" class="textbox" id="owner.emergencyPhone" value="${owner.emergencyPhone}" style="width:80px;"  /></td>
              </tr>
              <tr>
                <td height="30" colspan="2" align="center" valign="middle">&nbsp;</td>
                <td height="30" colspan="3" align="left" valign="middle">&nbsp;</td>
              </tr>
              <tr>
                <td height="30" colspan="2" align="center" valign="middle">&nbsp;</td>
                <td height="30" colspan="3" align="left" valign="middle">&nbsp;</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="30" align="center" valign="bottom"><input type="button" name="ToP1" id="ToP1" value="上一步" onclick="to('P1');" />
              
     
     <input type="submit" name="Submit" id="Submit" value="完　成"   />
      
     <input type="button" name="button" id="button" value="关 闭" onclick="closeEditOwner()"/>
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