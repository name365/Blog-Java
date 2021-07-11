var subeily = {
    name:'哇哈哈',
    birth:2000,
    // 方法
    age:function () {
        // 今年-出生年
        var now = new Date().getFullYear();
        return now-this.birth;
    }
}
// 属性
subeily.name;
// 方法，注意带()
subeily.age();

function getAge() {
    // 今年-出生年
    var now = new Date().getFullYear();
    return now -this.birth;
}
var subei = {
    name:'可乐',
    birth: 2002,
    age:getAge
}

subei.age();
getAge();

var xiaosan = {
    name:'xiaosan',
    birth:2001,
    age:getAge
}

getAge.apply(xiaosan,[]);

