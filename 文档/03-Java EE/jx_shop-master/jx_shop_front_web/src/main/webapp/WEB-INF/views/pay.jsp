<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>订单支付</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <style>
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
        $(function () {
            //显示服务端响应的消息
            var failMsg = '${failMsg}';
            if (failMsg != '') {
                layer.msg(failMsg, {
                    time: 1500,
                    skin: 'errorMsg'
                });
            }
        });

        //使用支付宝支付
        function aliPay() {
            location.href = '${pageContext.request.contextPath}/front/pay/goAliPay?orderNumber=' + '${order.orderNumber}';
        }

        //使用微信支付
        function wxPay() {
            var url = '${pageContext.request.contextPath}/front/wxPay/showPayQRCode';
            // console.log(url)
            $.post(
                '${pageContext.request.contextPath}/front/wxPay/goWxPay',
                {'orderNumber':'${order.orderNumber}'},
                function (result) {
                    console.log(result);
                    if (result.status == 1) {
                        //弹出modal 之前给隐藏域赋值
                        $('#orderNumber').val('${order.orderNumber}');

                        $("#pay_img").attr("src", url);
                        $('#myModal').modal('show');
                    }else{
                        layer.msg(result.message,{
                           time: 1200,
                           skin: 'errorMsg'
                        });
                    }
                }
            );
            // $("#pay_img").attr("src", url);
            // $('#myModal').modal('show');
        }

        function payStatus() {
            //弹出二维码
            var orderNumber = $('#orderNumber').val();
            if (orderNumber == '') {
                //未弹出二维码
                orderNumber = '${order.orderNumber}';
            }
            $.post(
                '${pageContext.request.contextPath}/front/wxPay/CheckPayStatus',
                {'orderNumber':orderNumber},
                function (result) {
                    if (result.status == 1) {
                        // console.log("订单："+orderNumber+"已完成支付");
                        //订单状态已经修改，销毁定时器，并且跳转到成功页面
                        window.clearInterval(int);
                        setTimeout(function () {
                           location.replace('${pageContext.request.contextPath}/front/wxPay/ShowPayStatus?orderNumber='+orderNumber);
                        },1000);
                    }
                }
            );
        }
        //ajax 异步轮循,查看订单的状态是否更新
        var int = self.setInterval(function () {
            payStatus();
        },1000);
    </script>
</head>

<body class="animated fadeIn">

<!-- 导航栏 start -->
<jsp:include page="top.jsp">
    <jsp:param name="num" value="8"/>
</jsp:include>
<!-- 导航栏 end -->

<!-- content start -->
<div class="container">
    <div align="center" style="background-color: #fcf8e3;margin-top: 140px;width: 50%;margin-left: 23%">
        <ul style="margin-top: 10px" class="list-group">
            <li class="list-group-item">
                <div align="left" style="margin-left: 30px" class="mark">
                    <p>
                        <span class="des-label">订单编号：</span>
                        <span>${order.orderNumber}</span>
                    </p>
                    <p>
                        <%--TODO:商品名称--%>
                        <span class="des-label">商品名称：</span>
                        <span>jx水果</span>
                    </p>
                    <p>
                        <span class="des-label">支付金额：</span>
                        <b><span>${order.price}</span></b>
                    </p>
                    <p>
                        <span class="des-label">商品数量：</span>
                        <span>${order.productNumber}</span>
                    </p>
                </div>
            </li>
        </ul>
        <input type="hidden" name="orderNumber" id="orderNumber">
        <button type="button" class="btn btn-info col-sm-offset-3 col-sm-3" onclick="aliPay()">支付宝支付</button>
        <button type="button" class="btn btn-success col-sm-offset-1 col-sm-3" onclick="wxPay()">微信支付</button>
    </div>
</div>
<!-- content end-->


<!-- 微信支付二维码弹出窗口 -->
<div class="modal fade" style="top: 15%" align="center" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 70%">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel" style="font-size: 14px">订单有效时间10分钟，请尽快完成支付</h4>
            </div>

            <div class="modal-body">
                <%--支付的二维码--%>
                <div align="center">
                    <img id="pay_img" src="" alt="......" style="width: 200px;height: 200px" class="img-circle">
                </div>
            </div>
        </div>
    </div>
</div>

<!-- footers start -->
<footer id="footer" class="footers navbar-fixed-bottom">
    <div class="footer container-fluid footers">
        Copy Right @ 2020 Services To Help Agriculture
    </div>
</footer>
<!-- footers end -->
</body>

</html>
