package com.syafrizal.cafemenuapps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.syafrizal.cafemenuapps.Constant;
import com.syafrizal.cafemenuapps.R;
import com.syafrizal.cafemenuapps.models.Menu;
import com.syafrizal.cafemenuapps.models.Pesanan;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPaperDb();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }

    public void initPaperDb(){
        //init paperdb
        Paper.init(this);
        if(Paper.book().read(Constant.KEYPESANANDB) == null)
            Paper.book().write(Constant.KEYPESANANDB,new ArrayList<Pesanan>());

        if(Paper.book().read(Constant.KEYMENUDB) == null){
            List<Menu> menus = new ArrayList<>();
            menus.add(new Menu("B01","Minuman","Kopi Hitam","Kopi hitam dibuat dengan teknik espresso , dimana biji kopi digunakan yaitu berasal dari aceh gayo jenis arabika disajikan dengan gula terpisah",10000,"https://asset.kompas.com/crop/7x6:993x663/750x500/data/photo/2017/04/18/2438001086.jpg"));
            menus.add(new Menu("B02","Minuman","Cappuccino","Paduan koi dengan buih susu kental serta menggunakan sirup karamel , dimana biji kopi yang digunakan berasal dari gunung puntang jawa barat jenis robusta",20000,"https://www.thespruceeats.com/thmb/r01TxzOdpWMoltX0aYouUjR78ZY=/450x0/filters:no_upscale():max_bytes(150000):strip_icc()/462541167-56a176865f9b58b7d0bf85bd.jpg"));
            menus.add(new Menu("M03","Minuman","Sparkling Tea","<omi,am teh yang menggunaan daun teh dari pegunungan daerah garut dengan tmabhan sari melati asli dan gula merah alami",15000,"https://cdn.trendhunterstatic.com/thumbs/sparkling-teas.jpeg"));
            menus.add(new Menu("F01","Makanan","Batagor","Baso dan tahu gorang dengan sajian bumbu kacang dan kecap khas bandung",25000,"https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/12/Blog_Resep-dan-Cara-Mudah-Membuat-Batagor-Sederhana-yang-Gurih-nan-Enak.jpg"));
            menus.add(new Menu("F02","Makanan","Cireng","Makanan ringan berupa tepung kanji goreng isi bahan dasar tempe fermentasi yang disebut oncom , disajikan dengan bumbu kacang pedas ",10000,"https://cdn0-production-images-kly.akamaized.net/nt1SMK0h_B-gAW9a5KVzlD5YVsQ=/680x383/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/2551950/original/069517500_1545214506-cara-membuat-cireng.jpg"));
            menus.add(new Menu("F03","Makanan","Nasi Goreng","Nasi goreng ayam yang disajikan dengan telur mata sapi disertai satai ayam",50000,"https://foodfornet.com/wp-content/uploads/Nasi-Goreng-Final-1.jpg"));
            menus.add(new Menu("D01","Dessert","Cheese Cake","Kue tart 1 slice dengan bahan utama cream cheese dengan topping buah buahan asli seperti anggur , jeruk , kiwi",40000,"https://assets.kraftfoods.com/recipe_images/opendeploy/89805-b3fbd1f127524c18584dce486fffa1e532dbaf86_642x428.jpg"));
            menus.add(new Menu("D02","Dessert","Black Salad","Potongan buah buahan segar yang terdiri dari pepaya , jambu , melon dan mangga disajikan dengan bumbu rujak kacang pedas dan gula merah",30000,"https://images.101cookbooks.com/black_bean_salad_recipe.jpg?w=680"));
            Paper.book().write(Constant.KEYMENUDB,menus);
        }


//        if(Paper.book().read("notification"))
//        Paper.book().write("notification",)
    }
}
