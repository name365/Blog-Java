<%--
  Created by IntelliJ IDEA.
  User: subei
  Date: 2021/7/25
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%--
    这里提交的路径需要寻找到项目的路径
    ${pageContext.request.contextPath} : 代表当前项目
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/login" method="get">
    用户名:<input type="text" name="username"><br>
    密  码:<input type="password" name="password"><br>
    <input type="submit">
  </form>
  </body>
</html>
