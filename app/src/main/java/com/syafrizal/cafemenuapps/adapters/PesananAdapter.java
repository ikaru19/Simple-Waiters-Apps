package com.syafrizal.cafemenuapps.adapters;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.syafrizal.cafemenuapps.R;
import com.syafrizal.cafemenuapps.models.Pesanan;

import java.util.List;

public class PesananAdapter extends BaseQuickAdapter<Pesanan, BaseViewHolder> {

    public PesananAdapter(@Nullable List<Pesanan> data) {
        super(R.layout.item_pesanan,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Pesanan item) {
        TextView tvNumber = helper.getView(R.id.tvNumberPesan);
        TextView tvTanggal = helper.getView(R.id.tvTanggal);
        TextView tvJam = helper.getView(R.id.tvJam);


        tvNumber.setText(""+item.getNomorMeja());
        tvTanggal.setText(item.getTanggal());
        tvJam.setText(item.getJam());

    }
}
