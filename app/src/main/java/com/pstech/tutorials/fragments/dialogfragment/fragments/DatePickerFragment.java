package com.pstech.tutorials.fragments.dialogfragment.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by pagrawal on 05-03-2018.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private OnDatePicked mHandler;

    public interface OnDatePicked {
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHandler = (OnDatePicked) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        mHandler.onDateSet(datePicker, i, i1, i2);
    }
}
