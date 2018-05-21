<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js-modules/lib/html5shiv/3.7.3/html5shiv.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js-modules/lib/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- requirejs -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js-modules/requirejs/2.1.11/require.js"></script>
<!-- 引入require_config.js前 需要先声明ctx -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js-modules/require_config.js" ></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js-modules/lib/jquery/jquery-1.9.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js-modules/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js-modules/easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js-modules/easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common/common.js"></script>
<!--
Electron不能加载requirejs的解决办法：
方式一：适用于在网页的js中不使用 Node.js 和 Electron APIs。
把webPreferences的属性nodeIntegration设置为false；
let win = new BrowserWindow({
webPreferences: {
nodeIntegration:false
}
})

方式二：适用于在网页的js中还要使用 Node.js 和 Electron APIs。（更适合我们项目）
在调用require.js之前插入
<script>
window.nodeRequire = require;
delete window.require;
delete window.exports;
delete window.module;
</script>
参考开源：官方解决方案 https://electron.atom.io/docs/faq/
-->
