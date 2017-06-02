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
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mild);

        permissionsChecker = new PermissionsChecker(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(permissionsChecker.lacksPermissions(PERMISSIONS)){

        }
    }
}
