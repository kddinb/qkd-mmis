requirejs(['serializeObject', 'art-template', 'moment'], function (_, template, moment) {
    $(document).ready(function () {
        listData();
        setButtonState('enable', 'enable', 'enable', 'enable');
    });

    function listData() {
        $('#dg_approve').datagrid({
            method: 'post',
            striped: true,
            singleSelect: true,
            pageSize: 10,
            border: false,
            fitColumns: true,
            rownumbers: true,
            fit: true,
            toolbar: '#tb',
            pagination: true,
            queryParams: $("#searchForm").serializeObject(),
            onDblClickRow: function (rowIndex, row) {
            },
            onLoadSuccess: function () {
            },
            url: ctx + "/purchase/getExceptNoSub",
            columns: [[
                {
                    field: 'departId', title: '库房', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'serno', title: '单号', width: fixWidth(0.50),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'medId', title: '药品', width: fixWidth(0.50),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'typeId', title: '药品类型', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'supplierId', title: '供应商', width: fixWidth(0.60),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'userId', title: '申请人员', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'applyDate', title: '申请日期', width: fixWidth(0.50),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'amount', title: '数量', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'status', title: '状态', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                }
            ]],
            onAfterEdit: function (rowIndex, rowData, changes) {
                // $.ajax({
                //     type: "POST",
                //     url: ctx + "/supplier/saveSupplier",
                //     data: rowData,
                //     dataType: "json",
                //     success: function (data) {
                //         console.log(data);
                //         if (data.result == true) {
                //             message('', '成功', {'returnJson': data, 'type': 'success'});
                //             var searchData = $("#searchForm").serializeObject();
                //             $('#dg_approve').datagrid('reload', searchData);
                //         } else {
                //             message('', '失败', {'returnJson': data, 'type': 'error'});
                //         }
                //     }
                // });
                // editRow = undefined;
            },
            onClickRow: function (rowIndex, rowData) {
                //新增或修改时行不可选
                // if (editRow != undefined) {
                //     $('#dg_approve').datagrid('unselectRow', rowIndex);
                // }
            }
        });
    }

    var editRow = undefined;

    /**
     * 设置按钮状态
     */
    function setButtonState(searchState, clearState, addState, editState) {
        $('#searchButton').linkbutton(searchState);
        $('#clearButton').linkbutton(clearState);
        $('#passButton').linkbutton(addState);
        $('#backButton').linkbutton(editState);
    }

    /**
     * 查询
     */
    $("#searchButton").click(function () {
        if ($('#searchButton').linkbutton('options').disabled == false) {
            var data = $("#searchForm").serializeObject();
            $('#dg_approve').datagrid('load', data);
        }
    });

    /**
     * 监听回车事件,触发查询
     */
    $("#searchForm,#searchForm input").bind("keydown", function (e) {
        // 兼容FF和IE和Opera
        var theEvent = e || window.event;
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
        if (code == 13) {
            $("#searchButton").click();
        }
    });

    /**
     *  清空查询条件
     */
    $("#clearButton").click(function () {
        if ($('#clearButton').linkbutton('options').disabled == false) {
            $('#searchForm').form('reset');
            var data = $("#searchForm").serializeObject();
            $('#dg_approve').datagrid('load', data);
        }
    });

    /**
     * 通过
     */
    $("#passButton").click(function () {
        if ($('#passButton').linkbutton('options').disabled == false) {
            var rows = $('#dg_approve').datagrid("getSelections");
            var status = rows[0].status;
            if(status != '待审批'){
                $.messager.alert("提示", "请记录已审批完成", "error");
                return;
            }
            if(rows.length>0){
                $.messager.confirm("提示", "确认审批通过?", function (r) {
                    if(r){
                        rows[0].status = '通过';
                        $.ajax({
                            type: "POST",
                            url: ctx + "/purchase/savePurchase",
                            data: rows[0],
                            dataType: "json",
                            success: function (data) {
                                console.log(data);
                                if (data.result == true) {
                                    message('', '成功', {'returnJson': data, 'type': 'success'});
                                    var searchData = $("#searchForm").serializeObject();
                                    $('#dg_approve').datagrid('reload', searchData);
                                } else {
                                    message('', '失败', {'returnJson': data, 'type': 'error'});
                                }
                            }
                        });
                    }
                });
            }
            else {
                $.messager.alert("提示", "请选择一行进行操作", "error");
            }
        }
    });

    /**
     * 退回
     */
    $("#backButton").click(function () {
        if ($('#backButton').linkbutton('options').disabled == false) {
            var rows = $('#dg_approve').datagrid("getSelections");
            var status = rows[0].status;
            if(status != '待审批'){
                $.messager.alert("提示", "请记录已审批完成", "error");
                return;
            }
            if (rows.length>0) {
                $.messager.confirm("提示", "确认驳回?", function (r) {
                    if(r){
                        rows[0].status = '驳回';
                        $.ajax({
                            type: "POST",
                            url: ctx + "/purchase/savePurchase",
                            data: rows[0],
                            dataType: "json",
                            success: function (data) {
                                if (data.result == true) {
                                    message('', '驳回成功', {'returnJson': data, 'type': 'success'});
                                    var searchData = $("#searchForm").serializeObject();
                                    $('#dg_approve').datagrid('reload', searchData);
                                } else {
                                    message('', '失败', {'returnJson': data, 'type': 'error'});
                                }
                            }
                        });
                    }
                });
            } else {
                $.messager.alert("提示", "请选择要修改的行", "error");
            }
        }
    });


});


function fixWidth(percent) {
    return document.body.clientWidth * percent; //这里你可以自己做调整
}

//window.top["RELOAD_MY_DATAGRID"] = function () {
//    $("#dg_teacher").datagrid("reload");
//};

