package com.haonan.Database;


import com.haonan.Common.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class Table {
    private String [] labels;
    private String [] formats;
    private int currentSize = 0;
    private List<Page> pages;

    public Table(String [] labels) {
        this.labels = labels;
        formats = new String[labels.length];
        Arrays.fill(formats, Constant.INT);
        pages = new ArrayList<>();

    }

    public int getDataSize() {
        return this.currentSize;
    }

    public void insertData(int [] dataArr) {

    }

    public void insertData(String [] dataArr) {

    }



}
