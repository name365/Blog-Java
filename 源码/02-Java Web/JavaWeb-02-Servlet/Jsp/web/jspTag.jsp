<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>jspTag</title>
</head>
<body>

<h1>Tag1</h1>

<%--jsp:include--%>

<%--
http://localhost:8080/Jsp/jspTag.jsp?name=subeily&age=18
--%>

<jsp:forward page="/jspTag2.jsp">
    <jsp:param name="name" value="subeiLY"/>
    <jsp:param name="age" value="18"/>
</jsp:forward>


</body>
</html>
