//单位换算-功率
(function () {
    var $mod_panel = DOMUtil.getElementsByClassName('mod-panel')[0];
    var converter = function (value, from, to) {
        var unit = {
            Watt: 0.001,
            Kilowatt: 1,
            Horsepower: 0.745712172,
            mHorsepower: 0.7352941,
            kgms: 0.0098039215,
            kcals: 4.1841004,
            Btus: 1.05507491,
            ftlbs: 0.0013557483731,
            Js: 0.001,
            Nms: 0.001
        };
        return (value * unit[from] / unit[to]).toFixed(7).toString();
    };
    var $inputs = DOMUtil.getElementsByClassName('input-text', $mod_panel);
    var $botton = DOMUtil.getElementsByClassName('input-button', $mod_panel)[0];
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