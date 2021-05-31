package com.example.glidedownload;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView mCardView;
    private Context mContext;
    private Button download;
    private ImageView img;
    private File target = null;

    public static String PICTURE_01 =   "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=304881514,881600214&fm=26&gp=0.jpg";
    public static String PICsTURE_01 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=339074543,1379749639&fm=26&gp=0.jpg";
    //public static String PICTURE_01 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1774420118,989846740&fm=26&gp=0.jpg";
    public static String PICTUREs_01 = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1652123795,1945063222&fm=26&gp=0.jpg";
    private  String path;
    /*    private static final String URL =
            "https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture";*/
    public static String FILENAME =Environment.getExternalStorageDirectory()+ "/Download/picture.jpg";
    private static final String TAG = "MainActivity";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        startService(new Intent(mContext, MyService.class));
        download = findViewById(R.id.download);
        img = findViewById(R.id.img);






        target = new File(FILENAME);
        download.setOnClickListener(this);
        verifyStoragePermissions(this);
        if (target.exists()) {
            Glide.with(mContext).load(target).into(img);
        }





    }


    /**
     * 在对sd卡进行读写操作之前调用这个方法
     * Checks if the app has permission to write to device storage
     * If the app does not has permission then the user will be prompted to grant permissions
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.download) {
           new Thread(new MyRunnable()).start();

        }
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            downloadImage();
            getImageView();
        }
    }


    private void getImageView() {
        Uri  contentUri = Uri.fromFile(new File(path));
        Log.e(TAG, "onGetMessage_like: "+path);
        Intent  mediaScanIntent =new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,contentUri);
        sendBroadcast(mediaScanIntent);
    }

    private String downloadImage() {
        Glide.get(getApplicationContext()).clearDiskCache();
        File file = null;
        try {
            file = Glide.with(mContext)
                    .load(PICTURE_01)
                    .downloadOnly(96, 96)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
          path = FileImgUtil.copy(file, target);
            //    MediaStore.Images.Media.insertImage(getContentResolver(),.getPath(),"cameraPhoto", "Camera Pic Sample App Took");
    /*    runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(mContext)
                        .load(target)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(img);
            }
        });*/
        return path;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
