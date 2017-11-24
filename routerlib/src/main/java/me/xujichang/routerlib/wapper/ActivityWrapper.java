package me.xujichang.routerlib.wapper;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import me.xujichang.routerlib.bean.ActivityOptions;
import me.xujichang.routerlib.builder.ActivityOptionsBuilder;
import me.xujichang.routerlib.rules.ActivityPatternRule;

/**
 * private String mAction;
 * private Uri mData;
 * private String mType;
 * private String mPackage;
 * private ComponentName mComponent;
 * private int mFlags;
 * private ArraySet<String> mCategories;
 * private Bundle mExtras;
 * private Rect mSourceBounds;
 * private Intent mSelector;
 * private ClipData mClipData;
 * <p>
 * activity://authority/path?query&key=value#fragment
 * Des:
 * 此对应冠以以及解析方案适用于Activity
 * activity  -操作对象类型
 * authority -查找目标Activity的key,
 * path      - TODO 另行配置
 * query     - 解析query,不匹配key=value的query参数 TODO 另行配置
 * key=value - 解析query时，拥有Key、Value的除原有key外，
 * 均是intent中的extras,是{@link android.content.Intent#putExtra)}所赋予的值
 * fragment  -Activtiy的操作：finish start startForResult 等等
 *
 * @author xjc
 *         Created on 2017/11/22 14:25.
 */
public class ActivityWrapper implements BaseWrapper {
    public static final String ACTIVITY_REQUEST_CODE = "activity_request_code";
    public static final String ACTIVITY_START_ACTION = "activity_action";
    public static final String ACTIVITY_FLAGS_ADD = "activity_flags_add";
    public static final String ACTIVITY_FLAGS_SET = "activity_flags_set";

    /**
     * 将操作Activity的Builder转换为Uri格式
     *
     * @param builder
     * @return
     */
    public static String wrapperPattern(ActivityOptionsBuilder builder) {
        ActivityOptions options = builder.getOptions();
        Uri.Builder build = new Uri.Builder()
                .scheme(ActivityPatternRule.SCHEME_ACTIVITY)
                .fragment(options.getStartType() == 0 ?
                        String.valueOf(ActivityPatternRule.START_NORMAL) :
                        String.valueOf(options.getStartType()))
                //Activity  key
                .authority(options.getExecKey());
        //action
        if (!TextUtils.isEmpty(options.getAction())) {
            build.appendQueryParameter(ACTIVITY_START_ACTION, options.getAction());
        }
        //requestCode code  type都有效 才能正常启动
        if (0 != options.getRequestCode() && options.getStartType() == ActivityPatternRule.START_RESULT) {
            build.appendQueryParameter(ACTIVITY_REQUEST_CODE, String.valueOf(options.getRequestCode()));
        }
        //flagsSet
        if (0 != options.getFlagsSet()) {
            build.appendQueryParameter(ACTIVITY_FLAGS_SET, String.valueOf(options.getFlagsSet()));
        }
        //flagsAdd
        if (0 != options.getFlagsAdd()) {
            build.appendQueryParameter(ACTIVITY_FLAGS_ADD, String.valueOf(options.getFlagsAdd()));
        }
        //extrasData
        if (null != options.getExtraData()) {
            Bundle bundle = options.getExtraData();
            for (String key : bundle.keySet()) {
                build.appendQueryParameter(key, bundle.getString(key));
            }
        }
        return build.build().toString();
    }

    /**
     * 创建Activity路由的转发线路->Uri格式
     *
     * @param flag
     * @param value
     * @return
     */
    public static String createLines(String flag, String value) {
        Uri uri = new Uri.Builder()
                .scheme(ActivityPatternRule.SCHEME_ACTIVITY)
                .authority(flag)
                .path(value)
                .build();
        return uri.toString();
    }

    /**
     * 解析Activity 的转发线路
     *
     * @param lines
     * @return
     */
    public static Map<String, String> parseLines(String lines) {
        Uri uri = Uri.parse(lines);
        Map<String, String> map = new HashMap<>();
        String key = uri.getAuthority();
        String value = uri.getPath();
        value = value.substring(1, value.length());
        map.put(key, value);
        return map;
    }

    /**
     * 解析Uri 转换为ActivityOptions
     *
     * @param pattern
     * @return
     */
    public static ActivityOptions parsePattern(String pattern) {
        Uri uri = Uri.parse(pattern);
        ActivityOptions options = new ActivityOptions();
        //Activity  key
        options.setExecKey(uri.getAuthority());
        //startType
        if (!TextUtils.isEmpty(uri.getFragment())) {
            options.setStartType(Integer.parseInt(uri.getFragment()));
        }
        Set<String> parmeters = uri.getQueryParameterNames();
        Iterator<String> iterator = parmeters.iterator();
        //FlagsAdd
        if (parmeters.contains(ACTIVITY_FLAGS_ADD)) {
            options.setFlagsAdd(Integer.parseInt(uri.getQueryParameter(ACTIVITY_FLAGS_ADD)));
            parmeters.remove(ACTIVITY_FLAGS_ADD);
        }
        //action
        if (parmeters.contains(ACTIVITY_START_ACTION)) {
            options.setAction(uri.getQueryParameter(ACTIVITY_START_ACTION));
            parmeters.remove(ACTIVITY_START_ACTION);
        }
        //flagsSet
        if (parmeters.contains(ACTIVITY_FLAGS_SET)) {
            options.setFlagsSet(Integer.parseInt(uri.getQueryParameter(ACTIVITY_FLAGS_SET)));
            parmeters.remove(ACTIVITY_FLAGS_SET);
        }
        //requestCode
        if (options.getStartType() == ActivityPatternRule.START_RESULT && parmeters.contains(ACTIVITY_REQUEST_CODE)) {
            options.setRequestCode(Integer.parseInt(uri.getQueryParameter(ACTIVITY_REQUEST_CODE)));
            parmeters.remove(ACTIVITY_REQUEST_CODE);
        }
        //extraData
        if (parmeters.size() > 0) {
            for (String parmeter : parmeters) {
                options.setExtraData(parmeter, uri.getQueryParameter(parmeter));
            }
        }
        return options;
    }
}
