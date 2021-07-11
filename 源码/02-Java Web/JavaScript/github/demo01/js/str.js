function num() {
    var x = 1;

    // 内部函数可以访问外部函数的成员，反之则不行
    function num2(){
        var y = x + 1;  // 2
        console.log(y);
    }


    var z = y + 1;  // Uncaught ReferenceError: y is not defined
}

function num3(){

}