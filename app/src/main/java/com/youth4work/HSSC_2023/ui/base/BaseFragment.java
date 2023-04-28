package com.youth4work.HSSC_2023.ui.base;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.youth4work.HSSC_2023.PrepApplication;
import com.youth4work.HSSC_2023.R;
import com.youth4work.HSSC_2023.infrastructure.UserManager;
import com.youth4work.HSSC_2023.ui.home.NoInternetActivity;
import com.youth4work.HSSC_2023.util.CheckNetwork;
import com.youth4work.HSSC_2023.util.PreferencesManager;

public class BaseFragment extends Fragment {

    protected Context self;
    protected PreferencesManager mPreferencesManager;
    protected UserManager mUserManager;
    private boolean mActivityStopped;
    protected Context mContext;
    protected Resources mResources;
    protected int mFragmentTaskId = -1;
    protected View mView;
    protected LayoutInflater layoutInflater;
    protected View containerView;
    private View rootView = null;
    protected PrepApplication mAppState;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = getActivity();
        mPreferencesManager = PreferencesManager.instance(getActivity());
        mUserManager = UserManager.getInstance(getActivity());
        layoutInflater = LayoutInflater.from(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_youth, container, false);
        mContext = getActivity();
        mResources = mContext.getResources();
        mAppState = (PrepApplication) getActivity().getApplicationContext();
        layoutInflater = LayoutInflater.from(getActivity());
        if(!CheckNetwork.isInternetAvailable(getContext())){
            {
                Intent intent=new Intent(getContext(), NoInternetActivity.class);
                startActivity(intent);
            }
        }
        return rootView;
    }
    protected View setContentView(int layoutId, View parentView) {
        layoutInflater = LayoutInflater.from(getActivity().getBaseContext());
        LinearLayout containerLayout = parentView.findViewById(R.id.containerLayout);
        View view = layoutInflater.inflate(layoutId, containerLayout);
        return view;
    }
    @Override
    public void onStop() {
        mActivityStopped = true;

        super.onStop();
    }


    public boolean isActivityStopped() {
        return mActivityStopped;
    }

    @NonNull
    protected String getActivityName() {
        // This is a fallback which just logs the activity name if sub classes did not give activity name
        return getClass().getSimpleName();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
