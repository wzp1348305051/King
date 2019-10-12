package com.wzp.king;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wzp.king.common.activity.BaseSwipeBackSupportActivity;

@Route(path = "/app/main")
public class MainActivity extends BaseSwipeBackSupportActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initMemberData() {

    }

    @Override
    public void bindContentView(Bundle savedInstanceState) {

    }
}
