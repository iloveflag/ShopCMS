<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.example.shop.model.*, java.util.*" %>
<%
    User user = (User) session.getAttribute("user");
    List<Cart> list = (List)request.getAttribute("allcart");
    if (list == null) list = new ArrayList<Cart>();
    int total=0;
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>happymmall电商平台</title>
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/cart.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/order.css">
</head>
<style type="text/css">
#cart-table {
    width: 100%;
    padding-left: 20px;
}

#cart-footer {
    background: #eee;

}
</style>

<body>
    <!--nav-->
    <div class="nav">
        <div class="w">
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
            <div class="crumb-con">
                <a class="link" href="index.jsp">MMall</a>
                <span>>我的购物车</span>
                <span class="link-text"></span>
            </div>
        </div>
    </div>
    <% if(request.getAttribute("message")==null){%>
    <div class="cart-wrap w">
        <div class="cart-header">
            <table calss="cart-table" id="cart-table">
                <tbody>
                    <tr>
                        <th class="cart-cell cell-check">
                            <label>
                                <input type="checkbox" class="cart-select-all" checked="checked">
                                <span>全选</span>
                            </label>
                        </th>
                        <th class="cart-cell cell-info">商品信息</th>
                        <th class="cart-cell cell-price">单价</th>
                        <th class="cart-cell cell-count">数量</th>
                        <th class="cart-cell cell-total">合计</th>
                        <th class="cart-cell cell-opera">操作</th>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="cart-list">
            <table class="cart-table" data-product-id="26" data-checked="1">
                <tbody>
                <%for (Cart cart : list) {
                    total+=cart.getCount()*cart.getPrice();
                %>
                    <tr>
                        <td class="cart-cell cell-check">
                            <input type="checkbox" class="cart-select" checked="checked">
                        </td>
                        <td class="cart-cell cell-img">
                            <a><img class="p-img" src="<%=cart.getImg()%>" alt="<%=cart.getTitle()%>"></a>
                        </td>
                        <td class="cart-cell cell-info">
                            <a class="link p-name" href="goods.do?cmd=queryid&goodsid=<%=cart.getGoodsid()%>"><%=cart.getTitle()%>   库存:<span id="p-reserve"><%=cart.getReserve()%></span></a>
                        </td>
                        <td class="cart-cell cell-price">
                            ￥<%=cart.getPrice()%>
                        </td>
                        <td class="cart-cell cell-count">
                            <span class="count-btn" data-opera-type="minus"><a class="link" href="cart.do?goodsid=<%=cart.getGoodsid()%>&count=1&cmd=decreasechoose">-</a></span>
                            <form method="get" action="cart.do">
                                <input class="count-input" id="p-count"  oninput="if(value<0)value=0" data-max="118176" name="count" value="<%=cart.getCount()%>">
                                <input type="hidden" name="goodsid" value="<%=cart.getGoodsid()%>">
                                <button name="cmd" value="changecount">✔</button>
                            </form>
                            <span class="count-btn" data-opera-type="plus"><a class="link" href="cart.do?goodsid=<%=cart.getGoodsid()%>&count=1&cmd=addchoose">+</a></span>
                        </td>
                        <td class="cart-cell cell-total"><jsp:text>￥</jsp:text></td>
                        <td class="cart-cell cell-opera"><a class="link cart-delete" href="cart.do?goodsid=<%=cart.getGoodsid()%>&cmd=delchoose">删除</a></td>
                    </tr>
                <%} %>
                </tbody>
            </table>
        </div>
        <div class="cart-footer clear" id="cart-footer">
            <div class="select-con">
                <label>
                    <input type="checkbox" class="cart-select-all" checked="checked">
                    <span>全选</span>
                </label>
            </div>
            <div class="delete-con">
                <a class="cart-delete-seleced link">
                    <i class="fa fa-trash-o" aria-hidden="true"></i>
                    <span><a href="cart.do?cmd=delall">删除选中</a></span>
                </a>
            </div>
            <div class="submit-con">
                <span>总价：</span>
                <span class="submit-total"><%=total%></span>
                <span class="btn submit-btn"><a href="order.do?cmd=query" style="color:white;text-decoration: none;">去结算</a></span>
            </div>
            &nbsp
        </div>
    </div>
    <%}else{%>
    <p class="pay-tips enhance"><%=request.getAttribute("message")%></p>
    <%}%>
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

