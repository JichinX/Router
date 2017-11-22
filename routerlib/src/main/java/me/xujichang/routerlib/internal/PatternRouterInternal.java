package me.xujichang.routerlib.internal;

import android.content.Context;

import java.util.HashMap;
import java.util.Set;

import me.xujichang.routerlib.base.BasePatternRule;
import me.xujichang.routerlib.exceptions.NoPatternRuleException;
import me.xujichang.routerlib.interfaces.RouterInternal;
import me.xujichang.routerlib.rules.ActivityIntentRule;
import me.xujichang.routerlib.rules.ActivityPatternRule;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 10:55.
 */

public class PatternRouterInternal implements RouterInternal<String, String, BasePatternRule> {
    private static PatternRouterInternal sInternal;
    private static HashMap<String, BasePatternRule> sRules;

    public static PatternRouterInternal getInternal() {
        if (null == sInternal) {
            sInternal = ClassHolder.sInternal;
        }
        return sInternal;
    }

    private PatternRouterInternal() {
        sRules = new HashMap<>();
        initDefaultRules();
    }

    public void router(String lines) {
        BasePatternRule rule = getRule(lines);
        checkNoRule(rule, lines);
        rule.router(lines);
    }

    private static class ClassHolder {
        private static PatternRouterInternal sInternal = new PatternRouterInternal();
    }

    @Override
    public void initDefaultRules() {
        addRule(ActivityPatternRule.SCHEME_ACTIVITY, new ActivityPatternRule());
    }

    @Override
    public void addRule(String scheme, BasePatternRule rule) {
        sRules.put(scheme, rule);
    }

    /**
     * 添加转发线路
     *
     * @param pattern 标志
     * @param value   类路径
     */
    @Override
    public void router(String pattern, String value) {
        BasePatternRule rule = getRule(pattern);
        checkNoRule(rule, pattern);
        rule.router(pattern, value);
    }

    private void checkNoRule(BasePatternRule rule, String pattern) {
        if (null == rule) {
            throw new NoPatternRuleException(pattern);
        }
    }

    @Override
    public boolean resolveRule(String pattern) {
        BasePatternRule rule = getRule(pattern);
        return null != rule && rule.resolveRule(pattern);
    }

    @Override
    public String invoke(Context context, String pattern) {
        BasePatternRule rule = getRule(pattern);
        checkNoRule(rule, pattern);
        return rule.invoke(context, pattern);
    }

    @Override
    public BasePatternRule getRule(String pattern) {
        HashMap<String, BasePatternRule> rules = sRules;
        Set<String> schemes = rules.keySet();
        BasePatternRule rule = null;
        for (String scheme : schemes) {
            if (pattern.startsWith(scheme)) {
                rule = rules.get(scheme);
                break;
            }
        }
        return rule;
    }
}
