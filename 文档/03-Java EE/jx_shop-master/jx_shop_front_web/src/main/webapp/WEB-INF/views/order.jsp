<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>确认订单</title>
    <link rel="shortcut icon" type="image/x-icon" href="https://www.xmlvhy.com/images/xmshop/favicon.ico"
          media="screen"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/js/area.js"></script>
    <script src="${pageContext.request.contextPath}/js/template.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css"/>
    <style>
        .list-address {
            padding-left: 5px;
            margin-top: 15px;
        }

        /*添加地址卡片默认的状态*/
        .list-add-address li {
            border: 0;
            list-style: none;
            margin-right: 20px;
            background: url("${pageContext.request.contextPath}/images/address.png") no-repeat;
            height: 162px;
            width: 240px;
            background-position: -240px 0;
        }

        /*地址卡片默认的状态*/
        .list-address li {
            border: 0;
            list-style: none;
            margin-right: 20px;
            background: url("${pageContext.request.contextPath}/images/address.png") no-repeat;
            height: 162px;
            width: 240px;
            background-position: -240px 0;
        }

        /*地址卡片鼠标放上去的状态*/
        .list-address li:hover {
            background-position: -480px 0;
        }

        /*地址卡片选中状态*/
        .list-address li.active, .list-address li.active:hover {
            background-position: 0 0;
        }

        select {
            -webkit-appearance: none;
            height: 32px;
            border-radius: 5px;
            border: 1px solid #ddd;
            text-align: center;
            padding: 0 10px;
        }

        <%--让表格中的数据居中--%>
        .table tbody tr td {
            vertical-align: middle;
        }

        .table th {
            text-align: center !important;
        }

    </style>

    <script>
        $(function () {
            //设置底部footer 自适应高度
            autoFooterHeight();

            //默认选中第一个地址卡片
            $('.list-address li:first-child').addClass("active");
            //拿到默认选中的 地址id
            $('#modifyId').val($('.list-address li:first-child').next().val());
            $('#id').val($('.list-address li:first-child').next().val());
            $('#orderAddress').val($('.list-address li:first-child').next().val());
            // console.log($('.list-address li:first-child').next().val());

            //点击则选中点击的地址卡片
            $('.list-address li').click(function () {
                $('.list-address li').removeClass('active');
                $(this).addClass('active');
                $('#modifyId').val($(this).next().val());
                //给修改模态框中隐藏域保存id
                $('#id').val($(this).next().val());
                $('#orderAddress').val($(this).next().val());
                // console.log($(this).next().val());
            });

            //点击删除按钮
            $('.mydelSpan span').click(function () {
                // console.log($(this).prev().val());
                // $(this).parent().parent().removeClass('active');
            });
            //新增地址表单校验
            $('#frmAddShipping').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    receiverName: {
                        validators: {
                            notEmpty: {
                                message: '收件人姓名不能为空'
                            }
                        }
                    },
                    receiverMobile: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: '^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$',
                                message: '无效的手机号'
                            }
                        }
                    },
                    receiverProvince: {
                        validators: {
                            remote: {
                                type: 'post',
                                url: '${pageContext.request.contextPath}/front/shipping/checkProvince'
                            }
                        }
                    },
                    receiverCity: {
                        validators: {
                            remote: {
                                type: 'post',
                                url: '${pageContext.request.contextPath}/front/shipping/checkCity'
                            }
                        }
                    },
                    receiverDistrict: {
                        validators: {
                            remote: {
                                type: 'post',
                                url: '${pageContext.request.contextPath}/front/shipping/checkDistrict'
                            }
                        }
                    },
                    addressDetail: {
                        validators: {
                            notEmpty: {
                                message: '详细地址不能为空'
                            }
                        }
                    }
                }
            });

            //修改地址表单校验
            $('#frmModifyShipping').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    receiverName: {
                        validators: {
                            notEmpty: {
                                message: '收件人姓名不能为空'
                            }
                        }
                    },
                    receiverMobile: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: '^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$',
                                message: '无效的手机号'
                            }
                        }
                    },
                    receiverProvince: {
                        validators: {
                            notEmpty: {
                                message: '请填写省份信息'
                            }
                        }
                    },
                    receiverCity: {
                        validators: {
                            notEmpty: {
                                message: '请填写市信息'
                            }
                        }
                    },
                    receiverDistrict: {
                        validators: {
                            notEmpty: {
                                message: '请填写区或县信息'
                            }
                        }
                    },
                    addressDetail: {
                        validators: {
                            notEmpty: {
                                message: '详细地址不能为空'
                            }
                        }
                    }
                }
            });

            //确认之后,清空原来模态框中校验的数据
            $('#myAddressAddModal').on('hide.bs.modal', function () {
                $('#frmAddShipping').bootstrapValidator('resetForm');
                $('#frmAddShipping')[0].reset();
            });

            //确认之后,清空原来模态框中校验的数据
            $('#myAddressModifyModal').on('hide.bs.modal', function () {
                $('#frmModifyShipping').bootstrapValidator('resetForm');
                $('#frmModifyShipping')[0].reset();
            });
            //当是否立即支付提示框消息后，即客户不跳转到支付页面，则跳转到订单详情页
            $('#buildOrderModal').on('hide.bs.modal', function () {
                location.replace('${pageContext.request.contextPath}/front/order/showOrderDetails');
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

        //点击添加新地址弹出框
        function showAddAddress() {
            $('#myAddressAddModal').modal('show');
        }

        //点击修改地址弹出框
        function showModifyAddress() {
            var spid = $('#modifyId').val();
            if (spid == '') {
                layer.msg("您还未添加地址，请先添加",{
                    time: 1500,
                    skin: 'warningMsg'
                });
            } else {
                $.post(
                    '${pageContext.request.contextPath}/front/shipping/showOneShipping',
                    {'shippingId': $('#modifyId').val()},
                    function (result) {
                        if (result.status == 1) {
                            //成功,则设置值显示
                            $('#modify_name').val(result.data.receiverName);
                            $('#modify_phone').val(result.data.receiverPhone);
                            $('#modify_mobile').val(result.data.receiverMobile);
                            $('#modify_addressDetail').val(result.data.addressDetail);
                            $('#modify_zipCode').val(result.data.zipCode);
                            $('#s_province').val(result.data.receiverProvince);
                            $('#s_city').val(result.data.receiverCity);
                            $('#s_county').val(result.data.receiverDistrict);
                            //TODO:使用area.js 三级地址联动，无法实现模态框中设置回显值
                            // $('#s_province').val(result.data.receiverProvince);
                            // change(1);
                            // $('#s_city').val(result.data.receiverCity);
                            // change(2);
                            // $('#s_county').val(result.data.receiverDistrict);

                        } else {
                            layer.msg(result.message, {
                                time: 1500,
                                skin: 'errorMsg'
                            });
                        }
                    }
                );
                $('#myAddressModifyModal').modal('show');
            }
        }

        //修改地址
        function modifyShipping() {
            $('#frmModifyShipping').data('bootstrapValidator').validate();
            var flag = $('#frmModifyShipping').data('bootstrapValidator').isValid();
            if (flag) {
                $.post(
                    '${pageContext.request.contextPath}/front/shipping/modifyShipping',
                    $('#frmModifyShipping').serialize(),
                    function (result) {
                        if (result.status == 1) {
                            //成功，重新加载页面
                            layer.msg(result.message, {
                                time: 1000,
                                skin: 'successMsg'
                            },function () {
                                //地址修改成功，刷新页面
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
        }

        //新增收货地址
        function addShipping() {
            //先进行表单校验
            $('#frmAddShipping').data('bootstrapValidator').validate();
            var flag = $('#frmAddShipping').data('bootstrapValidator').isValid();
            if (flag) {
                $.post(
                    '${pageContext.request.contextPath}/front/shipping/saveShipping',
                    $('#frmAddShipping').serialize(),
                    function (result) {
                        if (result.status == 1) {
                            //地址新增成功
                            layer.msg(result.message, {
                                time: 1000,
                                skin: 'successMsg'
                            },function () {
                                //刷新页面
                                location.reload();
                            });
                        } else {
                            //地址新增失败
                            layer.msg(result.message, {
                                time: 1500,
                                skin: 'errorMsg'
                            });
                        }
                    }
                );
            }
        }

        //显示删除地址提示框
        function showDelModal(id) {
            $('#shippingId').val(id);
            $('#removeShoppingModal').modal('show');
        }

        //删除地址
        function removeShipping() {
            $.post(
                '${pageContext.request.contextPath}/front/shipping/removeShipping',
                {'shippingId': $('#shippingId').val()},
                function (result) {
                    if (result.status == 1) {
                        //重新加载页面
                        location.reload();
                    } else {
                        layer.msg(result.message, {
                            time: 1500,
                            skin: 'errorMsg'
                        });
                    }
                }
            );
        }

        //提交订单
        function commitOrders() {
            var shippingId = $('#orderAddress').val();
            var status = '${orderList.get(0).status}';
            <%--if (status == 2) {--%>
            <%--//表示走直接购买的流程--%>
            <%--$.post(--%>
            <%--'${pageContext.request.contextPath}/front/order/redirectAddOrder',--%>
            <%--{'shippingId': shippingId},--%>
            <%--function (result) {--%>
            <%--if (result.status == 1) {--%>
            <%--//创建订单成功--%>
            <%--$('#orderNo').text(result.message);--%>
            <%--$('#orderNumber').val(result.message);--%>
            <%--$('#buildOrderModal').modal('show');--%>
            <%--}--%>
            <%--}--%>
            <%--);--%>
            <%--} else {--%>
            $.post(
                '${pageContext.request.contextPath}/front/order/addOrder',
                {'shippingId': shippingId, 'status': status},
                function (result) {
                    if (result.status == 1) {
                        //创建订单成功
                        $('#orderNo').text(result.message);
                        $('#orderNumber').val(result.message);
                        $('#buildOrderModal').modal('show');
                    }
                }
            );
            //}
        }

        //如果没有点击立即支付，则自动跳转到订单明细页面
        function cancelOrder() {
            location.replace('${pageContext.request.contextPath}/front/order/showOrderDetails');
        }

        //点击返回
        function backCart() {
            //刷新上一级页面
            self.location = document.referrer;
            //判断是否是直接直接购买产生的购物车
            var status = '${orderList.get(0).status}';
            var cartId = '${orderList.get(0).id}'
            if (status == 2) {
                //把这个购物车清空
                $.post(
                    '${pageContext.request.contextPath}/front/cart/removeOneProduct',
                    {'cartId': cartId},
                    function (result) {
                        if (result.status == 1) {
                        } else {
                            layer.msg("临时购物车清空失败", {
                                time: 1500,
                                skin: 'errorMsg'
                            });
                        }
                    }
                );
            }
        }

        //立即支付
        function payOrderFromOrder() {
            var orderNumber = $('#orderNumber').val();
            location.href = '${pageContext.request.contextPath}/front/order/showPayOrders?orderNumber=' + orderNumber;
        }
    </script>
</head>

<body class="animated fadeIn">
<!-- 导航栏 start -->
<jsp:include page="top.jsp">
    <jsp:param name="num" value="7"/>
</jsp:include>
<!-- 导航栏 end -->
<!-- content start -->
<%--收货地址确认--%>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header" style="margin-top: 100px;">
                <h3>确认收货地址</h3>
                <p class="text-right">
                    <a class="btn btn-xs btn-info" href="javascript:void(0);" data-toggle="modal"
                       data-target="#myAddressModal" onclick="showModifyAddress()">
                        修改地址
                    </a>
                </p>
            </div>
            <c:choose>
                <c:when test="${empty shippingList}">
                    <!--新增收货地址卡片-->
                    <div class="list-add-address right">
                        <li class="col-sm-3" style="cursor: pointer;margin-top: 10px;margin-left: 5px"
                            onclick="showAddAddress()">
                            <div style="text-align: center;margin-top: 60px;">
                                <span><img src="${pageContext.request.contextPath}/images/add_cart.png"></span>
                            </div>
                        </li>
                    </div>
                </c:when>
                <c:otherwise>
                    <ul class="list-address">
                        <c:forEach items="${shippingList}" var="shipping">
                            <li class="col-sm-3" style="margin-top: 10px">
                                <h4>${shipping.receiverName}</h4>
                                <div class="mydelSpan" style="padding-top:8px">
                                    <p>${shipping.receiverProvince}${shipping.receiverCity}${shipping.receiverDistrict}</p>
                                    <p>${shipping.addressDetail}</p>
                                    <p>${shipping.receiverMobile}</p>
                                    <input type="hidden" name="shippingId" value="${shipping.id}">
                                    <span class="btn btn-xs"
                                          style="color:#8c8c8c;font-size: 10px;margin-bottom: 5px;padding: 0"
                                          onclick="showDelModal(${shipping.id})">删除</span>
                                </div>
                            </li>
                            <input type="hidden" name="shippingId" value="${shipping.id}">
                        </c:forEach>
                    </ul>
                    <!--新增收货地址卡片-->
                    <div class="list-add-address right">
                        <li class="col-sm-3" style="cursor: pointer;margin-top: 10px;"
                            onclick="showAddAddress()">
                            <div style="text-align: center;margin-top: 60px;">
                                <span><img src="${pageContext.request.contextPath}/images/add_cart.png"></span>
                            </div>
                        </li>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!--确认商品信息-->
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header" style="margin-top: 50px;">
                <h3>确认商品信息</h3>
            </div>
        </div>
    </div>
    <table class="table table-hover table-striped table-bordered text-center" style="margin-top: 20px">
        <tr class="success">
            <th>序号</th>
            <th>商品名称</th>
            <th>商品图片</th>
            <th>商品数量</th>
            <th>商品总价</th>
        </tr>
        <c:forEach items="${orderList}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.product.name}</td>
                <td><img src="${order.product.image}" alt="" width="60" height="60"></td>
                <td>${order.productNum}</td>
                <td>&yen; ${order.totalPrice}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" class="foot-msg" align="left">
                <span>共有 <b><span id="totalCount" name="totalCount">${count}</span></b> 件商品 ,&nbsp;</span>
                总计：<b> <span>&yen; ${price}</span></b> 元
            </td>
        </tr>
        <tr>
            <td colspan="5" class="right">
                <%--<a href="javascript:history.go(-1);">--%>
                <a href="javascript:void(0);">
                    <button class="btn btn-warning pull-right" onclick="backCart()">返回</button>
                </a>
                <input type="hidden" name="orderAddress" id="orderAddress">
                <button class="btn btn-warning pull-right margin-right-15" data-toggle="modal"
                        onclick="commitOrders()">提交订单
                </button>
            </td>
        </tr>
    </table>
</div>
<!-- content end-->

<!--订单生成-->
<div class="modal fade" id="buildOrderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="top: 10%;">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title text-center" id="myModalLabel">提示消息</h4>
            </div>
            <div class="modal-body text-center">
                <div class="orderMsg">
                    <h5 style="margin-top: 5px;font-size: 16px" class="text-success">订单提交成功！</h5>
                    <h5 class="text-info">订单号：<span id="orderNo">121314546</span></h5>
                    <h5 class="text-warning">是否立即支付？</h5>
                </div>
                <div class="">
                    <input type="hidden" id="orderNumber">
                    <button style="margin-left: 5px" class="btn btn-success" onclick="payOrderFromOrder()">前往支付
                    </button>
                    <button style="padding: 6px 30px" class="btn btn-warning" onclick="cancelOrder()">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--修改地址模态框-->
<div class="modal fade" id="myAddressModifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改收货地址</h4>
            </div>
            <div class="modal-body" style="overflow: auto">
                <form class="form-horizontal col-sm-10" id="frmModifyShipping">
                    <div class="form-group">
                        <input type="hidden" name="id" id="id"/>
                        <label for="modify_name" class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="modify_name" name="receiverName"
                                   placeholder="收货人姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modify_phone" class="col-sm-3 control-label">座机</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="modify_phone" name="receiverPhone"
                                   placeholder="座机,选填。如：07737160999">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modify_mobile" class="col-sm-3 control-label">手机号码</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="modify_mobile" name="receiverMobile"
                                   placeholder="11位手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">收货地址</label>
                        <div class="col-sm-9">
                            <%--TODO:临时方案使用input代替select，回显值--%>
                            <div class="col-sm-3" style="padding: 0;width: 30%">
                                <input class="form-control" type="text" name="receiverProvince" id="s_province">
                            </div>
                            <div class="col-sm-3" style="padding: 0;margin-left: 15px;width: 30%">
                                <input class="form-control" type="text" name="receiverCity" id="s_city">
                            </div>
                            <div class="col-sm-3" style="padding: 0;margin-left: 15px;width: 30%">
                                <input class="form-control" type="text" name="receiverDistrict" id="s_county">
                            </div>
                            <%--<select id="s_province" name="s_province"></select>--%>
                            <%--<select id="s_city" name="s_city"></select>--%>
                            <%--<select id="s_county" name="s_county"></select>--%>
                            <%--<script class="resources library" src="${pageContext.request.contextPath}/js/area.js"--%>
                            <%--type="text/javascript"></script>--%>
                            <%--<script type="text/javascript">_init_area('s_province', 's_city', 's_county');</script>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modify_addressDetail" class="col-sm-3 control-label">详细地址</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="modify_addressDetail" name="addressDetail"
                                   placeholder="路名或街道地址，门牌号...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modify_zipCode" class="col-sm-3 control-label">邮政编码</label>
                        <div class="col-sm-9">
                            <input id="modify_zipCode" class="form-control" type="text" name="zipCode"
                                   placeholder="邮政编码,选填。如：3646633...">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="shippingId" id="modifyId">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="modifyShipping()">确认修改</button>
            </div>
        </div>
    </div>
</div>

<!--添加地址模态框-->
<div class="modal fade" id="myAddressAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加收货地址</h4>
            </div>
            <form class="form-horizontal" style="width: 85%" id="frmAddShipping">
                <div class="modal-body" style="overflow: auto">
                    <div class="form-group">
                        <label for="add_name" class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="add_name" name="receiverName"
                                   placeholder="收货人姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_phone" class="col-sm-3 control-label">座机</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="add_phone" name="receiverPhone"
                                   placeholder="座机号码,选填。如：07737160999">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_mobile" class="col-sm-3 control-label">手机号码</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="add_mobile" name="receiverMobile"
                                   placeholder="11位手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">收货地址</label>
                        <div class="col-sm-9">
                            <select id="add_s_province" name="receiverProvince"></select>
                            <select id="add_s_city" name="receiverCity"></select>
                            <select id="add_s_county" name="receiverDistrict"></select>
                            <script class="resources library" src="${pageContext.request.contextPath}/js/area.js"
                                    type="text/javascript"></script>
                            <script type="text/javascript">_init_area('add_s_province', 'add_s_city', 'add_s_county');</script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_addressDetail" class="col-sm-3 control-label">详细地址</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="add_addressDetail" name="addressDetail"
                                   placeholder="路名或街道地址，门牌号...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_zipCode" class="col-sm-3 control-label">邮政编码</label>
                        <div class="col-sm-9">
                            <input id="add_zipCode" class="form-control" type="text" name="zipCode"
                                   placeholder="邮政编码,选填。如：3646633...">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="addShippingBtn" class="btn btn-primary" onclick="addShipping()">确认添加
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--弹出提示确认框--%>
<div class="modal fade" tabindex="-1" id="removeShoppingModal" style="top: 10%">
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
                <h5 class="text-warning">确认删除此收货地址吗？</h5>
            </div>
            <div class="modal-footer">
                <%--TODO:设置一个隐藏域来存放所要删除商品的id--%>
                <input type="hidden" id="shippingId">
                <button class="btn btn-primary updateProType" onclick="removeShipping()" data-dismiss="modal">确认
                </button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<div style="height: 288px"></div>

<!-- footers start -->
<footer id="footer" class="footers navbar-fixed-bottom">
    <div class="footer container-fluid footers">
        Copy Right @ 2020 Services To Help Agriculture
    </div>
</footer>
<!-- footers end -->
</body>

</html>
