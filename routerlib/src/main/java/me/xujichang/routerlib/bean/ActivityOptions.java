package me.xujichang.routerlib.bean;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * activity://authority/path?query&key=value#fragment
 * Des:
 * 提供对Activity的操作
 *
 * @author xjc
 *         Created on 2017/11/22 17:52.
 */

public class ActivityOptions {
    /**
     * 操作对象类型
     */
    private String type;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 标志 添加
     */
    private int flagsAdd;
    /**
     * 标志设置
     */
    private int flagsSet;
    /**
     * action
     */
    private String action;
    /**
     * {@link android.app.Activity#startActivityForResult(Intent, int, Bundle)}
     */
    private Bundle requestOptions;
    /**
     * 请求的ID
     */
    private int requestCode;
    /**
     * 执行的目标key
     */
    private String execKey;
    /**
     * 提供的Intent
     */
    private Intent preIntent;
    /**
     * 携带的数据
     */
    private Bundle extraData;
    /**
     * 启动类型
     */
    private int startType;

    public ActivityOptions(Context context, String type) {
        setContext(context);
        setType(type);
    }

    public ActivityOptions() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getFlagsAdd() {
        return flagsAdd;
    }

    public void setFlagsAdd(int flagsAdd) {
        this.flagsAdd = flagsAdd;
    }

    public int getFlagsSet() {
        return flagsSet;
    }

    public void setFlagsSet(int flagsSet) {
        this.flagsSet = flagsSet;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Bundle getRequestOptions() {
        return requestOptions;
    }

    public void setRequestOptions(Bundle requestOptions) {
        this.requestOptions = requestOptions;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public String getExecKey() {
        return execKey;
    }

    public void setExecKey(String execKey) {
        this.execKey = execKey;
    }

    public Intent getPreIntent() {
        return preIntent;
    }

    public void setPreIntent(Intent preIntent) {
        this.preIntent = preIntent;
    }

    public Bundle getExtraData() {
        return extraData;
    }

    public void setExtraData(Bundle extraData) {
        if (null == this.extraData) {
            this.extraData = extraData;
        } else {
            this.extraData.putAll(extraData);
        }
    }

    public int getStartType() {
        return startType;
    }

    public void setStartType(int startType) {
        this.startType = startType;
    }

    public void setExtraData(String key, String value) {
        if (extraData == null) {
            extraData = new Bundle();
        }
        extraData.putString(key, value);
    }
}
