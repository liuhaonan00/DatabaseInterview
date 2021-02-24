package com.haonan.Common;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-24-2021
 * @description:
 **/
public class Library {
    public static boolean checkFormat(Object obj, String format) {
        if (obj == null) return true;
        if (obj instanceof Integer && format.equals(Constant.INT)) {
            return true;
        } else if (obj instanceof Double && format.equals(Constant.DOUBLE)) {
            return true;
        } else if (obj instanceof Float && format.equals(Constant.FLOAT)) {
            return true;
        } else if (obj instanceof String && format.equals(Constant.STR)) {
            return true;
        }
        return false;
    }
}
