//单位换算-面积
(function () {
	var converter = function (value, from, to) {
		var unit = {
			n2: 2,
			n4: 4,
			n8: 8,
			n10: 10,
			n16: 16,
			n32: 32,
			n64: 64,
		};

		var v = parseInt(value, unit[from]);
		return v.toString(unit[to]);
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