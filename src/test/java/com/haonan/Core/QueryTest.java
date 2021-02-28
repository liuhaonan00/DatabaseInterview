package com.haonan.Core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-23-2021
 * @description:
 **/
public class QueryTest {

    @Test
    public void parseQuery() throws Exception {
        String queryStr = "select a, c, avg(b) from tableA group by a,c ;";
        Query query = new Query(queryStr);

    }
}