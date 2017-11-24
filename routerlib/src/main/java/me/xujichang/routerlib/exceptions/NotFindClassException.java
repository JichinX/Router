package me.xujichang.routerlib.exceptions;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/24 13:57.
 */

public class NotFindClassException extends RuntimeException {
    public NotFindClassException(String packageName, String className) {
        super(String.format("Can't find the class %s in this package:%s", className, packageName));
    }
}
