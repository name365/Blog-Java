<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css"/>
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath}/js/template.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css"/>

<%--TODO:artTemplate的使用--%>
<script id="welcome" type="text/html">
    <li class="userName">
        欢迎您 : <span id="customerName" class="text text-success">{{name}} !</span>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
            <img class="img-circle" src="${pageContext.request.contextPath}/images/user.jpeg"
                 height="30"/>
            <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
            <li>
                <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                    <i class="glyphicon glyphicon-cog"></i>修改密码
                </a>
            </li>
            <li>
                <a href="#" onclick="logout()">
                    <i class="glyphicon glyphicon-off"></i> 退出
                </a>
            </li>
        </ul>
    </li>
</script>

<%--TODO:修改密码重新登录加载模板--%>
<script type="text/html" id="loginOrRegist">
    <li>
        <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
    </li>
    <li>
        <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
    </li>
</script>

<script>
    $(function () {
        //默认选中首页
        // $('ul.navbar-nav li:first-child').addClass("active");
        //TODO:找到所有的li标签，对其 a 标签的链接地址进行判断，
        //TODO: 如何和当前浏览器的地址一致，就认为是当前应该激活的菜单，添加active类，否则就取消
        // $(".navbar-nav").find("li").each(function () {
        //     var a = $(this).find("a:first")[0];
        //     if ($(a).attr("href") === location.pathname) {
        //         $(this).addClass("active");
        //     } else {
        //         $(this).removeClass("active");
        //     }
        // });

        //TODO:用户账户密码登录数据校验
        $('#frmLoginByAccount').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                loginName: {
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                }
            }
        });

        //TODO:用户短信快捷登录校验
        $('#frmSmsLogin').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                phone: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: '^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$',
                            message: '无效的手机号码'
                        }
                    }
                },
                verifyCode: {
                    validators: {
                        notEmpty: {
                            message: '验证码不能为空'
                        },
                        regexp: {
                            regexp: '',
                            message: ''
                        },
                        remote: {
                            type: 'post',
                            url: '${pageContext.request.contextPath}/front/sms/CheckSmsCode'
                        }
                    }
                }
            }
        });

        //TODO:修改密码数据校验
        $('#frmModifyPWD').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                password: {
                    validators: {
                        notEmpty: {
                            message: '原始密码不能为空'
                        },
                        remote: {
                            type: 'post',
                            url: '${pageContext.request.contextPath}/front/customer/checkPassword'
                        }
                    }
                },
                newpassword: {
                    validators: {
                        notEmpty: {
                            message: '新密码不能为空'
                        },
                        identical: {
                            filed: 'password',
                            message: '新密码与旧密码一致'
                        },
                        stringLength: {
                            min: 6,
                            max: 18,
                            message: '用户密码长度不能少于6位或大于18位'
                        }
                    }
                },
                repassword: {
                    validators: {
                        notEmpty: {
                            message: '确认密码不能为空'
                        },
                        identical: {
                            field: 'newpassword',
                            message: '两次输入的密码不一致'
                        }
                    }
                }
            }
        });

        //TODO:用户注册数据校验
        $('#frmRegist').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: '用户姓名不能为空'
                        }
                    }
                },
                loginName: {
                    validators: {
                        notEmpty: {
                            message: '用户登录名不能为空'
                        },
                        remote: {
                            type: 'post',
                            url: '${pageContext.request.contextPath}/front/customer/checkLoginName'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 18,
                            message: '密码长度必须大于6位或小于18位'
                        }
                    }
                },
                phone: {
                    validators: {
                        notEmpty: {
                            message: '请输入手机号'
                        },
                        regexp: {
                            regexp: '^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$',
                            message: '无效的手机号码'
                        }
                    }
                },
                address: {
                    validators: {
                        notEmpty: {
                            message: '地址不能为空'
                        }
                    }
                }
            }
        });

        //TODO:模态框隐藏时候，清除校验信息
        $('#registModal').on('hide.bs.modal', function () {//监听modal消失
            //清除校验信息
            $('#frmRegist').bootstrapValidator('resetForm');
            //表单中填写的信息也清除
            $('#frmRegist')[0].reset();
        });
        $('#modifyPasswordModal').on('hide.bs.modal', function () {//监听modal消失
            //清除校验信息
            $('#frmModifyPWD').bootstrapValidator('resetForm');
            //表单中填写的信息也清除
            $('#frmModifyPWD')[0].reset();
        });
        $('#loginModal').on('hide.bs.modal', function () {//监听modal消失
            //清除校验信息
            $('#frmLoginByAccount').bootstrapValidator('resetForm');
            $('#frmSmsLogin').bootstrapValidator('resetForm');
            //表单中填写的信息也清除
            $('#frmLoginByAccount')[0].reset();
            $('#frmSmsLogin')[0].reset();
        });
        $('#registModal').on('hide.bs.modal', function () {//监听modal消失
            //清除校验信息
            $('#frmRegist').bootstrapValidator('resetForm');
            //表单中填写的信息也清除
            $('#frmRegist')[0].reset();
        });

    });

    /*根据账户名密码登录*/
    function loginByAccount() {
        $('#frmLoginByAccount').data('bootstrapValidator').validate();
        var flag = $('#frmLoginByAccount').data('bootstrapValidator').isValid();
        if (flag) {
            //校验通过发送ajax请求
            $.post(
                '${pageContext.request.contextPath}/front/customer/loginByAccount',
                /*TODO: 表单序列化直接获取值传给后端*/
                $('#frmLoginByAccount').serialize(),
                function (result) {
                    if (result.status == 1) {
                        //TODO: 这里刷新整个页面刷新页面，解决点击购物车，如果没登录时候，登录后没能及时刷新问题（登录成功购物车页面数据不能立即加载显示，以及客户的头像不能及时更新）；
                        //TODO:后续需要考虑有没有局部刷新的同时也实现页面数据的刷新
                        location.href = '${pageContext.request.contextPath}/front/product/searchAllProducts';
                        /*TODO: 使用局部刷新*/
                        // 隐藏模态框
                        $('#loginModal').modal('hide');
                        // TODO:局部刷新实现：加载模板,传入数据到模板中去渲染
                        // var content = template('welcome', result.data);
                        // $('#navbarInfo').html(content);

                    } else {
                        layer.msg(result.message, {
                            time: 1000,
                            skin: 'errorMsg'
                        });
                    }
                }
            );
        }
    }

    /*退出登录*/
    function logout() {
        $.post(
            '${pageContext.request.contextPath}/front/customer/logout',
            function (result) {
                if (result.status == 1) {
                    location.href = '${pageContext.request.contextPath}/front/product/searchAllProducts';
                }
            }
        );
    }

    /*发送验证码*/
    function sendVerifyCode(btn) {
        $.post(
            '${pageContext.request.contextPath}/front/sms/sendVerifyCode',
            {'phone': $('#phone').val()},
            function (result) {
                if (result.status == 1) {
                    //定义倒计时
                    //TODO:发送短信验证码校验，倒计时的使用场景
                    var time = 60;
                    timer = setInterval(function () {
                        if (time > 0) {
                            $(btn).attr('disabled', true);
                            $(btn).html('重新发送(' + time + ')');
                            time--;
                        } else {
                            $(btn).attr('disabled', false);
                            $(btn).html('重新发送');
                            //停止计时器
                            clearInterval(timer);
                        }
                    }, 1000);

                    //消息提示
                    layer.msg(result.message, {
                        time: 1000,
                        skin: 'successMsg'
                    });
                } else {
                    //定义倒计时
                    //TODO:测试：发送短信验证码校验，倒计时的使用场景
                    var time = 60;
                    timer = setInterval(function () {
                        if (time > 0) {
                            $(btn).attr('disabled', true);
                            $(btn).html('重新发送(' + time + ')');
                            time--;
                        } else {
                            $(btn).attr('disabled', false);
                            $(btn).html('重新发送');
                            //停止计时器
                            clearInterval(timer);
                        }
                    }, 1000);

                    //消息提示
                    layer.msg(result.message, {
                        time: 1000,
                        skin: 'errorMsg'
                    });
                }
            }
        );
    }

    /*短信验证码实现快捷登录*/
    function loginBySms() {
        $('#frmSmsLogin').data('bootstrapValidator').validate();
        var flag = $('#frmSmsLogin').data('bootstrapValidator').isValid();
        if (flag) {
            $.post(
                '${pageContext.request.contextPath}/front/customer/loginBySms',
                $('#frmSmsLogin').serialize(),
                function (result) {
                    if (result.status == 1) {
                        //TODO: 这里刷新整个页面刷新页面，解决点击购物车，如果没登录时候，登录后没能及时刷新问题（登录成功购物车页面数据不能立即加载显示，以及客户的头像不能及时更新）；
                        //TODO:后续需要考虑有没有局部刷新的同时也实现页面数据的刷新
                        location.href = '${pageContext.request.contextPath}/front/product/searchAllProducts';

                        /*TODO: 使用局部刷新*/
                        // 隐藏模态框
                        $('#loginModal').modal('hide');
                        // 加载模板,传入数据到模板中去渲染
                        // var content = template('welcome', result.data);
                        // $('#navbarInfo').html(content);

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

    /*用户注册*/
    function CustomerRegist() {

        $('#frmRegist').data('bootstrapValidator').validate();
        var valid = $('#frmRegist').data('bootstrapValidator').isValid();
        if (valid) {
            $.post(
                '${pageContext.request.contextPath}/front/customer/regist',
                $('#frmRegist').serialize(),
                function (result) {
                    if (result.status == 1) {
                        /*TODO: 使用局部刷新*/
                        // 隐藏模态框
                        $('#registModal').modal('hide');
                        // TODO: 加载模板,传入数据到模板中去渲染,这块局部刷新，头像显示会有问题
                        // var content = template('welcome', result.data);
                        // $('#navbarInfo').html(content);

                        layer.msg(result.message, {
                            time: 1000,
                            skin: 'successMsg'
                        }, function () {
                            //这里刷新更新一下头像
                            location.reload();
                        });

                    } else {
                        layer.msg(result.message, {
                            time: 1000,
                            skin: 'errorMsg'
                        });
                    }
                }
            );
        }
    }

    /*用户注册按重置按钮，清空表单数据和校验信息*/
    function resetFormDates() {
        $('#frmRegist').bootstrapValidator('resetForm');
        $('#frmRegist')[0].reset();
    }

    /*用户修改密码*/
    function modifyPassword() {
        //启动校验
        $('#frmModifyPWD').data('bootstrapValidator').validate();
        //校验结果
        var flag = $('#frmModifyPWD').data('bootstrapValidator').isValid();
        if (flag) {
            $.post(
                '${pageContext.request.contextPath}/front/customer/modifyPassword',
                $('#frmModifyPWD').serialize(),
                function (result) {
                    if (result.status == 1) {
                        console.log(result);
                        //密码修改成功，重新加载页面登录
                        layer.msg(result.message, {
                            time: 1000,
                            skin: 'successMsg'
                        }, function () {
                            location.reload();
                        });
                        // TODO:修改密码，局部刷新 隐藏模态框
                        // $('#modifyPasswordModal').modal('hide');
                        // // 加载模板,传入数据到模板中去渲染
                        // var content = template('loginOrRegist');
                        $('#navbarInfo').html(content);
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

    //未登录弹出登录框
    function showLoginModal() {
        $('#loginModal').modal('show');
    }

    /*登录则展示客户购物车*/
    function showShopCarts() {
        location.href = '${pageContext.request.contextPath}/front/cart/myCarts';
    }

    /*展示用户所有订单列表*/
    function showOrderDetails() {
        location.href = '${pageContext.request.contextPath}/front/order/myOrders';
    }

    /*展示用户个人中心*/
    function showCustomerInfo() {
        location.href = '${pageContext.request.contextPath}/front/customer/customerCenter';
    }

</script>
<!-- 导航栏 start -->
<div class="navbar navbar-default navbar-fixed-top clear-bottom">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand logo-style" target="_blank" href="https://www.xmlvhy.com">
                <img class="brand-img" src="${pageContext.request.contextPath}/images/com-logo1.png" alt="logo"
                     height="66">
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <%--todo:通过jsp include 传值的方式设置active--%>
                <li class="${param.num == 1 ? 'active' : ''}">
                    <a href="${pageContext.request.contextPath}/front/product/searchAllProducts">商城主页</a>
                </li>
                <li class="${param.num == 2 ? 'active' : ''}" style="cursor: pointer;">
                    <%--<a id="shopCart" onclick="showShopCarts()"  href="${pageContext.request.contextPath}/front/cart/myCarts">--%>
                    <a id="shopCart"
                       onclick="${sessionScope.get('customer') == null ? 'showLoginModal()' :'showShopCarts()'}">
                        <span>购物车</span>
                    </a>
                </li>
                <li class="${param.num == 3 ? 'active' : ''}">
                    <a href="javascript:void(0)"
                       onclick="${sessionScope.get('customer') == null ? 'showLoginModal()' :'showOrderDetails()'}">我的订单</a>
                </li>
                <li class="${param.num == 4 ? 'active' : ''}">
                    <a href="javascript:void(0)"
                       onclick="${sessionScope.get('customer') == null ? 'showLoginModal()' :'showCustomerInfo()'}">会员中心</a>
                </li>
                <li>
                    <div style="background-color: #efefef; margin: 20px 80px">
                        <span>助农服务—Services To Help Agriculture</span>
                    </div>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right" id="navbarInfo">
                <%--TODO: 使用c:choose 判断 session中是否存有用户的信息--%>
                <c:choose>
                    <%--TODO:如果session中用户为空则走 when 中--%>
                    <c:when test="${empty customer}">
                        <li>
                            <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
                        </li>
                        <li>
                            <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
                        </li>
                    </c:when>
                    <%--TODO:否则走 otherwise 中--%>
                    <c:otherwise>
                        <li class="userName">
                            欢迎您 : <span class="text text-success">${sessionScope.get("customer").name} !</span>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                                <img class="img-circle" src="${pageContext.request.contextPath}/images/user.png"
                                     height="30"/>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                                        <i class="glyphicon glyphicon-cog"></i>修改密码
                                    </a>
                                </li>
                                <li>
                                    <a href="#" onclick="logout()">
                                        <i class="glyphicon glyphicon-off"></i> 退出
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>
<!-- 导航栏 end -->

<!-- 修改密码模态框 start -->
<div class="modal fade" id="modifyPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="top: 10%;">
    <div class="modal-dialog" role="document" style="width: 30%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="modifyPWD">修改密码</h4>
            </div>
            <form id="frmModifyPWD" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">原密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" name="newpassword">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">重复密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" name="repassword">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        取&nbsp;&nbsp;消
                    </button>
                    <%--<button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>--%>
                    <button type="button" class="btn btn-warning" onclick="modifyPassword()">确&nbsp;&nbsp;认</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 修改密码模态框 end -->

<!-- 登录模态框 start -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="top: 10%;">
    <div class="modal-dialog" role="document" style="width: 34%">
        <!-- 用户名密码登陆 start -->
        <div class="modal-content" id="login-account">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">用户名密码登录
                    <small class="text-danger"></small>
                </h4>
            </div>
            <form id="frmLoginByAccount" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" placeholder="请输入用户名" name="loginName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" placeholder="请输入密码" name="password">
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <%--TODO:忘记密码模块没实现--%>
                    <%--<a class="btn-link">忘记密码？</a> &nbsp;--%>
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="button" class="btn btn-warning" onclick="loginByAccount()">登&nbsp;&nbsp;录</button>
                    &nbsp;&nbsp;
                    <a class="btn-link" id="btn-sms-back">短信快捷登录</a>
                </div>
            </form>
        </div>
        <!-- 用户名密码登陆 end -->

        <!-- 短信快捷登陆 start -->
        <div class="modal-content" id="login-sms" style="display: none;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">短信快捷登录</h4>
            </div>
            <form class="form-horizontal" id="frmSmsLogin">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" placeholder="请输入手机号" id="phone" name="phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">验证码：</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" id="smsCode" name="verifyCode" placeholder="请输入验证码">
                        </div>
                        <div class="col-sm-2">
                            <button class="pass-item-timer" type="button" onclick="sendVerifyCode(this)">发送验证码</button>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <%--TODO:忘记密码暂未实现--%>
                    <%--<a class="btn-link">忘记密码？</a> &nbsp;--%>
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="button" class="btn btn-warning" onclick="loginBySms()">登&nbsp;&nbsp;录</button> &nbsp;&nbsp;
                    <a class="btn-link" id="btn-account-back">用户名密码登录</a>
                </div>
            </form>
        </div>
        <!-- 短信快捷登陆 end -->

    </div>
</div>
<!-- 登录模态框 end -->

<!-- 注册模态框 start -->
<div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="top: 10%;">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 80%">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">会员注册</h4>
            </div>
            <form id="frmRegist" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户姓名:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录账号:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="loginName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录密码:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系电话:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系地址:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="address">
                        </div>
                    </div>
                </div>
                <div class="modal-footer" align="center">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">
                        关&nbsp;&nbsp;闭
                    </button>
                    <button type="button" class="btn btn-warning" onclick="resetFormDates()">重&nbsp;&nbsp;置</button>
                    <button type="button" class="btn btn-warning" onclick="CustomerRegist()">注&nbsp;&nbsp;册</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 注册模态框 end -->