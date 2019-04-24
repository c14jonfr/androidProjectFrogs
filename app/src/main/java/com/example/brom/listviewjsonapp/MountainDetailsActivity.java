package com.example.brom.listviewjsonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MountainDetailsActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    String matterhorn = "matterhorn";
    String montblanc = "montblanc";
    String denali = "denali";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_details);

        String mountainInfo = getIntent().getExtras().getString("MOUNTAININFO");
        textView = (TextView)findViewById(R.id.berra);
        textView.setText(mountainInfo);



    }
}
