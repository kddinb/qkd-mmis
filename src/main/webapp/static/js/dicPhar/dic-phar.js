requirejs(['serializeObject', 'art-template', 'moment'], function (_, template, moment) {
    $(document).ready(function () {
        listData();
        setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable');
    });

    function listData() {
        $('#dg_dicphar').treegrid({
            method: 'post',
            striped: true,
            singleSelect: true,
            pageSize: 100,
            border: false,
            fitColumns: true,
            rownumbers: true,
            animate: true,
            idField: 'id',
            treeField: 'name',
            collapsible: true,
            fit: true,
            toolbar: '#tb',
            queryParams: $("#searchForm").serializeObject(),
            // onDblClickRow: function (rowIndex, row) {
            // },
            // onLoadSuccess: function () {
            // },
            url: ctx + "/dicPhar/getDicPhar",
            columns: [[
                {
                    field: 'code', title: '药理编码', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'name', title: '药理名称', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'parentId', title: '父级编码', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                }
            ]],
            onAfterEdit: function (rowData) {
                if(editId != undefined){
                    var obj_node = $('#dg_dicphar').treegrid('getSelected');
                    console.log(rowData);
                    rowData.id = editId;
                    if(rowData.code == null){
                        rowData.code = obj_node.code;
                    }
                    if(rowData.name == null){
                        rowData.name = obj_node.name;
                    }
                    if(rowData.parentNode = null){
                        rowData.parentNode = obj_node.parentNode;
                    }
                }
                $.ajax({
                    type: "POST",
                    url: ctx + "/dicPhar/saveDicPhar",
                    data: rowData,
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        if (data.result == true) {
                            message('', '成功', {'returnJson': data, 'type': 'success'});
                            var searchData = $("#searchForm").serializeObject();
                            $('#dg_dicphar').treegrid('reload', searchData);
                        } else {
                            message('', '失败', {'returnJson': data, 'type': 'error'});
                        }
                    }
                });
                editId = undefined;
                isAdd = false;
            },
            onClickRow: function (rowIndex) {
                //新增或修改时行不可选
                var obj_node = $('#dg_dicphar').treegrid('getSelected');
                if (editId != undefined) {
                    $('#dg_dicphar').treegrid('unselect', obj_node.id);
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
            $('#dg_dicphar').treegrid('load', data);
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
            $('#dg_dicphar').treegrid('load', data);
        }
    });

    /**
     * 新增
     */
    $("#addButton").click(function () {
        if ($('#addButton').linkbutton('options').disabled == false) {
            setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable');
            var obj_node = $('#dg_dicphar').treegrid('getSelected');
            if (obj_node) {
                $('#dg_dicphar').treegrid('append', {
                    parent: obj_node.id,//treegrid 父id 必须指定
                    data: [{
                        code:'',
                        name:'',
                        parentId:'',
                    }]
                });
                $('#dg_dicphar').treegrid('beginEdit');
                isAdd = true;
            }
            // if (editRow != undefined) {
            //     $('#dg_dicphar').treegrid("endEdit", editRow);
            // }
            // if (editRow == undefined) {
            //     $('#dg_dicphar').treegrid("insertRow", {
            //         index: 0,
            //         row: {}
            //     });
            //     $('#dg_dicphar').treegrid("unselectAll");
            //     editRow = 0;
            //     $('#dg_dicphar').treegrid("beginEdit", editRow);
            // }
        }
    });

    var editId = undefined;
    var isAdd = false;
    /**
     * 修改
     */
    $("#editButton").click(function () {
        if ($('#editButton').linkbutton('options').disabled == false) {
            //修改时要获取选择到的行
            var obj_node = $('#dg_dicphar').treegrid('getSelected');
            //如果只选择了一行则可以进行修改，否则不操作
            if (obj_node) {
                setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable');

                $('#dg_dicphar').treegrid('beginEdit', obj_node.id);
                $('#dg_dicphar').treegrid('unselectAll');
                editId = obj_node.id;
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
            var obj_node = $('#dg_dicphar').treegrid('getSelected');
            if(editId == undefined){
                $('#dg_dicphar').treegrid("remove",obj_node.id);
                $('#dg_dicphar').treegrid("reload");
                isAdd = false;
            }else{
                $('#dg_dicphar').treegrid("cancelEdit",editId);
                editId = undefined;
            }
            $('#dg_dicphar').treegrid("unselectAll");
            setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable');
        }
    });

    /**
     * 保存
     */
    $("#saveButton").click(function () {
        if ($('#saveButton').linkbutton('options').disabled == false) {
           // var obj_node = $('#dg_dicphar').treegrid('getSelected');
            if(editId != undefined){
                $('#dg_dicphar').treegrid("endEdit",editId);
            }
            $('#dg_dicphar').treegrid("endEdit");
            setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable');
        }
    });

    /**
     * 删除
     */
    $("#removeButton").click(function () {
        if ($('#removeButton').linkbutton('options').disabled == false) {
            //删除时先获取选择行
            var rows = $('#dg_dicphar').treegrid("getSelections");
            //选择要删除的行
            if (rows.length > 0) {
                $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                    if (r) {
                        var ids = rows[0];
                        $.ajax({
                            type: "POST",
                            url: ctx + "/dicPhar/removeDicPhar",
                            data: ids,
                            dataType: "json",
                            success: function (data) {
                                if (data.result == true) {
                                    message('', '成功', {'returnJson': data, 'type': 'success'});
                                    var searchData = $("#searchForm").serializeObject();
                                    $('#dg_dicphar').treegrid('reload', searchData);
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

