<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>个人中心</title>
    <link rel="shortcut icon" type="image/x-icon" href="https://www.xmlvhy.com/images/xmshop/favicon.ico"
          media="screen"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/iconfont.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/zshop.js"></script>

    <script>
        $(function () {
            autoFooterHeight();
            $('#frmModifyCustomer').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    phone: {
                        validators: {
                            notEmpty: {
                                message: '联系电话不能为空'
                            },
                            regexp: {
                                regexp: '^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$',
                                message: '无效的手机号'
                            }
                        }
                    },
                    address: {
                        validators: {
                            notEmpty: {
                                message: '联系地址不能为空'
                            }
                        }
                    }
                }
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

        //显示修改编辑信息弹框
        function showModifyCustomer(customerId) {
            console.log(customerId);
            $('#customerId').val(customerId);
            $('#ModifyCustomerModal').modal('show');
        }

        //修改用户信息
        function modifyCustomer() {
            $.post(
                '${pageContext.request.contextPath}/front/customer/modifyCenterCustomer',
                {
                    'customerId': $('#customerId').val(),
                    'mobile': $('#mobile').val(), 'address': $('#customerAddr').val()
                },
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
    </script>
</head>

<body>

<!-- 导航栏 start -->
<jsp:include page="top.jsp">
    <jsp:param name="num" value="4"/>
</jsp:include>
<!-- 导航栏 end -->

<!-- content start -->
<div class="container" style="margin-top:100px;margin-left: 350px">
    <div class="row">
        <div class="col-sm-6">
            <div class="page-header" style="margin-bottom: 0px;">
                <h3>基本资料
                    <span style="letter-spacing:1px;font-size:14px;cursor: pointer;float: right"
                          class="glyphicon glyphicon-edit text-success"
                          onclick="showModifyCustomer(${user.id})">编辑</span>
                </h3>
            </div>
        </div>
    </div>
</div>
<div class="container" style="margin-left: 350px">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">用户姓名:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="name" placeholder="用户姓名" readonly="readonly"
                       value="${user.name}">
            </div>
        </div>
        <div class="form-group">
            <label for="loginName" class="col-md-2 col-sm-2 control-label">登陆账号:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="loginName" placeholder="登陆账号" readonly="readonly"
                       value="${user.loginName}">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-md-2  col-sm-2 control-label">联系电话:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="phone" placeholder="联系电话" value="${user.phone}" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-md-2   col-sm-2  control-label">联系地址:</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" id="address" placeholder="详细地址" value="${user.address}"
                       readonly>
            </div>
        </div>
        <%--<div class="form-group">--%>
        <%--<div class="col-sm-offset-2 col-sm-3">--%>
        <%--<button type="submit" class="btn btn-info">保存</button>--%>
        <%--<button type="submit" class="btn btn-info">取消</button>--%>
        <%--</div>--%>
        <%--</div>--%>
    </form>
</div>
<div class="modal fade" id="ModifyCustomerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="top: 10%">
    <div class="modal-dialog" role="document" style="width: 36%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">个人信息修改</h4>
            </div>
            <div class="modal-body" style="overflow: auto">
                <form class="form-horizontal col-sm-11" id="frmModifyCustomer">
                    <div class="form-group">
                        <label for="customerName" class="col-sm-4 control-label">用户姓名</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="customerName" name="name" readonly
                                   value="${user.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="customerLoginName" class="col-sm-4 control-label">登录账号</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="customerLoginName" name="loginName"
                                   value="${user.loginName}" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mobile" class="col-sm-4 control-label">联系电话</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="mobile" name="phone" value="${user.phone}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="customerAddr" class="col-sm-4 control-label">联系地址</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="customerAddr" name="address"
                                   value="${user.address}">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="customerId" id="customerId">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="modifyCustomer()">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- content end-->

<!-- footers start -->
<footer id="footer" class="footer navbar-fixed-bottom">
    <div class="container-fluid footers">
        Copy Right @ 2020 Services To Help Agriculture
    </div>
</footer>
<!-- footers end -->
</body>

</html>
