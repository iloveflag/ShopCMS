<%--
  Created by IntelliJ IDEA.
  User: iloveflag
  Date: 2021/6/4
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.example.shop.model.*, java.util.*" %>
<%
    User user = (User) session.getAttribute("user");
    List<Cart> list = (List)request.getAttribute("allcart");
    if (list == null) list = new ArrayList<Cart>();
    int total=0;
    for (Cart cart : list) {
        total += cart.getCount() * cart.getPrice();
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>happymmall电商平台</title>
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/order.css">
    <link rel="stylesheet" href="css/layout.css">
</head>
<style type="text/css" >

</style>
<body>
<!--nav-->
<div class="nav">
    <div class="w">
        <div class="user-info">
            <span class="user login">
                <span class="link-text">
                    欢迎，
                    <span class="username"></span>
                </span>
                <span class="link js-logout">
                    <%if(user!=null){ %>
                    <a class="link" href="user.do?cmd=logout">退出</a>
                    <p>欢迎你:<%=user.getUsername()%></p>
                    <%}%></span>
                </span>
            </span>
        </div>
        <ul class="nav-list">
            <li class="nav-item">
                <a class="link" href="cart.do?cmd=querycart">
                    <i class="fa fa-shopping-cart"></i>
                    购物车
                </a>
            </li>
            <li class="nav-item">
                <a class="link" href="./order.do?cmd=query">我的订单</a>
            </li>
            <li class="nav-item">
                <a class="link" href="./#">我的MMall</a>
            </li>
            <li class="nav-item">
                <a class="link" href="./#">关于MMall</a>
            </li>
        </ul>
    </div>
</div>
<!--nav-->

<div class="crumb">
    <div class="w">
        <div class="crumb-con">
            <a class="link" href="index.jsp">MMall</a>
            <span>>订单支付</span>
            <span class="link-text"></span>
        </div>
    </div>
</div>
<div class="pay-wrap w">
    <%if(request.getAttribute("byok")!=null){%>
        <p class="pay-tips">支付成功，已清空购物车!</p>
    <%}else{%>
    <p class="pay-tips">订单提交成功，请您尽快支付！</p>
    <p class="pay-tips enhance">总价:<%=total%></p>
    <p class="pay-tips enhance">请使用支付宝扫描如下二维码进行支付：</p>
    <div class="img-con">
        <img class="qr-code" src="/image/icon/qr.png">
    </div>
    <p class="pay-tips enhance"><a href="order.do?cmd=byok">我已支付</a></p>
    <%}%>
</div>




<!--footer-->
<div class="footer">
    <div class="w">
        <div class="links">
            <a class="link" href="http://www.imooc.com" target="_blank">慕课网</a> |
            <a class="link" href="https://www.baidu.com" target="_blank">百度</a> |
            <a class="link" href="https://www.taobao.com" target="_blank">淘宝</a> |
            <a class="link" href="https://www.zhihu.com" target="_blank">知乎</a>
        </div>
        <p class="copyright">
            Copyright © 2017 happymmall.com All Right Reserved
        </p>
    </div>
</div>
<!--footer-->
</body>
</html>
