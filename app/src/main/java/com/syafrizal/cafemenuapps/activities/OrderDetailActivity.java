package com.syafrizal.cafemenuapps.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.syafrizal.cafemenuapps.Constant;
import com.syafrizal.cafemenuapps.R;
import com.syafrizal.cafemenuapps.adapters.MenuAdapter;
import com.syafrizal.cafemenuapps.models.Pesanan;

import net.glxn.qrgen.android.QRCode;

import java.util.List;

import io.paperdb.Paper;

public class OrderDetailActivity extends AppCompatActivity {

    TextView tvDate;
    TextView tvTime;
    TextView tvMeja;
    RecyclerView recyclerView;
    Button btnSaveOrder;
    ImageView ivQr;
    TextView tvHarga;
    Pesanan pesanan;
    MenuAdapter adapter;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        pesanan = getIntent().getParcelableExtra(Constant.KEYPESAN);


        tvDate = findViewById(R.id.tvDetailDate);
        tvTime = findViewById(R.id.tvDetailTime);
        tvMeja = findViewById(R.id.tvDetailTableNumber);
        tvHarga = findViewById(R.id.tvDetailTotal);
        ivQr = findViewById(R.id.ivQR);
        recyclerView = findViewById(R.id.rvDetail);
        btnSaveOrder = findViewById(R.id.btnSaveOrder);


        //make the qr code
        Bitmap myBitmap = QRCode.from(pesanan.getId()).bitmap();
        ivQr.setImageBitmap(myBitmap);


        tvDate.setText(pesanan.getTanggal());
        tvTime.setText(pesanan.getJam());
        tvMeja.setText(""+pesanan.getNomorMeja());
        tvHarga.setText("Rp "+pesanan.getTotal());


        adapter = new MenuAdapter(R.layout.item_menu_detail,pesanan.getMenus());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //check the intent status delete or add
        position = getIntent().getIntExtra("position",-1);


        if (position > -1){
            btnSaveOrder.setText("Delete");
            btnSaveOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteOnClick();
                }
            });

        }else{
            btnSaveOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveOnClick();
                }
            });
        }




    }

    private void saveOnClick(){
        List<Pesanan> pesananList = Paper.book().read(Constant.KEYPESANANDB);
        pesananList.add(pesanan);
        Paper.book().write(Constant.KEYPESANANDB,pesananList);
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void deleteOnClick(){
        List<Pesanan> pesananList = Paper.book().read(Constant.KEYPESANANDB);
        pesananList.remove(position);
        Paper.book().write(Constant.KEYPESANANDB,pesananList);
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
