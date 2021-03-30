package com.hualong.mylibrary.helper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.PermissionUtils;
import com.hualong.mylibrary.constant.PermissionsConstant;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class PermissionHelper {
    private static PermissionHelper instance = new PermissionHelper();
    private PermissionHelper(){}
    public static PermissionHelper getInstance(){
        return instance;
    }

    public boolean checkWriteStoragePermission(Activity activity) {
        int writeStoragePermissionState =
                ContextCompat.checkSelfPermission(activity, WRITE_EXTERNAL_STORAGE);

        boolean writeStoragePermissionGranted = writeStoragePermissionState == PackageManager.PERMISSION_GRANTED;

        if (!writeStoragePermissionGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(PermissionsConstant.PERMISSIONS_EXTERNAL_WRITE,
                        PermissionsConstant.REQUEST_EXTERNAL_WRITE);
            }
        }

        // activity.onRequestPermissionsResult(); 请求权限返回
        // Console.loge("writeStoragePermissionGranted",writeStoragePermissionGranted?"true":"false");
        return writeStoragePermissionGranted;
    }

    public static boolean validWriteStoragePermission(){
        if(PermissionUtils.isGranted( Manifest.permission.WRITE_EXTERNAL_STORAGE))
            return true;
        return false;
    }

    public void validePermission(){
        if(!PermissionUtils.isGranted("android.permission.WRITE_EXTERNAL_STORAGE")){
            PermissionUtils.launchAppDetailsSettings();
            // PermissionUtils.
        }
        if(!PermissionUtils.isGrantedWriteSettings())
            PermissionUtils.requestWriteSettings(new PermissionUtils.SimpleCallback() {
                @Override
                public void onGranted() {

                }

                @Override
                public void onDenied() {

                }
            });
    }
}
