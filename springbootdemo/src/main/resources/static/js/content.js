// var $parentNode = window.parent.document;

// function $childNode(name) {
//     return window.frames[name]
// }

// // tooltips
// $('.tooltip-demo').tooltip({
//     selector: "[data-toggle=tooltip]",
//     container: "body"
// });

// // 使用animation.css修改Bootstrap Modal
// $('.modal').appendTo("body");

// $("[data-toggle=popover]").popover();

//var baseUrl = "http://localhost:9600";
// var baseUrl = "http://"+ (document.location.hostname || '192.168.205.122') +":9600";
var baseUrl = document.location.protocol+"//"+ (document.location.hostname || 'localhost') +":"+document.location.port;
var baseUrl = document.location.protocol+"//"+ (document.location.hostname || 'localhost') +":" + 8080;
console.log(baseUrl);
//var baseUrl = 'http://192.168.205.122:9600';
//var baseUrl = 'http://10.35.80.234:9600';
//var baseUrl = 'http://192.168.1.102:9600';
var currUserName = '';
//判断当前页面是否在iframe中
if (top == this) {
    // var gohome = '<div class="gohome"><a class="animated bounceInUp" href="index.html?v=4.0" title="返回首页"><i class="fa fa-home"></i></a></div>';
    // $('body').append(gohome);
}

// debugger;
var urlVal = window.location.href.split('?');
var urlVal2 = urlVal[1] ? urlVal[1]:'';
var urlValArr = urlVal2.split('=');
var tokenPos = urlValArr.indexOf('authorizon');
if(tokenPos > -1){
    var authorizon = urlValArr[tokenPos + 1];
    if(window.localStorage){
        localStorage.setItem('token',authorizon);
    }
    $.cookie('token',authorizon);

}
console.log('authorizon==',authorizon);
if(window.localStorage){
    var cookieToken = $.cookie('token');
    cookieToken =  cookieToken == 'null' ? JSON.parse(cookieToken) : cookieToken ? cookieToken : null;
    var loginLicense = localStorage.getItem('token') ? localStorage.getItem('token') : cookieToken;
    console.log('loginLicense==',loginLicense);
}else{
    var cookieToken = $.cookie('token');
    cookieToken =  cookieToken == 'null' ? JSON.parse(cookieToken) : cookieToken ? cookieToken : null;
    var loginLicense = cookieToken;
}
if(!loginLicense && window.location.href.indexOf('login') == -1){
    window.location.href = './login.html';
    // window.location.href = 'file:///D:/aiPlatform/AI20191025/aiplatform/%E4%BA%AC%E9%9A%86%E7%94%B5%E5%8E%82/powerPlant/hAdmin/login.html'
}
/*
var loginLicense = sessionStorage.loginLicense;
console.log('loginLicense==',loginLicense)
if(loginLicense != 'ok' && window.location.href.indexOf('login') == -1){
    window.location.href = '/login.html';
    // window.location.href = 'file:///D:/aiPlatform/AI20191025/aiplatform/%E4%BA%AC%E9%9A%86%E7%94%B5%E5%8E%82/powerPlant/hAdmin/login.html'
}
*/
//animation.css
function animationHover(element, animation) {
    element = $(element);
    element.hover(
        function () {
            element.addClass('animated ' + animation);
        },
        function () {
            //动画完成之前移除class
            window.setTimeout(function () {
                element.removeClass('animated ' + animation);
            }, 2000);
        });
}

//拖动面板
function WinMove() {
    var element = "[class*=col]";
    var handle = ".ibox-title";
    var connect = "[class*=col]";
    $(element).sortable({
        handle: handle,
        connectWith: connect,
        tolerance: 'pointer',
        forcePlaceholderSize: true,
        opacity: 0.8,
    })
        .disableSelection();
};


//日期、时间格式转换方法
function formateDate(time){
    let curTime = new Date(time);
    let year = curTime.getFullYear();
    let month = curTime.getMonth() + 1;
    let day = curTime.getDate();
    let hour = curTime.getHours();
    let min = curTime.getMinutes();
    let sec = curTime.getSeconds();

    return year + '-' + addPreZero(month) + '-' + addPreZero(day) + ' ' +addPreZero(hour) + ':' + addPreZero(min) + ':' + addPreZero(sec);
}
//不足10前面补0的方法;
function addPreZero(val){
    val = val < 10 ? '0' + val : val;
    return val;
}


/*$.ajaxSetup({
    beforeSend:function(xhr){
        //  console.log('xhr222==',xhr);
         if(window.localStorage){
             var token = localStorage.getItem('token');
         }else{
             var token = $.cookie('token');
         }
		 xhr.setRequestHeader('Authorization','Bearer '+token);
    },
    complete:function(xhr,ts){
        //   console.log('xhr==',xhr,'ts==',ts);
    }
})*/





