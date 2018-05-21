<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>药事管理系统</title>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <!--Sidebar-->
    <link href="${ctx}/static/css/sidebar.css" rel="stylesheet">
    <!--Relate to this page-->
    <link href="${ctx}/static/css/index.css" rel="stylesheet">
    <jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
    <script type="text/javascript" src="${ctx}/static/js/openstyle/open-style.js"></script>
</head>
<body>
<div class="easyui-layout" id="cc" data-options="fit:true">
    <%@ include file="/WEB-INF/views/main/header.jsp" %>
    <div class="center-title" region="center">
        <div id="tt" class="easyui-tabs" border="false"
             data-options="tools:'#tab-tools',fit:true">
            <div id="tab1" class="text-center">
                <img src="${ctx}/static/img/welcome.png">
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/main/modal.jsp" %>
</body>
</html>

