function f2() {
    var y;
    var x = "x"+y;
    console.log(x);
    y = 'y';
}
f2();
function ft2() {
    var y;
    var x = "x" + y;
    console.log(x);
    y = 'y';
}

function de() {
    var x = 1,
    y=x+1,
        z,i,a;
}

x = 1;
function f() {
    console.log(x);
}
f();
console.log(x);



var x = 'xxx';
alert(x);
alert(window.x);
var old_alter = window.alert;
window.alert = function () {

};
// alter()失效了
window.alert(123);

// 恢复
window.alert = old_alter;
window.alert(453);

var subei = {};

// 定义全局变量
subei.name = 'subeiLY';
subei.add = function (a,b){
    return a+b;
}

function bbb() {
    for (var i = 0; i < 100; i++) {
        console.log(i);
    }
    console.log(i+1);
}

var PI='3.14';
console.log(PI);
PI='213';
console.log(PI);

const PI = '3.14';
console.log(PI);
PI = '123';
console.log(PI);