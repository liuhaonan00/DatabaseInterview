package com.haonan.Aggregator;

import com.haonan.Common.Constants;
import com.haonan.Common.Library;
import com.haonan.Database.DB;
import com.haonan.Database.Table;
import com.haonan.Database.Tuple;
import com.haonan.Database.View;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
/**
 * @author: create by Hao Nan Liu
 * @date: Mar-01-2021
 * @description:
 **/
public class AverageTest {
    private static ClassLoader classLoader = AverageTest.class.getClassLoader();
    private static String tableName = "foo";
    private static View view = null;
    private static String [] labels = {"a", "b", "c"};
    private static String [] format = {Constants.INT, Constants.INT, Constants.INT};


    @BeforeClass
    public static void setUp() throws Exception {
        String filePath = classLoader
                .getResource("Aggregator/Aggregate.csv").getPath();
        Library.readData(filePath, "foo");

        Table table = DB.getTable(tableName);
        String [] targetLabels = {"a", "b", "c"};
        view = table.getViewFromTable(targetLabels);
        view.printView();

    }


    @Test
    public void test1() throws Exception {
        String [] groupByLabel = {"a", "b"};
        Average avg = new Average(view, groupByLabel, "c");

        View resultView = avg.generateResultView();
        resultView.printView();

        assertArrayEquals(resultView.getLabels(), new String [] {"a", "b", "avg(c)"});
        assertEquals(resultView.getTuples().size(), 6);

        //verify each tuple
        List<Tuple> tupleList = resultView.getTuples();

        assertTrue(hasValue(new Object[] {3, 3, 0d}, tupleList));
        assertTrue(hasValue(new Object[] {4, 4, 4d}, tupleList));
        assertTrue(hasValue(new Object[] {1, 2, 3.5d}, tupleList));
        assertTrue(hasValue(new Object[] {2, 1, 1d}, tupleList));
        assertTrue(hasValue(new Object[] {1, 3, 5d}, tupleList));
        assertTrue(hasValue(new Object[] {5, null, 5d}, tupleList));

    }


    public boolean hasValue(Object [] objs, List<Tuple> tupleList) {
        for (Tuple t: tupleList) {
            Object [] tupleObjs = t.getTuple();

            boolean isSame = true;
            for (int i = 0; i < objs.length; i++) {
                if (tupleObjs[i] == null && objs[i] == null) continue;
                else if (tupleObjs[i] == null || objs[i] == null) {
                    isSame = false;
                    break;
                } else {
                    if (Double.parseDouble(tupleObjs[i].toString())
                            - Double.parseDouble(objs[i].toString()) != 0d) {
                        isSame = false;
                        break;
                    }
                }

            }
            if (isSame) return true;
        }
        return false;
    }
}
