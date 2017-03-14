/**
 * Created by Xin09 on 2017/3/10.
 */
$(function () {
    $('#sign').click(function() {
        $.ajax({
            url: "/sign",
            type: "post",
            data: "",
            dataType:"json",
            success:function (data){
                if(data.success){
                    layer.msg('签到成功！');
                    window.location.href="/center";
                }else {
                    layer.msg('签到失败，您今天已经签到过了o');
                }
            }
        })
    })
})