package com.turing.framework.select;

import java.util.ArrayList;
import java.util.List;

public class SelectOptions {
	private String key;
	private Object value;
	private String selected;
	private List<SelectOptions> selectList = new ArrayList<SelectOptions>();
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public List<SelectOptions> getSelectList() {
		return selectList;
	}
	public void setSelectList(List<SelectOptions> selectList) {
		this.selectList = selectList;
	}
	
	
}
