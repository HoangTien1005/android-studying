package com.example.fragmenthomework;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class FragmentMaster extends Fragment implements FragmentCallbacks {

    MainActivity main;
    Context context = null;

    int currentPos;
    TextView txtMsg;
    ListView listViewMembers;

    String[] ids = {"19120622", "19120641", "19120644", "19120678"};
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


        MemberAdapter adapter = new MemberAdapter(context, R.layout.custom_list_item, ids, avatars);

        listViewMembers.setAdapter(adapter);

        listViewMembers.setSelection(0); listViewMembers.smoothScrollToPosition(0);

        listViewMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentPos = i;
                main.onMsgFromFragToMain("MASTER-FRAG", currentPos);
                txtMsg.setText("Mã số: " + ids[currentPos]);
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
}