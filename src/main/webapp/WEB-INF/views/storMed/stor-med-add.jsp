<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>药库药品维护</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
</head>
<body class="easyui-layout">
<style>
    .form-item {
        text-align: center;
    }

</style>
<div id="tb" data-options="region:'north'" style="height:58px;overflow:hidden;">
    <form id="searchForm" method="post">
        <label>药品名称</label>
        <input name="nmae" class="easyui-textbox" type="text">
        <label>药品编码</label>
        <input name="code" class="easyui-textbox" type="text">
        <input name="departId" id="scDepart" class="easyui-textbox" type="text">
        <a id="searchButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id="clearButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
    </form>
    <a id="saveButton" href="javascript:void(0)" iconCls="icon-save" class="easyui-linkbutton">保存</a>
</div>
<div id="centerC" data-options="region:'center'">
<table id="dg_storMed" class="easyui-datagrid"></table>
</div>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/storMed/stor-med-add.js"></script>
</body>
</html>
