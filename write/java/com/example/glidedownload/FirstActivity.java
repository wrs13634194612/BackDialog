package com.example.glidedownload;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {
    private String phone = "1\uD83D\uDC48 hi:/\uD83D\uDDDDupvbXU2HWxZ\uD83D\uDDDD  Apple/苹果 iPhone 11 移动联通电信4G全网通手机 2020新版";
    private String book = "4\uD83D\uDC48 hi:/\uD83D\uDCB2AINOXUdS5o3₴  国富论（上下卷）(权威译本)";
    private String table = "8\uD83D\uDC48 ha:/✔PEcrXUdiNYn《  SUNSHINE BABY/阳光芭比诺檀丝木小书桌实木桌子客厅家具";
    private TextView tv_content;
    private Button btn_open;
    private String content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_open = findViewById(R.id.btn_open);
        tv_content = findViewById(R.id.tv_content);
        tv_content.setText(table);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = tv_content.getText().toString();
                GangUpInvite(getApplicationContext(), content);
            }
        });
    }

    public void GangUpInvite(final Context context, String content) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", content);
        clipboard.setPrimaryClip(mClipData);


        Log.e("TAG", "tv_text:" + mClipData + content);
        //无数据时直接返回
        if (!clipboard.hasPrimaryClip()) {
            return;
        }
        //如果是文本信息
        if (clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
            ClipData cdText = clipboard.getPrimaryClip();
            ClipData.Item item = cdText.getItemAt(0);
            //此处是TEXT文本信息
            if (item.getText() != null) {
                //启动测试程序
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.mepositry");
                if (intent != null) {
                    startActivity(intent);
                }
            }
        }
    }
}
