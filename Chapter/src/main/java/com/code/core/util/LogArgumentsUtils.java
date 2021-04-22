package com.code.core.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogArgumentsUtils {
    private static Logger logger = LoggerFactory.getLogger(LogArgumentsUtils.class);

    public LogArgumentsUtils() {
    }

    public static Object[] getArguments(Object[] args, KafkaData kafkaData) {
        try {
            Object[] newArgs = null;
            if (args == null) {
                newArgs = new Object[1];
                newArgs[newArgs.length - 1] = kafkaData;
            } else {
                newArgs = new Object[args.length + 1];
                if (args.length > 0) {
                    System.arraycopy(args, 0, newArgs, 0, args.length);
                    if (args[args.length - 1] instanceof Throwable) {
                        newArgs[newArgs.length - 1] = args[args.length - 1];
                        newArgs[args.length - 1] = kafkaData;
                    } else {
                        newArgs[newArgs.length - 1] = kafkaData;
                    }
                } else {
                    newArgs[newArgs.length - 1] = kafkaData;
                }
            }

            return newArgs;
        } catch (Exception var3) {
            logger.error("log to kafka getArguments error", var3);
            return args;
        }
    }
}
