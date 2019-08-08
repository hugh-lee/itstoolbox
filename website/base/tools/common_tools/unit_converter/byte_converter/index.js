//单位换算-温度
(function () {
	var converter = function (value, from, to) {
		var unit = {
			bit: 1 / 8,
			byte: 1,
			kbyte: 1024,
			mbyte: 1024 * 1024,
			gbyte: 1024 * 1024 * 1024,
			pbyte: 1024 * 1024 * 1024 *1024,
			tbyte: 1024 * 1024 * 1024 *1024 * 1024,
		};

		// if (to == 'femtosecond')
		// 	return Math.round((value * unit[from] / unit[to])).toFixed(3).toString();

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


