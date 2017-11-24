package me.xujichang.routerlib.builder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import me.xujichang.routerlib.bean.ActivityOptions;
import me.xujichang.routerlib.wapper.ActivityWrapper;
import me.xujichang.routerlib.wapper.RouterWrapper;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/24 14:30.
 */

public class ActivityOptionsBuilder implements BaseBuilder<ActivityOptions> {
    private ActivityOptions mActivityOptions;
    private RouterWrapper mWrapper;

    public ActivityOptionsBuilder(Context context, String type, RouterWrapper wrapper) {
        mActivityOptions = new ActivityOptions(context, type);
        mWrapper = wrapper;
    }

    public ActivityOptionsBuilder withType(String type) {
        mActivityOptions.setType(type);
        return this;
    }

    public ActivityOptionsBuilder withContext(Context context) {
        mActivityOptions.setContext(context);
        return this;
    }

    public ActivityOptionsBuilder addFlags(int flags) {
        mActivityOptions.setFlagsAdd(flags);
        return this;
    }

    public ActivityOptionsBuilder setFlags(int flags) {
        mActivityOptions.setFlagsSet(flags);
        return this;
    }

    public ActivityOptionsBuilder setAction(String actions) {
        mActivityOptions.setAction(actions);
        return this;
    }

    public ActivityOptionsBuilder requestResult(int requestNumber, Bundle options) {
        mActivityOptions.setRequestCode(requestNumber);
        mActivityOptions.setRequestOptions(options);
        return this;
    }


    public ActivityOptionsBuilder withIntent(Intent intent) {
        mActivityOptions.setPreIntent(intent);
        return this;
    }

    public ActivityOptionsBuilder withExtras(Bundle extras) {
        mActivityOptions.setExtraData(extras);
        return this;
    }

    public ActivityOptionsBuilder requestResult(int requestCode) {
        mActivityOptions.setRequestCode(requestCode);
        return this;
    }

    public ActivityOptionsBuilder withStartType(int startType) {
        mActivityOptions.setStartType(startType);
        return this;
    }

    public void invoke(String key) {
        mActivityOptions.setExecKey(key);
        invoke();
    }

    public ActivityOptionsBuilder withExecKey(String key) {
        mActivityOptions.setExecKey(key);
        return this;
    }

    public void invoke() {
        if (!checkKeyUseful(mActivityOptions.getExecKey())) {
            throw new RuntimeException("无法确定此次执行目标");
        }
        mWrapper.invoke(this);
    }


    private boolean checkKeyUseful(String execKey) {
        return !TextUtils.isEmpty(execKey);
    }

    @Override
    public String createPattern() {
        return ActivityWrapper.wrapperPattern(this);
    }

    @Override
    public Context getContext() {
        return mActivityOptions.getContext();
    }

    @Override
    public ActivityOptions getOptions() {
        return mActivityOptions;
    }
}
