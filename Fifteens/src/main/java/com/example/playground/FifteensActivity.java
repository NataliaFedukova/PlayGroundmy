package com.example.playground;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;

public class FifteensActivity extends Activity implements View.OnClickListener{

    public static final String APP_DATA = "DataConfig";//файл сохранения настроек
    public static final String APP_VALUE_BEST = "record";
    private SharedPreferences mSettings;

    private GridLayout field;
    private RelativeLayout FRLayout;
    private TextView movesView;
    private Button bt[][];
    private TextView best;
    int movesCount;
    int DIALOG_EXIT = 1;
    int DIALOG_CONGRATULATION = 2;
    int color ;
    int bestRes ;
    boolean isRecord = false;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifteens);
        bt = new Button[4][4];
        movesCount = 0;
        bt[0][0] = (Button) findViewById(R.id.bt1);
        bt[0][1] = (Button) findViewById(R.id.bt2);
        bt[0][2] = (Button) findViewById(R.id.bt3);
        bt[0][3] = (Button) findViewById(R.id.bt4);
        bt[1][0] = (Button) findViewById(R.id.bt5);
        bt[1][1] = (Button) findViewById(R.id.bt6);
        bt[1][2] = (Button) findViewById(R.id.bt7);
        bt[1][3] = (Button) findViewById(R.id.bt8);
        bt[2][0] = (Button) findViewById(R.id.bt9);
        bt[2][1] = (Button) findViewById(R.id.bt10);
        bt[2][2] = (Button) findViewById(R.id.bt11);
        bt[2][3] = (Button) findViewById(R.id.bt12);
        bt[3][0] = (Button) findViewById(R.id.bt13);
        bt[3][1] = (Button) findViewById(R.id.bt14);
        bt[3][2] = (Button) findViewById(R.id.bt15);
        bt[3][3] = (Button) findViewById(R.id.bt16);
        field = (GridLayout) findViewById(R.id.fieldLayout);
        movesView = (TextView) findViewById(R.id.movesView);
        FRLayout = (RelativeLayout) findViewById(R.id.PlayLayout);
        best = (TextView)findViewById(R.id.outBest);
        mSettings = getSharedPreferences(APP_DATA, Context.MODE_PRIVATE);
        bestRes = mSettings.getInt(APP_VALUE_BEST, 1000);
        best.setText(String.valueOf(bestRes));
        color = getIntent().getIntExtra("color", 0);
        setColor(color);
        field.setColumnCount(4);
        field.setColumnCount(4);
        int h = bt[0][0].getWidth();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                bt[i][j].setOnClickListener(this);
                bt[i][j].setHeight(h);
            }
        }
        newGame();
    }

    private void setColor(int c) {
        if(c == 0)
            FRLayout.setBackgroundColor(Color.WHITE);
        if(c == 1)
            FRLayout.setBackgroundColor(getResources().getColor(R.color.c_apricot));
        if(c == 2)
            FRLayout.setBackgroundColor(getResources().getColor(R.color.c_light_blue));
        if(c == 3)
            FRLayout.setBackgroundColor(getResources().getColor(R.color.c_gray));
        if(c == 4)
            FRLayout.setBackgroundColor(getResources().getColor(R.color.c_mint));
    }

    public  void mainBack(){
        finish();
    }
    //____________________Actions___________________________
    public  void onExit(View v){
        showDialog(DIALOG_EXIT);
    }
    public  void onHelp(View v){
        Intent intent = new Intent(FifteensActivity.this, HelpActivity.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        showDialog(DIALOG_EXIT);
    }
    public  void restart(View  v){
        newGame();
        bt[3][3].setText("");
    }
    public void onClick(View v) {

        int i_ = 0, j_ = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (bt[i][j].getId() == v.getId()) {
                    i_ = i;
                    j_ = j;
                }
            }
        }
        //top
        if (i_ == 0) {
            if (j_ == 0) {
                if (isEmpty(bt[i_][j_ + 1]))
                    swapBt(bt[i_][j_ + 1], bt[i_][j_]);
                if (isEmpty(bt[i_ + 1][j_]))
                    swapBt(bt[i_ + 1][j_], bt[i_][j_]);
            }
            if (j_ == 3) {
                if (isEmpty(bt[i_][j_ - 1]))
                    swapBt(bt[i_][j_ - 1], bt[i_][j_]);
                if (isEmpty(bt[i_ + 1][j_]))
                    swapBt(bt[i_ + 1][j_], bt[i_][j_]);
            }
            if (j_ == 1 || j_ == 2) {
                if (isEmpty(bt[i_][j_ - 1]))
                    swapBt(bt[i_][j_ - 1], bt[i_][j_]);
                if (isEmpty(bt[i_ + 1][j_]))
                    swapBt(bt[i_ + 1][j_], bt[i_][j_]);
                if (isEmpty(bt[i_][j_ + 1]))
                    swapBt(bt[i_][j_ + 1], bt[i_][j_]);
            }
        }
        if (i_ == 3) { //bottom
            if (j_ == 0) {
                if (isEmpty(bt[i_][j_ + 1]))
                    swapBt(bt[i_][j_ + 1], bt[i_][j_]);
                if (isEmpty(bt[i_ - 1][j_]))
                    swapBt(bt[i_ - 1][j_], bt[i_][j_]);
            }
            if (j_ == 3) {
                if (isEmpty(bt[i_][j_ - 1]))
                    swapBt(bt[i_][j_ - 1], bt[i_][j_]);
                if (isEmpty(bt[i_ - 1][j_]))
                    swapBt(bt[i_ - 1][j_], bt[i_][j_]);
            }
            if (j_ == 1 || j_ == 2) {
                if (isEmpty(bt[i_][j_ - 1]))
                    swapBt(bt[i_][j_ - 1], bt[i_][j_]);
                if (isEmpty(bt[i_ - 1][j_]))
                    swapBt(bt[i_ - 1][j_], bt[i_][j_]);
                if (isEmpty(bt[i_][j_ + 1]))
                    swapBt(bt[i_][j_ + 1], bt[i_][j_]);
            }
        }
        if (i_ == 1 || i_ == 2) {
            if (j_ == 0) {
                if (isEmpty(bt[i_ + 1][j_]))
                    swapBt(bt[i_ + 1][j_], bt[i_][j_]);
                if (isEmpty(bt[i_ - 1][j_]))
                    swapBt(bt[i_ - 1][j_], bt[i_][j_]);
                if (isEmpty(bt[i_][j_ + 1]))
                    swapBt(bt[i_][j_ + 1], bt[i_][j_]);
            }
            if (j_ == 3) {
                if (isEmpty(bt[i_ + 1][j_]))
                    swapBt(bt[i_ + 1][j_], bt[i_][j_]);
                if (isEmpty(bt[i_ - 1][j_]))
                    swapBt(bt[i_ - 1][j_], bt[i_][j_]);
                if (isEmpty(bt[i_][j_ - 1]))
                    swapBt(bt[i_][j_ - 1], bt[i_][j_]);
            }
            if (j_ == 1 || j_ == 2) {
                if (isEmpty(bt[i_ + 1][j_]))
                    swapBt(bt[i_ + 1][j_], bt[i_][j_]);
                if (isEmpty(bt[i_ - 1][j_]))
                    swapBt(bt[i_ - 1][j_], bt[i_][j_]);
                if (isEmpty(bt[i_][j_ - 1]))
                    swapBt(bt[i_][j_ - 1], bt[i_][j_]);
                if (isEmpty(bt[i_][j_ + 1]))
                    swapBt(bt[i_][j_ + 1], bt[i_][j_]);
            }
        }
        movesView.setText(String.valueOf(movesCount));
        if (isDone()) {
            if(bestRes > movesCount) {
                SharedPreferences.Editor ed = mSettings.edit();
                ed.putInt(APP_VALUE_BEST, movesCount);
                ed.commit();
                best.setText(String.valueOf(movesCount));
                bestRes = movesCount;
                isRecord = true;
            }
            showDialog(DIALOG_CONGRATULATION);
        }
    }
    //_________________Game Logic_____________________________
    public void newGame() {
        int valmas[] = new int[15];
        for (int i = 0; i < 15; i++) {
            valmas[i] = i + 1;
        }
        Random rnd = new Random();
        for (int i = 14; i > 0; i--) {
            int j = rnd.nextInt(i);
            int tmp = valmas[i];
            valmas[i] = valmas[j];
            valmas[j] = tmp;
        }
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if(i*4+j != 15)
                    bt[i][j].setText(String.valueOf(valmas[i*4+j]));
        movesCount = 0;
        isRecord = false;
        movesView.setText("");
    }

    public boolean isDone()
    {
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                if(i*4+j != 15) {
                    if (!bt[i][j].getText().toString().equals(String.valueOf(i*4+j+1)))
                        return false;
                }
            }
        }
        return  true;
    }

    public void swapBt(Button bt1, Button bt2) {
        CharSequence temp = bt1.getText();
        bt1.setText(bt2.getText());
        bt2.setText(temp);
        movesCount++;
    }

    public boolean isEmpty(Button b) {  return String.valueOf(b.getText()) == "";}

    //_________________Dialog Windows_____________________
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            // заголовок
            adb.setTitle(getResources().getString(R.string.tdreturn_main));
            adb.setIcon(R.drawable.warning);
            // сообщение
            adb.setMessage(getResources().getString(R.string.return_main_dialog_text));
            adb.setPositiveButton(getResources().getString(R.string.yes), exitDialog);
            adb.setNeutralButton(getResources().getString(R.string.no), exitDialog);
            // создаем диалог
            return adb.create();
        }
        if(id == DIALOG_CONGRATULATION)
        {
            AlertDialog.Builder ADBC0nr = new AlertDialog.Builder(this);
            ADBC0nr.setTitle(getResources().getString(R.string.tdgame_win));
            ADBC0nr.setMessage(getResources().getString(R.string.game_win_dialog_text));
            if(isRecord)
                ADBC0nr.setIcon(R.drawable.gold);
            else
                ADBC0nr.setIcon(R.drawable.silver);
            ADBC0nr.setPositiveButton(getResources().getString(R.string.yes), congrialog);
            ADBC0nr.setNegativeButton(getResources().getString(R.string.no), congrialog);
            return ADBC0nr.create();
        }
        return super.onCreateDialog(id);
    }
    OnClickListener exitDialog = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    mainBack();
                    break;
                // негаитвная кнопка
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };
    OnClickListener congrialog = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_POSITIVE:
                    newGame();
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    mainBack();
                    break;
            }
        }
    };
}