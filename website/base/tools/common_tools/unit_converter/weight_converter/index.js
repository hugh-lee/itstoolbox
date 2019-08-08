//单位换算-重量
(function(){
	var converter = function(value,from,to){
		var unit = {
			mTon:1000,
			mKilogram:1,
			mGram:0.001,
			mMilligram:1e-6,
			cJin:0.5,
			cDan:50,
			cLiang:0.05,
			cQian:0.005,
			avdpPound:0.45359237
		};
		unit.briTon = 2240 * unit.avdpPound;
		unit.usTon = 2000 * unit.avdpPound;
		unit.briCWT = 112 * unit.avdpPound;
		unit.usCWT = 100 * unit.avdpPound;
		unit.briStone = 14 * unit.avdpPound;    
		unit.avdpOunce = unit.avdpPound / 16;
		unit.avdpDram= unit.avdpPound / 256;
		unit.avdpGrain = unit.avdpPound / 7000;
		unit.troyPound = 5760 * unit.avdpGrain;
		unit.troyOunce = 480 * unit.avdpGrain;
		unit.troyDWT = 24 * unit.avdpGrain;
		unit.troyGrain = unit.avdpGrain;
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