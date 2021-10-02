<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>助农服务-后台管理系统</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css"/>

    <script>

        $(function () {
             /*登录表单校验*/
            $('#frmLogin').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
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
                    },
                    randCode: {
                        validators: {
                            notEmpty: {
                                message: '请输入验证码'
                            },
                            remote: {
                                type: 'post',
                                url: '${pageContext.request.contextPath}/admin/code/checkRandCode',
                                message: '验证码不正确'
                            }
                        }
                    }
                }
            });

            /*显示服务器端的消息*/
            var failMsg = '${failMsg}';
            if (failMsg != '') {
                layer.msg(failMsg,{
                    time: 1500,
                    area: '100px',
                    skin: 'errorMsg'
                });
            }
        });

        /*点击看不清，刷新验证码*/
         function reloadCodeImage() {
            var timestamp = new Date().valueOf();
            $('#randCode').attr('src','${pageContext.request.contextPath}/admin/code/getCodeImage?time='
                + timestamp);
            $('#code').val('');
         }

         //TODO:点击重置按钮,清除校验的内容以及表单中填写的内容
        /*点击重置按钮也要重新刷新验证码*/
        function reloadCodeImage1() {
            $("#frmLogin").bootstrapValidator('resetForm'); //清除当前验证
            $("#frmLogin")[0].reset(); //将表单中的数据也清空
        }
    </script>

</head>
<body>
<!-- 使用自定义css样式 div-signin 完成元素居中-->
<div class="container div-signin" style="margin-top: 99px">
    <div class="panel panel-primary div-shadow">
        <!-- h3标签加载自定义样式，完成文字居中和上下间距调整 -->
        <div class="panel-heading">
            <h3>助农服务Services To Help Agriculture v1.0</h3>
            <span>STHA Manager System</span>
        </div>
        <div class="panel-body">
            <!-- login form start -->
            <form action="${pageContext.request.contextPath}/admin/system_user/manager/login" class="form-horizontal" method="post" id="frmLogin">
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户名：</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="text" placeholder="请输入用户名" id="loginName" name="loginName">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                    <div class="col-sm-9">
                        <input class="form-control" type="password" placeholder="请输入密码" id="password" name="password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">验证码：</label>
                    <div class="col-sm-4">
                        <input class="form-control" type="text" id="code" name="randCode" placeholder="验证码">
                    </div>
                    <div class="col-sm-2">
                        <!-- 验证码 -->
                        <img class="img-rounded" id="randCode" src="${pageContext.request.contextPath}/admin/code/getCodeImage" style="height: 32px; width: 70px;"/>
                    </div>
                    <div class="col-sm-2">
                        <%--<button type="button" class="btn btn-link" onclick="reloadCodeImage()">看不清</button>--%>
                        <a href="#" class="btn btn-link" onclick="reloadCodeImage()">看不清</a>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-3">
                    </div>
                    <div class="col-sm-9 padding-left-0">
                        <div class="col-sm-4">
                            <button type="submit" class="btn btn-primary btn-block">登&nbsp;&nbsp;陆</button>
                        </div>
                        <div class="col-sm-4">
                            <button type="button" class="btn btn-primary btn-block" onclick="reloadCodeImage1()">重&nbsp;&nbsp;置</button>
                        </div>
                        <div class="col-sm-4">
                            <%--<button type="button" class="btn btn-link btn-block">忘记密码？</button>--%>
                            <a href="#" class="btn btn-link btn-block">忘记密码？</a>
                        </div>
                    </div>
                </div>
            </form>
            <!-- login form end -->
        </div>
    </div>
</div>

<!-- footers start -->
<footer id="footer" class="footer navbar-fixed-bottom">
    <div class="container-fluid footers text-center">
        Copy Right @ 2020 Services To Help Agriculture
    </div>
</footer>
<!-- footers end -->
</body>
</html>
