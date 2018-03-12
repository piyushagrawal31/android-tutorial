package com.pstech.tutorials.broadcast.receiver;

import android.content.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by pagrawal on 11-03-2018.
 */

public class BCastReceiver extends BroadcastReceiver {
    private final String TAG = "Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if (info != null && info.isConnected()) {
            // Do your work.

            // e.g. To check the Network Name or other info:
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID();

            Toast.makeText(context, "Wifi RECEIVED by Receiver", Toast.LENGTH_SHORT).show();
        } else {
            Log.i(TAG, "INTENT RECEIVED");

            Vibrator v = (Vibrator) context
                    .getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);

            Toast.makeText(context, "INTENT RECEIVED by Receiver", Toast.LENGTH_SHORT).show();
        }
    }
}
