package com.central.backend.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Entry {
    private String question;
    private String answer;
    private String questionType;
    private String dataSource;
    private String keyword;
    private String questionCategory;
    private String equipmentCategory;

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setEquipmentCategory(String equipmentCategory) {
        this.equipmentCategory = equipmentCategory;
    }

    public String getEquipmentCategory() {
        return equipmentCategory;
    }
}
