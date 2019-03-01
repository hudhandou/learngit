package com.turing.manage.entity;

public class SubjectPaperTable {
    private String subjectPaperId;

    private String paperId;

    private String subjectId;

    public String getSubjectPaperId() {
        return subjectPaperId;
    }

    public void setSubjectPaperId(String subjectPaperId) {
        this.subjectPaperId = subjectPaperId == null ? null : subjectPaperId.trim();
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }
}