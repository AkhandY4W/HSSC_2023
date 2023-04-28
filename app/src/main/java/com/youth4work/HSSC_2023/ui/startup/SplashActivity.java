package com.youth4work.HSSC_2023.ui.startup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;

//import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.youth4work.HSSC_2023.PrepApplication;

//import com.youth4work.AIIMS_MBBS.infrastructure.gcm.RegistrationIntentService;
import com.youth4work.HSSC_2023.R;
import com.youth4work.HSSC_2023.network.model.Category;
import com.youth4work.HSSC_2023.ui.base.BaseActivity;
import com.youth4work.HSSC_2023.ui.home.CategoryExamsActivity;
import com.youth4work.HSSC_2023.ui.home.DashboardActivity;
import com.youth4work.HSSC_2023.util.Constants;
import com.youth4work.HSSC_2023.util.DeeplinkingManager;
import com.youth4work.HSSC_2023.util.PreferencesManager;

import java.util.List;

//import io.intercom.android.sdk.Intercom;
//import io.intercom.android.sdk.identity.Registration;
import bolts.AppLinks;


public class SplashActivity extends BaseActivity implements
        DeeplinkingManager.DeepLinkListener {


    @Override
    protected void onStart() {
        super.onStart();

        // Check if the intent contains an AppInvite and then process the referral information.
        Intent intent = getIntent();
        if (AppInviteReferral.hasReferral(intent)) {
            processReferralIntent(intent);
        }
    }

    // [START process_referral_intent]
    private void processReferralIntent(Intent intent) {
        // Extract referral information from the intent
        // String invitationId = AppInviteReferral.getInvitationId(intent);
        String deepLink = AppInviteReferral.getDeepLink(intent);
        DeeplinkingManager.getInstance(SplashActivity.this).handleDeeplinkingSupport(deepLink);
        // [END_EXCLUDE]
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FacebookSdk.sdkInitialize(SplashActivity.this);

        String token= PreferencesManager.instance(SplashActivity.this).getToken();
        if(token==null){
            Constants.getAuthToken(SplashActivity.this);
        }
        Intent startingIntent = getIntent();
        Bundle pushData = startingIntent.getBundleExtra("push");
        if (pushData != null) {
            final AppEventsLogger logger = AppEventsLogger.newLogger(SplashActivity.this);
            logger.logPushNotificationOpen(pushData, startingIntent.getAction());
        }
        if (mUserManager.isUserLoggedIn()) {
            if(mUserManager.getUser().getSelectedCatID()==0){
                Intent intent = new Intent(SplashActivity.this, CategoryExamsActivity.class);
                startActivity(intent);
            }
            else {
                List<Category> mcat=Constants.getCategory();
                boolean hasSelected = false;
                for (Category category : mcat) {
                    if (category.getCatid() == mUserManager.getUser().getSelectedCatID() ) {
                        mUserManager.setCategory(category);
                        hasSelected = true;
                    }
                }
                if(hasSelected){
                    DashboardActivity.show(self, mUserManager.getCategory());
                    Constants.logLoginEventFb(mUserManager.getUser().getUserId(),mUserManager.getUser().getUserName(),SplashActivity.this);
                    PrepApplication application = (PrepApplication) getApplication();
                    Tracker mTracker = application.getDefaultTracker();
                    mTracker.set("&uid", String.valueOf(mUserManager.getUser().getUserId()));
                    mTracker.send(new HitBuilders.EventBuilder()
                            .setCategory("UX")
                            .setAction("User Sign In")
                            .build());
                    if (startingIntent.getStringExtra("deeplinkurl") != null) {
                        DeeplinkingManager.getInstance(SplashActivity.this).handleDeeplinkingSupport(getIntent().getStringExtra("deeplinkurl"));
                    } else {
                        String appLinkAction = startingIntent.getAction();
                        Uri applinkTargetUrl =
                                AppLinks.getTargetUrlFromInboundIntent(SplashActivity.this, startingIntent);
                        if (applinkTargetUrl != null) {
                        } else {
                            applinkTargetUrl = startingIntent.getData();
                        }
                        if (Intent.ACTION_VIEW.equals(appLinkAction) && applinkTargetUrl != null) {
                            String deepLink = applinkTargetUrl.toString();
                            DeeplinkingManager.getInstance(SplashActivity.this).handleDeeplinkingSupport(deepLink);
                        }
                        DeeplinkingManager deepLinkManager = new DeeplinkingManager(SplashActivity.this, SplashActivity.this);
                        deepLinkManager.checkForInvites(true);
                        SplashActivity.this.finish();
                    }

                }
                else {
                    startActivity(new Intent(SplashActivity.this, CategoryExamsActivity.class));
                }

            }

        }else {
            //Intercom.client().registerUnidentifiedUser();
            LoginActivity.show(self);
            if (startingIntent.getStringExtra("deeplinkurl") != null) {
                DeeplinkingManager.getInstance(SplashActivity.this).handleDeeplinkingSupport(getIntent().getStringExtra("deeplinkurl"));
            } else {
                String appLinkAction = startingIntent.getAction();
                Uri applinkTargetUrl =
                        AppLinks.getTargetUrlFromInboundIntent(SplashActivity.this, startingIntent);
                if (applinkTargetUrl != null) {
                } else {
                    applinkTargetUrl = startingIntent.getData();

                }
                if (Intent.ACTION_VIEW.equals(appLinkAction) && applinkTargetUrl != null) {
                    String deepLink = applinkTargetUrl.toString();
                    DeeplinkingManager.getInstance(SplashActivity.this).handleDeeplinkingSupport(deepLink);

                }
                DeeplinkingManager deepLinkManager = new DeeplinkingManager(SplashActivity.this, SplashActivity.this);
                deepLinkManager.checkForInvites(true);
                SplashActivity.this.finish();
            }
        }

    }

    @Override
    public void onConnectionError(String errorMessage) {

    }

    @Override
    public void onBackPressed() {
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
    }
}
