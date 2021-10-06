package com.example.mylifecycle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;


public class MainActivity extends Activity {

//    khai báo các widget hoặc đối tượng cần xài
    private Button btnExit;
    private EditText txtMsg;
    private TextView txtSpy;
    private ConstraintLayout myScreen;
    private Context context;
    private String PREFNAME = "myPrefFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        nối các widget với layout
        btnExit = (Button) findViewById(R.id.btnExit);
        txtMsg = (EditText) findViewById(R.id.txtMsg);
        txtSpy = (TextView) findViewById(R.id.txtSpy);
        myScreen = (ConstraintLayout) findViewById(R.id.myScreen);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                lấy ra input rồi đưa vào hàm setBackground
                String chosenColor = s.toString();
                setBackgroundColor(chosenColor, myScreen);

            }
        });

        context = getApplicationContext();
        Toast.makeText(context, "onCreate", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        khi on start thì load dữ liệu
        loadStateData();
        Toast.makeText(context, "onStart", Toast.LENGTH_SHORT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(context, "onResume", Toast.LENGTH_SHORT);

    }

    @Override
    protected void onPause() {
        super.onPause();
//        trở về home thì lấy ra màu hiện tại
        String chosenColor = txtMsg.getText().toString();
        saveStateData(chosenColor);
        Toast.makeText(context, "onPause", Toast.LENGTH_SHORT);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(context, "onStop", Toast.LENGTH_SHORT);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(context, "onDestroy", Toast.LENGTH_SHORT);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(context, "onRestart", Toast.LENGTH_SHORT);

    }

    private void setBackgroundColor(String chosenColor, ConstraintLayout myScreen) {
        int color = 0xffffff;
        if (chosenColor.contains("tam")) color = 0xff120644;
        if (chosenColor.contains("tai")) color = 0xff120641;
        if (chosenColor.contains("tien")) color = 0xff120678;
        if (chosenColor.contains("phung")) color = 0xff120622;
        if (color != 0xffffff) {
            myScreen.setBackgroundColor(color);
            txtMsg.setTextColor(0xffF36530);
            txtSpy.setTextColor(0xffF36530);
            btnExit.setTextColor(0xffF36530);
        }
    }

    private void saveStateData(String chosenColor) {
        SharedPreferences myPrefFile = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPrefFile.edit();
        myEditor.putString("chosenBackgroundColor", chosenColor);
        myEditor.commit();
    }

    private void loadStateData() {
        SharedPreferences myPrefFile = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        String defaultValue = "tam";
        String key = "chosenBackgroundColor";
        if (myPrefFile != null && myPrefFile.contains(key)) {
            String color = myPrefFile.getString(key, defaultValue);
            setBackgroundColor(color, myScreen);
        }
    }
}