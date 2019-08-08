//单位换算-温度
(function(){
	var converter = function(value,from,to){
		switch(from){
			case 'tempFahr':
				value = (value-32)/1.8;
				break;
			case 'tempKelvin':
				value = value - 273.15;
				break;
			case 'tempRankine':
				value = value/1.8-273.15;
				break;
			case 'tempReaumur':
				value = value*1.25;
				break;
		}
		switch(to){
			case 'tempFahr':
				value = value*1.8+32
				break;
			case 'tempKelvin':
				value = value + 273.15;
				break;
			case 'tempRankine':
				value = (value + 273.15)*1.8;
				break;
			case 'tempReaumur':
				value = value/1.25;
				break;
		}
		return value.toString();
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
                //$inputs[i].value = (value!=''?converter(+value,input.name,$inputs[i].name):'').replace(noZero,'');
                $inputs[i].value = (value!=''?converter(+value,input.name,$inputs[i].name):'');
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