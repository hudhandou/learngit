package com.turing.student.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Studentpaper {
    private String studentpaperId;

    private String studentId;

    private String studentpaperTopicsId;

    private Double studentpaperScore;

    private BigDecimal studentpaperYongshi;

    private Double studentpaperZongfen;

    private Date studentpaperKaoshishijian;

    private String paperId;

    private String studentpaperAnswer;

    private Double studentpaperCount;

    public String getStudentpaperId() {
        return studentpaperId;
    }

    public void setStudentpaperId(String studentpaperId) {
        this.studentpaperId = studentpaperId == null ? null : studentpaperId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentpaperTopicsId() {
        return studentpaperTopicsId;
    }

    public void setStudentpaperTopicsId(String studentpaperTopicsId) {
        this.studentpaperTopicsId = studentpaperTopicsId == null ? null : studentpaperTopicsId.trim();
    }

    public Double getStudentpaperScore() {
        return studentpaperScore;
    }

    public void setStudentpaperScore(Double studentpaperScore) {
        this.studentpaperScore = studentpaperScore;
    }

    public BigDecimal getStudentpaperYongshi() {
        return studentpaperYongshi;
    }

    public void setStudentpaperYongshi(BigDecimal studentpaperYongshi) {
        this.studentpaperYongshi = studentpaperYongshi;
    }

    public Double getStudentpaperZongfen() {
        return studentpaperZongfen;
    }

    public void setStudentpaperZongfen(Double studentpaperZongfen) {
        this.studentpaperZongfen = studentpaperZongfen;
    }

    public Date getStudentpaperKaoshishijian() {
        return studentpaperKaoshishijian;
    }

    public void setStudentpaperKaoshishijian(Date studentpaperKaoshishijian) {
        this.studentpaperKaoshishijian = studentpaperKaoshishijian;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getStudentpaperAnswer() {
        return studentpaperAnswer;
    }

    public void setStudentpaperAnswer(String studentpaperAnswer) {
        this.studentpaperAnswer = studentpaperAnswer == null ? null : studentpaperAnswer.trim();
    }

    public Double getStudentpaperCount() {
        return studentpaperCount;
    }

    public void setStudentpaperCount(Double studentpaperCount) {
        this.studentpaperCount = studentpaperCount;
    }
}