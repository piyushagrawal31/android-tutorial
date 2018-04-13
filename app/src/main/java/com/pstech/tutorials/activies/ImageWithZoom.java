package com.pstech.tutorials.activies;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pstech.tutorials.R;
import com.pstech.tutorials.utils.AppConstants;

/**
 * Created by pagrawal on 13-04-2018.
 */

public class ImageWithZoom extends AppCompatActivity {
    ImageView imageView;
    Button loadImageBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_with_zoom);
        imageView = findViewById(R.id.imageView);
        loadImageBtn = findViewById(R.id.loadButton);

        loadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//

                startActivityForResult(Intent.createChooser(intent, "Select Picture"), AppConstants.REQ_CODE_SELECT_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImageUri = data.getData();
                imageView.setImageURI(selectedImageUri);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), AppConstants.SEARCH_CANCELLED, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
