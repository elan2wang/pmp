<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维修单打印</title>
<link href="CSS/iBox.css" rel="stylesheet" type="text/css" />

<script language=javascript>

    function doPrint() {

        bdhtml = window.document.body.innerHTML;

        sprnstr = "<!--startprint-->";

        eprnstr = "<!--endprint-->";

        prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);

        prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));

        window.document.body.innerHTML = prnhtml;

        window.print();

    }    

</script>

<style type="text/css">

.textbox{

	width:90px;

	height:14px;

}

.textbox1 {	width:90px;

	height:14px;

}

.pinfen{

	border:0px;	

}

.pinfen td{

	border:0px;	

}

</style>

</head>
<body>
<!--startprint-->

<table width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>

    <td height="30" align="center" valign="middle">&nbsp;</td>

  </tr>

  <tr>

    <td height="45" align="center" valign="middle"><h1><strong>维修单</strong></h1></td>

  </tr>

  <tr>

    <td height="50" align="center" valign="middle" style="font-size:16px;">服务中心：_________________　　　　　　　　　　　　　NO:__________</td>

  </tr>

  <tr>

    <td align="center" valign="middle"><table width="547" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">

      <tr>

        <td height="30" colspan="6" align="center" valign="middle"><strong>业主基本信息</strong></td>

      </tr>

      <tr>

        <td height="30" align="center" valign="middle">楼宇：</td>

        <td align="center" valign="middle">6号楼</td>

        <td align="center" valign="middle">单元：</td>

        <td align="center" valign="middle">1单元</td>

        <td align="center" valign="middle">户：</td>

        <td align="center" valign="middle">302户</td>

      </tr>

      <tr>

        <td width="80" height="30" align="center" valign="middle">业主姓名：</td>

        <td width="100" align="center" valign="middle">张宏</td>

        <td width="80" align="center" valign="middle">联系电话：</td>

        <td width="100" align="center" valign="middle">13868202150</td>

        <td width="80" align="center" valign="middle">报修时间：</td>

        <td width="100" align="center" valign="middle">2012-05-11</td>

      </tr>

      <tr>

        <td height="30" colspan="6" align="center" valign="middle"><strong>维修项目信息（新建必填）</strong></td>

      </tr>

      <tr>

        <td width="80" height="30" align="center" valign="middle">维修单号：</td>

        <td align="center" valign="middle">003</td>

        <td align="center" valign="middle">预约日期：</td>

        <td align="center" valign="middle">2012-05-11</td>

        <td align="center" valign="middle">预约时间：</td>

        <td align="center" valign="middle">14:00</td>

      </tr>

      <tr>

        <td width="80" height="50" align="center" valign="middle">维修内容：</td>

        <td colspan="2" align="center" valign="middle">车库换锁</td>

        <td align="center" valign="middle">详细情况：</td>

        <td colspan="2" align="center" valign="middle">车库锁坏</td>

      </tr>

      <tr>

        <td height="30" colspan="6" align="center" valign="middle"><strong>维修反馈信息</strong></td>

      </tr>

      <tr>

        <td height="30" align="center" valign="middle">维修员：</td>

        <td colspan="2" align="center" valign="middle">戎夫来</td>

        <td align="center" valign="middle">是否派单：</td>

        <td colspan="2" align="center" valign="middle">已修复（物业）</td>

      </tr>

      <tr>

        <td width="80" height="30" align="center" valign="middle">完工日期：</td>

        <td colspan="2" align="center" valign="middle">2012-05-11</td>

        <td align="center" valign="middle">完工时间：</td>

        <td colspan="2" align="center" valign="middle">14:00</td>

      </tr>

      <tr>

        <td height="30" align="center" valign="middle">业主验收：</td>

        <td colspan="2" align="center" valign="middle"></td>

        <td rowspan="2" align="center" valign="middle">业主评价：</td>

        <td colspan="2" rowspan="2" align="center" valign="middle"></td>

      </tr>

      <tr>

        <td height="30" align="center" valign="middle">服务态度：</td>

        <td colspan="2" align="center" valign="middle"></td>

      </tr>

      <tr>

        <td height="30" colspan="6" align="center" valign="middle"><strong>材料/费用清单（业主未结单）</strong></td>

      </tr>

      <tr>

        <td height="150" colspan="6" align="center" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">

          <tr>

            <td width="120" height="25" align="center" valign="middle"><strong>材料名称</strong></td>

            <td width="60" align="center" valign="middle"><strong>数量</strong></td>

            <td width="100" align="center" valign="middle"><strong>金额</strong></td>

            <td align="center" valign="middle"><strong>备注</strong></td>

          </tr>
                <tr>
                  <td width="120" height="25" align="center" valign="middle">材料费</td>
                  <td width="60" align="center" valign="middle">1</td>
                  <td width="100" align="center" valign="middle">10</td>
                  <td  align="center" valign="middle"></td>
                </tr>
              <tr>
            <td height="25" colspan="4" align="center" valign="middle"><div style="width:100%; height:150px; overflow-y:auto;">

              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="tbProjList">

                <tr>

                  <td width="120" height="25" align="center" valign="middle">工时费</td>

                  <td width="60" align="center" valign="middle">-</td>

                  <td width="100" align="center" valign="middle">0</td>

                  <td align="center" valign="middle">&nbsp;</td>

                </tr>

              </table>

            </div></td>

          </tr>

        </table></td>

      </tr>

    </table></td>

  </tr>

  <tr>

    <td height="25" align="center" valign="middle">注：本维修单一式三联，业主、维修员、服务中心各留存一份。　签发日期：　　　年　　月　　日</td>

  </tr>

</table>

<!--endprint-->

<table width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>

    <td height="35" align="center" valign="middle"><input type="button" name="button" id="button" value="打印" onClick="doPrint()"/></td>

  </tr>

</table>

</body>
</html>