package com.shim.gearup;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.content.SharedPreferences;

public class BluetoothEventReceiver extends BroadcastReceiver {
    private final String TAG = "BluetoothEventReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String intentAction = intent.getAction();
        Log.v(TAG, "onReceive() action : " + intentAction);
        if (intentAction == null)
            return;

        int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF);
        Log.v(TAG, "onReceive() state : " + state);

        int previousState = intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_STATE, BluetoothAdapter.STATE_OFF);
        Log.v(TAG, "onReceive() previousState : " + previousState);

        // Intent newIntent = new Intent(context, MainActivity.class);
        // newIntent.putExtra("")
        // context.start

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String strDate = dateFormat.format(date);

        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String inout = pref.getString("inout", "");
        StringBuilder sb = new StringBuilder(inout);
        sb.append('\n');

        if (state == BluetoothAdapter.STATE_ON) {
            // intent.setClass(context, MainActivity.class);
            // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // // intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            // context.startActivity(intent);

            sb.append("On/" + strDate);
        } else if (state == BluetoothAdapter.STATE_OFF) {
            sb.append("Off/" + strDate);
        }
        editor.putString("inout", sb.toString());
        editor.commit();
    }
}
