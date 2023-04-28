package com.youth4work.HSSC_2023;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Rating extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;
    private TextView txtRating;
    Button btnCancle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rating);



        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
        addListenerOnRatingBar();
        addListenerOnButton();

    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));
                if (ratingBar.getRating() < 4 ) {

                    txtRating.setText("Thanks for Rating \n Please give feedback to improve the app");

                } else {
                    txtRating.setText(" So glad you love the app \n Please give me rate on Play store");

                }

            }
        });
    }

    public void addListenerOnButton() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        txtRating = (TextView) findViewById(R.id.txtRating);
        //if click on me, then display the current rating value.
        btnSubmit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (ratingBar.getRating() <4) {

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto: appsupport@youth4work.com"));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name) + " Feedback");
                    startActivity(Intent.createChooser(emailIntent, "Send feedback"));
                    finish();

                } else {

                    String url = "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}