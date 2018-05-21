require.config({
    baseUrl: ctx + '/static/js-modules/',
    paths: {
        'jquery': 'lib/jquery/jquery-1.9.1.min',
        'jquery.validate': 'lib/jquery-validation/1.13.1/jquery.validate',//表单验证
        'jquery.validate.ex': 'lib/jquery-validation/1.13.1/localization/messages_zh.ex',//表单验证
        'serializeObject': 'lib/jquery-serialize-object/2.5.0/jquery.serialize-object.min',//将form表单转化成Javascript object
        'moment': 'lib/moment/2.13.0/moment.min',//日期格式化
        'art-template': 'lib/art-template/3.0.0/template',//html模板
        'kindeditor': 'lib/kindeditor-4.1.7/kindeditor-min',//富文本编辑器
        'kindeditor.ex': 'lib/kindeditor-4.1.7/lang/zh_CN',//富文本编辑器 中文语言包
        'plupload':'lib/plupload/2.1.8/plupload.full.min',//上传文件组件
        'datetimepicker':'lib/datepicker/js/bootstrap-datetimepicker',//日期插件
        'datetimepicker-ex':'lib/datepicker/js/locales/bootstrap-datetimepicker.zh-CN',//日期插件
        'toastr': 'lib/toastr/2.1.2/toastr.min',//侧边栏提醒
    },
    map: {
        '*': {
            'css': 'lib/require-css/0.1.8/css.min'//RequireJS加载样式文件插件
        }
    },
    shim: {
        'kindeditor.ex': {
            deps: [
                'css!lib/kindeditor-4.1.7/themes/default/default.css',
                'kindeditor'
            ]
        },
        'jquery-validate-ex': {
            deps: ['jquery-validate']
        },
        'datetimepicker-ex':{
            deps: [
                'css!lib/datepicker/css/bootstrap-datetimepicker.css',
                'datetimepicker'
            ]
        },
        'toastr': ['css!lib/toastr/2.1.2/toastr.min.css']
    }
});
