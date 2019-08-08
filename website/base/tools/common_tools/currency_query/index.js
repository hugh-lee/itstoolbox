$(document).ready(function () {
    $('#submitBtn').click(converter);
    $('#submit2Btn').click(converter);
});

function converter(e) {
    var from = $('#from').val();
    var fromNumber = Math.round($('#fromNumber').val() * 10000);
    var to = $('#to').val();
    var toNumber = Math.round($('#toNumber').val() * 10000);


    var number = fromNumber;
    var chiyou = from;
    var duihuan = to;

    if (e.currentTarget.id == 'submit2Btn') {
        number = toNumber;
        chiyou = to;
        duihuan = from;
    }

    time = new Date();
    url = `https://webapi.huilv.cc/api/exchange?num=${number}&chiyouhuobi=${chiyou}&duihuanhuobi=${duihuan}&callback=jisuanjieguo&_=${time.getTime()}`;
    request({
        url: url,
        method: 'get',
        datatype : 'text',
        success: retrieveCurrency
    });
}


function retrieveCurrency(str) {
    // var start = str.indexOf('<table><tr><th colspan="3"><p>按当前汇率兑换结果');
    // var end = str.indexOf('</table>', start);

    start = str.indexOf('(');
    end = str.lastIndexOf(')');

    data = JSON.parse(str.substring(start + 1, end));

    var from = $('#from').val();
    var fromNumber = Math.round($('#fromNumber').val() *10000);
    var to = $('#to').val();
    var toNumber = Math.round($('#toNumber').val()*10000);

    if (data.chiyouhuobi.indexOf(from) != -1) {
        $('#toNumber').val((fromNumber * data.dangqianhuilv /10000).toFixed(4));
    } else {
        $('#fromNumber').val((toNumber * data.dangqianhuilv / 10000).toFixed(4));       
    }

    var comment = `当前汇率：<b class='text-danger'>${data.dangqianhuilv}</b>  &nbsp;&nbsp;&nbsp;&nbsp;更新日期：<b class='text-danger'>${data.huilvupdate}</b>。<br/><br/>`;
    $('#comment').empty();
    $('#comment').append(comment);
}