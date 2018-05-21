requirejs(['serializeObject', 'art-template', 'moment'], function (_, template, moment) {
    $(document).ready(function () {
        listData();
        setButtonState('enable', 'enable','enable');
        $("#scDepart").next().hide();
    });

    function listData() {
        var scdepart = parent.$('#scDepart').textbox("getValue");
        if(scdepart == '西药房'){
            scdepart = '西药库';
        }else{
            scdepart = '中药库';
        }
        $('#scDepart').textbox("setValue", scdepart);
        $('#dg_storMed').datagrid({
            method: 'post',
            striped: true,
            singleSelect: false,
            pageSize: 10,
            border: false,
            fitColumns: true,
            rownumbers: true,
            fit: true,
           // toolbar: '#tb',
            pagination: true,
            queryParams: $("#searchForm").serializeObject(),
            onDblClickRow: function (rowIndex, row) {
            },
            url: ctx + "/storMed/getStorMedFilt",
            columns: [[
                {field:'ck',checkbox:true},
                {
                    field: 'departId', title: '库房', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'nmae', title: '药品名', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'code', title: '药品编码', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'gnameId', title: '通用名', width: fixWidth(0.50),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'pharId', title: '药理', width: fixWidth(0.50),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'typeId', title: '药品类型', width: fixWidth(0.40),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                }
                // }, {
                //     field: 'origin', title: '原产地', width: fixWidth(0.30),
                //     editor: {
                //         type: 'text',
                //         options: {}
                //     }
                // }, {
                //     field: 'efid', title: '商品电子码', width: fixWidth(0.40),
                //     editor: {
                //         type: 'text',
                //         options: {}
                //     }
                // },
                // {
                //     field: 'tradePrice', title: '批发价', width: fixWidth(0.30),
                //     editor: {
                //         type: 'text',
                //         options: {}
                //     }
                // }, {
                //     field: 'retailPrice', title: '零售价', width: fixWidth(0.30),
                //     editor: {
                //         type: 'text',
                //         options: {}
                //     }
                // },
                // {
                //     field: 'packUnit', title: '包装单位', width: fixWidth(0.30),
                //     editor: {
                //         type: 'text',
                //         options: {}
                //     }
                // }, {
                //     field: 'packNum', title: '包装数量', width: fixWidth(0.30),
                //     editor: {
                //         type: 'numberspinner',
                //         options: {}
                //     }
                // },
                // {
                //     field: 'wmUsed', title: '门诊可用', width: fixWidth(0.30),
                //     editor: {
                //         type: 'combobox',
                //         options: {
                //             panelHeight: 'auto',
                //             valueField: 'value',
                //             textField: 'text',
                //             data: [{
                //                 value: '可用',
                //                 text: '可用'
                //             }, {
                //                 value: '不可用',
                //                 text: '不可用'
                //             }]
                //         }
                //     }
                // }, {
                //     field: 'cmUsed', title: '住院可用', width: fixWidth(0.30),
                //     editor: {
                //         type: 'combobox',
                //         options: {
                //             panelHeight: 'auto',
                //             valueField: 'value',
                //             textField: 'text',
                //             data: [{
                //                 value: '可用',
                //                 text: '可用'
                //             }, {
                //                 value: '不可用',
                //                 text: '不可用'
                //             }]
                //         }
                //     }
                // },
                // {
                //     field: 'remark', title: '备注', width: fixWidth(0.80),
                //     editor: {
                //         type: 'text',
                //         options: {}
                //     }
                // }
            ]],
            onAfterEdit: function (rowIndex, rowData, changes) {
                // $.ajax({
                //     type: "POST",
                //     url: ctx + "/dicMed/saveDicMed",
                //     data: rowData,
                //     dataType: "json",
                //     success: function (data) {
                //         if (data.result == true) {
                //             message('', '成功', {'returnJson': data, 'type': 'success'});
                //             var searchData = $("#searchForm").serializeObject();
                //             $('#dg_storMed').datagrid('reload', searchData);
                //         } else {
                //             message('', '失败', {'returnJson': data, 'type': 'error'});
                //         }
                //     }
                // });
                editRow = undefined;
            },
            onClickRow: function (rowIndex, rowData) {
                //新增或修改时行不可选
                if (editRow != undefined) {
                    $('#dg_storMed').datagrid('unselectRow', rowIndex);
                }
            },

        });
    }
    

    var editRow = undefined;

    /**
     * 设置按钮状态
     */
    function setButtonState(searchState, clearState,saveState) {
        $('#searchButton').linkbutton(searchState);
        $('#clearButton').linkbutton(clearState);
        $('#saveButton').linkbutton(saveState);
    }

    /**
     * 查询
     */
    $("#searchButton").click(function () {
        if ($('#searchButton').linkbutton('options').disabled == false) {
            var data = $("#searchForm").serializeObject();
            $('#dg_storMed').datagrid('load', data);
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
            $('#dg_storMed').datagrid('load', data);
        }
    });

    /**
     * 新增保存
     */
    $("#saveButton").click(function () {
        var rows = $('#dg_storMed').datagrid("getSelections");
        console.log(rows);
        if(rows != undefined && rows.length>0){
            var len = rows.length;
            for(var i=0;i<len;i++){
                rows[i].id = null;
                rows[i].stock = 0;
                if(rows[i].departId == '西药库'){
                    rows[i].departId = '西药房';
                }else{
                    rows[i].departId = '中药房';
                }
            }
            var rows =  JSON.stringify(rows);
            $.ajax({
                type: "POST",
                url: ctx + "/pharmacyMed/savePharmacyMed",
                data: rows,
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    console.log(data);
                    if (data.result == true) {
                        message('', '成功', {'returnJson': data, 'type': 'success'});
                        var searchData = $("#searchForm").serializeObject();
                        $('#dg_storMed').datagrid('reload',searchData);
                    } else {
                        message('', '失败', {'returnJson': data, 'type': 'error'});
                    }
                }
            });
        }else{
            $.messager.alert("提示", "未选择行", "error");
        }
    });


});


function fixWidth(percent) {
    return document.body.clientWidth * percent; //这里你可以自己做调整
}

//window.top["RELOAD_MY_DATAGRID"] = function () {
//    $("#dg_teacher").datagrid("reload");
//};

