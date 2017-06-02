package com.yuan.bee_permissionutils.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by yuan on 2017/5/15.
 */

public class PermissionsChecker {

    private Context mContext;
    public PermissionsChecker(Context context){
        mContext = context.getApplicationContext();
    }

    public boolean lacksPermissions(String... permissions){

        for(String permission : permissions){
            if(lackPermission(permission)){
                return true;
            }
        }
        return false;
    }

    private boolean lackPermission(String permission){
        return ContextCompat.checkSelfPermission(mContext,permission) == PackageManager.PERMISSION_DENIED;
    }


}
