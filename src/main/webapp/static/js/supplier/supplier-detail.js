requirejs(['serializeObject', 'art-template', 'moment'], function (_, template, moment) {
    $(document).ready(function () {
        listData();
        disableOcx();
    });

    function listData() {
        $('#tbName').textbox({
            width: 340,
            label: '名称：',
            labelField: true,
            labelWidth:80
        });
        $('#tbCode').textbox({
            width: 340,
            label: '编码：',
            labelField: true,
            labelWidth:80
        });
        $('#tbContact').textbox({
            width: 340,
            label: '联系人：',
            labelField: true,
            labelWidth:80
        });
        $('#tbTelephone').textbox({
            width: 340,
            label: '联系电话：',
            labelField: true,
            labelWidth:80
        });
        $('#tbAddr').textbox({
            width: 340,
            label: '联系地址：',
            labelField: true,
            labelWidth:80
        });
        $('#tbBank').textbox({
            width: 340,
            label: '开户银行：',
            labelField: true,
            labelWidth:80
        });
        $('#tbBankAccount').textbox({
            width: 340,
            label: '银行账号：',
            labelField: true,
            labelWidth:80
        });

        var scdepart = parent.$('#cmbSupplierId').combobox("getValue");
        var param = {name:scdepart};
        $.getJSON(ctx + "/supplier/getSupplierRow",param,function(data){
            console.log(data);
            $('#tbName').textbox("setValue", data[0].name);
            $('#tbCode').textbox("setValue", data[0].code);
            $('#tbContact').textbox("setValue", data[0].contact);
            $('#tbTelephone').textbox("setValue", data[0].telephone);
            $('#tbAddr').textbox("setValue", data[0].addr);
            $('#tbBank').textbox("setValue", data[0].bank);
            $('#tbBankAccount').textbox("setValue", data[0].bankAccount);
        });
        }


    function disableOcx() {
        $("#tbName").textbox({disabled:true});
        $("#tbCode").textbox({disabled:true});
        $("#tbContact").textbox({disabled:true});
        $("#tbTelephone").textbox({disabled:true});
        $("#tbAddr").textbox({disabled:true});
        $("#tbBank").textbox({disabled:true});
        $("#tbBankAccount").textbox({disabled:true});
    }
    /**
     * 查询
     */
    $("#searchButton").click(function () {
        if ($('#searchButton').linkbutton('options').disabled == false) {
            var data = $("#searchForm").serializeObject();
            $('#dg_supplier').datagrid('load', data);
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


});


function fixWidth(percent) {
    return document.body.clientWidth * percent; //这里你可以自己做调整
}

//window.top["RELOAD_MY_DATAGRID"] = function () {
//    $("#dg_teacher").datagrid("reload");
//};

