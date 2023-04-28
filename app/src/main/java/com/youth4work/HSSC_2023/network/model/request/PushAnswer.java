package com.youth4work.HSSC_2023.network.model.request;

public class PushAnswer {

    private int AnswerID;
    private int result;
    private int questionid;
    private long answerby;
    private int defaulttime;
    private int timetaken;

    public PushAnswer(int selectedAnswerId, int mWinOrLose, int questionId, long userId, int time2solve, int mTimeTaken) {
        this.AnswerID = selectedAnswerId;
        this.result = mWinOrLose;
        this.questionid = questionId;
        this.answerby = userId;
        this.defaulttime = time2solve;
        this.timetaken = mTimeTaken;
    }

    public int getAnswerID() {
        return AnswerID;
    }

    public void setAnswerID(int answerID) {
        AnswerID = answerID;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public long getAnswerby() {
        return answerby;
    }

    public void setAnswerby(int answerby) {
        this.answerby = answerby;
    }

    public int getDefaulttime() {
        return defaulttime;
    }

    public void setDefaulttime(int defaulttime) {
        this.defaulttime = defaulttime;
    }

    public int getTimetaken() {
        return timetaken;
    }

    public void setTimetaken(int timetaken) {
        this.timetaken = timetaken;
    }
}
