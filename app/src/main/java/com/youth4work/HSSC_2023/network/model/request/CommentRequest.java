package com.youth4work.HSSC_2023.network.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ravindra on 17/03/16.
 */
public class CommentRequest {


    /**
     * UserID : 72
     * Questionid : 1000
     * Comment : test
     */

    @SerializedName("UserID")
    private long mUserID;
    @SerializedName("Questionid")
    private int mQuestionid;
    @SerializedName("Comment")
    private String mComment;

    public CommentRequest(long mUserID, int mQuestionid, String mComment) {
        this.mUserID = mUserID;
        this.mQuestionid = mQuestionid;
        this.mComment = mComment;
    }

    public long getUserID() {
        return mUserID;
    }

    public void setUserID(int userID) {
        mUserID = userID;
    }


    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public int getmQuestionid() {
        return mQuestionid;
    }

    public void setmQuestionid(int mQuestionid) {
        this.mQuestionid = mQuestionid;
    }
}
