<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--Header navigation-->
<link href="${ctx}/static/css/nav/bootstrap-submenu.css" rel="stylesheet">
<link href="${ctx}/static/css/nav/nav.css" rel="stylesheet">
<div id="collapsedShowDiv" style="display: none;">
    <span class="pull-right glyphicon glyphicon-fullscreen" onclick="showNav()">&nbsp;</span>
    <span class="pull-right "><span class="glyphicon glyphicon-user"></span>${username}&nbsp;&nbsp;</span>
    <span class="pull-left ">&nbsp;<span class="glyphicon glyphicon-th-list"></span>&nbsp;当前位置:</span>
    <span class="pull-left ">&nbsp;主菜单&nbsp;&nbsp;/&nbsp;</span>
</div>
<div data-options="region:'north',border:false,split:true,hideCollapsedContent:false,
            collapsedContent:show(),expandMode:null,hideExpandTool:true"
     class="north-box" id="navDiv1">
    <!--navdiv-->
    <!--header nav start-->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation"
         id="nav1">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span>
                <span class="icon-bar"></span><span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><i class="glyphicon glyphicon-apple"></i>药事管理系统</a>
        </div>
        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-1">
            <!--navbar left start-->
            <ul class="nav navbar-nav" id="menuLi">
            </ul>
            <!--navbar right start-->
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="glyphicon glyphicon-user"></i>${username}<strong class="caret"></strong>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${ctx}/user/logout"><i class="glyphicon glyphicon-off"></i>退出</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" onclick="closeNav()">
                        <i class="glyphicon glyphicon-resize-small"></i>&nbsp;&nbsp;
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<script type="text/html" id="menuTemplate">
    {{each}}
        <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"
               {{if $value.url}}
               onclick="addTab1('{{$value.name}}','${ctx}{{$value.url}}','{{$value.icon}}')">
                {{else}}
                    >
                {{/if}}
                <i class="glyphicon {{$value.class}}"></i>{{$value.name}}
            </a>
            {{if $value.child}}
            <ul class="dropdown-menu">
            {{each $value.child as subMenu}}
            <%--<ul class="dropdown-menu">--%>
                <li>
                    <a href="javascript:;"
                       {{if subMenu.url}}
                       onclick="addTab1('{{subMenu.name}}','${ctx}{{subMenu.url}}','{{subMenu.icon}}')">
                        {{else}}
                        >
                        {{/if}}
                        <i class="glyphicon {{subMenu.class}}" style="margin-right: 10px;"></i>{{subMenu.name}}
                    </a>
                </li>
            <%--</ul>--%>
            {{/each}}
            </ul>
            {{/if}}
        </li>
    {{/each}}
</script>
<!--Header navigation js-->
<%--<script type="text/javascript" src="${ctx}/static/js/header/bootstrap-submenu.js"></script>--%>
<script type="text/javascript" src="${ctx}/static/js/header/nav.js"></script>
