package me.xujichang.routerlib.interfaces;

import android.content.Context;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 09:44.
 */

public interface Rule<T, V> {
    /**
     * 添加Rule
     *
     * @param pattern
     * @param rulervalue
     */
    void router(String pattern, T rulervalue);

    /**
     * 执行
     *
     * @param context
     * @param pattern
     * @return
     */
    V invoke(Context context, String pattern);

    /**
     * 确定指定的规则是否存在
     *
     * @param pattern
     * @return
     */
    boolean resolveRule(String pattern);
}
