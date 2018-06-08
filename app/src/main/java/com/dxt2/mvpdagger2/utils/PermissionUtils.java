package com.dxt2.mvpdagger2.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public class PermissionUtils {
    public static boolean checkPermissions(Activity context) {
        List<String> permissions = new ArrayList<>();
        int phoneStatePermission = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        if (phoneStatePermission == PackageManager.PERMISSION_DENIED) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }
        int storagePermission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (storagePermission == PackageManager.PERMISSION_DENIED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        int cameraPermission = ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (cameraPermission == PackageManager.PERMISSION_DENIED) {
            permissions.add(Manifest.permission.CAMERA);
        }
        if (permissions.size() > 0) {
            ActivityCompat.requestPermissions(context, permissions.toArray(new String[permissions.size()]), 0);
            return false;
        }
        return true;
    }
}
