package com.example.rsshomework;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends Activity {

    String news[] = {"VnExpress", "Báo Thanh Niên","Tuổi trẻ"};
    Integer images[]={R.drawable.express,R.drawable.bao_thanh_nien,R.drawable.tuoi_tre};
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView)findViewById(R.id.gridView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_simple_list_item_1, news);
        gridView.setAdapter(new ImageAdapter(this,images));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, ChannelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Ten", news[position]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        this.setTitle("News App");

    }
}