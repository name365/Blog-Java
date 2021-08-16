function tick() {
    var today=new Date();
    var month=today.getMonth()+1;<!--getMonth显示当前月份-1，所以要+1才能显示当前月份-->
    var year, date, hours, minutes, seconds;
    var intHours, intMinutes, intSeconds;
    var week=new Array()
    // 显示几天为星期几
    week[0]="星期天 ";
    week[1]="星期一 ";
    week[2]="星期二 ";
    week[3]="星期三 ";
    week[4]="星期四 ";
    week[5]="星期五 ";
    week[6]="星期六 ";
    intHours = today.getHours();
    intMinutes = today.getMinutes();
    intSeconds = today.getSeconds();
    year=today.getFullYear();
    date=today.getDate();
    var time;
    if (intHours == 0) {
    hours = "00:";
}
    else if (intHours < 10) {
    hours = "0" + intHours+":";
}
    else {
    hours = intHours + ":";
}
    if (intMinutes < 10) {
    minutes = "0"+intMinutes+":";
}
    else {
    minutes = intMinutes+":";
}
    if (intSeconds < 10) {
    seconds = "0"+intSeconds+" ";
}
    else {
    seconds = intSeconds+" ";
}
//根据不同的时间显示不同的问候。
//下面采用自己总结的测试纠正法对time进行赋值
    time="午夜好";
    if(today.getHours()<=22)  time="晚上好";
    if(today.getHours()<=19)  time="傍晚好";
    if(today.getHours()<=17)  time="下午好";
    if(today.getHours()<=14)  time="中午好";
    if(today.getHours()<=12)  time="上午好";
    if(today.getHours()<=8)   time="早上好";
    if(today.getHours()<=5)   time="凌晨好";
    timeString="今天是："+year+"年"+month+"月"+date+"日  "+hours+minutes+seconds+week[today.getDay()]+time;
    Clock.innerHTML = timeString;
    window.setTimeout("tick();", 1000);
}
window.onload = tick;
