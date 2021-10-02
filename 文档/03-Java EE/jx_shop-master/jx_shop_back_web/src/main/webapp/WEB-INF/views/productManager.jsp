<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
            //上传图像预览
            $('#product-image').on('change', function () {
                $('#img').attr('src', window.URL.createObjectURL(this.files[0]));
            });
            $('#pro-image').on('change', function () {
                $('#img2').attr('src', window.URL.createObjectURL(this.files[0]));
            });

            //显示服务端的提示消息
            var successMsg = '${successMsg}';
            var failMsg = '${failMsg}';
            var errorMsg = '${errorMsg}';
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
            //文件上传失败提示
            if (errorMsg != '') {
                layer.msg(errorMsg, {
                    time: 1500,
                    area: '100px',
                    skin: 'errorMsg'
                });
            }

            //TODO:使用BootstrapValidator插件进行客户端数据校验
            $('#addProductForm').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: '商品名称不能为空'
                            },
                            //TODO:bootstrapValidator 校验 插件 remote 校验会自己读取响应的 valid 的值 和message的
                            remote: {   //校测名称是否存在
                                type: 'post',   //注意，请求方式一定要指定，默认为put,当参数传中文的时候会出现乱码
                                url: '${pageContext.request.contextPath}/admin/product/checkProductName'
                            }
                        }
                    },
                    price: {
                        validators: {
                            notEmpty: {
                                message: '商品价格不能为空'
                            },
                            regexp: {
                                //商品价格校验的正则表达式
                                regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                                message: '商品价格不正确'
                            }
                        }
                    },
                    file: {
                        validators: {
                            notEmpty: {
                                message: '请选择商品的图片'
                            }
                        }
                    },
                    info:{
                        validators: {
                            notEmpty: {
                                message: '商品简介信息不能为空'
                            },
                            stringLength: {
                                min: 15,
                                message: '建议字数不少于15字数'
                            }
                        }
                    },
                    productTypeId: {
                        validators: {
                            notEmpty: {
                                message: '请选择一种商品类型'
                            }
                        }
                    }
                }
            });

            $('#modifyProductForm').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    price: {
                        validators: {
                            notEmpty: {
                                message: '商品价格不能为空'
                            },
                            regexp: {
                                //商品价格校验的正则表达式
                                regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                                message: '商品价格不正确'
                            }
                        }
                    },
                    file: {
                        validators: {
                            notEmpty: {
                                message: '请选择商品的图片'
                            }
                        }
                    },
                    info:{
                        validators: {
                            notEmpty: {
                                message: '商品简介信息不能为空'
                            },
                            stringLength: {
                                min: 15,
                                message: '建议字数不少于15字数'
                            }
                        }
                    },
                    productTypeId: {
                        validators: {
                            notEmpty: {
                                message: '请选择一种商品类型'
                            }
                        }
                    }
                }
            });

            //清除校验信息
            $('#Product').on('hide.bs.modal',function () {
                $('#addProductForm')[0].reset();
                $('#addProductForm').bootstrapValidator('resetForm');
            });

            $('#myProduct').on('hide.bs.modal',function () {
                $('#modifyProductForm').bootstrapValidator('resetForm');
            })

            //todo:/*分页*/
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
                pageUrl: function (type, page, current) {
                    return '${pageContext.request.contextPath}/admin/product/findAllProduct?pageNum=' + page;
                }
            });

            //控制添加商品的 textarea 的字数
            $('#product-info').on('input propertychange',function () {
                var $this = $(this),
                    _val = $this.val(),
                    count = "";
                if (_val.length > 30) {
                    $this.val(_val.substring(0, 30));
                }
                count = 30 - $this.val().length;
                $("#add-count").text(count);
            });

            //控制修改商品的 textarea 的字数
            $('#info').on('input propertychange',function () {
                var $this = $(this),
                    _val = $this.val(),
                    count = "";
                if (_val.length > 30) {
                    //超过的字数截取掉
                    $this.val(_val.substring(0, 30));
                }
                //计算剩余的字数
                count = 30 - $this.val().length;
                //显示剩余的字数
                $("#modify-count").text(count);
            });
        });

        //显示商品信息
        function showProduct(id) {
            $.post(
                '${pageContext.request.contextPath}/admin/product/findProductById',
                {'id': id},
                function (result) {
                    if (result.status == 1) {
                        $('#pro-num').val(result.data.id);
                        $('#pro-name').val(result.data.name);
                        $('#pro-price').val(result.data.price);
                        $('#info').val(result.data.info);
                        $('#pro-TypeId').val(result.data.productType.id);
                        $('#img2').attr('src',result.data.image);
                    }
                }
            );
        }

        //显示删除商品模态框
        function showDeleteModal(id) {
            //TODO:将id存放到隐藏域中，交给弹出框这个页面中
            $('#deleteProductId').val(id);
            $('#deleteProductModal').modal('show');
        }

        //删除商品信息
        function deleteProduct(id) {
            $.post(
                '${pageContext.request.contextPath}/admin/product/removeProductById',
                {'id':id},
                function (result) {
                    if (result.status == 1) {
                        console.log(result);
                        layer.msg(result.message,{
                            time: 700,
                            skin: "successMsg",
                        },function () {
                            //删除成功，重新加载页面数据
                           if( ${pageInfo.size > 1}){
                                console.log('${pageInfo}');
                                location.href = '${pageContext.request.contextPath}/admin/product/findAllProduct?pageNum='+${pageInfo.pageNum};
                           }else{
                                location.href = '${pageContext.request.contextPath}/admin/product/findAllProduct';
                           }
                        })
                    }else{
                        layer.msg(result.message,{
                            time: 700,
                            skin: "errorMsg"
                        });
                    }
                }
            );
        }
    </script>
</head>
    <body>

        <div class="panel panel-default" id="userPic">
            <div class="panel-heading">
                <h3 class="panel-title">商品管理</h3>
            </div>
            <div class="panel-body">
                <input type="button" value="添加商品" class="btn btn-primary" id="doAddPro">
                <br>
                <br>
                <div class="show-list text-center">
                    <table class="table table-bordered table-hover" style='text-align: center;'>
                        <thead>
                        <tr class="text-danger">
                            <th class="text-center">编号</th>
                            <th class="text-center">商品</th>
                            <th class="text-center">价格</th>
                            <th class="text-center">产品类型</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody id="tb">
                        <c:forEach items="${pageInfo.list}" var="product">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.price}</td>
                                <td>${product.productType.name}</td>
                                <td>
                                    <c:if test="${product.productType.status == 1}">有效商品</c:if>
                                    <c:if test="${product.productType.status == 0}">无效商品</c:if>
                                </td>
                                <td class="text-center">
                                    <input type="button" class="btn btn-warning btn-sm doProModify" value="修改"
                                           onclick="showProduct(${product.id})">
                                    <input type="button" class="btn btn-danger btn-sm doProDelete" value="删除" onclick="showDeleteModal(${product.id})">
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <%--分页插件--%>
                    <ul id="pagination"></ul>
                </div>
            </div>
        </div>

        <!-- 添加商品 start -->
        <div class="modal fade" tabindex="-1" id="Product">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <form action="${pageContext.request.contextPath}/admin/product/addProduct" method="post" class="form-horizontal"
                      enctype="multipart/form-data" id="addProductForm">
                    <div class="modal-content">
                        <!-- 头部、主体、脚注 -->
                        <div class="modal-header">
                            <button class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">添加商品</h4>
                        </div>
                        <div class="modal-body text-center row">
                            <div class="col-sm-8">
                                <div class="form-group">
                                    <%--TODO:隐藏域存放当前页码--%>
                                    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                                    <label for="product-name" class="col-sm-4 control-label">商品名称：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="product-name" name="name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="product-price" class="col-sm-4 control-label">商品价格：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="product-price" name="price">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="product-image" class="col-sm-4 control-label">商品图片：</label>
                                    <div class="col-sm-8">
                                        <a href="javascript:;" class="file">选择文件
                                            <input type="file" name="file" id="product-image">
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="product-info" class="col-sm-4 control-label">商品描述：</label>
                                    <div class="col-sm-8">
                                        <textarea id="product-info" class="form-control" name="info" style="height: 55px;width: 370px;resize: none" placeholder="请输入商品简介"></textarea>
                                        <p class="text  text-warning"><span id="add-count">30</span>/30</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" name="productTypeId" id="product-type">
                                            <option value="">--请选择--</option>
                                            <c:forEach items="${productTypes}" var="productType">
                                                <option value="${productType.id}">${productType.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <!-- 显示图像预览 -->
                                <img style="width: 160px;height: 180px;" id="img">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" type="submit">添加</button>
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- 添加商品 end -->

        <!-- 修改商品 start -->
        <div class="modal fade" tabindex="-1" id="myProduct">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-lg">
                <!-- 内容声明 -->
                <form action="${pageContext.request.contextPath}/admin/product/modifyProduct" method="post"
                      enctype="multipart/form-data" class="form-horizontal" id="modifyProductForm">
                    <div class="modal-content">
                        <!-- 头部、主体、脚注 -->
                        <div class="modal-header">
                            <button class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">修改商品</h4>
                        </div>
                        <div class="modal-body text-center row">
                            <div class="col-sm-8">
                                <div class="form-group">
                                    <%--TODO:隐藏域存放当前页码--%>
                                    <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
                                    <label for="pro-num" class="col-sm-4 control-label">商品编号：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="pro-num" name="id" readonly>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pro-name" class="col-sm-4 control-label">商品名称：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="pro-name" readonly name="name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pro-price" class="col-sm-4 control-label">商品价格：</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="pro-price" name="price">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pro-image" class="col-sm-4 control-label">商品图片：</label>
                                    <div class="col-sm-8">
                                        <a class="file">
                                            选择文件 <input type="file" name="file" id="pro-image">
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="product-info" class="col-sm-4 control-label">商品描述：</label>
                                    <div class="col-sm-8">
                                        <textarea id="info" class="form-control" name="info" style="height: 55px;width: 370px;resize: none" placeholder="请输入商品简介"></textarea>
                                        <p class="text text-warning"><span id="modify-count">30</span>/30</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="product-type" class="col-sm-4 control-label">商品类型：</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="pro-TypeId" name="productTypeId">
                                            <option value="">--请选择--</option>
                                            <c:forEach items="${productTypes}" var="productType">
                                                <option value="${productType.id}">${productType.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <!-- 显示图像预览 -->
                                <img style="width: 160px;height: 180px;" id="img2">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">确认</button>
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- 修改商品 end -->

        <!-- 确认删除商品 start -->
        <div class="modal fade" tabindex="-1" id="deleteProductModal">
            <!-- 窗口声明 -->
            <div class="modal-dialog modal-sm">
                <!-- 内容声明 -->
                    <div class="modal-content">
                        <!-- 头部、主体、脚注 -->
                        <div class="modal-header">
                            <button class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">提示消息</h4>
                        </div>
                        <div class="modal-body text-center row">
                            <h5 class="text-center text-warning">确认要删除该商品吗？</h5>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" id="deleteProductId">
                            <button class="btn btn-primary" data-dismiss="modal" onclick="deleteProduct($('#deleteProductId').val())">确认</button>
                            <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                        </div>
                    </div>
            </div>
        </div>
        <!-- 确认删除商品 end -->

    </body>
</html>
