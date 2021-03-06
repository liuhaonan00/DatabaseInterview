package com.haonan.Common;

/**
 * @author: create by Hao Nan Liu
 * @date: Feb-28-2021
 * @description:
 **/
public class Logger {
    private static int logLevel = 1;

    public static void setLogLevel(String level) {
        if (level.toLowerCase().equals("debug")) {
            logLevel = Constants.DEBUG;
            info("The log level has been set to [DEBUG]");
        }
    }

    public static void info(String msg) {
        System.out.println("[INFO] " + msg);
    }

    public static void debug(String msg) {
        if (logLevel >= Constants.DEBUG) {
            System.out.println("[DEBUG] " + msg);
        }
    }

    public static void error(String msg) {
        System.out.println("[ERROR] " + msg);
    }
}
