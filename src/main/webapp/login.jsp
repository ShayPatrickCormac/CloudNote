<%--
  Created by IntelliJ IDEA.
  User: Zijian Huang
  Date: 6/3/2021
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>CloudNote</title>
    <link href="statics/css/login.css" rel="stylesheet" type="text/css" />
    <script src="statics/js/jquery-1.11.3.js" type=text/javascript></script>
    <script src="statics/js/config.js" type=text/javascript></script>
    <script src="statics/js/util.js" type=text/javascript></script>

</head>
<body>
<!--head-->
<div id="head">
    <div class="top">
        <div class="fl yahei18">For you to record your life</div>
    </div>
</div>

<!--login box-->
<div class="wrapper">
    <div id="box">
        <div class="loginbar">Login</div>
        <div id="tabcon">
            <div class="box show">
                <form action="user" method="post" id="loginForm">
                    <input type="hidden" name="actionName" value="login"/>
                    <input type="text" class="user yahei16" id="userName" name="userName" value="${resultInfo.result.uname}" /><br /><br />
                    <input type="password" class="pwd yahei16" id="userPwd" name="userPwd" value="${resultInfo.result.upwd}" /><br /><br />
                    <input name="rem" type="checkbox" value="1"  class="inputcheckbox"/> <label>Remember me</label>&nbsp; &nbsp;
                    <span id="msg" style="color: red;font-size: 12px">${resultInfo.msg}</span><br /><br />
                    <input type="button" class="log jc yahei16" value="Sign in" onclick="checkLogin()" />&nbsp; &nbsp; &nbsp; <input type="reset" value="Cancel" class="reg jc yahei18" />
                </form>
            </div>
        </div>
    </div>
</div>

<div id="flash">
    <div class="pos">
        <!-- <a bgUrl="images/banner-bg1.jpg" id="flash1" style="display:block;"><img src="images/banner_pic1.png"></a>
        <a bgUrl="images/banner-bg2.jpg" id="flash2"                       ><img src="images/banner-pic2.jpg"></a>	   -->
    </div>
    <div class="flash_bar">
        <!-- <div class="dq" id="f1" onclick="changeflash(1)"></div>
        <div class="no" id="f2" onclick="changeflash(2)"></div>		-->
    </div>
</div>

<!--bottom-->
<div id="bottom">
    <div id="copyright">
        <div class="quick">
            <ul>
<%--                <li><input type="button" class="quickbd iphone" onclick="location.href='https://www.rose-hulman.edu'" /></li>--%>
<%--                <li><input type="button" class="quickbd android" onclick="location.href='https://www.rose-hulman.edu'" /></li>--%>
<%--                <li><input type="button" class="quickbd pc" onclick="location.href='https://www.rose-hulman.edu'" /></li>--%>
                <div class="clr"></div>
            </ul>
            <div class="clr"></div>
        </div>
        <div class="text">
            <a href="https://www.rose-hulman.edu"> Rose-Hulman Institute of Technology</a>
        </div>
    </div>
</div>
</body>

</html>
