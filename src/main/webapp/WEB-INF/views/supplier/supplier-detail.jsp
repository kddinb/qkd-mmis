<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>供应商</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
</head>
<body>
<style>
    .form-item {
        text-align: center;
    }

</style>
<form id = "supplierForm" class="form-item">
    <br>
    <div>
        <span class="easyui-textbox" id="tbName" name="name"></span>
    </div>
    <br>
    <div>
        <span class="easyui-textbox" id="tbCode" name="code"></span>
        <span class="easyui-textbox" id="tbContact" name="contact"></span>
    </div>
    <br>
    <div>
        <span class="easyui-textbox" id="tbTelephone" name="telephone"></span>
        <span class="easyui-textbox" id="tbAddr" name="addr"></span>
    </div>
    <br>
    <div>
        <span class="easyui-textbox" id="tbBank" name="bank"></span>
        <span class="easyui-textbox" id="tbBankAccount" name="bankAccount"></span>
    </div>

</form>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/supplier/supplier-detail.js"></script>
</body>
</html>
