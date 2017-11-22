package me.xujichang.routerlib.base;

/**
 * Des:自定义运行时
 *
 * @author xjc
 *         Created on 2017/11/22 10:02.
 */

public class NoSuchRuleException extends RuntimeException {

    public NoSuchRuleException(String name, String pattern) {
        super(String.format("%s cannot be resolved with pattern %s, have you declared it in your Router?", name, pattern));
    }
}
