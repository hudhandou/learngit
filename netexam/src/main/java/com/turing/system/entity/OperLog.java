package com.turing.system.entity;

import java.util.Date;

public class OperLog {

	private String operId;
	private String operPersonid;
	private String operPersonname;
	private String operTime;
	private String operType;
	private String operDesc;
	private String operModel;
	
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getOperPersonid() {
		return operPersonid;
	}
	public void setOperPersonid(String operPersonid) {
		this.operPersonid = operPersonid;
	}
	public String getOperPersonname() {
		return operPersonname;
	}
	public void setOperPersonname(String operPersonname) {
		this.operPersonname = operPersonname;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public String getOperDesc() {
		return operDesc;
	}
	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}
	public String getOperModel() {
		return operModel;
	}
	public void setOperModel(String operModel) {
		this.operModel = operModel;
	}
	public String getOperTime() {
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
}
