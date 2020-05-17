package com.infocaliper.projectdep;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by Muhammad Abubakar on 11/17/2017.
 * this is the class that shows dialogue of permission
 * by using this class you can make any permission in android
 */

public class PermissionsHelper {
    public static boolean getPermission(final Activity activity, final String permission, int title, int message, final int id) {
        if (ContextCompat.checkSelfPermission(activity,
                permission) //ACCESS_FINE_LOCATION
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    permission)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // context thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(activity)
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(activity,
                                        new String[]{permission}, id);
                            }
                        })
                        .create()
                        .show();

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        id);
            }
            return false;
        } else {
            return true;
        }
    }

}

