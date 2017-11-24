package me.xujichang.routerlib.wapper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.lang.ref.WeakReference;

import me.xujichang.routerlib.builder.ActivityOptionsBuilder;
import me.xujichang.routerlib.builder.BaseBuilder;
import me.xujichang.routerlib.internal.PatternRouterInternal;
import me.xujichang.routerlib.rules.ActivityPatternRule;

/**
 * Des: 对路由框架 核心进行包装，暴露出简单的方法供调用。
 *
 * @author xjc
 *         Created on 2017/11/22 10:58.
 */

public class RouterWrapper {
    public static final String SCHEME_FRAGMENT = "fragment";
    public static final String SCHEME_WEBVIEW = "webview";
    public static final String SCHEME_TOAST = "toast";
    public static final String SCHEME_LOADING = "loading";
    public static final String SCHEME_DIALOG = "dialog";
    private static final String TAG = "RouterWrapper";

    private static WeakReference<Context> sWeakReference;
    private static RouterWrapper sWrapper;
    private static PatternRouterInternal sPatternRouterInternal;

    private RouterWrapper() {
        sPatternRouterInternal = PatternRouterInternal.getInternal();
    }

    private static RouterWrapper getWrapper() {
        if (null == sWrapper) {
            sWrapper = ClassHolder.sWrapper;
        }
        return sWrapper;
    }

    public static void init(Context context) {
        getWrapper();
        sWeakReference = new WeakReference<Context>(context);
    }

    public static void addPatternLines(String type, String flag, String value) {
        sPatternRouterInternal.router(ActivityWrapper.createLines(flag, value));
    }

    private static class ClassHolder {
        private static RouterWrapper sWrapper = new RouterWrapper();
    }

    public static ActivityOptionsBuilder createActivityBuilder(Context context) {
        return createActivityBuilder(context, null);
    }

    public static ActivityOptionsBuilder createActivityBuilder(String type) {
        return createActivityBuilder(null, type);
    }

    public static ActivityOptionsBuilder createActivityBuilder() {
        return createActivityBuilder(null, null);
    }

    public static ActivityOptionsBuilder createActivityBuilder(Context context, String type) {
        return new ActivityOptionsBuilder(context, type, getWrapper());
    }

    public void invoke(BaseBuilder builder) {
        sPatternRouterInternal.invoke(builder.getContext(), builder.createPattern());
    }
}
