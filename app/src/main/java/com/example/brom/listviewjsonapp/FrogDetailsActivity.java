package com.example.brom.listviewjsonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FrogDetailsActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frog_details);

        String frogInfo = getIntent().getExtras().getString("FROGINFO");

        String imageurl = getIntent().getExtras().getString("IMGURL");
        imageView = (ImageView)findViewById(R.id.imageView);
        Picasso.get().load(imageurl).fit().centerCrop().into(imageView);
        textView = (TextView)findViewById(R.id.berra);
        textView.setText(frogInfo);

        Log.e("berra", imageurl);









    }
}
