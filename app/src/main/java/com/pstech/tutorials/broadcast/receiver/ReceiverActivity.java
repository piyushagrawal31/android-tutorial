package com.pstech.tutorials.broadcast.receiver;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.pstech.tutorials.R;


/**
 * Created by pagrawal on 12-03-2018.
 */

public class ReceiverActivity extends AppCompatActivity {

    // for dynamic registration. Starts

    private static final String CUSTOM_INTENT = "course.examples.BroadcastReceiver.show_toast";
    private final IntentFilter intentFilter = new IntentFilter(CUSTOM_INTENT);

    private final BCastReceiver receiver = new BCastReceiver();
    private LocalBroadcastManager mBroadcastMgr;
    // Dynamic registration Ends

    IntentFilter wifiIntentFilter = new IntentFilter(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);

    // wifiIntentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dynamic registration Starts
        mBroadcastMgr = LocalBroadcastManager
                .getInstance(getApplicationContext());

        mBroadcastMgr.registerReceiver(receiver, intentFilter);
//         Dynamic registration Ends
        registerReceiver(receiver, wifiIntentFilter);
    }
}
