<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>错误页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/404.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var h = $(window).height();
            $('body').height(h);
            $('.mianBox').height(h);
            centerWindow(".tipInfo");
        });

        //2.将盒子方法放入这个方，方便法统一调用
        function centerWindow(a) {
            center(a);
            //自适应窗口
            $(window).bind('scroll resize',
                function () {
                    center(a);
                });
        }

        //1.居中方法，传入需要剧中的标签
        function center(a) {
            var wWidth = $(window).width();
            var wHeight = $(window).height();
            var boxWidth = $(a).width();
            var boxHeight = $(a).height();
            var scrollTop = $(window).scrollTop();
            var scrollLeft = $(window).scrollLeft();
            var top = scrollTop + (wHeight - boxHeight) / 2;
            var left = scrollLeft + (wWidth - boxWidth) / 2;
            $(a).css({
                "top": top,
                "left": left
            });
        }
    </script>
</head>
<body class="animated fadeIn">
<div class="mianBox">
    <img src="${pageContext.request.contextPath}/images/yun0.png" alt="" class="yun yun0"/>
    <img src="${pageContext.request.contextPath}/images/yun1.png" alt="" class="yun yun1"/>
    <img src="${pageContext.request.contextPath}/images/yun2.png" alt="" class="yun yun2"/>
    <img src="${pageContext.request.contextPath}/images/bird.png" alt="" class="bird"/>
    <img src="${pageContext.request.contextPath}/images/san.png" alt="" class="san"/>
    <div class="tipInfo">
        <div class="in">
            <div class="textThis">
                <h2>服务器内部错误！</h2>
                <p><span>页面自动<a id="href" href="${pageContext.request.contextPath}/showLogin">跳转</a></span><span>等待<b
                        id="wait"> 6</b> 秒</span></p>
                <script type="text/javascript">
                    (function () {
                        var wait = document.getElementById('wait'),
                            href = document.getElementById('href').href;
                        var interval = setInterval(function () {
                            var time = --wait.innerHTML;
                            if (time <= 0) {
                                clearInterval(interval);
                                location.href = href;
                            };
                        }, 1000);
                    })();
                </script>
            </div>
        </div>
    </div>
</div>

</body>
</html>
