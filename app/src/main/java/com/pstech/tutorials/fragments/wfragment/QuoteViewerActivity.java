package com.pstech.tutorials.fragments.wfragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pstech.tutorials.R;

/**
 * Created by pagrawal on 04-03-2018.
 */

public class QuoteViewerActivity extends AppCompatActivity implements TitlesFragment.ListSelectionListener {
    public static String[] mTitleArray;
    public static String[] mQuoteArray;
    private QuotesFragment mDetailsFragment;
    private static final String TAG = "QuoteViewerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitleArray = getResources().getStringArray(R.array.Titles);
        mQuoteArray = getResources().getStringArray(R.array.Quotes);

        setContentView(R.layout.activity_main_fragment);

        mDetailsFragment = (QuotesFragment) getSupportFragmentManager()
                .findFragmentById(R.id.details);
    }

    @Override
    public void onListSelection(int index) {
        if (mDetailsFragment.getShownIndex() != index) {
            mDetailsFragment.showQuoteAtIndex(index);
        }
    }
}
