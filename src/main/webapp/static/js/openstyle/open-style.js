$(function () {
    $('#tt').tabs({
        onClose: function (title, index) {
            if (index == 0) {
                index += 1;
            }
            $('#tt').tabs('select', index - 1);
        }
    });
    $("[data-toggle='tooltip']").tooltip();
});

var index = 0;
function addTab(title, url, icon) {
    index++;
    var tabId = title + '_tabId_' + index;
    var name = url;
    var s = url.indexOf('?');
    if (s != -1) {
        name = url.substring(0, s);
    }
    var name = url.substring(0, url.indexOf("?"));
    var content = '<iframe scrolling="no" frameborder="0" name="' + name + '" id="' + tabId + '" src="' + url + '"></iframe>';
    $('#tt').tabs('add', {
        title: title,
        content: content,
        closable: true,
        iconCls: icon
    });
}

function addTab1(title, url, icon) {
    index++;
    var tabId = title + '_tabId_' + index;
    var name = url;
    var s = url.indexOf('?');
    if (s != -1) {
        name = url.substring(0, s);
    }
    if ($("iframe[src='" + url + "']").html() != null) {
        var oldTitle = $("iframe[src='" + url + "']:first").attr('id');
        var i = oldTitle.substring(0, oldTitle.indexOf("_tabId_"));
        $('#tt').tabs('select', i);
    } else {
        var content = '<iframe scrolling="auto" frameborder="0" style="height:100%;margin-bottom:-4px;" name="' + name + '" id="' + tabId + '" src="' + url + '"></iframe>';
        $('#tt').tabs('add', {
            title: title,
            content: content,
            closable: true,
            iconCls: icon
        });
    }
}

function addTab2(title, url, icon) {
    index++;
    var tabId = title + '_tabId_' + index;
    var name = url;
    var s = url.indexOf('?');
    if (s != -1) {
        name = url.substring(0, s);
    }
    if ($("iframe[name='" + name + "']").html() != null) {
        var oldTitle = $("iframe[name='" + name + "']:first").attr('id');

        var i = oldTitle.substring(0, oldTitle.indexOf("_tabId_"));
        $('#tt').tabs('select', i);
    } else {

        var content = '<iframe scrolling="auto" frameborder="0" name="' + name + '" id="' + tabId + '" src="' + url + '"></iframe>';
        $('#tt').tabs('add', {
            title: title,
            content: content,
            closable: true,
            iconCls: icon
        });
    }
}

function removeTab() {
    var tab = $('#tt').tabs('getSelected');
    if (tab) {
        var index = $('#tt').tabs('getTabIndex', tab);
        $('#tt').tabs('close', index);
    }
}

function addModel(title, url) {
    $("#myModalLabel").html(title);
    $("#modelform").attr("src", url);
    $('#myModal').modal('show');
}

function reloadTabGrid(title) {
    if ($("#tt").tabs('exists', title)) {
        window.top.RELOAD_MY_DATAGRID.call();
    }
}

function reloadTab(title) {
    if ($("#tt").tabs('exists', title)) {
        var tab = $("#tt").tabs('getTab', title);
        $("#tt").tabs('update', {
            tab: tab,
            options: {}
        });
    }
}
