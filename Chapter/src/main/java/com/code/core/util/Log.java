package com.code.core.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private static Logger log = LoggerFactory.getLogger(Log.class);

    public Log() {
    }

    private static Logger buildLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    private static Logger getLogger() {
        try {
            StackTraceElement[] trace = (new Throwable()).getStackTrace();
            StackTraceElement[] var1 = trace;
            int var2 = trace.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                StackTraceElement ele = var1[var3];
                if (!ele.getClassName().equals(Log.class.getName())) {
                    return buildLogger(ele.getClassName() + "." + ele.getMethodName());
                }
            }

            return buildLogger(trace[1].getClassName() + "." + trace[1].getMethodName());
        } catch (Exception var5) {
            log.error("log to kafka getLogger error", var5);
            return log;
        }
    }

    public static void info(String msg, Object... args) {
        (new LoggerProxy(getLogger(), KafkaData.EMPTY)).info(msg, args);
    }

    public static void info(KafkaData kafkaData, String msg, Object... args) {
        (new LoggerProxy(getLogger(), kafkaData)).info(msg, args);
    }

    public static void warn(String msg, Object... args) {
        (new LoggerProxy(getLogger(), KafkaData.EMPTY)).warn(msg, args);
    }

    public static void warn(KafkaData kafkaData, String msg, Object... args) {
        (new LoggerProxy(getLogger(), kafkaData)).warn(msg, args);
    }

    public static void error(String format, Object... args) {
        (new LoggerProxy(getLogger(), KafkaData.EMPTY)).error(format, args);
    }

    public static void error(KafkaData kafkaData, String format, Object... args) {
        (new LoggerProxy(getLogger(), kafkaData)).error(format, args);
    }

    public static void debug(String msg, Object... args) {
        (new LoggerProxy(getLogger(), KafkaData.EMPTY)).debug(msg, args);
    }

    public static void debug(KafkaData kafkaData, String msg, Object... args) {
        (new LoggerProxy(getLogger(), kafkaData)).debug(msg, args);
    }

    public static void trace(String msg, Object... args) {
        (new LoggerProxy(getLogger(), KafkaData.EMPTY)).trace(msg, args);
    }

    public static void trace(KafkaData kafkaData, String msg, Object... args) {
        (new LoggerProxy(getLogger(), kafkaData)).trace(msg, args);
    }

    public static LoggerProxy data(KafkaData kafkaData) {
        return new LoggerProxy(getLogger(), kafkaData);
    }
}
