package com.example.playground;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private RelativeLayout RLayout;
    Button playFifteens;
    Button back_color;
    TextView start;
    int color = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playFifteens = (Button) findViewById(R.id.playfifteens);
        playFifteens.setOnClickListener(this);
        start = (TextView) findViewById(R.id.textView);
        RLayout = (RelativeLayout) findViewById(R.id.RLayout);
        back_color = (Button) findViewById(R.id.back_color);

    }

    protected void onResume() {
        super.onResume();


    }
    public void onBackPressed() {
        finish();
    }

    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, FifteensActivity.class);
        intent.putExtra("color", color);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void onBackChange(View v) {
        showPopupMenu(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popupmenu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.apricot:
                        RLayout.setBackgroundColor(getResources().getColor(R.color.c_apricot));
                        color = 1;
                        return true;
                    case R.id.light_blue:
                        RLayout.setBackgroundColor(getResources().getColor(R.color.c_light_blue));
                        color = 2;
                        return true;
                    case R.id.gray:
                        RLayout.setBackgroundColor(getResources().getColor(R.color.c_gray));
                        color = 3;
                        return true;
                    case R.id.mint:
                        RLayout.setBackgroundColor(getResources().getColor(R.color.c_mint));
                        color = 4;
                        return true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.show();
    }
}