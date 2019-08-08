$(document).ready(function () {
    $('#inpuText').keyup(function (e) {
        var v = e.target.value;
        $("#simplifiedTxt").val(toSimplified(v));
        $("#traditionalTxt").val(toTraditionalized(v));
        $("#martianTxt").val(toSpark(v));
        $("#pinyinTxt").val(toPinyin(v));
    });

    $("#copyBtn").click(function (e) {
        $(".tab-content textarea").each(function (i, item) {
            if (item.clientHeight > 0) {
                item.select();
                if (document.execCommand('copy')) {
                    document.execCommand('copy');
                    window.alert('复制成功，现在您可以粘贴到任何地方！')
                }
            }
        });
    });

    $("#clearBtn").click(function (e) {
        $("textarea").val("");
    });

});

function toSimplified(str) {
    var s = '';
    for (var i = 0; i < str.length; i++) {
        var c = str.charAt(i);
        if (isNumberOrLetter(c)) {
            s += c;
            continue;
        }
        var index = str_traditional.indexOf(c);
        if (index < 0) {
            index = str_spark.indexOf(c);
        }
        if (index >= 0) {
            c = str_simplified.charAt(index);
        }
        s += c;
    }
    return s;
}

function toTraditionalized(str) {
    var s = '';
    for (var i = 0; i < str.length; i++) {
        var c = str.charAt(i);
        if (isNumberOrLetter(c)) {
            s += c;
            continue;
        }

        var index = str_simplified.indexOf(c);
        if (index < 0) {
            index = str_spark.indexOf(c);
        }
        if (index >= 0) {
            c = str_traditional.charAt(index);
        }
        s += c;
    }
    return s;
}

function toSpark(str) {
    var s = '';
    for (var i = 0; i < str.length; i++) {
        var c = str.charAt(i);
        if (isNumberOrLetter(c)) {
            s += c;
            continue;
        }

        var index = str_simplified.indexOf(c);
        if (index < 0) {
            index = str_traditional.indexOf(c);
        }
        if (index >= 0) {
            c = str_spark.charAt(index);
        }
        s += c;
    }
    return s;
}

function toPinyin(cc) {
    var str = '';
    var s;
    for (var i = 0; i < cc.length; i++) {
        if (pydic.indexOf(cc.charAt(i)) != -1 && cc.charCodeAt(i) > 200) {
            s = 1;
            while (pydic.charAt(pydic.indexOf(cc.charAt(i)) + s) != ",") {
                str += pydic.charAt(pydic.indexOf(cc.charAt(i)) + s);
                s++;
            }
            str += " ";
        } else {
            str += cc.charAt(i);
        }
    }
    return str;
}

function isNumberOrLetter(value) {
    var Regx = /^[A-Za-z0-9]*$/;
    if (Regx.test(value)) {
        return true;
    } else {
        return false;
    }
}