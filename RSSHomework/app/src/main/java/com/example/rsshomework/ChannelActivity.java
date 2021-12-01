package com.example.rsshomework;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChannelActivity extends Activity {
    ArrayAdapter<String> adapterChannels;
    ListView myListView;
    TextView tvTitle1;
    TextView tvTitle2;
    Context context;
    SingleItem selectedItem;

    String [][] TNCaptionMenu = {
            {"https://thanhnien.vn/rss/video/giai-tri-334.rss", "Giải Trí"},
            {"https://thanhnien.vn/rss/video/mon-ngon-350.rss", "Món Ngon"},
            {"https://thanhnien.vn/rss/video/video-the-thao-335.rss", "Thể Thao"},
            {"https://thanhnien.vn/rss/video/the-gioi-348.rss", "Thế Giới"},
            {"https://thanhnien.vn/rss/thoi-su/phap-luat-5.rss", "Pháp Luật"},
            {"https://thanhnien.vn/rss/thoi-su/quoc-phong-64.rss", "Quốc Phòng"},
            {"https://thanhnien.vn/rss/the-gioi/chuyen-la-269.rss", "Chuyện Lạ"},
            {"https://thanhnien.vn/rss/van-hoa-93.rss","Văn hóa"},
            {"https://thanhnien.vn/rss/doi-song-17.rss","Đời sống"},
            {"https://thanhnien.vn/rss/tai-chinh-kinh-doanh-49.rss","Tài chính - kinh doanh"},
    };

    String [][] VNExpressCaptionMenu = {
            {"https://vnexpress.net/rss/giai-tri.rss", "Giải Trí"},
            {"https://vnexpress.net/rss/giao-duc.rss", "Giáo Dục"},
            {"https://vnexpress.net/rss/the-thao.rss", "Thể Thao"},
            {"https://vnexpress.net/rss/the-gioi.rss", "Thế Giới"},
            {"https://vnexpress.net/rss/phap-luat.rss", "Pháp Luật"},
            {"https://vnexpress.net/rss/khoa-hoc.rss", "Khoa Học"},
            {"https://vnexpress.net/rss/tam-su.rss", "Tâm Sự"},
            {"https://vnexpress.net/rss/suc-khoe.rss","Sức khỏe"},
            {"https://vnexpress.net/rss/gia-dinh.rss","Đời sống"},
            {"https://vnexpress.net/rss/du-lich.rss","Du lịch"}
    };

    String[][] TuoiTreCaptionMenu = {
            {"https://tuoitre.vn/rss/the-gioi.rss", "Thế giới"},
            {"https://tuoitre.vn/rss/kinh-doanh.rss","Kinh doanh"},
            {"https://tuoitre.vn/rss/van-hoa.rss","Văn hóa"},
            {"https://tuoitre.vn/rss/the-thao.rss","Thể thao"},
            {"https://tuoitre.vn/rss/khoa-hoc.rss","Khoa học"},
            {"https://tuoitre.vn/rss/gia-that.rss","Giả thật"},
            {"https://tuoitre.vn/rss/thoi-su.rss","Thời sự"},
            {"https://tuoitre.vn/rss/phap-luat.rss","Pháp luật"},
            {"https://tuoitre.vn/rss/nhip-song-tre.rss","Nhịp sống trẻ"},
            {"https://tuoitre.vn/rss/nhip-song-so.rss","Công nghệ"},

    };

    String[] myUrlCaption;
    String[] myUrlAddress;
    String [][] currCaptionMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        Intent callingIntent = getIntent();
        Bundle bundle = callingIntent.getExtras();
        myUrlCaption=new String[10] ;
        myUrlAddress=new String[10] ;
        currCaptionMenu=new String[10][10];
        currCaptionMenu=getMenu(bundle);
        for (int i=0; i<myUrlAddress.length; i++) {
            myUrlAddress[i] = currCaptionMenu[i][0];
            myUrlCaption[i] = currCaptionMenu[i][1];
        }
        context = getApplicationContext();
        tvTitle1=(TextView) findViewById(R.id.tv_title1);
        tvTitle2=(TextView) findViewById(R.id.tv_title2);
        myListView = (ListView) findViewById(R.id.myListView);


        tvTitle1.setText("Channel in");
        tvTitle2.setText(bundle.getString("Ten"));
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String urlAddress = myUrlAddress[i], urlCaption = myUrlCaption[i];
//create an Intent to talk to activity: ShowHeadlines
                Intent callShowHeadlines = new Intent(ChannelActivity.this, ShowHeadlines.class);
//prepare a Bundle and add the input arguments: url & caption
                Bundle myData = new Bundle();
                myData.putString("urlAddress", urlAddress); myData.putString("urlCaption", urlCaption);
                myData.putString("title",bundle.getString("Ten"));
                callShowHeadlines.putExtras(myData); startActivity(callShowHeadlines);
            }
        });
        adapterChannels = new ArrayAdapter<String>(this, R.layout.my_simple_list_item_1, myUrlCaption);
        myListView.setAdapter(adapterChannels);
    }
    private String[][] getMenu(Bundle bundle){
        if (bundle.getString("Ten").equals("VnExpress")){

            return VNExpressCaptionMenu;
        }
        else if(bundle.getString("Ten").equals("Báo Thanh Niên")) {

            return TNCaptionMenu;
        }
        else{
            return TuoiTreCaptionMenu;
        }

    }

}