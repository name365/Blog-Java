$(function(){
    $(".user-setting li").click(function(){
        $(".user-setting li").removeClass("active");
        $(this).addClass("active");
        var panelId = "#"+$(this).attr("name");
        $("#userPanel > div").css({'display':'none'});
        $(panelId).css({'display':'block'});

    });

    // 用户管理修改模态框
    $(".doModify").on("click", function() {
        _this = this; //this是事件源
        $("#myModal").modal("show");
    });

    $(".updateOne").on("click", function() {
        $("#myModal").modal("hide");
    });

    // 管理员管理 添加管理员模态框
    $("#doAddManger").on("click", function() {
        _this = this; //this是事件源
        $("#myMangerUser").modal("show");
    });

    $(".add-Manger").on("click", function() {
        $("#myMangerUser").modal("hide");
    });

    // 管理员管理 修改管理员模态框
    $(".doMangerModify").on("click", function() {
        _this = this; //this是事件源
        $("#myModal-Manger").modal("show");
    });

    $(".doMargerModal").on("click", function() {
        $("#myModal-Manger").modal("hide");
    });

    // 部门管理 添加部门模态框
    $("#doAddDept").on("click", function() {
        _this = this; //this是事件源
        $("#Dept").modal("show");
    });

    $(".addDept").on("click", function() {
        $("#Dept").modal("hide");
    });
    
    //部门管理,添加子部门模态框
    $(".doAddSonDept").on("click", function() {
    	_this = this; //this是事件源
    	$("#sonDept").modal("show");
    });
    
    $(".addSonDept").on("click", function() {
    	$("#sonDept").modal("hide");
    });
    
 // 部门管理 修改部门模态框
    $(".doModifyDept").on("click", function() {
        _this = this; //this是事件源
        $("#modifyDept").modal("show");
    });

    $(".modifyDept").on("click", function() {
        $("#modifyDept").modal("hide");
    });

    // 商品管理 修改商品
    $(".doProModify").on("click", function() {
        _this = this; //this是事件源
        $("#myProduct").modal("show");
    });

    $(".updatePro").on("click", function() {
        $("#myProduct").modal("hide");
    });

    // 商品管理 添加商品
    $("#doAddPro").on("click", function() {
        _this = this; //this是事件源
        $("#Product").modal("show");
    });

    $(".addProduct").on("click", function() {
        $("#Product").modal("hide");
    });

    // 商品类型管理 添加商品类型
    $("#doAddProTpye").on("click", function() {
        _this = this; //this是事件源
        $("#ProductType").modal("show");
    });

    $(".addProductType").on("click", function() {
        $("#ProductType").modal("hide");
    });

    // 商品类型管理 类型修改
    $(".doProTypeModify").on("click", function() {
        _this = this; //this是事件源
        $("#myProductType").modal("show");
    });

    $(".updateProType").on("click", function() {
        $("#myProductType").modal("hide");
    });

    // 显示隐藏用户查询
    $('#showusersearch').click(function(){
        $('.showusersearch').slideDown(500);
    });
    $('.upp').click(function(){
        $('.showusersearch').slideUp(500);
    });

    // 显示隐藏用户查询
    $('#showmargersearch').click(function(){
        $('.showmargersearch').slideDown(500);
    });
    $('.uppp').click(function(){
        $('.showmargersearch').slideUp(500);
    });

	$('#file').change(function() {
        $("#showImg").attr("src", window.URL.createObjectURL(this.files[0]));
    });


});

function openFileInput(){
	$('#file').click();
}
