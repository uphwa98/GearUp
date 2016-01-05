package com.shim.gearup;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class DMMonitorReceiver extends BroadcastReceiver {
    private final String TAG = "DMMonitorReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String intentAction = intent.getAction();
        Log.v(TAG, "onReceive() action : " + intentAction);
        if (intentAction == null)
            return;

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");//("HH:mm:ss");
        String strDate = dateFormat.format(date);

        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String inout = pref.getString("inout", "");
        StringBuilder sb = new StringBuilder(inout);
        sb.append('\n');

        String command = intent.getStringExtra("DMCommand");
        Log.v(TAG, "onReceive() command : " + command);

        boolean value = intent.getBooleanExtra("DMValue", false);
        Log.v(TAG, "onReceive() value : " + value);

        if ("Camera".equals(command)) {
            /* In/Out check */
            if (value == true) {
                sb.append("Out / " + strDate);
            } else {
                sb.append("In / " + strDate);
            }

        } else if ("Bluetooth".equals(command)) {

        } else {
            Log.w(TAG, "Invalid command");
        }

        editor.putString("inout", sb.toString());
        editor.commit();

//         Intent newIntent = new Intent(context, MainActivity.class);
//         newIntent.putExtra("")
//         context.start
//         intent.setClass(context, MainActivity.class);
//         context.startActivity(intent);
    }
}
