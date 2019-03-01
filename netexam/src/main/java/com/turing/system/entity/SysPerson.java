package com.turing.system.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SysPerson {
    private String ppId;

    private String ppNo;

    private String ppMinzu;

    private String ppInfo;

    private Date ppInTime;

    private BigDecimal ppPay;

    private String ppState;

    private String ppName;

    private Date ppBirth;

    private String ppSex;

    private String ppAdds;

    private String ppPhone;

    private String ppShenfenzheng;

    private String spId;
    
    private String spName;

    private String userId;
    
    private String nickName;

    private String deptId;
    
    private String deptName;

    private String ppPic;

    public String getPpId() {
        return ppId;
    }

    public void setPpId(String ppId) {
        this.ppId = ppId == null ? null : ppId.trim();
    }

    public String getPpNo() {
        return ppNo;
    }

    public void setPpNo(String ppNo) {
        this.ppNo = ppNo == null ? null : ppNo.trim();
    }

    public String getPpMinzu() {
        return ppMinzu;
    }

    public void setPpMinzu(String ppMinzu) {
        this.ppMinzu = ppMinzu == null ? null : ppMinzu.trim();
    }

    public String getPpInfo() {
        return ppInfo;
    }

    public void setPpInfo(String ppInfo) {
        this.ppInfo = ppInfo == null ? null : ppInfo.trim();
    }

    public Date getPpInTime() {
        return ppInTime;
    }

    public void setPpInTime(Date ppInTime) {
        this.ppInTime = ppInTime;
    }

    public BigDecimal getPpPay() {
        return ppPay;
    }

    public void setPpPay(BigDecimal ppPay) {
        this.ppPay = ppPay;
    }

    public String getPpState() {
        return ppState;
    }

    public void setPpState(String ppState) {
        this.ppState = ppState == null ? null : ppState.trim();
    }

    public String getPpName() {
        return ppName;
    }

    public void setPpName(String ppName) {
        this.ppName = ppName == null ? null : ppName.trim();
    }

    public Date getPpBirth() {
        return ppBirth;
    }

    public void setPpBirth(Date ppBirth) {
        this.ppBirth = ppBirth;
    }

    public String getPpSex() {
        return ppSex;
    }

    public void setPpSex(String ppSex) {
        this.ppSex = ppSex == null ? null : ppSex.trim();
    }

    public String getPpAdds() {
        return ppAdds;
    }

    public void setPpAdds(String ppAdds) {
        this.ppAdds = ppAdds == null ? null : ppAdds.trim();
    }

    public String getPpPhone() {
        return ppPhone;
    }

    public void setPpPhone(String ppPhone) {
        this.ppPhone = ppPhone == null ? null : ppPhone.trim();
    }

    public String getPpShenfenzheng() {
        return ppShenfenzheng;
    }

    public void setPpShenfenzheng(String ppShenfenzheng) {
        this.ppShenfenzheng = ppShenfenzheng == null ? null : ppShenfenzheng.trim();
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

	public String getPpPic() {
		return ppPic;
	}

	public void setPpPic(String ppPic) {
		this.ppPic = ppPic;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}