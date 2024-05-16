package com.syariahrooms;

import java.util.List;

public class arraycheckout {
    private String nama;
    private String tipe;

    public arraycheckout(String nama, String tipe) {
        this.nama = nama;
        this.tipe = tipe;

    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setTipe(String tipe) {
        this.tipe = tipe;
    }


    public String getTipe() {
        return tipe;
    }


}
