package com.example.users.tokentest;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    Button test;
    ImageView cam;

    String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test=(Button)findViewById(R.id.test);
        cam=(ImageView)findViewById(R.id.cam);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFilePath= Environment.getExternalStorageDirectory().getPath();

                mFilePath=mFilePath+"/"+ String.valueOf(System.currentTimeMillis()) +".jpg";

                //指定拍照

                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //加载路径

                Uri uri = Uri.fromFile(new File(mFilePath));

                //指定存储路径，这样就可以保存原图了

                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);

                //拍照返回图片

                startActivityForResult(intent,2);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==2) {

            String filePath=mFilePath;

            Bitmap bitmap= BitmapFactory.decodeFile(filePath,getBitmapOption(2));//将图片的长和宽缩小味原来的1/2

            cam.setImageBitmap(bitmap);

        }

    }
    private BitmapFactory.Options getBitmapOption(int inSampleSize){

        System.gc();

        BitmapFactory.Options options =new BitmapFactory.Options();

        options.inPurgeable=true;

        options.inSampleSize= inSampleSize;

        return options;

    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

}
