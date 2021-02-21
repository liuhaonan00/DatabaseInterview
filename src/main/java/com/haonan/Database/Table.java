package com.haonan.Database;


import java.util.Arrays;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class Table {
    private int [][] data;
    private String [] label;
    private int currentSize = 0;
    private int labelNum = 0;

    public Table(int n, String [] labels) {
        int labelNum = labels.length;
        data = new int [n][labelNum];
        label = labels;

    }

    public int getTableSize() {
        return this.currentSize;
    }

    public int [][] getData() {
        return this.data;
    }

    public String [] getLabel() {
        return this.label;
    }

    public void insertData(int [] dataArr) {
        if (currentSize == data.length) {
            System.out.println("Insert error");
        }
        data[currentSize] = dataArr;
        currentSize++;
    }

    public void insertData(String [] dataArr) {
        if (currentSize == data.length) {
            System.out.println("Insert error");
        }
        int [] datas =  Arrays.stream(dataArr).mapToInt(Integer::parseInt).toArray();

        data[currentSize] = datas;
        currentSize++;
    }



}
