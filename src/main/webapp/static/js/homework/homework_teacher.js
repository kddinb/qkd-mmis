requirejs(['serializeObject', 'art-template', 'moment'], function (_, template, moment) {
    $(document).ready(function () {
        listData();
    });

    function listData() {
        $('#dg_teacher').datagrid({
            method: 'post',
            striped: true,
            singleSelect: true,
            pageSize: 20,
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
            url: ctx + "/study/homework/findPageByTid",
            columns: [[
                {field: 'title', title: '标题', width: fixWidth(0.20)},
                {field: 'deadlineDate', title: '截至日期', formatter: formatterDate, width: fixWidth(0.20)},
                {field: 'publishDate', title: '发布日期', formatter: formatterDate, width: fixWidth(0.20)},
                {field: 'remark', title: '备注', width: fixWidth(0.30)},
                {
                    field: 'operate', title: '操作', width: fixWidth(0.10),
                    formatter: function (val, row, index) {
                        var html = template("operateTemplate", row);
                        return html;
                    }
                }
            ]]
        });
    }

    /**
     * 查询
     */
    $("#searchButton").click(function () {
        var data = $("#searchForm").serializeObject();
        $('#dg_teacher').datagrid('load', data);
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
        $('#searchForm').form('reset');
        var data = $("#searchForm").serializeObject();
        $('#dg_teacher').datagrid('load', data);
    });

    /**
     *查看已提交的作业
     */
    $(document).on('click', ".js-check", function () {
        var homeworkId = $(this).data('id');
        window.parent.addTab1('查看作业', ctx + '/study/homework/page/check?homeworkId=' + homeworkId);
    });

    function formatterDate(val) {
        if(val){
            return moment(val).format(DATE_FORMAT.DATE_FORMAT_YMD);
        }
        return "";
    }
});


function fixWidth(percent) {
    return document.body.clientWidth * percent; //这里你可以自己做调整
}

//window.top["RELOAD_MY_DATAGRID"] = function () {
//    $("#dg_teacher").datagrid("reload");
//};

