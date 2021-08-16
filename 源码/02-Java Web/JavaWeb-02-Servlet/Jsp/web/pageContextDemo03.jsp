<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    pageContext.forward("/index.jsp");
    request.getRequestDispatcher("index.jsp").forward(request,response);
%>

</body>
</html>
