<%--
  Created by IntelliJ IDEA.
  User: iloveflag
  Date: 2021/6/4
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>

</head>
<body>
<div style="text-align: center">
    <img src="/image/reserve.png" >
    <p><%=request.getAttribute("message")%></p>
    <a href="../cart.do?cmd=querycart">返回购物车</a>
</div>
</body>
</html>
