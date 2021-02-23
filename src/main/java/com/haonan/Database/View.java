package com.haonan.Database;

import com.haonan.Constants.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-22-2021
 * @description:
 **/
public class View {
    private String [] labels;
    private String [] formats;

    private List<Tuple> dataList;

    public View(String [] labels, String [] formats) {
        this.labels = labels;
        this.formats = formats;
        dataList = new ArrayList<>();
    }

    public void insertTuple(Object [] tupleObj) throws Exception {
        //check length
        if (tupleObj.length != labels.length) {
            throw new Exception("");
        }
        //check format
        for (int i = 0; i < tupleObj.length; i++) {
            if (!checkFormat(tupleObj[i], formats[i])) {
                throw new Exception("");
            }
        }
        //create and add tuple
        Tuple tuple = new Tuple(tupleObj);
        dataList.add(tuple);
    }

    public boolean checkFormat(Object obj, String format) {
        if (obj == null) return true;
        if (obj instanceof Integer && format.equals(Constant.INT)) {
            return true;
        } else if (obj instanceof Double && format.equals(Constant.DOUBLE)) {
            return true;
        } else if (obj instanceof Float && format.equals(Constant.FLOAT)) {
            return true;
        } else if (obj instanceof String && format.equals(Constant.STR)) {
            return true;
        }
        return false;
    }

    public void printView() {
        //print labels
        System.out.println("--------");
        String labelsStr = String.join(", ", labels);
        System.out.println(labelsStr);
        System.out.println("--------");
        //print tuples
        for(Tuple t: dataList) {
            try {
                t.printTuple(formats);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
