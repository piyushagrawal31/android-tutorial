package com.pstech.tutorials.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by pagrawal on 08-03-2018.
 */

public class LoggingService extends IntentService {

    public static String EXTRA_LOG = "tutorial.Services.Logging.MESSAGE";
    private static final String TAG = "LoggingService";

    public LoggingService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.i(TAG, intent.getStringExtra(EXTRA_LOG));

    }

}
