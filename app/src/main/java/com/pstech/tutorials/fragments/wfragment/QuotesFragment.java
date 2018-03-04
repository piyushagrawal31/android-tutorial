package com.pstech.tutorials.fragments.wfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pstech.tutorials.R;

/**
 * Created by pagrawal on 04-03-2018.
 */

public class QuotesFragment extends Fragment {

    private TextView mQuoteView = null;
    private int mCurrIdx = -1;
    private int mQuoteArrayLen;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quote_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mQuoteView = getActivity().findViewById(R.id.quoteView);
        mQuoteArrayLen = QuoteViewerActivity.mQuoteArray.length;
    }

    public int getShownIndex() {
        return mCurrIdx;
    }

    public void showQuoteAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mQuoteArrayLen)
            return;
        mCurrIdx = newIndex;
        mQuoteView.setText(QuoteViewerActivity.mQuoteArray[mCurrIdx]);
    }

}
