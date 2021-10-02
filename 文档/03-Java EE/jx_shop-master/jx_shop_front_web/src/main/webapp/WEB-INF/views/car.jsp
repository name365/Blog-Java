<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>我的购物车</title>
    <link rel="shortcut icon" type="image/x-icon" href="https://www.xmlvhy.com/images/xmshop/favicon.ico"  media="screen"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xmstyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/iconfont/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/zshop.js"></script>
    <style>
        <%--让表格中的数据居中--%>
        .table tbody tr td {
            vertical-align: middle;
        }

        .table th {
            text-align: center !important;
        }

        .footer {
            margin-top: 80px;
        }
    </style>
    <script>
        $(function () {
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
    </script>

</head>

<body class="animated fadeIn">

<!-- 导航栏 start -->
<jsp:include page="top.jsp">
    <jsp:param name="num" value="2"/>
</jsp:include>
<!-- 导航栏 end -->

<!-- content start -->
<div class="container">
    <div class="row">
        <div class="col-xs-12" style="margin-top: 66px">
            <div class="page-header">
                <h3>我的购物车</h3>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${empty cartList}">
            <h3 class="text-success" style="text-align: center;margin-top: 180px">
                购物车啥也没有哦, <a class="btn-link" href="${pageContext.request.contextPath}/front/product/searchAllProducts">前往购物吧~</a>
            </h3>
        </c:when>
        <%--如果购物车为空则让它看到的是空页面--%>
        <c:otherwise>
            <table class="table table-hover table-striped table-bordered table-condensed text-center">
                <tr class="success">
                    <th>
                        <input type="checkbox" id="checkall" name="checkall">
                        <label for="checkall"/>
                        <span class="text-danger">全选</span>
                    </th>
                    <th>序号</th>
                    <th>商品名称</th>
                    <th>商品图片</th>
                    <th>商品单价</th>
                    <th>商品数量</th>
                    <th>商品小计</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${cartList}" var="cart">
                    <tr class="single-item" name="clearTr">
                        <td>
                                <%--这里将购物车中的每个物品id 绑定--%>
                            <input type="checkbox" id="checkProduct" name="checkProduct" value="${cart.id}">
                            <label for="checkProduct"/>
                        </td>
                        <td>${cart.id}</td>
                        <td>${cart.product.name}</td>
                        <td>
                            <img src="${cart.product.image}" alt="" width="60" height="60">
                        </td>
                        <td>
                            <span class="price">${cart.product.price}</span>
                        </td>
                        <td>
                            <div class="cartCount1" style="align-content: center">
                                    <%--隐藏域中存放购物车id--%>
                                <input type="hidden" name="inputCartId" value="${cart.id}">
                                <input type="button" name="min" value="-">
                                <input class="textBox" name="textBox" type="text"
                                       value="${cart.productNum}">
                                <input id="add" name="add" type="button" value="+">
                            </div>
                        </td>

                            <%--TODO:通过后端计算的商品总价--%>
                            <%--<td id="totalPrice">--%>
                            <%--<span>${cart.totalPrice}</span>--%>
                            <%--</td>--%>
                            <%--TODO:前端计算商品总价--%>
                        <td>&yen; ${cart.totalPrice}</td>
                        <td>
                            <a class="btn btn-danger btn-sm" type="button" onclick="clearOneProduct(${cart.id})">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="8" align="right" style="height: 60px">
                        <button class="btn btn-warning margin-right-15" id="clearCartBtn" type="button"
                                onclick="showRemoveCartMore()">删除选中项
                        </button>
                        <button class="btn btn-warning  margin-right-15" type="button" onclick="clearAll()"> 清空购物车
                        </button>
                        <button class="btn btn-warning margin-right-15" type="button" onclick="goToShoping()"> 继续购物
                        </button>
                        <button class="btn btn-warning " type="button" onclick="settleAccounts()">结算</button>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" align="right" class="foot-msg" style="height: 50px">
                        <span>总共选中了 <b><span id="totalCount" name="totalCount">0</span></b> 件商品</span>
                        <span>总计: <b><span id="totalPrices" name="totalPrices"> &yen;0.00</span></b> 元</span>
                    </td>
                </tr>
            </table>
        </c:otherwise>
    </c:choose>
</div>
<!-- content end-->

<!-- 从购物车中删除某一项商品 start -->
<div class="modal fade" tabindex="-1" id="removeOneProductModal" style="top: 10%;">
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
                <h5 class="text-warning">确认从购物车中移除此商品吗？</h5>
            </div>
            <div class="modal-footer">
                <%--TODO:设置一个隐藏域来存放所要删除商品的id--%>
                <input type="hidden" id="cartId">
                <button class="btn btn-primary updateProType" onclick="removeOneProduct()" data-dismiss="modal">确认
                </button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 从购物车中删除某一项商品 end -->

<!-- 清空购物车 start -->
<div class="modal fade" tabindex="-1" id="removeAllProductModal" style="top: 10%;">
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
                <h5 class="text-warning">确认从购物车中移除所有商品吗？</h5>
            </div>
            <div class="modal-footer">
                <%--TODO:设置一个隐藏域来存放所要删除商品的id--%>
                <input type="hidden" id="cartId">
                <button class="btn btn-primary updateProType" onclick="removeAllProduct()" data-dismiss="modal">确认
                </button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--清空购物车 end -->

<!-- 移除购物车中选中的商品 start -->
<div class="modal fade" tabindex="-1" id="removeMoreProductModal" style="top: 10%;">
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
                <h5 class="text-warning">确认从购物车中移除所选商品吗？</h5>
            </div>
            <div class="modal-footer">
                <%--TODO:设置一个隐藏域来存放所要删除商品的id--%>
                <input type="hidden" id="cartIds">
                <button class="btn btn-primary updateProType" onclick="removeMoreProduct()" data-dismiss="modal">确认
                </button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div >
<!--移除购物车汇总选中的商品 end -->

<!-- footers start -->
<footer id="footer" class="footer navbar-fixed-bottom">
    <div class="container-fluid footers">
        Copy Right @ 2020 Services To Help Agriculture
    </div>
</footer>
<!-- footers end -->
<input type="hidden" id="refreshed" value="no">
</body>

<script>
    // jQuery中的$函数(jQuery)的作用
    // 1. 如果$函数的参数是一个函数那么该函数绑定文档加载完成后要执行的回调函数
    // 2. 如果$函数的参数是一个选择器字符串那么$函数会返回对应的元素(jQuery对象)
    // 3. 如果$函数的参数是一个标签字符串那么$函数会创建该元素并返回(jQuery对象)
    // 4. 如果$函数的参数是一个原生的JS元素对象那么$函数会将它转变成jQuery对象
    $(function () {
        //TODO:当从订单确认页面点击浏览器返回时候设置刷新页面
        var e = document.getElementById("refreshed");
        if (e.value == "no") e.value = "yes";
        else {
            e.value = "no";
            location.reload();
        }

        //TODO:返回页面时候，如果上一次有选中商品也需要计算
        calcTotal();

        $('#checkall').on('change', function (evt) {
            // 获取事件源的两种方式: evt.target或者this
            // 这里拿到的是原生的JavaScript对象
            if ($(this).prop('checked')) {
                $('.single-item input[type="checkbox"]').prop('checked', true);
                calcTotal();
            } else {
                $('.single-item input[type="checkbox"]').prop('checked', false);
                $('#totalCount').text('0');
                $('#totalPrices').html('&yen;0.00');
            }
        });

        // 为单个商品项的复选框绑定改变事件
        $('input[name="checkProduct"]').on('change', function () {
            calcTotal();
            if (!$(this).prop('checked')) {
                $('#checkall').prop('checked', false);
                $(this).prop('checked', false);
            }
        });

        // 为删除选中商品超链接绑定事件回调
        // $('#clearCartBtn').on('click', function () {
        //     if (window.confirm('确定要删除所选商品吗?')) {
        //         $('.single-item').each(function () {
        //             if ($(this).find('input[name="checkProduct"]').prop('checked')) {
        //                 $(this).remove();
        //             }
        //         });
        //         calcTotal();
        //     }
        // });

        // 为单个商品项删除超链接绑定事件回调
        // $('.single-item a').on('click', function () {
        //     if (window.confirm('确定要删除该项吗?')) {
        //         $(this).parent().parent().remove();
        //         calcTotal();
        //     }
        // });

        // 为减少和添加商品数量的按钮绑定事件回调
        $('.single-item input[type="button"]').on('click', function (evt) {
            //遍历查找被选中的元素，这里的this 为 +  - 两个按钮
            $(this).parent().parent().find('input[name="checkProduct"]').prop('checked', true);
            if ($(this).val().trim() == '-') {
                //计算商品数量减
                var count = parseInt($(this).next().val());
                if (count > 1) {
                    count -= 1;
                    $(this).next().val(count);
                } else {
                    //商品至少有一个
                    // alert('商品数量最少为1');
                    layer.msg("商品数量最少为1", {
                        time: 1500,
                        skin: 'warningMsg'
                    });
                }
                //TODO:从 - 找到隐藏域的元素，拿到对应的cart id值
                var id = $(this).prev().val();
                // console.log(id);
                // console.log(count);
                //发送ajax 请求，更新商品数量
                inputModifyProductNum(id, count);
            } else {
                //计算商品数量加
                var count = parseInt($(this).prev().val());
                if (count < 200) {
                    count += 1;
                    $(this).prev().val(count);
                } else {
                    //超过库存则禁止按钮动作
                    layer.msg("库存不足", {
                        time: 1500,
                        skin: 'warningMsg'
                    });
                }
                //TODO:从 + 找到隐藏域的元素，拿到对应的cart id值
                var id = $(this).prev().prev().prev().val();
                // console.log(id);
                // console.log(count);
                //发送ajax 请求，更新商品数量
                inputModifyProductNum(id, count);
            }
            //找到每一项对应的单价
            var price = parseFloat($(this).parent().parent().prev().find('span').text());
            //toFixed() 方法可把 Number 四舍五入为指定小数位数的数字。
            $(this).parent().parent().next().html('&yen; ' + (price * count).toFixed(2));
            calcTotal();
        });

        // 为商品数量文本框绑定改变事件回调
        $('.single-item input[type="text"]').on('change', function () {
            //这里的this为 商品数量的输入框
            $(this).parent().parent().find('input[name="checkProduct"]').prop('checked', true);
            var count = parseInt($(this).val());
            if (count != $(this).val() || count < 1 || count > 200) {
                layer.msg("无效的商品数量", {
                    time: 1500,
                    skin: 'warningMsg'
                });
                count = 1;
                $(this).val(count);
                //TODO:从文本框找到隐藏域的元素，拿到对应的cart id值
                var id = $(this).prev().prev().val();
                // console.log(id);
                // console.log(count);
                //发送ajax 请求，更新商品数量
                inputModifyProductNum(id, count);
            }

            //TODO:从文本框找到隐藏域的元素，拿到对应的cart id值
            var id = $(this).prev().prev().val();
            // console.log(id);
            // console.log(count);
            //发送ajax 请求，更新商品数量
            inputModifyProductNum(id, count);

            var price = parseFloat($(this).parent().parent().prev().find('span').text());
            $(this).parent().parent().next().html('&yen;' + (price * count).toFixed(2));
            calcTotal();
        });
    });

    //购物车页面商品数量改变则更新商品数量
    function inputModifyProductNum(cartId, num) {
        //发送ajax 异步请求，更新购物车中最终的商品数量
        $.post(
            '${pageContext.request.contextPath}/front/cart/inputModifyProductNum',
            {'cartId': cartId, 'productNum': num},
            function (result) {
                if (result.status == 1) {
                    //修改成功
                    // layer.msg(result.message,{
                    //     time: 1000,
                    //     skin: 'successMsg'
                    // });
                } else {
                    //修改失败
                    layer.msg(result.message, {
                        time: 1500,
                        skin: 'errorMsg'
                    });
                }
            }
        );
    }

    // 计算总计
    function calcTotal() {
        var checkBoxes = $('input[name="checkProduct"]');
        var priceSpans = $('.single-item .price');
        var countInputs = $('.single-item .textBox');
        var totalCount = 0;
        var totalPrice = 0;
        for (var i = 0; i < priceSpans.length; i += 1) {
            // 复选框被勾中的购物车项才进行计算
            if ($(checkBoxes[i]).prop('checked')) {
                // 强调: jQuery对象使用下标运算或get方法会还原成原生的JavaScript对象
                var price = parseFloat($(priceSpans[i]).text());
                var count = parseInt($(countInputs[i]).val());
                totalCount += count;
                totalPrice += price * count;
            }
        }
        //显示计算后的总价以及选中的商品总数量
        $('#totalCount').text(totalCount);
        $('#totalPrices').html('&yen;' + totalPrice.toFixed(2));
    }

    //继续 购物，跳转到商品主页面
    function goToShoping() {
        location.href = '${pageContext.request.contextPath}/front/product/searchAllProducts';
    }

    //显示清空购物车确认框
    function clearAll() {
        $('#removeAllProductModal').modal('show')
    }

    //清空购物车中所有的商品
    function removeAllProduct() {
        $.post(
            '${pageContext.request.contextPath}/front/cart/clearAllProductFromCart',
            function (result) {
                if (result.status == 1) {
                    //表示购物车清空成功
                    // layer.msg(result.message,{
                    //     time: 1000,
                    //     skin: 'successMsg'
                    // });
                    //跳转到空购物车页面
                    location.href = '${pageContext.request.contextPath}/front/cart/myCarts';
                }
            }
        );
    }

    //显示从购物车删除某一个商品确认框
    function clearOneProduct(id) {
        $('#cartId').val(id);
        $('#removeOneProductModal').modal('show')
    }

    //从购物车中移除一个商品
    function removeOneProduct() {
        $.post(
            '${pageContext.request.contextPath}/front/cart/removeOneProduct',
            {'cartId': $('#cartId').val()},
            function (result) {
                if (result.status == 1) {
                    //商品移除成功则重新加载页面
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

    //显示移除选中商品的提示框，并且计算选择的cartId
    function showRemoveCartMore() {
        //找到被选中的所有的项，这里需要注意排除 checkall的那个 checkbox
        var selects = $('.single-item input:checked');
        var cartIds = [];
        for (var i = 0; i < selects.length; i++) {
            cartIds.push(selects[i].value);
        }
        cartIds = cartIds.join(',');
        // console.log(cartIds);
        //将cartIds 给 modal
        $('#cartIds').val(cartIds);
        $('#removeMoreProductModal').modal('show');
    }

    //移除购物车中选中的商品
    function removeMoreProduct() {
        if ($('#cartIds').val().length > 0) {
            $.post(
                '${pageContext.request.contextPath}/front/cart/removeMoreProductFromCart',
                {'cartIds': $('#cartIds').val()},
                function (result) {
                    if (result.status == 1) {
                        //成功则刷新页面
                        location.reload();
                    } else {
                        layer.msg(result.message, {
                            time: 1500,
                            skin: 'errorMsg'
                        });
                    }

                }
            );
        } else {
            layer.msg("请选择要移除的商品", {
                time: 1500,
                skin: 'warningMsg'
            });
        }
    }

    //结算购物车
    function settleAccounts() {
        var orderCartIds = [];
        var selects = $('.single-item input:checked');

        for (var i = 0; i < selects.length; i++) {
            orderCartIds.push(selects[i].value);
        }
        //获取选中的cartid 数组 24,26,15...
        orderCartIds = orderCartIds.join(',');

        var count = $('#totalCount').text();
        var price = $('#totalPrices').text();

        if (selects.length == 0) {
            layer.msg("请选择要购买的商品", {
                time: 1500,
                skin: 'warningMsg'
            });
        } else {
            //发送一个ajax 请求，到后端保存数据起来
            $.post(
                '${pageContext.request.contextPath}/front/cart/addTempOrderItem',
                {'count': count, 'price': price, 'orderCartIds': orderCartIds},
                function (result) {
                    if (result.status == 1) {
                        //成功，则转到确认订单页面
                        location.href = '${pageContext.request.contextPath}/front/order/confirmOrder';
                    }
                }
            );
        }
    }
</script>

</html>