package com.example.progress_report.model;


public class  Ques_data {
    public String question,option1,option2,option3,option4,answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOp1() {
        return option1;
    }

    public void setOp1(String option1) {
        this.option1 = option1;
    }

    public String getOp2() {
        return option2;
    }

    public void setOp2(String option2) {
        this.option2 = option2;
    }

    public String getOp3() {
        return option3;
    }

    public void setOp3(String option3) {
        this.option3 = option3;
    }

    public String getOp4() {
        return option4;
    }

    public void setOp4(String option4) {
        this.option4 = option4;
    }

    public String getAns() {
        return answer;
    }

    public void setAns(String answer) {
        this.answer = answer;
    }

    public Ques_data(String question, String op1, String op2, String op3, String op4, String ans) {
        this.question = question;
        this.option1 = op1;
        this.option2 = op2;
        this.option3 = op3;
        this.option4 = op4;
        this.answer = ans;

    }

    public Ques_data() {

    }
}
