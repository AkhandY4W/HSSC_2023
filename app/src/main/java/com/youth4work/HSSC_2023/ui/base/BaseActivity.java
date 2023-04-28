package com.youth4work.HSSC_2023.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.youth4work.HSSC_2023.infrastructure.UserManager;
import com.youth4work.HSSC_2023.network.PrepApi;
import com.youth4work.HSSC_2023.network.PrepService;
import com.youth4work.HSSC_2023.network.model.AutoCompleteData;
import com.youth4work.HSSC_2023.network.model.request.GcmRegister;
import com.youth4work.HSSC_2023.network.model.request.LoginRequest;
import com.youth4work.HSSC_2023.network.model.request.UserRegister;
import com.youth4work.HSSC_2023.network.model.response.LoginResponse;
import com.youth4work.HSSC_2023.ui.quiz.ReviewTestActivity;
import com.youth4work.HSSC_2023.util.Constants;
import com.youth4work.HSSC_2023.util.PreferencesManager;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivity extends RxAppCompatActivity {

    public static PrepService prepService;
    protected Context self;
    protected static AutoCompleteData mAutoCompleteData;
    protected static UserRegister user;
    protected PreferencesManager mPreferencesManager;
    protected UserManager mUserManager;
    protected String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self = this;
        //prepService = PrepApi.provideRetrofit();
        mPreferencesManager = PreferencesManager.instance(this);
        mUserManager = UserManager.getInstance(this);
        Constants.logEvent4FCM(self,self.getClass().getSimpleName(),self.getClass().getSimpleName(),new Date(),"Screen","SELECT_CONTENT");
        String token=mPreferencesManager.getToken();
        if((token == null)) {
            LoginRequest loginRequest = new LoginRequest("YOUTH4WORKAPP", "YOUTH4WORK@14FEB");
            prepService = PrepApi.createService(PrepService.class);
            prepService.getAuth(loginRequest).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String authtoken = response.body().getToken();
                        mPreferencesManager.settoken(authtoken);
                        prepService = PrepApi.createService(PrepService.class, mPreferencesManager.getToken());

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    //Toast.makeText(context, "Somethig went wrong,Please try again...", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            prepService=PrepApi.createService(PrepService.class,mPreferencesManager.getToken());
        }

    }

    protected void inviteFriends() {
        Constants.logEvent4FCM(self,"inviteFriends","inviteFriends",new Date(),"Screen","SELECT_CONTENT");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String category = mUserManager.getCategory().getCategory() != null ? mUserManager.getCategory().getCategory() : "competitive exams";
        sendIntent.putExtra(Intent.EXTRA_TEXT, "I am preparing for "+ category+" with Prep App. Are you preparing for any competitive exam? With Prep App, yes you can. Download today.https://play.google.com/store/apps/details?id=com.youth4work.prepapp&referrer=utm_source%3D"+ mUserManager.getUser().getUserName());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    public View.OnClickListener errorClickListener = v -> {
        finish();
        startActivity(getIntent());
    };
    public void doRegisterGcmUser(Boolean FirstCheck) {
        String deviceId = android.provider.Settings.Secure.getString(this.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        String gcmToken = mPreferencesManager.getGCMToken();
        if (gcmToken != null && deviceId != "") {
            GcmRegister gcmRegister = new GcmRegister(FirstCheck?0:mUserManager.getUser().getUserId(), deviceId,gcmToken,FirstCheck?"app@youth4work.com": mUserManager.getUser().getEmailID(),getApplication().getPackageName());
            prepService.registerGcm(gcmRegister).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                    if (response.isSuccessful()&&FirstCheck==false) {
                        mPreferencesManager.setGCMRegistered(true);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }
    public void SubmitMockTest(int testId, long userid) {

        prepService.SubmitTest(testId, userid).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body()) {
                        Toast.makeText(BaseActivity.this, "Test submit sucessfully", Toast.LENGTH_SHORT).show();
                        Calendar currentSelectedDate = Calendar.getInstance();
                        long diff = (currentSelectedDate.getTimeInMillis() - mUserManager.getStartDate().getTime());
                        String dayNo = Long.toString(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                        ReviewTestActivity.show(self, currentSelectedDate.getTime(), dayNo, 20);

                    } else {
                        Toast.makeText(BaseActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(BaseActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
