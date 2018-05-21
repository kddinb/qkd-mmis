/*顶部导航栏使用的*/
//$(document).ready(function(){
//$('[data-submenu]').submenupicker();//激活多级菜单
//$("[data-toggle='popover']").popover();//激活悬浮框
//});

function show() {
    return $('#collapsedShowDiv').html();
}

function showNav() {
    $(".navbar-fixed-top").slideDown();
    $('#cc').layout('expand', 'north');
}
function closeNav() {
    $(".navbar-fixed-top").hide();
    $('#cc').layout('collapse', 'north');
}

requirejs(['art-template'], function (template) {
    $(document).ready(function () {
        loadMenu();
    });

    /**
     * 获取菜单
     */
    function loadMenu() {
        $.ajax({
            type: "post",
            url: ctx + "/view/menu",
            dataType: "json",
            success: function (result) {
                var html = template("menuTemplate", result);
                // console.log(result);
                $('#menuLi').html(html);
            },
            error: function () {
                messagerFail('获取菜单失败');
            }
        });
    }

});


