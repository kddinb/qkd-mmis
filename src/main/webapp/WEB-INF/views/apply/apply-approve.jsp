<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>药房申领单审批</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
</head>
<body>
<div id="tb">
    <form id="searchForm" method="post">
        <label>单号</label>
        <input name="serno" class="easyui-textbox" type="text">
        <a id="searchButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id="clearButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
    </form>
    <a id="passButton" href="javascript:void(0)" iconCls="icon-add" class="easyui-linkbutton">通过</a>
    <a id="backButton" href="javascript:void(0)" iconCls="icon-edit" class="easyui-linkbutton">退回</a>
</div>
<table id="dg_approve" class="easyui-datagrid"></table>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/apply/apply-approve.js"></script>
</body>
</html>
