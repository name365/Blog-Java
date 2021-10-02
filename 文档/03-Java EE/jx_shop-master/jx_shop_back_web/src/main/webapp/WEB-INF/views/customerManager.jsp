<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            //分页插件
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion: 3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
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
                <%--return '${pageContext.request.contextPath}/admin/customer/getAllCustomers?pageNum=' + page;--%>
                <%--}--%>
                <%--TODO:此设置是在当前页面查找--%>
                onPageClicked: function (event, orignalEvent, type, page) {
                    $('#pageNum').val(page);
                    $('#frmSearch').submit();
                }
            });

            //显示服务端传过来的消息
            var successMsg = '${successMsg}';
            var failMsg = '${failMsg}';

            if (successMsg != '') {
                layer.msg(successMsg,{
                   time: 1000,
                    //TODO:设置他弹出层的宽度，高度自适应
                    area: '100px',
                   skin: 'successMsg'
                });
            }

            if (failMsg != '') {
                layer.msg(failMsg,{
                    time: 1500,
                    //TODO:设置他弹出层的宽度，高度自适应
                    area: '100px',
                    skin: 'errorMsg'
                });
            }

            //修改客户信息校验
            $('#frmModifyCustomer').bootstrapValidator({
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
                    phone: {
                        validators: {
                            notEmpty: {
                                message: "用户手机号不能为空"
                            },
                            regexp: {
                                regexp: '^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0-9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$',
                                message: '无效的手机号码'
                            }
                        }
                    },
                    address: {
                        validators: {
                            notEmpty: {
                                message: '用户地址不能为空'
                            }
                        }
                    }
                }
            });

            //TODO:检测修改用户信息的modal 框隐藏的时候，将校验的数据清空
            $('#myModal').on('hide.bs.modal',function () {
                //清空校验数据
                $('#frmModifyCustomer').bootstrapValidator('resetForm');
            });
        });

        //展示客户信息
        function showCustomer(id) {
            $.post(
                '${pageContext.request.contextPath}/admin/customer/showCustomer',
                {'id': id},
                function (result) {
                    if (result.status == 1) {
                        $('#id').val(result.data.id);
                        $('#name').val(result.data.name);
                        $('#loginName').val(result.data.loginName);
                        $('#phone').val(result.data.phone);
                        $('#my-addrees').val(result.data.address);
                    }
                }
            );
        }

        //修改客户状态
        function modifyCustomerStatus(id,btn){
            $.post(
                '${pageContext.request.contextPath}/admin/customer/modifyCustomerStatus',
                {'id' : id},
                function (result) {
                    if (result.status == 1) {
                        layer.msg(result.message,{
                            time: 700,
                            skin: 'successMsg'
                        },function () {
                            var $td = $(btn).parent().prev();
                            if ($td.text().trim() == '有效') {
                                //修改表格中td的值显示
                                $td.text('无效');
                                //修改按钮的状态
                                $(btn).val('启用').removeClass('btn-danger').addClass('btn-success');
                            }else{
                                //修改表格中td的值显示
                                $td.text('有效');
                                $(btn).val('禁用').removeClass('btn-success').addClass('btn-danger');
                            }
                        });
                    }else{
                        layer.msg(result.message,{
                            time: 700,
                            skin: 'errorMsg'
                        });
                    }
                }
            );
        }

    </script>

</head>

<body>
<div class="panel panel-default" id="userInfo" id="homeSet">
    <div class="panel-heading">
        <h3 class="panel-title">客户管理</h3>
    </div>
    <div class="panel-body">
        <div class="showusersearch">
            <form class="form-inline" action="${pageContext.request.contextPath}/admin/customer/getAllCustomersByParams"
                  method="post" id="frmSearch">
                <div class="form-group">
                    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}" id="pageNum">
                    <label for="customer_name">姓名:</label>
                    <input type="text" class="form-control" id="customer_name" name="name" placeholder="请输入姓名"
                           value="${params.name}" size="15px">
                </div>
                <div class="form-group">
                    <label for="customer_loginName">帐号:</label>
                    <input type="text" class="form-control" id="customer_loginName" name="loginName" placeholder="请输入帐号"
                           value="${params.loginName}" size="15px">
                </div>
                <div class="form-group">
                    <label for="customer_phone">电话:</label>
                    <input type="text" class="form-control" id="customer_phone" name="phone" placeholder="请输入电话"
                           value="${params.phone}" size="15px">
                </div>
                <div class="form-group">
                    <label for="customer_address">地址:</label>
                    <input type="text" class="form-control" id="customer_address" name="address" placeholder="请输入地址"
                           value="${params.address}">
                </div>
                <div class="form-group">
                    <label for="customer_isValid">状态:</label>
                    <select class="form-control" id="customer_isValid" name="isValid">
                        <option value="-1">全部</option>
                        <option value="1" <c:if test="${params.isValid == 1}">selected</c:if>>---有效---</option>
                        <option value="0" <c:if test="${params.isValid == 0}">selected</c:if>>---无效---</option>
                    </select>
                </div>
                <input type="submit" value="查询" class="btn btn-primary" id="doSearch">
            </form>
        </div>

        <%--表头--%>
        <div class="show-list text-center" style="position: relative;top: 30px;">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">帐号</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">地址</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="customer">
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.name}</td>
                        <td>${customer.loginName}</td>
                        <td>${customer.phone}</td>
                        <td>${customer.address}</td>
                        <td>
                            <c:if test="${customer.isValid == 1}">有效</c:if>
                            <c:if test="${customer.isValid == 0}">无效</c:if>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doModify"
                                   onclick="showCustomer(${customer.id})" value="修改">
                            <c:if test="${customer.isValid == 0}">
                                <input type="button" class="btn btn-success btn-sm doDisable" onclick="modifyCustomerStatus(${customer.id},this)" value="启用">
                            </c:if>
                            <c:if test="${customer.isValid == 1}">
                                <input type="button" class="btn btn-danger btn-sm doDisable" onclick="modifyCustomerStatus(${customer.id},this)" value="禁用">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <ul id="pagination"></ul>
        </div>
    </div>
</div>

<!-- 修改客户信息 start -->
<div class="modal fade" tabindex="-1" id="myModal">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <form action="${pageContext.request.contextPath}/admin/customer/modifyCustomer" method="post" id="frmModifyCustomer">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改客户</h4>
                </div>
                <div class="modal-body text-center">
                    <div class="row text-right">
                        <%--TODO:当前页面放到隐藏域中--%>
                        <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                        <label for="id" class="col-sm-4 control-label">编号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" readonly id="id" name="id">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right form-group">
                        <label for="name" class="col-sm-4 control-label">姓名：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="loginName" class="col-sm-4 control-label">帐号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" readonly id="loginName" name="loginName">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right form-group">
                        <label for="phone" class="col-sm-4 control-label">电话：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="phone" name="phone">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right form-group">
                        <label for="my-addrees" class="col-sm-4 control-label">地址：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="my-addrees" name="address">
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary updateOne">确认</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 修改客户信息 end -->
</body>

</html>
