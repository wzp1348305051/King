package com.wzp.king;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wzp.king.common.activity.BaseSwipeBackSupportActivity;
import com.wzp.king.common.widget.SingleClickListener;

import androidx.annotation.NonNull;
import butterknife.BindView;

public class LauncherActivity extends BaseSwipeBackSupportActivity {
    @BindView(R.id.iv_launcher_content)
    ImageView mIvContent;

    @Override
    public int getContentView() {
        return R.layout.activity_launcher;
    }

    @Override
    public void initMemberData() {

    }

    @Override
    public void bindContentView(Bundle savedInstanceState) {
        mIvContent.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(@NonNull View v) {
                ARouter.getInstance().build("/app/main").navigation(_mActivity);
            }
        });
    }
}
