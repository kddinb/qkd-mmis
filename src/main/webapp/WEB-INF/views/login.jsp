<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>药事管理系统-登录</title>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <link rel="stylesheet" href="${ctx}/static/css/login/login.css">
</head>
<body>
<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 text">
                    <h1>
                        <img src="${ctx}/static/js-modules/easyui-1.5.1/images/login-logo.png">
                        <span>|</span>
                        <span>&nbsp;用户登录</span>
                    </h1>
                </div>
            </div>
            <div class="row form-box">
                <div class="col-sm-6 col-sm-offset-3">

                    <div class="form-top">
                        <div class="text-center">
                            <h2>欢迎使用本系统</h2>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-lock"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" name="loginForm" id="loginForm" action="" method="post"
                              class="login-form form-group-lg">
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"> <i class="glyphicon glyphicon-user"></i></span>
                                <input type="text" class="form-control" name="name" placeholder="用户名">
                            </div>
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon"> <i class="glyphicon glyphicon-lock"></i></span>
                                <input type="password" class="form-control" placeholder="密码" name="pwd">
                            </div>
                            <button type="button" class="btn btn-primary btn-lg btn-block" id="submitButton">登&nbsp;录
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script type="text/javascript" src="${ctx}/static/js/login/login.js"></script>
</body>
</html>
