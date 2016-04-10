package com.example.olliketola.nhl_app;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ozog on 25.3.2016.
 */
public class GameList extends ArrayAdapter<String>{

    private ArrayList<String> home;
    private ArrayList<String>  visitor;
    private ArrayList<String> hs;
    private ArrayList<String> vs;
    private ArrayList<String> _final;
    private ArrayList<String> namehome;
    private ArrayList<String> namevisitor;
    private Activity context;

    public GameList(Activity context, ArrayList<String> home, ArrayList<String>  visitor, ArrayList<String>  hs, ArrayList<String>  vs, ArrayList<String> _final, ArrayList<String> namehome, ArrayList<String> namevisitor ){
        super(context, R.layout.gamelist, home);
        this.context = context;
        this.home = home;
        this.visitor = visitor;
        this.hs = hs;
        this.vs = vs;
        this._final = _final;
        this.namehome = namehome;
        this.namevisitor = namevisitor;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.gamelist, null, true);

        TextView textViewHome = (TextView) listViewItem.findViewById(R.id.home);
        TextView textViewVisitor = (TextView) listViewItem.findViewById(R.id.visitor);
        TextView textViewHs = (TextView) listViewItem.findViewById(R.id.hs);
        TextView textViewVs = (TextView) listViewItem.findViewById(R.id.vs);
        TextView textViewFinal = (TextView) listViewItem.findViewById(R.id._final);
        ImageView imageViewHome = (ImageView) listViewItem.findViewById(R.id.team1);
        ImageView imageViewVisitor = (ImageView) listViewItem.findViewById(R.id.team2);


       // int namehomeint = getContext().getResources().getIdentifier((String)namehome.get(position).toLowerCase().replaceAll("\\s+",""),null, null);
       // int namevisitorint = getContext().getResources().getIdentifier((String)namevisitor.get(position).toLowerCase().replaceAll("\\s+",""),null, null);

        int idhome = getContext().getResources().getIdentifier((String)namehome.get(position).toLowerCase().replaceAll("\\s+",""), "drawable", getContext().getPackageName());
        int idvisitor = getContext().getResources().getIdentifier((String)namevisitor.get(position).toLowerCase().replaceAll("\\s+",""), "drawable", getContext().getPackageName());
        textViewHome.setText(home.get(position));
        textViewVisitor.setText(visitor.get(position));
        textViewHs.setText(hs.get(position));
        textViewVs.setText(vs.get(position));
        textViewFinal.setText(_final.get(position));
        imageViewHome.setImageResource(idhome);
        imageViewVisitor.setImageResource(idvisitor);


        return  listViewItem;

}

}
