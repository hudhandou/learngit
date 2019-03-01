jQuery.validator.addMethod(
    "telphone", 
    function(value, element, param) {
        var reg = /^1[3-9]{1}\d{9}$/;
		if(reg.test(value)){
			return true;
		}
        return false;   
    }, 
    "请输入正确的电话号"
);
jQuery.validator.addMethod(
	    "chinese", 
	    function(value, element, param) {
	        var reg = /^[\u4e00-\u9fa5]*$/;
			if(reg.test(value)){
				return true;
			}
	        return false;   
	    }, 
	    "请输入汉字"
	);