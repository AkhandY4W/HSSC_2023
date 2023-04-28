package com.youth4work.HSSC_2023.ui.workmail;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.youth4work.HSSC_2023.R;
import com.youth4work.HSSC_2023.network.model.response.Message;
import com.youth4work.HSSC_2023.ui.base.BaseActivity;
import com.youth4work.HSSC_2023.ui.workmail.fragment.ConversationsFragment;


public class ConversationActivity extends BaseActivity {
    Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("msg");
        message = gson.fromJson(strObj, Message.class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Bundle bundle = new Bundle();
        if(message!=null && message.getSenderUserId()!=null && mUserManager.getUser().getUserId()!=0 && mPreferencesManager!=null) {
            mPreferencesManager.setid(message.getSenderUserId());
            bundle.putString("userid", message.getSenderUserId());
            ConversationsFragment fragInfo = new ConversationsFragment();
            fragInfo.setArguments(bundle);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment2, fragInfo);
            fragmentTransaction.commit();
        }
    }

}
