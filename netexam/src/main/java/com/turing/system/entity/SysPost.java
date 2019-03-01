package com.turing.system.entity;

public class SysPost {
    private String spId;

    private String spName;

    private String spInfo;

    private String deptId;
    
    private String deptName;

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName == null ? null : spName.trim();
    }

    public String getSpInfo() {
        return spInfo;
    }

    public void setSpInfo(String spInfo) {
        this.spInfo = spInfo == null ? null : spInfo.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}