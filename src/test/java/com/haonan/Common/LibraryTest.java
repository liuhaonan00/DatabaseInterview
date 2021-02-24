package com.haonan.Common;

import com.haonan.Common.Library;
import com.haonan.Database.View;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-22-2021
 * @description:
 **/
public class LibraryTest {

    @Test
    public void IntTest() {
        String format = "Integer";
        boolean res = Library.checkFormat(null, format);
        assertTrue(res);
        res = Library.checkFormat(1, format);
        assertTrue(res);
        res = Library.checkFormat(1d, format);
        assertFalse(res);
        res = Library.checkFormat(1f, format);
        assertFalse(res);
        res = Library.checkFormat("1f", format);
        assertFalse(res);
    }

    @Test
    public void DoubleTest() {
        String format = "Double";
        boolean res = Library.checkFormat(null, format);
        assertTrue(res);
        res = Library.checkFormat(1d, format);
        assertTrue(res);
        res = Library.checkFormat(1, format);
        assertFalse(res);
        res = Library.checkFormat(1f, format);
        assertFalse(res);
        res = Library.checkFormat("1f", format);
        assertFalse(res);
    }

    @Test
    public void FloatTest() {
        String format = "Float";
        boolean res = Library.checkFormat(null, format);
        assertTrue(res);
        res = Library.checkFormat(1f, format);
        assertTrue(res);
        res = Library.checkFormat(1, format);
        assertFalse(res);
        res = Library.checkFormat(1d, format);
        assertFalse(res);
        res = Library.checkFormat("1f", format);
        assertFalse(res);
    }

    @Test
    public void StringTest() {
        String format = "String";
        boolean res = Library.checkFormat(null, format);
        assertTrue(res);
        res = Library.checkFormat("test", format);
        assertTrue(res);
        res = Library.checkFormat(1d, format);
        assertFalse(res);
        res = Library.checkFormat(1f, format);
        assertFalse(res);
        res = Library.checkFormat(1, format);
        assertFalse(res);
    }


}