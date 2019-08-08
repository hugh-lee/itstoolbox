//单位换算-长度
(function () {
    var converter = function (value, from, to) {
        var unit = {
            mMeter_s: 1,
            mMeter_h: 1 / 3600,
            mKilometer_s: 1 / 0.001,
            mKilometer_h: 1 / 3.6,
            mFoot_s: 12 / 39.370079,
            mFoot_h: 12 / (39.370079 * 3600 ),
            mInch_s: 1 / 39.370079,
            mInch_h: 1 / (39.370079 * 3600 ),
            mMile_s: 3600 / (2.236936),
            mMile_h: 1 / 2.236936,
            mC: 1 / 3.3356e-9,
            mMach: 1 / 0.0029386,

        };

        return (value * unit[from] / unit[to]).toFixed(7).toString();
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
        }, 200);
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