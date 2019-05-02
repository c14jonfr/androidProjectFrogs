package com.example.brom.listviewjsonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class FrogDetailsActivity extends AppCompatActivity {

    TextView textView;
    TextView photoTV;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frog_details);

        String frogInfo = getIntent().getExtras().getString("FROGINFO");
        String profile = "Photo by: ";
        profile+=getIntent().getExtras().getString("license");
        String imageurl = getIntent().getExtras().getString("IMGURL");
        imageView = (ImageView)findViewById(R.id.imageView);
        Picasso.get().load(imageurl).fit().centerCrop().into(imageView);
        textView = (TextView)findViewById(R.id.berra);
        photoTV = (TextView) findViewById(R.id.photoTV);
        photoTV.setText(profile);
        textView.setText(frogInfo);

        Log.e("berra", imageurl);

        photoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });









    }


}
