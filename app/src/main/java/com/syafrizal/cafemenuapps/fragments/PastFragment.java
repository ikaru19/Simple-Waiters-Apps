package com.syafrizal.cafemenuapps.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.syafrizal.cafemenuapps.Constant;
import com.syafrizal.cafemenuapps.R;
import com.syafrizal.cafemenuapps.activities.OrderDetailActivity;
import com.syafrizal.cafemenuapps.adapters.PesananAdapter;
import com.syafrizal.cafemenuapps.models.Pesanan;

import java.util.List;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastFragment extends Fragment {
    RecyclerView recyclerView;
    PesananAdapter adapter;
    List<Pesanan> pesananList;


    public PastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_past, container, false);
        pesananList = Paper.book().read(Constant.KEYPESANANDB);

        recyclerView = view.findViewById(R.id.rvPesanan);

        adapter = new PesananAdapter(pesananList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Pesanan pesanan = pesananList.get(position);
                Intent intent = new Intent(getContext(), OrderDetailActivity.class);
                intent.putExtra(Constant.KEYPESAN,pesanan);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });


        return view;
    }

}
