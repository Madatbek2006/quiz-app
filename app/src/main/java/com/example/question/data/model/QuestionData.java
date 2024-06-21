package com.example.question.data.model;

public class QuestionData {
    private String questionTest;
    private String[]variants;
    private int correct;

    public QuestionData(String questionTest, String variant1, String variant2, String variant3, String variant4, int correct) {
        this.questionTest = questionTest;
        variants=new String[4];
        this.variants[0] = variant1;
        this.variants[1] = variant2;
        this.variants[2] = variant3;
        this.variants[3] = variant4;
        this.correct = correct;
    }

    public String getQuestionTest() {
        return questionTest;
    }

    public String[] getVariants() {
        return variants;
    }

    public int getCorrect() {
        return correct;
    }
}
