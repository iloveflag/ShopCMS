<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.shop.model.*, java.util.*" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/layout.css">
</head>
<style type="text/css">
.banner-con .banner-img{
    width: 100%;
    height: 370px;
}
</style>
<body>
<!--nav-->
<div class="nav">
    <div class="w">
        <div class="user-info">
            <span class="user not-login">
                <%if(user==null){ %>
                <span class="link js-login" ><a href="./login.jsp"> 登录</a></span>
                <span class="link js-register"><a href="./register.jsp"> 注册</a></span>
                <%}%>
            </span>
        </div>
        <ul class="nav-list">
            <li class="nav-item">
                <%if(user!=null){ %>
                <a class="link" href="user.do?cmd=logout">退出</a>
                <span>欢迎你:<%=user.getUsername()%></span>
                <%}%>
            </li>
            <li class="nav-item">
                <a class="link" href="./cart.do?cmd=querycart">
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

<!--index-->
<div class="w">
            <ul class="keywords-list">
                <li class="keywords-item">
                    <a class="link"href="#F1">家用电器</a>
                </li>
                <li class="keywords-item">
                    <a class="link"href="#F2">数码3C</a>
                </li>
                <li class="keywords-item">
                    <a class="link"href="#F3">服装箱包</a>
                </li>
                <li class="keywords-item">
                    <a class="link"href="#F4">食品生鲜</a>
                </li>
                <li class="keywords-item">
                    <a class="link"href="#F5">酒水饮料</a>
                </li>
            </ul>
            <div class="banner-con">
                <!--<div class="loading"></div>-->
                <ul>
                    <li><a href="" title=""><img class="banner-img" src="image/banner/banner1.jpg" alt=""></a> </li>
                </ul>
            </div>
        </div>
        <div class="w">
            <div class="floor-wrap">
                <h1 class="floor-title" id="F1">F1 家用电器</h1>
                <ul class="floor-list">
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100000">
                            <span class="floor-text">双开门冰箱</span>
                            <img class="floor-img" src="image/floor/floor1-1.jpg" alt="双开门冰箱" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100001">
                            <span class="floor-text">电视</span>
                            <img class="floor-img" src="image/floor/floor1-2.jpg" alt="电视" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100002">
                            <span class="floor-text">洗衣机</span>
                            <img class="floor-img" src="image/floor/floor1-3.jpg" alt="洗衣机" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100003">
                            <span class="floor-text">空调</span>
                            <img class="floor-img" src="image/floor/floor1-4.jpg" alt="空调" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100004">
                            <span class="floor-text">热水器</span>
                            <img class="floor-img" src="image/floor/floor1-5.jpg" alt="热水器" />
                        </a>
                    </li>
                </ul>
            </div>
            <div class="floor-wrap">
                <h1 class="floor-title" id="F2">F2 数码3C</h1>
                <ul class="floor-list">
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100005">
                            <span class="floor-text">笔记本电脑</span>
                            <img class="floor-img" src="image/floor/floor2-1.jpg" alt="笔记本电脑" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100006">
                            <span class="floor-text">手机</span>
                            <img class="floor-img" src="image/floor/floor2-2.jpg" alt="手机" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100007">
                            <span class="floor-text">平板电脑</span>
                            <img class="floor-img" src="image/floor/floor2-3.jpg" alt="平板电脑" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100008">
                            <span class="floor-text">数码相机</span>
                            <img class="floor-img" src="image/floor/floor2-4.jpg" alt="数码相机" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100009">
                            <span class="floor-text">3C配件</span>
                            <img class="floor-img" src="image/floor/floor2-5.jpg" alt="3C配件" />
                        </a>
                    </li>
                </ul>
            </div>
            <div class="floor-wrap">
                <h1 class="floor-title" id="F3">F3 服装箱包</h1>
                <ul class="floor-list">
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100010">
                            <span class="floor-text">女装</span>
                            <img class="floor-img" src="image/floor/floor3-1.jpg" alt="女装" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100011">
                            <span class="floor-text">帽子专区</span>
                            <img class="floor-img" src="image/floor/floor3-2.jpg" alt="帽子专区" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100012">
                            <span class="floor-text">旅行箱</span>
                            <img class="floor-img" src="image/floor/floor3-3.jpg" alt="旅行箱" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100013">
                            <span class="floor-text">手提包</span>
                            <img class="floor-img" src="image/floor/floor3-4.jpg" alt="手提包" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100014">
                            <span class="floor-text">保暖内衣</span>
                            <img class="floor-img" src="image/floor/floor3-5.jpg" alt="保暖内衣" />
                        </a>
                    </li>
                </ul>
            </div>
            <div class="floor-wrap">
                <h1 class="floor-title" id="F4">F4 食品生鲜</h1>
                <ul class="floor-list">
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100015">
                            <span class="floor-text">最爱零食</span>
                            <img class="floor-img" src="image/floor/floor4-1.jpg" alt="最爱零食" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100016">
                            <span class="floor-text">生鲜</span>
                            <img class="floor-img" src="image/floor/floor4-2.jpg" alt="生鲜" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100017">
                            <span class="floor-text">半成品菜</span>
                            <img class="floor-img" src="image/floor/floor4-3.jpg" alt="半成品菜" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100018">
                            <span class="floor-text">速冻专区</span>
                            <img class="floor-img" src="image/floor/floor4-4.jpg" alt="速冻专区" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100019">
                            <span class="floor-text">进口牛奶</span>
                            <img class="floor-img" src="image/floor/floor4-5.jpg" alt="进口牛奶" />
                        </a>
                    </li>
                </ul>
            </div>
            <div class="floor-wrap">
                <h1 class="floor-title" id="F5">F5 酒水饮料</h1>
                <ul class="floor-list">
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100020">
                            <span class="floor-text">白酒</span>
                            <img class="floor-img" src="image/floor/floor5-1.jpg" alt="白酒" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100021">
                            <span class="floor-text">红酒</span>
                            <img class="floor-img" src="image/floor/floor5-2.jpg" alt="红酒" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100022">
                            <span class="floor-text">饮料</span>
                            <img class="floor-img" src="image/floor/floor5-3.jpg" alt="饮料" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100023">
                            <span class="floor-text">调制鸡尾酒</span>
                            <img class="floor-img" src="image/floor/floor5-4.jpg" alt="调制鸡尾酒" />
                        </a>
                    </li>
                    <li class="floor-item">
                        <a target="_blank" href="goods.do?cmd=queryid&goodsid=100024">
                            <span class="floor-text">进口洋酒</span>
                            <img class="floor-img" src="image/floor/floor5-5.jpg" alt="进口洋酒" />
                        </a>
                    </li>
                </ul>
            </div>
        </div>

<!--index-->
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

