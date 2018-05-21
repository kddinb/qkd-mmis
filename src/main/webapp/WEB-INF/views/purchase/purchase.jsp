<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>药库采购申请</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
</head>
<body class="easyui-layout" fit="true">
<style>
    .form-item {
        text-align: center;
        padding: 5px;
    }

</style>
<div id="westW" data-options="region:'west',split:'true',title:'检索区域'"
     style="height:100%;width:290px;overflow:hidden;padding-bottom:110px;">
    <div id="tb" data-options="region:'north'" style="height:100px;overflow:hidden;">
        <form id="searchForm" method="post" class="form-item">
            <label>单号</label>
            <input name="serno" class="easyui-textbox" type="text">
            <br>
            <label>状态</label>
            <input name="status" class="easyui-textbox" type="text">
            <br>
            <a id="searchButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search">查询</a>
            <a id="clearButton" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload">清空</a>
        </form>
    </div>
    <%--<a id="syncButton" href="javascript:void(0)" iconCls="icon-reload" class="easyui-linkbutton">同步</a>--%>
    <%--<a id="switchButton" href="javascript:void(0)" iconCls="icon-mini-refresh" class="easyui-linkbutton">切换药库</a>--%>
    <%--<a id="editButton" href="javascript:void(0)" iconCls="icon-edit" class="easyui-linkbutton">修改</a>--%>
    <%--<a id="saveButton" href="javascript:void(0)" iconCls="icon-save" class="easyui-linkbutton">保存</a>--%>
    <%--<a id="removeButton" href="javascript:void(0)" iconCls="icon-remove" class="easyui-linkbutton">删除</a>--%>
    <%--<a id="redoButton" href="javascript:void(0)" iconCls="icon-redo" class="easyui-linkbutton">取消</a>--%>
    <table id="dg_purchase" class="easyui-datagrid"></table>
</div>
<div id="centerC" data-options="region:'center',split:'true',title:'详细页面'" style="height:185px;overflow:hidden;">
    <div id="dlg-toolbar" style="padding:5px;">
        <table cellpadding="0" cellspacing="0" style="width:100%;padding:5px;">
            <tr>
                <td>
                    <a id="subButton" href="javascript:void(0)" iconCls="icon-add" class="easyui-linkbutton">提交审批</a>
                    <a id="addButton" href="javascript:void(0)" iconCls="icon-add" class="easyui-linkbutton">新增</a>
                    <a id="editButton" href="javascript:void(0)" iconCls="icon-edit" class="easyui-linkbutton">修改</a>
                    <a id="saveButton" href="javascript:void(0)" iconCls="icon-save" class="easyui-linkbutton">保存</a>
                    <a id="removeButton" href="javascript:void(0)" iconCls="icon-remove" class="easyui-linkbutton">删除</a>
                    <a id="redoButton" href="javascript:void(0)" iconCls="icon-redo" class="easyui-linkbutton">取消</a>
                </td>
                <td style="text-align:right">
                    <label>药品名称</label>
                    <input name="nmae" id = "scnmae" class="easyui-textbox" type="text">
                    <a id="searchmButton" href="javascript:void(0);" class="easyui-linkbutton"
                       iconCls="icon-search">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div id="northC" data-options="region:'north',split:'true',title:'详细页面'" style="height:140px;overflow:hidden;">
        <div style="margin-bottom: 5px">
            <%--药品申请表单--%>
            <fieldset style="width:1024px;border:solid 8px #aaa;position:relative;">
                <form id="purchaseForm" class="form-item">
                    <table style="width:100%;">
                        <tr>
                            <td>
                                <span class="easyui-textbox" id="tbId" name="id"></span>
                                <span class="easyui-textbox" id="tbSerno" name="serno"></span>
                            </td>
                            <td>
                                <span class="easyui-textbox" id="tbDepartId" name="departId"></span>
                            </td>
                            <td>
                                <span class="easyui-textbox" id="tbMedId" name="medId"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="easyui-textbox" id="tbTypeId" name="typeId"></span>
                            </td>
                            <td>
                                <span class="easyui-textbox" id="tbSupplierId" name="supplierId"></span>
                            </td>
                            <td>
                                <span class="easyui-textbox" id="tbUserId" name="userId"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="easyui-textbox" id="tbStatus" name="status"></span>
                            </td>
                            <td>
                                <span class="easyui-textbox" id="tbApplyDate" name="applyDate"></span>
                            </td>
                            <td>
                                <span class="easyui-textbox" id="tbAmount" name="amount"></span>
                            </td>
                        </tr>
                    </table>
                </form>
            </fieldset>
        </div>
    </div>
    <!-- 遮罩层 -->
    <%--<div id="zSearchArea"--%>
         <%--style="display:none;background-color: #ccc;width: 100%;position:absolute;height: 100%;opacity: 0.5;z-index: 100;">--%>
    <%--</div>--%>
    <div id="centerM" data-options="region:'center',split:'true',title:'药品列表'" style="height:100%;overflow:hidden;padding-bottom:187px;">
        <table id="dg_storMed" class="easyui-datagrid"></table>
    </div>
</div>
<%--<div id="southC" data-options="region:'south',split:'true',title:'详细页面'" style="height:185px;overflow:hidden;">--%>
<%--<form id = "storMedForm" class="form-item">--%>
<%--<br>--%>
<%--<div>--%>
<%--<span class="easyui-textbox" id="tbId" name="id"></span>--%>
<%--<span class="easyui-textbox" id="tbDepartId" name="departId"></span>--%>
<%--<span class="easyui-textbox" id="tbName" name="name"></span>--%>
<%--<span class="easyui-textbox" id="tbCode" name="code"></span>--%>
<%--<span class="easyui-combobox" id="cmbGnameId" name="gnameId"></span>--%>
<%--<span class="easyui-textbox" id="tbPharId" name="pharId"></span>--%>
<%--</div>--%>
<%--<br>--%>
<%--<div>--%>
<%--<span class="easyui-textbox" id="tbTypeId" name="typeId"></span>--%>
<%--<span class="easyui-textbox" id="tbStock" name="stock"></span>--%>
<%--<span class="easyui-numberspinner" id="spRetailPrice" name="retailPrice"></span>--%>
<%--<span class="easyui-combobox" id="cmbPackUnit" name="packUnit"></span>--%>
<%--<span class="easyui-numberspinner" id="spPackNum" name="packNum"></span>--%>
<%--</div>--%>
<%--<br>--%>
<%--<div>--%>
<%--<span class="easyui-combobox" id="cmbInUsed" name="inUsed"></span>--%>
<%--<span class="easyui-combobox" id="cmbOutUsed" name="outUsed"></span>--%>
<%--<span class="easyui-textbox" id="tbEfid" name="efid"></span>--%>
<%--<span class="easyui-textbox" id="tbRemark" name="remark"></span>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/purchase/purchase.js"></script>
</body>
</html>
