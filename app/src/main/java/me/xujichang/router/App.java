package me.xujichang.router;

import android.app.Application;
import android.content.Context;

import me.xujichang.routerlib.wapper.RouterWrapper;

/**
 * Des:
 *
 * @author xjc
 *         Created on 2017/11/22 14:09.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        RouterWrapper.init(base);
    }
}
