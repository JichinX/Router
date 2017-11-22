package me.xujichang.routerlib.rules;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import me.xujichang.routerlib.base.BasePatternRule;
import me.xujichang.routerlib.bean.ActivityOptions;
import me.xujichang.routerlib.wapper.ActivityWrapper;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 12:00.
 */

public class ActivityPatternRule extends BasePatternRule {
    public static final String SCHEME_ACTIVITY = "activity";

    @Override
    protected void invokeString(Context context, String pattern) {
        ActivityOptions options = ActivityWrapper.parsePattern(pattern);
        String className = options.getClassName();
        if (className.contains(".")) {

        } else {
            className = mLines.get(className);
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, className));
        context.startActivity(intent);
    }

    @Override
    public void router(String lines) {
        mLines.putAll(ActivityWrapper.parseLines(lines));
    }
}
