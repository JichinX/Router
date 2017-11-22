package me.xujichang.routerlib.rules;

import android.app.Activity;

import me.xujichang.routerlib.base.BaseIntentRule;
import me.xujichang.routerlib.exceptions.ActivityNoSuchRuleException;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 09:46.
 */

public class ActivityIntentRule extends BaseIntentRule<Activity> {

    public static final String PATTERN_ACTIVITY = "activity";

    /**
     * {@inheritDoc}
     *
     * @param pattern
     */
    @Override
    protected void throwRuleException(String pattern) {
        throw new ActivityNoSuchRuleException(pattern);
    }
}
