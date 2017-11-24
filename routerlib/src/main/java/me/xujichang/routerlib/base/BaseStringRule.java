package me.xujichang.routerlib.base;

import android.content.Context;

import me.xujichang.routerlib.interfaces.Rule;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 12:04.
 */

public abstract class BaseStringRule<V> implements Rule<String, V> {
    @Override
    public V invoke(Context context, String pattern) {
        invokeString(context, pattern);
        return null;
    }

    protected abstract void invokeString(Context context, String pattern);
}
