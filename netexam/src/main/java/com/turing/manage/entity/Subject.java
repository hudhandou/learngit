package com.turing.manage.entity;

public class Subject {
    private String subjectId;

    private String subjectName;

    private String subjectPid;

    private Integer sorder;
     
    private String bigname;
    
    public String getBigname() {
		return bigname;
	}

	public void setBigname(String bigname) {
		this.bigname = bigname;
	}

	public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getSubjectPid() {
        return subjectPid;
    }

    public void setSubjectPid(String subjectPid) {
        this.subjectPid = subjectPid == null ? null : subjectPid.trim();
    }

    public Integer getSorder() {
        return sorder;
    }

    public void setSorder(Integer sorder) {
        this.sorder = sorder;
    }

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", subjectPid=" + subjectPid
				+ ", sorder=" + sorder + "]";
	}
    
}