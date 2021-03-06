﻿requirejs(['serializeObject', 'art-template', 'moment'], function (_, template, moment) {
    $(document).ready(function () {
        listData();
        setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable','enable');
        $("#scDepart").next().hide();
    });

    function listData() {
        $('#dg_storMed').datagrid({
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
            rowStyler: function (index, row) {
                if(row.stock == 0){
                    return 'background-color:#FFCC99;';
                } else if(row.stock <= 100){
                    return 'background-color:#CCFF99;';
                }
            },
            url: ctx + "/storMed/getStorMed",
            columns: [[
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
                },
                {
                    field: 'stock', title: '库存', width: fixWidth(0.40),
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

            onSelect:function (rowIndex,rowData) {
                $("#tbId").next().hide();
                if(rowData){
                    $('#storMedForm').form('load',{
                        'id':rowData.id,
                        'departId':rowData.departId,
                        'name':rowData.nmae,
                        'code':rowData.code,
                        'gnameId':rowData.gnameId,
                        'pharId':rowData.pharId,
                        'typeId':rowData.typeId,
                        'efid':rowData.efid,
                        'retailPrice':rowData.retailPrice,
                        'packUnit':rowData.packUnit,
                        'packNum':rowData.packNum,
                        'remark':rowData.remark,
                        'stock':rowData.stock,
                        'inUsed':rowData.inUsed,
                        'outUsed':rowData.outUsed
                    });
                }
                disableOcx();
            }
        });
        $('#tbName').textbox({
            width: 230,
            label: '名称：',
            labelField: true,
            labelWidth:80
        });
        $('#tbDepartId').textbox({
            width: 230,
            label: '部门：',
            labelField: true,
            labelWidth:80
        });
        $('#tbCode').textbox({
            width: 230,
            label: '编码：',
            labelField: true,
            labelWidth:80
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
        $('#tbEfid').textbox({
            width: 300,
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
        $('#cmbInUsed').combobox({
            width: 230,
            label: '门诊可用：',
            labelField: true,
            labelWidth:80,
            valueField: 'value',
            textField: 'text',
            panelHeight: 'auto',
            data: [{
                value: '可用',
                text: '可用'
            }, {
                value: '不可用',
                text: '不可用'
            }]
        });
        $('#cmbOutUsed').combobox({
            width: 230,
            label: '住院可用：',
            labelField: true,
            labelWidth:80,
            valueField: 'value',
            textField: 'text',
            panelHeight: 'auto',
            data: [{
                value: '可用',
                text: '可用'
            }, {
                value: '不可用',
                text: '不可用'
            }]
        });
        $('#tbRemark').textbox({
            width: 400,
            label: '备注：',
            labelField: true,
            labelWidth:80
        });
        $('#tbStock').textbox({
            width: 230,
            label: '库存：',
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
        // var form = document.forms[1];
        // for ( var i = 0; i < form.length; i++) {
        //     var element = form.elements[i];
        //     element.disabled = false;
        // }
        var inused = $('#cmbInUsed').combobox("getValue");
        var outused = $('#cmbOutUsed').combobox("getValue");
        var price = $('#spRetailPrice').numberspinner("getValue");

        $("#tbRemark").textbox({disabled:false});
        $('#spRetailPrice').numberspinner({disabled:false});
        $("#cmbInUsed").combobox({disabled:false});
        $("#cmbOutUsed").combobox({disabled:false});

        $('#cmbInUsed').combobox("setValue", inused);
        $('#cmbOutUsed').combobox("setValue", outused);
        $('#spRetailPrice').numberspinner("setValue",price);
    }

    function setAllinUsed(){
        var form = document.forms[1];
        for ( var i = 0; i < form.length; i++) {
            var element = form.elements[i];
            element.disabled = false;
        }
    }

    /**
     * 设置按钮状态
     */
    function setButtonState(searchState, clearState, addState, editState, saveState, removeState, redoState, switchState) {
        $('#searchButton').linkbutton(searchState);
        $('#clearButton').linkbutton(clearState);
        $('#syncButton').linkbutton(addState);
        $('#editButton').linkbutton(editState);
        $('#saveButton').linkbutton(saveState);
        $('#removeButton').linkbutton(removeState);
        $('#redoButton').linkbutton(redoState);
        $('#switchButton').linkbutton(switchState);
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
            var scdepart = $('#scDepart').textbox("getValue");
            $('#searchForm').form('reset');
            $('#scDepart').textbox("setValue", scdepart);
            var data = $("#searchForm").serializeObject();
            $('#dg_storMed').datagrid('load', data);
        }
    });

    /**
     * 同步
     */
    $("#syncButton").click(function () {
        $.messager.confirm("提示", "确定同步药品目录?", function (r) {
            if (r) {
                $.ajax({
                    type: "POST",
                    url: ctx + "/storMed/synDicMed",
                    data:$("#searchForm").serializeObject(),
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        var refreshdata = $("#searchForm").serializeObject();
                        $('#dg_storMed').datagrid('load',refreshdata);
                    }
                });
            }
        });
    });

    /**
     * 切换药库
     */
    $("#switchButton").click(function () {
        $.messager.confirm("提示", "确定切换药库?", function (r) {
            if (r) {
                var scdepart = $('#scDepart').textbox("getValue");
                if(scdepart == "中药库"){
                    $('#scDepart').textbox("setValue", "西药库");
                }else{
                    $('#scDepart').textbox("setValue", "中药库");
                }
                var scdata = $("#searchForm").serializeObject();
                $('#dg_storMed').datagrid('reload',scdata);
            }
        });
    });

    /**
     * 修改
     */
    $("#editButton").click(function () {
        if ($('#editButton').linkbutton('options').disabled == false) {
            //修改时要获取选择到的行
            var rows = $('#dg_storMed').datagrid("getSelections");
            //如果只选择了一行则可以进行修改，否则不操作
            if (rows.length == 1) {
                noDisableOcx();
                setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable','disable');
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
            $('#dg_storMed').datagrid("rejectChanges");
            $('#dg_storMed').datagrid("unselectAll");
            setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable','enable');
        }
    });

    /**
     * 保存
     */
    $("#saveButton").click(function () {
        if ($('#saveButton').linkbutton('options').disabled == false) {
            setAllinUsed();
            $('#storMedForm').form('submit', {
                url: ctx + "/storMed/saveStorMed",
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
                        $('#dg_storMed').datagrid('reload', searchData);
                    }
                    else {
                        $.messager.alert('提示信息', '提交失败，请联系管理员！', 'warning');
                    }
                    $('#dg_storMed').datagrid("endEdit", editRow);
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
            var rows = $('#dg_storMed').datagrid("getSelections");
            //选择要删除的行
            var stock = $('#tbStock').textbox("getValue");
            if(stock != '0'){
                $.messager.alert("提示", "该药品库存大于0，不能删除", "error");
                return;
            }
            if (rows.length > 0) {
                $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                    if (r) {
                        var ids = rows[0];
                        $.ajax({
                            type: "POST",
                            url: ctx + "/storMed/removeStorMed",
                            data: ids,
                            dataType: "json",
                            success: function (data) {
                                if (data.result == true) {
                                    message('', '成功', {'returnJson': data, 'type': 'success'});
                                    var searchData = $("#searchForm").serializeObject();
                                    $('#dg_storMed').datagrid('reload', searchData);
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

