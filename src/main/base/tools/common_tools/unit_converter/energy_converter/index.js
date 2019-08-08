//单位换算-功
(function () {
    var converter = function (value, from, to) {
        var unit = {
            Joule: 1,
            Kgm: 9.80392157,
            Psh: 2647603.9184538,
            Hph: 2684563.7583893,
            Kwh: 3599712.023038157,
            Kcal: 4185.851820846,
            Btu: 1055.0749103,
            Ftlb: 1.3557483731
        };
        return (value * unit[from] / unit[to]).toFixed(7);
    };
    var $inputs = $("input");
    var $botton = $(".input-button")[0];
    var hander = null;
    var change = function () {
        var input = this;
        hander && clearTimeout(hander);
        hander = setTimeout(function () {
            var value = input.value.trim();
            var name = input.name;
            var noZero = /\.?0+$/;
            for (var i = 0; i < $inputs.length; i++) {
                $inputs[i].value = (value != '' ? converter(+value, input.name, $inputs[i].name) : '').replace(noZero, '');
            }
        }, 500);
    }
    for (var i = 0; i < $inputs.length; i++) {
        (function (i) {
            $inputs[i].onkeydown = function () {
                hander && clearTimeout(hander);
            }
            $inputs[i].onkeyup = change;
            $inputs[i].onchange = change;
        })(i);
    }
    $botton.onclick = function () {
        for (var i = 0; i < $inputs.length; i++) {
            $inputs[i].value = '';
        }
    }
})();


function normalize(what, digits) {
    var str = "" + what;
    var pp = Math.max(str.lastIndexOf("+"), str.lastIndexOf("-"));
    var idot = str.indexOf(".");
    if (idot >= 1) {
        var ee = (pp > 0) ? str.substring(pp - 1, str.length) : "";
        digits += idot;
        if (digits >= str.length)
            return str;
        if (pp > 0 && digits >= pp)
            digits -= pp;
        var c = eval(str.charAt(digits));
        var ipos = digits - 1;
        if (c >= 5) {
            while (str.charAt(ipos) == "9")
                ipos--;
            if (str.charAt(ipos) == ".") {
                var nc = eval(str.substring(0, idot)) + 1;
                if (nc == 10 && ee.length > 0) {
                    nc = 1;
                    ee = "e" + (eval(ee.substring(1, ee.length)) + 1);
                }
                return "" + nc + ee;
            }
            return str.substring(0, ipos) + (eval(str.charAt(ipos)) + 1) + ee;
        } else
            var ret = str.substring(0, digits) + ee;
        for (var i = 0; i < ret.length; i++)
            if (ret.charAt(i) > "0" && ret.charAt(i) <= "9")
                return ret;
        return str;
    }
    return str;
}