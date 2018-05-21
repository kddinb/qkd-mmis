requirejs(['serializeObject', 'art-template', 'moment'], function (_, template, moment) {
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            url: ctx + "/view/getloginName",
            dataType: "json",
            success: function (data) {
                user = data.result;
            }
        });
        listData();
        setButtonState('enable', 'enable', 'disable', 'disable', 'disable', 'disable', 'disable', 'enable','disable');
    });

    function listData() {
        $('#zSearchArea').show();
        $('#dg_pharmacyMed').datagrid({
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
            //queryParams: $("#searchForm").serializeObject(),
            onDblClickRow: function (rowIndex, row) {
                if(editRow != undefined){
                    console.log(row);
                    $("#tbDepartId").textbox("setValue",row.departId);
                    $("#tbMedId").textbox("setValue",row.nmae);
                    $("#tbTypeId").textbox("setValue",row.typeId);
                }
            },
            onLoadSuccess: function () {
                //$(this).datagrid('selectRow',0);
            },
            url: ctx + "/pharmacyMed/getHaveStockPharmacyMed",
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
                }
            ]],
            onAfterEdit: function (rowIndex, rowData, changes) {
                //editRow = undefined;
            },
            onClickRow: function (rowIndex, rowData) {
                //新增或修改时行不可选
                // if (editRow != undefined) {
                //     $('#dg_pharmacyMed').datagrid('unselectRow', rowIndex);
                // }
            },

            onSelect:function (rowIndex,rowData) {
            }
        });
        $('#dg_apply').datagrid({
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
            url: ctx + "/prescription/getPrescription",
            columns: [[
                {
                    field: 'serno', title: '单号', width: fixWidth(0.50),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'patient', title: '姓名', width: fixWidth(0.30),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                },
                {
                    field: 'medId', title: '药品', width: fixWidth(0.40),
                    editor: {
                        type: 'text',
                        options: {}
                    }
                }
            ]],
            onAfterEdit: function (rowIndex, rowData, changes) {
                editRow = undefined;
            },
            onClickRow: function (rowIndex, rowData) {
                //新增或修改时行不可选
                if (editRow != undefined) {
                    $('#dg_apply').datagrid('unselectRow', rowIndex);
                }
            },

            onSelect:function (rowIndex,rowData) {
                $("#tbId").next().hide();
                if(rowData){
                    $('#applyForm').form('load',{
                        'id':rowData.id,
                        'departId':rowData.departId,
                        'serno':rowData.serno,
                        'medId':rowData.medId,
                        'typeId':rowData.typeId,
                        'amount':rowData.amount,
                        'userId':rowData.userId,
                        'status':rowData.status,
                        'applyDate':rowData.applyDate,
                        'patient':rowData.patient
                    });
                }
            }
        });

        $($('#dg_apply').datagrid('getPager')).pagination({
            layout:['prev','manual','next','refresh']
        });
        $($('#dg_apply').datagrid('getPager')).pagination('refresh');

        $('#tbSerno').textbox({
            width: 230,
            label: '单据号：',
            labelField: true,
            labelWidth:80
        });
        $('#tbDepartId').textbox({
            width: 230,
            label: '药房：',
            labelField: true,
            labelWidth:80,
            required:true
        });
        $('#tbMedId').textbox({
            width: 230,
            label: '药品：',
            labelField: true,
            labelWidth:80,
            required:true
        });
        $('#tbTypeId').textbox({
            width: 230,
            label: '药品类型：',
            labelField: true,
            labelWidth:80,
            required:true
        });
        $('#tbAmount').textbox({
            width: 230,
            label: '数量：',
            labelField: true,
            labelWidth:80,
            required:true
        });
        $('#tbUserId').textbox({
            width: 230,
            label: '申请人员：',
            labelField: true,
            labelWidth:80
        });
        $('#tbStatus').textbox({
            width: 230,
            label: '审批状态：',
            labelField: true,
            labelWidth:80
        });
        $('#tbApplyDate').textbox({
            width: 230,
            label: '申请日期：',
            labelField: true,
            labelWidth:80
        });
        $('#tbPatient').textbox({
            width: 230,
            label: '患者姓名：',
            labelField: true,
            labelWidth:80,
            required:true
        });

        disableOcx();
    }
    

    var editRow = undefined;
    var user = 'aa';
    var serno = undefined;

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
        $("#tbPatient").textbox({disabled:false});
        $("#tbAmount").textbox({disabled:false});

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
    function setButtonState(searchState, clearState, addState, editState, saveState, removeState, redoState, begState,endState) {
        $('#begButton').linkbutton(begState);
        $('#endButton').linkbutton(endState);
        $('#redoEndButton').linkbutton(endState);

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
            $('#dg_apply').datagrid('load', data);
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
            $('#dg_apply').datagrid('load', data);
        }
    });

    /**
     * 药品查询
     */
    $("#searchmButton").click(function () {
        var nmaedata = $("#scnmae").textbox("getValue");
        var data = {nmae: nmaedata};
        $('#dg_pharmacyMed').datagrid('load', data);
    });

    /**
     * 开始发药
     */
    $("#begButton").click(function () {
        setButtonState('disable', 'disable', 'enable', 'enable', 'disable', 'enable', 'disable', 'disable','enable');
        $.ajax({
            type: "POST",
            url: ctx + "/keyUtil/getSerno",
            dataType: "json",
            success: function (data) {
                serno= data;
                console.log(serno);
            }
        });
    });

    /**
     * 取消发药
     */
    $("#redoEndButton").click(function () {
        $.messager.confirm("提示", "确定取消发药?", function (r) {
            if(r){
                setButtonState('enable', 'enable', 'disable', 'disable', 'disable', 'disable', 'disable', 'enable', 'disable');
                var savedata = {serno: serno, status: '作废'};
                $.ajax({
                    type: "POST",
                    url: ctx + "/prescription/updateStatusBySerno",
                    data: savedata,
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        if (data.result == true) {
                            message('', '成功', {'returnJson': data, 'type': 'success'});
                            var searchData = $("#searchForm").serializeObject();
                            $('#dg_apply').datagrid('reload', searchData);
                        } else {
                            message('', '未发药', {'returnJson': data, 'type': 'error'});
                        }
                    }
                });
                serno = undefined;
            }
        });
    });

    /**
     * 结束发药
     */
    $("#endButton").click(function () {
        $.messager.confirm("提示", "确定结束发药?", function (r) {
            if(r){
                setButtonState('enable', 'enable', 'disable', 'disable', 'disable', 'disable', 'disable', 'enable', 'disable');
                var savedata = {serno: serno, status: '已开药'};
                $.ajax({
                    type: "POST",
                    url: ctx + "/prescription/updateStatusBySerno",
                    data: savedata,
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        if (data.result == true) {
                            message('', '成功', {'returnJson': data, 'type': 'success'});
                            var searchData = $("#searchForm").serializeObject();
                            $('#dg_apply').datagrid('reload', searchData);
                        } else {
                            message('', '未发药', {'returnJson': data, 'type': 'error'});
                        }
                    }
                });
                serno = undefined;
            }
        });
    });

    /**
     * 新增
     */
    $("#addButton").click(function () {
       // $('#zSearchArea').hide();
        setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable','disable','disable');
        $('#applyForm').form("clear");
        $('#tbSerno').textbox("setValue", serno);
        var curr_time=new Date();
        var strDate=curr_time.getFullYear()+"-";
        strDate +=curr_time.getMonth()+1+"-";
        strDate +=curr_time.getDate();
        strDate +=" "+curr_time.getHours()+":";
        if(curr_time.getMinutes()<10){
            strDate +="0"
        }
        strDate +=curr_time.getMinutes()+":";
        if(curr_time.getSeconds()<10){
            strDate +="0"
        }
        strDate +=curr_time.getSeconds();
        $("#tbApplyDate").textbox("setValue",strDate);
        $("#tbStatus").textbox("setValue","待提交");
        $("#tbUserId").textbox("setValue",user);
        noDisableOcx();
        editRow = true;
    });

    /**
     * 修改
     */
    $("#editButton").click(function () {
        if ($('#editButton').linkbutton('options').disabled == false) {
            //修改时要获取选择到的行
            var rows = $('#dg_apply').datagrid("getSelections");
            //如果只选择了一行则可以进行修改，否则不操作
            if (rows.length == 1) {
                var status = $('#tbStatus').textbox("getValue");
                if(status != '待发药'){
                    $.messager.alert("提示", "该记录已完成", "error");
                    return;
                }
                var curr_time=new Date();
                var strDate=curr_time.getFullYear()+"-";
                strDate +=curr_time.getMonth()+1+"-";
                strDate +=curr_time.getDate();
                strDate +=" "+curr_time.getHours()+":";
                if(curr_time.getMinutes()<10){
                    strDate +="0"
                }
                strDate +=curr_time.getMinutes()+":";
                if(curr_time.getSeconds()<10){
                    strDate +="0"
                }
                strDate +=curr_time.getSeconds();
                $("#tbApplyDate").textbox("setValue",strDate);
                noDisableOcx();
                setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable','disable','disable');
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
            $('#dg_apply').datagrid("rejectChanges");
            $('#dg_apply').datagrid("unselectAll");
            setButtonState('disable', 'disable', 'enable', 'enable', 'disable', 'enable', 'disable', 'disable','enable');
            disableOcx();

        }
    });

    /**
     * 保存
     */
    $("#saveButton").click(function () {
        if ($('#saveButton').linkbutton('options').disabled == false) {
            var amount = $('#tbAmount').textbox("getValue");
            if(amount<0){
                $.messager.alert('提示信息', '发药数量必须大于0！', 'warning');
                return;
            }
            setAllinUsed();
            $('#tbStatus').textbox("setValue","待开药");
            $('#applyForm').form('submit', {
                url: ctx + "/prescription/savePrescription",
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
                        $('#dg_apply').datagrid('reload', searchData);
                    }
                    else {
                        $.messager.alert('提示信息', '提交失败，请联系管理员！', 'warning');
                    }
                    disableOcx();
                    setButtonState('disable', 'disable', 'enable', 'enable', 'disable', 'enable', 'disable', 'disable','enable');
                    editRow = undefined;
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
            var rows = $('#dg_apply').datagrid("getSelections");
            //选择要删除的行
            var status = $('#tbStatus').textbox("getValue");
            if(status != '待发药'){
                $.messager.alert("提示", "该记录已完成", "error");
                return;
            }
            if (rows.length > 0) {
                $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                    if (r) {
                        var ids = rows[0];
                        $.ajax({
                            type: "POST",
                            url: ctx + "/prescription/removePrescription",
                            data: ids,
                            dataType: "json",
                            success: function (data) {
                                if (data.result == true) {
                                    message('', '成功', {'returnJson': data, 'type': 'success'});
                                    var searchData = $("#searchForm").serializeObject();
                                    $('#dg_apply').datagrid('reload', searchData);
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

