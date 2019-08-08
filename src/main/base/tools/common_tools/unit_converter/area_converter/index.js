//单位换算-面积
(function(){
	var converter = function(value,from,to){
		var unit = {
			mSquare_kilometer:1e6,
			mHectare:1e4,
			mSquare_meter:1,
			mAre:10000/15,
			mSquare_decimeter:1e-2,
			mSquare_centimeter:1e-4,
			mSquare_millimeter:1e-6,
			engSquare_foot:(0.3048 * 0.3048)
		};
		unit.engSquare_yard = 3*3*unit.engSquare_foot;
		unit.usSquare_rod = 16.5*16.5*unit.engSquare_foot;
		unit.engAcre = 160*unit.usSquare_rod;
		unit.engSquare_mile = 5280*5280*unit.engSquare_foot;
		unit.engSquare_inch = unit.engSquare_foot/(12 * 12);
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