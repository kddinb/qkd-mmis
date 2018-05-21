//@author jerry.pan
requirejs(['jquery', 'plupload'], function ($, _) {
    //上传组件
    var uploader = new plupload.Uploader({
        browse_button : 'selectImage',
        url: ctx + "/file/uploadAndLoadUrl",
        filters: {
            max_file_size: '1mb',
            mime_types: [
                {title: "Image files", extensions: "jpg,gif,png,bmp"}
            ]
        },
        init: {
            PostInit: function () {
            },
            //当文件添加到上传队列后触发
            FilesAdded: function (up, files) {
                uploader.start();
            },
            //当队列中的某一个文件上传完成后触发
            FileUploaded: function (uploader,file,responseObject) {
                var json = jQuery.parseJSON(responseObject.response);
                ajaxReturn(json,null,'上传图片失败',function(){
                    $("#image").val(json.object.id);
                    showLogo(json.object.url);
                });
            },
            Error: function (up, err) {
            }
        }
    });

    uploader.init();//在实例对象上调用init()方法进行初始化

    function showLogo(url){
        $("#showImage").html('<image style="max-width:120px;max-height:120px;" src="'+ctx+url+'" />');
    }

});

