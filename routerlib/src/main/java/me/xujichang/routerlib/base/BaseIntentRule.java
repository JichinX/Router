package me.xujichang.routerlib.base;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

import me.xujichang.routerlib.interfaces.Rule;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 09:47.
 */

public abstract class BaseIntentRule<T> implements Rule<T, Intent> {
    private HashMap<String, Class<T>> mIntentRules;

    public BaseIntentRule() {
        mIntentRules = new HashMap<>();
    }

    @Override
    public void router(String pattern, T rulervalue) {

    }

    @Override
    public Intent invoke(Context context, String pattern) {
        Class<T> tClass = mIntentRules.get(pattern);
        if (null == tClass) {
            throwRuleException(pattern);
        }
        return new Intent(context, tClass);
    }

    @Override
    public boolean resolveRule(String pattern) {
        return null != mIntentRules.get(pattern);
    }

    /**
     * 抛出异常
     *
     * @param pattern
     */
    protected abstract void throwRuleException(String pattern);
}
