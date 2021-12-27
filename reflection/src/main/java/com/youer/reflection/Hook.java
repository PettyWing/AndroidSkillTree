package com.youer.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

/**
 * @author youer
 * @date 2021/12/27
 */
public class Hook {

    private static final String TAG = "Hook";

    public static void hookPackageManager(Context context) {
        //越早 hook 越好，推荐在 attachBaseContext 调用
        Class activityThreadClz = null;
        try {
            // 获取ActivityThread
            activityThreadClz = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = activityThreadClz.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            Object activityThread = currentActivityThread.invoke(null);

            // 获取packageManager
            Field packageManagerField = activityThreadClz.getDeclaredField("sPackageManager");
            packageManagerField.setAccessible(true);
            final Object packageManager = packageManagerField.get(activityThread);

            // 动态代理处理数据
            Class<?> packageManagerClazz = Class.forName("android.content.pm.IPackageManager", false,
                context.getClassLoader());
            Object proxy = Proxy.newProxyInstance(context.getClassLoader(), new Class[] {packageManagerClazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = method.invoke(packageManager, args);
                        if ("getPackageInfo".equals(method.getName())) {
                            PackageInfo packageInfo = (PackageInfo)result;
                            packageInfo.versionName = "sdsds";
                        }
                        return result;
                    }
                });
            //hook sPackageManager
            packageManagerField.set(activityThread, proxy);

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void test(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            Log.d(TAG, pi.versionName);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }
} 