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
    private String [] formats;
    private Map<String, Integer> labelIdxMap;
    private int currentSize = 0;
    private List<Page> pages;

    public Table(String name, String [] labels) throws Exception {
        tableName = name;
        this.labels = labels;
        formats = new String[labels.length];
        labelIdxMap = new HashMap<>();

        for (int i = 0; i < labels.length; i++) {
            String label = labels[i];
            if (labelIdxMap.containsKey(label)) {
                throw new Exception("Duplicate column name: " + label);
            }
            labelIdxMap.put(label, i);
            formats[i] = Constants.INT;
        }

        pages = new ArrayList<>();

    }

    public View getViewFromTable(String [] targetLabels) throws Exception {
        String [] targetFormats = new String[targetLabels.length];
        for (int i = 0; i < targetLabels.length; i++) {
            int idx = labelIdxMap.get(targetLabels[i]);
            targetFormats[i] = formats[idx];
        }

        //init view
        View view = new View(targetLabels, targetFormats);

        //add data
        if (currentSize > 0) {
            for (Page page: pages) {
                List<Tuple> tuples = page.getTuples();
                for (Tuple tuple: tuples) {
                    Object [] targetObjs = new Object[targetLabels.length];
                    Object [] objs = tuple.getTuple();

                    for (int i = 0; i < targetLabels.length; i++) {
                        int idx = labelIdxMap.get(targetLabels[i]);
                        targetObjs[i] = objs[idx];
                    }
                    view.insertTuple(targetObjs);
                }
            }
        }
        return view;
    }


    public String getTableName() {
        return tableName;
    }

    public String getFormat(String label) throws Exception {
        if (!labelIdxMap.containsKey(label)) {
            throw new Exception("Cannot find label " + label + " in the table " + tableName);
        }
        int idx = labelIdxMap.get(label);
        return formats[idx];

    }

    public void addData(String [] t) throws Exception {
        if (t.length != labels.length) {
            throw new Exception("The length of the tuple is not equal to current table");
        }
        Object [] objects = new Object[labels.length];
        for (int i = 0; i < labels.length; i++) {
            String format = getFormat(labels[i]);
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
