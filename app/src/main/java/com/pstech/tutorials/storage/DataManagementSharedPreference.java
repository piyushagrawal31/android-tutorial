package com.pstech.tutorials.storage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pstech.tutorials.R;

import java.util.Random;

/**
 * Created by pagrawal on 26-02-2018.
 */

public class DataManagementSharedPreference extends AppCompatActivity implements View.OnClickListener{
    private TextView highScoreTxt;
    private TextView currScoreTxt;
    private Button playBtn;
    private Button resetBtn;
    SharedPreferences pref;
    private static String HIGH_SCORE = "high_score";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.shared_pref_main);
        setTitle(R.string.title_shared_pref_activity);

        pref = getPreferences(MODE_PRIVATE);

        highScoreTxt = findViewById(R.id.high_score_text);
        currScoreTxt = findViewById(R.id.game_score_text);
        playBtn   = findViewById(R.id.play_button);
        resetBtn  = findViewById(R.id.reset_button);

        highScoreTxt.setText(pref.getInt(HIGH_SCORE, 0));

    }

    @Override
    public void onClick(View view) {
        if (view == playBtn) {
            Random r = new Random();
            int val = r.nextInt(10000);

            // do not directly set integer to setText as integer refers resource id
            // always use String.valueOf
            currScoreTxt.setText(String.valueOf(val));
            if (val > (pref.getInt(HIGH_SCORE, 0))) {
                pref.edit().putInt(HIGH_SCORE, val).commit();
            }
        } else if (view == resetBtn) {
            pref.edit().putInt(HIGH_SCORE, 0).commit();
            highScoreTxt.setText("0");
            currScoreTxt.setText("0");

        }
    }
}
