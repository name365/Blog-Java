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
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zshop.css"/>

    <script>
        /*页面加载完*/
        $(function () {
            <%--初始化分页插件--%>
            //TODO: 了解如何使用 paginator 分页插件
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion: 3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                numberOfPages:${pageInfo.pageSize},
                pageUrl: function (type, page, current) {
                    return '${pageContext.request.contextPath}/admin/product_type/find_all?pageNum=' + page;
                },
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
                }
            });

            /*添加商品类型的表单校验*/
            $('#frmAddProductType').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    productTypeName: {
                        validators: {
                            notEmpty: {
                                message: '商品类型不能为空'
                            }
                        }
                    }
                }
            });

            /*修改商品类型的表单校验*/
            $('#frmModifyProductType').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    proTypeName: {
                        validators: {
                            notEmpty: {
                                message: '商品类型不能为空'
                            }
                        }
                    }
                }
            });

            //清除表单校验
            $('#ProductType').on('hide.bs.modal', function () {
                $('#frmAddProductType').bootstrapValidator('resetForm');
                $('#frmAddProductType')[0].reset();
            });

            $('#myProductType').on('hide.bs.modal', function () {
                $('#frmModifyProductType').bootstrapValidator('resetForm');
                $('#frmModifyProductType')[0].reset();
            })
        });

        //添加商品类型
        function addProductType() {
            //TODO: 了解 ajax 请求方式
            $('#frmAddProductType').data('bootstrapValidator').validate();//启用验证
            var flag = $('#frmAddProductType').data('bootstrapValidator').isValid()//验证是否通过true/false
            if (flag) {
                //通过校验后发送请求
                $.post( //ajax发请求
                    '${pageContext.request.contextPath}/admin/product_type/add',  //参数1：请求的url
                    {'name': $('#productTypeName').val()}, //参数2：传递的数据
                    function (result) {   //参数3：回调函数,result为服务器返回的结果
                        // console.log(result.message);
                        if (result.status == 1) {
                            //成功
                            //TODO:了解一下 layer 插件弹框的使用
                            layer.msg(result.message, {
                                time: 1500,          //弹出框消息的时间
                                skin: 'successMsg'   //自定义的样式
                            }, function () {
                                //添加商品成功，刷新数据
                                location.href = '${pageContext.request.contextPath}/admin/product_type/find_all?pageNum=' +
                                ${pageInfo.pageNum}
                            });
                        } else {
                            //失败
                            layer.msg(result.message, {
                                time: 1500,        //弹出框消息的时间
                                skin: 'errorMsg'  //自定义的样式
                            });
                        }
                    }
                );
            }
        };

        //显示商品类型
        function showProductType(id) {
            $.get(
                '${pageContext.request.contextPath}/admin/product_type/findProductTypeById',
                {"id": id},
                function (result) {
                    if (result.status == 1) {
                        $('#proTypeNum').val(result.data.id);
                        $('#proTypeName').val(result.data.name);
                    }
                }
            );
        }

        //修改商品类型名称
        function modifyProductTypeName() {
            $('#frmModifyProductType').data('bootstrapValidator').validate();//启用验证
            var flag = $('#frmModifyProductType').data('bootstrapValidator').isValid()//验证是否通过true/false
            if (flag) {

                //ajax 请求
                //TODO: 了解 ajax 请求方式
                $.ajax({
                    type: 'post', //请求方式
                    url: '${pageContext.request.contextPath}/admin/product_type/modifyProductTypeName', //请求的url
                    data: {'id': $('#proTypeNum').val(), 'name': $('#proTypeName').val()},  //请求的参数数据
                    dataType: 'json',    //响应的数据类型
                    success: function (result) {     //请求回调方法
                        console.log(result);
                        if (result.status == 1) {
                            layer.msg(result.message, {
                                time: 1500,
                                skin: 'successMsg'
                            }, function () { //回调方法,当弹出框消失后执行
                                //刷新页面重新加载数据，重新查找商品类型类表
                                location.href = '${pageContext.request.contextPath}/admin/product_type/find_all?pageNum=' +${pageInfo.pageNum};
                            });
                        } else {
                            layer.msg(result.message, {
                                time: 1500,
                                skin: 'errorMsg'
                            });
                        }

                    }
                });
            }
        }

        //显示确认删除的提示框
        function showDeleteModal(id) {
            $('#deleteProductTypeId').val(id);
            $('#deleteProductTypeModal').modal('show');
        }

        //删除商品类型
        function deleteProductType() {
            $.get(
                '${pageContext.request.contextPath}/admin/product_type/removeProductType',
                {'id': $('#deleteProductTypeId').val()},
                function (result) {
                    if (result.status == 1) {
                        layer.msg(result.message, {
                            time: 1500,
                            skin: 'successMsg'
                        }, function () {
                            //删除成功，刷新页面重新加载数据
                            if (${pageInfo.size > 1}) {
                                console.log('${pageInfo}')
                                //如果删除该项，当前页面还有条目则刷新当前页面的数据
                                location.href = '${pageContext.request.contextPath}/admin/product_type/find_all?pageNum=' +${pageInfo.pageNum};
                            } else {
                                //如果删除该项，当前页面没有条目则加载默认第一页的数据
                                location.href = '${pageContext.request.contextPath}/admin/product_type/find_all';
                            }
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

        //修改商品类型的状态,方式一：刷新页面重新加载数据
        <%--function modifyStatus(id) {--%>
        <%--$.post(--%>
        <%--'${pageContext.request.contextPath}/admin/product_type/modifyProductTypeStatus',--%>
        <%--{'id': id},--%>
        <%--function (result) {--%>
        <%--if (result.status == 1) {--%>
        <%--layer.msg(result.message,{--%>
        <%--time: 1500,--%>
        <%--skin: 'successMsg'--%>
        <%--},function () {--%>
        <%--//删除成功，刷新页面重新加载数据--%>
        <%--location.href = '${pageContext.request.contextPath}/admin/product_type/find_all?pageNum='+${pageInfo.pageNum};--%>
        <%--});--%>
        <%--}else {--%>
        <%--layer.msg(result.message,{--%>
        <%--time: 1500,--%>
        <%--skin: 'errorMsg'--%>
        <%--});--%>
        <%--}--%>
        <%--}--%>
        <%--);--%>
        <%--}--%>

        //修改商品类型的状态,方式二：使用ajax实现局部刷新
        function modifyStatus(id, btn) {
            $.post(
                '${pageContext.request.contextPath}/admin/product_type/modifyProductTypeStatus',
                {'id': id},
                function (result) {
                    if (result.status == 1) {
                        layer.msg(result.message, {
                            time: 500,
                            skin: 'successMsg'
                        }, function () {
                            //删除成功，刷新页面重新加载数据
                            //由于只是需要刷新状态，不需要去刷新整个页面，那样不友好。
                            //TODO:使用ajax实现局部刷新页面
                            //通过jQuery dom，操作找到 该控件的前一个的前一个的 td
                            var $td = $(btn).parent().prev();
                            if ($td.text().trim() == '启用') {
                                $td.text('禁用');
                                $(btn).val('启用').removeClass('btn-danger').addClass('btn-success');
                            } else {
                                $td.text('启用');
                                $(btn).val('禁用').removeClass('btn-success').addClass('btn-danger');
                            }
                        });
                    } else {
                        layer.msg(result.message, {
                            time: 500,
                            skin: 'errorMsg'
                        });
                    }
                }
            );
        }
    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">商品类型管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加商品类型" class="btn btn-primary" id="doAddProTpye">
        <br>
        <br>
        <div class="show-list text-center">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">类型名称</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="productType">
                    <tr>
                        <td>${productType.id}</td>
                        <td>${productType.name}</td>
                        <td>
                            <c:if test="${productType.status == 1}">启用</c:if>
                            <c:if test="${productType.status == 0}">禁用</c:if>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doProTypeModify"
                                   onclick="showProductType(${productType.id})" value="修改">
                            <input type="button" class="btn btn-danger btn-sm doProTypeDelete"
                                   onclick="showDeleteModal(${productType.id})" value="删除">
                            <c:if test="${productType.status == 1}">
                                <input type="button" class="btn btn-danger btn-sm doProTypeDisable" value="禁用"
                                       onclick="modifyStatus(${productType.id},this)">
                            </c:if>
                            <c:if test="${productType.status == 0}">
                                <input type="button" class="btn btn-success btn-sm doProTypeDisable" value="启用"
                                       onclick="modifyStatus(${productType.id},this)">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--使用bootstrap-paginator分页插件--%>
            <ul id="pagination"></ul>
        </div>
    </div>
</div>

<!-- 添加商品类型 start -->
<div class="modal fade" tabindex="-1" id="ProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <form id="frmAddProductType">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加商品类型</h4>
                </div>
                <div class="modal-body text-center">
                    <div class="form-group">
                        <div class="row text-right">
                            <label for="productTypeName" class="col-sm-4 control-label">类型名称：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="productTypeName" name="productTypeName">
                            </div>
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="addProductType()">添加</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 添加商品类型 end -->

<!-- 修改商品类型 start -->
<div class="modal fade" tabindex="-1" id="myProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <form id="frmModifyProductType">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改商品类型</h4>
                </div>
                <div class="modal-body text-center">
                    <div class="row text-right">
                        <label for="proTypeNum" class="col-sm-4 control-label">编号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="proTypeNum" readonly>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <div class="row text-right">
                            <label for="proTypeName" class="col-sm-4 control-label">类型名称</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="proTypeName" name="proTypeName">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" onclick="modifyProductTypeName()">确认</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 修改商品类型 end -->

<!-- 确认删除 start -->
<div class="modal fade" tabindex="-1" id="deleteProductTypeModal">
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
                <h5 class="text-warning">确认删除该商品类型吗？</h5>
            </div>
            <div class="modal-footer">
                <%--设置一个隐藏域来存放所要删除商品的id--%>
                <input type="hidden" id="deleteProductTypeId">
                <button class="btn btn-primary updateProType" onclick="deleteProductType()" data-dismiss="modal">确认
                </button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 确认删除 end -->
</body>

</html>
