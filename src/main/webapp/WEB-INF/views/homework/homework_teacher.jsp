<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>作业列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
</head>
<body>
<div id="tb">
    <form id="searchForm" method="post">
        <label>标题</label>
        <input name="title" class="easyui-textbox" type="text">
        <label>截止日期</label>
        <input type="text" name="deadlineDateStart" class="easyui-datebox">
        <label>到</label>
        <input type="text" name="deadlineDateEnd" class="easyui-datebox">
        <a id="clearButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
        <a id="searchButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
    <a href="javascript:void(0)" iconCls="icon-add" class="easyui-linkbutton"
       onclick="window.parent.addTab1('发布作业','${ctx}/study/homework/add','icon-add')">发布作业</a>
</div>
<table id="dg_teacher" class="easyui-datagrid"></table>
<script type="text/html" id="operateTemplate">
    <a href="javascript:;" class="btn btn-primary btn-xs js-editButton js-check" style="margin-right: 5px;"
       data-id="{{id}}">查看作业</a>
</script>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/homework/homework_teacher.js"></script>
</body>
</html>
