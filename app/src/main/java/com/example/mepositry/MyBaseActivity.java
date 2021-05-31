package com.example.mepositry;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MyBaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

        CourserApplication.getContext().setOnVisibilityChangeListener(new CourserApplication.ValueChangeListener() {
            @Override
            public void onChanged(Boolean isAppInBackground) {
                Log.e("TAG", "YoungerHu:\t" + isAppInBackground);
                if (isAppInBackground) {
                    //显示view
                    AboutDialogFragment aboutDialog = new AboutDialogFragment();
                    aboutDialog.show(getFragmentManager(), "AboutDialogFragment");
                }
            }
        });
    }
}
