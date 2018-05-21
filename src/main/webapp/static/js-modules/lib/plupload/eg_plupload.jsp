<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>上传组件测试</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>

<body>

<form>
    <div>
        LOGO
        <input type="hidden" id="image" name="image">
        <button id="selectImage">选择文件</button>
    </div>
    <br>

    <div id="showImage"></div>
</form>

<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js-modules/lib/plupload/eg_plupload.js"></script>
</body>
</html>

