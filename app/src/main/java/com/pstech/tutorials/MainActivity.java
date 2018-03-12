package com.pstech.tutorials;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pstech.tutorials.activies.AsyncTaskActivity;
import com.pstech.tutorials.activies.ImageFromGalleryActivity;
import com.pstech.tutorials.broadcast.SimpleBroadcast;
import com.pstech.tutorials.listview.ListViewDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button basicIntentBtn;
    private Button intentWithDataBtn;
    private Button loadImageGalleryBtn;
    private Button showListViewExampleBtn;
    private Button sendBcastBtn;

    private Button shareBtn;
    private TextView dataTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataTxt = findViewById(R.id.typed_txt);

        basicIntentBtn = findViewById(R.id.basic_intent);
        intentWithDataBtn = findViewById(R.id.intent_with_data);
        loadImageGalleryBtn = findViewById(R.id.image_load_gallery);
        showListViewExampleBtn = findViewById(R.id.list_view_example);
        sendBcastBtn = findViewById(R.id.send_broadcast);

        basicIntentBtn.setOnClickListener(this);
        intentWithDataBtn.setOnClickListener(this);
        loadImageGalleryBtn.setOnClickListener(this);
        showListViewExampleBtn.setOnClickListener(this);
        sendBcastBtn.setOnClickListener(this);
    }

    private void playRingtone() {

        SharedPreferences pref = getPreferences(MODE_PRIVATE);

        pref.edit().putBoolean("showRingToneOnNewMessage", false);

        boolean showRingTone = pref.getBoolean("showRingToneOnNewMessage", false);
        if (showRingTone) {
//            playRingtone();
        } else {

        }
    }

    @Override
    public void onClick(View view) {

        if (view == basicIntentBtn) {

            Intent basicIntent = new Intent(MainActivity.this, BasicIntentActivity.class);
            startActivity(basicIntent);

        } else if (view == intentWithDataBtn) {

            String data = dataTxt.getText().toString();

            Intent dataIntent = new Intent(MainActivity.this, IntentWithData.class);

            dataIntent.putExtra(IntentWithData.NAME, data);
            dataIntent.putExtra(IntentWithData.CITY, "Pune");
            dataIntent.putExtra(IntentWithData.COUNTRY, "India");
            dataIntent.putExtra("isTrue", false);

            startActivity(dataIntent);

        } else if (view == shareBtn) {
            String data = dataTxt.getText().toString();
            shareApp();
        } else if (view == loadImageGalleryBtn) {
            Intent showImageIntent = new Intent(MainActivity.this, ImageFromGalleryActivity.class);
//            Intent showImageIntent = new Intent(MainActivity.this, AsyncTaskActivity.class);
            startActivity(showImageIntent);
        } else if (view == showListViewExampleBtn) {
            Intent showListViewDemoIntent = new Intent(MainActivity.this, ListViewDemoActivity.class);
            startActivity(showListViewDemoIntent);
        } else if (view == sendBcastBtn) {
            Intent sendBcastIntent = new Intent(MainActivity.this, SimpleBroadcast.class);
            startActivity(sendBcastIntent);
        }
    }

    public void sendFeedback() {
        String emailId = "emailId@gmail.com";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", emailId, null));
        String[] addresses = {emailId};
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject: " + getPackageName());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body:");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses); // String[] addresses
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }

    public void rateApp() {

        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }

    }

    public void shareApp() {
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Text To be shared");
        sendIntent.setType("text/html");
        startActivity(Intent.createChooser(sendIntent, "Send To..."));
    }
}
