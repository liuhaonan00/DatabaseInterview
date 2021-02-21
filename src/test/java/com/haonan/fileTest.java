package com.haonan;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-21-2021
 * @description:
 **/
public class fileTest {
    private static ClassLoader classLoader = fileTest.class.getClassLoader();

    @Test
    public void getFileTest() {
        String filePath = classLoader
                .getResource("test_data.csv").getPath();
        System.out.println(filePath);
        assertNotNull(filePath);
    }
}
