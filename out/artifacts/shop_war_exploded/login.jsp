<%@ page contentType="text/html;charset=UTF-8" %>
<%
    // 取出错误提示信息
    String message = (String)request.getAttribute("message");
    if (message == null) message = "";
//当出错时返回页面，获得刚才输入的信息
    String loginName = (String)request.getParameter("user_name");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <script src="js/main.js"></script>
      <link rel="stylesheet" href="css/footer.css">
      <link rel="stylesheet" href="css/login.css">
      <link rel="stylesheet" href="css/nav-simple.css">
     <link rel="stylesheet" href="css/layout.css">
</head>
<style type="text/css" >
.w{
    width: 1080px;
    margin: 0 auto;
    position: relative;
    overflow: hidden;
}
</style>
<body>
<div class="nav-simple">
    <div class="w">
        <a class="logo" href="index.jsp">MMALL</a>
    </div>
</div>
<form action="user.do" method="post" onsubmit="check()">
<div class="page-wrap">
            <div class="w">
                <div class="user-con">
                    <div class="user-title">用户登录</div>
                    <font color="red"><%=message %></font>
                    <div class="user-box">
                        <div class="error-item">
                            <i class="fa fa-minus-circle error-icon"></i>
                            <p class="err-msg">Error</p>
                        </div>
                        <div class="user-item">
                            <label class="user-label">
                                <i class="fa fa-user">用户名:</i>
                            </label>
                            <input class="user-content" id="username" placeholder="请输入用户名" autocomplete="off" name="username">
                        </div>
                        <div class="user-item">
                            <label class="user-label" for="password">
                                <i class="fa fa-lock">密码:</i>
                            </label>
                            <input type="password" class="user-content" id="password" placeholder="请输入密码" autocomplete="off" name="password">
                        </div>
                        <button class="btn btn-submit" id="submit" name="cmd" value="login">登录</button>
                        <div class="link-item">
                            <a class="link" href="./user-pass-reset.html" target="_blank">忘记密码</a>
                            <a class="link" href="register.jsp" target="_blank">免费注册</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</form>
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



