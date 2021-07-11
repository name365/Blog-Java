var now = new Date();
now.getFullYear();  // 年
now.getMonth(); // 月
now.getDate();  // 日
now.getDay();   // 星期
now.getHours(); // 时
now.getMinutes();   // 分
now.getSeconds();   // 秒

now.getTime();  // 时间戳
console.log(new Date(1625895623770));    // 时间戳转时间

var admin = {
    name:"admin",
    age:31,
    sex:'M'
}

// 对象转化为JSON字符
var jsonAdmin = JSON.stringify(admin);

// JSON字符转化为对象，参数为 JSON字符
var obj = JSON.parse('{"name":"subei","age":"22","sex":"W"}');


