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
        <input name="name" class="easyui-textbox" type="text">
        <label>药品编码</label>
        <input name="code" class="easyui-textbox" type="text">
        <input name="departId" id="scDepart" class="easyui-textbox" type="text">
        <a id="searchButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id="clearButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
    </form>
    <a id="addButton" href="javascript:void(0)" iconCls="icon-add" class="easyui-linkbutton">新增</a>
    <a id="switchButton" href="javascript:void(0)" iconCls="icon-mini-refresh" class="easyui-linkbutton">切换药房</a>
    <a id="removeButton" href="javascript:void(0)" iconCls="icon-remove" class="easyui-linkbutton">删除</a>
</div>
<div id="centerC" data-options="region:'center'">
<table id="dg_pharmacyMed" class="easyui-datagrid"></table>
</div>
<div id="southC" data-options="region:'south',split:'true',title:'详细页面'" style="height:185px;overflow:hidden;">
    <form id = "pharmacyMedForm" class="form-item">
        <br>
        <div>
            <span class="easyui-textbox" id="tbId" name="id"></span>
            <span class="easyui-textbox" id="tbDepartId" name="departId"></span>
            <span class="easyui-textbox" id="tbName" name="name"></span>
            <span class="easyui-textbox" id="tbCode" name="code"></span>
            <span class="easyui-combobox" id="cmbGnameId" name="gnameId"></span>
            <span class="easyui-textbox" id="tbPharId" name="pharId"></span>
        </div>
        <br>
        <div>
            <span class="easyui-textbox" id="tbTypeId" name="typeId"></span>
            <span class="easyui-textbox" id="tbStock" name="stock"></span>
            <span class="easyui-numberspinner" id="spRetailPrice" name="retailPrice"></span>
            <span class="easyui-combobox" id="cmbPackUnit" name="packUnit"></span>
            <span class="easyui-numberspinner" id="spPackNum" name="packNum"></span>
        </div>
        <br>
        <div>
            <span class="easyui-combobox" id="cmbInUsed" name="inUsed"></span>
            <span class="easyui-combobox" id="cmbOutUsed" name="outUsed"></span>
            <span class="easyui-textbox" id="tbEfid" name="efid"></span>
            <span class="easyui-textbox" id="tbRemark" name="remark"></span>
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/pharmacyMed/pharmacy-med.js"></script>
</body>
</html>
