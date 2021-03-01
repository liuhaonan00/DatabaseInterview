package com.haonan.Database;

import com.haonan.Common.Constants;
import com.haonan.Common.Library;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author: create by Hao Nan Liu
 * @date: Mar-01-2021
 * @description:
 **/
public class TableViewTest {
    private static ClassLoader classLoader = TableViewTest.class.getClassLoader();
    private static String filePath;
    private static String tableName = "foo";
    private static Table table = null;

    @BeforeClass
    public static void setUp() throws Exception {
        filePath = classLoader
                .getResource("Database/DbTest.csv").getPath();
        Library.readData(filePath, "foo");

        table = DB.getTable(tableName);
    }

    @Test
    public void VerifyTable() throws Exception {
        assertEquals(table.pages.size(), 1);
        assertEquals(table.getFormat("a"), Constants.INT);

        assertArrayEquals(table.labels, new String[] {"a", "b"});

        System.out.println(Arrays.toString(table.labels));
    }

    @Test
    public void getViewTest() throws Exception {
        String [] targetLabels = {"a"};
        View view = table.getViewFromTable(targetLabels);
        assertArrayEquals(view.getLabels(), new String[] {"a"});

    }

    @Test
    public void getViewTestError() {
        String [] targetLabels = {"aa"};
        try {
            table.getViewFromTable(targetLabels);
            assertEquals(1, 0);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "There is no label: aa in the table");
        }
    }

    @Test
    public void getViewTest2() throws Exception {
        String [] targetLabels = {"b", "a"};
        View view = table.getViewFromTable(targetLabels);
        assertArrayEquals(view.getLabels(), new String[] {"b", "a"});
        assertEquals(view.getTuples().size(), 1998);
    }

}
