package com.haonan;

import com.haonan.Aggregator.Average;
import com.haonan.Core.LoadData;
import com.haonan.Database.Table;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class DBService {
    private static ClassLoader classLoader = DBService.class.getClassLoader();
    public static void main(String[] args) {
//        System.out.println("Starting the SQL");
//        if (args.length < 2) {
//            printUsage();
//            System.exit(1);
//        }
//
//        System.out.println("Reading database ... ");

        String fileName = classLoader.getResource("csv" + File.separator + "data1.csv").getPath();


        System.out.println(fileName);
        Table table = LoadData.readData(fileName);
//
        System.out.println(Arrays.toString(table.getLabel()));
        System.out.println(table.getTableSize());

        Average average = new Average();
        Map<Integer, Double> map = average.getAvg(table, "a", "b");
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            int key = entry.getKey();
            double value = map.get(key);
            System.out.println("a = " + key + ", avg(b) = " + value);
        }




    }

    public static void printUsage() {
        System.out.println("Usage: ");
        System.out.println("java -jar DatabaseInterview-1.0-SNAPSHOT-jar-with-dependencies.jar DATASOURCE");
    }
}
