package com.haonan.Database;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-22-2021
 * @description:
 **/
public class Tuple {
    private Object [] tupleObj;
    private int size;

    public Tuple(int n) {
        tupleObj = new Object[n];
        size = n;
    }

    public Tuple(Object [] obj) {
        size = obj.length;
        tupleObj = obj;
    }

    public void setTuple(Object [] obj) {
        tupleObj = obj;
    }

    public Object [] getTuple() {
        return tupleObj;
    }

    public void printTuple(String [] format) throws Exception {
        if (tupleObj.length != format.length) {
            throw new Exception("The format length is in compatible with tuple Object");
        }
        for (int i = 0; i < tupleObj.length; i++) {
            Object obj = tupleObj[i];
            if (obj != null) {
                System.out.print(obj);
            }

            if (i != tupleObj.length-1) {
                System.out.print(", ");
            }

        }
        System.out.println("");
    }
}
