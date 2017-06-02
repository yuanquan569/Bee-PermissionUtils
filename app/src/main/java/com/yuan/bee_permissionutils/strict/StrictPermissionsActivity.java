package com.yuan.bee_permissionutils.strict;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.yuan.bee_permissionutils.R;
import com.yuan.bee_permissionutils.common.PermissionsChecker;

/**
 * Created by yuan on 2017/5/15.
 */

public class StrictPermissionsActivity extends Activity{

    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
    };
    private PermissionsChecker permissionsChecker;
    private static final int REQUEST_CODE = 0; // 请求码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strict);
        permissionsChecker = new PermissionsChecker(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(permissionsChecker.lacksPermissions(PERMISSIONS)){
            StrictPermissionsUtils.startActivityForResult(this,REQUEST_CODE,PERMISSIONS);        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == StrictPermissionsUtils.PERMISSIONS_DENIED){
            finish();
        }
    }
}
