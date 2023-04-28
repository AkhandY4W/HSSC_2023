package com.youth4work.HSSC_2023.ui.startup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.TextView;


import com.youth4work.HSSC_2023.R;
import com.youth4work.HSSC_2023.network.model.Category;
import com.youth4work.HSSC_2023.ui.base.BaseActivity;
import com.youth4work.HSSC_2023.ui.home.CategoryExamsActivity;
import com.youth4work.HSSC_2023.ui.views.PrepButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GettingStartedActivity extends BaseActivity {

    @Nullable
    @BindView(R.id.txt_header)
    TextView txtHeader;
    @Nullable
    @BindView(R.id.txt_message)
    TextView txtMessage;
    @Nullable
    @BindView(R.id.btn_get_started)
    PrepButton btnGetStarted;

    public static void show(@NonNull Context fromActivity) {
        Intent intent = new Intent(fromActivity, GettingStartedActivity.class);
        fromActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_get_started)
    void OnGetStartedClicked() {
        GettingStartedActivity.this.finish();
        CategoryExamsActivity.show(this, new Category(1));
    }
}
