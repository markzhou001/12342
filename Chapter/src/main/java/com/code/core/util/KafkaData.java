package com.code.core.util;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


public class KafkaData {
    public static KafkaData EMPTY = new KafkaData("EMPTY", 0);
    private String category;
    private int errorCode;
    private String loggerName;

    public KafkaData() {
        this.errorCode = 0;
        this.category = EMPTY.getCategory();
    }

    public KafkaData(String category, int errorCode) {
        this.errorCode = 0;
        this.category = category;
        this.errorCode = errorCode;
    }

    public KafkaData(ErrMsg errMsg) {
        this.errorCode = 0;
        this.category = errMsg.getName();
        this.errorCode = errMsg.getCode();
    }

    private KafkaData(Builder builder) {
        this.errorCode = 0;
        this.setCategory(builder.category);
        this.setErrorCode(builder.errorCode);
        this.setLoggerName(builder.loggerName);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static KafkaData of(String category) {
        return new KafkaData(category, 0);
    }

    public static KafkaData of(String category, int errorCode) {
        return new KafkaData(category, errorCode);
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLoggerName() {
        return this.loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String toString() {
        return "KafkaData{category='" + this.category + '\'' + ", loggerName='" + this.loggerName + '\'' + '}';
    }

    public static final class Builder {
        private String category;
        private int errorCode;
        private String loggerName;

        private Builder() {
        }

        public Builder category(String val) {
            this.category = val;
            return this;
        }

        public Builder errorCode(int val) {
            this.errorCode = val;
            return this;
        }

        public Builder loggerName(String val) {
            this.loggerName = val;
            return this;
        }

        public KafkaData build() {
            return new KafkaData(this);
        }
    }
}
