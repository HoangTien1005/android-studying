package com.example.customlistdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView txtMsg;
    ListView listViewMembers;
    String[] names = {"Nguyễn Minh Phụng", "Nguyễn Đức Phát Tài", "Lê Đức Tâm", "Nguyễn Hoàng Tiến"};
    String[] ids = {"19120622", "19120641", "19120644", "19120678"};
    Integer[] avatars = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        listViewMembers = (ListView) findViewById(R.id.listViewMembers);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_list_item, names, ids, avatars);
        listViewMembers.setAdapter(adapter);

        listViewMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txtMsg.setText("You choose: " + names[i]);
            }
        });
    }
}