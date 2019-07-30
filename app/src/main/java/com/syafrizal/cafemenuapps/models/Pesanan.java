package com.syafrizal.cafemenuapps.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pesanan implements Parcelable {
    String id;
    String tanggal;
    String jam;
    int nomorMeja;
    List<Menu> menus;
    int total;

    public Pesanan(){
        menus = new ArrayList<>();
        id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public int getNomorMeja() {
        return nomorMeja;
    }

    public void setNomorMeja(int nomorMeja) {
        this.nomorMeja = nomorMeja;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.tanggal);
        dest.writeString(this.jam);
        dest.writeInt(this.nomorMeja);
        dest.writeTypedList(this.menus);
        dest.writeInt(this.total);
    }

    protected Pesanan(Parcel in) {
        this.id = in.readString();
        this.tanggal = in.readString();
        this.jam = in.readString();
        this.nomorMeja = in.readInt();
        this.menus = in.createTypedArrayList(Menu.CREATOR);
        this.total = in.readInt();
    }

    public static final Creator<Pesanan> CREATOR = new Creator<Pesanan>() {
        @Override
        public Pesanan createFromParcel(Parcel source) {
            return new Pesanan(source);
        }

        @Override
        public Pesanan[] newArray(int size) {
            return new Pesanan[size];
        }
    };
}
