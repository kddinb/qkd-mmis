﻿requirejs(['serializeObject', 'art-template', 'moment'], function (_, template, moment) {
    $(document).ready(function () {
        listData();
        setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable');
    });

    function listData() {
        $('#dg_dicGname').datagrid({
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
            url: ctx + "/dicGname/getDicGname",
            columns: [[
                {
                    field: 'name', title: '通用名', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'code', title: '通用名编码', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'ename', title: '英文名', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'pharId', title: '药理', width: fixWidth(0.30),
                    editor: {
                        type: 'combobox',
                        options: {
                            valueField: 'name',
                            textField: 'name',
                            method: 'post',
                            url: ctx + "/dicPhar/getDicPharRow"
                        }
                    }
                },
                {
                    field: 'typeId', title: '药品类型', width: fixWidth(0.40),
                    editor: {
                        type: 'combobox',
                        options: {
                            valueField: 'name',
                            textField: 'name',
                            method: 'post',
                            url: ctx + "/dicType/getDicTypeRow"
                        }
                    }
                }
            ]],
            onAfterEdit: function (rowIndex, rowData, changes) {
                $.ajax({
                    type: "POST",
                    url: ctx + "/dicGname/saveDicGname",
                    data: rowData,
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        if (data.result == true) {
                            message('', '成功', {'returnJson': data, 'type': 'success'});
                            var searchData = $("#searchForm").serializeObject();
                            $('#dg_dicGname').datagrid('reload', searchData);
                        } else {
                            message('', '失败', {'returnJson': data, 'type': 'error'});
                        }
                    }
                });
                editRow = undefined;
            },
            onClickRow: function (rowIndex, rowData) {
                //新增或修改时行不可选
                if (editRow != undefined) {
                    $('#dg_dicGname').datagrid('unselectRow', rowIndex);
                }
            }
        });
    }

    var editRow = undefined;

    /**
     * 设置按钮状态
     */
    function setButtonState(searchState, clearState, addState, editState, saveState, removeState, redoState) {
        $('#searchButton').linkbutton(searchState);
        $('#clearButton').linkbutton(clearState);
        $('#addButton').linkbutton(addState);
        $('#editButton').linkbutton(editState);
        $('#saveButton').linkbutton(saveState);
        $('#removeButton').linkbutton(removeState);
        $('#redoButton').linkbutton(redoState);
    }

    /**
     * 查询
     */
    $("#searchButton").click(function () {
        if ($('#searchButton').linkbutton('options').disabled == false) {
            var data = $("#searchForm").serializeObject();
            $('#dg_dicGname').datagrid('load', data);
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
            $('#dg_dicGname').datagrid('load', data);
        }
    });

    /**
     * 新增
     */
    $("#addButton").click(function () {
        if ($('#addButton').linkbutton('options').disabled == false) {
            setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable');
            if (editRow != undefined) {
                datagrid.datagrid("endEdit", editRow);
            }
            if (editRow == undefined) {
                $('#dg_dicGname').datagrid("insertRow", {
                    index: 0,
                    row: {}
                });
                $('#dg_dicGname').datagrid("unselectAll");
                editRow = 0;
                $('#dg_dicGname').datagrid("beginEdit", editRow);
            }
        }
    });

    /**
     * 修改
     */
    $("#editButton").click(function () {
        if ($('#editButton').linkbutton('options').disabled == false) {
            //修改时要获取选择到的行
            var rows = $('#dg_dicGname').datagrid("getSelections");
            //如果只选择了一行则可以进行修改，否则不操作
            if (rows.length == 1) {
                setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable');
                //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
                if (editRow != undefined) {
                    $('#dg_dicGname').datagrid("endEdit", editRow);
                }
                //当无编辑行时
                if (editRow == undefined) {
                    //获取到当前选择行的下标
                    var index = $('#dg_dicGname').datagrid("getRowIndex", rows[0]);
                    //开启编辑
                    $('#dg_dicGname').datagrid("beginEdit", index);
                    //把当前开启编辑的行赋值给全局变量editRow
                    editRow = index;
                    //当开启了当前选择行的编辑状态之后，
                    //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                    $('#dg_dicGname').datagrid("unselectAll");
                }
            } else {
                $.messager.alert("提示", "请选择要修改的行", "error");
            }
        }
    });

    /**
     * 取消
     */
    $('#redoButton').click(function () {
        if ($('#redoButton').linkbutton('options').disabled == false) {
            //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
            editRow = undefined;
            $('#dg_dicGname').datagrid("rejectChanges");
            $('#dg_dicGname').datagrid("unselectAll");
            setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable');
        }
    });

    /**
     * 保存
     */
    $("#saveButton").click(function () {
        if ($('#saveButton').linkbutton('options').disabled == false) {
            $('#dg_dicGname').datagrid("endEdit", editRow);
            setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable');
        }
    });

    /**
     * 删除
     */
    $("#removeButton").click(function () {
        if ($('#removeButton').linkbutton('options').disabled == false) {
            //删除时先获取选择行
            var rows = $('#dg_dicGname').datagrid("getSelections");
            //选择要删除的行
            if (rows.length > 0) {
                $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                    if (r) {
                        var ids = rows[0];
                        $.ajax({
                            type: "POST",
                            url: ctx + "/dicGname/removeSupplier",
                            data: ids,
                            dataType: "json",
                            success: function (data) {
                                if (data.result == true) {
                                    message('', '成功', {'returnJson': data, 'type': 'success'});
                                    var searchData = $("#searchForm").serializeObject();
                                    $('#dg_dicGname').datagrid('reload', searchData);
                                } else {
                                    message('', '失败', {'returnJson': data, 'type': 'error'});
                                }
                            }
                        });
                    }
                });
            }
            else {
                $.messager.alert("提示", "请选择要删除的行", "error");
            }
        }
    })


});


function fixWidth(percent) {
    return document.body.clientWidth * percent; //这里你可以自己做调整
}

//window.top["RELOAD_MY_DATAGRID"] = function () {
//    $("#dg_teacher").datagrid("reload");
//};

