requirejs(['serializeObject', 'art-template', 'moment'], function (_, template, moment) {
    $(document).ready(function () {
        listData();
    });

    function listData() {
        $('#tbBackReason').textarea({
            width: 600,
            height: 500,
            label: '驳回理由：',
            labelField: true,
            labelWidth:80,
            required: true
        });
        }
    var scdepart = parent.$('#dg_approve').datagrid("getSelections");
    console.log(scdepart);

    $("#saveButton").click(function () {
        var backReason = $('#tbBackReason').textarea("getValue");
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
    });

});


function fixWidth(percent) {
    return document.body.clientWidth * percent; //这里你可以自己做调整
}

//window.top["RELOAD_MY_DATAGRID"] = function () {
//    $("#dg_teacher").datagrid("reload");
//};

