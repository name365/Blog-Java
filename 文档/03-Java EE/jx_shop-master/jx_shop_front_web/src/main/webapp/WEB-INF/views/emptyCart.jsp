<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>我的购物车</title>
    <link rel="shortcut icon" type="image/x-icon" href="https://www.xmlvhy.com/images/xmshop/favicon.ico"  media="screen"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>

<body class="animated fadeIn">

<!-- 导航栏 start -->
<jsp:include page="top.jsp">
    <jsp:param name="num" value="6"/>
</jsp:include>
<!-- 导航栏 end -->

<!-- content start -->
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header" style="margin-bottom: 0px;">
                <h3>我的购物车</h3>
            </div>
        </div>
    </div>
    <div>
        <h3 class="text-success" style="text-align: center;margin-top: 180px">
            购物车空空如也，<a class="btn-link" href="${pageContext.request.contextPath}/front/product/searchAllProducts">前往购物~</a>
        </h3>
    </div>
</div>
<!-- content end-->
<div style="height: 250px"></div>

<!-- footers start -->
<footer id="footer" class="footers navbar-fixed-bottom">
    <div class="footer container-fluid footers">
        Copy Right @ 2020 Services To Help Agriculture
    </div>
</footer>
<!-- footers end -->
</body>

</html>
