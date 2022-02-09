package com.youer.androiskilltree;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.startup.Initializer;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author youer
 * @date 2022/2/7
 */
public class Initer implements Initializer {
    @NonNull
    @Override
    public Object create(@NonNull Context context) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .tag("AndroidSkillTree")
            .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return new ArrayList<>();
    }
}