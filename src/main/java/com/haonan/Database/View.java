package com.haonan.Database;

import com.haonan.Common.Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-22-2021
 * @description:
 **/
public class View {
    private String [] labels;
    private Map<String, Integer> labelIdx;
    private String [] formats;
    private List<Tuple> dataList;

    public View(String [] labels, String [] formats) {
        this.labels = labels;
        this.formats = formats;
        dataList = new ArrayList<>();
        labelIdx = new HashMap<>();
        for (int i = 0; i < labels.length; i++) {
            labelIdx.put(labels[i], i);
        }

    }

    public int getLabelIndex(String label) throws Exception {
        if (labelIdx.containsKey(label)) {
            return labelIdx.get(label);
        }
        throw new Exception("Cannot find the label" + label + " in the view");
    }

    public String [] getLabels() {
        return labels;
    }

    public String [] getFormats() {
        return formats;
    }

    public List<Tuple> getTuples() {
        return dataList;
    }

    public void insertTuple(Object [] tupleObj) throws Exception {
        //check length
        if (tupleObj.length != labels.length) {
            throw new Exception("");
        }
        //check format
        for (int i = 0; i < tupleObj.length; i++) {
            if (!Library.checkFormat(tupleObj[i], formats[i])) {
                throw new Exception("");
            }
        }
        //create and add tuple
        Tuple tuple = new Tuple(tupleObj);
        dataList.add(tuple);
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
