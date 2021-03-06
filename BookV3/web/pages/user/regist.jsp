<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城用户注册页面</title>
    <%@ include file="/pages/common/head.jsp" %>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>

    <script type="text/javascript">

        $(function(){

            //给 username输入框,绑定 blur事件.实时检验用户名是否存在,返回到页面
            $("#username").blur(function() {
                //获取 标签下的 value值,
               var name = this.value;
               //将value用 getJSON传给UserServlet中检验
               $.getJSON("http://localhost:8080/BookV3/userServlet","action=ajaxExistsUsername&username=" + name,
               function (data){
                   //alert(data.existsName);
                    if (!data.existsName) {
                        $("span.errorMsg").text("用户名已存在");
                    } else {
                        $("span.errorMsg").text("用户名可用");
                    }
               });
            });

            //点击 验证码图片,进行更换验证码
            $("#code_img").click(function () {
               this.src="${basePath}kaptcha.jpg?d=" + new Date();
            });

            //1.这些信息验证 都是 在 submit 的时候进行判断的（正规的应该是 写完就验证 mouseout事务
            $("#sub_btn").click(function(){
                // 验证用户名：必须由字母，数字下划线组成，并且长度为5 到12 位
                //1.获取用户名输入框的内容
                var userName = $("#username").val();
                //2.创建 正则表达式
                var regExpUserName = /^[a-z0-9_-]{3,16}$/;
                //3.进行判断是否符合条件
                if (!regExpUserName.test(userName)) {
                    $("span.errorMsg").text("用户名不规范");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5 到12 位
                //1.获取密码输入框的内容
                var password = $("#password").val();
                //2.创建正则表达式
                var regExpPassword = /^[a-z0-9_-]{6,18}$/;
                //3.进行比较，判断是否符合
                if (!regExpPassword.test(password)) {
                    $("span.errorMsg").text("密码不规范");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }

                // 验证确认密码：和密码相同
                //1.获取两个密码输入框的内容
                //借用上面的password
                var password2 = $("#repwd").val();
                //2.进行比较
                if (password != password2) {
                    $("span.errorMsg").text("两次密码不一致");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }

                // 邮箱验证：xxxxx@xxx.com
                //1.获取邮箱输入框的内容
                var email = $("#email").val();
                //2.创建正则表达式
                var regExpEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3.进行判断
                if (!regExpEmail.test(email)) {
                    $("span.errorMsg").text("邮箱格式不正确");
                    return false;
                } else {
                    $("span.errorMsg").text("");
                }
                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                //1.获取验证码输入框内容
                var code = $("#code").val();
                //2.去除空格，进行判断
                var codeTrim = code.trim();
                if (codeTrim == null || codeTrim.length == 0) {
                    $("span.errorMsg").text("验证码不能为空");
                    return false;
                } else {
                    $("span.errotMsg").text("");
                }
            })
        })

    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="../static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册用户</h1>
                    <span class="errorMsg">
<%--                        <%=request.getAttribute("msg")==null?"请输入信息":request.getAttribute("msg")%>--%>
                        ${empty requestScope.msg ? "请输入信息" : requestScope.msg}
                    </span>
                </div>
                <div class="form">
<!--                <form action="regist_success.jsp">-->
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
<%--                        value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
                        value="${empty requestScope.username ? "" : requestScope.username}"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                               value="${empty requestScope.email ? "" : requestScope.email}"
                        />
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" name="code" style="width: 150px;" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height: 30px;">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>