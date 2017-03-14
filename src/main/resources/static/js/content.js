/**
 * Created by Xin09 on 2017/3/7.
 */
$(document).ready(function (){

    $('.M-box3').pagination({
        pageCount:$('#total').text(),
        current:$('#curr').text(),
        coping:true,
        homePage:'首页',
        endPage:'末页',
        prevContent:'上页',
        nextContent:'下页',
        callback:function(api){
            window.location.href="/test?page="+api.getCurrent();
        }
    });
})