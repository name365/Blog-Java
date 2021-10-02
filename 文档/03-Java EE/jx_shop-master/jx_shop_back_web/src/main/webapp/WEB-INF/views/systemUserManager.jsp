<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>backend</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/file.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css"/>

    <script>
        $(function () {

            /*分页插件的初始化*/
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion: 3,
                currentPage: ${pageInfo.pageNum},
                totalPages: ${pageInfo.pages},
                numberOfPages:${pageInfo.pageSize},
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case 'first':
                            return '首页';
                        case 'prev':
                            return '上一页';
                        case 'next':
                            return '下一页';
                        case 'last':
                            return '末页';
                        case 'page':
                            return page;
                    }
                },
                <%--pageUrl: function (type, page, current) {--%>
                <%--return '${pageContext.request.contextPath}/admin/system_user/manager/getAllSystemUsers?pageNum='+ page;--%>
                <%--}--%>
                <%--TODO:此设置是在当前页面查找--%>
                onPageClicked: function (event, orignalEvent, type, page) {
                    $('#pageNum').val(page);
                    $('#frmSearch').submit();
                }
            });


            /*TODO:1.添加系统用户信息表单校验（ajax+bootstrapValidator）*/
            $('#frmAddSystemUser').bootstrapValidator({
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
                                message: '登录名不能为空'
                            },
                            remote: {
                                type: 'post',
                                url: '${pageContext.request.contextPath}/admin/system_user/manager/checkSystemUserLoginName'
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
                    phone: {
                        validators: {
                            notEmpty: {
                                message: '电话号码不能为空'
                            },
                            regexp: {
                                regexp: '^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$',
                                message: '无效的手机号码'
                            }
                        }
                    },
                    email: {
                        validators: {
                            notEmpty: {
                                message: '邮件不能为空'
                            },
                            regexp: {
                                regexp: '^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$',
                                message: '无效的邮箱地址'
                            }
                        }
                    },
                    roleId: {
                        validators: {
                            notEmpty: {
                                message: '请选择一种用户角色'
                            }
                        }
                    }
                }
            });

            //修改用户信息表单校验
            $('#frmModifySystemUser').bootstrapValidator({
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
                    password: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            }
                        }
                    },
                    phone: {
                        validators: {
                            notEmpty: {
                                message: '电话号码不能为空'
                            },
                            regexp: {
                                regexp: '^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$',
                                message: '无效的手机号码'
                            }
                        }
                    },
                    email: {
                        validators: {
                            notEmpty: {
                                message: '邮件不能为空'
                            },
                            emailAddress: {
                                message: '无效的邮箱地址'
                            }
                        }
                    },
                    roleId: {
                        validators: {
                            notEmpty: {
                                message: '请选择一种用户角色'
                            }
                        }
                    }
                }
            });

            //显示服务端的提示消息
            var successMsg = '${successMsg}';
            var failMsg = '${failMsg}';
            //成功保存商品信息提示
            if (successMsg != '') {
                layer.msg(successMsg, {
                    time: 1000,
                    area: '100px',
                    skin: 'successMsg'
                });
            }
            //保存商品信息失败提示
            if (failMsg != '') {
                layer.msg(failMsg, {
                    time: 1500,
                    area: '100px',
                    skin: 'errorMsg'
                });
            }

            //清除表单校验信息
            $('#myMangerUser').on('hide.bs.modal',function () {
               $('#frmAddSystemUser').bootstrapValidator('resetForm');
               $('#frmAddSystemUser')[0].reset();
            });
            $('#myModal-Manger').on('hide.bs.modal',function () {
               $('#frmModifySystemUser').bootstrapValidator('resetForm');
               $('#frmModifySystemUser')[0].reset();
            });

        });

        <%--添加系统用户--%>
        <%--TODO:这里不使用表单提交，使用ajax请求 + 表单验证 --%>
        function addSystemUser() {
            /*TODO:2.启用验证和判断验证是否通过*/
            $('#frmAddSystemUser').data('bootstrapValidator').validate();//启用验证
            var flag = $('#frmAddSystemUser').data('bootstrapValidator').isValid()//验证是否通过true/false
            if (flag) {
                //表单验证通过，发出请求
                $.post(
                    '${pageContext.request.contextPath}/admin/system_user/manager/addSystemUser',
                    /*这里可以通过id一个个的取元素的值，也可以通过表单serialize()方法获取表单所有的值 */
                    $('#frmAddSystemUser').serialize(),
                    function (result) {
                        if (result.status == 1) {
                            //成功
                            layer.msg(result.message, {
                                time: 700,
                                skin: 'successMsg'
                            }, function () {
                                location.href = '${pageContext.request.contextPath}/admin/system_user/manager/getAllSystemUsers?pageNum' +
                                ${pageInfo.pageNum}
                            })
                        } else {
                            layer.msg(result.message, {
                                time: 700,
                                skin: 'errorMsg'
                            })
                        }
                    }
                );
            }
        }

        /*修改用户的状态*/
        function modifyStatus(id, btn) {
            $.post(
                '${pageContext.request.contextPath}/admin/system_user/manager/modifySystemUserStatus',
                {'id': id},
                function (result) {
                    var _btn = $(btn);
                    if (result.status == 1) {
                        layer.msg(result.message, {
                            time: 700,
                            skin: "successMsg"
                        }, function () {
                            var $td = $(_btn).parent().parent().children().eq(5);
                            if ($td.text().trim() == '有效') {
                                $td.text('无效');
                                $(_btn).val('启用').removeClass('btn-danger').addClass('btn-success');
                            } else {
                                $td.text('有效');
                                $(_btn).val('禁用').removeClass('btn-success').addClass('btn-danger');
                            }
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

        /*点击修改，显示系统用户信息*/
        function showSystemUser(id) {
            $.post(
                '${pageContext.request.contextPath}/admin/system_user/manager/findSystemUser',
                {'id': id},
                function (result) {
                    console.log(result);
                    if (result.status == 1) {
                        $('#MargerStaffId').val(result.data.id);
                        $('#MargerUsername').val(result.data.name);
                        $('#MargerLoginName').val(result.data.loginName);
                        $('#MargerAdrees').val(result.data.email);
                        $('#MargerPhone').val(result.data.phone);
                        $('#MargerRole').val(result.data.role.id);
                    }
                }
            );
        }
    </script>
</head>

<body>
<!-- 系统用户管理 -->
<div class="panel panel-default" id="adminSet">
    <div class="panel-heading">
        <h3 class="panel-title">系统用户管理</h3>
    </div>
    <div class="panel-body">
        <div class="showmargersearch">
            <form action="${pageContext.request.contextPath}/admin/system_user/manager/findSystemUserByParams"
                  class="form-inline" method="post" id="frmSearch">
                <div class="form-group">
                    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}" id="pageNum">
                    <label for="userName">姓名:</label>
                    <input type="text" class="form-control" id="userName" name="name" placeholder="请输入姓名"
                           value="${params.name}">
                </div>
                <div class="form-group">
                    <label for="loginName">帐号:</label>
                    <input type="text" class="form-control" id="loginName" name="loginName" placeholder="请输入帐号"
                           value="${params.loginName}">
                </div>
                <div class="form-group">
                    <label for="phone">电话:</label>
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入电话"
                           value="${params.phone}">
                </div>
                <div class="form-group">
                    <label for="role">角色</label>
                    <select class="form-control" id="role" name="roleId">
                        <option value="-1">全部</option>
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}"
                                    <c:if test="${role.id == params.roleId}">selected</c:if> >${role.roleName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="status">状态</label>
                    <select class="form-control" name="isValid" id="status">
                        <option value="-1">全部</option>
                        <option value="1" <c:if test="${params.isValid == 1}">selected</c:if>>---有效---</option>
                        <option value="0" <c:if test="${params.isValid == 0}">selected</c:if>>---无效---</option>
                    </select>
                </div>
                <input type="submit" value="查询" class="btn btn-primary" id="doSearch">
            </form>
        </div>
        <br>
        <%--添加系统用户 start--%>
        <input type="button" value="添加系统用户" class="btn btn-primary" id="doAddManger">
        <div class="show-list text-center" style="position: relative; top: 10px;">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">帐号</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">邮箱</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">注册时间</th>
                    <th class="text-center">角色</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="systemUser">
                    <tr>
                        <td>${systemUser.id}</td>
                        <td>${systemUser.name}</td>
                        <td>${systemUser.loginName}</td>
                        <td>${systemUser.phone}</td>
                        <td>${systemUser.email}</td>
                        <td>
                            <c:if test="${systemUser.isValid== 1}">有效</c:if>
                            <c:if test="${systemUser.isValid== 0}">无效</c:if>
                        </td>
                        <td>
                            <fmt:formatDate value="${systemUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>${systemUser.role.roleName}</td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doMangerModify" value="修改"
                                   onclick="showSystemUser(${systemUser.id})">
                            <c:if test="${systemUser.isValid == 1}">
                                <input type="button" class="btn btn-danger btn-sm doMangerDisable" value="禁用"
                                       onclick="modifyStatus(${systemUser.id},this)">
                            </c:if>

                            <c:if test="${systemUser.isValid == 0}">
                                <input type="button" class="btn btn-success btn-sm doMangerDisable" value="启用"
                                       onclick="modifyStatus(${systemUser.id},this)">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul id="pagination"></ul>
        </div>
        <%--添加系统用户 end--%>
    </div>
</div>

<!-- 添加系统用户 start -->
<div class="modal fade" tabindex="-1" id="myMangerUser">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <%--<form id="frmAddSystemUser" method="post" action="${pageContext.request.contextPath}/admin/system_user/manager/addSystemUser">--%>
        <form id="frmAddSystemUser">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加系统用户</h4>
                </div>
                <div class="modal-body">
                    <div class="row text-right">
                        <div class="form-group">
                            <label for="marger-username" class="col-sm-4 control-label">用户姓名：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="marger-username" name="name">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <div class="form-group">
                            <label for="marger-loginName" class="col-sm-4 control-label">登录帐号：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="marger-loginName" name="loginName">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <div class="form-group">
                            <label for="marger-password" class="col-sm-4 control-label">登录密码：</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" id="marger-password" name="password">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <div class="form-group">
                            <label for="marger-phone" class="col-sm-4 control-label">联系电话：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="marger-phone" name="phone">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <div class="form-group">
                            <label for="marger-email" class="col-sm-4 control-label">联系邮箱：</label>
                            <div class="col-sm-4">
                                <input type="email" class="form-control" id="marger-email" name="email">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <div class="form-group">
                            <label for="role"
                                   class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                            <div class=" col-sm-4">
                                <select class="form-control" name="roleId">
                                    <option value="">--请选择--</option>
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role.id}">${role.roleName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <%--通过ajax 请求方式提交表单--%>
                    <button class="btn btn-primary" type="button" onclick="addSystemUser()">添加</button>
                        <%--通过表单提交的方式 submit 按钮提交--%>
                    <%--<button class="btn btn-primary" type="submit">添加</button>--%>
                    <button class="btn btn-primary cancel" data-dismiss="modal" type="button">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 添加系统用户 end -->

<!-- 修改系统用户 start -->
<div class="modal fade" tabindex="-1" id="myModal-Manger">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <form action="${pageContext.request.contextPath}/admin/system_user/manager/modifySystemUser" method="post"
              id="frmModifySystemUser">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">系统用户修改</h4>
                </div>

                <div class="modal-body text-center">
                    <div class="row text-right">
                        <%--TODO：隐藏域存放 用户的密码 password 和当前页码 pageNum--%>
                        <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                        <label for="MargerStaffId" class="col-sm-4 control-label">用户编号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="MargerStaffId" readonly="readonly" name="id">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <div class="form-group">
                            <label for="MargerUsername" class="col-sm-4 control-label">用户姓名：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="MargerUsername" name="name">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="MargerLoginName" class="col-sm-4 control-label">登录帐号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="MargerLoginName" readonly="readonly"
                                   name="loginName">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <div class="form-group">
                            <label for="MargerPhone" class="col-sm-4 control-label">联系电话：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="MargerPhone" name="phone">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <div class="form-group">
                            <label for="MargerAdrees" class="col-sm-4 control-label">联系邮箱：</label>
                            <div class="col-sm-4">
                                <input type="email" class="form-control" id="MargerAdrees" name="email">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <div class="form-group">
                            <label for="MargerRole" class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                            <div class=" col-sm-4">
                                <select class="form-control" id="MargerRole" name="roleId">
                                    <option value="">--请选择--</option>
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role.id}">${role.roleName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">确定</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 修改系统用户 end -->

</body>

</html>
