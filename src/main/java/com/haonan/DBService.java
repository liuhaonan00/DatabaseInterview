package com.haonan;

import com.haonan.Aggregator.Average;
import com.haonan.Common.Library;
import com.haonan.Common.Logger;
import com.haonan.Core.Query;
import com.haonan.Database.Table;
import com.haonan.Database.View;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class DBService {
    private static ClassLoader classLoader = DBService.class.getClassLoader();

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            printUsage();
            System.exit(1);
        }
        Logger.info("Starting the SQL");
        Logger.info("Reading database ... ");

        if (args.length >= 2) {
            String logLevel = args[1];
            if (logLevel.toLowerCase().equals("debug")) {
                Logger.setLogLevel("debug");
            }
        }

        try {
            String fileName = args[0];
            Library.readData(fileName, "foo");
        } catch (Exception e) {
            Logger.error("Load data error: " + e.getMessage());
            System.exit(1);
        }


        while(true) {
            Logger.info("Please input valid sql query");
            Scanner input = new Scanner(System.in);
            String queryStr = input.nextLine();
            try {
                Query query = new Query(queryStr);
                View resultView = query.getResult();
                resultView.printView();
            } catch (Exception e) {
                Logger.error("SQL error: " + e.getMessage());
            }

        }
    }

    public static void printUsage() {
        System.out.println("Usage: ");
        System.out.println("java -jar DatabaseInterview-1.0-SNAPSHOT-jar-with-dependencies.jar $DATA_SOURCE");
        System.out.println("To upgrade debug log level: ");
        System.out.println("java -jar DatabaseInterview-1.0-SNAPSHOT-jar-with-dependencies.jar $DATA_SOURCE DEBUG");
    }
}
