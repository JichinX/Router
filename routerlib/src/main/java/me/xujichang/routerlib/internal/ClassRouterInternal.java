package me.xujichang.routerlib.internal;

import android.content.Context;

import java.util.HashMap;
import java.util.Set;

import me.xujichang.routerlib.base.NoSuchRuleException;
import me.xujichang.routerlib.interfaces.Rule;
import me.xujichang.routerlib.rules.ActivityIntentRule;

/**
 * Des:
 * 主要是通过Class 进行路由转发
 * 还是与具体的Class相关，不建议使用此路由
 * 原理很简单：
 * "<scheme,<flag,class>>"结构
 * 1.通过scheme(activity、service、fragment等等包括自定义的scheme)找到维护Class对象的MAP
 * 2.再从Class map集合中取flag对应的 Class 返回Intent
 *
 * @author xjc
 *         Created on 2017/11/22 10:08.
 */

public class ClassRouterInternal {
    private static ClassRouterInternal sInternal;
    private static HashMap<String, Rule> sRules;

    private ClassRouterInternal() {
        sRules = new HashMap<>();
        initDefaultRules();
    }

    private void initDefaultRules() {
        addRule(ActivityIntentRule.PATTERN_ACTIVITY, new ActivityIntentRule());
    }

    public static ClassRouterInternal getInternal() {

        if (null == sInternal) {
            sInternal = ClassHolder.sInternal;
        }
        return sInternal;
    }

    private static class ClassHolder {
        private static ClassRouterInternal sInternal = new ClassRouterInternal();
    }

    public ClassRouterInternal addRule(String flag, Rule rule) {
        sRules.put(flag, rule);
        return this;
    }

    public <T> ClassRouterInternal router(String pattern, T tClass) {
        Rule<T, ?> rule = getRule(pattern);
        checkNullRule(rule, pattern);
        rule.router(pattern, tClass);
        return this;
    }

    private <T> void checkNullRule(Rule<T, ?> rule, String pattern) {
        if (null == rule) {
            throw new NoSuchRuleException("unknown", pattern);
        }
    }

    public <V> V invoke(Context context, String pattern) {
        Rule<?, V> rule = getRule(pattern);
        checkNullRule(rule, pattern);
        return rule.invoke(context, pattern);
    }

    public boolean resolveRouter(String pattern) {
        Rule<?, ?> rule = getRule(pattern);
        return null != rule && rule.resolveRule(pattern);
    }

    private <T, V> Rule<T, V> getRule(String pattern) {
        HashMap<String, Rule> rules = sRules;
        Set<String> flagSet = rules.keySet();
        Rule<T, V> rule = null;
        for (String flag : flagSet) {
            if (pattern.startsWith(flag)) {
                rule = rules.get(flag);
                break;
            }
        }
        return rule;
    }

}
