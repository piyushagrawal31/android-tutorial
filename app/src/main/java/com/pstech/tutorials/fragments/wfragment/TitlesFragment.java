package com.pstech.tutorials.fragments.wfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pstech.tutorials.R;

/**
 * Created by pagrawal on 04-03-2018.
 */

public class TitlesFragment extends ListFragment {

    private ListSelectionListener mListener;
    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ListSelectionListener) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.title_item, QuoteViewerActivity.mTitleArray));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        getListView().setItemChecked(position, true);
        mListener.onListSelection(position);
    }
}
