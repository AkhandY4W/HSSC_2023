/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.youth4work.HSSC_2023.util;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.util.Patterns;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.youth4work.HSSC_2023.network.PrepApi;
import com.youth4work.HSSC_2023.network.PrepService;
import com.youth4work.HSSC_2023.network.model.Category;
import com.youth4work.HSSC_2023.network.model.request.LoginRequest;
import com.youth4work.HSSC_2023.network.model.response.LoginResponse;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Constants {
    protected static AppEventsLogger appEventsLogger;
    protected static FirebaseAnalytics mFirebaseAnalytics;
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    //public static final int CatID=143;
    public static final String CatName="Practice Tests";
    public static final String ScreenPrefix="AIIMS_MBBS Exam Screen ";

    public static final int PAGE_LIMIT = 10;

   /* public static List<Category> getCategory()
    {
        List<Category> mCategories =new ArrayList<Category>();
        mCategories.add(new Category(11,"AIIMS"));
        mCategories.add(new Category(406,"AIIMS Nursing Entrance Examination"));
        return mCategories;
    }*/

    public static List<Category> getCategory()
    {
        List<Category> mCategories =new ArrayList<Category>();
        mCategories.add(new Category(43324,"HSSC Healthcare Sector Skill Council",294,"NSDC SKILLS CERTIFICATION","https://www.static-contents.youth4work.com/prep/img/categoryimages/dd923e4a-c034-474b-9bc1-7bf5c6cea78c.png","https://www.static-contents.youth4work.com/prep/img/categoryimages/f1150bba-0f9f-467f-a506-40dd439b0108.png","Healthcare Sector Skill Council (HSSC) has been registered under the Societies Registration Act 1860 as a “not-for profit” organization, created by National Skill Development Corporation (NSDC), Confederation of Indian Industry (CII) and leading healthcare service providers representing both public and private sector.","7730","2521046"));
       // mCategories.add(new Category(3169,"AIIMS Nursing Entrance Examination",406,"Medical","https://www.static-contents.youth4work.com/prep/img/categoryimages/96090c04-ca83-4b0c-a3f7-c63e6bbe2690_subcategory_800x450.png","https://www.static-contents.youth4work.com/prep/img/categoryimages/96090c04-ca83-4b0c-a3f7-c63e6bbe2690_subcategory_800x450.png","AIIMS Nursing Mock Test can be practiced here to prepare for the upcoming AIIMS Nursing Entrance Examination for admission in B.Sc and M.Sc Nursing courses in the university. Start attempting AIIMS nursing online test to enhance your chances of cracking the entrance exam and go one step ahead in making a career in the Nursing sector.","870","96828"));
        return mCategories;
    }

    public static String EncodeMaster(String val)
    {
        return val != null ? val.replace(".", "_").replace("-", "_").replace(" ", "-") : "";
    }
    public static void sendScreenImageName(Tracker mTracker, String name) {

        // [START screen_view_hit]

        mTracker.setScreenName(ScreenPrefix+" Screen " + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]
    }
    public static String getDateMonthTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("d MMM 'at' hh:mm a");
        Date d = null;
        try {
            d = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = output.format(d);
        return formattedTime;

/*
        time = time.replace("/Date(", "");
        time = time.replace("+0530)/", "");
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.valueOf(time));

        Date date = cal.getTime();
        //Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        return new SimpleDateFormat("d MMM 'at' hh:mm a").format(date);
*/
    }
    public static String getFloat2digit(Float text) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        String input = formatter.format(text);
        return input;
    }

    public static String getDateMonth(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("d MMM");
        Date d = null;
        try {
            d = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = output.format(d);
        return formattedTime;

    }

    public static void logEvent4FCM(Context context, String itemID, String itemName, Date date, String category, String eventType) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        appEventsLogger= AppEventsLogger.newLogger(context);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemID);
        bundle.putString(FirebaseAnalytics.Param.START_DATE, new SimpleDateFormat("yyyy-MM-dd").format(date));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);
        if (eventType.equals("VIEW_ITEM")) {
            bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, category);
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
            appEventsLogger.logEvent(AppEventsConstants.EVENT_NAME_VIEWED_CONTENT,bundle);
        } else if (eventType.equals("SELECT_CONTENT")) {
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, category);
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            appEventsLogger.logEvent(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE,bundle);
        }
    }

    public static void logLoginEventFb (long userId, String userName,Context context) {
        Date obDate = new Date();
        Bundle params = new Bundle();
        params.putLong("UserId", userId);
        params.putString("UserName", userName);
        params.putString("loginDate", obDate.toString());
        AppEventsLogger appEventsLogger=AppEventsLogger.newLogger(context);
        AppEventsLogger.setUserID(String.valueOf(userId));
        appEventsLogger.logEvent("Login", params);
    }
    public static String capitalizeWord(String str){
        String words[]=str.split("\\s");
        String capitalizeWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeWord.trim();
    }

    public static void update(Context context)    {

        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.youth4work.AIIMS_MBBS")));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.youth4work.AIIMS_MBBS")));
        }
        ((Activity)context).finish();
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        if(html == null){
            // return an empty spannable if the html is null
            return new SpannableString("");
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }
    public static String getDate(String time)  {

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);
        // System.out.println(formattedDate);
        return formattedDate;
        /*   time = time.replace("/Date(", "");
        time = time.replace("+0530)/", "");
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.valueOf(time));
        return DateFormat.format("dd-MM-yyyy", cal).toString();*/
    }
    public static void getAuthToken(Context context) {
        PrepService prepService;
        LoginRequest loginRequest = new LoginRequest("YOUTH4WORKAPP", "YOUTH4WORK@14FEB");
        prepService = PrepApi.createService(PrepService.class);
        prepService.getAuth(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String authtoken = response.body().getToken();
                    PreferencesManager.instance(context).settoken(authtoken);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                //Toast.makeText(context, "Somethig went wrong,Please try again...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static final boolean IS_DEBUGGABLE = false;
    public static final String LOG_TAG = "PrepGuru";
//Deeplinking

    public static final String DEEPLINKING_SCHEME = "y4w://";
    public static void log(String msg) {
        if(IS_DEBUGGABLE)
            Log.i(LOG_TAG, msg);
    }
    public static String getFirstName(String firstname){
        String s = firstname;
        String words[] = s.split(" ");
        String firstName = words[0];
        return firstName;
    }
    //get EmailID
    public static String getPrimaryEmailID(Context context)
    {
        return getInfofromAcc(context, Patterns.EMAIL_ADDRESS);
    }


    //get Mobile
    public static String getMobileNo(Context context)
    {
        TelephonyManager tm = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        String number = tm.getLine1Number();
        if(number==null) {
            number= getInfofromAcc(context,Patterns.PHONE);
        }
        return number!=null?number:"";
    }
    public static String getInfofromAcc(Context context,Pattern p)
    {
        String possibleEmail="";
        Pattern emailPattern = p; // API level 8+
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS)
                == PackageManager.PERMISSION_GRANTED) {
            Account[] accounts = AccountManager.get(context).getAccounts();
            for (Account account : accounts) {
                if (emailPattern.matcher(account.name).matches()) {
                    possibleEmail = account.name;
                }
            }
        }
        return possibleEmail;
    }
}
