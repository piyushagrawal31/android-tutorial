package com.pstech.tutorials.services.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pstech.tutorials.R;
import com.pstech.tutorials.services.LoggingService;

/**
 * Created by pagrawal on 08-03-2018.
 */

public class LoggingServiceClientActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.logging_client_activity);

        final Button messageButton = (Button) findViewById(R.id.message_button);
        messageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Create an Intent for starting the LoggingService
                Intent startServiceIntent = new Intent(getApplicationContext(),
                        LoggingService.class);

                // Put Logging message in intent
                startServiceIntent.putExtra(LoggingService.EXTRA_LOG,
                        "Log this message");

                // Start the Service
                startService(startServiceIntent);



            }
        });
    }
}
