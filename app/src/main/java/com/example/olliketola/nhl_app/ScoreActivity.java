package com.example.olliketola.nhl_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity implements  GetResponse{

    AsyncReuseJson requestServer;
    private ListView listView;
    public String data;
    private JSONObject job;
    private ArrayList<String>ids = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        executeServerReq();
        requestServer.execute();

        //Create an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> listView, View v, int position, long id){

                Toast.makeText(getApplicationContext(), "Clicked item in row"+ids.get(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ScoreActivity.this, PlayerActivity.class);
                intent.putExtra("id", ids.get(position));
                startActivity(intent);
            }
        };


        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(itemClickListener);
    }
    public void executeServerReq() {
        requestServer = new AsyncReuseJson("http://51.255.194.175/api.php?call=scores",true, ScoreActivity.this);
        requestServer.getResponse = this;
    }
    @Override
    public String getData(String objects) {
        Log.e("Response", "----------" + objects);
        data = objects;

        if(data != null){
            try{

                ArrayList<String>names = new ArrayList<String>();
                ArrayList<String>ranks = new ArrayList<String>();
                ArrayList<String>teams = new ArrayList<String>();
                ArrayList<String>goals = new ArrayList<String>();
                ArrayList<String>assists = new ArrayList<String>();
                ArrayList<String>points = new ArrayList<String>();

                JSONArray arr = new JSONArray(data);

                for(int i = 0; i < arr.length(); i++){

                    JSONObject js = arr.getJSONObject(i);
                    String id = js.getString("id");
                    String rank = js.getString("rank");
                    String team = js.getString("team");
                    String name = js.getString("name");
                    String game = js.getString("gamesplayed");
                    String goal = js.getString("goals");
                    String assist = js.getString("assists");
                    String point = js.getString("points");

                    ids.add(id);
                    names.add(name);
                    ranks.add(rank);
                    teams.add(team);
                    goals.add(goal);
                    assists.add(assist);
                    points.add(point);
                }

                CustomList cl = new CustomList(this, ranks, names, teams, goals, assists, points );
                listView.setAdapter(cl);


            }catch (JSONException e){

                Log.e("ERROR", "----------" + e);
            }
           // textView.setText(data);
        }
        return objects;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
