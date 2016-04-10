package com.example.olliketola.nhl_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button score;
    private Button today;
    private  Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        score = (Button)findViewById(R.id.scoreleaderboard);
        today = (Button)findViewById(R.id.today);
        search = (Button)findViewById(R.id.search);

        score.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent act1 = new Intent(v.getContext(), ScoreActivity.class);
                startActivity(act1);
            }
        });
        today.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent act1 = new Intent(v.getContext(), GamesActivity.class);
                startActivity(act1);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent act1 = new Intent(v.getContext(), SearchPlayersActivity.class);
                startActivity(act1);
            }
        });
    }


}
