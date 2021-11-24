package com.example.fragmenthomework;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FragmentMaster extends Fragment implements FragmentCallbacks {
    SQLiteDatabase db;
    MainActivity main;
    Context context = null;

    int currentPos;
    TextView txtMsg;
    ListView listViewMembers;

    List<String> ids = new ArrayList<String>();
    Integer[] avatars = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};


    public static FragmentMaster newInstance(String strArg) {
        FragmentMaster fragment = new FragmentMaster();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity();
            main = (MainActivity)getActivity();
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout fragment_master = (LinearLayout) inflater.inflate(R.layout.fragment_master, null);

        txtMsg = (TextView) fragment_master.findViewById(R.id.txtMsg);

        listViewMembers = (ListView) fragment_master.findViewById(R.id.listViewMembers);

        getData();


        MemberAdapter adapter = new MemberAdapter(context, R.layout.custom_list_item, ids, avatars);

        listViewMembers.setAdapter(adapter);

        listViewMembers.setSelection(0); listViewMembers.smoothScrollToPosition(0);

        listViewMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentPos = i;
                main.onMsgFromFragToMain("MASTER-FRAG", currentPos);
                txtMsg.setText("Mã số: " + ids.get(currentPos));
            }
        });
        return fragment_master;
    }

    @Override
    public void onMsgFromMainToFragment(int pos) {
        currentPos = pos;
        listViewMembers.performItemClick(
                listViewMembers.getAdapter().getView(currentPos, null, null),
                currentPos,
                listViewMembers.getAdapter().getItemId(currentPos));
    }

    private void getData() {
        try {
            File storagePath = main.getApplication().getFilesDir();
            String myDbPath = storagePath + "/" + "students";
            db = SQLiteDatabase.openDatabase(myDbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        } catch (SQLiteException e) {
            main.finish();
        }

        try{
            String sql = "select Mssv from HOCSINH, LOP where HOCSINH.Malop = LOP.Malop;";
            Cursor c = db.rawQuery(sql,null);
            c.moveToPosition(-1);
            while(c.moveToNext())
            {
                ids.add(c.getString(0));
            }
        }catch (Exception e){}
        finally {
            db.close();
        }
    }
}