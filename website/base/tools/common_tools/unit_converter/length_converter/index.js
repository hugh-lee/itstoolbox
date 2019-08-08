//单位换算-长度
(function () {
    var converter = function (value, from, to) {
        var unit = {
            mKilometer: 1e3,
            mMeter: 1,
            mDecimeter: 1e-1,
            mCentimeter: 1e-2,
            mMillimeter: 1e-3,
            mMicronmeter: 1e-6,
            mLimeter: 500,
            mZhangmeter: 10 / 3,
            mChimeter: 1 / 3,
            mCunmeter: 1 / 30,
            mFenmeter: 1 / 300,
            mmLimeter: 1 / 3000,
            engFoot: 0.3048,
            nautMile: 1852
        };
        unit.engMile = 5280 * unit.engFoot;
        unit.engFurlong = 660 * unit.engFoot;
        unit.engYard = 3 * unit.engFoot;
        unit.engInch = unit.engFoot / 12;
        unit.nautFathom = 6 * unit.engFoot;
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