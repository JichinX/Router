package me.xujichang.routerlib.interfaces;

import android.content.Context;

/**
 * Des:
 * 规定路由核心对象 应该具有的基本功能
 * 1.可添加路由规则
 * 2.可查找
 * 3.可扩展
 * 4.可执行
 * T,V规定 路由规则的类型
 *
 * @author xjc
 *         Created on 2017/11/22 10:56.
 */

public interface RouterInternal<T, V, R extends Rule<T, V>> {
    /**
     * 路由规则初始化，包括每条规则的初始化
     */
    void initDefaultRules();

    /**
     * 可添加
     * 添加路由规则
     *
     * @param scheme
     * @param rule
     */
    void addRule(String scheme, R rule);

    /**
     * 添加规则下的转发线路
     */
    void router(String flag, String value);

    /**
     * 可查找
     * 查找具体路由规则
     *
     * @param pattern 某条路由规则中的key
     * @return 有 无
     */
    boolean resolveRule(T pattern);

    /**
     * 可执行
     * 执行某规则中的某条转发映射
     *
     * @param context
     * @param pattern
     * @return
     */
    V invoke(Context context, T pattern);

    /**
     * 查找某一规则
     *
     * @return
     */
    R getRule(String pattern);

}
