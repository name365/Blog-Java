$(function(){
    //用户名密码登陆和短信登陆的切换
    $('#btn-sms-back').click(function(){
        $('#login-account').css('display','none');
        $('#login-sms').css('display','block');
    });
    $('#btn-account-back').click(function(){
        $('#login-sms').css('display','none');
        $('#login-account').css('display','block');
    });
    
});