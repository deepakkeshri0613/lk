package com.deepak.lk;

/**
 * Created by dsk on 13-Mar-18.
 */

public class Question {

    private String question;
    private String options[];
    private String typeOfQuestion;
    private String negativeMarks;
    private String correctAnswer;
    private int correctOptionIndex;
    private String solution;


    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }




    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getTypeOfQuestion() {
        return typeOfQuestion;
    }

    public void setTypeOfQuestion(String typeOfQuestion) {
        this.typeOfQuestion = typeOfQuestion;
    }

    public String getNegativeMarks() {
        return negativeMarks;
    }

    public void setNegativeMarks(String negativeMarks) {
        this.negativeMarks = negativeMarks;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }




}
