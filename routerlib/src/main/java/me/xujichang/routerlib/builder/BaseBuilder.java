package me.xujichang.routerlib.builder;

import android.content.Context;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/24 14:49.
 */

public interface BaseBuilder<T> {
    String createPattern();

    Context getContext();

    T getOptions();
}
