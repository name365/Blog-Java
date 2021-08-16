<%@ page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css" />
    <script>
        function tick() {
            var today=new Date();
            var month=today.getMonth()+1;<!--getMonth显示当前月份-1，所以要+1才能显示当前月份-->
            var year, date, hours, minutes, seconds;
            var intHours, intMinutes, intSeconds;
            var week=new Array()
            // 显示几天为星期几
            week[0]="星期天 ";
            week[1]="星期一 ";
            week[2]="星期二 ";
            week[3]="星期三 ";
            week[4]="星期四 ";
            week[5]="星期五 ";
            week[6]="星期六 ";
            intHours = today.getHours();
            intMinutes = today.getMinutes();
            intSeconds = today.getSeconds();
            year=today.getFullYear();
            date=today.getDate();
            var time;
            if (intHours == 0) {
                hours = "00:";
            }
            else if (intHours < 10) {
                hours = "0" + intHours+":";
            }
            else {
                hours = intHours + ":";
            }
            if (intMinutes < 10) {
                minutes = "0"+intMinutes+":";
            }
            else {
                minutes = intMinutes+":";
            }
            if (intSeconds < 10) {
                seconds = "0"+intSeconds+" ";
            }
            else {
                seconds = intSeconds+" ";
            }
//根据不同的时间显示不同的问候。
//下面采用自己总结的测试纠正法对time进行赋值
            time="午夜好";
            if(today.getHours()<=22)  time="晚上好";
            if(today.getHours()<=19)  time="傍晚好";
            if(today.getHours()<=17)  time="下午好";
            if(today.getHours()<=14)  time="中午好";
            if(today.getHours()<=12)  time="上午好";
            if(today.getHours()<=8)   time="早上好";
            if(today.getHours()<=5)   time="凌晨好";
            timeString="今天是："+year+"年"+month+"月"+date+"日  "+hours+minutes+seconds+week[today.getDay()]+time;
            Clock.innerHTML = timeString;
            window.setTimeout("tick();", 1000);
        }
        window.onload = tick;
    </script>
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>超市订单管理系统</h1>
        <div class="publicHeaderR">
            <p><span>下午好！</span><span style="color: #fff21b"> ${userSession.userName }</span> , 欢迎你！</p>
            <a href="${pageContext.request.contextPath }/jsp/logout.do">退出</a>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <span id="Clock"></span>
        <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
    </section>
 <!--主体内容-->
 <section class="publicMian ">
     <div class="left">
         <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
         <nav>
             <ul class="list">
                 <li ><a href="${pageContext.request.contextPath }/jsp/bill.do?method=query">订单管理</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/provider.do?method=query">供应商管理</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/user.do?method=query">用户管理</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/pwdmodify.jsp">密码修改</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/logout.do">退出系统</a></li>
             </ul>
         </nav>
     </div>
     <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
     <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>

