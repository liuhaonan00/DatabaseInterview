package com.haonan.Core;

import com.haonan.Database.DB;
import com.haonan.Database.Table;
import com.haonan.Database.View;

import java.util.Arrays;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class Query {
    private String query;
    private String tableName;
    private Table table;
    private String [] labels;
    private String [] groupBy;
    private String [] formats;

    String aggregateLabel = "";

    public Query(String q) throws Exception {
        this.query = preprocess(q);
        tableName = getTableName(query);
        table = DB.getTable(tableName);

        labels = getLabels(query);
        formats = getFormats(labels);
        groupBy = getGroupBy(query);



    }

    public View getResult() {

        //init view
        View view = new View(labels, formats);

        //Init aggregator




        return view;
    }


    private String [] getFormats(String [] labels) throws Exception {
        String [] formats = new String[labels.length];
        for (int i = 0; i < labels.length; i++) {
            String label = labels[i];
            formats[i] = table.getFormat(labels[i]);
        }

        return labels;
    }


    private String [] getGroupBy(String query) {
        String groupByStr = query.split("group by")[1].replaceAll(" ", "");
        String [] groupBy = groupByStr.split(",");
        System.out.println(Arrays.toString(groupBy));
        return groupBy;
    }

    private String [] getLabels(String query) {
        String labelStr = query.split("select|from")[1].replaceAll(" ", "");
        String [] labels = labelStr.split(",");

        for (int i = 0; i < labels.length; i++) {
            if (labels[i].contains("avg")) {
                labels[i] = labels[i].substring(4, labels[i].length()-1);
                aggregateLabel = labels[i];
            }
        }


        System.out.println(Arrays.toString(labels));
        return labels;
    }

    private String getTableName(String query) {
        String tableName = query.split("from|group by")[1];
        tableName = tableName.replaceAll(" ", "");
        System.out.println(tableName);
        return tableName;
    }


    private String preprocess(String q) {
        //step 0, to lowercase
        q = q.toLowerCase();
        //step 1, remove unuseful space
        int startIdx = 0;
        while (startIdx < q.length()) {
            if(q.charAt(startIdx) == ' ') {
                startIdx++;
            } else break;
        }
        int endIdx = q.length()-1;
        while (endIdx >= startIdx) {
            if(q.charAt(endIdx) == ' ' || q.charAt(endIdx) == ';') {
                endIdx--;
            }
            else break;
        }
        q = q.substring(startIdx, endIdx+1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q.length(); ++i) {
            sb.append(q.charAt(i));
            if(q.charAt(i) == ' ') {
                for (int j = i+1; j < q.length(); ++j) {
                    if (q.charAt(j) != ' ' ) {
                        i = j-1;
                        break;
                    }
                }
            }
        }

        String query = sb.toString();
        System.out.println(query);
        return query;
    }

}
