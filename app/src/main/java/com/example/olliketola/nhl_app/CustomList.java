package com.example.olliketola.nhl_app;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by olliketola on 23.3.2016.
 */
public class CustomList extends ArrayAdapter<String> {

    private ArrayList<String> rank;
    private ArrayList<String>  team;
    private ArrayList<String> name;
    private ArrayList<String> goals;
    private ArrayList<String> assists;
    private ArrayList<String> points;
    private Activity context;

    public CustomList(Activity context, ArrayList<String> rank, ArrayList<String>  team, ArrayList<String>  name, ArrayList<String>  goals, ArrayList<String>  assists, ArrayList<String>  points){
        super(context, R.layout.custom_listview, name);
        this.context = context;
        this.rank = rank;
        this.team = team;
        this.name = name;
        this.goals = goals;
        this.assists = assists;
        this.points = points;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.custom_listview, null, true);
        TextView textViewRank = (TextView) listViewItem.findViewById(R.id.textViewRank);
        TextView textViewTeam = (TextView) listViewItem.findViewById(R.id.textViewTeam);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGoals = (TextView) listViewItem.findViewById(R.id.textViewGoals);
        TextView textViewAssists = (TextView) listViewItem.findViewById(R.id.textViewAssists);
        TextView textViewPoints = (TextView) listViewItem.findViewById(R.id.textViewPoints);

        textViewRank.setText(rank.get(position));
        textViewTeam.setText(team.get(position));
        textViewName.setText(name.get(position));
        textViewGoals.setText(goals.get(position));
        textViewAssists.setText(assists.get(position));
        textViewPoints.setText(points.get(position));


        return  listViewItem;
    }
}
