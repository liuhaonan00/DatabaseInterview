package com.haonan.Database;


import com.haonan.Common.Constants;

import java.util.*;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class Table {
    private String tableName;
    private String [] labels;
    private Map<String, String> labelFormatMap;
    private int currentSize = 0;
    private List<Page> pages;

    public Table(String name, String [] labels) throws Exception {
        tableName = name;
        this.labels = labels;
        labelFormatMap = new HashMap<>();

        for (String label: labels) {
            if (labelFormatMap.containsKey(label)) {
                throw new Exception("Duplicate column name: " + label);
            }
            labelFormatMap.put(label, Constants.INT);
        }

        pages = new ArrayList<>();

    }

    public String getTableName() {
        return tableName;
    }

    public String getFormat(String label) throws Exception {
        if (!labelFormatMap.containsKey(label)) {
            throw new Exception("Cannot find labels in the Table " + tableName);
        }

        return labelFormatMap.get(label);

    }

    public void addData(String [] t) throws Exception {
        if (t.length != labels.length) {
            throw new Exception("The length of the tuple is not equal to current table");
        }
        Object [] objects = new Object[labels.length];
        for (int i = 0; i < labels.length; i++) {
            String format = labelFormatMap.get(labels[i]);
            if (t[i].equals("") && !format.equals(Constants.STR)) {
                objects[i] = null;
                continue;
            }

            switch (format) {
                case Constants.INT:
                    objects[i] = Integer.parseInt(t[i]);
                    break;
                case Constants.FLOAT:
                    objects[i] = Float.parseFloat(t[i]);
                    break;
                case Constants.DOUBLE:
                    objects[i] = Double.parseDouble(t[i]);
                    break;
                default:
                    objects[i] = t[i];
            }
        }
        Tuple tuple = new Tuple(objects);
        addTuple(tuple);
    }

    public void addTuple(Tuple t) {

        //add size
        currentSize++;

        //add tuples
        Page lastPage = null;
        if (!pages.isEmpty()) {
            lastPage = pages.get(pages.size()-1);
        }

        if ( lastPage == null || lastPage.isFull()) {
            Page page = new Page();
            page.addTuple(t);
            pages.add(page);
            return;
        }

        lastPage.addTuple(t);
    }

    public int getDataSize() {
        return this.currentSize;
    }


}
