<%@ page contentType="text/html;charset=UTF-8" %>
<%--注册填写邮箱的前端页面--%>
<html>
<head>
  <title>注册</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/RegisterServlet.do" method="post">
  用户名：<input type="text" name="username"><br/>
  密码：<input type="password" name="password"><br/>
  邮箱：<input type="text" name="email"><br/>
  <input type="submit" value="注册">
</form>

</body>
</html>
