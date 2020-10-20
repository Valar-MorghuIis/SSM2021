<%--
  Created by IntelliJ IDEA.
  User: xiaoj
  Date: 2020/10/12
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + request.getContextPath() + "/";

%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer-v3.1.1/layer/layer.js"></script>
    <script>
        $(function () {
            $("#btn01").click(function () {
                var array = [1,2,3,4];
                var jsonArray = JSON.stringify(array);
                $.ajax({
                    "url" : "send/array.html",
                    "type" : "post",
                    "data" : jsonArray,
                    "contentType" : "application/json;charset=utf-8",
                    "success" : function (response) {
                        alert(response);
                    },
                    "error" : function (response) {
                        alert(response);
                    }
                    }
                )
            });
            $("#btn02").click(function () {
                layer.msg("layer的弹窗");
            });
        })
    </script>
</head>
<body>
<a href="test/ssm.html">SSM整合测试</a>
<br><br>
<button id="btn01">发送数组测试@RequestBody</button>
<br><br>
<button id="btn02">使用layer弹窗</button>
</body>
</html>
