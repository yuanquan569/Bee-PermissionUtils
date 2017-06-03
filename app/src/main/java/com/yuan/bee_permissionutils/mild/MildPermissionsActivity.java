package com.yuan.bee_permissionutils.mild;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;

import com.yuan.bee_permissionutils.R;
import com.yuan.bee_permissionutils.common.PermissionsChecker;

/**
 * Created by yuan on 2017/5/16.
 * 近期更新
 */

public class MildPermissionsActivity extends Activity{

    private PermissionsChecker permissionsChecker;

    private static final int PERMISSION_REQUEST_CODE = 0; // 系统权限管理页面的参数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mild);

        permissionsChecker = new PermissionsChecker(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(permissionsChecker.lacksPermissions(Manifest.permission.CAMERA)){
            MildPermissionsUtils.requestPermissions(this,Manifest.permission.CAMERA,PERMISSION_REQUEST_CODE);
        }
    }
}
