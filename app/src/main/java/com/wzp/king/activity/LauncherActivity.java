package com.wzp.king.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wzp.king.R;
import com.wzp.king.bean.AppPath;
import com.wzp.king.common.activity.BaseActivity;
import com.wzp.king.common.util.ThreadPool;

@Route(path = AppPath.PATH_LAUNCHER)
public class LauncherActivity extends BaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_launcher;
    }

    @Override
    public void initMemberData() {

    }

    @Override
    public void bindContentView(Bundle savedInstanceState) {
        mHeaderView.setVisibility(View.GONE);

        ThreadPool.scheduleInMainThread(() -> {
            ARouter.getInstance().build(AppPath.PATH_LOGIN).navigation(mActivity);
            finish();
        }, 2000);
    }
}
