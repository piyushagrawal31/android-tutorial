package com.pstech.tutorials.fragments.wofragment;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pstech.tutorials.R;

/**
 * Created by pagrawal on 04-03-2018.
 */

public class TitlesListActivity extends ListActivity {
    public static String[] mTitleArray;
    public static String[] mQuoteArray;

    public static final String INDEX = "index";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitleArray = getResources().getStringArray(R.array.Titles);
        mQuoteArray = getResources().getStringArray(R.array.Quotes);

        ArrayAdapter adapter = new ArrayAdapter<String>(TitlesListActivity.this,
                R.layout.list_text_item_layout, TitlesListActivity.mTitleArray);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent showItemIntent = new Intent(TitlesListActivity.this,
                QuoteListActivity.class);
        showItemIntent.putExtra(INDEX, mQuoteArray[position]);
        startActivity(showItemIntent);
    }
}
