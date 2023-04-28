package com.youth4work.HSSC_2023.network.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Samar on 5/18/2016.
 */
public class VoteResponse {

    @SerializedName("VoteForumAnswerResult")
    private boolean VoteForumAnswerResult;

    public boolean isVoteForumAnswerResult() {
        return VoteForumAnswerResult;
    }

    public void setVoteForumAnswerResult(boolean voteForumAnswerResult) {
        VoteForumAnswerResult = voteForumAnswerResult;
    }
}
