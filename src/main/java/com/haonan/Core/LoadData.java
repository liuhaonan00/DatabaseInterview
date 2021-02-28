package com.haonan.Core;
import com.haonan.Database.DB;
import com.haonan.Database.Table;
import com.haonan.Database.Tuple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class LoadData {

    public static void readData(String path, String tableName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String [] labels = reader.readLine().split(",");

            int lineNum = 0;
            //read the first line
            String line = null;
            List<String []> dataList = new ArrayList<>();
            while((line=reader.readLine()) != null){
                String[] data = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                dataList.add(data);
                lineNum++;
            }

            Table t = new Table(tableName, labels);
            for (String [] data: dataList) {
                t.addData(data);
            }

//            System.out.println(t.getTableName());
//            System.out.println(t.getDataSize());

            DB.addTable(t);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
