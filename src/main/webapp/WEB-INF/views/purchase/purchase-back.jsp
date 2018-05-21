<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>驳回</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
</head>
<body>
<style>
    .form-item {
        text-align: center;
    }

</style>
<a id="saveButton" href="javascript:void(0);" class="easyui-savebutton" iconCls="icon-reload">确定</a>
<form id = "backForm" class="form-item">
    <br>
    <div>
        <span class="easyui-textarea" id="tbBackReason" name="backReason"></span>
    </div>
    <br>
</form>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/purchase/purchase-back.js"></script>
</body>
</html>
