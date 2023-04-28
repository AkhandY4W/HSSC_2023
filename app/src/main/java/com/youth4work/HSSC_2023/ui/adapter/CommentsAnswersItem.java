package com.youth4work.HSSC_2023.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.squareup.picasso.Picasso;
import com.youth4work.HSSC_2023.R;
import com.youth4work.HSSC_2023.network.model.CommentsAnswersData;
import com.youth4work.HSSC_2023.util.Constants;

/**
 * Created by Samar on 5/16/2016.
 */

public class CommentsAnswersItem extends AbstractItem<CommentsAnswersItem, CommentsAnswersItem.ViewHolder> {
    public CommentsAnswersData commentsAnswers;
    String CommentDate;
    Context context;

    public CommentsAnswersItem(CommentsAnswersData commentsAnswers, Context context) {
        this.commentsAnswers = commentsAnswers;
        this.context = context;
        CommentDate = commentsAnswers.getCommentDate();
        CommentDate = Constants.getDate(CommentDate);


    }

    public int getType() {
        return R.id.attempt_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.layout_comment;
    }

    @Override
    public void bindView(ViewHolder viewHolder) {
        super.bindView(viewHolder);

        viewHolder.txtComment.setText(Html.fromHtml(commentsAnswers.getComment()));
        viewHolder.txtCreatedByName.setText("By "+commentsAnswers.getCommentByName());
        //viewHolder.txtLastModified.setText((CommentDate));
        Picasso.with(context).load(commentsAnswers.getCommentByPic()).into(viewHolder.imageView);


    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtComment;
        TextView txtCreatedByName;
        //TextView txtLastModified;
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtComment = itemView.findViewById(R.id.answer);
            txtCreatedByName = itemView.findViewById(R.id.AnswerByName);
            //txtLastModified = (TextView) itemView.findViewById(R.id.date);
            imageView =  itemView.findViewById(R.id.imageAnswer);

        }
    }
}

