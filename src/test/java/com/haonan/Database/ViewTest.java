package com.haonan.Database;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-22-2021
 * @description:
 **/
public class ViewTest {
    private static View view;

    @BeforeClass
    public static void setUp() throws Exception {
        String [] labels = {"a", "b", "c"};
        String [] format = {"String", "Integer", "Double"};
        view = new View(labels, format);
        view.insertTuple(new Object[]{"Test1", 1, 1d});
        view.insertTuple(new Object[]{null, null, null});

    }

    @Test
    public void IntTest() {
        String format = "Integer";
        boolean res = view.checkFormat(null, format);
        assertTrue(res);
        res = view.checkFormat(1, format);
        assertTrue(res);
        res = view.checkFormat(1d, format);
        assertFalse(res);
        res = view.checkFormat(1f, format);
        assertFalse(res);
        res = view.checkFormat("1f", format);
        assertFalse(res);
    }

    @Test
    public void DoubleTest() {
        String format = "Double";
        boolean res = view.checkFormat(null, format);
        assertTrue(res);
        res = view.checkFormat(1d, format);
        assertTrue(res);
        res = view.checkFormat(1, format);
        assertFalse(res);
        res = view.checkFormat(1f, format);
        assertFalse(res);
        res = view.checkFormat("1f", format);
        assertFalse(res);
    }

    @Test
    public void FloatTest() {
        String format = "Float";
        boolean res = view.checkFormat(null, format);
        assertTrue(res);
        res = view.checkFormat(1f, format);
        assertTrue(res);
        res = view.checkFormat(1, format);
        assertFalse(res);
        res = view.checkFormat(1d, format);
        assertFalse(res);
        res = view.checkFormat("1f", format);
        assertFalse(res);
    }

    @Test
    public void StringTest() {
        String format = "String";
        boolean res = view.checkFormat(null, format);
        assertTrue(res);
        res = view.checkFormat("test", format);
        assertTrue(res);
        res = view.checkFormat(1d, format);
        assertFalse(res);
        res = view.checkFormat(1f, format);
        assertFalse(res);
        res = view.checkFormat(1, format);
        assertFalse(res);
    }

    @Test
    public void printTest() {
        view.printView();
    }

}