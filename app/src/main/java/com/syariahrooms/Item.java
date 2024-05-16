package com.syariahrooms;

import java.util.List;

public class Item {
    private String nama;
    private String tipe;
    private String gambar;
    private String kapasitas;
    private String sisakamar;
    private String hargapromo;
    private List<SubItem> subItemList;

    public Item(String nama, String tipe, String gambar, String kapasitas, String sisakamar, String hargapromo, List<SubItem> subItemList) {
        this.nama = nama;
        this.gambar = gambar;
        this.tipe = tipe;
        this.kapasitas = kapasitas;
        this.sisakamar = sisakamar;
        this.hargapromo = hargapromo;
        this.subItemList = subItemList;
    }


    public List<SubItem> getSubItemList() {
        return subItemList;
    }

    public void setSubItemList(List<SubItem> subItemList) {
        this.subItemList = subItemList;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getkapasitas() {
        return kapasitas;
    }

    public void setkapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getSisakamar() {
        return sisakamar;
    }
    public String getTipe() {
        return tipe;
    }

    public void setSisakamar(String sisakamar) {
        this.sisakamar = sisakamar;
    }

    public String getHargapromo() {
        return hargapromo;
    }

    public void setHargapromo(String hargapromo) {
        this.hargapromo = hargapromo;
    }
}
