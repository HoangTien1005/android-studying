package com.example.fragmenthomework;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends FragmentActivity implements MainCallbacks {
    SQLiteDatabase db;
    FragmentTransaction ft;
    FragmentDetail fragmentDetail;
    FragmentMaster fragmentMaster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File storagePath = getApplication().getFilesDir();
        String myDbPath = storagePath + "/" + "students";
        try {
            openDatabase();
            dropTable();
            InsertData();
            getData();
            db.close();
        } catch (SQLiteException e) {

        }

        ft = getSupportFragmentManager().beginTransaction();
        fragmentMaster = FragmentMaster.newInstance("master");
        ft.replace(R.id.main_holder_fragment_master, fragmentMaster);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        fragmentDetail = FragmentDetail.newInstance("detail");
        ft.replace(R.id.main_holder_fragment_detail, fragmentDetail);
        ft.commit();
    }

    @Override
    public void onMsgFromFragToMain(String sender, int pos) {

        Toast.makeText(getApplication(), sender + ": " + pos, Toast.LENGTH_LONG).show();
        if (sender.equals("DETAIL-FRAG")) {
            try {
                fragmentMaster.onMsgFromMainToFragment(pos);
            } catch (Exception e) {
            }
        }
        if (sender.equals("MASTER-FRAG")) {
            try {
                fragmentDetail.onMsgFromMainToFragment(pos);
            } catch (Exception e) {
            }
        }
    }

    private void openDatabase() {
        try {
            File storagePath = getApplication().getFilesDir();
            String myDbPath = storagePath + "/" + "students";
            db = SQLiteDatabase.openDatabase(myDbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        } catch (SQLiteException e) {
            finish();
        }
    }

    private void dropTable() {

        try {
            db.execSQL(" drop table HOCSINH; ");


        } catch (Exception e) { }
        try{
            db.execSQL(" drop table LOP; ");
        } catch (Exception e){}
    }

    private void InsertData() {

        db.beginTransaction();
        try { // create table
            db.execSQL("create table LOP (Malop integer PRIMARY KEY autoincrement, Tenlop text not null);");
            db.execSQL("create table HOCSINH (Mssv text PRIMARY KEY, TenHS text not null, Malop integer not null, Diem float not null," +
                    " FOREIGN KEY (Malop) REFERENCES LOP (Malop));");
// commit your changes
            db.setTransactionSuccessful();
        } catch (SQLException e1) {
        } finally {
            db.endTransaction();
        }
// populate table
        db.beginTransaction();
        try { // insert rows
            db.execSQL("insert into LOP(Malop, Tenlop)"+"values(1,'19CTT1');");
            db.execSQL("insert into LOP(Malop, Tenlop)"+"values(2,'19CTT2');");
            db.execSQL("insert into LOP(Malop, Tenlop)"+"values(3,'19CTT3');");
            db.execSQL("insert into LOP(Malop, Tenlop)"+"values(4,'19CTT4');");


            db.execSQL("insert into HOCSINH(Mssv, TenHS, Malop, Diem)"+"values('19120622','Nguyễn Minh Phụng','4',5);");
            db.execSQL("insert into HOCSINH(Mssv, TenHS, Malop, Diem)"+"values('19120641','Nguyễn Đức Phát Tài','3',7.5);");
            db.execSQL("insert into HOCSINH(Mssv, TenHS, Malop, Diem)"+"values('19120644','Lê Đức Tâm','2',6.5);");
            db.execSQL("insert into HOCSINH(Mssv, TenHS, Malop, Diem)"+"values('19120678','Nguyễn Hoàng Tiến','1',5.5);");

// commit your changes
            db.setTransactionSuccessful();
        } catch (SQLiteException e2) {
        } finally {
            db.endTransaction();
        }
    }// insertSomeData

    private void getData() {
        try{
            String sql = "select tenHS, Mssv, LOP.Tenlop, Diem from HOCSINH, LOP where HOCSINH.Malop = LOP.Malop;";
            Cursor c = db.rawQuery(sql,null);
            c.moveToPosition(-1);
            while(c.moveToNext())
            {
                Member.MEMBERS.add(new Member(c.getString(0),c.getString(1),c.getString(2),c.getFloat(3)));
            }
        }catch (Exception e){}
    }
}
