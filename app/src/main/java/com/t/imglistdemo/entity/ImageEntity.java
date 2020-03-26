package com.t.imglistdemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageEntity implements Serializable {

    private int total;
    private int totalHits;
    private ArrayList<Hit> hits;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public ArrayList<Hit> getHits() {
        return hits;
    }

    public void setHits(ArrayList<Hit> hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "ImageEntity{" +
                "total=" + total +
                ", totalHits=" + totalHits +
                ", hits=" + hits +
                '}';
    }
}
