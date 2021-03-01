package com.haonan.Core;

import com.haonan.Common.Constants;
import com.haonan.Common.Library;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-23-2021
 * @description:
 **/
public class QueryTest {
    private static ClassLoader classLoader = QueryTest.class.getClassLoader();
    private static String tableName = "foo";
    @BeforeClass
    public static void setUp() throws Exception {
        String filePath = classLoader
                .getResource("Core" + File.separator +"QueryTest.csv").getPath();

        Library.readData(filePath, tableName);


    }


    @Test
    public void parseQuery() throws Exception {
        String queryStr = "select a, c, avg(b) from foo group by a,c ;";
        Query query = new Query(queryStr);
        assertEquals(query.tableName, tableName);
        assertArrayEquals(query.labels, new String[] {"a", "c", "b"});
        assertArrayEquals(query.formats, new String[] {Constants.INT, Constants.INT, Constants.INT});
        assertArrayEquals(query.groupBy, new String[] {"a", "c"});
    }

    @Test
    public void parseQueryWrongTableName()  {
        String queryStr = "select a, c, avg(b) from foo1 group by a,c ;";
        try {
            new Query(queryStr);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("There is no such table:"));
        }

    }

    @Test
    public void parseQueryWrongLabel()  {
        String queryStr = "select a, c, avg(b), d from foo group by a,c ;";
        try {
            new Query(queryStr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertEquals("Cannot find label d in the table foo", e.getMessage());
        }

    }
}