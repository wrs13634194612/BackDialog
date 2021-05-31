package com.example.mepositry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        textview = findViewById(R.id.textview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取剪切板内容
        getClipboardData();
    }

    private void getClipboardData() {
        this.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                //把获取到的内容打印出来
                Log.e("YoungerHu", ClipBoardUtil.paste());
                textview.setText(ClipBoardUtil.paste());
            }
        });
    }

}
