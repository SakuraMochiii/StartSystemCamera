package com.smartpos.camerashortcut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCameraNoVideo1, btnVideoNoCamera2, btnCamera3, btnVideo4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCameraNoVideo1 = findViewById(R.id.cameraNoVideo);
        btnVideoNoCamera2 = findViewById(R.id.videoNoCamera);
        btnCamera3 = findViewById(R.id.camera);
        btnVideo4 = findViewById(R.id.video);
        btnCameraNoVideo1.setOnClickListener(this);
        btnVideoNoCamera2.setOnClickListener(this);
        btnCamera3.setOnClickListener(this);
        btnVideo4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cameraNoVideo:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri outputFileUri = FileProvider.getUriForFile(
                        this, getPackageName() + ".fileprovider",
                        new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera/test.jpg"));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(intent, 0);
                break;
            case R.id.videoNoCamera:
                startActivity(new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE));
                break;
            case R.id.camera:
                startActivity(new Intent(android.provider.MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA));
                break;
            case R.id.video:
                startActivity(new Intent(android.provider.MediaStore.INTENT_ACTION_VIDEO_CAMERA));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri outputFileUri = FileProvider.getUriForFile(
                this, getPackageName() + ".fileprovider",
                new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera/test.jpg"));
        mediaScanIntent.setData(outputFileUri);
        this.sendBroadcast(mediaScanIntent);
        super.onActivityResult(requestCode, resultCode, data);
    }

}