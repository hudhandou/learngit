package com.turing.manage.entity;

public class Topic {
    private String topicId;

    private String subjectId;

    private String topicName;

    private String topicAnswerA;

    private String topicAnswerB;

    private String topicAnswerC;

    private String topicAnswerD;

    private String topicAnswerTrue;

    private Double topicState;
    
    private String subjectName;
    
    

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId == null ? null : topicId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName == null ? null : topicName.trim();
    }

    public String getTopicAnswerA() {
        return topicAnswerA;
    }

    public void setTopicAnswerA(String topicAnswerA) {
        this.topicAnswerA = topicAnswerA == null ? null : topicAnswerA.trim();
    }

    public String getTopicAnswerB() {
        return topicAnswerB;
    }

    public void setTopicAnswerB(String topicAnswerB) {
        this.topicAnswerB = topicAnswerB == null ? null : topicAnswerB.trim();
    }

    public String getTopicAnswerC() {
        return topicAnswerC;
    }

    public void setTopicAnswerC(String topicAnswerC) {
        this.topicAnswerC = topicAnswerC == null ? null : topicAnswerC.trim();
    }

    public String getTopicAnswerD() {
        return topicAnswerD;
    }

    public void setTopicAnswerD(String topicAnswerD) {
        this.topicAnswerD = topicAnswerD == null ? null : topicAnswerD.trim();
    }

    public String getTopicAnswerTrue() {
        return topicAnswerTrue;
    }

    public void setTopicAnswerTrue(String topicAnswerTrue) {
        this.topicAnswerTrue = topicAnswerTrue == null ? null : topicAnswerTrue.trim();
    }

    public Double getTopicState() {
        return topicState;
    }

    public void setTopicState(Double topicState) {
        this.topicState = topicState;
    }
}