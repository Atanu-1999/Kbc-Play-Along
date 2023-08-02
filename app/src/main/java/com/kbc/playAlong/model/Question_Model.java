package com.kbc.playAlong.model;

public class Question_Model {
    private String contest_question;
    private String option_one;
    private String option_two;
    private String option_three;
    private String option_four;
    private String answer;
    private String question_id;

    public Question_Model(String contest_question, String option_one, String option_two, String option_three, String option_four, String answer, String question_id) {
        this.contest_question = contest_question;
        this.option_one = option_one;
        this.option_two = option_two;
        this.option_three = option_three;
        this.option_four = option_four;
        this.answer = answer;
        this.question_id = question_id;
    }

    public String getContest_question() {
        return contest_question;
    }

    public void setContest_question(String contest_question) {
        this.contest_question = contest_question;
    }

    public String getOption_one() {
        return option_one;
    }

    public void setOption_one(String option_one) {
        this.option_one = option_one;
    }

    public String getOption_two() {
        return option_two;
    }

    public void setOption_two(String option_two) {
        this.option_two = option_two;
    }

    public String getOption_three() {
        return option_three;
    }

    public void setOption_three(String option_three) {
        this.option_three = option_three;
    }

    public String getOption_four() {
        return option_four;
    }

    public void setOption_four(String option_four) {
        this.option_four = option_four;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }
}
