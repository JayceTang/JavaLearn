package com.jayce.util;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName ExceptionUtil
 * @description
 * @date 2019/4/30 16:45
 */
public class ExceptionUtil {

    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }
}
