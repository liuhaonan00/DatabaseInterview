package com.haonan.Aggregator;

import com.haonan.Common.Constants;
import com.haonan.Database.Tuple;
import com.haonan.Database.View;

import java.util.*;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class Average {
    private String [] labels;
    private Map<String, Integer> labelIdxMap;
    private String [] targetFormats;
    private Map<String, AvgInfo> kvMap;
    private int avgLabelIdx = -1;

    public Average(View view, String [] groupByLabels, String avgLabel) throws Exception {
        labels = view.getLabels();
        labelIdxMap = new HashMap<>();
        kvMap = new HashMap<>();

        String [] formats = view.getFormats();
        targetFormats = new String[formats.length];
        //get formats
        for (int i = 0; i < labels.length; i++) {
            //update label index map
            labelIdxMap.put(labels[i], i);

            //update label format
            if (labels[i].equals(avgLabel)) {
                targetFormats[i] = Constants.DOUBLE;
            } else {
                targetFormats[i] = formats[i];
            }
        }
        //change the label to avg(label)
        avgLabelIdx = view.getLabelIndex(avgLabel);
        labels[avgLabelIdx] = "avg(" + avgLabel + ")";

        processAggregate(view, groupByLabels, avgLabel);

    }

    private void processAggregate(View view, String [] groupByLabels, String avgLabel) throws Exception {
        List<Tuple> tupleList = view.getTuples();
        int coreNum = Runtime.getRuntime().availableProcessors();
//        System.out.println("Core number: " + coreNum);

        for (Tuple t: tupleList) {
            String [] keys = new String[groupByLabels.length];

            for (int i = 0; i < groupByLabels.length; i++) {
                int idx = view.getLabelIndex(groupByLabels[i]);
                Object obj = t.getTuple()[idx];
                if (obj == null) {
                    keys[i] = groupByLabels[i] + ":NULL";
                } else {
                    keys[i] = groupByLabels[i] + ":" + String.valueOf(obj);
                }

            }

            String key = generateKey(keys);
            int valueIdx = view.getLabelIndex(avgLabel);
            Object obj = t.getTuple()[valueIdx];

            if (kvMap.containsKey(key)) {
                AvgInfo info = kvMap.get(key);
                info.addElement(obj);
            } else {
                AvgInfo info = new AvgInfo();
                info.addElement(obj);
                kvMap.put(key, info);
            }

        }

    }

    public View generateResultView() throws Exception {
        View view = new View(labels, targetFormats);
        //generate tuple
        for (Map.Entry<String, AvgInfo> entry : kvMap.entrySet()) {
            String keys = entry.getKey();
            String [] labelValueKey = keys.split("-");
            Object [] tupleObj = new Object[labels.length];
            for (String labelValue: labelValueKey) {
                String label = labelValue.split(":")[0];
                String valueStr = labelValue.split(":")[1];

                int labelIdx = labelIdxMap.get(label);

                if (valueStr.equals("NULL")) {
                    tupleObj[labelIdx] = null;
                    continue;
                }
                //transfer by format
                switch (targetFormats[labelIdx]) {
                    case Constants.INT:
                        tupleObj[labelIdx] = Integer.parseInt(valueStr);
                        break;
                    case Constants.FLOAT:
                        tupleObj[labelIdx] = Float.parseFloat(valueStr);
                        break;
                    case Constants.DOUBLE:
                        tupleObj[labelIdx] = Double.parseDouble(valueStr);
                        break;
                    default:
                        tupleObj[labelIdx] = valueStr;
                }

            }
            //get the avg result
            double value = entry.getValue().getAvg();
            tupleObj[avgLabelIdx] = value;

            view.insertTuple(tupleObj);

        }
        return view;
    }


    private String generateKey(String [] keys) {
        if (keys.length >= 1) {
            return String.join("-", keys);
        }

        return "";
    }




    private class AvgInfo {
        double sum = 0d;
        long count = 0;

        AvgInfo() {}

        void addElement(Object v) throws Exception {
            if (v == null) {
                return;
            }

            if (v instanceof String) {
                throw new Exception("Error format: " + v);
            }

            sum = sum + Double.parseDouble(v.toString());
            count++;
        }

        double getAvg() {
            if (count == 0) return 0.0;
            else return sum/(double) count;
        }
    }
}
