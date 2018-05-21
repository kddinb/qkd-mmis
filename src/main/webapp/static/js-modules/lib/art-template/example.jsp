<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglibs.jsp" %>
<html>
<head>
    <title>artTemplate 简洁语法版</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
</head>
<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">artTemplate 简洁语法版</div>
        <div class="panel-body">
            引用简洁语法的引擎版本:template.js
            <%--<p>...</p>--%>
            <h2>表达式</h2>
            <pre>{{ 与 }} </pre>
            符号包裹起来的语句则为模板的逻辑表达式。
            <h3>输出表达式</h3>

            对内容编码输出：
            <pre>{{content}}</pre>
            不编码输出：
            <pre>{{#content}}</pre>
            编码可以防止数据中含有 HTML 字符串，避免引起 XSS 攻击。<br/>
            <h3>条件表达式</h3>
<pre>
{{if admin}}
    &lt;p&gt;>admin&lt;/p&gt;
{{else if code > 0}}
    &lt;p&gt;>master&lt;/p&gt;
{{else}}
    &lt;p&gt;>error!&lt;/p&gt;
{{/if}}
</pre>
            <h3>遍历表达式</h3>
            无论数组或者对象都可以用 each 进行遍历<br/>
<pre>
{{each list as value index}}
    &lt;li&gt;>{{index}} - {{value.user}}&lt;/li&gt;
{{/each}}
</pre>
            亦可以被简写：<br/>
<pre>
{{each list}}
    &lt;li&gt;>{{$index}} - {{$value.user}}&lt;/li&gt;
{{/each}}
</pre>
            <h3>模板包含表达式</h3>

            用于嵌入子模板。<br/>
<pre>
{{include 'template_name'}}
</pre>
            子模板默认共享当前数据，亦可以指定数据：<br/>
<pre>
{{include 'template_name' news_list}}
</pre>

            <h3>辅助方法</h3>
            使用template.helper(name, callback)注册公用辅助方法：<br/>
<pre>
template.helper('dateFormat', function (date, format) {
    // ..
    return value;
});
</pre>
            模板中使用的方式：<br/>
<pre>
{{time | dateFormat:'yyyy-MM-dd hh:mm:ss'}}
</pre>
            支持传入参数与嵌套使用：<br/>
<pre>
{{time | say:'cd' | ubb | link}}
</pre>
        </div>
    </div>


</div>

<script type="text/html">

</script>
</body>
</html>
