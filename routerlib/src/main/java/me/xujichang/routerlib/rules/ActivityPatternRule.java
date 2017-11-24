package me.xujichang.routerlib.rules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import me.xujichang.routerlib.base.BasePatternRule;
import me.xujichang.routerlib.bean.ActivityOptions;
import me.xujichang.routerlib.intent.IntentWrapper;
import me.xujichang.routerlib.wapper.ActivityWrapper;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 12:00.
 */

public class ActivityPatternRule extends BasePatternRule {
    public static final String SCHEME_ACTIVITY = "activity";
    public static final int START_NORMAL = 11;
    public static final int START_RESULT = 12;
    public static final int ACTIVITY_FINISH = 13;

    @Override
    protected void invokeString(Context context, String pattern) {
        Activity activity = (Activity) context;
        ActivityOptions options = ActivityWrapper.parsePattern(pattern);
        String className = mLines.get(options.getExecKey());
        if (TextUtils.isEmpty(className)) {
            className = options.getExecKey();
        }
        Intent intent = IntentWrapper.createActivityIntent(context, className, options);
        if (options.getStartType() == START_RESULT && options.getRequestCode() != 0) {
            activity.startActivityForResult(intent, options.getRequestCode());
        } else {
            activity.startActivity(intent);
        }
    }

    @Override
    public void router(String lines) {
        mLines.putAll(ActivityWrapper.parseLines(lines));
    }
}
