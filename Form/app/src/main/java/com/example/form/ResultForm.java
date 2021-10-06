package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultForm extends Activity {

    private TextView txtUsername, txtPassword, txtBirthdate, txtGender, txtHobbies;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_form);

        txtUsername = (TextView)findViewById(R.id.txtUsername);
        txtPassword = (TextView)findViewById(R.id.txtPassword);
        txtBirthdate = (TextView)findViewById(R.id.txtBirthdate);
        txtGender = (TextView)findViewById(R.id.txtGender);
        txtHobbies = (TextView)findViewById(R.id.txtHobbies);

        Bundle bundle = getIntent().getExtras();

        String strUsername = bundle.getString("Username");
        String strPassword = bundle.getString("Password");
        String strBirthdate = bundle.getString("Birthdate");
        String strGender = bundle.getString("Gender");
        String strHobbies = bundle.getString("Hobbies");

        txtUsername.setText(txtUsername.getText().toString() + strUsername);
        txtPassword.setText(txtPassword.getText().toString() + toPassword(strPassword));
        txtBirthdate.setText(txtBirthdate.getText().toString() + strBirthdate);
        txtGender.setText(txtGender.getText().toString() + strGender);
        txtHobbies.setText(txtHobbies.getText().toString() + strHobbies);

        btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private String toPassword(String value) {
        String password = "";
        for(int i = 0; i < value.length(); i++) password += "*";
        return password;
    }
}