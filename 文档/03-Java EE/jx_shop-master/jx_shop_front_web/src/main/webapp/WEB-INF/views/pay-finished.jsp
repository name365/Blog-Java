<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>支付成功</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

    <style>
        ul {
            border: 0;
        }

        .tick {
            text-align: center;
        }

        .tickimg {
            width: 60px;
            border-radius: 50%;
            height: 57px;
            background-color: #0BB20C;
            margin: 20px auto 10px;
            position: relative;
        }

        .tickimg:after {
            position: absolute;
            content: '7';
            font-size: 48px;
            font-weight: bold;
            color: #fff;
            top: -6px;
            left: 22px;
            transform: rotateZ(-300deg) rotateX(-180deg);
            -webkit-transform: rotateZ(-300deg) rotateX(-180deg);
        }

        p {
            margin: 10px 0;
        }

        .mark {
            font-size: 16px;
            color: #808080;
            padding: .2em 0;
        }

        .des-label {
            width: 100px;
            text-align: right;
            font-size: 16px;
            font-weight: bolder;
            color: #808080;
            display: inline-block;
        }

        .list-group-item {
            border: 0;
        }
    </style>

    <script>
        <%--查看订单列表--%>
        function showOrders() {
            location.href = '${pageContext.request.contextPath}/front/order/myOrders';
        }
        function goShopping() {
            location.href = '${pageContext.request.contextPath}/front/product/searchAllProducts';
        }
    </script>
</head>

<body class="animated fadeIn">
<!-- 导航栏 start -->
<jsp:include page="top.jsp">
    <jsp:param name="num" value="9"/>
</jsp:include>
<!-- 导航栏 end -->
<!-- content start -->
<!--主体内容-->
<div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-md-offset-2 col-sm-7">
            <div class="tick">
                <div class="tickimg"></div>
                <p>购买成功</p>
            </div>
            <div align="center">
                <ul style="margin-top: 10px" class="list-group">
                    <li class="list-group-item">
                        <div align="left" style="margin-left: 30px" class="mark">
                            <p>
                                <span class="des-label">订单编号：</span>
                                <span>${orderNumber}</span>
                            </p>
                            <p>
                                <span class="des-label">交易编号：</span>
                                <span>${trade_no}</span>
                            </p>
                            <p>
                                <span class="des-label">商品名称：</span>
                                <span>${productName}</span>
                            </p>
                            <p>
                                <span class="des-label">支付金额：</span>
                                <b><span>${price}</span></b>
                            </p>
                        </div>

                    </li>
                </ul>
                <button type="button" class="btn btn-default col-sm-offset-3 col-sm-3" onclick="showOrders()">查看订单
                </button>
                <button type="button" class="btn btn-success col-sm-offset-1 col-sm-3" onclick="goShopping()">继续购物
                </button>
            </div>
        </div>
    </div>
</div>
<!-- content end-->

<!-- footers start -->
<footer id="footer" class="footers navbar-fixed-bottom">
    <div class="footer container-fluid footers">
        Copy Right @ 2020 Services To Help Agriculture
    </div>
</footer>
<!-- footers end -->
</body>

</html>
