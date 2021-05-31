package com.example.mepositry;


        import android.app.DialogFragment;
        import android.app.FragmentManager;
        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;
        import com.example.mepositry.R;


public class AboutDialogFragment extends DialogFragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private TextView TV_MCU_Version_Menu, TV_SoftVersion_Menu, TV_CopyRight_Menu, TV_DeviceMac;//TV_DeviceVersion
    private Button AboutSure;

    private SetOnClickDialogListener mSetOnClickListener;

    public void onSetClickDialogListener(SetOnClickDialogListener listener) {
        this.mSetOnClickListener = listener;
    }

    //这个接口 如果是在项目中，请新建文件 统一管理
    public interface SetOnClickDialogListener {
        void onClickDoalogListener(int type, boolean boolClick);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chs_about_dialog, container, false);
        initView(view);
        return view;
    }

    private void initView(View V_AboutDialog) {
        AboutSure = (Button) V_AboutDialog.findViewById(R.id.id_b_about_ok);
        TV_MCU_Version_Menu = (TextView) V_AboutDialog.findViewById(R.id.id_tv_device_version);
        TV_SoftVersion_Menu = (TextView) V_AboutDialog.findViewById(R.id.id_tv_soft_version);
        TV_CopyRight_Menu = (TextView) V_AboutDialog.findViewById(R.id.id_tv_copyright);
        TV_DeviceMac = (TextView) V_AboutDialog.findViewById(R.id.id_tv_device_mac);
        TV_MCU_Version_Menu.setText(getResources().getString(R.string.app_name) + "MacCfg.DeviceVerString");
        TV_SoftVersion_Menu.setText(getResources().getString(R.string.app_name) + "MacCfg.App_versions");
        TV_CopyRight_Menu.setText("MacCfg.Copyright");
        TV_DeviceMac.setText(getResources().getString(R.string.app_name) + "MacCfg.Mac");

        AboutSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
                Log.e("TAG", "onCance_AboutSure:");
                if (mSetOnClickListener != null) {
                    mSetOnClickListener.onClickDoalogListener(0, true);
                }
            }
        });

    }

    //    This method will be invoked when the dialog is canceled. 取消对话框时将调用此方法。
    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.e("TAG", "onCancel:");
    }

    //This method will be invoked when the dialog is dismissed.
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.e("TAG", "onDismiss:");
    }


}