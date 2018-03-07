package com.pstech.tutorials.fragments.dialogfragment.activies;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pstech.tutorials.R;
import com.pstech.tutorials.fragments.dialogfragment.fragments.DatePickerFragment;
import com.pstech.tutorials.fragments.dialogfragment.fragments.TimePickerFragment;

public class DateTimeDisplayActivity extends AppCompatActivity implements
        DatePickerFragment.OnDatePicked, TimePickerFragment.OnTimePicked, View.OnClickListener {

    private TextView currTimeText;
    private TextView currDateText;
    private Button btnTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currTimeText = findViewById(R.id.currTimeText);
        currDateText = findViewById(R.id.currDateText);

        btnTimePicker = findViewById(R.id.btn_time);
        btnTimePicker.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        // set result appropriately
        currDateText.setText(String.valueOf(datePicker.getMonth()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        // set result appropriately
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            currTimeText.setText(String.valueOf(timePicker.getHour()));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnTimePicker) {

            TimePickerFragment fragment = TimePickerFragment.newInstance();
            fragment.show(getSupportFragmentManager(), "Choose Time");

        } else {

        }
    }
}
