package com.pstech.tutorials.listview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pstech.tutorials.R;
import com.pstech.tutorials.models.LineType;
import com.pstech.tutorials.models.Station;

import java.util.ArrayList;
import java.util.List;

public class ListViewDemoActivity extends AppCompatActivity {

    ListView mStationsView;
    List<Station> stations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        stations = getStations();
        mStationsView = findViewById(R.id.item_list);
        ArrayAdapter adapter =
                new ShowStationItemAdapter(getApplicationContext(), stations);
        mStationsView.setAdapter(adapter);

    }

    public List<Station> getStations() {
        List<Station> stations = new ArrayList<>();
        Station.Builder builder = new Station.Builder();
        builder.setId(1).setLineType(LineType.GREEN.toString());
        stations.add(builder.build("Delhi"));

        builder.setId(2).setLineType(LineType.RED.toString());
        stations.add(builder.build("Mumbai"));
        return stations;
    }
}
