package com.youth4work.HSSC_2023.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.squareup.picasso.Picasso;
import com.youth4work.HSSC_2023.R;
import com.youth4work.HSSC_2023.network.PrepApi;
import com.youth4work.HSSC_2023.network.PrepService;
import com.youth4work.HSSC_2023.network.model.Answers;
import com.youth4work.HSSC_2023.network.model.request.PostVote;
import com.youth4work.HSSC_2023.network.model.response.VoteResponse;
import com.youth4work.HSSC_2023.util.Constants;
import com.youth4work.HSSC_2023.util.PreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Samar on 5/13/2016.
 */
public class AnswersItem extends AbstractItem<AnswersItem, AnswersItem.ViewHolder> {
    public Answers answers;
    private PrepService prepService;
    Context context;
    int answerId;
    long userId;

    public AnswersItem(Answers answers, Context context, int answerId, long userId) {
        this.answers = answers;
        this.context = context;
        this.answerId = answerId;
        this.userId = userId;
    }

    public int getType() {
        return R.id.attempt_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.answers_layout2;
    }

    @Override
    public void bindView(ViewHolder viewHolder) {
        super.bindView(viewHolder);

        prepService = PrepApi.createService(PrepService.class, PreferencesManager.instance(context).getToken());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            viewHolder.txtAnswer.setText(Html.fromHtml(answers.getAnswer(),Html.FROM_HTML_MODE_COMPACT));
        }
        else {
            viewHolder.txtAnswer.setText(Html.fromHtml(answers.getAnswer()));
        }
        String text = "By "+Constants.getFirstName(answers.getAnswerByName())+" • "+"Comments "+answers.getTotalComment();
        viewHolder.txtAnswerByName.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
        viewHolder.txtVotes.setText(Integer.toString(answers.getARank()));
        //viewHolder.txtAnswerByName.setText(answers.getAnswerByName());
        //  viewHolder.txtTotalComment.setText("Comment "+ answers.getTotalComment());
        // viewHolder.txtAnswerByName.setText(String.valueOf("Vote "+answers.getARank()));
//        viewHolder.txtLastModified.setText((Constants.getDate(answers.getLastModified())));
        Picasso.with(context).load(answers.getAnswerByPic()).into(viewHolder.imageView);

        viewHolder.btnDn.setOnClickListener(v -> {
            if(answers.AnswerByUserId==userId)
            {
                Toast.makeText(context, "Can't vote on your own answer", Toast.LENGTH_SHORT).show();
            }
            else
            {

                prepService.VoteForumAnswer(new PostVote(answerId, false, userId)).enqueue(new Callback<VoteResponse>() {
                    @Override
                    public void onResponse(Call<VoteResponse> call, @NonNull Response<VoteResponse> response) {
                        if (response.isSuccessful()) {
                            Boolean check = response.body().isVoteForumAnswerResult();
                            if (check)
                                Toast.makeText(context, "Already voted", Toast.LENGTH_SHORT).show();
                            else {
                                Toast.makeText(context, "Voted", Toast.LENGTH_SHORT).show();
                                viewHolder.txtVotes.setText(Integer.toString(answers.getARank() - 1));
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<VoteResponse> call, Throwable t) {

                    }
                });
            }
        });

        viewHolder.btnUp.setOnClickListener(v -> {
            if(answers.AnswerByUserId==userId)
            {
                Toast.makeText(context, "Can't vote on your own answer", Toast.LENGTH_SHORT).show();
            }
            else {
                prepService.VoteForumAnswer(new PostVote(answerId, true, userId)).enqueue(new Callback<VoteResponse>() {
                    @Override
                    public void onResponse(Call<VoteResponse> call, @NonNull Response<VoteResponse> response) {
                        if(response.isSuccessful()) {
                            Boolean check = response.body().isVoteForumAnswerResult();
                            if (check)
                                Toast.makeText(context, "Already voted", Toast.LENGTH_SHORT).show();
                            else {
                                Toast.makeText(context, "Voted", Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, "Voted", Toast.LENGTH_SHORT).show();
                                viewHolder.txtVotes.setText(Integer.toString(answers.getARank() + 1));
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<VoteResponse> call, Throwable t) {

                    }
                });
            }
        });


    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtAnswer;
        TextView txtAnswerByName;
        TextView txtVotes;
        LinearLayout btnUp;
        ImageView btnDn;
        CircularImageView imageView;


        ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtAnswer = itemView.findViewById(R.id.answer);
            txtAnswerByName = itemView.findViewById(R.id.AnswerByName);
            txtVotes = itemView.findViewById(R.id.totalvotes);
//          txtLastModified = (TextView) itemView.findViewById(R.id.LastModified);
            // txtARank = itemView.findViewById(R.id.ARank);
            imageView = itemView.findViewById(R.id.imageAnswer);
            btnUp =  itemView.findViewById(R.id.up);
            btnDn = itemView.findViewById(R.id.down);

        }
    }
}