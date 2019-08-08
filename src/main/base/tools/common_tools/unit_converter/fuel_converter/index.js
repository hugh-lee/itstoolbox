//单位换算-温度
(function () {
	var converter = function (value, from, to) {
		var unit = {
			us_mile_per_gallon: 1,
			uk_mile_per_gallon: 1 / 1.20095,
			us_kilometer_per_gallon: 1 / 1.609344,
			kilometer_per_liter: 1 / 0.425144,
		};

		return (value * unit[from] / unit[to]).toFixed(3).toString();
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
			var noupdate = false;
			if (input.name == 'liter_per_hundred_kilometer') {
				noupdate = true;
				var v = input.value;
				$("input[name='gallon_per_hundred_kilometer']").val((v/3.78541).toFixed(3).toString().replace(noZero, ''));
				$("input[name='kilometer_per_liter']").val((100 /v).toFixed(3).toString().replace(noZero, ''));
				input = $("input[name='kilometer_per_liter']")[0];
				var value = input.value.trim();
			}
			if (input.name == 'gallon_per_hundred_kilometer') {
				noupdate = true;
				var v = input.value;
				$("input[name='liter_per_hundred_kilometer']").val((v * 3.78541).toFixed(3).toString().replace(noZero, ''));
				$("input[name='us_kilometer_per_gallon']").val((100 /v).toFixed(3).toString().replace(noZero, ''));
				input = $("input[name='us_kilometer_per_gallon']")[0];
				var value = input.value.trim();
			}

			
			for (var i = 0; i < $inputs.length; i++) {
				if ($inputs[i].name == 'liter_per_hundred_kilometer' || $inputs[i].name == 'gallon_per_hundred_kilometer') {
					var v = $("input[name='us_kilometer_per_gallon']").val();
					if (v != "" && !noupdate) {
						$("input[name='gallon_per_hundred_kilometer']").val((100 / v).toFixed(3).toString().replace(noZero, ''));
						$("input[name='liter_per_hundred_kilometer']").val((100 * 3.78541 / v).toFixed(3).toString().replace(noZero, ''));
					}
					continue;
				}
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