package com.example.playground;;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Хозяюшка on 14.12.2015.
 */
public class HelpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }
    public void onBackPressed() {
        finish();
    }
}
