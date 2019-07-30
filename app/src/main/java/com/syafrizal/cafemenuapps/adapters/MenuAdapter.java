package com.syafrizal.cafemenuapps.adapters;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.syafrizal.cafemenuapps.R;
import com.syafrizal.cafemenuapps.models.Menu;

import java.util.List;

public class MenuAdapter extends BaseQuickAdapter<Menu, BaseViewHolder> {

    public MenuAdapter(@Nullable List<Menu> data) {
        super(R.layout.item_menu,data);
    }

    public MenuAdapter(int layoutResId, @Nullable List<Menu> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Menu item) {

        if (mLayoutResId == R.layout.item_menu){
            TextView menuTitle = helper.getView(R.id.tvMenuName);
            TextView menuDesc = helper.getView(R.id.tvMenuDesc);
            TextView menuPrice = helper.getView(R.id.tvMenuPrice);
            ImageView menuPicture = helper.getView(R.id.ivMenuPic);

            menuTitle.setText(item.getNama());
            menuDesc.setText(item.getPenjelasan());
            menuPrice.setText("Rp." +  item.getHarga());
            Picasso.get().load(item.getGambar()).into(menuPicture);
        }

        if (mLayoutResId == R.layout.item_menu_detail){
            TextView tvDetail = helper.getView(R.id.tvDetail);
            TextView tvTotal = helper.getView(R.id.tvTotal);
            tvDetail.setText(item.getNama());
            tvTotal.setText("Rp "+ item.getHarga());
        }




    }
}
