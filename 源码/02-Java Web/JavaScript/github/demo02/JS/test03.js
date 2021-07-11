var Student = {
    name:"subeiLY",
    age:20,
    run:function () {
        console.log(this.name + " run....");
    }
};
var xiaoming = {
    name:"xiaoming",
};

var bird = {
    fly:function (){
        console.log(this.name + " fly....")
    }
}

// 原型对象
// xiaoming.__proto__ = Student;
xiaoming.__proto__ = bird;

