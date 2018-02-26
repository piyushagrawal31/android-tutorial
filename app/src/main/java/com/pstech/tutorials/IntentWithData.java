package com.pstech.tutorials;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class IntentWithData extends AppCompatActivity {

    public static final String CITY = "City";
    public static final String COUNTRY = "country";

    public static String NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_with_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        String data = i.getStringExtra(NAME);
        i.getBooleanExtra("hello", false);

        if (!data.isEmpty())
            Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "passed data is empty", Toast.LENGTH_SHORT).show();

    }
}
