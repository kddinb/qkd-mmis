<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IDEA搭建SpringMVC并用maven配置的小例子</title>
</head>
<%--<link rel="stylesheet" href="/asset/css/style.css"/>--%>
<script src="${ctx}/static/js-modules/easyui/jquery.min.js"></script>

<body>
<form id="userForm" action="/user/find" method="post">
    用户名：<input type="text" name="name"><br>
    密码：<input type="password" name="pwd">
    <button type="button" onclick="login()">登录</button>
</form>
</body>
<script>
    function login() {
        $.ajax({
            type: 'post',
            url: $('#userForm').attr('action'),
            data: $('#userForm').serialize(),
            dataType: 'json',
            success: function(data){
                console.log(data);
                if (data.result == "success"){
                    alert("登录成功");
                    location.href='/user/success';
                }else{
                    alert("用户名或者密码错误！");
                }
            }
        });
    }


</script>
</html>