package me.xujichang.routerlib.bean;

import android.net.Uri;

/**
 * activity://authority/path?query&key=value#fragment
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 17:52.
 */

public class ActivityOptions {
    private int flagsSet;
    private int flagsAdd;
    private String category;
    private String action;
    private String packageName;
    private Uri data;
    private String className;

    public ActivityOptions(Uri uri) {
    }

    public int getFlagsSet() {
        return flagsSet;
    }

    public int getFlagsAdd() {
        return flagsAdd;
    }

    public String getCategory() {
        return category;
    }

    public String getAction() {
        return action;
    }

    public String getPackageName() {
        return packageName;
    }

    public Uri getData() {
        return data;
    }

    public String getClassName() {
        return className;
    }
}
