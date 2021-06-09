<%--
  Created by IntelliJ IDEA.
  User: iloveflag
  Date: 2021/6/2
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.example.shop.model.*, java.util.*" %>
<%
    User user = (User) session.getAttribute("user");
    Goods goodsDetail = (Goods) request.getAttribute("goodsDetail");
    if (goodsDetail == null) goodsDetail = new Goods();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <script src="js/main.js"></script>
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/detail.css">
</head>
<style type="text/css">
    .crumb .crumb-item {
        color: #888;
        text-decoration: none;
        cursor: pointer;
    }

</style>
<body>
<!--nav-->
<div class="nav">
    <div class="w">
        <div class="user-info">
        </div>
        <ul class="nav-list">
            <li class="nav-item">
                <%if(user!=null){ %>
                <a class="link" href="user.do?cmd=logout">退出</a>
                <span>欢迎你:<%=user.getUsername()%></span>
                <%}%>
            </li>
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
        <div class="crumb-list">
            <a href="index.jsp" class="crumb-item">MMall</a>
            <span>></span>
            <span class="crumb-item">商品详情</span>
        </div>
    </div>
</div>
<div class="page-container w">
    <div class="intro-wrap clear">
        <div class="p-img-wrap">
            <div class="main-img-con">
                <img class="main-img" src="./<%=goodsDetail.getImg()%>" >
            </div>
        </div>
        <form class="p-info-wrap" method="get" action="cart.do">
            <p class="p-name"><%=goodsDetail.getTitle()%></p>
            <p class="p-subtitle">618大促</p>
            <div class="info-item p-price-con">
                <span class="label">价格：</span>
                <span class="p-price">￥<%=goodsDetail.getPrice()%></span>
            </div>
            <div class="info-item p-quantity-con">
                <span class="label">库存</span>
                <span class="p-price" id="p-reserve"><%=goodsDetail.getReserve()%></span>
            </div>
            <div class="info-item">
                <span class="label">数量 </span>
                <input type="hidden" name="goodsid" value="<%=goodsDetail.getGoodsid()%>">
                <input class="p-count" id="p-count" value="1" name="count" oninput="if(value<1)value=1">
                <span class="p-count-btn plus" data-opera-type="plus" onclick="plus()">+</span>
                <span class="p-count-btn minus" data-opera-type="mius" onclick="minus()">-</span>
            </div>
            <div class="info-item">
                <button class="btn cart-add" name="cmd" value="addchoose">加入购物车</button>
            </div>
        </form>
    </div>
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
