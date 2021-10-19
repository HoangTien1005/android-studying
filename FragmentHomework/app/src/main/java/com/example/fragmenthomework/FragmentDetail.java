package com.example.fragmenthomework;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentDetail extends Fragment implements FragmentCallbacks, View.OnClickListener {
    Context context;
    MainActivity main;
    int currentPos;
    TextView txtId, txtName, txtClassroom, txtScore;
    Button btnPrevious, btnNext, btnFirst, btnLast;

    public static FragmentDetail newInstance(String strArg1) {
        FragmentDetail fragment = new FragmentDetail();
        Bundle bundle = new Bundle();
        bundle.putString("arg1", strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException( "Activity must implement MainCallbacks");
        }

        context =  getActivity();
        main = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout fragment_detail = (LinearLayout) inflater.inflate(R.layout.fragment_detail, null);
        txtId = (TextView) fragment_detail.findViewById(R.id.txtId);
        txtName = (TextView) fragment_detail.findViewById(R.id.txtName);
        txtClassroom = (TextView) fragment_detail.findViewById(R.id.txtClassroom);
        txtScore = (TextView) fragment_detail.findViewById(R.id.txtScore);

        btnPrevious = (Button) fragment_detail.findViewById(R.id.btnPrevious);
        btnNext = (Button) fragment_detail.findViewById(R.id.btnNext);
        btnFirst = (Button) fragment_detail.findViewById(R.id.btnFirst);
        btnLast = (Button) fragment_detail.findViewById(R.id.btnLast);

        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnFirst.setOnClickListener(this);
        btnLast.setOnClickListener(this);
        try {
            Bundle arguments = getArguments();
            txtId.setText(arguments.getString("arg1", ""));
        }
        catch (Exception e) { }



        return fragment_detail;
    }

    @Override
    public void onMsgFromMainToFragment(int pos) {
        currentPos = pos;
        txtId.setText(Member.MEMBERS[pos].id);
        txtName.setText(Member.MEMBERS[pos].name);
        txtClassroom.setText("Lớp: " +  Member.MEMBERS[pos].classroom);
        txtScore.setText("Điểm trung bình: " + Member.MEMBERS[pos].score);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btnPrevious.getId()) {
            currentPos = (currentPos + 4 - 1) % 4;
        }

        if(view.getId() == btnNext.getId()) {
            currentPos = (currentPos + 4 + 1) % 4;
        }

        if(view.getId() == btnFirst.getId()) {
            currentPos = 0;

        }
        if(view.getId() == btnLast.getId()) {
            currentPos = 3;
        }
        main.onMsgFromFragToMain("DETAIL-FRAG", currentPos);
    }
}