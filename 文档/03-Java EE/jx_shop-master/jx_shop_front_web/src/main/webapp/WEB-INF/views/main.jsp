<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>助农服务-在线商城</title>
    <link rel="shortcut icon" type="image/x-icon" href="https://www.xmlvhy.com/images/xmshop/favicon.ico"
          media="screen"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xmstyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/zshop.js"></script>

    <%--我的自定义样式--%>
    <style type="text/css">
        #xm {
            width: auto;
            height: auto;
        }
    </style>

    <script>
        $(function () {
            autoFooterHeight();
            $('#xm').carousel({
                interval: '1500',//设置自动播放的间隔时间
                pause: null,//当鼠标悬停在图片上时是否暂停播放
                wrap: true //设置是否循环播放
            });
        });

        //TODO: 设置固定在底部的footer 可以自适应高度
        function autoFooterHeight() {
            // 获取内容的高度
            var bodyHeight = $("body").height();
            // 获取底部导航的高度
            var navHeight = $(".footer").height();
            // 获取显示屏的高度
            var iHeight = document.documentElement.clientHeight || document.body.clientHeight;
            // 如果内容的高度大于（窗口的高度 - 导航的高度）,移除属性样式
            if (bodyHeight > (iHeight - navHeight)) {
                $("#footer").removeClass("navbar-fixed-bottom");
            }
        }

        //展示商品详情
        function showProductDetail(id) {
            location.href = '${pageContext.request.contextPath}/front/product/showProductDetail?id=' + id;
        }
    </script>
</head>

<body class="animated fadeIn">
<div id="wrapper" style="margin-top: 50px">
    <!-- 导航栏 start -->
    <jsp:include page="top.jsp">
        <jsp:param name="num" value="1"/>
    </jsp:include>
    <!-- 导航栏 end -->

    <%--轮播插件--%>
    <div style="background-color: #fafafa">
        <div class="container" style="margin-top: 67px;">
            <div id="xm" class="carousel col-sm-3" data-ride="carousel">
                <!--指示器，下面的小圆点-->
                <ul class="carousel-indicators">
                    <li data-target="#xm" data-slide-to="0" class="active"></li>
                    <li data-target="#xm" data-slide-to="1"></li>
                    <li data-target="#xm" data-slide-to="2"></li>
                    <li data-target="#xm" data-slide-to="3"></li>
                </ul>
                <!--滑块-->
                <div class="carousel-inner" style="height: 220px">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/images/banner1.jpg" alt="">
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/images/banner2.jpg" alt="">
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/images/banner3.jpg" alt="">
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/images/banner4.jpg" alt="">
                    </div>
                </div>
                <!--控制器-->
                <a href="#xm" class="carousel-control left" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a href="#xm" class="carousel-control right" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
        </div>

    <%--TODO:商品展示没有加分页功能，还有数据的回显功能--%>
    <!-- 中间展示的内容 start -->
    <%--<div class="container-fluid" style="background-color: #FFF8DC">--%>
        <div class="container">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="page-header" style="margin-bottom: 0px;">
                            <h3 style="color: #48900F">商品列表</h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-inline hot-search"
                              action="${pageContext.request.contextPath}/front/product/searchAllProducts"
                              method="post">
                            <div class="form-group">
                                <label class="control-label">商品：</label>
                                <input type="text" class="form-control" placeholder="商品名称" name="name">
                            </div>
                            &nbsp;
                            <div class="form-group">
                                <label class="control-label">价格：</label>
                                <input type="text" class="form-control" placeholder="最低价格" name="minPrice"> --
                                <input type="text" class="form-control" placeholder="最高价格" name="maxPrice">
                            </div>
                            &nbsp;
                            <div class="form-group">
                                <label class="control-label">种类：</label>
                                <select class="form-control input-sm" name="productTypeId">
                                    <option value="-1" selected="selected">查询全部</option>
                                    <c:forEach items="${productTypes}" var="productType">
                                        <option value="${productType.id}">${productType.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            &nbsp;
                            <div class="form-group">
                                <button type="submit" class="btn btn-warning">
                                    <i class="glyphicon glyphicon-search"></i> 查询
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content-back">
        <div class="container" id="a">
            <div class="row">
                <c:forEach items="${pageInfo.list}" var="product">
                    <div class="col-xs-3  hot-item" id="showDetail" onclick="showProductDetail(${product.id})"
                         style="cursor: pointer;">
                        <div class="panel clear-panel">
                            <div class="panel-body">
                                <div class="art-back clear-back">
                                    <div class="add-padding-bottom">
                                        <img src="${product.image}" class="shopImg">
                                    </div>
                                    <h4 class="myH4"><a href="#">${product.name}</a></h4>
                                    <div class="user clearfix pull-right"><span>¥ </span>${product.price}</div>
                                    <div>
                                        <a class="my" href="#">${product.info}</a>
                                    </div>
                                        <%--<div class="attention pull-right">--%>
                                        <%--<i class="icon iconfont icon-gouwuche"></i>--%>
                                        <%--</div>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- 中间展示的内容 end-->

    <!-- footers start -->
    <footer id="footer" class="footers navbar-fixed-bottom">
        <div class="container-fluid footers">
            Copy Right @ 2020 Services To Help Agriculture
        </div>
    </footer>
    <!-- footers end -->
</div>
</body>

</html>
