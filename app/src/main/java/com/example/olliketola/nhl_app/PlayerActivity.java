package com.example.olliketola.nhl_app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class PlayerActivity extends AppCompatActivity  {

    private String id;
    private ImageView imgView;
    private  Drawable x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        AsyncTask task = new AsyncTask() {
            @Override
            protected Drawable doInBackground(Object[] params) {

                try{
                    id = (String)getIntent().getExtras().get("id");
                    Log.e("Response", "----------" + id);
                    InputStream is = (InputStream) new URL("http://3.cdn.nhle.com/photos/mugs/"+id+".jpg").getContent();
                    Drawable d = Drawable.createFromStream(is,"player");
                    Log.e("Response", "----------" + d);
                    return d;
                }catch (Exception e) {
                    Log.e("Error setting picture", "----------" + e);
                    return null;
                }
            }
        };

        imgView =(ImageView)findViewById(R.id.playerimage);
        try{

            x = (Drawable)task.execute().get();
            imgView.setImageDrawable(x);
        }catch (Exception e){

            Log.e("Error setting picture", "----------" + e);
        }
    }


}

