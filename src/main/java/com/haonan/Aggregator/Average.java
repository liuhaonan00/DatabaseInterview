package com.haonan.Aggregator;

import com.haonan.Database.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class Average {
    public Map<Integer, Double> getAvg(Table t,String groupByLabel, String anotherLabel) {
        //map
        int [][] data = t.getData();
        String [] label = t.getLabel();
        int size = t.getTableSize();
        //combine
        Map<Integer, AvgInfo> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int key = 0;
            int value = 0;
            for(int j = 0; j < 2; j++) {
                if (groupByLabel.equals(label[j])) {
                    key = data[i][j];
                } else {
                    value = data[i][j];
                }
            }

            if (map.containsKey(key)) {
                AvgInfo info = map.get(key);
                info.addElement(value);
            } else {
                AvgInfo info = new AvgInfo();
                info.addElement(value);
                map.put(key, info);
            }
        }

        //get the result
        Map<Integer, Double> avgResultMap= new HashMap<>();
        for (Map.Entry<Integer, AvgInfo> entry : map.entrySet()) {
            int key = entry.getKey();
            double value = map.get(key).getAvg();
            avgResultMap.put(key, value);
        }
        return avgResultMap;
    }


    private class AvgInfo {
        double sum = 0d;
        long count = 0;

        AvgInfo() {}

        void addElement(int v) {
            sum = sum + (double) v;
            count++;
        }

        double getAvg() {
            if (count == 0) return 0.0;
            else return sum/(double) count;
        }
    }
}
