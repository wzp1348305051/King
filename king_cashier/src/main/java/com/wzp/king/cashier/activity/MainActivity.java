package com.wzp.king.cashier.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.navigation.NavigationView;
import com.wzp.king.cashier.R;
import com.wzp.king.cashier.R2;
import com.wzp.king.cashier.bean.CashierPath;
import com.wzp.king.common.activity.BaseActivity;

import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

@Route(path = CashierPath.PATH_MAIN)
public class MainActivity extends BaseActivity {
    private static final int INDEX_CASHIER = 0;
    private static final int INDEX_EDIT = 1;
    private static final int INDEX_REPORT = 2;
    private static final int INDEX_SETTING = 3;
    @BindView(R2.id.dl_main_root)
    DrawerLayout mDlRoot;
    @BindView(R2.id.nv_main_menu)
    NavigationView mNvMenu;
    private int mCurrentMenuIndex;
    private SupportFragment[] mFragmentList;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initMemberData() {
    }

    @Override
    public void bindContentView(Bundle savedInstanceState) {
        mHeaderView.setVisibility(View.GONE);
    }
}
