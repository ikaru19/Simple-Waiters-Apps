package com.syafrizal.cafemenuapps.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
    private String kode;
    private String jenis;
    private String nama;
    private String penjelasan;
    private String gambar;
    private int harga;

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Menu(String kode, String jenis, String nama, String penjelasan, int harga, String gambar) {
        this.kode = kode;
        this.jenis = jenis;
        this.nama = nama;
        this.penjelasan = penjelasan;
        this.harga = harga;
        this.gambar =gambar;
    }


    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPenjelasan() {
        return penjelasan;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kode);
        dest.writeString(this.jenis);
        dest.writeString(this.nama);
        dest.writeString(this.penjelasan);
        dest.writeInt(this.harga);
    }

    protected Menu(Parcel in) {
        this.kode = in.readString();
        this.jenis = in.readString();
        this.nama = in.readString();
        this.penjelasan = in.readString();
        this.harga = in.readInt();
    }

    public static final Parcelable.Creator<Menu> CREATOR = new Parcelable.Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel source) {
            return new Menu(source);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };
}
