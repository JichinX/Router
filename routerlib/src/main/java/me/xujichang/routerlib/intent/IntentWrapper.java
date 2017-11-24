package me.xujichang.routerlib.intent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.List;

import me.xujichang.routerlib.bean.ActivityOptions;
import me.xujichang.routerlib.exceptions.NotFindClassException;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/24 11:03.
 */

public class IntentWrapper {
    private static final String TAG = "IntentWrapper";

    /**
     * 创建跳转 Activity需要的Intent
     *
     * @param context
     * @param className
     * @param options
     * @return
     */
    public static Intent createActivityIntent(Context context, String className, ActivityOptions options) {
        Intent intent = new Intent();
        intent.setClassName(context.getPackageName(), className);
        if (!checkClassValid(context, intent)) {
            throw new NotFindClassException(context.getPackageName(), className);
        }
        return intent;
    }

    private static boolean checkClassValid(Context context, Intent intent) {
        List<ResolveInfo> infos = context.getPackageManager().queryIntentActivities(intent, 0);
        return infos.size() > 0;
    }
//
//    protected void doStartApplicationWithPackageName(String packagename, String appName,
//                                                     Bundle bundle) {
//        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
//        PackageInfo packageinfo = null;
//        try {
//            packageinfo = getPackageManager().getPackageInfo(packagename, 0);
//        } catch (PackageManager.NameNotFoundException e) {
//            showNeedModuleDialog(packagename, appName);
//            return;
//        }
//        if (packageinfo == null) {
//            showNeedModuleDialog(packagename, appName);
//            return;
//        }
//        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
//        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
//        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        resolveIntent.setPackage(packageinfo.packageName);
//        // 通过getPackageManager()的queryIntentActivities方法遍历
//        List<ResolveInfo> resolveinfoList = getPackageManager()
//                .queryIntentActivities(resolveIntent, 0);
//        if (null == resolveinfoList) {
//            showToast("启动失败：ResolveInfo 集合为null");
//            return;
//        }
//        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
//        if (resolveinfo != null) {
//            // packagename = 参数packname
//            String packageName = resolveinfo.activityInfo.packageName;
//            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
//            String className = resolveinfo.activityInfo.name;
//            // LAUNCHER Intent
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            intent.setFlags(
//                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//            // 设置ComponentName参数1:packagename参数2:MainActivity路径
//            ComponentName cn = new ComponentName(packageName, className);
//
//            intent.setComponent(cn);
//            if (null != bundle) {
//                intent.putExtra("data", bundle);
//            }
//            startActivity(intent);
//        }
//    }
}
