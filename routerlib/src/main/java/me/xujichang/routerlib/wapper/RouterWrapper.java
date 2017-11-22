package me.xujichang.routerlib.wapper;

import android.content.Context;

import java.lang.ref.WeakReference;

import me.xujichang.routerlib.interfaces.RouterInternal;
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

    private static WeakReference<Context> sWeakReference;
    private static RouterWrapper sWrapper;
    private static PatternRouterInternal sPatternRouterInternal;

    private RouterWrapper() {
        sPatternRouterInternal = PatternRouterInternal.getInternal();
    }

    private RouterWrapper(Builder builder) {
        String pattern = createPattern(builder);
    }

    private String createPattern(Builder builder) {
        String pattern = null;
        switch (builder.type) {
            case ActivityPatternRule.SCHEME_ACTIVITY:
                pattern = ActivityWrapper.wrapperPattern(builder);
                break;
            default:
        }
        return pattern;
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
        switch (type) {
            case ActivityPatternRule.SCHEME_ACTIVITY:
                sPatternRouterInternal.router(ActivityWrapper.createLines(flag,value));
                break;
            default:
        }
    }

    private static class ClassHolder {
        private static RouterWrapper sWrapper = new RouterWrapper();
    }

    public static Builder createBuilder(Context context) {
        return new Builder(context);
    }

    public static Builder createBuilder(String type) {
        return new Builder(type);
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private Context context;

        public Builder() {
        }

        public Builder(String type) {
            this.type = type;
        }

        public Builder(Context context) {
            this.context = context;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder withFlag() {
            return this;

        }

        public void invoke() {
            getWrapper().invoke(this);
        }

        public String getType() {
            return type;
        }

        public Context getContext() {
            return context;
        }
    }

    private void invoke(Builder builder) {
        String pattern = createPattern(builder);

//        sPatternRouterInternal.invoke(sWeakReference.get(), pattern);
    }
}
