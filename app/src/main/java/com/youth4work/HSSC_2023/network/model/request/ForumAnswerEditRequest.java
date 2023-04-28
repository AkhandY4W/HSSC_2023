package com.youth4work.HSSC_2023.network.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Samar on 5/17/2016.
 */
public class ForumAnswerEditRequest {
    /*
    {
     "AnswerId": 649,
     "Answer": "bachelor degree",
     "AnswerByUserId":7
    }
     */
    public ForumAnswerEditRequest(int AnswerId,String Answer, long AnswerByUserId) {
        this.AnswerId = AnswerId;
        this.Answer = Answer;
        this.AnswerByUserId=AnswerByUserId;
    }
    @SerializedName("AnswerId")
    private int AnswerId;
    @SerializedName("Answer")
    private String Answer;
    @SerializedName("AnswerByUserId")
    private long AnswerByUserId;

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public int getAnswerId() {
        return AnswerId;
    }

    public void setAnswerId(int answerId) {
        AnswerId = answerId;
    }

    public long getAnswerByUserId() {
        return AnswerByUserId;
    }

    public void setAnswerByUserId(int answerByUserId) {
        AnswerByUserId = answerByUserId;
    }


}
