var zg = {};
/**
 * 设置cookies,生存时间为1个月
 */
zg.setCookie=function(name,value){
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime()+Days*24*60*60*1000);
	document.cookie=name+"="+escape(value)+";expires="+exp.toGMTString();
};
/**
 * 设置cookie，生存时间为自定义，单位毫秒
 */
zg.setCookie=function(name,value,time){
	var exp = new Date();
	exp.setTime(exp.getTime()+parseInt(time));
	document.cookie=name+"="+escape(value)+";expires="+exp.toGMTString();
};
/**
 * 获取cookies
 */
zg.getCookie=function(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg)){
		return unescape(arr[2]);
	}else{
		return null;
	}
};
/**
 * 删除cookies
 */
zg.delCookie=function(name){
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = zg.getCookie(name);
	if(cval != null){
		document.cookie=name+"="+cval+";expires="+exp.toGMTString();
	}
};