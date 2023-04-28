package com.youth4work.HSSC_2023.ui.home;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.youth4work.HSSC_2023.R;
import com.youth4work.HSSC_2023.ui.startup.SplashActivity;
import com.youth4work.HSSC_2023.util.CheckNetwork;

public class NoInternetActivity extends AppCompatActivity {
    Button btnTryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        btnTryAgain = (Button) findViewById(R.id.btntryagain);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!CheckNetwork.isInternetAvailable(getApplication())) {
                    {
                        Intent intent = new Intent(NoInternetActivity.this, NoInternetActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                } else {
                    finish();
                    Intent intent = new Intent(NoInternetActivity.this, SplashActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
