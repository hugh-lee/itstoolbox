$(document).ready(function () {
    $('#translateBtn').click(function (e){
        var name = $('.nav-item a.active').text();
        var value = $('textarea').val();
        if (name == "词酷翻译") {
            window.open('http://ce.linedict.com/dict.html#/cnen/search?query='+ encodeURIComponent(value));
        }
        if (name == "金山词霸") {
            window.open('http://www.iciba.com/'+encodeURIComponent(value)+'/');
        }
        if (name == "Google翻译") {
            window.open('http://translate.google.cn/#en/zh-CN/'+encodeURIComponent(value));
        }
        if (name == "有道词典") {
            window.open('http://dict.youdao.com/search?ue=utf8&q='+encodeURIComponent(value));
        }
        if (name == "必应翻译") {
            window.open('https://cn.bing.com/dict/search?q='+ encodeURIComponent(value));
        }
        if (name == "百度词典") {
            window.open('http://dict.baidu.com/s?wd='+encodeURIComponent(value));
        }
        if (name == "cnki翻译助手") {
            window.open('http://dict.cnki.net/dict_result.aspx?searchword='+encodeURIComponent(value));
        }
    });
});


