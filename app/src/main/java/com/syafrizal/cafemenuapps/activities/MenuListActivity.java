package com.syafrizal.cafemenuapps.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.syafrizal.cafemenuapps.Constant;
import com.syafrizal.cafemenuapps.R;
import com.syafrizal.cafemenuapps.adapters.MenuAdapter;
import com.syafrizal.cafemenuapps.models.Menu;
import com.syafrizal.cafemenuapps.models.Pesanan;

import java.util.List;

import io.paperdb.Paper;

public class MenuListActivity extends AppCompatActivity {
    Pesanan pesanan;
    RecyclerView recyclerView;
    List<Menu> menuList;
    MenuAdapter adapter;
    TextView textViewTotal;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        menuList = Paper.book().read(Constant.KEYMENUDB);
        pesanan = getIntent().getParcelableExtra(Constant.KEYPESAN);
        TextView textViewNumber = findViewById(R.id.textViewNumber);
        Button btnOrder = findViewById(R.id.btnOrder);
        textViewTotal = findViewById(R.id.textViewTotal);
        textViewNumber.setText("Table Number : " +  pesanan.getNomorMeja());
        total = 0;
        if (total == 0){
            textViewTotal.setVisibility(View.INVISIBLE);
        }
        recyclerView = findViewById(R.id.rvMenus);
        adapter = new MenuAdapter(menuList);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Menu menu = menuList.get(position);
                pesanan.getMenus().add(menu);
                total++;
                Toast.makeText(MenuListActivity.this, menu.getNama()+" Added", Toast.LENGTH_SHORT).show();
                textViewTotal.setVisibility(View.VISIBLE);
                textViewTotal.setText("Total Item : " + total);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Menu> menus = pesanan.getMenus();
                int total = 0;
                for (Menu menu:menus){
                    total = total + menu.getHarga();
                }
                pesanan.setTotal(total);
                Intent intent = new Intent(MenuListActivity.this,OrderDetailActivity.class);
                intent.putExtra(Constant.KEYPESAN,pesanan);
                startActivity(intent);
            }
        });
    }
}
