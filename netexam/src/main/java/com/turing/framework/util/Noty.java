package com.turing.framework.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

public class Noty {
	//topRight topLeft topCenter buttomLeft buttomRight buttomCenter center
	private static final String LAYOUT = "center";
	private static final int TIMEOUT = 2000;

	public static void alert(String msg, RedirectAttributes ra) {
		alert(msg, ra, "alert");
	}

	public static void success(String msg, RedirectAttributes ra) {
		alert(msg, ra, "success");
	}

	public static void error(String msg, RedirectAttributes ra) {
		alert(msg, ra, "error");
	}

	public static void warn(String msg, RedirectAttributes ra) {
		alert(msg, ra, "warning");
	}

	public static void info(String msg, RedirectAttributes ra) {
		alert(msg, ra, "information");
	}

	private static void alert(String msg, RedirectAttributes ra, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("layout", LAYOUT);
		map.put("timeout", TIMEOUT);
		map.put("text", msg);
		map.put("type", type);
		ra.addFlashAttribute("notyInfo", JSON.toJSONString(map));
	}
}
