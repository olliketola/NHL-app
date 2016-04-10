package com.example.olliketola.nhl_app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class SearchPlayersActivity extends AppCompatActivity implements GetResponse {

    private Button search;
    private EditText text;
    public AsyncReuseJson req;
    public ListView listView;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_players);

        search = (Button) findViewById(R.id.search);
        text = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listView2);

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                executeServerReq();
                req.execute();

            }
        });

    }

    public void executeServerReq() {
        String params = (String) text.getText().toString();
        params = params.replaceAll("[^a-zA-Z0-9]", "");
        req = new AsyncReuseJson("http://51.255.194.175/api.php?name=" + params, true, SearchPlayersActivity.this);
        req.getResponse = this;
    }

    @Override
    public String getData(String objects) {
        Log.e("Response", "----------" + objects);
        data = objects;

        if(data != null){
            try{

                ArrayList<String>names = new ArrayList<String>();
                JSONArray arr = new JSONArray(data);

                for(int i = 0; i < arr.length(); i++){

                    JSONObject js = arr.getJSONObject(i);
                    String name = js.getString("fullname");
                    names.add(name);

                }

                ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);
                listView.setAdapter(ad);

            }catch (JSONException e){

                Log.e("ERROR", "----------" + e);
            }
            // textView.setText(data);
        }
        return objects;
    }
}