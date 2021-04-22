package com.code.core.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import org.slf4j.Logger;
import org.slf4j.Marker;

public class LoggerProxy implements Logger {
    private Logger logger;
    private KafkaData kafkaData;

    public LoggerProxy(Logger logger, KafkaData kafkaData) {
        this.logger = logger;
        this.kafkaData = kafkaData;
    }

    protected Object[] arguments(Object[] args) {
        return LogArgumentsUtils.getArguments(args, this.kafkaData);
    }

    public String getName() {
        return this.logger.getName();
    }

    public boolean isTraceEnabled() {
        return this.logger.isTraceEnabled();
    }

    public void trace(String msg) {
        this.logger.trace(msg, this.arguments((Object[])null));
    }

    public void trace(String format, Object arg) {
        this.logger.trace(format, this.arguments(new Object[]{arg}));
    }

    public void trace(String format, Object arg1, Object arg2) {
        this.logger.trace(format, this.arguments(new Object[]{arg1, arg2}));
    }

    public void trace(String format, Object... arguments) {
        this.logger.trace(format, this.arguments(arguments));
    }

    public void trace(String msg, Throwable t) {
        this.logger.trace(msg, this.arguments(new Object[]{t}));
    }

    public boolean isTraceEnabled(Marker marker) {
        return this.logger.isTraceEnabled(marker);
    }

    public void trace(Marker marker, String msg) {
        this.logger.trace(marker, msg, this.arguments((Object[])null));
    }

    public void trace(Marker marker, String format, Object arg) {
        this.logger.trace(marker, format, this.arguments(new Object[]{arg}));
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        this.logger.trace(marker, format, this.arguments(new Object[]{arg1, arg2}));
    }

    public void trace(Marker marker, String format, Object... argArray) {
        this.logger.trace(marker, format, this.arguments(argArray));
    }

    public void trace(Marker marker, String msg, Throwable t) {
        this.logger.trace(marker, msg, this.arguments(new Object[]{t}));
    }

    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public void debug(String msg) {
        this.logger.debug(msg, this.arguments((Object[])null));
    }

    public void debug(String format, Object arg) {
        this.logger.debug(format, this.arguments(new Object[]{arg}));
    }

    public void debug(String format, Object arg1, Object arg2) {
        this.logger.debug(format, this.arguments(new Object[]{arg1, arg2}));
    }

    public void debug(String format, Object... arguments) {
        this.logger.debug(format, this.arguments(arguments));
    }

    public void debug(String msg, Throwable t) {
        this.logger.debug(msg, this.arguments(new Object[]{t}));
    }

    public boolean isDebugEnabled(Marker marker) {
        return this.logger.isDebugEnabled(marker);
    }

    public void debug(Marker marker, String msg) {
        this.logger.debug(marker, msg, this.arguments((Object[])null));
    }

    public void debug(Marker marker, String format, Object arg) {
        this.logger.debug(marker, format, this.arguments(new Object[]{arg}));
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        this.logger.debug(marker, format, this.arguments(new Object[]{arg1, arg2}));
    }

    public void debug(Marker marker, String format, Object... arguments) {
        this.logger.debug(marker, format, this.arguments(arguments));
    }

    public void debug(Marker marker, String msg, Throwable t) {
        this.logger.debug(marker, msg, new Object[]{t});
    }

    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    public void info(String msg) {
        this.logger.info(msg, this.arguments((Object[])null));
    }

    public void info(String format, Object arg) {
        this.logger.info(format, this.arguments(new Object[]{arg}));
    }

    public void info(String format, Object arg1, Object arg2) {
        this.logger.info(format, this.arguments(new Object[]{arg1, arg2}));
    }

    public void info(String format, Object... arguments) {
        this.logger.info(format, this.arguments(arguments));
    }

    public void info(String msg, Throwable t) {
        this.logger.info(msg, this.arguments(new Object[]{t}));
    }

    public boolean isInfoEnabled(Marker marker) {
        return this.logger.isInfoEnabled(marker);
    }

    public void info(Marker marker, String msg) {
        this.logger.info(marker, msg, this.arguments((Object[])null));
    }

    public void info(Marker marker, String format, Object arg) {
        this.logger.info(marker, format, this.arguments(new Object[]{arg}));
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        this.logger.info(marker, format, this.arguments(new Object[]{arg1, arg2}));
    }

    public void info(Marker marker, String format, Object... arguments) {
        this.logger.info(marker, format, this.arguments(arguments));
    }

    public void info(Marker marker, String msg, Throwable t) {
        this.logger.info(marker, msg, this.arguments(new Object[]{t}));
    }

    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }

    public void warn(String msg) {
        this.logger.warn(msg, this.arguments((Object[])null));
    }

    public void warn(String format, Object arg) {
        this.logger.warn(format, this.arguments(new Object[]{arg}));
    }

    public void warn(String format, Object... arguments) {
        this.logger.warn(format, this.arguments(arguments));
    }

    public void warn(String format, Object arg1, Object arg2) {
        this.logger.warn(format, this.arguments(new Object[]{arg1, arg2}));
    }

    public void warn(String msg, Throwable t) {
        this.logger.warn(msg, this.arguments(new Object[]{t}));
    }

    public boolean isWarnEnabled(Marker marker) {
        return this.logger.isWarnEnabled(marker);
    }

    public void warn(Marker marker, String msg) {
        this.logger.warn(marker, msg, this.arguments((Object[])null));
    }

    public void warn(Marker marker, String format, Object arg) {
        this.logger.warn(marker, format, this.arguments(new Object[]{arg}));
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        this.logger.warn(marker, format, this.arguments(new Object[]{arg1, arg2}));
    }

    public void warn(Marker marker, String format, Object... arguments) {
        this.logger.warn(marker, format, this.arguments(arguments));
    }

    public void warn(Marker marker, String msg, Throwable t) {
        this.logger.warn(marker, msg, this.arguments(new Object[]{t}));
    }

    public boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }

    public void error(String msg) {
        this.logger.error(msg, this.arguments((Object[])null));
    }

    public void error(String format, Object arg) {
        this.logger.error(format, this.arguments(new Object[]{arg}));
    }

    public void error(String format, Object arg1, Object arg2) {
        this.logger.error(format, this.arguments(new Object[]{arg1, arg2}));
    }

    public void error(String format, Object... arguments) {
        this.logger.error(format, this.arguments(arguments));
    }

    public void error(String msg, Throwable t) {
        this.logger.error(msg, this.arguments(new Object[]{t}));
    }

    public boolean isErrorEnabled(Marker marker) {
        return this.logger.isErrorEnabled(marker);
    }

    public void error(Marker marker, String msg) {
        this.logger.error(marker, msg, this.arguments(new Object[]{msg}));
    }

    public void error(Marker marker, String format, Object arg) {
        this.logger.error(marker, format, this.arguments(new Object[]{arg}));
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        this.logger.error(marker, format, this.arguments(new Object[]{arg1, arg2}));
    }

    public void error(Marker marker, String format, Object... arguments) {
        this.logger.error(marker, format, this.arguments(arguments));
    }

    public void error(Marker marker, String msg, Throwable t) {
        this.logger.error(marker, msg, this.arguments(new Object[]{t}));
    }
}
