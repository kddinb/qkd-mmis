requirejs(['jquery.validate.ex'], function (_) {

    var validator = $("#loginForm").validate({
        errorElement:'em',//默认的是label
        rules: {
            name: "required",
            pwd: "required"
        },
        messages: {//Key is the name of an element
            name: getValidationErrorHtml('请输入用户名'),//为了兼容bootstrap样式,改成这种方式
            pwd: getValidationErrorHtml('请输入密码')
        }
    });

    $("#submitButton").click(function () {
        if (validator.form()) {
            $.ajax({
                type: "POST",
                url: ctx + "/user/find",
                data: $("#loginForm").serialize(),
                dataType: "json",
                success: function (data) {
                    if(data.result == '1'){
                        location.href = ctx + "/view/gotoMain";
                    }else{
                        message('', '用户名或密码错误', {'returnJson':data,'type':'error'});
                    }
                }
            });
        } else {
            validator.focusInvalid();//focus 错误的输入框
        }
    });

});