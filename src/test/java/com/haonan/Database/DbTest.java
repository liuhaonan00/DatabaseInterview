package com.haonan.Database;

import com.haonan.Common.Library;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-21-2021
 * @description:
 **/
public class DbTest {
    private static ClassLoader classLoader = DbTest.class.getClassLoader();
    private static String filePath;
    private static String tableName = "foo";
    @BeforeClass
    public static void setUp() throws Exception {
        filePath = classLoader
                .getResource("Database/DbTest.csv").getPath();
        Library.readData(filePath, "foo");
    }

    @Test
    public void loadDB() {
        try {
            Table table = DB.getTable(tableName);
            assertEquals(table.getDataSize(), 1998);
            assertEquals(table.getTableName(), tableName);
        } catch (Exception e) {
            assertEquals(0, 1);
        }
    }

    @Test
    public void loadErrorDB() {
        try {
            DB.getTable("foo1");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "There is no such table: foo1");
        }
    }

}
