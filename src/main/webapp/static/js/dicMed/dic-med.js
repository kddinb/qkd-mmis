requirejs(['serializeObject', 'art-template', 'moment'], function (_, template, moment) {
    $(document).ready(function () {
        $(function() {
            $('body').append('<div id="myWindow" class="easyui-dialog" closed="true"></div>');
        });
        listData();
        setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable','enable');
    });

    function listData() {
        $('#dg_dicMed').datagrid({
            method: 'post',
            striped: true,
            singleSelect: true,
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
            onLoadSuccess: function () {
                $(this).datagrid('selectRow',0);
            },
            url: ctx + "/dicMed/getDicMed",
            columns: [[
                {
                    field: 'name', title: '药品名', width: fixWidth(0.30),
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
                },
                {
                    field: 'supplierId', title: '供应商', width: fixWidth(0.8),
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
                //             $('#dg_dicMed').datagrid('reload', searchData);
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
                    $('#dg_dicMed').datagrid('unselectRow', rowIndex);
                }
            },

            onSelect:function (rowIndex,rowData) {
                $("#tbId").next().hide();
                $('#dicMedForm').form('load',{
                    'id':rowData.id,
                    'name':rowData.name,
                    'code':rowData.code,
                    'gnameId':rowData.gnameId,
                    'pharId':rowData.pharId,
                    'typeId':rowData.typeId,
                    'supplierId':rowData.supplierId,
                    'origin':rowData.origin,
                    'efid':rowData.efid,
                    'tradePrice':rowData.tradePrice,
                    'retailPrice':rowData.retailPrice,
                    'packUnit':rowData.packUnit,
                    'packNum':rowData.packNum,
                    'wmUsed':rowData.wmUsed,
                    'cmUsed':rowData.cmUsed,
                    'remark':rowData.remark
                });
                disableOcx();
            }
        });
        $('#tbName').textbox({
            width: 230,
            label: '名称：',
            labelField: true,
            labelWidth:80,
            required:true
        });
        $('#tbCode').textbox({
            width: 230,
            label: '编码：',
            labelField: true,
            labelWidth:80,
            required:true
        });
        $('#cmbGnameId').combobox({
            width: 230,
            label: '通用名：',
            labelField: true,
            labelWidth:80,
            required:true,
            valueField: 'name',
            textField: 'name',
            panelHeight: 'auto',
            url: ctx + "/dicGname/getDicGnameRow",
            onChange:function (e) {
                var name = {name:e};
                $.ajax({
                    type: "POST",
                    url: ctx + "/dicGname/getDicGnameRow",
                    data: name,
                    dataType: "json",
                    success: function (data) {
                        if(data.length == 1){
                            $('#tbPharId').textbox("setValue", data[0].pharId);
                            $('#tbTypeId').textbox("setValue", data[0].typeId);
                        }
                    }
                });
            }
        });
        $('#tbPharId').textbox({
            width: 230,
            label: '药理：',
            labelField: true,
            labelWidth:80
        });
        $('#tbTypeId').textbox({
            width: 230,
            label: '药品类型：',
            labelField: true,
            labelWidth:80
        });
        $('#cmbSupplierId').combobox({
            width: 230,
            label: '生产厂家：',
            labelField: true,
            labelWidth:80,
            valueField: 'name',
            textField: 'name',
            panelHeight: 'auto',
            url: ctx + "/supplier/getSupplierRow"
        });
        $('#tbOrigin').textbox({
            width: 230,
            label: '原厂地：',
            labelField: true,
            labelWidth:80
        });
        $('#tbEfid').textbox({
            width: 230,
            label: '商品电子码：',
            labelField: true,
            labelWidth:80
        });
        $('#spTradePrice').numberspinner({
            width: 230,
            label: '批发价：',
            labelField: true,
            labelWidth:80,
            min: 0,
            max:10000,
            precision:2
        });
        $('#spRetailPrice').numberspinner({
            width: 230,
            label: '零售价：',
            labelField: true,
            labelWidth:80,
            min: 0,
            max:10000,
            precision:2
        });
        $('#cmbPackUnit').combobox({
            width: 230,
            label: '包装单位：',
            labelField: true,
            labelWidth:80,
            valueField: 'value',
            textField: 'text',
            panelHeight: 'auto',
            data: [{
                value: '盒',
                text: '盒'
            }, {
                value: '罐',
                text: '罐'
            },{
                value:'瓶',
                text:'瓶'
            },{
                value:'板',
                text:'板'
            },{
                value:'克',
                text:'克'
            }]
        });
        $('#spPackNum').numberspinner({
            width: 230,
            label: '包装数量：',
            labelField: true,
            labelWidth:80,
            min: 0,
            max:1000
        });
        $('#cmbwmUsed').combobox({
            width: 230,
            label: '西药库可用：',
            labelField: true,
            labelWidth:80,
            valueField: 'value',
            textField: 'text',
            panelHeight: 'auto',
            required:true,
            data: [{
                value: '可用',
                text: '可用'
            }, {
                value: '不可用',
                text: '不可用'
            }]
        });
        $('#cmbcmUsed').combobox({
            width: 230,
            label: '中药库可用：',
            labelField: true,
            labelWidth:80,
            valueField: 'value',
            textField: 'text',
            panelHeight: 'auto',
            required:true,
            data: [{
                value: '可用',
                text: '可用'
            }, {
                value: '不可用',
                text: '不可用'
            }]
        });
        $('#tbRemark').textbox({
            width: 230,
            label: '备注：',
            labelField: true,
            labelWidth:80
        });
    }
    

    var editRow = undefined;

    function disableOcx() {
        var form = document.forms[1];
        for ( var i = 0; i < form.length; i++) {
            var element = form.elements[i];
            element.disabled = "true";
        }
    }

    function noDisableOcx() {
        var form = document.forms[1];
        for ( var i = 0; i < form.length; i++) {
            var element = form.elements[i];
            element.disabled = false;
        }
    }

    /**
     * 设置按钮状态
     */
    function setButtonState(searchState, clearState, addState, editState, saveState, removeState, redoState, supplierState) {
        $('#searchButton').linkbutton(searchState);
        $('#clearButton').linkbutton(clearState);
        $('#addButton').linkbutton(addState);
        $('#editButton').linkbutton(editState);
        $('#saveButton').linkbutton(saveState);
        $('#removeButton').linkbutton(removeState);
        $('#redoButton').linkbutton(redoState);
        $('#supplierButton').linkbutton(supplierState);
    }

    /**
     * 查询
     */
    $("#searchButton").click(function () {
        if ($('#searchButton').linkbutton('options').disabled == false) {
            var data = $("#searchForm").serializeObject();
            $('#dg_dicMed').datagrid('load', data);
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
            $('#dg_dicMed').datagrid('load', data);
        }
    });

    function showMyWindow(title, href, width, height, modal, minimizable,
                          maximizable) {
        $('#myWindow').window(
            {
                title : title,
                width : width === undefined ? 600 : width,
                height : height === undefined ? 400 : height,
                content : '<iframe scrolling="yes" frameborder="0"  src="'
                + href
                + '" style="width:100%;height:98%;"></iframe>',
                modal : modal === undefined ? true : modal,
                minimizable : minimizable === undefined ? false
                    : minimizable,
                maximizable : maximizable === undefined ? false
                    : maximizable,
                shadow : false,
                cache : false,
                closed : false,
                collapsible : false,
                resizable : true,
                queryParams: { code: '10001' },
                loadingMessage : '正在加载数据，请稍等片刻......'
            });
    }

    /***
     * 弹出供应商信息页面
     */
    $("#supplierButton").click(function () {
        if ($('#supplierButton').linkbutton('options').disabled == false) {
            showMyWindow("供应商信息",
                'goSupplierDetail',
                800, 300);
        }
    });


    function assgVal(){
        var fatherText = $("#fatherText").val();
        $('#son').contents().find("#sonId").val(fatherText);
    }

    /**
     * 新增
     */
    $("#addButton").click(function () {
        if ($('#addButton').linkbutton('options').disabled == false) {
            setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable','disable');
            if (editRow != undefined) {
                datagrid.datagrid("endEdit", editRow);
            }
            if (editRow == undefined) {
                $('#dg_dicMed').datagrid("insertRow", {
                    index: 0,
                    row: {}
                });
                $('#dg_dicMed').datagrid("unselectAll");
                editRow = 0;
                $('#dg_dicMed').datagrid("beginEdit", editRow);
                $('#dicMedForm').form("clear");
                noDisableOcx();
            }
        }
    });

    /**
     * 修改
     */
    $("#editButton").click(function () {
        if ($('#editButton').linkbutton('options').disabled == false) {
            //修改时要获取选择到的行
            var rows = $('#dg_dicMed').datagrid("getSelections");
            //如果只选择了一行则可以进行修改，否则不操作
            if (rows.length == 1) {
                noDisableOcx();
                setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable','disable');
                //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
                // if (editRow != undefined) {
                //     $('#dg_dicMed').datagrid("endEdit", editRow);
                // }
                // //当无编辑行时
                // if (editRow == undefined) {
                //     //获取到当前选择行的下标
                //     var index = $('#dg_dicMed').datagrid("getRowIndex", rows[0]);
                //     //开启编辑
                //     $('#dg_dicMed').datagrid("beginEdit", index);
                //     //把当前开启编辑的行赋值给全局变量editRow
                //     editRow = index;
                //     //当开启了当前选择行的编辑状态之后，
                //     //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                //     $('#dg_dicMed').datagrid("unselectAll");
                // }
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
            $('#dg_dicMed').datagrid("rejectChanges");
            $('#dg_dicMed').datagrid("unselectAll");
            setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable','enable');
        }
    });

    /**
     * 保存
     */
    $("#saveButton").click(function () {
        if ($('#saveButton').linkbutton('options').disabled == false) {
            $('#dicMedForm').form('submit', {
                url: ctx + "/dicMed/saveDicMed",
                onSubmit:function(){
                    var isValid = $(this).form('validate');//验证表单中的一些控件的值是否填写正确，比如某些文本框中的内容必须是数字
                    if (!isValid) {
                    }
                    return isValid;
                },
                success: function (data) {
                    if (data == '{"result":true}') {
                        message('', '成功', {'returnJson': data, 'type': 'success'});
                        var searchData = $("#searchForm").serializeObject();
                        $('#dg_dicMed').datagrid('reload', searchData);
                    }
                    else {
                        $.messager.alert('提示信息', '提交失败，请联系管理员！', 'warning');
                    }
                    $('#dg_dicMed').datagrid("endEdit", editRow);
                    setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable','enable');
                }
            });
        }
    });

    /**
     * 删除
     */
    $("#removeButton").click(function () {
        if ($('#removeButton').linkbutton('options').disabled == false) {
            //删除时先获取选择行
            var rows = $('#dg_dicMed').datagrid("getSelections");
            //选择要删除的行
            if (rows.length > 0) {
                $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                    if (r) {
                        var ids = rows[0];
                        $.ajax({
                            type: "POST",
                            url: ctx + "/dicMed/removeDicMed",
                            data: ids,
                            dataType: "json",
                            success: function (data) {
                                if (data.result == true) {
                                    message('', '成功', {'returnJson': data, 'type': 'success'});
                                    var searchData = $("#searchForm").serializeObject();
                                    $('#dg_dicMed').datagrid('reload', searchData);
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

