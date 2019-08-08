//单位换算-体积
(function () {
	var converter = function (value, from, to) {
		var unit = {
			mCubic_meter: 1000,
			mHectoliter: 100,
			mDekaliter: 10,
			mLiter: 1,
			mDeciliter: 0.1,
			mCentiliter: 0.01,
			mMilliliter: 0.001,
			mCubic_millimeter: 1e-6,
			mcTable_spoon: 0.015,
			mcTea_spoon: 0.005,
			uscCubic_inch: 0.016387064,
			briGallon: 4.54609
		};
		unit.uscAcre_foot = 43560 * 1728 * unit.uscCubic_inch;
		unit.uscCubic_yard = 27 * 1728 * unit.uscCubic_inch;
		unit.uscCubic_foot = 1728 * unit.uscCubic_inch;
		unit.uslGallon = 231 * unit.uscCubic_inch;
		unit.uslBarrel = 42 * unit.uslGallon;
		unit.uslQuart = unit.uslGallon / 4;
		unit.uslPint = unit.uslGallon / 8;
		unit.uslGill = unit.uslGallon / 32;
		unit.uslFluid_ounce = unit.uslGallon / 128;
		unit.uslFluid_dram = unit.uslGallon / 1024;
		unit.uslMinim = unit.uslFluid_ounce / 61440;
		unit.usdBarrel = 7056 * unit.uscCubic_inch;
		unit.usdBushel = 2150.42 * unit.uscCubic_inch;
		unit.usdPeck = unit.usdBushel / 4;
		unit.usdQuart = unit.usdBushel / 32;
		unit.usdPint = unit.usdBushel / 64;
		unit.uscCup = 8 * unit.uslFluid_ounce;
		unit.uscTable_spoon = unit.uslFluid_ounce / 2;
		unit.uscTea_spoon = unit.uslFluid_ounce / 6;
		unit.briBarrel = 36 * unit.briGallon;
		unit.briBushel = 8 * unit.briGallon;
		unit.briPint = unit.briGallon / 8;
		unit.briFluid_ounce = unit.briGallon / 160;
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