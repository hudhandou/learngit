package com.turing.system.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SysDept {
    private String deptId;

    private String deptName;

    private String deptCode;

    private String deptInfo;

    private String deptPhone;

    private BigDecimal deptSequ;

    private String deptState;

    private String deptPar;
    
    private List deptList = new ArrayList();

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
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getDeptInfo() {
        return deptInfo;
    }

    public void setDeptInfo(String deptInfo) {
        this.deptInfo = deptInfo == null ? null : deptInfo.trim();
    }

    public String getDeptPhone() {
        return deptPhone;
    }

    public void setDeptPhone(String deptPhone) {
        this.deptPhone = deptPhone == null ? null : deptPhone.trim();
    }

    public BigDecimal getDeptSequ() {
        return deptSequ;
    }

    public void setDeptSequ(BigDecimal deptSequ) {
        this.deptSequ = deptSequ;
    }

    public String getDeptState() {
        return deptState;
    }

    public void setDeptState(String deptState) {
        this.deptState = deptState == null ? null : deptState.trim();
    }

    public String getDeptPar() {
        return deptPar;
    }

    public void setDeptPar(String deptPar) {
        this.deptPar = deptPar == null ? null : deptPar.trim();
    }

	public List getDeptList() {
		return deptList;
	}

	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}
}