<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="window_content">
<form name="form1" id="form1" action="inputCondoFee" method="post">
         <div class="rowStyle" style="display:none">
             <div><span >物业费编号:</span><input type="text" name="condoFee.cfId" value="${condoFee.cfId }"/></div>
         </div>
         <div class="rowStyle">
             <div><span >业主姓名:</span><span>${condoFee.owner.ownerName } </span></div>
         </div>
         <div class="rowStyle">
             <div><span >房号:</span><span>${condoFee.house.houseNum }</span></div>
         </div>
         <div class="rowStyle">
             <div><span >联系方式:</span><span></span>${condoFee.owner.mobile }</div>
         </div>
         <div class="rowStyle">
             <div><span >缴费周期:</span><span><fmt:formatDate value="${condoFee.startDate}" type="both" pattern="yyyy-MM-dd"/>
                              至 <fmt:formatDate value="${condoFee.endDate}" type="both" pattern="yyyy-MM-dd"/>
             </span></div>
         </div>
         <div class="rowStyle">
             <div><span >应收金额:</span><span>${condoFee.oughtMoney }</span></div>
         </div>
         <div class="rowStyle">
             <div><span >缴费状态:</span><span>${condoFee.state }</span></div>
         </div>
         <div class="rowStyle">
             <div><span >收缴时间:</span><span><input type="text" name="condoFee.fetchDate" readonly="readonly" style="cursor:pointer;" onFocus="WdatePicker()"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >实收金额:</span><span><input type="text" name="condoFee.fetchMoney"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >收缴人员:</span><span><input type="text" name="condoFee.fetchPerson"/></span></div>
         </div>
         <div class="rowStyle">
             <div><span >收缴凭证:</span><span><input type="text" name="condoFee.fetchTicket"/></span></div>
         </div>
          <div class="rowStyle">
              <div style=" margin-left:100px;margin-top:20px"><input type="submit" value="提交" onclick="return  FormCheck(); "/>
              <input type="button" value="关闭" onclick="Close(); "/></div>
           </div>
         </form>
         </div>
</body>
</html>