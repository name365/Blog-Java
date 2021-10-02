<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>订单详情</title>
    <link rel="shortcut icon" type="image/x-icon" href="https://www.xmlvhy.com/images/xmshop/favicon.ico"
          media="screen"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

    <style>
        /*让表格中的数据居中*/
        .table tbody tr td {
            vertical-align: middle;
        }

        .table th {
            text-align: center !important;
        }
    </style>
    <script>
    </script>
</head>

<body class="animated fadeIn">
<!-- 导航栏 start -->
<jsp:include page="top.jsp">
    <jsp:param name="num" value="8"/>
</jsp:include>
<!-- 导航栏 end -->

<!-- content start -->
<div class="container" style="margin-top: 50px">
    <c:choose>
        <c:when test="${empty order}">
            <h3 class="text-success" style="text-align: center;margin-top: 200px">
                该订单已删除, <a class="btn-link" href="javascript:void(0)">前往回收站查看~</a>
            </h3>
        </c:when>
        <c:otherwise>
            <div class="row">
                <c:if test="${order.status != 4}">
                    <div class="col-xs-12">
                        <div class="page-header" style="margin-bottom: 0px;">
                            <h3>收货地址</h3>
                        </div>
                        <div class="">
                            <b><span style="letter-spacing: 1px;font-size: 15px; color: #f0ad4e">${order.address}</span></b>
                        </div>
                    </div>
                </c:if>
                <div class="col-xs-12">
                    <div class="page-header" style="margin-bottom: 0px;">
                        <h3>订单详情</h3>
                    </div>
                </div>
            </div>
            <div class="row head-msg">
                <div class="col-xs-12">
                    <span style="font-size: 15px">用户: </span><b><span
                        style="font-size: 14px">${sessionScope.get("customer").name}</span></b>
                    <span style="margin-left: 15px;font-size: 15px">订单号: </span><b><span
                        style="font-size: 14px">${order.orderNumber}</span></b>
                    <span style="margin-left: 15px;font-size: 15px">订单状态:</span>
                    <b>
                <span style="font-size: 14px">
                    <c:if test="${order.status == 0}">待支付</c:if>
                    <c:if test="${order.status == 1}">买家已支付待发货</c:if>
                    <c:if test="${order.status == 2}">卖家已发货待收货</c:if>
                    <c:if test="${order.status == 3}">交易已完成</c:if>
                    <c:if test="${order.status == 4}">订单已取消</c:if>
                </span>
                    </b>
                </div>
            </div>
            <table class="table table-hover table-striped table-bordered text-center" style="margin-top: 5px">
                <tr class="text-success">
                    <th>序号</th>
                    <th>商品名称</th>
                    <th>商品图片</th>
                    <th>商品数量</th>
                    <th>商品总价</th>
                </tr>
                <c:forEach items="${orderItems}" var="orderItem">
                    <tr>
                        <td>${orderItem.id}</td>
                        <td><span style="cursor: pointer" onclick="showProductFromOrder(${orderItem.product.id})">${orderItem.product.name}</span></td>
                        <td><img src="${orderItem.product.image}" alt="" width="60" height="60" style="cursor: pointer" onclick="showProductFromOrder(${orderItem.product.id})"></td>
                        <td>${orderItem.num}</td>
                        <td>${orderItem.price}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="5" class="foot-msg" align="left">
                    <span>共有 <b><span id="totalCount"
                                      name="totalCount">${order.productNumber}</span></b> 件商品 ,&nbsp;</span>
                        总计：<b> <span>&yen; ${order.price}</span></b> 元
                    </td>
                </tr>
                <tr>
                    <td colspan="5" align="right">
                        <c:if test="${order.status == 0}">
                            <button type="button" class="btn btn-success" onclick="payOrder()">支付订单</button>
                            <button class="btn btn-warning" style="padding: 6px 12px"
                                    onclick="showCancelOrderModal(${order.id})">取消订单
                            </button>
                        </c:if>
                        <c:if test="${order.status == 1}">
                            <button type="button" class="btn btn-primary" onclick="remindSeller()">提醒发货</button>
                        </c:if>
                        <c:if test="${order.status == 2}">
                            <button class="btn btn-info" onclick="showConfirmModal(${order.id})">确认收货</button>
                        </c:if>
                        <c:if test="${order.status == 3}">
                            <button style="padding: 6px 12px" class="btn btn-danger"
                                    onclick="showRemoveOrderModal(${order.id})">删除订单
                            </button>
                        </c:if>
                        <c:if test="${order.status == 4}">
                            <button style="padding: 6px 12px" class="btn btn-danger"
                                    onclick="showRemoveOrderModal(${order.id})">删除订单
                            </button>
                        </c:if>
                    </td>
                </tr>
            </table>

        </c:otherwise>
    </c:choose>
</div>
<!-- content end-->

<!--删除订单弹出提示框-->
<div class="modal fade" tabindex="-1" id="removeOrderModal" style="top: 10%">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-sm">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">提示消息</h4>
            </div>
            <div class="modal-body text-center">
                <h5 class="text-warning" style="letter-spacing: 1px">确认要删除该笔订单吗？</h5>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="removeOrderId" id="removeOrderId">
                <button class="btn btn-primary updateProType" data-dismiss="modal" onclick="removeOrder()">确认</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!--取消订单弹出提示框-->
<div class="modal fade" tabindex="-1" id="cancelOrderModal" style="top: 10%">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-sm">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">提示消息</h4>
            </div>
            <div class="modal-body text-center">
                <h5 class="text-warning" style="letter-spacing: 1px">确认要取消该笔订单吗？</h5>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="orderId" id="orderId">
                <button class="btn btn-primary updateProType" data-dismiss="modal" onclick="cancelThisOrder()">确认
                </button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<%--确认收货--%>
<div class="modal fade" tabindex="-1" id="confirmOrderModal">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-sm">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">提示消息</h4>
            </div>
            <div class="modal-body text-center">
                <h5 class="text-warning" style="letter-spacing: 1px">确认完成收货吗？</h5>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="confirmOrderId" id="confirmOrderId">
                <button class="btn btn-primary updateProType" data-dismiss="modal" onclick="confirmReceiveOrder()">确认
                </button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<div style="height: 150px"></div>
<!-- footers start -->
<footer id="footer" class="footers navbar-fixed-bottom">
    <div class="footer container-fluid footers">
        Copy Right @ 2020 Services To Help Agriculture
    </div>
</footer>
<!-- footers end -->
<script>
    //点击支付跳转到支付页面
    function payOrder() {
        location.href = '${pageContext.request.contextPath}/front/order/showPayOrders?orderNumber='+'${order.orderNumber}';
    }

    <%--显示提示取消订单的modal 框--%>
    function showCancelOrderModal(orderId) {
        $('#orderId').val(orderId);
        $('#cancelOrderModal').modal('show');
    }

    /*取消订单确认*/
    function cancelThisOrder() {
        $.post(
            '${pageContext.request.contextPath}/front/order/cancelOrder',
            {"orderId": $('#orderId').val()},
            function (result) {
                if (result.status == 1) {
                    layer.msg(result.message, {
                        time: 1000,
                        skin: 'successMsg'
                    }, function () {
                        //重新加载页面
                        location.reload();
                    });
                } else {
                    layer.msg(result.message, {
                        time: 1500,
                        skin: 'errorMsg'
                    });
                }
            }
        );
    }

    //显示删除订单的modal框
    function showRemoveOrderModal(removeOrderId) {
        $('#removeOrderId').val(removeOrderId);
        $('#removeOrderModal').modal('show');
    }

    //删除订单
    function removeOrder() {
        $.post(
            '${pageContext.request.contextPath}/front/order/removeOrder',
            {'removeOrderId': $('#removeOrderId').val()},
            function (result) {
                if (result.status == 1) {
                    layer.msg(result.message, {
                        time: 1000,
                        skin: 'successMsg'
                    }, function () {
                        location.reload();
                    });
                } else {
                    layer.msg(result.message, {
                        time: 1500,
                        skin: 'errorMsg'
                    });
                }
            }
        );
    }

    //显示确认收货的提示框
    function showConfirmModal(confirmOrderId) {
        $('#confirmOrderId').val(confirmOrderId);
        $('#confirmOrderModal').modal('show');
    }

    //确认收货
    function confirmReceiveOrder() {
        $.post(
            '${pageContext.request.contextPath}/front/order/confirmReceiveOrder',
            {'confirmOrderId': $('#confirmOrderId').val()},
            function (result) {
                if (result.status == 1) {
                    layer.msg(result.message, {
                        time: 1000,
                        skin: 'successMsg'
                    }, function () {
                        location.reload();
                    });
                } else {
                    layer.msg(result.message, {
                        time: 1500,
                        skin: 'errorMsg'
                    });
                }
            }
        );
    }

    //显示提醒卖家发货modal
    function remindSeller() {
        layer.msg("已提醒卖家发货", {
            time: 1500,
            skin: 'warningMsg'
        });
    }

    //展示商品详情
    function showProductFromOrder(productId) {
        location.href = '${pageContext.request.contextPath}/front/product/showProductDetail?id='+productId;
    }
</script>

</body>

</html>
