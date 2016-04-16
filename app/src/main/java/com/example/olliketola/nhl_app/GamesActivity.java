package com.example.olliketola.nhl_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;


public class GamesActivity extends AppCompatActivity implements GetResponse {


    AsyncReuseJson requestServer;
    private ListView listView;
    public String data;
    private JSONObject job;
    private String date;
    public TextView currentDate;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calendar, menu);
        return true;
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentDate = (TextView)findViewById(R.id.date);
        DateFormat df = new SimpleDateFormat("dd MM yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        currentDate.setText(date);



        executeServerReq();
        requestServer.execute();


        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> listView, View v, int position, long id){

                Toast.makeText(getApplicationContext(), "Clicked item in row", Toast.LENGTH_SHORT).show();
                GameInfoDialog asd = new GameInfoDialog();
                asd.show(getSupportFragmentManager(), "str");
            }
        };

        listView = (ListView)findViewById(R.id.gamelist);
        listView.setOnItemClickListener(itemClickListener);


    }

    public void executeServerReq() {
        requestServer = new AsyncReuseJson("http://51.255.194.175/api.php?call=today",true, GamesActivity.this);
        requestServer.getResponse = this;
    }

    public String getData(String objects) {
        Log.e("Response", "----------" + objects);
        data = objects;

        if(data != null){
            try{

                ArrayList<String> home = new ArrayList<String>();
                ArrayList<String> visitor = new ArrayList<String>();
                ArrayList<String> hs = new ArrayList<String>();
                ArrayList<String> vs = new ArrayList<String>();
                ArrayList<String> _final = new ArrayList<String>();
                ArrayList<String> namehome = new ArrayList<String>();
                ArrayList<String> namevisitor = new ArrayList<String>();

                JSONArray arr = new JSONArray(data);

                for(int i = 0; i < arr.length(); i++){

                    JSONObject js = arr.getJSONObject(i);
                    String home1 = js.getString("home_short");
                    String visitor1 = js.getString("visitor_short");
                    String hs1 = js.getString("home_goals");
                    String vs1 = js.getString("visitor_goals");
                    String _final1 = js.getString("final");
                    String nameh = js.getString("home_team");
                    String namev = js.getString("visitor_team");

                    home.add(home1);
                    visitor.add(visitor1);
                    hs.add(hs1);
                    vs.add(vs1);
                    _final.add(_final1);
                    namehome.add(nameh);
                    namevisitor.add(namev);

                }

                GameList games = new GameList(this, home, visitor, hs, vs, _final, namehome, namevisitor);
                listView.setAdapter(games);

            }catch (JSONException e){

                Log.e("ERROR", "----------" + e);
            }
            // textView.setText(data);
        }
        return objects;
    }

}
