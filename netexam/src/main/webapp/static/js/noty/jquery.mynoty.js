

function noty(options){
	if(options == ''){
		return;
	}
	//var options = eval(options);
	var $div = $('<div class="noty_bar"><div class="noty_message"><span class="noty_text"></span><div class=""></div></div></div>');
	var type = options.type;
	var text = options.text;
	var timeout = options.timeout;
	var layout = options.layout;
	$div.find(".noty_text").html(text);
	$div.addClass("noty_theme_default noty_layout_topCenter noty_"+type);
	//	$div.addClass("noty_theme_default noty_layout_topCenter noty_error");
// 	$div.addClass("noty_theme_default noty_layout_topCenter noty_alert");
// 	$div.addClass("noty_theme_default noty_layout_topCenter noty_success");
// 	$div.addClass("noty_theme_default noty_layout_topCenter noty_information");
// 	$div.addClass("noty_theme_default noty_layout_topCenter noty_warning");
	//$div.addClass("mynoty_position");
	$div.css({ position: "absolute", width: "300px" });
	v_h = window.innerHeight;
	v_w = window.innerWidth;
	//$div.css({ left: (v_w-320)+"px", top: "10px" });
	//$div.css({ left: (v_w/2-150)+"px", top: "260px" });
	if ("topRight"==layout) {
		$div.css({ left: (v_w-320)+"px", top: "10px" });
	}else if("topLeft"==layout){
		$div.css({ left: "10px", top: "10px" });
	}else if("topCenter"==layout){
		$div.css({ left: (v_w/2-150)+"px", top: "10px" });
	}else if("buttomLeft"==layout){
		$div.css({ left: "10px", top: "530px" });
	}else if("buttomRight"==layout){
		$div.css({ left: (v_w-320)+"px", top: "530px" });
	}else if("buttomCenter"==layout){
		$div.css({ left: (v_w/2-150)+"px", top: "530px" });
	}else if("center"==layout){
		$div.css({ left: (v_w/2-150)+"px", top: "260px" });
	}
	$div.hide();
	$div.fadeIn(200,function(){
		setTimeout(function(){$div.fadeOut(1000);},timeout);
	});
//	$div.find("noty_close").click(function(){
//		$div.fadeOut(20);
//	});
	$div.prependTo($('body'));
}