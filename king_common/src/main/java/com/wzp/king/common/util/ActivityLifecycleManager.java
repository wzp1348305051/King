package com.wzp.king.common.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * 全局Activity生命收起管理类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/27
 */

public class ActivityLifecycleManager implements Application.ActivityLifecycleCallbacks {

    public static ActivityLifecycleManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ActivityLifecycleManager INSTANCE = new ActivityLifecycleManager();
    }

    private ActivityLifecycleManager() {

    }

    public void init(@NonNull Application application) {
        application.registerActivityLifecycleCallbacks(getInstance());
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
