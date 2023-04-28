package com.youth4work.HSSC_2023.network.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Samar on 5/17/2016.
 */
public class CommentOnForumAnswerRequest {
    /*
{   
  "AnswerId": 649,
  "Comment": "good",
  "CommentByUserId":72
}     
    */

    public CommentOnForumAnswerRequest(int AnswerId, String Comment, long CommentByUserId) {
        this.AnswerId = AnswerId;
        this.Comment = Comment;
        this.CommentByUserId = CommentByUserId;
    }

    @SerializedName("AnswerId")
    private int AnswerId;
    @SerializedName("Comment")
    private String Comment;
    @SerializedName("CommentByUserId")
    private long CommentByUserId;

}
