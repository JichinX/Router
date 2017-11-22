package me.xujichang.routerlib.exceptions;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 13:35.
 */

public class NoPatternRuleException extends RuntimeException {
    public NoPatternRuleException(String pattern) {
        this("UnKnown", pattern);
    }

    public NoPatternRuleException(String scheme, String pattern) {
        super(String.format("The %s cannot be resolved with pattern %s, have you declared it in your Router?", scheme, pattern));
    }
}
