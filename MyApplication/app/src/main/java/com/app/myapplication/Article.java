package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.myapplication.RssFeed.RssFeed;
import com.app.myapplication.RssFeed.RssItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.parceler.Parcels;

import java.util.List;


public class Article extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        String title = getIntent().getStringExtra("title");
        String link = getIntent().getStringExtra("link");
        String description = getIntent().getStringExtra("description");
        String image = getIntent().getStringExtra("image");
        String guid = getIntent().getStringExtra("guid");
        String comments = getIntent().getStringExtra("comments");
        String pubDate = getIntent().getStringExtra("pubDate");

        Button shareBtn = (Button) findViewById(R.id.share);
        TextView titleTextview = (TextView) findViewById(R.id.title);
        TextView pubDateTextview = (TextView) findViewById(R.id.pubDate);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        WebView webView = (WebView) findViewById(R.id.description);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(image , imageView);
        titleTextview.setText(title);
        pubDateTextview.setText(keepDayDateTime(pubDate));
        webView.loadData(description, "text/html; charset=utf-8", "UTF-8");

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "body";
                intent.putExtra(Intent.EXTRA_SUBJECT , shareBody);
                startActivity(Intent.createChooser(intent,"Sharing"));
            }
        });

    }


    public String keepDayDateTime(String date){
        String[] edited = date.split(" ");
        String[] time =  edited[3].split(":");
        return edited[0] + " " + edited[2] + " " + edited[1] + " " + edited[5] + " " +time[0] +":" + time[1];
    }


}