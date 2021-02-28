package com.haonan.Common;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-24-2021
 * @description:
 **/
public class Library {



    /*
        check object format
     */
    public static boolean checkFormat(Object obj, String format) {
        if (obj == null) return true;
        if (obj instanceof Integer && format.equals(Constants.INT)) {
            return true;
        } else if (obj instanceof Double && format.equals(Constants.DOUBLE)) {
            return true;
        } else if (obj instanceof Float && format.equals(Constants.FLOAT)) {
            return true;
        } else if (obj instanceof String && format.equals(Constants.STR)) {
            return true;
        }
        return false;
    }
}
