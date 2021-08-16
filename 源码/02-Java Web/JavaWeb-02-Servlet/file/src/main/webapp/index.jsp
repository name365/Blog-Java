<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Hello World!</h2>

<%--
    通过表单上传文件
    get:上传文件有大小限制,只能提交4-5kb的数据
    post:上传文件无大小限制
    注意：文件一般比较大，所以上传文件都是使用post方式提交
    ${pageContext.request.contextPath}:获取到webapp路径
--%>

<form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data" method="post">
    上传用户: <input type="text" name="username"><br>
    上传文件1: <input type="file" name="file1"><br>
    上传文件2: <input type="file" name="file2"><br>
    <p> <input type="submit" value="提交文件">  | <input type="reset" value="重置"> </p>
</form>

</body>
</html>