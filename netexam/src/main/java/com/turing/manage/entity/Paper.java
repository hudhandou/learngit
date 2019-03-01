package com.turing.manage.entity;

import java.util.Date;

public class Paper {
    private String paperId;

    private String paperName;

    private Date paperCreatetime;

    private Integer paperNeedtime;

    private Integer paperScore;

    private String paperState;

    private Integer paperTmsl;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName == null ? null : paperName.trim();
    }

    public Date getPaperCreatetime() {
        return paperCreatetime;
    }

    public void setPaperCreatetime(Date paperCreatetime) {
        this.paperCreatetime = paperCreatetime;
    }

    public Integer getPaperNeedtime() {
        return paperNeedtime;
    }

    public void setPaperNeedtime(Integer paperNeedtime) {
        this.paperNeedtime = paperNeedtime;
    }

    public Integer getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(Integer paperScore) {
        this.paperScore = paperScore;
    }

    public String getPaperState() {
        return paperState;
    }

    public void setPaperState(String paperState) {
        this.paperState = paperState == null ? null : paperState.trim();
    }

    public Integer getPaperTmsl() {
        return paperTmsl;
    }

    public void setPaperTmsl(Integer paperTmsl) {
        this.paperTmsl = paperTmsl;
    }
}