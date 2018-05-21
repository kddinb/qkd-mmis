<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>通用名维护</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
</head>
<body>
<div id="tb">
    <form id="searchForm" method="post">
        <label>通用名名称</label>
        <input name="name" class="easyui-textbox" type="text">
        <label>通用名代码</label>
        <input name="code" class="easyui-textbox" type="text">
        <a id="searchButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id="clearButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
    </form>
    <a id="addButton" href="javascript:void(0)" iconCls="icon-add" class="easyui-linkbutton">新增</a>
    <a id="editButton" href="javascript:void(0)" iconCls="icon-edit" class="easyui-linkbutton">修改</a>
    <a id="saveButton" href="javascript:void(0)" iconCls="icon-save" class="easyui-linkbutton">保存</a>
    <a id="removeButton" href="javascript:void(0)" iconCls="icon-remove" class="easyui-linkbutton">删除</a>
    <a id="redoButton" href="javascript:void(0)" iconCls="icon-redo" class="easyui-linkbutton">取消</a>
</div>
<table id="dg_dicGname" class="easyui-datagrid"></table>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/dicGname/dic-gname.js"></script>
</body>
</html>
