package me.xujichang.routerlib.exceptions;

import me.xujichang.routerlib.base.NoSuchRuleException;
import me.xujichang.routerlib.rules.ActivityIntentRule;
import me.xujichang.routerlib.rules.ActivityPatternRule;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 10:04.
 */

public class ActivityNoSuchRuleException extends NoSuchRuleException {

    public ActivityNoSuchRuleException(String pattern) {
        super(ActivityPatternRule.SCHEME_ACTIVITY, pattern);
    }
}
