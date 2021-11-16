package com.example.threadhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText edtInput;
    TextView txtProgress;
    ProgressBar progressBar;
    Button btnDoItAgain;

    Handler myHandler = new Handler();


    int globalVar = 0;
    int accum = 0;
    int MAX_PROGRESS = 100;
    double inputValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtInput = (EditText) findViewById(R.id.edtInput);
        txtProgress = (TextView) findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnDoItAgain = (Button) findViewById(R.id.btnDoItAgain);


        btnDoItAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValue = Double.parseDouble(edtInput.getText().toString());
                onStart();
            }
        });

        edtInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE){
                    inputValue = Double.parseDouble(edtInput.getText().toString());
                    onStart();
                }
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnDoItAgain.setEnabled(false);
        progressBar.setMax(MAX_PROGRESS);
        progressBar.setProgress(0);
        accum = 0;
        globalVar = 0;

        Thread myBackgroundThread = new Thread(backgroundTask, "backAlias1");

        myBackgroundThread.start();
    }

    private Runnable foregroundTask = new Runnable() {
        @Override
        public void run() {
            accum = (int) Math.round(globalVar * MAX_PROGRESS / inputValue);
            progressBar.setProgress(accum);
            txtProgress.setText(accum + "%");
            if (accum >= progressBar.getMax()) {
                btnDoItAgain.setEnabled(true);
            }
        }
    };


    private Runnable backgroundTask = new Runnable() {
        @Override
        public void run() {
            try {
                for(int n = 0; n < inputValue; n++) {
                    Thread.sleep(0);
                    globalVar++;
                    myHandler.post(foregroundTask);
                }
            }
            catch(InterruptedException e) {}
        };
    };
}