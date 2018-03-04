package com.pstech.tutorials.listview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pstech.tutorials.R;
import com.pstech.tutorials.models.Station;
import com.pstech.tutorials.utils.AppUtils;

import java.util.List;

/**
 * Created by pagrawal on 01-03-2018.
 */

class ShowStationItemAdapter extends ArrayAdapter {

    private final Context context;
    private List<Station> mStations;

    public ShowStationItemAdapter(Context context, List<Station> stations) {
        super(context, -1, stations);
        this.context = context;
        this.mStations = stations;
    }

    @Override
    public int getCount() {
        return mStations.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.station_list_item, parent, false);
//        } else {
//        }

        View rootView = inflater.inflate(R.layout.station_list_item, parent, false);

        Station station = mStations.get(position);
        ImageView lineColor = rootView.findViewById(R.id.line_color);
        TextView stationInfo = rootView.findViewById(R.id.station_info);
        TextView lineInfo = rootView.findViewById(R.id.line_info);
        TextView stationId = rootView.findViewById(R.id.stationId);

//        lineColor.setBackgroundColor(AppUtils.getColor(station.getLineType()));

//        lineColor.setColorFilter(Color.GREEN);

        lineColor.setColorFilter(AppUtils.getColor(station.getLineType()));
        stationInfo.setText(station.getStation());
        stationId.setText(String.valueOf(station.getId()));
        return rootView;

    }
}
