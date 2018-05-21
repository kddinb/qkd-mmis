/**
 * 返回码
 */
var RESULT_STATUSCODE = {
    SUCCESS: 1,
    FAIL: 99999
};

/**
 * 日期格式
 */
var DATE_FORMAT = {
    DATE_FORMAT_YM: "YYYY-MM",
    DATE_FORMAT_YMD: "YYYY-MM-DD",
    DATE_FORMAT_YMDHMS: "YYYY-MM-DD hh:mm:ss"
};

/**
 * 用户类型: 0表示学生，1表示老师
 */
var USER_TYPE = {
    STUDENT: "0",
    TEACHER: "1"
};

/**
 * 页面类型
 */
var PAGE_TYPE = {
    ADD: 'add',
    VIEW: 'view',
    EDIT: 'edit'
};


/**
 * ajax返回处理
 */
function ajaxReturn(result, successFun, failFun) {
    if (result && result.statusCode == RESULT_STATUSCODE.SUCCESS) {
        if (successFun) {
            successFun();
        }
    } else {
        if (failFun) {
            failFun();
        }
    }
}

/**
 * ajax返回处理
 */
function ajaxReturnWithMsg(result, successFun, failFun, successMsg, failMsg) {
    if (result && result.statusCode == RESULT_STATUSCODE.SUCCESS) {
        if (successFun) {
            successFun();
        }
        if (successMsg) {
            messagerSuccess(successMsg);
        }
    } else {
        if (failFun) {
            failFun();
        }
        if (result && result.statusCode != RESULT_STATUSCODE.SUCCESS
            && result.statusCode != RESULT_STATUSCODE.FAIL) {
            failMsg = result.message;
        }
        if (failMsg) {
            messagerFail(failMsg);
        }
    }
}

/**
 * 成功时的提示
 */
function messagerSuccess(msg) {
    $.messager.show({
        title: '提示',
        msg: msg,
        timeout: 2000,
        showType: 'slide'
    });
}

/**
 * 失败时的提示
 */
function messagerFail(msg) {
    $.messager.show({
        title: '提示',
        msg: '<p style="color: red;">' + msg + '</p>',
        timeout: 10000,
        showType: 'slide'
    });
}

/**
 * 一般提示
 */
function messagerShow(title, msg) {
    $.messager.show({
        title: title,
        msg: msg,
        timeout: 0,//不自动关闭
        showType: 'slide'
    });
}


/**
 * 默认提示框(自定义直接调用 toastr.info() 方法)
 * @param title
 * @param content
 * @param options
 *      returnJson 后台返回的对象
 *      onShown 打开后调用函数
 *      onClose 关闭后调用函数
 *      type 类型：success、info、warning、error
 *      timeOut 单位毫秒 默认5000
 */
function message(title, content, options) {
    var thisTitle = title ? title : '';
    var thisContent = content ? content : '';
    var thisOptions = options ? options : {};
    var returnJson = thisOptions.returnJson;
    if (returnJson && returnJson.statusCode && returnJson.statusCode != RESULT_STATUSCODE.SUCCESS
        && returnJson.statusCode != RESULT_STATUSCODE.FAIL && returnJson.message) {
        if (thisContent) {
            thisContent += "：";
        }
        thisContent += returnJson.message;
    }
    requirejs(["toastr"], function (toastr) {
        var toastrOptions = {
            //"closeButton" : true,
            "newestOnTop": false, //默认 true
            "progressBar": true,
            //"timeOut": "5000", //默认5秒
            "timeOut": thisOptions.timeOut ? thisOptions.timeOut : 2000,
            onShown: function () {
                if (thisOptions.onShown) {
                    thisOptions.onShown();
                }
            },
            onHidden: function () {
                if (thisOptions.onClose) {
                    thisOptions.onClose();
                }
            }
        };
        var type = thisOptions.type ? thisOptions.type : 'info';
        if (type == 'success') {
            toastr.success(thisTitle, thisContent, toastrOptions);
        } else if (type == 'info') {
            toastr.info(thisTitle, thisContent, toastrOptions);
        } else if (type == 'warning') {
            toastr.warning(thisTitle, thisContent, toastrOptions);
        } else if (type == 'error') {
            toastr.error(thisTitle, thisContent, toastrOptions);
        }
    });
}

function messageError(content) {
    message('', content, {'type':'error'});
}

/**
 * 添加、修改、查看页面加载时
 * @param pageType 页面类型
 * @param selector 选择器，一般是Form表单
 * @param inSelector 是否只对选择器中的子元素操作
 */
function onEditPageLoad(pageType, selector, inSelector) {
    if (pageType && pageType == PAGE_TYPE.VIEW) {
        if (selector) {
            selector.find('input').attr('readonly', true);
            selector.find('.easyui-textbox').textbox('readonly', true);
            selector.find('.easyui-datebox').datebox('readonly', true);
            selector.find('.easyui-combobox').combobox('readonly', true);
            selector.find('input[type="checkbox"]').click(function () {
                return false;
            });
        }
        if (selector && inSelector) {
            selector.find('.js-addShow').hide();
            selector.find('.js-editShow').hide();
            selector.find('.js-viewShow').show();
        } else {
            $('.js-addShow').hide();
            $('.js-editShow').hide();
            $('.js-viewShow').show();
        }
    } else if (pageType && pageType == PAGE_TYPE.EDIT) {
        if (selector) {
            selector.find('input').attr('readonly', false);
            selector.find('.easyui-textbox').textbox('readonly', false);
            selector.find('.easyui-datebox').datebox('readonly', false);
            selector.find('.easyui-combobox').combobox('readonly', false);
        }
        if (selector && inSelector) {
            selector.find('.js-addShow').hide();
            selector.find('.js-viewShow').hide();
            selector.find('.js-editShow').show();
        } else {
            $('.js-addShow').hide();
            $('.js-viewShow').hide();
            $('.js-editShow').show();
        }
    } else if (pageType && pageType == PAGE_TYPE.ADD) {
        if (selector) {
            selector.find('input').attr('readonly', false);
            selector.find('.easyui-textbox').textbox('readonly', false);
            selector.find('.easyui-datebox').datebox('readonly', false);
            selector.find('.easyui-combobox').combobox('readonly', false);
        }
        if (selector && inSelector) {
            selector.find('.js-editShow').hide();
            selector.find('.js-viewShow').hide();
            selector.find('.js-addShow').show();
        } else {
            $('.js-editShow').hide();
            $('.js-viewShow').hide();
            $('.js-addShow').show();
        }
    }
}

/**
 * 验证警告的HTML
 */
function getValidationErrorHtml(msg) {
    var errorHtml = '<div class="alert alert-danger" style="margin: 0;padding-bottom: 0;">' +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>' + msg +
        '</div>';
    return errorHtml;
}


