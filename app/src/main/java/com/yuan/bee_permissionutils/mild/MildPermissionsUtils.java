package com.yuan.bee_permissionutils.mild;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.yuan.bee_permissionutils.R;

/**
 * Created by yuan on 2017/5/16.
 * 待更新
 */

public class MildPermissionsUtils {

    public static void requestPermissions(Activity context, String permission,int requestCode){
        if(Build.VERSION.SDK_INT >= 23){
            if(!context.shouldShowRequestPermissionRationale(permission)){
                showMissPermissionsDialog(context);
            }
            context.requestPermissions(new String[]{permission},requestCode);
        }else {
            showMissPermissionsDialog(context);
        }
    }

    private static AlertDialog dialog;
    private static void showMissPermissionsDialog(final Activity activity){
        if(dialog == null){
            dialog = new AlertDialog.Builder(activity)
                    .setTitle(activity.getString(R.string.notify))
                    .setMessage(activity.getString(R.string.to_settings_set))
                    .setPositiveButton(activity.getString(R.string.go_to_set), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startAppSettings(activity);
                        }
                    })
                    .setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create();
            dialog.show();
        }else {
            if(!dialog.isShowing()){
                dialog.show();
            }
        }
    }

    // 启动应用的设置
    private static void startAppSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }


}
