//单位换算
(function(){
	var converter = function(value,from,to){
		var unit = {
			mKilopascal:1000,
			mHectopascal:100,
			mPascal:1,
			mBar:1e5,
			mMillibar:100,
			mAtm :101325,
			engPound_sq_inch:6894.757,
			xpressKg_sq_cm:98066.5,
			xpressKg_sq_m:9.80665,
			mmmH2O :1/0.101972
		};
		unit.mMillimeter_Hg = unit.mAtm / 760;
		unit.engInch_Hg = 25.4 * unit.mMillimeter_Hg;
		unit.engPound_sq_foot = unit.engPound_sq_inch / 144;
		return (value*unit[from]/unit[to]).toFixed(7).toString();
	};
	var $inputs = $("input");
    var $botton = $(".input-button")[0];
	var hander = null;
	var change = function(){
		var input = this;
		hander&&clearTimeout(hander);
		hander = setTimeout(function(){
			var value = input.value.trim();
			var name = input.name;
			var noZero = /\.?0+$/;
			for(var i=0;i<$inputs.length;i++){				
				$inputs[i].value = (value!=''?converter(+value,input.name,$inputs[i].name):'').replace(noZero,'');
			}
		},200);
	}
	for(var i=0;i<$inputs.length;i++){
		(function(i){
			$inputs[i].onkeydown = function(){
				hander&&clearTimeout(hander);
			}
			$inputs[i].onkeyup = change;
			$inputs[i].onchange = change;
		})(i);
	}
	$botton.onclick = function(){
		for(var i=0;i<$inputs.length;i++){
			$inputs[i].value = '';
		}
	}
})();