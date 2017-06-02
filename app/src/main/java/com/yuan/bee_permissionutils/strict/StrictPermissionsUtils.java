package com.yuan.bee_permissionutils.strict;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import com.yuan.bee_permissionutils.R;

/**
 * Created by yuan on 2017/5/15.
 */

public class StrictPermissionsUtils extends Activity{

    private static final String EXTRA_PERMISSIONS =
            "me.chunyu.clwang.permission.extra_permission"; // 权限参数

    private boolean isRequireCheck; // 是否需要系统权限检测, 防止和系统提示框重叠
    private static final int PERMISSION_REQUEST_CODE = 0; // 系统权限管理页面的参数
    public static final int PERMISSIONS_GRANTED = 0; // 权限授权
    public static final int PERMISSIONS_DENIED = 1; // 权限拒绝


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utils_strict);

        isRequireCheck = true;
    }

    public static void startActivityForResult(Activity activity,int requestCode , String... permissions){

        Intent intent = new Intent(activity,StrictPermissionsUtils.class);
        intent.putExtra(EXTRA_PERMISSIONS,permissions);
        ActivityCompat.startActivityForResult(activity,intent,requestCode,null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isRequireCheck){
            ActivityCompat.requestPermissions(this,getPermissions(),PERMISSION_REQUEST_CODE);
        }else {
            isRequireCheck = true;
        }
    }

    private String[] getPermissions(){
        return getIntent().getStringArrayExtra(EXTRA_PERMISSIONS);
    }

    private void allPermissionsGranted(){
        setResult(PERMISSIONS_GRANTED);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionsGranted(grantResults)){
            allPermissionsGranted();
            isRequireCheck = true;
        }else {
            isRequireCheck = false;
            showMissPermissionsDialog();
        }
    }

    private boolean hasAllPermissionsGranted(int... grantResults){
        for(int grantResult : grantResults){
            if(grantResult == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    private AlertDialog dialog;
    private void showMissPermissionsDialog(){
        if(dialog == null){
            dialog = new AlertDialog.Builder(StrictPermissionsUtils.this)
                    .setTitle(getString(R.string.notify))
                    .setMessage(getString(R.string.to_settings_set))
                    .setPositiveButton(getString(R.string.go_to_set), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startAppSettings();
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setResult(PERMISSIONS_DENIED);
                            finish();                        }
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
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

}
