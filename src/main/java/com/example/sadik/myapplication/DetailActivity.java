package com.example.sadik.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.sadik.myapplication.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        String imageurl=intent.getStringExtra(EXTRA_URL);
        String CreatorName=intent.getStringExtra(MainActivity.EXTRA_CREATOR);
        String Content=intent.getStringExtra(MainActivity.EXTRA_CONTENT);
        String HaberUrl=intent.getStringExtra(MainActivity.EXTRA_HABERURL);
        String Published=intent.getStringExtra(MainActivity.EXTRA_PUBLİSHED);

        ImageView ımageView=findViewById(R.id.ımgmain);
        TextView genelbaslik=findViewById(R.id.genelbaslik);
        TextView published=findViewById(R.id.published);
        TextView url=findViewById(R.id.url);

        TextView content=findViewById(R.id.content);

        Picasso.with(this).load(imageurl).fit().centerInside().into(ımageView);
        genelbaslik.setText(CreatorName);
        published.setText(Published);
        url.setText(HaberUrl);
        content.setText(Content);

    }
}
