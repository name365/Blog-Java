alert('hello,world!');

var abs = function(x){
    console.log("x=>"+x);
    for (var i = 0; i < arguments.length; i++) {
        console.log(arguments[i]);
    }
    if(x>=0){
        return x;
    }else{
        return -x;
    }
}

