package com.example.form;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;


public class RegisterForm extends Activity {

    private Button btnSelect, btnReset, btnSignUp;
    private EditText edtUsername, edtPassword, edtRetype, edtBirthdate;
    private RadioGroup radioGroupGender;
    private RadioButton radMale, radFemale;
    private CheckBox chkTennis, chkFootball, chkOthers;
    private View.OnClickListener btnListener= new View.OnClickListener() {
        public void onClick(View view) {
            //btnReset.onClick();
            if (view.getId() == btnReset.getId()) {
                resetAll();
            }
//        btnSelect.onClick();
            if (view.getId() == btnSelect.getId()) {
                selectDate();
            }
//        btnSignUp.onClick();
            if (view.getId() == btnSignUp.getId()) {
                if (checkAll()) {
                    Intent intent = new Intent(RegisterForm.this, ResultForm.class);
                    intent.putExtra("Username", edtUsername.getText().toString());
                    intent.putExtra("Password", edtPassword.getText().toString());
                    intent.putExtra("Birthdate", edtBirthdate.getText().toString());

                    if (radMale.isChecked()) intent.putExtra("Gender", radMale.getText());
                    else intent.putExtra("Gender", radFemale.getText());

                    String strHobbies = "";
                    if (chkTennis.isChecked()) strHobbies += chkTennis.getText() + " ";
                    if (chkFootball.isChecked()) strHobbies += chkFootball.getText() + " ";
                    if (chkOthers.isChecked()) strHobbies += chkOthers.getText() + " ";
                    if (strHobbies.isEmpty()) intent.putExtra("Hobbies", "None");
                    else intent.putExtra("Hobbies", strHobbies);

                    startActivity(intent);
                    finish();
                }
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtRetype = (EditText) findViewById(R.id.edtRetype);
        edtBirthdate = (EditText) findViewById(R.id.edtBirthdate);

        radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);

        radMale = (RadioButton) findViewById(R.id.radMale);
        radFemale = (RadioButton) findViewById(R.id.radFemale);

        chkTennis = (CheckBox) findViewById(R.id.chkTennis);
        chkFootball = (CheckBox) findViewById(R.id.chkFootball);
        chkOthers = (CheckBox) findViewById(R.id.chkOthers);

        btnSelect.setOnClickListener(btnListener);
        btnReset.setOnClickListener(btnListener);
        btnSignUp.setOnClickListener(btnListener);
    }

    private void resetAll() {
        edtUsername.getText().clear();
        edtPassword.getText().clear();
        edtRetype.getText().clear();
        edtBirthdate.getText().clear();

        radioGroupGender.clearCheck();

        chkTennis.setChecked(false);
        chkFootball.setChecked(false);
        chkOthers.setChecked(false);
    }

    private void selectDate(){
        final Calendar c=Calendar.getInstance();
        int day=c.get(Calendar.DATE);
        int month=c.get(Calendar.MONTH);
        int year= c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog= new DatePickerDialog(RegisterForm.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month++;
                edtBirthdate.setText(dayOfMonth+"/"+month +"/"+year);
            }
        },year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void makeToast(String message) {
        Toast.makeText(RegisterForm.this, message, Toast.LENGTH_LONG).show();
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    private boolean checkAll() {
        if (isEmpty(edtUsername)) {
            makeToast("Please enter your Username!");
            edtUsername.requestFocus();
            return false;
        }
        if (isEmpty(edtPassword)) {
            makeToast("Please enter your Password!");
            edtPassword.requestFocus();
            return false;
        }
        if (isEmpty(edtRetype)) {
            makeToast("Please retype your Password!");
            edtRetype.requestFocus();
            return false;
        }
        if (!edtPassword.getText().toString().matches(edtRetype.getText().toString())) {
            makeToast("Your password is not matched!");
            edtRetype.requestFocus();
            return false;
        }
        if (isEmpty(edtBirthdate)) {
            makeToast("Please choose your Birthdate!");
            return false;
        }
        if (!radMale.isChecked() && !radFemale.isChecked()) {
            makeToast("Please choose your Gender!");
            return false;
        }

        return true;
    }
}