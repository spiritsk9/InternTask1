package com.nooralhealth.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import com.nooralhealth.R;
import com.nooralhealth.activity.libs.BitmapManager;
import com.nooralhealth.model.room.AppConfig;
import com.nooralhealth.model.room.AppDatabase;
import com.nooralhealth.model.room.model.MyImage;

public class ImgToRoom extends AppCompatActivity implements View.OnClickListener {

    public static final int CAMERA_REQUEST_CODE=101;

    Button btnCapture, btnAddPhoto;

    ImageView ivPhoto;
    RecyclerView rvItems;
    AppDatabase db;
    Bitmap bitmap;
    private String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCapture=findViewById(R.id.btnCapture);
        btnAddPhoto=findViewById(R.id.btnAddPhoto);

        ivPhoto=findViewById(R.id.ivPhoto);
        db= Room.databaseBuilder(getApplicationContext(), AppDatabase.class, AppConfig.DB_NAME)
                .allowMainThreadQueries()
                .build();
        ivPhoto.setOnClickListener(this);
        btnCapture.setOnClickListener(this);
        btnAddPhoto.setOnClickListener(this);
   //     btnAddPhoto.setEnabled(false);
        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivPhoto:
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),CAMERA_REQUEST_CODE);
                break;

            case R.id.btnCapture:
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),CAMERA_REQUEST_CODE);
                break;

            case R.id.btnAddPhoto:
                insertImages();
                break;
        }
    }

    private void insertImages(){
        String photo= BitmapManager.bitmapToBase64(bitmap);
        byte[] image=BitmapManager.bitmapToByte(bitmap);
        db.myImageDao().insertAll(new MyImage(photo,image));
        Toast.makeText(getApplicationContext(),"Image Saved to Room", Toast.LENGTH_SHORT).show();
        ivPhoto.setImageResource(R.drawable.camerayello);
       // btnAddPhoto.setEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                bitmap = (Bitmap) data.getExtras().get("data");
//        bitmap=BitmapManager.drawString(bitmap,"Hi,\nI am Asif Mohammad Mollah,\nHow are you ?",0,0,10);
                ivPhoto.setImageBitmap(bitmap);
            //    int lastUID = 0;
                try {
            //        lastUID = db.myImageDao().last().getUid();
                } catch (Exception e) {
                }
             //   String title = "Image " + String.valueOf(lastUID + 1);

            //    btnAddPhoto.setEnabled(true);
            }
        }
    }
}
