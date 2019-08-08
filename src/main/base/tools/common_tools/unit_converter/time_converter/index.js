//单位换算-温度
(function () {
	var converter = function (value, from, to) {
		var unit = {
			year: 365 * 24 * 60 * 60,
			month: 30 * 24 * 60 * 60,
			week: 7 * 24 * 60 * 60,
			day: 24 * 60 * 60,
			hour: 1 * 60 * 60,
			minute: 1 * 60,
			second: 1,
			millisecond: 1e-3,
			microsecond: 1e-6,
			nanosecond: 1e-9,
			picosecond: 1e-12,
			femtosecond: 1e-15,
		};

		if (to == 'femtosecond')
			return Math.round((value * unit[from] / unit[to])).toFixed(3).toString();

		return (value * unit[from] / unit[to]).toFixed(6).toString();
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


