package com.example.a18097511_nguyendinhhoangmy;

import java.io.Serializable;

public class Song implements Serializable {
    private String tenbh,tencs,thoiluong;
    private int anh;

    public String getTenbh() {
        return tenbh;
    }

    public void setTenbh(String tenbh) {
        this.tenbh = tenbh;
    }

    public String getTencs() {
        return tencs;
    }

    public void setTencs(String tencs) {
        this.tencs = tencs;
    }

    public String getThoiluong() {
        return thoiluong;
    }

    public void setThoiluong(String thoiluong) {
        this.thoiluong = thoiluong;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public Song(String tenbh, String tencs, String thoiluong, int anh) {
        this.tenbh = tenbh;
        this.tencs = tencs;
        this.thoiluong = thoiluong;
        this.anh = anh;
    }
}
