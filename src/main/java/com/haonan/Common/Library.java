package com.haonan.Common;

import com.haonan.Database.DB;
import com.haonan.Database.Table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-24-2021
 * @description:
 **/
public class Library {

    /*
        read data to db
     */
    public static void readData(String path, String tableName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String [] labels = reader.readLine().split(",");

            int lineNum = 0;
            //read the first line
            String line = null;
            List<String []> dataList = new ArrayList<>();
            while((line=reader.readLine()) != null){
                String[] data = line.split(",");
                dataList.add(data);
                lineNum++;
            }

            Table t = new Table(tableName, labels);
            for (String [] data: dataList) {
                t.addData(data);
            }

            DB.addTable(t);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
        check object format
     */
    public static boolean checkFormat(Object obj, String format) {
        if (obj == null) return true;
        if (obj instanceof Integer && format.equals(Constants.INT)) {
            return true;
        } else if (obj instanceof Double && format.equals(Constants.DOUBLE)) {
            return true;
        } else if (obj instanceof Float && format.equals(Constants.FLOAT)) {
            return true;
        } else if (obj instanceof String && format.equals(Constants.STR)) {
            return true;
        }
        return false;
    }
}
