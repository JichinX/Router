package me.xujichang.routerlib.base;

import android.net.Uri;

import java.util.HashMap;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 11:16.
 */

public abstract class BasePatternRule extends BaseStringRule<String> {
    /**
     * 转发线路集合,key 标志 value 路径不同的子类对其有不同的解析、处理方式
     */
    protected HashMap<String, String> mLines;

    public BasePatternRule() {
        mLines = new HashMap<>();
    }

    @Override
    public void router(String pattern, String value) {
        String flag = getFlag(pattern);
        mLines.put(flag, value);
    }

    /**
     * 从Pattern中取出flag
     *
     * @param pattern
     * @return
     */
    protected String getFlag(String pattern) {
        Uri uri = Uri.parse(pattern);
        return uri.getPath();
    }

    @Override
    public boolean resolveRule(String pattern) {
        String flag = getFlag(pattern);
        return mLines.containsKey(flag);
    }

    public abstract void router(String lines);
}
