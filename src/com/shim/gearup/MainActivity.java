package com.shim.gearup;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
    private final String TAG = "MainActivity";

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) this.findViewById(R.id.text_main);

        Log.v(TAG, "onCreate() Bundle : " + savedInstanceState);

        // Intent intent = this.getIntent();
        // int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
        // BluetoothAdapter.STATE_OFF);
        // Log.v(TAG, "onCreate() state : " + state);
        //
        // int previousState =
        // intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_STATE,
        // BluetoothAdapter.STATE_OFF);
        // Log.v(TAG, "onCreate() previousState : " + previousState);

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String inout = pref.getString("inout", "");
        mTextView.setText("History : " + inout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_clear) {

            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();

            mTextView.setText("History : ");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
