<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>我的订单</title>
    <link rel="shortcut icon" type="image/x-icon" href="https://www.xmlvhy.com/images/xmshop/favicon.ico"
          media="screen"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/iconfont.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/zshop.js"></script>

    <style>
        .list-group-item {
            margin-bottom: 0;
            border: 0;
            background-color: inherit;
        }

        .list2 {
            border-bottom: 0;
            cursor: pointer;
            border: 0;
        }
        .second li{
            padding: 8px 6px;
        }
        /*地址卡片鼠标放上去的状态*/
        .second li:hover {
            background: #9d9d9d;
        }

        /*地址卡片选中状态*/
        .second li.active, .list-group li.active:hover {
            background-color: #f0ad4e;
        }

        .table tbody tr td {
            vertical-align: middle;
        }

        .table th {
            text-align: center !important;
        }

        .table > tbody > tr > th {
            text-align: center;
            border-bottom: 0;
            border-top: 0;
            background: #fafafa url(${pageContext.request.contextPath}/images/bag.gif) repeat-x;
        }

        .table > tbody > tr > td {
            position: relative;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        .table > tbody > tr > td:first-child {
            border-left: 1px solid #ddd;
        }

        .table > tbody > tr > td:last-child {
            border-right: 1px solid #ddd;
        }

        table tr td img {
            width: 60px;
            height: 60px;
        }
    </style>

    <script>
        $(function () {

            //TODO:当从订单确认页面点击浏览器返回时候设置刷新页面
            var e = document.getElementById("refreshed");
            if (e.value == "no") e.value = "yes";
            else {
                e.value = "no";
                //TODO:点击页面刷新会有问题，会刷新两次页面
                location.reload();
            }

            //默认选中第一个地址卡片
            // $('.second li:first-child').addClass("active");

            //点击则选中点击的地址卡片
            $('.second li').click(function () {
                // $('.second li').removeClass('active');
                // $(this).addClass('active');
            });
            autoFooterHeight();
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

        //点击订单号跳转到详情页面
        function showOrderDetailInfo(orderNo) {
            location.href = '${pageContext.request.contextPath}/front/order/showOrderDetailInfo?orderNo=' + orderNo;
        }

        <%--显示提示取消订单的modal 框--%>
        function showCancelOrderModal(orderId) {
            console.log(orderId);
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
            console.log(removeOrderId);
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
            console.log(confirmOrderId);
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

        //支付订单
        function payMyOrders(orderNumber) {
            location.href = '${pageContext.request.contextPath}/front/order/showPayOrders?orderNumber='+orderNumber;
        }

        /*左边导航栏*/
        //显示未支付的订单列表
        function showNotPaid() {
            location.href = '${pageContext.request.contextPath}/front/order/showNotPaid';
        }
        //显示未发货的订单列表
        function showNotShipped() {
            location.href = '${pageContext.request.contextPath}/front/order/showNotShipped';
        }
        //显示待收货的订单列表
        function showReceive() {
            location.href = '${pageContext.request.contextPath}/front/order/showReceive';
        }
        //显示已完成交易的订单列表
        function showFinished() {
            location.href = '${pageContext.request.contextPath}/front/order/showFinished';
        }
        //显示已取消的订单列表
        function showCancel() {
            location.href = '${pageContext.request.contextPath}/front/order/showCancel';
        }
        //显示回收站的订单列表
        function showDelete() {
            location.href = '${pageContext.request.contextPath}/front/order/showDelete';
        }
        
        //展示商品详情
        function showProductFromOrder(productId) {
            location.href = '${pageContext.request.contextPath}/front/product/showProductDetail?id='+productId;
        }
    </script>
</head>

<body class="animated fadeIn">
<!-- 导航栏 start -->
<jsp:include page="top.jsp">
    <jsp:param name="num" value="3"/>
</jsp:include>
<!-- 导航栏 end -->

<!-- content start -->
<div class="container" style="margin-top: 50px">
    <div class="row">
        <div class="col-sm-12">
            <div class="page-header" style="margin-top: 30px;">
                <h3>我的订单</h3>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3" style="background: #f7f7f7">
            <ul class="list-group">
                <li class="list-group-item"><b><span style="color: #f0ad4e">全部订单</span></b>
                    <ul class="list-group second" style="margin-top: 10px">
                        <li class="list-group-item list2" onclick="showNotPaid()">
                            <span class="badge" style="color: #f0ad4e">${map.get("unpaid")}</span>
                            待付款
                        </li>
                        <li class="list-group-item list2" onclick="showNotShipped()">
                            <span class="badge" style="color: #f0ad4e">${map.get("notShipped")}</span>
                            待发货
                        </li>
                        <li class="list-group-item list2" onclick="showReceive()">
                            <span class="badge" style="color: #f0ad4e">${map.get("unReceive")}</span>
                            待收货
                        </li>
                        <li class="list-group-item list2" onclick="showFinished()">
                            <span class="badge" style="color: #f0ad4e">${map.get("finished")}</span>
                            待评价
                        </li>
                        <li class="list-group-item list2" onclick="showCancel()">
                            <span class="badge" style="color: #f0ad4e">${map.get("cancel")}</span>
                            已取消
                        </li>
                        <li class="list-group-item list2" onclick="showDelete()">
                            <span class="badge" style="color: #f0ad4e">${map.get("delete")}</span>
                            回收站
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="col-sm-9">
            <table class="table table-hover orderDetail">
                <tr class="text-warning">
                    <th>商品图片</th>
                    <th>名称</th>
                    <th>数量</th>
                    <th>单价</th>
                    <th>实付款（元）</th>
                    <th>交易状态</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${orderVoList}" var="orderVo">
                    <tr>
                        <td colspan="7" style="border: 0">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7" style="text-align: left;background-color: cornsilk">
                            <span>订单编号：<a class="btn-link" style="text-decoration: none;color: #333333"
                                          href="javascript:void(0)"
                                          onclick="showOrderDetailInfo('${orderVo.orderNumber}')">${orderVo.orderNumber}
                            </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            <span>下单时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
                                                       value="${orderVo.createDate}"/></span>
                        </td>
                    </tr>
                    <c:forEach items="${orderVo.orderItemList}" var="orderItem">
                        <tr>
                            <td class="col-sm-1">
                                <img src="${orderItem.product.image}" alt="" style="cursor: pointer" onclick="showProductFromOrder(${orderItem.product.id})">
                            </td>
                            <td><span style="cursor: pointer" onclick="showProductFromOrder(${orderItem.product.id})">${orderItem.product.name}</span></td>
                            <td><span>${orderItem.num}</span></td>
                            <td><span>&yen; ${orderItem.product.price}</span></td>
                            <td><span>&yen; ${orderItem.price}</span></td>
                            <td>
                                <span>
                                    <c:if test="${orderVo.status == 0}">待支付</c:if>
                                    <c:if test="${orderVo.status == 1}">买家已支付待发货</c:if>
                                    <c:if test="${orderVo.status == 2}">卖家已发货待收货</c:if>
                                    <c:if test="${orderVo.status == 3}">交易已完成</c:if>
                                    <c:if test="${orderVo.status == 4}">订单已取消</c:if>
                                </span>
                            </td>
                            <td>
                                    <%--如果是未支付状态则显示可移除订单中的某个商品--%>
                                    <%--<c:if test="${orderVo.status == 0}">--%>
                                    <%--<span style="cursor: pointer;" class="glyphicon glyphicon-trash" data-toggle="modal"--%>
                                    <%--data-target="#removeOrderProductModal">--%>
                                    <%--</span>--%>
                                    <%--</c:if>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="7">
                            <div align="left">
                                <span class="left">总计: <b><span
                                        style="color: #FF9D00;">&yen; ${orderVo.price}</span></b></span>
                                    <%--<span style="margin-left: 65%">--%>
                                    <%--<button class="btn btn-info">前往支付</button>--%>
                                    <%--</span>--%>
                                <c:if test="${orderVo.status == 0}">
                                    <span class="pull-right">
                                        <button class="btn btn-success" onclick="payMyOrders('${orderVo.orderNumber}')">支付订单</button>
                                        <button style="padding: 6px 12px" class="btn btn-warning"
                                                onclick="showCancelOrderModal(${orderVo.id})">取消订单</button>
                                    </span>
                                </c:if>
                                <c:if test="${orderVo.status == 1}">
                                    <span class="pull-right">
                                        <button class="btn btn-primary" onclick="remindSeller()">提醒发货</button>
                                    </span>
                                </c:if>
                                <c:if test="${orderVo.status == 2}">
                                    <span class="pull-right">
                                        <button class="btn btn-info"
                                                onclick="showConfirmModal(${orderVo.id})">确认收货</button>
                                    </span>
                                </c:if>
                                <c:if test="${orderVo.status == 3}">
                                    <span class="pull-right">
                                    <button style="padding: 6px 12px" class="btn btn-danger"
                                            onclick="showRemoveOrderModal(${orderVo.id})">删除订单</button>
                                     </span>
                                </c:if>

                                <c:if test="${orderVo.status == 4}">
                                    <span class="pull-right">
                                    <button style="padding: 6px 12px" class="btn btn-danger"
                                            onclick="showRemoveOrderModal(${orderVo.id})">删除订单</button>
                                     </span>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                    <%--<!--间隔-->--%>
                    <%--<tr>--%>
                    <%--<td colspan="7" style="border: 0">--%>
                    <%--</td>--%>
                    <%--</tr>--%>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<!-- content end-->

<div style="height: 288px"></div>

<!-- footers start -->
<footer id="footer" class="footers navbar-fixed-bottom">
    <div class="footer container-fluid footers">
        Copy Right @ 2020 Services To Help Agriculture
    </div>
</footer>
<!-- footers end -->
<input type="hidden" id="refreshed" value="no">
<!--删除订单弹出提示框-->
<div class="modal fade" tabindex="-1" id="removeOrderModal" style="top: 10%;">
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
<div class="modal fade" tabindex="-1" id="cancelOrderModal" style="top: 10%;">
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
<div class="modal fade" tabindex="-1" id="confirmOrderModal" style="top: 10%;">
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

</body>

</html>
