package com.turing.system.entity;

import java.math.BigDecimal;

public class SysUser {
    private String userId;

    private String userName;

    private String userPass;

    private String nickName;

    private String userState;

    private String userInfo;

    private BigDecimal userSequ;
    
    private String sysPersonName;
    
    private String sysPersonId;
    private String  studentPassword;
    
    
    public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword==null?null:studentPassword.trim();
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState == null ? null : userState.trim();
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo == null ? null : userInfo.trim();
    }

    public BigDecimal getUserSequ() {
        return userSequ;
    }

    public void setUserSequ(BigDecimal userSequ) {
        this.userSequ = userSequ;
    }

	public String getSysPersonName() {
		return sysPersonName;
	}

	public void setSysPersonName(String sysPersonName) {
		this.sysPersonName = sysPersonName;
	}

	public String getSysPersonId() {
		return sysPersonId;
	}

	public void setSysPersonId(String sysPersonId) {
		this.sysPersonId = sysPersonId;
	}

}