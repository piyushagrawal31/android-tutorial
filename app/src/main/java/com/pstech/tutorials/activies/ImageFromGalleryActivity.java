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
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pstech.tutorials.R;
import com.pstech.tutorials.utils.AppConstants;

/**
 * Created by pagrawal on 28-02-2018.
 */

public class ImageFromGalleryActivity extends AppCompatActivity {

    ImageView imageView;
    Button loadImageBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_thread);
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

//                String selectedImagePath = getPath(selectedImageUri);
//                System.out.println("Image Path : " + selectedImagePath);
//
//                ImageLoaderTask imageLoaderTask = new ImageLoaderTask(getApplicationContext());
//                imageLoaderTask.execute(selectedImagePath);
                imageView.setImageURI(selectedImageUri);

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // toast cancel
                Toast.makeText(getApplicationContext(), AppConstants.SEARCH_CANCELLED, Toast.LENGTH_SHORT).show();
            }

        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private class ImageLoaderTask extends AsyncTask<String, String, Bitmap> {

        private Context context;
        ProgressDialog mDialog;
        ImageLoaderTask(Context context) {
            this.context = context;
            mDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog.setMessage("Image Loading ...");
            mDialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (mDialog.isShowing()) {
                mDialog.cancel();
                imageView.setImageBitmap(bitmap);
            }
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String filePath = strings[0];
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
//            Bitmap tmp = BitmapFactory.decodeResource(getResources(), filePath);
            return bitmap;
        }
    }
}
