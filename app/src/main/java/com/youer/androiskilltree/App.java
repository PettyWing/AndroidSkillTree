package com.youer.androiskilltree;

import android.app.Application;
import android.content.Context;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.youer.reflection.Hook;

/**
 * @author youer
 * @date 2021/12/27
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .tag("AndroidSkillTree")
            .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // hook相关的事件
        Hook.hookPackageManager(base);

    }
}