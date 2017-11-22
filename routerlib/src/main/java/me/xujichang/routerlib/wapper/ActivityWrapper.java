package me.xujichang.routerlib.wapper;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

import me.xujichang.routerlib.bean.ActivityOptions;
import me.xujichang.routerlib.rules.ActivityPatternRule;

/**
 * activity://authority/path?query&key=value#fragment
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 14:25.
 */

public class ActivityWrapper implements BaseWrapper {

    public static String wrapperPattern(RouterWrapper.Builder builder) {
        Uri uri = new Uri.Builder()
                .scheme(builder.getType())
                .path("path")
                .fragment("fragment")
                .authority("authority")
                .query("query")
                .appendQueryParameter("key", "value")
                .build();
        return uri.toString();
    }

    public static String createLines(String flag, String value) {
        Uri uri = new Uri.Builder()
                .scheme(ActivityPatternRule.SCHEME_ACTIVITY)
                .authority(flag)
                .path(value)
                .build();
        return uri.toString();
    }

    public static Map<String, String> parseLines(String lines) {
        Uri uri = Uri.parse(lines);
        Map<String, String> map = new HashMap<>();
        map.put(uri.getAuthority(), uri.getPath());
        return map;
    }

    public static ActivityOptions parsePattern(String pattern) {
        Uri uri = Uri.parse(pattern);
        return new ActivityOptions(uri);
    }
}
