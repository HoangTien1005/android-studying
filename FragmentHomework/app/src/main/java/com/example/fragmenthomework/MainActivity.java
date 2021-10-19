package com.example.fragmenthomework;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;



public class MainActivity extends FragmentActivity implements MainCallbacks{
    FragmentTransaction ft;
    FragmentDetail fragmentDetail;
    FragmentMaster fragmentMaster;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            }
            catch (Exception e) {}
        }
        if (sender.equals("MASTER-FRAG")) {
            try {
                fragmentDetail.onMsgFromMainToFragment(pos);
            }
            catch (Exception e) {}
        }
    }
}
