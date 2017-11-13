package com.example.rezafd.my_apps.Model;

/**
 * Created by REZAFD on 12/11/2017.
 */

public class ResponsModel {
    String Kode, Pesan;

    public ResponsModel(String kode, String pesan) {
        Kode = kode;
        Pesan = pesan;
    }

    public ResponsModel() {
    }

    public String getKode() {
        return Kode;
    }

    public void setKode(String kode) {
        Kode = kode;
    }

    public String getPesan() {
        return Pesan;
    }

    public void setPesan(String pesan) {
        Pesan = pesan;
    }
}
