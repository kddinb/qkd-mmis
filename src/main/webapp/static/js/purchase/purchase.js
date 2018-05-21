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
        setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable');
    });

    function listData() {
        $('#zSearchArea').show();
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
            //queryParams: $("#searchForm").serializeObject(),
            onDblClickRow: function (rowIndex, row) {
                if(editRow != undefined){
                    console.log(row);
                    $("#tbDepartId").textbox("setValue",row.departId);
                    $("#tbMedId").textbox("setValue",row.nmae);
                    $("#tbTypeId").textbox("setValue",row.typeId);
                    $("#tbSupplierId").textbox("setValue",row.supplierId);
                }
            },
            onLoadSuccess: function () {
                //$(this).datagrid('selectRow',0);
            },
            url: ctx + "/storMed/getAllStorMed",
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
                //     $('#dg_storMed').datagrid('unselectRow', rowIndex);
                // }
            },

            onSelect:function (rowIndex,rowData) {
            }
        });
        $('#dg_purchase').datagrid({
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
            url: ctx + "/purchase/getPurchase",
            columns: [[
                {
                    field: 'departId', title: '库房', width: fixWidth(0.20),
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
                    field: 'status', title: '状态', width: fixWidth(0.20),
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
                    $('#dg_purchase').datagrid('unselectRow', rowIndex);
                }
            },

            onSelect:function (rowIndex,rowData) {
                $("#tbId").next().hide();
                if(rowData){
                    $('#purchaseForm').form('load',{
                        'id':rowData.id,
                        'departId':rowData.departId,
                        'serno':rowData.serno,
                        'medId':rowData.medId,
                        'typeId':rowData.typeId,
                        'amount':rowData.amount,
                        'userId':rowData.userId,
                        'supplierId':rowData.supplierId,
                        'status':rowData.status,
                        'applyDate':rowData.applyDate
                    });
                }
            }
        });

        $($('#dg_purchase').datagrid('getPager')).pagination({
            layout:['prev','manual','next','refresh']
        });
        $($('#dg_purchase').datagrid('getPager')).pagination('refresh');

        $('#tbSerno').textbox({
            width: 230,
            label: '单据号：',
            labelField: true,
            labelWidth:80
        });
        $('#tbDepartId').textbox({
            width: 230,
            label: '库房：',
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
        $('#tbSupplierId').textbox({
            width: 230,
            label: '生产厂家：',
            labelField: true,
            labelWidth:80,
            required:true
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

        disableOcx();
    }
    

    var editRow = undefined;
    var user = 'aa';

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
    function setButtonState(searchState, clearState, addState, editState, saveState, removeState, redoState) {
        $('#searchButton').linkbutton(searchState);
        $('#clearButton').linkbutton(clearState);
        $('#addButton').linkbutton(addState);
        $('#editButton').linkbutton(editState);
        $('#saveButton').linkbutton(saveState);
        $('#removeButton').linkbutton(removeState);
        $('#redoButton').linkbutton(redoState);
        $('#subButton').linkbutton(addState);
    }

    /**
     * 查询
     */
    $("#searchButton").click(function () {
        if ($('#searchButton').linkbutton('options').disabled == false) {
            var data = $("#searchForm").serializeObject();
            $('#dg_purchase').datagrid('load', data);
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
            $('#dg_purchase').datagrid('load', data);
        }
    });

    /**
     * 药品查询
     */
    $("#searchmButton").click(function () {
        var nmaedata = $("#scnmae").textbox("getValue");
        var data = {nmae: nmaedata};
        $('#dg_storMed').datagrid('load', data);
    });

    /**
     * 新增
     */
    $("#addButton").click(function () {
       // $('#zSearchArea').hide();
        setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable');
        $('#purchaseForm').form("clear");
        $.ajax({
            type: "POST",
            url: ctx + "/keyUtil/getSerno",
            dataType: "json",
            success: function (data) {
                $('#tbSerno').textbox("setValue", data);
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
                //$("#jssj").datetimebox("setValue",strDate);
            }
        });
    });

    /**
     * 修改
     */
    $("#editButton").click(function () {
        if ($('#editButton').linkbutton('options').disabled == false) {
            //修改时要获取选择到的行
            var rows = $('#dg_purchase').datagrid("getSelections");
            //如果只选择了一行则可以进行修改，否则不操作
            if (rows.length == 1) {
                var status = $('#tbStatus').textbox("getValue");
                if(status != '待提交' && status != '驳回'){
                    $.messager.alert("提示", "该记录已提交审批或审批通过", "error");
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
                setButtonState('disable', 'disable', 'disable', 'disable', 'enable', 'disable', 'enable');
            } else {
                $.messager.alert("提示", "请选择要修改的行", "error");
            }
        }
    });

    /**
     * 提交审批
     */
    $("#subButton").click(function () {
        if ($('#subButton').linkbutton('options').disabled == false) {
            var rows = $('#dg_purchase').datagrid("getSelections");
            var status = rows[0].status;
            if(status == '待审批' || status == '通过'){
                $.messager.alert("提示", "请记录已提交审批或审批通过", "error");
                return;
            }
            if(rows.length>0){
                $.messager.confirm("提示", "确认提交审批?", function (r) {
                    if(r){
                        rows[0].status = '待审批';
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
                                    $('#dg_purchase').datagrid('reload', searchData);
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
     * 取消
     */
    $('#redoButton').click(function () {
        if ($('#redoButton').linkbutton('options').disabled == false) {
            //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
            editRow = undefined;
            $('#dg_purchase').datagrid("rejectChanges");
            $('#dg_purchase').datagrid("unselectAll");
            setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable');
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
                $.messager.alert('提示信息', '申请数量必须大于0！', 'warning');
                return;
            }
            setAllinUsed();
            $('#tbStatus').textbox("setValue","待提交");
            $('#purchaseForm').form('submit', {
                url: ctx + "/purchase/savePurchase",
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
                        $('#dg_purchase').datagrid('reload', searchData);
                    }
                    else {
                        $.messager.alert('提示信息', '提交失败，请联系管理员！', 'warning');
                    }
                    disableOcx();
                    setButtonState('enable', 'enable', 'enable', 'enable', 'disable', 'enable', 'disable');
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
            var rows = $('#dg_purchase').datagrid("getSelections");
            //选择要删除的行
            var status = $('#tbStatus').textbox("getValue");
            if(status != '待提交' && status != '驳回'){
                $.messager.alert("提示", "该记录已提交审批或审批已通过", "error");
                return;
            }
            if (rows.length > 0) {
                $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                    if (r) {
                        var ids = rows[0];
                        $.ajax({
                            type: "POST",
                            url: ctx + "/purchase/removePurchase",
                            data: ids,
                            dataType: "json",
                            success: function (data) {
                                if (data.result == true) {
                                    message('', '成功', {'returnJson': data, 'type': 'success'});
                                    var searchData = $("#searchForm").serializeObject();
                                    $('#dg_purchase').datagrid('reload', searchData);
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

