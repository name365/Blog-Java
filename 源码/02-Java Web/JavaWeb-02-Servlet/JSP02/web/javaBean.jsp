<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
//    People people = new People();
//    people.setAddress();
//    people.setId();
//    people.getAge()
//    people.setName();

%>

<jsp:useBean id="people" class="com.github.pojo.People" />

<jsp:setProperty name="people" property="address" value="成都"/>
<jsp:setProperty name="people" property="id" value="1"/>
<jsp:setProperty name="people" property="age" value="20"/>
<jsp:setProperty name="people" property="name" value="哇哈哈AD钙"/>

姓名:<jsp:getProperty name="people" property="name"/>
 ID:<jsp:getProperty name="people" property="id"/>
年龄:<jsp:getProperty name="people" property="age"/>
地址:<jsp:getProperty name="people" property="address"/>

</body>
</html>
