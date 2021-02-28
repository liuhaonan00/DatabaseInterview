package com.haonan.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-20-2021
 * @description:
 **/
public class Page {
    private int MAX_SIZE = 10000;
    List<Tuple> tuples;

    public Page() {
        tuples = new ArrayList<>();
    }

    public boolean isFull() {
        return tuples.size() == MAX_SIZE;
    }

    public void addTuple(Tuple t) {
        tuples.add(t);
    }

}
