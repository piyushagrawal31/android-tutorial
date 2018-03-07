package com.pstech.tutorials.fragments.dialogfragment.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.pstech.tutorials.R;
import com.pstech.tutorials.fragments.dialogfragment.activies.MainActivityAddFilter;

/**
 * Created by pagrawal on 05-03-2018.
 */

public class AddFilterDialog extends DialogFragment {

    public interface AddFilterInterface {
        public void onDialogFilterPositiveClick(DialogFragment dialog);
    }

    private MainActivityAddFilter mHandler;

    public static AddFilterDialog newInstance() {
        return new AddFilterDialog();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHandler = (MainActivityAddFilter) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_add_filter, null))
                // Add action buttons
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mHandler.onDialogFilterPositiveClick(AddFilterDialog.this);
                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

}
