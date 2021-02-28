package com.haonan.Database;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-25-2021
 * @description:
 **/
public class DB {
    private static Map<String, Table> tableMap = new HashMap<>();

    public static void addTable(Table t) {
        tableMap.put(t.getTableName(), t);
    }

    public static Table getTable(String tableName) throws Exception {
        if (!tableMap.containsKey(tableName)) {
            throw new Exception("There is no such table: " + tableName);
        }

        return tableMap.get(tableName);
    }
}
