package com.youer.androiskilltree;

import android.app.Application;
import android.content.Context;
import com.youer.reflection.Hook;

/**
 * @author youer
 * @date 2021/12/27
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // hook相关的事件
        Hook.hookPackageManager(base);

    }
}